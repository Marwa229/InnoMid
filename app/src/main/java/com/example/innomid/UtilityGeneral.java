package com.example.innomid;

import android.content.Context;
import android.net.ConnectivityManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class UtilityGeneral {

    static public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    static public boolean isConnectedWifi(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
    }

    static public boolean isArabic() {
        return Locale.getDefault().getDisplayLanguage().equals("العربية");
    }

    static public String GetUTCDateTimeAsString() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(new Date());
    }

    static public String GetDateTimeAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US);
        return sdf.format(new Date());
    }

    static public Date convertStringToDateTime(String time) {
        try {
            return new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US).parse(time);
        } catch (ParseException e) {
            return null;
        }
    }

}
