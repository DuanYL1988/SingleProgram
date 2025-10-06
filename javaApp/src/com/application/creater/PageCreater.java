package com.application.creater;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.application.common.Context;
import com.application.dto.Table;
import com.application.util.TextUtil;

public class PageCreater extends BaseCreater {

    /**
     * 创建Controller类
     *
     * @param  tableObj
     * @param  pkgNm
     * @return
     */
    public String createController(Table tableObj, String location, String projectName, String pkgNm, Map<String, String> resultMap, boolean fileFlag) {

        // 实体类
        String modelNm = TextUtil.transNmDbToJava(tableObj.getName(), true);
        // 实体类变量名
        String modelVarNm = modelNm.toLowerCase();
        // RESTFUL类
        boolean restFlag = "Y".equals(resultMap.get(Context.RESTFUL));
        boolean jpaFlag = "Y".equals(resultMap.get(Context.JPA));
        boolean serviceFlag = "Y".equals(resultMap.get(Context.SERVICE));

        StringBuilder ctrlStr = new StringBuilder();
        // Package
        String thisPkg = pkgNm + ".controller";
        String fileNm = modelNm + "Controller";
        ctrlStr.append("package " + thisPkg + ";" + Context.CRLF2);
        // Import
        ctrlStr.append("import java.util.*;" + Context.CRLF);

        // 关联对象import
        if (serviceFlag) {
            ctrlStr.append("import " + pkgNm + ".service." + modelNm + "Service;" + Context.CRLF);
        }
        ctrlStr.append("import " + pkgNm + ".model." + modelNm + ";" + Context.CRLF2);
        ctrlStr.append("import org.springframework.beans.factory.annotation.Autowired;" + Context.CRLF);
        if (restFlag) {
            if (jpaFlag) {
                ctrlStr.append("import " + pkgNm + ".service." + modelNm + "Service;" + Context.CRLF);
            } else {
                ctrlStr.append("import " + pkgNm + ".repository." + modelNm + "Repository;" + Context.CRLF);

            }
            ctrlStr.append("import org.springframework.web.bind.annotation.*;" + Context.CRLF);
        } else {
            ctrlStr.append("import org.springframework.stereotype.Controller;" + Context.CRLF);
            ctrlStr.append("import org.springframework.web.bind.annotation.ResponseBody;" + Context.CRLF2);
        }
        ctrlStr.append("import org.springframework.transaction.annotation.Transactional;" + Context.CRLF);
        ctrlStr.append("import org.springframework.ui.Model;" + Context.CRLF);
        ctrlStr.append("import org.springframework.web.bind.annotation.CrossOrigin;" + Context.CRLF);
        ctrlStr.append("import org.springframework.web.bind.annotation.PathVariable;" + Context.CRLF);
        ctrlStr.append("import org.springframework.web.bind.annotation.RequestBody;" + Context.CRLF);
        ctrlStr.append("import org.springframework.web.bind.annotation.RequestMapping;" + Context.CRLF);
        ctrlStr.append("import org.springframework.web.bind.annotation.RequestMethod;" + Context.CRLF2);

        // Class-Start
        if ("Y".equals(resultMap.get(Context.RESTFUL))) {
            ctrlStr.append("@RestController" + Context.CRLF);
            ctrlStr.append("@CrossOrigin" + Context.CRLF);
        } else {
            ctrlStr.append("@Controller" + Context.CRLF);
        }
        ctrlStr.append(editReqMp(modelVarNm, true, false));
        ctrlStr.append("public class " + fileNm + " {" + Context.CRLF2);

        // spring注入对象-service
        if (serviceFlag) {
            ctrlStr.append("    @Autowired" + Context.CRLF);
            ctrlStr.append("    " + modelNm + "Service " + modelVarNm + "Service;" + Context.CRLF2);
        }
        // spring注入对象-Dao
        if (restFlag) {
            ctrlStr.append("    @Autowired" + Context.CRLF);
            String type = "Y".equals(resultMap.get(Context.JPA)) ? "Dao " : "Repository ";
            ctrlStr.append("    " + modelNm + type + modelVarNm + "Dao;" + Context.CRLF2);
        }

        // 方法
        ctrlStr.append(createMethodSearch(modelNm, modelVarNm, restFlag));
        ctrlStr.append(createMethodDetail(modelNm, modelVarNm, tableObj, restFlag));
        if (!restFlag) {
            ctrlStr.append(createMethodAjaxSearch(modelNm, modelVarNm));
        }
        ctrlStr.append(createMethodUpdate(modelNm, modelVarNm, restFlag));

        // Class-end
        ctrlStr.append("}" + Context.CRLF);

        if (fileFlag) {
            return super.writeFile(location, thisPkg, fileNm + ".java", ctrlStr.toString());
        }

        return ctrlStr.toString();
    }

