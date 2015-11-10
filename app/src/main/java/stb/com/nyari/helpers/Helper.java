package stb.com.nyari.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import stb.com.nyari.R;
import stb.com.nyari.models.ApplicationSettings;


/**
 * Created by Ari_S on 7/27/2015.
 */
public class Helper {
    public static void toastIt(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * @param context
     */
    public static void hideKeyboard(Context context) {
        InputMethodManager inputManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        //check if no view has focus:
        View view = ((Activity) context).getCurrentFocus();
        if (view == null)
            return;

        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * @param context
     */
    public static void logout(Context context) {
        // clear all shared preference except the version
//        int version = ApplicationSettings.getConfigVersion(context);
//        ApplicationSettings.getPrefs(context).edit().clear().commit();
//        ApplicationSettings.setConfigVersion(context, version);
//
//        removeCredential(context);
//
//        // return to login page
//        Intent intent = new Intent(context, LoginActivity.class);
//        context.startActivity(intent);
//        ((Activity) context).finish();
    }

    /**
     * @param context
     * @param extras
     */
    public static void logout(Context context, Bundle extras) {
        removeCredential(context);

        // return to login page
//        Intent intent = new Intent(context, LoginActivity.class);
//        intent.putExtras(extras);
//        context.startActivity(intent);
//        ((Activity) context).finish();
    }

    /**
     * @param context
     */
    public static void removeCredential(Context context) {
        ApplicationSettings.setUserName(context, "");
        ApplicationSettings.setUserPassword(context, "");
        ApplicationSettings.setIsLogin(context, false);
        ApplicationSettings.setFullName(context, "");
        ApplicationSettings.setIsRemembered(context, true);
    }

    /**
     * @param context
     * @param message
     * @return
     */
    public static ProgressDialog showWaitView(Context context, String message) {
        ProgressDialog myDialog = new ProgressDialog(context);
        myDialog.setMessage(message);
        myDialog.setCancelable(false);
        myDialog.show();

        return myDialog;
    }

    /**
     * @param dialog
     */
    public static void hideWaitView(ProgressDialog dialog) {
        if ((dialog != null) && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
    }

    /**
     * @param context
     * @param title
     * @param message
     * @param listener
     * @return
     */
    public static AlertDialog showConfirmationDialog(Context context, String title, String message, final OnConfirmationListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(R.drawable.ic_launcher_chrome);

        builder.setPositiveButton(context.getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onOkClick();
                    }
                });

        builder.setNegativeButton(context.getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();

        return alert;
    }

    /**
     * Dialog with button action listener
     * @param context
     * @param title
     * @param message
     * @param dialog
     * @param listener
     * @return
     */
    public static AlertDialog showErrorDialog(Context context, String title, String message,
                                              ProgressDialog dialog,
                                              final OnConfirmationListener listener) {
        if (dialog != null) {
            hideWaitView(dialog);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(R.drawable.ic_launcher_chrome);

        builder.setPositiveButton(context.getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onOkClick();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();

        return alert;
    }

    /**
     * @param context
     * @param title
     * @param message
     * @param dialog
     * @return
     */
    public static AlertDialog showErrorDialog(Context context, String title, String message, ProgressDialog dialog) {
        if (dialog != null) {
            hideWaitView(dialog);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(R.drawable.ic_launcher_chrome);

        builder.setNegativeButton(context.getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();

        return alert;
    }

    /**
     * @param context
     * @param message
     */
    public static void errorOnLogin(Context context, String message) {
        Bundle extras = new Bundle();
        extras.putBoolean(Constants.EXTRA_IS_EXCEPTION, true);
        extras.putString(Constants.EXTRA_EXCEPTION_MESSAGE, message);
        logout(context, extras);
    }

    public interface OnConfirmationListener {
        void onOkClick();
    }

    /**
     * @param context
     * @return
     */
    public static String appNameAndVersionNumberDisplayString(Context context) {
        String versionName = "";
        Integer versionCode = 0;
        try {
            versionName = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
            versionCode = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return (context.getString(R.string.version) + " " + versionName + "." + versionCode);
    }

    /**
     * @param appcontext
     * @return
     */
    public static Boolean isMobileAvailable(Context appcontext) {
        TelephonyManager tel = (TelephonyManager) appcontext
                .getSystemService(Context.TELEPHONY_SERVICE);
        return ((tel.getNetworkOperator() != null && tel.getNetworkOperator()
                .equals("")) ? false : true);
    }

    /**
     * @param context
     * @param url
     */
    public static void openInBrowser(Context context, String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }

    /**
     * @param context
     * @param locationAddress
     */
    public static void openLocationInMaps(Context context, String locationAddress) {
        String map = Constants.URL_GOOGLE_MAP + locationAddress;
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
        context.startActivity(i);
    }

    /**
     * @param context
     * @param emailAddress
     * @param isWithSubject
     * @param subject
     * @param message
     * @param isWithAttachment
     * @param attachment
     */
    public static void sendEmail(Context context, String emailAddress, boolean isWithSubject, String subject, String message, boolean isWithAttachment, Uri attachment) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        // to:
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
        if (isWithSubject) {
            // subject:
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        } else {
            subject = null;
        }

        if (isWithAttachment) {
            // attachment
            sendIntent.putExtra(Intent.EXTRA_STREAM, attachment);
        } else {
            attachment = null;
        }

        // body
        sendIntent.putExtra(Intent.EXTRA_TEXT, getTimeStamp() + " \n" + message);
        sendIntent.setType(Constants.TYPE_TEXT_HTML);
        context.startActivity(Intent.createChooser(sendIntent, context.getString(R.string.send_email_using) + " : "));
    }

    /**
     * @return
     */
    private static String getTimeStamp() {
        Long tsLong = System.currentTimeMillis() / 1000;
        String ts = tsLong.toString();

        return ts;
    }

}
