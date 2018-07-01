package com.dialerindia.vidu.dialerindia.DAO;

import android.os.AsyncTask;
import android.util.Log;

import com.dialerindia.vidu.dialerindia.Constants.Constants;
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
        URL = constants.BASE_URL_LOGIN+constants.URL_SUFFIX_LOGIN;
    }

    public static LoginDAO getInstance() {
        if (instance == null) {
            instance = new LoginDAO();
        }
        return instance;
    }


    public static Future<Boolean> tryLogin(final String user, final String password) {
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
                    String temp1 = new JSONObject(response.body()).getString("temp1");
                    Log.w("Login DAO", temp1);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return null;
    }

}
