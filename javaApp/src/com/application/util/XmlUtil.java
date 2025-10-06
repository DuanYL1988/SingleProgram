package com.application.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultText;

public class XmlUtil {
    
    private static boolean READ_FLAG = false;

    public static void main(String[] args) {
        String basePath = new File("").getAbsolutePath() + "\\pom.xml";
        if (READ_FLAG) {
            Element rootEle = getRootElement(basePath);
            Element nodeEle = getElementByname(rootEle, "dependencies");
            List<List<CustAttributer>> attrs = getNodeInfo(nodeEle, "dependency");
            printAttrs(attrs);
        } else {
            // 创建根节点
            Element root = createRootEelment("root");
            // 编辑node属性
            List<List<CustAttributer>> dataInfo = createTest();
            addElementAttrs(root, "student", dataInfo);
            // 将根节点加入文档
            Document doc = createOutputDoc(root);
            outPutXml("output.xml", doc);
        }
    }

    // ========== 解析xml相关  ==========//
    /**
     * 取得根元素
     * 
     * @param xmlPath 文件路径名称
     */
    public static Element getRootElement(String xmlPath) {
        SAXReader reader = new SAXReader();
        Element rootEle = null;
        try {
            // 取得dom文件
            Document doc = reader.read(new File(xmlPath));
            // 取得根元素
            rootEle = doc.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return rootEle;
    }

    /**
     * 通过名字取得节点
     * 
     * @param root
     * @param name
     * @return
     */
    public static Element getElementByname(Element root, String name) {
        return root.element("dependencies");
    }

    /**
     * 取得节点属性信息集合
     * 
     * @param rootEle  根节点
     * @param nodeName 节点名
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<List<CustAttributer>> getNodeInfo(Element rootEle, String nodeName) {
        List<List<CustAttributer>> nodeList = new ArrayList<List<CustAttributer>>();

        // 取得特定节点
        List<Element> elements = rootEle.elements(nodeName);
        for (Element ele : elements) {
            List<CustAttributer> attrList = new ArrayList<CustAttributer>();
            // 取得属性
            List<Attribute> attrs = (List<Attribute>) ele.attributes();
            for (Attribute attr : attrs) {
                CustAttributer attribute = createAttr(attr.getName(), attr.getText(), false);
                attrList.add(attribute);
            }
            // 子element
            List<Element> childElements = ele.elements();
            for (Element childE : childElements) {
                // 单纯的文字节点,没有子element,也没有定义attr
                if (childE.elements().size() == 0 && childE.attributes().size() == 0) {
                    if (childE.content().size() > 0
                            && "org.dom4j.tree.DefaultText".equals(childE.content().get(0).getClass().getName())) {
                        DefaultText textN = (DefaultText) childE.content().get(0);
                        CustAttributer attribute = createAttr(childE.getName(), textN.getText(), true);
                        attrList.add(attribute);
                    }
                }
            }
            //
            nodeList.add(attrList);
        }
        return nodeList;
    }

    public static void printAttrs(List<List<CustAttributer>> attrsList) {
        for (List<CustAttributer> attrs : attrsList) {
            for (CustAttributer attr : attrs) {
                System.out.println(attr.getName() + ":" + attr.getValue());
            }
        }
    }

    
    // ========== 输出xml相关  ==========//
    public static Document createOutputDoc(Element rootElement) {
        Document doc = DocumentHelper.createDocument();
        // 将根节点加入文档
        doc.setRootElement(rootElement);
        return doc;
    }
    
    public static Element createRootEelment(String rootEleName) {
        return DocumentHelper.createElement(rootEleName);
    }
    
    /**
     * 添加ELEMENT
     * 
     * @param root     根节点
     * @param nodeName 节点名
     * @param dataInfo 属性集合
     */
    public static void addElementAttrs(Element root, String nodeName, List<List<CustAttributer>> dataInfo) {
        for (List<CustAttributer> data : dataInfo) {
            Element childEle = root.addElement(nodeName);
            for (CustAttributer attr : data) {
                if (attr.isNodeEleFlag()) {
                    Element textNode = childEle.addElement(attr.getName());
                    textNode.setText(attr.getValue());
                } else {
                    childEle.addAttribute(attr.getName(), attr.getValue());
                }
            }

        }
    }

    /**
     * 输出xml
     * 
     * @param path 文件路径
     * @param doc  文档对象
     */
    public static void outPutXml(String path, Document doc) {
        try {
            // 输出输出到控制台
            XMLWriter xmlWriter = new XMLWriter();
            xmlWriter.write(doc);

            // 输出到文件
            // 格式
            OutputFormat format = new OutputFormat("    ", true);// 设置缩进为4个空格，并且另起一行为true
            XMLWriter xmlWriter2;
            xmlWriter2 = new XMLWriter(new FileOutputStream(path), format);
            xmlWriter2.write(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CustAttributer createAttr(String name, String value, boolean textNode) {
        CustAttributer attr = new CustAttributer();
        attr.setName(name);
        attr.setValue(value);
        attr.setNodeEleFlag(textNode);
        return attr;
    }
    
    private static List<List<CustAttributer>> createTest() {
        List<List<CustAttributer>> result = new ArrayList<>();
        for(int i=1 ; i < 4; i++) {
            List<CustAttributer> node = new ArrayList<>();
            node.add(createAttr("ID", i+"", false));
            node.add(createAttr("NAME", "zhang san" + i, true));
            node.add(createAttr("ADDRESS", "24200" + i , true));
            result.add(node);
        }
        return result;
    }

    static class CustAttributer {
        /* 属性名 */
        private String name;

        /* 属性值 */
        private String value;

        /* 文本节点flag */
        private boolean nodeEleFlag = false;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isNodeEleFlag() {
            return nodeEleFlag;
        }

        public void setNodeEleFlag(boolean nodeEleFlag) {
            this.nodeEleFlag = nodeEleFlag;
        }
    }
}
