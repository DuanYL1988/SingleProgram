package com.application.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import com.application.common.Context;
import com.application.util.TextUtil;

public class SourcesCreater extends JFrame {

    private static final long serialVersionUID = 4783178871399676541L;

    private static final String TITLE = "模板";

    /* 标准宽度 */
    private static final int BASE_WIDTH = 100;

    /* 标准高度 */
    private static final int BASE_HEIGHT = 30;

    /* 开始坐标 */
    private int startX, startY, defaultX = 10;

    /* 窗体的宽高 */
    private int maxX, maxY = 100;

    private static String PROJ_PATH = new File("").getAbsolutePath();

    /* 按钮 */
    private JButton btn1, btn2;

    /* 标题 */
    private JLabel projLbl, locationLbl, pkgLbl, tblLbl, projTypeLbl;
    /* 输入框 */
    private JTextField projectNm, location, packageNm;

    /* 多选框 */
    private JCheckBox cntrFlg, serFlg, daoFlg, mybatisFlg, jpaFlg, htmlFlg, allTblFlg, restFulFlg;

    /* 标题 */
    private JLabel projKbnLbl, pageKbnLbl, dbTypeLbl;

    /* 单选按钮 */
    private JRadioButton thymeleafBtn, jspBtn, oracleBtn, mysqlBtn, springbootBtn, javaBtn;

    /* 下拉框 */
    private JComboBox<String> ddlList;

    /* 结果表示 */
    private JTextArea result;

    private JPanel jp;

    private String netImgUrl = "http://m.qpic.cn/psc?/V51v220v2C0E493HVi1R4bhVpB2uqOS1/bqQfVz5yrrGYSXMvKr.cqY6G86vx90y.6DC5tUWkQB3UzwEA0kH4r0jI5FqQZoy*eKBRI.QywhDJkcjJK8cIZ*Xm.CgO4f0uyIj.2zxelVM!/b&bo=*gE.A*4BPgMDByI!&rf=viewer_4";

    /* 图标 */
    private String iconImg = "/icon.png";

    MainFrameAction service = new MainFrameAction();

    public static void main(String[] args) {
        new SourcesCreater("代码生成工具");
    }

    SourcesCreater(String str) {
        super(TITLE);
        jp = (JPanel) getContentPane();
        SwingComnon.initFrame(this, jp, netImgUrl, iconImg);
        setLayout();
        setInitStatus();
        SwingComnon.initFrameOption(this, maxX + 35, maxY + 20, false, str);
    }

    private void setLayout() {
        // 输入内容
        projLbl = new JLabel("项目名");
        setComponet(projLbl, BASE_WIDTH / 2, BASE_HEIGHT, false);
        String projNm = "Thymeleaf_Proj";
        projectNm = new JTextField(projNm);
        setComponet(projectNm, 4 * BASE_WIDTH, BASE_HEIGHT, true);

        locationLbl = new JLabel("路径");
        setComponet(locationLbl, BASE_WIDTH / 2, BASE_HEIGHT, false);
        location = new JTextField("路径");
        setComponet(location, 4 * BASE_WIDTH, BASE_HEIGHT, true);
        pkgLbl = new JLabel("包名");
        setComponet(pkgLbl, BASE_WIDTH / 2, BASE_HEIGHT, false);
        packageNm = new JTextField("包名");
        setComponet(packageNm, 4 * BASE_WIDTH, BASE_HEIGHT, true);
        /** onchange事件 */
        projectNm.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent arg0) {
                setLocation(projectNm.getText());
            }