    /**
     * 创建HTML
     *
     * @return
     * @throws IOException
     */
    public String createHtml(Table tableObj, String projectName, String pkgNm, String type, boolean jspFlag, boolean fileFlag) throws IOException {
        // 取得模板
        List<String> template = super.getTemplete(type + ".html");
        // 生成文件名
        String fileName = type;

        StringBuilder htmlStr = new StringBuilder();
        Map<String, String> listHtml = thForeach(tableObj);
        for (String line : template) {
            // BASE_URL
            if (line.indexOf(Context.COLUMN_TITLE) >= 0) {
                htmlStr.append(listHtml.get(Context.COLUMN_TITLE) + Context.CRLF);
            } else if (line.indexOf(Context.BASE_URL) >= 0) {
                htmlStr.append(line.replace(Context.BASE_URL, tableObj.getName().toLowerCase()) + Context.CRLF);
            } else if (line.indexOf(Context.SEARCH_TD) >= 0) {
                htmlStr.append(listHtml.get(Context.SEARCH_TD) + Context.CRLF);
            } else if (line.indexOf(Context.COLUMN_INFO) >= 0) {
                htmlStr.append(listHtml.get(Context.COLUMN_INFO) + Context.CRLF);
            } else if (line.indexOf(Context.TITLE) > 0) {
                htmlStr.append(line.replace(Context.TITLE, tableObj.getName()) + Context.CRLF);
            } else {
                htmlStr.append(line + Context.CRLF);
            }
        }

        if (fileFlag) {
            String pageType = jspFlag ? ".jsp" : ".html";
            return super.writeFile(projectName, pkgNm + tableObj.getName().toLowerCase(), fileName + pageType, htmlStr.toString());
        }
        return htmlStr.toString();
    }

    /**
     * 详细页面
     *
     * @param  tableObj
     * @return
     */
    private Map<String, String> thForeach(Table tableObj) {
        Map<String, String> result = new HashMap<String, String>();
        // 标题列
        StringBuilder columnTitle = new StringBuilder();
        // 数据列集合
        StringBuilder columnInfo = new StringBuilder();
        // 检索列
        StringBuilder serachTr = new StringBuilder();

        for (Table.Column column : tableObj.getColumnList()) {
            String propNm = column.getSourceName();
            if (propNm.indexOf("Datetime") > 0) {
                continue;
            }
            columnTitle.append(Context.SPACE12 + "<td>" + column.getLogicName() + "</td>" + Context.CRLF);
            String tdStr = "<td name=\"" + propNm + "\" th:text=\"${obj." + propNm + "}\"";
            // 列表内通过ID打开详细页面
            if ("id".equals(column.getSourceName())) {
                tdStr += " th:onclick=\"openDetail('" + tableObj.getName().toLowerCase() + "',this)\"";
            }
            tdStr += "></td>";
            columnInfo.append(Context.SPACE12 + tdStr + Context.CRLF);
            // 检索入力项目(第一列)
            serachTr.append(Context.SPACE12 + "<td><input type='text' th:field=\"*{inDto." + propNm + "}\" /></td>" + Context.CRLF);
        }
        result.put(Context.COLUMN_TITLE, columnTitle.toString());
        result.put(Context.COLUMN_INFO, columnInfo.toString());
        result.put(Context.SEARCH_TD, serachTr.toString());
        return result;
    }

    /**
     * 检索
     *
     * @param  objNm
     * @param  paramNm
     * @return
     */
    private String createMethodSearch(String objNm, String paramNm, boolean restFlag) {
        StringBuilder ctrlStr = new StringBuilder();
        String returnType = restFlag ? "Object" : "String";

        ctrlStr.append("    /**" + Context.CRLF);
        ctrlStr.append("     * 一览表示" + Context.CRLF);
        ctrlStr.append("     */" + Context.CRLF);
        // requestMapping的value
        ctrlStr.append(editReqMp("search", false, false));
        ctrlStr.append("    public " + returnType + " doSearch(Model model, " + objNm + " condition) {" + Context.CRLF);
        if (!restFlag) {
            ctrlStr.append("        List<" + objNm + "> resultList = " + paramNm + "Service.getSearchList(condition);" + Context.CRLF);
            ctrlStr.append("        model.addAttribute(\"resultList\", resultList);" + Context.CRLF);
            ctrlStr.append("        model.addAttribute(\"inDto\", inDto);" + Context.CRLF);
            ctrlStr.append("        return \"" + paramNm + "/list\";" + Context.CRLF);
        } else {
            ctrlStr.append("        return " + paramNm + "Dao.selectByDto(condition);" + Context.CRLF);
        }
        ctrlStr.append("    }" + Context.CRLF2);

        return ctrlStr.toString();
    }

