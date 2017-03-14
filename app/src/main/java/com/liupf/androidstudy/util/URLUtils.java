package com.liupf.androidstudy.util;

import android.util.Log;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by lpf on 2017/3/7.
 */

public class URLUtils {
    public static String urlEncoded(String paramString) {

        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        } catch (Exception localException) {
        }

        return "";
    }

    public static String urlDecoded(String paramString) {
        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLDecoder.decode(str, "UTF-8");
            return str;
        } catch (Exception localException) {
        }

        return "";
    }


    public static void main(String[] args){
        String url="https://www.baidu.com/";
        url=xmlEncoded(url);
        System.out.println(url);
    }
    /**
     * xml特殊字符转义
     * @param paramString
     * @return
     */
    public static String xmlEncoded(String paramString) {
        if(paramString==null||"".equals(paramString)){
            return "";
        }
        paramString=paramString.replaceAll("\"", "&quot;");
        paramString=paramString.replaceAll("&", "&amp;");
        paramString=paramString.replaceAll("<", "&lt;");
        paramString=paramString.replaceAll(">", "&gt;");
        paramString=paramString.replaceAll(" ", "&nbsp;");
        return paramString;
    }

    /**
     * xml特殊字符解析
     * @param paramString
     * @return
     */
    public static String xmlDecoded(String paramString) {
        if(paramString==null||"".equals(paramString)){
            return "";
        }
        paramString=paramString.replaceAll("&quot;", "\"");
        paramString=paramString.replaceAll("&amp;", "&");
        paramString=paramString.replaceAll("&lt;", "<");
        paramString=paramString.replaceAll("&gt;", ">");
        paramString=paramString.replaceAll("&nbsp;", " ");
        return paramString;
    }

}
