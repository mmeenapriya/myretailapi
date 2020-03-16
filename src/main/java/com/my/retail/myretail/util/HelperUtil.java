package com.my.retail.myretail.util;

import org.springframework.util.StringUtils;

public class HelperUtil {

    public static String validate(String id)
    {
        String error = null;
        if (StringUtils.isEmpty(id)){
            error = "Please provide valid input";
        }
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e ){
            error = "Please enter a valid product id ";
        }
        return error;
    }
}
