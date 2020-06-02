package com.mingjie.utils;

import java.util.UUID;

/**
 * @Author:Liweijian
 * @Description: 通用工具类
 */
public class CommonsUtils {

    //获取UUID
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    //校验验证码
    public static boolean checkCode(String checkCode, String session_checkCode){
        if (checkCode.equals(session_checkCode)){
            return true;
        }else {
            return false;
        }
    }
}
