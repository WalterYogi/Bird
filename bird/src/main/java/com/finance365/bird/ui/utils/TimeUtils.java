package com.finance365.bird.ui.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2016/5/5.
 */
public class TimeUtils {

    public static String getSysTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());

    }
}
