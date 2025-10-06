package com.application.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.ibatis.session.SqlSession;

import com.application.jdbc.MybatisBase;
import com.application.model.Hero;
import com.application.repository.HeroRepository;

public class FeHeroFrame extends JFrame {

    private static final long serialVersionUID = 4783178871399676541L;

    private static final String TITLE = "火纹-英雄";

    private FeHeroFrame thisFrame;

    /* 标准宽度 */
    private static final int BASE_WIDTH = 100;

    /* 标准高度 */
    private static final int BASE_HEIGHT = 30;

    /* 开始坐标 */
    private int startX, startY, defaultX = 10;

    /* 窗体的宽高 */
    private int maxX, maxY = 100;

    private JPanel jp;

    private JLabel faceImage;

    /* 图标 */
    private String iconImg = "/icon.png";

    private JLabel titleLbl, nameLbl, levelLbl, limitLbl, hpLbl, attactLbl;
    private JTextField title, name, level, limit, hp, attact;

    private int index = 4;

    private SqlSession session;

    private HeroRepository dao;

    private Hero hero;

    public static void main(String[] args) {
        new FeHeroFrame(5, TITLE);
    }

    FeHeroFrame(int id, String closePtn) {
        super(TITLE);
        thisFrame = this;
        jp = (JPanel) getContentPane();

        MybatisBase mybatisUtil = new MybatisBase();
        session = mybatisUtil.getSession();
        dao = session.getMapper(HeroRepository.class);
        hero = dao.selectOneById(id);

        SwingComnon.initFrame(this, jp, hero.getExtraImgUrl(), iconImg);
        jp.setBackground(new Color(0, 0, 0, 50));

        setLayout();
        SwingComnon.initFrameOption(this, 1080, 1960, true, closePtn);
        // SwingComnon.initFrameOption(this, maxX + 50, maxY + 20, true, closePtn);
    }

    /**
     * 设置页面布局
     */
    private void setLayout() {
        startY = 10;
        faceImage = new JLabel();
        int faceWidth = 100;
        SwingComnon.setImageComp(hero.getFaceImgUrl(), faceImage, faceWidth, faceWidth);
        setComponet(faceImage, faceWidth, faceWidth, false);
        defaultX += faceWidth + 20;

        titleLbl = new JLabel("称谓 ");
        setComponet(titleLbl, BASE_WIDTH / 2, BASE_HEIGHT, false);
        title = new JTextField(hero.getTitleName());
        setComponet(title, BASE_WIDTH, BASE_HEIGHT, false);

        nameLbl = new JLabel("名称 ");
        setComponet(nameLbl, BASE_WIDTH / 2, BASE_HEIGHT, false);
        name = new JTextField(hero.getName());
        setComponet(name, BASE_WIDTH, BASE_HEIGHT, false);

        levelLbl = new JLabel("Level ");
        setComponet(levelLbl, BASE_WIDTH / 2, BASE_HEIGHT, false);
        level = new JTextField("40");
        setComponet(level, BASE_WIDTH, BASE_HEIGHT, false);

        limitLbl = new JLabel(" + ");
        setComponet(limitLbl, BASE_WIDTH / 2, BASE_HEIGHT, false);
        limit = new JTextField(hero.getLimitPlus());
        setComponet(limit, BASE_WIDTH, BASE_HEIGHT, true);

        hpLbl = new JLabel("HP");
        setComponet(hpLbl, BASE_WIDTH / 2, BASE_HEIGHT, false);
        hp = new JTextField(hero.getHp());
        setComponet(hp, BASE_WIDTH, BASE_HEIGHT, false);

        attactLbl = new JLabel("ATTACT");
        setComponet(attactLbl, BASE_WIDTH / 2, BASE_HEIGHT, false);
        attact = new JTextField(hero.getAttact());
        setComponet(attact, BASE_WIDTH, BASE_HEIGHT, false);
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
        // compant.setOpaque(false);
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

    @SuppressWarnings("unused")
    private ActionListener changePic() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                index++;
                int i = index % 4;
                switch (i) {
                case 1:
                    SwingComnon.setBackgroundImage(hero.getAttactImgUrl(), thisFrame);
                case 2:
                    SwingComnon.setBackgroundImage(hero.getExtraImgUrl(), thisFrame);
                case 3:
                    SwingComnon.setBackgroundImage(hero.getBreakImgUrl(), thisFrame);
                case 0:
                    SwingComnon.setBackgroundImage(hero.getNormalImgUrl(), thisFrame);
                }
            }
        };
    }
}
