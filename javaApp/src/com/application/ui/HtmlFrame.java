package com.application.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class HtmlFrame extends JFrame {

    private static final long serialVersionUID = 756024747728857762L;
    private static final String LEFT_MENU_KEY = "left-index>";
    private static final String TOP_MENU_KEY = "top-index>";
    private static final String ICON_MENU_KEY = "icon-index>";
    private static final String POPUP_MENU_KEY = "popup-index>";
    /* 开始坐标 */
    private static int startX, defaultX = -10;
    private static int startY = 0;
    private int maxX = 0;

    /* 背景图片 */
    private String netImgUrl = "http://m.qpic.cn/psc?/V51v220v2C0E493HVi1R4bhVpB2uqOS1/bqQfVz5yrrGYSXMvKr.cqYxKVrE3jvWZl8YQ2wR3actp9tPsaTqo5FQiyG.O9pI.ix*aBYwZwqINsyuZMERgc1rzBJ.9fEng*C5yHu.N*IU!/b&bo=WAKQAQAAAAABB.s!&rf=viewer_4";
    private String iconImg = "/icon.png";
    private JPanel jp;
    /* 左侧菜单 */
    private JPanel leftJp;

    /* 表格一览 */
    private JScrollPane srrollTable = null;

    // 菜单项
    JMenuBar menuBar = new JMenuBar();
    // 创建工具栏
    JToolBar toolBar = new JToolBar();
    // 右键弹出菜单
    JPopupMenu popup = new JPopupMenu();

    /* 左侧菜单 */
    Map<String, String[]> leftMenuMap;
    private Map<String, List<JMenuItem>> leftMenuBar = new HashMap<String, List<JMenuItem>>();

    public static void main(String[] args) {
        new HtmlFrame("招投标");
    }

    public HtmlFrame(String closePtn) {
        super("招投标");
        jp = (JPanel) getContentPane();
        SwingComnon.initFrame(this, jp, netImgUrl, iconImg);

        // 菜单栏
        Map<String, String[]> menuBarMap = new HashMap<String, String[]>();
        menuBarMap.put("文件", new String[] { "新建,null", "打开文件,null", "保存,null", "另存为,null" });
        menuBarMap.put("编辑", new String[] { "复制,null", "粘贴,null", "删除,null" });
        menuBarMap.put("帮助", new String[] { "关于,null", "详细信息,null" });
        menuBarMap.put("   ", new String[] {});
        createTopMenuBar(menuBarMap);

        // 添加工具栏
        String[] toolBtnAtt = new String[] { "新建,icon_new.png", "复制,icon_copy.png", "粘贴,icon_paste.png", "导入,icon_import.png", "导出,icon_export.png" };
        createBoxLine(toolBtnAtt);

        // 添加右键菜单
        String[] popupArr = new String[] { "新建文件,icon_new.png", "打开文件,icon_copy.png", "粘贴,icon_paste.png" };
        createPopupMenu(popupArr);

        // 左侧菜单
        leftMenuMap = new HashMap<String, String[]>();
        leftMenuMap.put("数据源", new String[] { "HERO,火纹,null", "SERVANT,FGO,null", "CODE_MASTER,字典,null", "TABLE_INFO,配置信息,null" });
        createLeftMenuBar();

        // 一览表格
        srrollTable = SwingComnon.createTable("HERO", " 1 = 1 order by FAVORITE limit 0 , 30 ", srrollTable);
        startX = 110;
        startY = 70;
        setComponet(srrollTable, 480, 300, true, true);

        SwingComnon.initFrameOption(this, 600, 400, false, closePtn);
    }

    /**
     * 创建菜单栏
     *
     * @param menuBarMap
     */
    private void createTopMenuBar(Map<String, String[]> menuBarMap) {
        Set<String> topMenuName = menuBarMap.keySet();
        int index = 1;
        for (String name : topMenuName) {
            // 子菜单
            List<JMenuItem> menuList = new ArrayList<>();
            for (String subName : menuBarMap.get(name)) {
                // 子菜单信息
                String[] menuInfo = subName.split(",");
                JMenuItem menuItem = SwingComnon.createMenuItem(TOP_MENU_KEY + index, menuInfo[0], menuInfo[1], listener, this);
                menuList.add(menuItem);
            }
            JMenu topMenu = SwingComnon.createMenu(name, menuList);
            menuBar.add(topMenu);
        }
        startX = -1;
        // menuBar.setBackground(new Color(255, 136, 148));
        setComponet(menuBar, 9999, 30, true, true);
        index++;
    }

    /**
     * 添加工具栏
     *
     * @param toolBtnAtt
     * @param root
     */
    private void createBoxLine(String[] toolBtnAtt) {
        int index = 1;
        for (String btnMenu : toolBtnAtt) {
            String[] info = btnMenu.split(",");
            JButton i_new = SwingComnon.toolButton(ICON_MENU_KEY + index, info[0], info[1], listener, this.getClass());
            toolBar.add(i_new);
        }
        startY = 30;
        setComponet(toolBar, 9999, 30, true, true);
        index++;
    }

    /**
     * 右键菜单
     *
     * @param popupArr
     * @param root
     */
    private void createPopupMenu(String[] popupArr) {
        int index = 1;
        for (String btnMenu : popupArr) {
            String[] popItem = btnMenu.split(",");
            JMenuItem newItem = SwingComnon.createMenuItem(POPUP_MENU_KEY + index, popItem[0], popItem[1], listener, this);
            popup.add(newItem);
            index++;
        }
        jp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3)
                    popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }

    private void createLeftMenuBar() {
        leftJp = new JPanel(new GridLayout(20, 1, 0, 0));
        createLeftMenu();
        startY = startY - 15;
        startX = -1;
        setComponet(leftJp, 100, 350, true, true);
    }

    private void createLeftMenu() {
        Set<String> level1Menus = leftMenuMap.keySet();
        int parentIndex = 1;
        for (String level1 : level1Menus) {
            JMenuItem menu = SwingComnon.createMenuItem(LEFT_MENU_KEY + parentIndex, level1, "", listener, this);
            menu.setBackground(new Color(127, 206, 199));
            menu.setFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 15));
            List<JMenuItem> leftSubMenuList = new ArrayList<JMenuItem>();
            leftJp.add(menu);
            for (String subMenus : leftMenuMap.get(level1)) {
                String[] menuInfo = subMenus.split(",");
                JMenuItem level2 = SwingComnon.createMenuItem(LEFT_MENU_KEY + menuInfo[0], menuInfo[1], menuInfo[2], listener, this);
                level2.setBackground(new Color(17, 179, 215));
                level2.setFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 12));
                leftSubMenuList.add(level2);
                leftJp.add(level2);
            }
            leftMenuBar.put(level1, leftSubMenuList);
            parentIndex++;
        }
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

    // 监听事件
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 获取该控件命令码
            String action = e.getActionCommand();
            // 通过对话框打印
            // JOptionPane.showMessageDialog(HtmlFrame.this, action);
            System.out.println(action);
            String[] actionName = action.split(">");
            if (action.indexOf(LEFT_MENU_KEY) >= 0) {
                String tableName = actionName[1];
                SwingComnon.createTable(tableName, " 1 = 1 limit 0 , 30 ", srrollTable);
            }
        }
    };

}
