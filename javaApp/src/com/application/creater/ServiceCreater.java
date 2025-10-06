package com.application.creater;

import com.application.common.Context;
import com.application.dto.Table;
import com.application.dto.Table.Column;
import com.application.util.TextUtil;

public class ServiceCreater extends BaseCreater {

    /**
     * 创建Service类
     *
     * @param  tableObj
     * @param  pkgNm
     * @return
     */
    public String createService(Table tableObj, String projectName, String pkgNm, boolean fileFlag) {

        // 实体类
        String modelNm = TextUtil.transNmDbToJava(tableObj.getName(), true);
        // 实体类变量名
        String varNm = modelNm.toLowerCase();
        String fileNm = modelNm + "Service";
        // 主键ID类型
        Column pkCol = tableObj.getPkey();

        StringBuilder serviceStr = new StringBuilder();
        // Package
        String thisPkg = pkgNm + ".service";
        serviceStr.append("package " + thisPkg + ";" + Context.CRLF2);

        // 关联对象import
        serviceStr.append("import java.util.*;" + Context.CRLF);
        // serviceStr.append("import com.springboot.demo.dto.Layout;" + Context.CRLF);
        serviceStr.append("import " + pkgNm + ".model." + modelNm + ";" + Context.CRLF2);

        // Class-Start
        serviceStr.append("public interface " + fileNm + " {" + Context.CRLF2);

        // 方法
        serviceStr.append("    List<" + modelNm + "> getSearchList(" + modelNm + " " + varNm + ");" + Context.CRLF2);
        serviceStr.append("    " + modelNm + " selectOneById("+ pkCol.getJavaType() + " id);" + Context.CRLF2);
        serviceStr.append("    int doInsertOrUpdate(" + modelNm + " " + varNm + ");" + Context.CRLF2);
        serviceStr.append("    void delete("+ pkCol.getJavaType() +" id);" + Context.CRLF2);
        // serviceStr.append(" Map<String,List<Layout>> detailLayout(String id);" + Context.CRLF2);

        // Class-end
        serviceStr.append("}" + Context.CRLF);

        if (fileFlag) {
            return super.writeFile(projectName, thisPkg, fileNm + ".java", serviceStr.toString());
        }

        return serviceStr.toString();
    }

    /**
     * 创建Service类
     *
     * @param  tableObj
     * @param  pkgNm
     * @return
     */
    public String createServiceImpl(Table tableObj, String projectName, String pkgNm, boolean fileFlag, boolean hibernateFlag) {

        // 实体类
        String modelNm = TextUtil.transNmDbToJava(tableObj.getName(), true);
        // 实体类变量名
        String varNm = modelNm.toLowerCase();
        String fileNm = modelNm + "ServiceImpl";

        StringBuilder serviceStr = new StringBuilder();
        String thisPkg = pkgNm + ".service.impl";

        // import部分
        serviceStr.append(pkgAndImportPart(pkgNm, modelNm, hibernateFlag));
        // 定义组件部分
        serviceStr.append(componentPart(fileNm, modelNm, varNm, hibernateFlag));
        // 检索方法
        serviceStr.append(searchMethodPart(modelNm, varNm, hibernateFlag));
        // 检索一件
        serviceStr.append(findOnePart(modelNm, varNm, hibernateFlag, tableObj));
        // 更新
        serviceStr.append(updatePart(modelNm, varNm, hibernateFlag));
        // 删除
        serviceStr.append(deletePart(modelNm, varNm, hibernateFlag,tableObj));

        // 详细页面布局
        // serviceStr.append(" public Map<String,List<Layout>> detailLayout(String id) {" + Context.CRLF);
        // serviceStr.append(Context.SPACE8 + modelNm + " " + varNm + " = " + varNm + "Dao.findById(id).get();" + Context.CRLF);
        // serviceStr.append(" Map<String,List<Layout>> layout = new HashMap<>();" + Context.CRLF);
        // serviceStr.append(" List<Layout> baseInfo = new ArrayList<>();" + Context.CRLF);
        // // serviceStr.append(" baseInfo.add(createCell("","",servant.getName(),""));"+ Context.CRLF2);
        // String obj = TextUtil.transNmDbToJava(tableObj.getName(), false);
        // for (Table.Column column : tableObj.getColumnList()) {
        // if ("id".equals(column.getColumnName().toLowerCase())) {
        // continue;
        // }
        // String getMet = TextUtil.transNmDbToJava(column.getColumnName(), true);
        // String cell = " baseInfo.add(createCell(\"";
        // cell += column.getLogicName() + "\",\"";
        // cell += column.getSourceName() + "\",";
        // cell += obj + ".get" + getMet + "()+\"\",\"\"));";
        // serviceStr.append(cell + Context.CRLF);
        // }
        //
        // serviceStr.append(" layout.put(\"baseInfo\",baseInfo);" + Context.CRLF);
        // serviceStr.append(" return layout;" + Context.CRLF);
        // serviceStr.append(" }" + Context.CRLF2);
        //
        // serviceStr.append(" private Layout createCell(String label,String name,String value,String type){" + Context.CRLF);
        // serviceStr.append(" Layout layout = new Layout();" + Context.CRLF);
        // serviceStr.append(" layout.setLabel(label);" + Context.CRLF);
        // serviceStr.append(" layout.setName(name);" + Context.CRLF);
        // serviceStr.append(" layout.setValue(value);" + Context.CRLF);
        // serviceStr.append(" layout.setType(type);" + Context.CRLF);
        // serviceStr.append(" return layout;" + Context.CRLF);
        // serviceStr.append(" }" + Context.CRLF2);
        // // Class-end
        serviceStr.append("}" + Context.CRLF);
        if (fileFlag) {
            return super.writeFile(projectName, thisPkg, fileNm + ".java", serviceStr.toString());
        }
        return serviceStr.toString();
    }