    /**
     * AJAX检索
     *
     * @param  objNm
     * @param  paramNm
     * @return
     */
    private String createMethodAjaxSearch(String objNm, String paramNm) {
        StringBuilder ctrlStr = new StringBuilder();

        ctrlStr.append("    /**" + Context.CRLF);
        ctrlStr.append("     * AJAX检索" + Context.CRLF);
        ctrlStr.append("     */" + Context.CRLF);
        ctrlStr.append(editReqMp("ajaxSearch", false, false));
        ctrlStr.append("    @ResponseBody" + Context.CRLF);
        ctrlStr.append("    public Object doAjaxSearch(@RequestBody " + objNm + " " + paramNm + ") {" + Context.CRLF);
        ctrlStr.append("        List<" + objNm + "> resultList = " + paramNm + "Service.getSearchList(" + paramNm + ");" + Context.CRLF);
        ctrlStr.append("        return resultList;" + Context.CRLF);
        ctrlStr.append("    }" + Context.CRLF2);

        return ctrlStr.toString();
    }

    /**
     * 取得单条数据
     *
     * @param  objNm
     * @param  paramNm
     * @return
     */
    private String createMethodDetail(String objNm, String paramNm, Table tableObj, boolean restFlag) {
        StringBuilder ctrlStr = new StringBuilder();

        ctrlStr.append("    /**" + Context.CRLF);
        ctrlStr.append("     * 取得单条数据" + Context.CRLF);
        ctrlStr.append("     */" + Context.CRLF);
        ctrlStr.append(editReqMp("detail/{id}", false, false));
        String pkType = tableObj.getPkey().getJavaType();
        String returnType = restFlag ? "Object" : "String";
        ctrlStr.append("    public " + returnType + " getDetail(@PathVariable(\"id\") " + pkType + " id, Model model) {" + Context.CRLF);
        if (!restFlag) {
            ctrlStr.append("        " + objNm + " " + paramNm + " = " + paramNm + "Service.selectOneById(id);" + Context.CRLF);
            ctrlStr.append("        model.addAttribute(\"" + paramNm + "\"," + paramNm + ");" + Context.CRLF);
            ctrlStr.append("        return \"" + paramNm + "/detail\";" + Context.CRLF);
        } else {
            ctrlStr.append("        return " + paramNm + "Dao.selectOneById(id);" + Context.CRLF);
        }
        ctrlStr.append("    }" + Context.CRLF2);

        return ctrlStr.toString();
    }

    private String createMethodUpdate(String objNm, String paramNm, boolean restFlag) {
        StringBuilder ctrlStr = new StringBuilder();

        ctrlStr.append("    /**" + Context.CRLF);
        ctrlStr.append("     * Ajax更新" + Context.CRLF);
        ctrlStr.append("     */" + Context.CRLF);
        ctrlStr.append(editReqMp("ajaxRegist", false, true));
        if (!restFlag) {
            ctrlStr.append("    @ResponseBody" + Context.CRLF);
            ctrlStr.append("    @Transactional" + Context.CRLF);
            ctrlStr.append("    public Object doAjaxRegist(@RequestBody " + objNm + " obj) {" + Context.CRLF);
            ctrlStr.append("        " + paramNm + "Service.doInsertOrUpdate(obj);" + Context.CRLF);
            ctrlStr.append("        return \"update success!\";" + Context.CRLF);
        } else {
            ctrlStr.append("    public Object doAjaxRegist(@RequestBody " + objNm + " obj) {" + Context.CRLF);
            ctrlStr.append("        int updCnt = 0;" + Context.CRLF);
            ctrlStr.append("        if (null == obj.getId() || \"\".equals(obj.getId())) {" + Context.CRLF);
            ctrlStr.append("          updCnt = " + paramNm + "Dao.insert(obj);" + Context.CRLF);
            ctrlStr.append("        } else {" + Context.CRLF);
            ctrlStr.append("          updCnt = " + paramNm + "Dao.update(obj);" + Context.CRLF);
            ctrlStr.append("        }" + Context.CRLF);
            ctrlStr.append("        return updCnt;" + Context.CRLF);
        }
        ctrlStr.append("    }" + Context.CRLF2);

        return ctrlStr.toString();
    }

    /**
     * RequestMapping
     *
     * @param  value
     * @return       "@RequestMapping(value = "/{value}")"
     */
    private String editReqMp(String value, boolean withSlash, boolean postFlag) {
        String str = "@RequestMapping(value = \"";
        str = withSlash ? str + "/" : "    " + str;
        str += value + "\"";
        str += postFlag ? ", method = RequestMethod.POST" : "";
        str += ")" + Context.CRLF;
        return str;
    }

}
