package magic.studio.service.customer.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Amor on 03-08-2017.
 */

public class GlobalValue {
    Context context;

    public static String MY_PREFS_NAME = "ServiceApp";
    public static String ID = "id";

    public GlobalValue(Context context) {
        this.context = context;
    }

    public void put(String name, String value) {
        try {
            SharedPreferences.Editor editor = context.getSharedPreferences(
                    MY_PREFS_NAME, Activity.MODE_PRIVATE).edit();
            editor.putString(name, value);
            editor.commit();
        } catch (Exception exp) {
        }
    }
    public void clear() {
        try {
            SharedPreferences.Editor editor = context.getSharedPreferences(
                    MY_PREFS_NAME, Activity.MODE_PRIVATE).edit();
            editor.clear();
            editor.commit();
        } catch(Exception exp) {

        }
    }
    public String getString(String value) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME,
                    Activity.MODE_PRIVATE);
            return prefs.getString(value, "");
        } catch(Exception exp) {
            return "";
        }
    }
}