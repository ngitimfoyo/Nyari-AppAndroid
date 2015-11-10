package stb.com.nyari.helpers;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;
import stb.com.nyari.models.ApplicationSettings;

/**
 * Singleton RestClient
 * Created by Ari_S on 7/27/2015.
 */
public class HttpClient {
    private static HttpClient mInstance = null;
    private AsyncHttpClient aSyncClient;
    private static boolean loggedIn = true;

    /**
     * @return
     */
    public static HttpClient getInstance() {
        if(mInstance == null) {
            mInstance = new HttpClient();
        }

        return mInstance;
    }

    private HttpClient() {
        aSyncClient = new AsyncHttpClient();
        aSyncClient.setTimeout(60 * 1000);
        aSyncClient.setMaxRetriesAndTimeout(5, 1000);
    }

    /**
     * @return
     */
    public AsyncHttpClient getClient(){
        return aSyncClient;
    }

    private String getAbsoluteUrl(Context context, String relativeUrl) {
        return ApplicationSettings.getServiceURL(context) + relativeUrl;
    }

    /**
     * @param context
     * @param relativeUrl
     * @param entity
     * @param contentType
     * @param responseHandler
     * @throws JSONException
     * @throws UnsupportedEncodingException
     */
    public void post(final Context context, final String relativeUrl, final HttpEntity entity, final String contentType, final ResponseHandlerInterface responseHandler) throws JSONException, UnsupportedEncodingException {
        final String absoluteUrl = getAbsoluteUrl(context, relativeUrl);

        verifyLogin(context, new Runnable() {
            @Override
            public void run() {
                aSyncClient.post(context, absoluteUrl, entity, contentType, responseHandler);
            }
        });
    }

    public void get(final  Context context, final String relativeUrl, final RequestParams params, final ResponseHandlerInterface responseHandler) throws  JSONException, UnsupportedEncodingException {
        final String absoluteUrl = getAbsoluteUrl(context, relativeUrl);
        verifyLogin(context, new Runnable() {
            @Override
            public void run() {
                aSyncClient.get(context, absoluteUrl, params, responseHandler);
            }
        });
    }

    public void get(final Context context, String relativeUrl, final ResponseHandlerInterface responseHandler) throws JSONException, UnsupportedEncodingException {
        final String absoluteUrl =  getAbsoluteUrl(context, relativeUrl);

        verifyLogin(context, new Runnable() {
            @Override
            public void run() {
                aSyncClient.get(context, absoluteUrl, responseHandler);
            }
        });
    }


