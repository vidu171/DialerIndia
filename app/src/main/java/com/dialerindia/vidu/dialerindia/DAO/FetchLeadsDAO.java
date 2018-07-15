package com.dialerindia.vidu.dialerindia.DAO;

import android.os.AsyncTask;
import android.util.Log;

import com.dialerindia.vidu.dialerindia.Constants.Constants;
import com.dialerindia.vidu.dialerindia.helper.PrefsHelper;
import com.google.common.util.concurrent.SettableFuture;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.concurrent.Future;

public class FetchLeadsDAO {
    static String URL = new String();
    static final Constants constants  = new Constants();
    private static FetchLeadsDAO instance;
    public long val = 0;
    private FetchLeadsDAO() {
        URL = constants.BASE_URL +constants.URL_SUFFIX_LEADS_DETAIL;
    }

    public static FetchLeadsDAO getInstance() {
        if (instance == null) {
            instance = new FetchLeadsDAO();
        }
        return instance;
    }


    public Future<Boolean> FetchData(final String user) {
        URL+="&VD_login="+user;
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
                            .method(Connection.Method.GET)
                            .ignoreContentType(true)
                            .validateTLSCertificates(false)
                            .followRedirects(false)
                            .execute();
                    Log.w("DAO Fetch", response.body());

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
