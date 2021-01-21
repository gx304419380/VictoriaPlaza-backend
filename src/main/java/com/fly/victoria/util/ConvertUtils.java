package com.fly.victoria.util;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;

/**
 * @author guoxiang
 * @version 1.0.0
 * @since 2021/1/21
 */
public class ConvertUtils {
    //驼峰转下划线
    private static Converter<String, String> camelToUnderscoreConverter =
            CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);

    public static String convertToUnderscore(String s) {
        return camelToUnderscoreConverter.convert(s);
    }

}
