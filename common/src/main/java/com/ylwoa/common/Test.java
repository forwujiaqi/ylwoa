package com.ylwoa.common;

import com.google.common.base.CharMatcher;

/**
 * Created by wubiqing on 2017/8/13.
 */
public class Test {
    public static void main(String[] args) {
//        System.out.println(maskForCookie("aasfasf"));
//        System.out.println(Commons.maskForDB("15250084792"));

//        String aa = "4567";
//        System.out.println(aa.substring(2,3));


//        System.out.println(CharMatcher.javaLetterOrDigit().matchesAllOf("sf3ｓ３SDｄdsf"));

        String a ="asf，dfsf，".replaceAll("，",",");
        System.out.println(a);
    }
}
