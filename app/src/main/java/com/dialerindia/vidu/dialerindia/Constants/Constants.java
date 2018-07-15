package com.dialerindia.vidu.dialerindia.Constants;

public class Constants {
    // User Agent
    public static String USERAGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21";

    //IntentEXTRAkeys
    public final String INTENT_KEY_TYPE = "TYPE";
    public final String INTENT_KEY_NOTIFICATION_CANCEL = "TYPE";
    public final String INTENT_KEY_NUMBER = "NUMBER";
    public final String INTENT_KEY_SCHEDULED_LEAD_ID = "Scheduled_id";

    //SharedPref Keys
    // Calling
    public final String PREF_AUTOMATIC_CALLING = "AutomaticCalling";
    public final String PREF_NOTIFICATION_ID = "NOTIFICATION_ID";
    public final String PREF_LAST_NUMBER = "LatestNumber";
    public final String PREF_LEAD_ID = "LatestNumberId";
    public final String PREF_SCHEDULED_NOTIFICATION_ID = "ScheduledNotificationId";
    public final String PREF_SCHEDULED_REPEAT_TIME = "REPEAT_TIME";

    // Shared Prefs
    // User Details
    public final String PREF_USER_NAME = "user_name";
    public final String PREF_USER_ID = "user_id";
    public final String PREF_USE_FULL_NAME = "user_full_name";
    public final String PREF_USER_GROUP = "user_group";
    public final String PREF_USER_SCHEDULED_CALLBACKS = "user_scheduledcallbacks";
    public final String PREF_USER_PASS = "user_pass";
    public final String PREF_USER_EMAIL = "user_email";
    public final String PREF_USER_LEVEL = "user_level";

    // LEAD STATUS
    public final int LEADS_ALL = 1;
    public final int LEADS_ANSWERED = 2;
    public final int LEADS_NOT_ANSWERED = 3;
    public final int LEADS_PENDING = 4;

    // CALLBACK TIME
    public final int MIN_15 = 0;
    public final int MIN_20 = 1;
    public final int MIN_30 = 2;
    public final int MIN_45 = 3;
    public final int HR_1 = 4;


    // URLS
    public final String BASE_URL = "http://103.228.112.114/mobiledialerapi/app-call.php";
    public final String URL_SUFFIX_LOGIN = "?function=agentlogin";
    public final String URL_SUFFIX_LEADS_DETAIL = "?function=leadsdetails";
    public final String URL_SUFFIX_POST_DISPOSTION = "?function=POST";


}
