package com.ylwoa.common;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

/**
 * Created by wubiqing on 2017/7/15.
 */
public class Commons {
    public static final int ACTIVE_STATE = 0;
    public static final int INACTIVE_STATE = 1;//删除flg
    public static final String USER_COOKIE_KEY = "uid";
    public static final String USER_SESSION_MARK = "USER_SESSION_MARK";
    public static final String DELETE_FLG = "deleteFlg";

    // TODO 换枚举
    public static final int PROGRESS = 1;

    public static String maskForCookie(String str) {
        String MASK_FOR_COOKIE = "rdfEk83.ejK.IMnBdU";
        return Hashing.sha256().hashString(MASK_FOR_COOKIE + str, Charsets.UTF_8).toString();
    }

    public static String maskForDB(String str) {
        String MASK_FOR_DB = "W2jN.OibW73Pmaf0EJ";
        return Hashing.sha256().hashString(MASK_FOR_DB + str, Charsets.UTF_8).toString();
    }

    public static void main(String[] args) {
        System.out.println(maskForCookie("aasfasf"));
    }

}
