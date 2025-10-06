package com.application.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.application.common.Context;

public class TextUtil {

    /**
     * 判断字符串不为空
     *
     * @param  text
     * @return
     */
    public static boolean isNotEmpty(String text) {
        if (null != text && !"".equals(text)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 数组中是否包含特定字符串
     *
     * @param  aStrArr
     * @param  target
     * @return
     */
    public static boolean isInclude(String[] aStrArr, String target) {
        for (String name : aStrArr) {
            if (name.equals(target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将DB的字段名转为Java的驼峰命名
     *
     * @param  dbNm        DB字段名
     * @param  firstUpFlag 首字母大写
     * @return
     */
    public static String transNmDbToJava(String dbNm, boolean firstUpFlag) {
        String[] nameArr = dbNm.split("_");
        String javaNm = "";
        for (int i = 0; i < nameArr.length; i++) {
            String nm = nameArr[i].toLowerCase();
            if (i > 0 || firstUpFlag) {
                nm = nm.substring(0, 1).toUpperCase() + nm.substring(1).toLowerCase();
            }
            javaNm += nm;
        }
        return javaNm;
    }

    /**
     * 正则匹配
     *
     * @param  text
     * @param  regex
     * @param  replacement
     * @return
     */
    public static String replaceTextPattern(String text, String regex, String replacement) {
        String result = text.replaceAll(regex, replacement);
        System.out.println(result);
        return result;
    }

    public static List<String> getPatternStr(String text, String regex) {
        List<String> result = new ArrayList<String>();
        // 正则表达
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    public static String fillChar(String str, String charS, int length, String orientation) {
        while (str.length() < length) {
            if (Context.BEFORE.equals(orientation)) {
                str = charS + str;
            } else {
                str = str + charS;
            }
        }
        return str;
    }

    /**
     * @param  ddlLine
     * @return
     */
    public static String replaceLineHeard(String ddlLine) {
        while (ddlLine.indexOf(" ") == 0 || ddlLine.indexOf(",") == 0) {
            ddlLine = ddlLine.substring(1);
            replaceLineHeard(ddlLine);
        }
        return ddlLine;
    }

    public static String fromatMoney(BigDecimal money, int intLen, int decLen) {
        String moneyStr = money.toString();
        String intPart, decPart = "";
        // 整数部分
        intPart = moneyStr.split("\\.")[0].replace("-", "");
        // 从后往前截取
        intPart = intPart.substring((intPart.length() - intLen) > 0 ? intPart.length() - intLen : 0);
        // 负数判断
        intPart = money.compareTo(BigDecimal.ZERO) > 0 ? intPart : "-" + intPart;
        if (moneyStr.indexOf(".") > 0) {
            // 小数部分
            decPart = moneyStr.split("\\.")[1];
            decPart = decPart.substring(0, decLen > decPart.length() - 1 ? decPart.length() - 1 : decLen);
        }
        moneyStr = "".equals(decPart) ? intPart : intPart + "." + decPart;
        return moneyStr;
    }

    public static void main(String[] args) {
        // replaceTextPattern("dyl@19880816$test001", "[a-z|A-Z]", "0");
        // replaceTextPattern("dyl@19880816$test001", "[^0-9]", "0");
        // System.out.println(replaceLineHeard(" , abc varchar(20) not null --哈哈"));
        // System.out.println(fillChar("1", "0", 2, Context.BEFORE));

        // BigDecimal money = new BigDecimal("-01234.12321");
        // String moneyStr = fromatMoney(money, 2, 3);
        // DecimalFormat fmt = new DecimalFormat("##,###,###,###,###.###");
        // System.out.println(moneyStr + ":" + fmt.format(new BigDecimal(moneyStr)));

        List<String> result = getPatternStr("ON ADM.C_USER_ID = TI.C_USER_ID", "[a-zA-Z0-9_]*\\.[a-zA-Z0-9_]*");
        for (String rst : result) {
            System.out.println(rst);
        }
    }
}