    /**
     * @param  pkgNm
     * @param  modelNm
     * @param  hibernateFlag
     * @return
     */
    private String pkgAndImportPart(String pkgNm, String modelNm, boolean hibernateFlag) {
        String thisPkg = pkgNm + ".service.impl";
        StringBuilder serviceStr = new StringBuilder();
        // Package
        serviceStr.append("package " + thisPkg + ";" + Context.CRLF2);

        // 关联对象import
        serviceStr.append("import java.util.*;" + Context.CRLF);

        // spring注入类
        serviceStr.append("import org.springframework.beans.factory.annotation.Autowired;" + Context.CRLF);
        serviceStr.append("import org.springframework.stereotype.Service;" + Context.CRLF2);

        // Jpa相关类
        if (hibernateFlag) {
            serviceStr.append("import org.springframework.data.domain.Example;" + Context.CRLF);
            serviceStr.append("import org.springframework.data.domain.Page;" + Context.CRLF);
            serviceStr.append("import org.springframework.data.domain.PageRequest;" + Context.CRLF);
            serviceStr.append("import org.springframework.data.domain.Pageable;" + Context.CRLF);
            serviceStr.append("import org.springframework.data.domain.Sort;" + Context.CRLF2);
            serviceStr.append("import " + pkgNm + ".dao." + modelNm + "Dao;" + Context.CRLF);
        }

        serviceStr.append("import " + pkgNm + ".service." + modelNm + "Service;" + Context.CRLF);
        serviceStr.append("import " + pkgNm + ".repository." + modelNm + "Repository;" + Context.CRLF);

        // serviceStr.append("import com.springboot.demo.dto.Layout;" + Context.CRLF);
        serviceStr.append("import " + pkgNm + ".model." + modelNm + ";" + Context.CRLF2);

        return serviceStr.toString();
    }

    /**
     * @param  fileNm
     * @param  modelNm
     * @param  varNm
     * @param  hibernateFlag
     * @return
     */
    private String componentPart(String fileNm, String modelNm, String varNm, boolean hibernateFlag) {
        StringBuilder serviceStr = new StringBuilder();
        serviceStr.append("@Service" + Context.CRLF);
        serviceStr.append("public class " + fileNm + " implements " + modelNm + "Service {" + Context.CRLF2);

        serviceStr.append("    @Autowired" + Context.CRLF);
        serviceStr.append("    " + modelNm + "Repository " + varNm + "Repository;" + Context.CRLF2);
        if (hibernateFlag) {
            serviceStr.append("    @Autowired" + Context.CRLF);
            serviceStr.append("    " + modelNm + "Dao " + varNm + "Dao;" + Context.CRLF2);
        }
        return serviceStr.toString();
    }

    /**
     * @param  modelNm
     * @param  varNm
     * @param  hibernateFlag
     * @return
     */
    private String searchMethodPart(String modelNm, String varNm, boolean hibernateFlag) {
        StringBuilder serviceStr = new StringBuilder();
        serviceStr.append("    public List<" + modelNm + "> getSearchList(" + modelNm + " inDto) {" + Context.CRLF);
        serviceStr.append("        List<" + modelNm + "> resultList = new ArrayList<" + modelNm + ">();" + Context.CRLF2);
        serviceStr.append("        inDto = null == inDto ? new " + modelNm + "() : inDto;" + Context.CRLF);

        String daoNm = varNm + (hibernateFlag ? "Dao" : "Repository");
        if (!hibernateFlag) {
            serviceStr.append("        int recCnt = " + daoNm + ".getCountByDto(inDto);" + Context.CRLF);
            serviceStr.append("        int maxPage = recCnt % inDto.getPageSize() > 0 ? recCnt / inDto.getPageSize() + 1" + Context.CRLF);
            serviceStr.append("                : recCnt / inDto.getPageSize();" + Context.CRLF);
            serviceStr.append("        inDto.setMaxPage(maxPage);" + Context.CRLF);
            serviceStr.append("        inDto.setStartRow((inDto.getPageNo() - 1) * inDto.getPageSize());" + Context.CRLF);
            serviceStr.append("        resultList = " + daoNm + ".selectByDto(inDto);" + Context.CRLF);
        } else {
            serviceStr.append("        // JPA自带分页查询" + Context.CRLF);
            serviceStr.append("        Pageable pageing = PageRequest.of(inDto.getPageNo()-1, inDto.getPageSize(), Sort.Direction.ASC, \"id\");" + Context.CRLF);
            serviceStr.append("        Page<" + modelNm + "> resultList = " + daoNm + ".findAll(condition,pageing);" + Context.CRLF);
            serviceStr.append("        inDto.setActionName(\"\");" + Context.CRLF2);
            serviceStr.append("        Example<" + modelNm + "> condition = Example.of(inDto);" + Context.CRLF);
        }

        serviceStr.append("        return resultList;" + Context.CRLF);
        serviceStr.append("    }" + Context.CRLF2);
        return serviceStr.toString();
    }