    /**
     * @param context
     * @param thenRun
     * @throws JSONException
     * @throws UnsupportedEncodingException
     */
    public void verifyLogin(Context context, final Runnable thenRun) throws JSONException, UnsupportedEncodingException
    {
        if(loggedIn)
        {
            thenRun.run();
        } else {
            login(context, new Runnable() {
                @Override
                public void run() {
                    thenRun.run();
                }
            }, new Runnable() {
                @Override
                public void run() {

                }
            }, new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    }

    /**
     * @param context
     * @param onSuccess
     * @param onFailed
     * @param onFailured
     * @throws JSONException
     * @throws UnsupportedEncodingException
     */
    public void login(final Context context, final Runnable onSuccess, final Runnable onFailed, final Runnable onFailured) throws JSONException, UnsupportedEncodingException
    {
        String userName = ApplicationSettings.getUserName(context);
        String encryptedPassword = HttpClient.getInstance().encryptDecryptPassword(context, null, false);

        login(context, userName, encryptedPassword, onSuccess, onFailed, onFailured);
    }

    public void login(final Context context, final String userName, final String password, final Runnable onSuccess, final Runnable onFailed, final Runnable onFailured) throws JSONException, UnsupportedEncodingException  {
//        final AsyncHttpClient client = new AsyncHttpClient();
//        String baseUrl = ApplicationSettings.getServiceURL(context);
//        String jsonLoginURL = context.getString(R.string.json_login_url);
//        String urlLogin = baseUrl + jsonLoginURL;
//        String deviceId = Helper.getDeviceId(context);
//
//        JSONObject jsonParams = new JSONObject();
//        jsonParams.put(context.getString(R.string.user_name_json), userName);
//        jsonParams.put(context.getString(R.string.password_json), password);
//        jsonParams.put(context.getString(R.string.did_json), deviceId);
//        StringEntity entity = new StringEntity(jsonParams.toString());
//
//        client.setTimeout(60 * 1000);
//        client.setMaxRetriesAndTimeout(5, 1000);
//        // set cookie the post request
////        client.addHeader("Cookie", context.getString(R.string.cookie_collaborate) + firmID);
//        client.post(context, urlLogin, entity, Constants.K_MIME_TYPE_JSON,
//                new JsonHttpResponseHandler() {
//
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, Throwable e,
//                                          JSONObject errorResponse) {
//                        super.onFailure(statusCode, headers, e, errorResponse);
//
//                        Log.e("Login error", e.getMessage());
//                        onFailured.run();
//                    }
//
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                        super.onSuccess(statusCode, headers, response);
//
//                        String loginResult = "";
//                        try {
//                            loginResult = response.getString(context.getString(R.string.json_login_int_unsuccessful));
//
//                            int loginResultInt = Integer.parseInt(loginResult);
//                            if (loginResultInt == 0) {
//                                String title = response.getString(context.getString(R.string.json_login_title));
//                                String firstName = response.getString(context.getString(R.string.json_login_first_name));
//                                String lastName = response.getString(context.getString(R.string.json_login_last_name));
//                                ApplicationSettings.setFullName(context, title + " " + firstName + " " + lastName);
//                                ApplicationSettings.setIsLogin(context, true);
//                                ApplicationSettings.setUserName(context, userName);
//
//                                // encrypt the password before store it to the shared preferences
//                                String encrypted = encryptDecryptPassword(context, password, true);
//                                ApplicationSettings.setUserPassword(context, encrypted);
//                                loggedIn = true;
//                                onSuccess.run();
//                            } else {
//                                onFailed.run();
//                                String message = response.getString(context.getString(R.string.json_login_message));
//                                Helper.showErrorDialog(context, context.getString(R.string.error_title), message, null);
//                            }
//                        } catch (JSONException e1) {
//                            e1.printStackTrace();
//                            Helper.errorOnLogin(context, e1.getMessage());
//                        } catch (Exception e2) {
//                            e2.printStackTrace();
//                            Helper.errorOnLogin(context, e2.getMessage());
//                        }
//                    }
//                });
    }

    /**
     * @param context
     * @param userName
     * @param password
     * @param onSuccess
     * @param onFailed
     * @param onFailure
     * @throws JSONException
     * @throws UnsupportedEncodingException
     */
    public void logout(final Context context, final String userName, final String password, final Runnable onSuccess, final Runnable onFailed, final Runnable onFailure) throws JSONException, UnsupportedEncodingException {
//        String jsonLogoutURL = context.getString(R.string.json_logout_url);
//        String decrypPassword = encryptDecryptPassword(context, null, false);
//        JSONObject jsonParams = new JSONObject();
//        jsonParams.put(context.getString(R.string.user_name_json), userName);
//        jsonParams.put(context.getString(R.string.password_json), decrypPassword);
//        StringEntity entity = new StringEntity(jsonParams.toString());
//        loggedIn = true;
//        getInstance().post(context, jsonLogoutURL, entity, Constants.K_MIME_TYPE_JSON,
//                new JsonHttpResponseHandler() {
//
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, Throwable e,
//                                          JSONObject errorResponse) {
//                        super.onFailure(statusCode, headers, e, errorResponse);
//                        Log.e("Logout error", e.getMessage());
//                        onFailure.run();
//                    }
//
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                        super.onSuccess(statusCode, headers, response);
//                        String logoutResult = "";
//                        try {
//                            logoutResult = response.getString(context.getString(R.string.json_success));
//                            boolean isLogout = Boolean.parseBoolean(logoutResult);
//                            if (isLogout) {
//                                loggedIn = false;
//                                onSuccess.run();
//                            } else {
//                                onFailed.run();
//                                String message = response.getString(context.getString(R.string.json_login_message));
//                                Helper.showErrorDialog(context, context.getString(R.string.error_title), message, null);
//                            }
//                        } catch (JSONException e1) {
//                            e1.printStackTrace();
//                            Helper.showErrorWithEmailOption(context, e1.getMessage());
//                        } catch (Exception e2){
//                            e2.printStackTrace();
//                            Helper.showErrorWithEmailOption(context, e2.getMessage());
//                        }
//                    }
//                });
    }

    /**
     * @param context
     * @param password
     * @param isEncrypting
     * @return
     */
    public String encryptDecryptPassword(Context context, String password, boolean isEncrypting) {
        Encryption encryption = new Encryption();
        String key = Constants.K_PASSWORD_KEY;
        String data = password;
        String result = "";
        if (isEncrypting) {
            result = encryption.encrypt(key, data);
        } else {
            String encrypted = ApplicationSettings.getUserPassword(context);
            result = encryption.decrypt(key, encrypted);
        }

        return result;
    }

    public void sendPost(Context context, String url, StringEntity bodyParam, JsonHttpResponseHandler callback) {
        aSyncClient.post(context, url, bodyParam,Constants.K_MIME_TYPE_JSON, callback );
    }
}
