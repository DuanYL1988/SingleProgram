package com.application.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

@SuppressWarnings("unused")
public class SampleFrame extends JFrame {

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

    /* 结果表示 */
    private JTextArea result;

    private JPanel jp;

    /* 背景图片 */
    private String netImgUrl = "http://m.qpic.cn/psc?/V51v220v2C0E493HVi1R4bhVpB2uqOS1/bqQfVz5yrrGYSXMvKr.cqY6G86vx90y.6DC5tUWkQB3UzwEA0kH4r0jI5FqQZoy*eKBRI.QywhDJkcjJK8cIZ*Xm.CgO4f0uyIj.2zxelVM!/b&bo=*gE.A*4BPgMDByI!&rf=viewer_4";

    /* 图标 */
    private String iconImg = "/icon.png";

    public static void main(String[] args) {
        new SampleFrame("首页");
    }

    SampleFrame(String str) {
        super(TITLE);
        jp = (JPanel) getContentPane();
        SwingComnon.initFrame(this, jp, netImgUrl, iconImg);
        setLayout();
        SwingComnon.initFrameOption(this, maxX + 50, maxY + 20, true, str);
    }

    /**
     * 设置页面布局
     */
    private void setLayout() {
        // 带滚动条log
        result = new JTextArea("");
        result.setEditable(false);
        result.setOpaque(false);
        result.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.black));
        JScrollPane scrollP = new JScrollPane();
        scrollP.getViewport().setOpaque(false);
        scrollP.setViewportView(result);
        setComponet(scrollP, maxX, 200, true);
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

}
