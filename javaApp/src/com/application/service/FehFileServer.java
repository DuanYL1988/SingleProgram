package com.application.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.application.common.Context;
import com.application.util.TextUtil;

public class FehFileServer {

    private static final String FOLDER_PATH_MARK = "\\";

    private static Map<String, String> MAPPING_NAME_MAP = null;

    private static final String NORMAL_BEF = "_Face.webp";

    private static final String NORMAL_AFT = "normal.png";

    private static final String ATTACT_BEF = "BtlFace.";

    private static final String ATTACT_AFT = "attact.png";

    private static final String BREAK_BEF = "BtlFace_D.";

    private static final String BREAK_AFT = "break.png";

    private static final String EXTRA_BEF = "BtlFace_C.";

    private static final String EXTRA_AFT = "extra.png";

    private static final String FACE_BEF = "_Face.png";

    private static final String FACE_AFT = "face.png";

    private static final String[] RADIO_TYPE_ARR = new String[] { ".mkv", ".mp4" };

    public static int COUNT = 0;

    /**
     * @param 文件夹目录
     */
    public String fehImgRename(String folderPath, String type, StringBuilder log) throws Exception {
        // 取得文件夾文件對象
        File folder = new File(folderPath);
        // 取得文件夹目录下文件
        File[] fileList = folder.listFiles();

        if (null != fileList) {
            for (File file : fileList) {
                // 子文件是文件夹的情况
                if (file.isDirectory()) {
                    // 迭代
                    fehImgRename(file.getPath(), type, log);
                } else {
                    if (Context.FEH.equals(type)) {
                        log.append(getRenameByName(file));
                    } else {
                        getFgoRenameByName(file);
                    }
                }
            }
        }
        return log.toString();
    }

    public static void main(String[] args) {
        FehFileServer service = new FehFileServer();
        String baseFolder = "D:\\Movie\\Animate\\ロード・エルメロイⅡ世の事件簿 -魔眼蒐集列車";
        service.radioRename(baseFolder, "1", 0);
    }

    public String radioRename(String baseFolder, String sessionNo, int startEpNo) {
        File folder = new File(baseFolder);
        // 判断是否为文件夹
        if (!folder.isDirectory()) {
            return "不是文件夹";
        }
        String baseName = folder.getName();
        StringBuilder log = new StringBuilder();
        // 文件夹下类容
        for (File file : folder.listFiles()) {
            String name = file.getName();
            String typeName = name.lastIndexOf(".") > 0 ? name.substring(name.lastIndexOf(".")) : "";
            if (TextUtil.isInclude(RADIO_TYPE_ARR, typeName)) {
                String rename = baseName + ".S" + TextUtil.fillChar(sessionNo + "", "0", 2, Context.BEFORE) + "E" + TextUtil.fillChar(startEpNo + "", "0", 2, Context.BEFORE) + typeName;
                file.renameTo(new File(file.getParent() + FOLDER_PATH_MARK + rename));
                String fileLog = "File name before :" + name + " → " + rename;
                log.append(fileLog + Context.CRLF);
                System.out.println(fileLog);
                startEpNo++;
            }
        }
        return log.toString();
    }

    /**
     * 重命名文件(FEH)
     *
     * @param file
     */
    private String getRenameByName(File file) {
        if (MAPPING_NAME_MAP == null) {
            MAPPING_NAME_MAP = initNameMappingMap();
        }
        return reName(file);
    }

    /**
     * 重命名文件(FGO)
     *
     * @param file
     */
    private void getFgoRenameByName(File file) {
        if (MAPPING_NAME_MAP == null) {
            MAPPING_NAME_MAP = initFgoNameMappingMap();
        }
        reName(file);
    }

    /**
     * 文件重命名
     *
     * @param file
     * @param rename
     */
    private String reName(File file) {
        StringBuilder logSb = new StringBuilder();
        Set<String> mappingName = MAPPING_NAME_MAP.keySet();
        for (String name : mappingName) {
            String rename = MAPPING_NAME_MAP.get(name);
            String fileNm = file.getName().toLowerCase();
            String keyNm = name.toLowerCase();
            if (fileNm.indexOf(keyNm) >= 0) {
                file.renameTo(new File(file.getParent() + FOLDER_PATH_MARK + rename));
                String log = "File name before :" + file.getPath() + " → " + rename;
                logSb.append(log + Context.CRLF);
                System.out.println(log);
                COUNT++;
                break;
            }
        }
        return logSb.toString();
    }

    /**
     * FEH立绘图片名修正匹配
     *
     * @return
     */
    private Map<String, String> initNameMappingMap() {
        MAPPING_NAME_MAP = new HashMap<String, String>();
        MAPPING_NAME_MAP.put(NORMAL_BEF, NORMAL_AFT);
        MAPPING_NAME_MAP.put(ATTACT_BEF, ATTACT_AFT);
        MAPPING_NAME_MAP.put(BREAK_BEF, BREAK_AFT);
        MAPPING_NAME_MAP.put(EXTRA_BEF, EXTRA_AFT);
        MAPPING_NAME_MAP.put(FACE_BEF, FACE_AFT);
        return MAPPING_NAME_MAP;
    }

    /**
     * FGO立绘图片名修正匹配
     *
     * @return
     */
    private Map<String, String> initFgoNameMappingMap() {
        MAPPING_NAME_MAP = new HashMap<String, String>();
        MAPPING_NAME_MAP.put("_Stage1.png", "Stage1.png");
        MAPPING_NAME_MAP.put("_Stage2.png", "Stage2.png");
        MAPPING_NAME_MAP.put("_Stage3.png", "Stage3.png");
        MAPPING_NAME_MAP.put("_Stage4.png", "Stage4.png");
        MAPPING_NAME_MAP.put("Icon.png", "Icon.png");
        return MAPPING_NAME_MAP;
    }

}
