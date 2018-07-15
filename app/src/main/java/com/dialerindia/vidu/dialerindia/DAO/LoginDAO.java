package com.dialerindia.vidu.dialerindia.DAO;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.dialerindia.vidu.dialerindia.Constants.Constants;
import com.dialerindia.vidu.dialerindia.helper.PrefsHelper;
import com.google.common.util.concurrent.SettableFuture;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.concurrent.Future;


public class LoginDAO {

    static String URL = new String();
    static final Constants constants  =new Constants();
    private static LoginDAO instance;
    public long val = 0;
    private LoginDAO() {
        URL = constants.BASE_URL +constants.URL_SUFFIX_LOGIN;
    }

    public static LoginDAO getInstance() {
        if (instance == null) {
            instance = new LoginDAO();
        }
        return instance;
    }


    public Future<Boolean> tryLogin(final String user, final String password, final Context context) {
        URL+="&VD_login="+user+"&VD_pass="+password;
        final SettableFuture<Boolean> LoginFuture = SettableFuture.create();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection.Response response = null;
                    response = Jsoup.connect(URL)
                            .userAgent(constants.USERAGENT)
                            .timeout(20000)
                            .data("user", user)
                            .data("password", password)
                            .method(Connection.Method.GET)
                            .ignoreContentType(true)
                            .validateTLSCertificates(false)
                            .followRedirects(false)
                            .execute();

                    Log.w("Login Dao", response.body());

                    if(response.body().contains("Invalide user id")){
                        LoginFuture.set(false);
                    }
                    else {
                        JSONObject user = new JSONObject(response.body());
                        String name = user.getString("full_name");
                        String user_name = user.getString("user");
                        String user_group = user.getString("user_group");
                        String scheduled_callbacks = user.getString("scheduled_callbacks");
                        String user_id = user.getString("user_id");
                        String pass = user.getString("pass");
                        String user_level = user.getString("user_level");
                        String user_email = user.getString("email");
                        PrefsHelper.writePrefString(context, constants.PREF_USE_FULL_NAME, name);
                        PrefsHelper.writePrefString(context, constants.PREF_USER_GROUP, user_group);
                        PrefsHelper.writePrefString(context, constants.PREF_USER_NAME, user_name);
                        PrefsHelper.writePrefString(context, constants.PREF_USER_SCHEDULED_CALLBACKS, scheduled_callbacks);
                        PrefsHelper.writePrefString(context, constants.PREF_USER_ID, user_id);
                        PrefsHelper.writePrefString(context, constants.PREF_USER_PASS, pass);
                        PrefsHelper.writePrefString(context, constants.PREF_USER_LEVEL, user_level);
                        PrefsHelper.writePrefString(context, constants.PREF_USER_EMAIL, user_email);
                        LoginFuture.set(true);
                    }


//                    String temp1 = new JSONObject(response.body()).getString("temp1");
//                    Log.w("Login DAO", response.body());
                }
                catch (Exception e){
                    e.printStackTrace();
                    LoginFuture.set(false);
                }
            }
        });
        return LoginFuture;
    }

}
