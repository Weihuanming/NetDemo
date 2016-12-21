package test.netdemo.util;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by whm on 16/12/21.
 */

public class TimeUtil {
    public static int getHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
}
