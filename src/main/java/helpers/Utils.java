package helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static Date getShiftedDate(int years, int months, int days) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, years);
        c.add(Calendar.MONTH, months);
        c.add(Calendar.DAY_OF_YEAR, days);
        return c.getTime();
    }

    public static String getFormatDateByPattern(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}