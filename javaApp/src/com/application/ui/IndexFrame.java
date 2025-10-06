package com.application.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class IndexFrame extends JFrame {

    IndexFrame thisFrame;

    private static final long serialVersionUID = 4783178871399676541L;

    /* 开始坐标 */
    private static int startX, defaultX = 0;

    private static int startY = 0;

    private int maxX = 600;

    private JPanel jp;

    private JDesktopPane desktopPanel;

    /* 背景图片 */
    private String netImgUrl = "http://m.qpic.cn/psc?/V51v220v2C0E493HVi1R4bhVpB2uqOS1/bqQfVz5yrrGYSXMvKr.cqYxKVrE3jvWZl8YQ2wR3actp9tPsaTqo5FQiyG.O9pI.ix*aBYwZwqINsyuZMERgc1rzBJ.9fEng*C5yHu.N*IU!/b&bo=WAKQAQAAAAABB.s!&rf=viewer_4";

    /* 图标 */
    private String iconImg = "/icon.png";

    public static void main(String[] args) {
        startX = -1;
        startY = -1;
        new IndexFrame("首页");
    }

    IndexFrame(String str) {
        super(str);
        desktopPanel = new JDesktopPane();
        desktopPanel.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        jp = (JPanel) getContentPane();
        jp.add(desktopPanel, BorderLayout.CENTER);

        SwingComnon.initFrame(this, jp, netImgUrl, iconImg);
        setLayout();
        SwingComnon.initFrameOption(this, maxX, 400, false, str);
    }

    /**
     * 设置页面布局
     */
    private void setLayout() {
        // 菜单栏
        JMenuBar menuBar = SwingComnon.createTopMenuBar("java", "index", "top", topMenuActionListener, this);
        setComponet(menuBar, maxX, 30, true, true);
    }

    /**
     * 设置组件位置
     *
     * @param compant  组件
     * @param width    组件宽度
     * @param height   组件高度
     * @param lineFlag 是否换行
     */
    private void setComponet(JComponent compant, int width, int height, boolean opaque, boolean lineFlag) {
        startX = startX == 0 ? 10 : startX;
        compant.setBounds(startX, startY, width, height);
        compant.setOpaque(opaque);
        startX += width + 20;
        if (lineFlag) {
            startY += height + 10;
            startX = defaultX;
        }
        maxX = startX > maxX ? maxX + width : maxX;
        // 添加组件
        jp.add(compant);
    }

    ActionListener topMenuActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            String action = arg0.getActionCommand();
            System.out.println(action);
            if ("SourcesCreater".equals(action)) {
                new SourcesCreater(JFrame.DISPOSE_ON_CLOSE + "");
            } else if ("RenamePicturesFrame".equals(action)) {
                new RenamePicturesFrame(JFrame.DISPOSE_ON_CLOSE + "");
            } else if ("HERO".equals(action)) {
                new FeHeroFrame(6, JFrame.DISPOSE_ON_CLOSE + "");
            } else if ("PrintBound".equals(action)) {
                SwingComnon.showFrameInfo(jp);
            } else {
                new HtmlFrame(JFrame.DISPOSE_ON_CLOSE + "");
            }
        }
    };

}
