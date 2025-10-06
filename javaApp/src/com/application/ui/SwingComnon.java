package com.application.ui;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.Connection;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.application.common.Context;
import com.application.jdbc.MybatisBase;
import com.application.jdbc.MysqlUtil;
import com.application.jdbc.OracleUtil;
import com.application.jdbc.SqliteUtil;
import com.application.model.Menu;
import com.application.model.TableInfo;
import com.application.repository.MenuRepository;
import com.application.repository.TableInfoRepository;

public class SwingComnon {

    /**
     * @param frame
     * @param jp
     * @param bkImg
     * @param iconImg
     */
    public static void initFrame(JFrame frame, JPanel jp, String bkImg, String iconImg) {
        // 设置背景图片
        setBackgroundImage(bkImg, frame);
        // 设置图标
        ImageIcon icon = new ImageIcon(frame.getClass().getResource(iconImg));
        frame.setIconImage(icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        if (null != jp) {
            jp.setOpaque(false);
            jp.setLayout(null);
        }
    }

    /**
     * @param frame      窗体frame对象
     * @param fWidht     宽度
     * @param fHeight    高度
     * @param reseizAble 宽高可变flag
     * @param closePtn   关闭方式[1:关闭当前对话框,2:结束引用]
     */
    public static void initFrameOption(JFrame frame, Integer fWidht, Integer fHeight, boolean reseizAble, String closePtn) {
        // 设置关闭方式
        if ("2".equals(closePtn)) {
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        frame.setSize(fWidht + 16, fHeight + 39);
        // 窗体居中显示
        frame.setLocationRelativeTo(null);
        frame.setResizable(reseizAble);
        frame.setVisible(true);
    }

    /**
     * @param imgUrl
     * @param frame
     */
    public static void setBackgroundImage(String imgUrl, JFrame frame) {
        try {
            URL url = new URL(imgUrl);
            BufferedImage bufferedImage = ImageIO.read(url.openStream());
            Image itemImage = bufferedImage.getScaledInstance(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.SCALE_SMOOTH);
            // 取得工程resources下图片
            // ImageIcon bkg = new ImageIcon(getClass().getResource("/background.png"));
            ImageIcon bkg = new ImageIcon(itemImage);
            JLabel backgound = new JLabel(bkg);
            frame.getLayeredPane().add(backgound, Integer.valueOf(Integer.MIN_VALUE));
            backgound.setBounds(0, 0, bkg.getIconWidth(), bkg.getIconHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param imgUrl
     * @param label
     * @param width
     * @param height
     */
    public static void setImageComp(String imgUrl, JLabel label, int width, int height) {
        try {
            URL url = new URL(imgUrl);
            BufferedImage bufferedImage = ImageIO.read(url.openStream());
            Image itemImage = bufferedImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);

            ImageIcon icon = new ImageIcon(itemImage);
            label.setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param frame
     */
    public static void showFrameInfo(JPanel frame) {
        System.out.println("窗体宽度:" + frame.getWidth());
        System.out.println("窗体高度:" + frame.getHeight());
    }

    public static void main(String[] args) {
    }

    public static JMenuBar createTopMenuBar(String app, String page, String location, ActionListener listener, JFrame frame) {
        // 取得menu一览
        Menu cond = new Menu();
        cond.setApplication(app);
        cond.setCompGroup(page);
        cond.setLocation(location);
        cond.setOrderBy("ITEM_SORT");
        MybatisBase conn = new MybatisBase();
        MenuRepository dao = conn.session.getMapper(MenuRepository.class);
        List<Menu> level1Menu = dao.selectByDto(cond);

        // 取得二级菜单
        JMenuBar topMenuBar = new JMenuBar();
        getJmenuList(1, null, topMenuBar, level1Menu, dao, listener, frame);
        return topMenuBar;
    }

    private static void getJmenuList(int level, JMenu parentJMenu, JMenuBar topMenuBar, List<Menu> dbList, MenuRepository dao, ActionListener listener, JFrame frame) {
        // 循环DB菜单数据
        for (Menu menu : dbList) {
            // 判断是否有子菜单
            Menu cond = new Menu();
            cond.setOrderBy("ITEM_SORT");
            cond.setParentId(menu.getId() + "");
            List<Menu> childMenuList = dao.selectByDto(cond);
            // 有子菜单,则本菜单为父菜单
            if (childMenuList.size() > 0) {
                JMenu jPmenu = new JMenu(menu.getName());
                getJmenuList(2, jPmenu, topMenuBar, childMenuList, dao, listener, frame);
                if (1 == level) {
                    topMenuBar.add(jPmenu);
                }
            } else {
                JMenuItem item = createMenuItem(menu.getAppParam(), menu.getName(), menu.getIconUrl(), listener, frame);
                parentJMenu.add(item);
            }
        }
    }

    /**
     * 创建菜单
     *
     * @param  text 主菜单名
     * @param  item 子菜单集合
     * @return
     */
    public static JMenu createMenu(String text, List<JMenuItem> item) // 一次性添加多个Item，所以用List集合
    {
        JMenu menu = new JMenu(text);
        if (item != null) {
            for (JMenuItem i : item) {
                menu.add(i);
            }
        }
        return menu;
    }

    /**
     * 创建菜单选项
     *
     * @param  action
     * @param  text
     * @param  filePath
     * @param  listener
     * @param  frameClass
     * @return
     */
    public static JMenuItem createMenuItem(String action, String text, String filePath, ActionListener listener, JFrame frame) {
        JMenuItem item = new JMenuItem(text);
        // ActionCommand为命令码，用于区别各个选项，后用于监听事件
        item.setActionCommand(action);
        // 设置监听事件
        item.addActionListener(listener);
        if (null != filePath && !"".equals(filePath) && !"null".equals(filePath.toLowerCase())) {
            String fileName = "/images/" + filePath;
            // 获取资源文件
            URL url = frame.getClass().getResource(fileName);
            // 设置图标
            item.setIcon(new ImageIcon(url));
        }
        return item;
    }

    // 创建工具栏
    @SuppressWarnings("rawtypes")
    public static JButton toolButton(String action, String toolText, String filePath, ActionListener listener, Class frameClass) {
        JButton button = new JButton();
        // 获取资源文件
        if (null != filePath && !"".equals(filePath) && !"null".equals(filePath.toLowerCase())) {
            String fileName = "/images/" + filePath;
            // 获取资源文件
            URL url = frameClass.getResource(fileName);
            // 设置图标
            button.setIcon(new ImageIcon(url));
        }
        button.setActionCommand(action);
        button.setToolTipText(toolText);
        button.setFocusable(false);
        // 设置监听事件
        button.addActionListener(listener);
        return button;
    }

    public static JScrollPane createTable(String dbTableName, String condition, JScrollPane tblPane) {
        TableInfo cond = new TableInfo();
        cond.setPageSize(100);
        cond.setTableName(dbTableName);
        cond.setColListDisableFlag(1);
        MybatisBase conn = new MybatisBase();
        TableInfoRepository dao = conn.session.getMapper(TableInfoRepository.class);
        List<TableInfo> selectColumns = dao.selectByDto(cond);
        String[] columnNames = new String[selectColumns.size()];
        String quary = "";

        for (int i = 0; i < selectColumns.size(); i++) {
            TableInfo column = selectColumns.get(i);
            quary += i == 0 ? "SELECT " + column.getColName() : " , " + column.getColName();
            columnNames[i] = column.getColNameCh();
        }
        quary += " FROM " + dbTableName + " WHERE " + condition;
        // 连接Sqlite
        Connection dbConn = null;
        Object[] jtableInfo = null;
        String dbType = Context.getProp(Context.DB_TYPE);
        if ("sqlite".equals(dbType)) {
            SqliteUtil dbObj = new SqliteUtil();
            dbConn = dbObj.getConnection();
            jtableInfo = dbObj.getJTableInfo(quary, dbConn);
        } else if ("mysql".equals(dbType)) {
            MysqlUtil dbObj = new MysqlUtil();
            dbConn = dbObj.getConnection();
            jtableInfo = dbObj.getJTableInfo(quary, dbConn);
        } else if ("oracle".equals(dbType)) {
            OracleUtil dbObj = new OracleUtil();
            dbConn = dbObj.getConnection();
            jtableInfo = dbObj.getJTableInfo(quary, dbConn);
        }

        String[][] rowData = (String[][]) jtableInfo[1];

        JTable result = new JTable(rowData, columnNames);
        result.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // 可滚动面板
        tblPane = null == tblPane ? new JScrollPane(result, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS) : tblPane;
        tblPane.setViewportView(result);
        return tblPane;
    }

    /**
     * 下拉框设置透明
     *
     * @param comboBox
     */
    @SuppressWarnings({ "unchecked", "serial", "rawtypes" })
    public static void setJcompoOpaque(JComboBox comboBox) {
        ((JTextField) comboBox.getEditor().getEditorComponent()).setOpaque(false);
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JComponent result = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                result.setOpaque(false);
                return result;
            }
        });
    }

}