    private String findOnePart(String modelNm, String varNm, boolean hibernateFlag, Table tableObj) {
        // 主键字段
        Column pkCol = tableObj.getPkey();
        StringBuilder serviceStr = new StringBuilder();
        serviceStr.append("    public " + modelNm + " selectOneById(" + pkCol.getJavaType() +" id) {" + Context.CRLF);
        serviceStr.append("        " + modelNm + " result = new " + modelNm + "();" + Context.CRLF2);

        String daoNm = varNm + (hibernateFlag ? "Dao" : "Repository");
        if (!hibernateFlag) {
            serviceStr.append("        result = " + daoNm + ".selectOneById(id);" + Context.CRLF);
            // serviceStr.append(" if (null == result) {" + Context.CRLF);
            // serviceStr.append(" result = " + daoNm + ".selectOneByUniqueKey(");
            // int index = 0;
            // for (Column column : tableObj.getUniqueKey()) {
            // serviceStr.append("inDto.get" + TextUtil.transNmDbToJava(column.getColumnName(), true) + (index < tableObj.getUniqueKey().size() ? "," : ""));
            // index++;
            // }
            // serviceStr.append(")" + Context.CRLF);
            // serviceStr.append(" }" + Context.CRLF);
        } else {
            serviceStr.append("        // JPA自带分页查询" + Context.CRLF);
        }

        serviceStr.append("        return result;" + Context.CRLF);
        serviceStr.append("    }" + Context.CRLF2);
        return serviceStr.toString();
    }

    // doInsertOrUpdate
    private String updatePart(String modelNm, String varNm, boolean hibernateFlag) {
        StringBuilder serviceStr = new StringBuilder();
        serviceStr.append(Context.SPACE4 + "@Override" + Context.CRLF);
        serviceStr.append(Context.SPACE4 + "public int doInsertOrUpdate(" + modelNm + " inDto) {" + Context.CRLF);

        String daoNm = varNm + (hibernateFlag ? "Dao" : "Repository");
        if (!hibernateFlag) {
            serviceStr.append(Context.SPACE8 + "int recCnt = -1;" + Context.CRLF);
            serviceStr.append(Context.SPACE8 + "if (null == inDto.getId()) {" + Context.CRLF);
            serviceStr.append(Context.SPACE12 + "recCnt = " + daoNm + ".insert(inDto);" + Context.CRLF);
            serviceStr.append(Context.SPACE8 + "} else {" + Context.CRLF);
            serviceStr.append(Context.SPACE12 + "recCnt = " + daoNm + ".update(inDto);" + Context.CRLF);
            serviceStr.append(Context.SPACE8 + "}" + Context.CRLF);
        } else {
            serviceStr.append("        // JPA自带分页查询" + Context.CRLF);
        }
        serviceStr.append("        return recCnt;" + Context.CRLF);
        serviceStr.append("    }" + Context.CRLF2);
        return serviceStr.toString();
    }

    private String deletePart(String modelNm, String varNm, boolean hibernateFlag,Table tableObj) {
        // 主键字段
        Column pkCol = tableObj.getPkey();
        StringBuilder serviceStr = new StringBuilder();
        serviceStr.append(Context.SPACE4 + "@Override" + Context.CRLF);
        serviceStr.append("    public void delete("+ pkCol.getJavaType() +" id) {" + Context.CRLF);

        String daoNm = varNm + (hibernateFlag ? "Dao" : "Repository");
        if (!hibernateFlag) {
            serviceStr.append(Context.SPACE8 + daoNm + ".delete(id);" + Context.CRLF);
        } else {
            serviceStr.append("        // JPA自带分页查询" + Context.CRLF);
        }
        serviceStr.append("    }" + Context.CRLF2);
        return serviceStr.toString();
    }
}
