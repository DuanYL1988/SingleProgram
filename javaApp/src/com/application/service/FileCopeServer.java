package com.application.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import com.application.common.Context;
import com.application.util.FileUtils;

public class FileCopeServer {

    private static String BASE_SRC = "C:\\Users\\dylsw\\OneDrive\\图片\\feTemp";
    private static File BASE_FOLDER = new File(BASE_SRC);
    private static String TARGET_PATH = "D:\\output\\temp\\";
    public static int COUNT = 0;

    public static void main(String[] args) {
        FileUtils.createFolder(TARGET_PATH);
        FileCopeServer local = new FileCopeServer();
        StringBuilder log = new StringBuilder();
        local.loopFolder(BASE_FOLDER, log);
        System.out.println(log + "Finish");
    }

    public void createUploadImg(String basePath, String targetPath, StringBuilder log) {
        COUNT = 0;
        BASE_SRC = basePath;
        TARGET_PATH = targetPath + "\\temp\\";
        FileUtils.createFolder(TARGET_PATH);
        BASE_FOLDER = new File(BASE_SRC);
        loopFolder(BASE_FOLDER, log);
        log.append("Finish Copy File Count : " + COUNT);
    }

    private void loopFolder(File file, StringBuilder log) {
        if (file.isDirectory()) {
            String[] childs = file.list();
            for (String childPath : childs) {
                File childFile = new File(file.getAbsolutePath() + "\\" + childPath);
                loopFolder(childFile, log);
            }
        } else {
            String[] path = file.getAbsolutePath().replace(BASE_SRC, "").split("\\\\");
            String pathName = path[path.length - 3] + "_" + path[path.length - 2] + "_" + giveIndex(path[path.length - 1]);
            if ("_".equals(pathName.substring(0, 1))) {
                pathName = pathName.substring(1);
            }
            File dest = new File(TARGET_PATH + pathName);
            try {
                copyFileUsingFileChannels(file, dest);
                log.append("Copy From : " + file.getName() + " → " + pathName + Context.CRLF);
                COUNT++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("resource")
    private void copyFileUsingFileChannels(File source, File dest) throws IOException {
        if (null == dest) {
            return;
        }
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }

    private String giveIndex(String name) {
        if (name.indexOf("face") >= 0) {
            name = name.replace("face", "1");
        } else if (name.indexOf("normal") >= 0) {
            name = name.replace("normal", "2");
        } else if (name.indexOf("attact") >= 0) {
            name = name.replace("attact", "3");
        } else if (name.indexOf("extra") >= 0) {
            name = name.replace("extra", "4");
        } else if (name.indexOf("break") >= 0) {
            name = name.replace("break", "5");
        }
        return name;
    }
}
