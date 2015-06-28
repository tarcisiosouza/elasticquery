package de.l3s.elasticquery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

final class DateUtils {

    public static String parseDate(String date_str) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat df11 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df3 = new SimpleDateFormat("EEE, MMM d, yyyy");
        SimpleDateFormat df4 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        SimpleDateFormat df5 = new SimpleDateFormat("EEE, MMM. dd, yyyy");
        SimpleDateFormat df6 = new SimpleDateFormat("EEE, MMM dd, yyyy");
        SimpleDateFormat df7 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat df8 = new SimpleDateFormat("EEEEE d MMM yyyy");
        SimpleDateFormat df9 = new SimpleDateFormat("EEEEE, MMM. dd, yyyy");
        SimpleDateFormat df10 = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat df12 = new SimpleDateFormat("MMM dd, yyyy, HH:mm a");
        SimpleDateFormat df14 = new SimpleDateFormat("MMM dd, yyyy, H:mm a");
        SimpleDateFormat df13 = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat df15 = new SimpleDateFormat("MMMMM dd, yyyy, HH:mm a");
        SimpleDateFormat df16 = new SimpleDateFormat("MMMMM dd, yyyy, H:mm a");
        SimpleDateFormat df17 = new SimpleDateFormat("MMM. dd, yyyy");
        SimpleDateFormat df18 = new SimpleDateFormat("MMMMM dd, yyyy");
        SimpleDateFormat df19 = new SimpleDateFormat("EEEEE, MMM. dd, yyyy");
        SimpleDateFormat df_20 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        SimpleDateFormat df21 = new SimpleDateFormat("MMMM dd, yyyy");
        SimpleDateFormat df22 = new SimpleDateFormat("MMMM dd, yyyy HH:mm a");
        SimpleDateFormat df23 = new SimpleDateFormat("EEE MMMM dd HH:mm:ss z yyyy");
        SimpleDateFormat df24 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df25 = new SimpleDateFormat("dd-MM-yyyy");
        List<SimpleDateFormat> lst = new ArrayList<SimpleDateFormat>();
        lst.add(df);
        lst.add(df1);
        lst.add(df2);
        lst.add(df3);
        lst.add(df4);
        lst.add(df5);
        lst.add(df6);
        lst.add(df7);
        lst.add(df8);
        lst.add(df9);
        lst.add(df10);
        lst.add(df11);
        lst.add(df12);
        lst.add(df13);
        lst.add(df14);
        lst.add(df15);
        lst.add(df16);
        lst.add(df17);
        lst.add(df18);
        lst.add(df19);
        lst.add(df_20);
        lst.add(df21);
        lst.add(df22);
        lst.add(df23);
        lst.add(df24);
        lst.add(df25);
        SimpleDateFormat df_simple = new SimpleDateFormat("yyyyMMddHHmmss");
        for (SimpleDateFormat df_tmp : lst) {
            try {
                Date dt = df_tmp.parse(date_str);
                return df_simple.format(dt);
            } catch (Exception e) {
                continue;
            }
        }
        return "";

    }

}
