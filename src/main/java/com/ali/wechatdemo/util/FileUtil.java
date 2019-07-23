package com.ali.wechatdemo.util;


public class FileUtil
{

    /**
     * Description: 获取文件后缀名
     *
     * @param fileName
     * @return
     * @see
     */
    public static String getExtensionName(String fileName)
    {
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return prefix;
    }

    /**
     * 根据path获取文件名
     * @param filename
     * @return
     */
    public static String getOriginalFilename(String filename)
    {
        if (filename == null) return "";
        int pos = filename.lastIndexOf("/");
        if (pos == -1) pos = filename.lastIndexOf("\\");
        if (pos != -1)
            return filename.substring(pos + 1);
        else
            return filename;
    }

}

