package com.application.creater;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.application.util.FileUtils;
import com.application.util.PropertyUtil;

public class BaseCreater implements Creater {

    PropertyUtil propUtil = new PropertyUtil();

    public String writeFile(String location, String pkgPath, String fileName, String writeStr) {
        // 创建文件夹
    	location += pkgPath.replace(".", "\\") + "\\";
        FileUtils.createFolder(location);
        // 生成文件
        location += fileName;
        
        FileUtils.writeFile(location, writeStr);
        return location;
    }

    public List<String> getTemplete(String templeteName) {
        String templatePath = FileUtils.getPath() + "\\resources\\" + templeteName;
        List<String> result = null;
        try {
            result = FileUtils.getFileText(new File(templatePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