            @Override
            public void focusGained(FocusEvent arg0) {
            }
        });
        tblLbl = new JLabel("表名");
        setComponet(tblLbl, BASE_WIDTH / 2, BASE_HEIGHT, false);
        ddlList = new JComboBox<String>(getDdlList(true));
        // 下拉框设置透明
        SwingComnon.setJcompoOpaque(ddlList);
        setComponet(ddlList, 3 * BASE_WIDTH, BASE_HEIGHT, false);
        allTblFlg = new JCheckBox("全生成");
        setComponet(allTblFlg, 80, BASE_HEIGHT, true);

        // 选项
        cntrFlg = new JCheckBox("生成Controller类");
        setComponet(cntrFlg, 150, BASE_HEIGHT, false);
        restFulFlg = new JCheckBox("RestFul类");
        JComponent[] ts1 = new JComponent[] { restFulFlg };
        cntrFlg.addActionListener(checkBoxCombo(cntrFlg, ts1));
        setComponet(restFulFlg, BASE_WIDTH, BASE_HEIGHT, true);

        serFlg = new JCheckBox("生成Service类");
        setComponet(serFlg, 150, BASE_HEIGHT, false);
        daoFlg = new JCheckBox("生成Dao类");
        setComponet(daoFlg, 150, BASE_HEIGHT, true);

        /* 数据库相关 */
        dbTypeLbl = new JLabel("数据库");
        setComponet(dbTypeLbl, 40, BASE_HEIGHT, false);
        ButtonGroup dbKbnGp = new ButtonGroup();
        oracleBtn = new JRadioButton("Oracle");
        mysqlBtn = new JRadioButton("MySql");
        dbKbnGp.add(oracleBtn);
        dbKbnGp.add(mysqlBtn);
        setComponet(oracleBtn, 65, BASE_HEIGHT, false);
        setComponet(mysqlBtn, 65, BASE_HEIGHT, false);
        projKbnLbl = new JLabel("持久化框架");
        setComponet(projKbnLbl, 70, BASE_HEIGHT, false);
        mybatisFlg = new JCheckBox("mybatis");
        setComponet(mybatisFlg, 80, BASE_HEIGHT, false);
        jpaFlg = new JCheckBox("jpa");
        setComponet(jpaFlg, 50, BASE_HEIGHT, true);

        /* 工程区分 */
        projTypeLbl = new JLabel("项目类型");
        setComponet(projTypeLbl, BASE_WIDTH, BASE_HEIGHT, false);
        springbootBtn = new JRadioButton("SpringBoot");
        setComponet(springbootBtn, BASE_WIDTH, BASE_HEIGHT, false);
        javaBtn = new JRadioButton("Java Swing");
        setComponet(javaBtn, BASE_WIDTH, BASE_HEIGHT, true);
        ButtonGroup projTyGp = new ButtonGroup();
        projTyGp.add(springbootBtn);
        projTyGp.add(javaBtn);
        JComponent[] ts2 = new JComponent[] { oracleBtn, mysqlBtn, mybatisFlg, jpaFlg, springbootBtn, javaBtn };
        daoFlg.addActionListener(checkBoxCombo(daoFlg, ts2));

        htmlFlg = new JCheckBox("生成页面");
        setComponet(htmlFlg, BASE_WIDTH, BASE_HEIGHT, false);
        pageKbnLbl = new JLabel("页面区分");
        setComponet(pageKbnLbl, 60, BASE_HEIGHT, false);
        ButtonGroup pageGp = new ButtonGroup();
        thymeleafBtn = new JRadioButton("thymeleaf");
        jspBtn = new JRadioButton("JSP");
        pageGp.add(thymeleafBtn);
        pageGp.add(jspBtn);
        setComponet(thymeleafBtn, BASE_WIDTH, BASE_HEIGHT, false);
        setComponet(jspBtn, BASE_WIDTH, BASE_HEIGHT, true);
        JComponent[] ts3 = new JComponent[] { thymeleafBtn, jspBtn };
        htmlFlg.addActionListener(checkBoxCombo(htmlFlg, ts3));

        btn2 = new JButton("Source生成");
        btn2.addActionListener(eventBtn2());
        setComponet(btn2, BASE_WIDTH, BASE_HEIGHT, false);

        btn1 = new JButton("测试方法");
        btn1.addActionListener(eventBtn1());
        setComponet(btn1, BASE_WIDTH, BASE_HEIGHT, true);

        // 带滚动条log
        result = new JTextArea("");
        result.setEditable(false);
        result.setOpaque(false);
        result.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
        JScrollPane scrollP = new JScrollPane();
        scrollP.getViewport().setOpaque(false);
        scrollP.setViewportView(result);
        setComponet(scrollP, 480, 400, true);

        setLocation(projNm);
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
     * 取得画面上输入信息
     *
     * @return 信息键值对
     */
    private Map<String, String> getInputValue() {
        Map<String, String> resultMap = new HashMap<String, String>();
        String projectStr = projectNm.getText();
        resultMap.put(Context.PROJECT, projectStr);
        String locationStr = TextUtil.isNotEmpty(location.getText()) ? location.getText() : PROJ_PATH;
        String packagePath = javaBtn.isSelected() ? "\\src\\" : "\\src\\main\\java\\";
        resultMap.put(Context.LOCATION, locationStr + "\\" + projectStr + packagePath);
        String jspPath = thymeleafBtn.isSelected() ? "" : "\\jsp";
        resultMap.put(Context.PAGEPATH, locationStr + "\\" + projectStr + "\\src\\main\\webapp\\WEB-INF\\views" + jspPath);
        resultMap.put(Context.PACKAGE, TextUtil.isNotEmpty(packageNm.getText()) ? packageNm.getText() : "com.application");
        resultMap.put(Context.TABLE, (String) ddlList.getSelectedItem());

        resultMap.put(Context.SELECTALL, allTblFlg.isSelected() ? "Y" : "N");
        resultMap.put(Context.CONTROLLER, cntrFlg.isSelected() ? "Y" : "N");
        resultMap.put(Context.RESTFUL, restFulFlg.isSelected() ? "Y" : "N");
        resultMap.put(Context.SERVICE, serFlg.isSelected() ? "Y" : "N");
        resultMap.put(Context.DAO, daoFlg.isSelected() ? "Y" : "N");
        resultMap.put(Context.ORACLE, oracleBtn.isSelected() ? "Y" : "N");
        resultMap.put(Context.MYSQL, mysqlBtn.isSelected() ? "Y" : "N");
        resultMap.put(Context.MYBATIS, mybatisFlg.isSelected() ? "Y" : "N");
        resultMap.put(Context.JPA, jpaFlg.isSelected() ? "Y" : "N");
        resultMap.put(Context.HTML, htmlFlg.isSelected() ? "Y" : "N");
        resultMap.put(Context.JSP_FLAG, jspBtn.isSelected() ? "Y" : "N");
        resultMap.put(Context.SPRINGBOOT_FLAG, springbootBtn.isSelected() ? "Y" : "N");
        resultMap.put(Context.JAVA_FLAG, javaBtn.isSelected() ? "Y" : "N");

        return resultMap;
    }

    /**
     * 按钮1事件
     */
    private ActionListener eventBtn1() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                result.setText("");
                StringBuilder sb = new StringBuilder();
                Map<String, String> resultMap = getInputValue();
                Set<String> keys = resultMap.keySet();
                for (String k : keys) {
                    sb.append(k + ":" + resultMap.get(k) + Context.CRLF);
                }
                result.setText(sb.toString());
            }
        };
    }

    /**
     * 按钮2事件
     */
    private ActionListener eventBtn2() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                result.setText("");
                Map<String, String> resultMap = getInputValue();
                String localPath = new File("").getAbsolutePath() + "\\";

                // 全部生成
                String[] outPutList;
                if ("Y".equals(resultMap.get("selectAll"))) {
                    outPutList = getDdlList(false);
                } else {
                    outPutList = new String[] { resultMap.get("table") };
                }

                service.doMainFrameCreateDDL(result, resultMap, outPutList, localPath);

            }
        };
    }

    /**
     * checkbox联动
     *
     * @param  base
     * @param  targets
     * @return
     */
    private ActionListener checkBoxCombo(JCheckBox base, JComponent[] targets) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                for (JComponent target : targets) {
                    target.setEnabled(base.isSelected());
                }
            }
        };
    }

    /**
     * 取得DDL文件一览
     *
     * @param  firstBlankFlag 首位是否有空白
     * @return
     */
    private String[] getDdlList(boolean firstBlankFlag) {
        List<String> ddlList = new ArrayList<String>();
        // 根目录文件夹
        File folder = new File(PROJ_PATH);
        for (File file : folder.listFiles()) {
            String fileName = file.getName();
            if (fileName.length() <= 4) {
                continue;
            }
            String type = fileName.substring(fileName.length() - 4);
            if (".ddl".equals(type)) {
                ddlList.add(fileName);
            }
        }

        String[] result;
        int index = 0;
        // 首位空白
        if (firstBlankFlag) {
            result = new String[ddlList.size() + 1];
            result[index] = "";
            index++;
        } else {
            result = new String[ddlList.size()];
        }

        for (int i = 0; i < ddlList.size(); i++) {
            result[index] = ddlList.get(i);
            index++;
        }
        return result;
    }

    /**
     * 根据项目名设置基本信息
     *
     * @param projNm
     */
    private void setLocation(String projNm) {
        if ("study".equals(projNm)) {
            location.setText("D:\\Project\\IdeaProjects");
            packageNm.setText("com.application");
        } else if ("javaApp".equals(projNm)) {
            location.setText("D:\\project");
            packageNm.setText("com.application");
            javaBtn.setSelected(true);
        } else if ("myapp".equals(projNm)) {
            location.setText("D:\\Project");
            packageNm.setText("com.app");
        } else if ("demo".equals(projNm)) {
            location.setText("D:\\Project\\IdeaProjects\\SpringBootProject");
            packageNm.setText("com.springboot.demo");
        } else if ("demo".equals(projNm)) {
            location.setText("D:\\Project\\IdeaProjects\\SpringBootProject");
            packageNm.setText("com.springboot.demo");
        } else if ("Thymeleaf_Proj".equals(projNm)) {
            location.setText("D:\\Project\\");
            packageNm.setText("com.thymeleaf");
            springbootBtn.setSelected(true);
        }
    }

    private void setInitStatus() {
        oracleBtn.setEnabled(false);
        mysqlBtn.setEnabled(false);
        mybatisFlg.setEnabled(false);
        jpaFlg.setEnabled(false);
        restFulFlg.setEnabled(false);
        thymeleafBtn.setEnabled(false);
        jspBtn.setEnabled(false);
    }

}
