package com.application.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import com.application.common.Context;
import com.application.service.FehFileServer;
import com.application.service.FileCopeServer;
import com.application.util.StringUtils;

public class RenamePicturesFrame extends JFrame {

    private static final long serialVersionUID = 2676545922854797174L;

    private static final String TITLE = "重命名立绘";

    /* 标准宽度 */
    private static final int BASE_WIDTH = 100;

    /* 标准高度 */
    private static final int BASE_HEIGHT = 30;

    /* 开始坐标 */
    private int startX, startY, defaultX = 10;

    /* 窗体的宽高 */
    private int maxX, maxY = 10;

    private JPanel jp;

    /* 按钮 */
    private JButton renameImgBtn, moveImgBtn, renameRadioBtn;

    /* 背景图片 */
    private String netImgUrl = "http://m.qpic.cn/psc?/V51v220v2C0E493HVi1R4bhVpB2uqOS1/bqQfVz5yrrGYSXMvKr.cqYxKVrE3jvWZl8YQ2wR3actp9tPsaTqo5FQiyG.O9pI.ix*aBYwZwqINsyuZMERgc1rzBJ.9fEng*C5yHu.N*IU!/b&bo=WAKQAQAAAAABB.s!&rf=viewer_4";

    /* 图标 */
    private String iconImg = "/icon.png";

    /* 结果表示 */
    private JTextArea result;

    /* 标题 */
    private JLabel baseDirLbl, copyToLbl, radioDirLbl, sessionLbl1;
    /* 输入框 */
    private JTextField baseDir, copyTo, radioDir, sessionNo;

    /* 标题 */
    private JLabel gameNmLbl;
    /* 下拉框 */
    private JComboBox<String> gameName;

    public static void main(String[] args) {
        new RenamePicturesFrame("重命名立绘");
    }

    RenamePicturesFrame(String str) {
        super(TITLE);
        startY = 10;
        jp = (JPanel) getContentPane();
        SwingComnon.initFrame(this, jp, netImgUrl, iconImg);
        setLayout();
        SwingComnon.initFrameOption(this, 600, 400, true, str);
    }

    /**
     * 设置页面布局
     */
    private void setLayout() {
        baseDirLbl = new JLabel("源目录");
        setComponet(baseDirLbl, BASE_WIDTH, BASE_HEIGHT, false);
        baseDir = new JTextField("C:\\Users\\dylsw\\OneDrive\\图片\\feTemp");
        setComponet(baseDir, BASE_WIDTH * 4, BASE_HEIGHT, true);

        copyToLbl = new JLabel("拷贝目录");
        setComponet(copyToLbl, BASE_WIDTH, BASE_HEIGHT, false);
        copyTo = new JTextField("D:\\output");
        setComponet(copyTo, BASE_WIDTH * 4, BASE_HEIGHT, true);

        gameNmLbl = new JLabel("游戏名");
        setComponet(gameNmLbl, BASE_WIDTH, BASE_HEIGHT, false);
        gameName = new JComboBox<String>(new String[] { "FEH", "FGO" });
        setComponet(gameName, BASE_WIDTH, BASE_HEIGHT, false);

        renameImgBtn = new JButton("重命名");
        renameImgBtn.addActionListener(renameImages());
        setComponet(renameImgBtn, BASE_WIDTH, BASE_HEIGHT, false);

        moveImgBtn = new JButton("复制上传文件");
        moveImgBtn.addActionListener(moveImages());
        setComponet(moveImgBtn, 150, BASE_HEIGHT, true);

        radioDirLbl = new JLabel("视频目录");
        setComponet(radioDirLbl, BASE_WIDTH, BASE_HEIGHT, false);
        radioDir = new JTextField();
        setComponet(radioDir, 160, BASE_HEIGHT, false);
        sessionLbl1 = new JLabel("SESSION:");
        setComponet(sessionLbl1, 60, BASE_HEIGHT, false);
        sessionNo = new JTextField("1");
        setComponet(sessionNo, 20, BASE_HEIGHT, false);
        renameRadioBtn = new JButton("重命名");
        renameRadioBtn.addActionListener(renameRadio());
        setComponet(renameRadioBtn, 80, BASE_HEIGHT, true);

        // 带滚动条log
        result = new JTextArea("");
        result.setEditable(false);
        result.setOpaque(false);
        result.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
        JScrollPane scrollP = new JScrollPane();
        scrollP.getViewport().setOpaque(false);
        scrollP.setViewportView(result);
        setComponet(scrollP, 560, 200, true);
    }

    /**
     * 取得画面上输入信息
     *
     * @return 信息键值对
     */
    private Map<String, String> getInputValue() {
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put(Context.BASE_DIR, baseDir.getText());
        resultMap.put(Context.COPY_TO, copyTo.getText());
        resultMap.put(Context.GAME_NAME, (String) gameName.getSelectedItem());

        return resultMap;
    }

    /**
     * 设置组件位置
     *
     * @param compant  组件
     * @param width    组件宽度
     * @param height   组件高度
     * @param lineFlag 是否换行
     */
    private void setComponet(JComponent compant, int width, int height, boolean lineFlag) {
        startX = startX == 0 ? 10 : startX;
        compant.setBounds(startX, startY, width, height);
        compant.setOpaque(false);
        startX += width + 20;
        if (lineFlag) {
            startY += height + 10;
            startX = defaultX;
        }
        maxX = startX + width > maxX + width ? maxX + width : maxX;
        maxY = startY + height > maxY ? maxY + height : maxY;
        // 添加组件
        jp.add(compant);
    }

    /**
     * 打开生成代码页面
     *
     * @return
     */
    private ActionListener renameImages() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                FehFileServer server = new FehFileServer();
                Map<String, String> modelValueMap = getInputValue();
                String folderPath = modelValueMap.get(Context.BASE_DIR);
                String type = modelValueMap.get(Context.GAME_NAME);
                StringBuilder log = new StringBuilder();
                // 验证
                if (StringUtils.isEmpty(folderPath) || StringUtils.isEmpty(type)) {
                    log.append("请确认文件夹和游戏!");
                    result.setText(log.toString());
                    return;
                }
                try {
                    log.append(server.fehImgRename(folderPath, type, log));
                    log.append("处理文件数：" + FehFileServer.COUNT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                result.setText(log.toString());
            }
        };
    }

    private ActionListener renameRadio() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                FehFileServer server = new FehFileServer();
                String folderPath = radioDir.getText();
                StringBuilder log = new StringBuilder();
                // 验证
                if (StringUtils.isEmpty(folderPath)) {
                    log.append("请确认文件夹和游戏!");
                    result.setText(log.toString());
                    return;
                }
                try {
                    log.append(server.radioRename(folderPath, sessionNo.getText(), 1));
                    log.append("处理文件数：" + FehFileServer.COUNT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                result.setText(log.toString());
            }
        };
    }

    /**
     * 复制要上传的文件并重命名
     *
     * @return
     */
    private ActionListener moveImages() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                FileCopeServer server = new FileCopeServer();
                Map<String, String> modelValueMap = getInputValue();
                String basePath = modelValueMap.get(Context.BASE_DIR);
                String targetPath = modelValueMap.get(Context.COPY_TO);
                StringBuilder log = new StringBuilder();
                // 验证
                if (StringUtils.isEmpty(basePath) || StringUtils.isEmpty(targetPath)) {
                    log.append("请确认源文件夹和复制文件夹");
                    result.setText(log.toString());
                    return;
                }
                try {
                    server.createUploadImg(basePath, targetPath, log);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                result.setText(log.toString());
            }
        };
    }

}
