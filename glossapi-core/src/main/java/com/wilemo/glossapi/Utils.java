package com.wilemo.glossapi;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by qiaoming on 2017/5/22.
 */
public class Utils {

    public static final String RESOURCE_PATH = Utils.class.getResource("/").getPath();

    public static String readDefaultString() {
        return readResourceToString("md/default.md");
    }

    public static String readResourceToString(String resourcePath) {
        try {
            return IOUtils.toString(new FileInputStream(RESOURCE_PATH + resourcePath), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void writeStringToResource(String resourcePath, String text) {
        try {
            String path = Utils.class.getResource("/").getPath();
            FileUtils.writeStringToFile(new File(path + resourcePath), text, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.out.println(getBase64("helloworld"));
    }

    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
