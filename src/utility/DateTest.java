package utility;

import java.text.*;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

    public static String hearingCase(String set) throws ParseException {

        Date dt = new Date();
        System.out.println("\nDate Today : "+dt.getDate() + "." + (dt.getMonth()+1) + "." + Integer.parseInt(new SimpleDateFormat("yyyy").format(dt)));
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt.getDate() + "." + (dt.getMonth()+1) + "." + Integer.parseInt(new SimpleDateFormat("yyyy").format(dt))));
        c.add(Calendar.DATE, Integer.parseInt(set));
        System.out.println("\nDate you set hearing : "+sdf.format(c.getTime())+"\n");
        return sdf.format(c.getTime());
    }
}