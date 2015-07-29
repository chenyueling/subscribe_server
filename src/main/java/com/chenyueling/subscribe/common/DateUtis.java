package com.chenyueling.subscribe.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenyueling on 2015/4/29.
 */
public class DateUtis {

    public static final SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

    public static final String formatDate(Date date){
        return sdf.format(date);
    }
}
