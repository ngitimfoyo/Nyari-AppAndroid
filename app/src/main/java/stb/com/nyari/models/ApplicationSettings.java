package stb.com.nyari.models;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ari_S on 7/27/2015.
 */
public class ApplicationSettings {
    private static String _serviceUrl = "http://mmsmobile-test.mitrais.com/MMSMobilePoly";

    public static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(Metadata.PreferenceName,
                Context.MODE_PRIVATE);
    }

    public static String getServiceURL(Context context) {
        return getPrefs(context).getString(Metadata.serviceURL, _serviceUrl);
    }

    public static void setServiceURL(Context context, String value) {
        getPrefs(context).edit().putString(Metadata.serviceURL, value).commit();
    }

    public static String getUserPassword(Context context) {
        return getPrefs(context).getString(Metadata.userPassword, "");
    }

    public static void setUserPassword(Context context, String value) {
        getPrefs(context).edit().putString(Metadata.userPassword, value).commit();
    }

    public static String getUserName(Context context) {
        return getPrefs(context).getString(Metadata.userName, "");
    }

    public static void setUserName(Context context, String value) {
        getPrefs(context).edit().putString(Metadata.userName, value).commit();
    }

    public static boolean getIsRemembered(Context context) {
        return getPrefs(context).getBoolean(Metadata.isRemembered, true);
    }

    public static void setIsRemembered(Context context, boolean value) {
        getPrefs(context).edit().putBoolean(Metadata.isRemembered, value)
                .commit();
    }

    public static boolean getIsLogin(Context context) {
        return getPrefs(context).getBoolean(Metadata.isLogin, false);
    }

    public static void setIsLogin(Context context, boolean value) {
        getPrefs(context).edit().putBoolean(Metadata.isLogin, value)
                .commit();
    }

    public static String getFullName(Context context) {
        return getPrefs(context).getString(Metadata.fullName, "");
    }

    public static void setFullName(Context context, String value) {
        getPrefs(context).edit().putString(Metadata.fullName, value).commit();
    }

    public static String getConfigValue(Context context, String key) {
        return getPrefs(context).getString(key, "");
    }

    public static void setConfigValue(Context context, String key, String value) {
        getPrefs(context).edit().putString(key, value).commit();
    }

    public static int getConfigVersion(Context context) {
        return getPrefs(context).getInt(Metadata.configVersion, 0);
    }

    public static void setConfigVersion(Context context, int value) {
        getPrefs(context).edit().putInt(Metadata.configVersion, value).commit();
    }

    public static void setLastService(Context context, int value) {
        getPrefs(context).edit().putInt(Metadata.lastService, value).commit();
    }

    public static int getLastService(Context context){
        return getPrefs(context).getInt(Metadata.lastService, 0);
    }

    public static void setLastShiftCode(Context context, int value) {
        getPrefs(context).edit().putInt(Metadata.lastShiftCode, value).commit();
    }

    public static int getLastShiftCode(Context context){
        return getPrefs(context).getInt(Metadata.lastShiftCode, 0);
    }

    public static class Metadata {
        public static final String PreferenceName = "sharedPreferences";

        public static final String serviceURL = "serviceURL";
        public static final String userPassword = "userPassword";
        public static final String userName = "userName";
        public static final String isRemembered = "isRemembered";
        public static final String isLogin = "isLogin";
        public static final String fullName = "fullName";

        public static final String totalPerPage = "TotalPerPage";
        public static final String configVersion = "ConfigVersion";

        public static final String lastService = "LastService";
        public static final String lastShiftCode = "LastShiftCode";
    }
}
