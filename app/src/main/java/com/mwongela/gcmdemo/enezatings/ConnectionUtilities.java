package com.mwongela.gcmdemo.enezatings;

/**
 * Created by mwongela on 7/7/15.
 */

public class ConnectionUtilities {

    public static String APP_KEY = "GDU1ITW69RO1TPAL2460LA43Z41VKCBR";
    public String ENEZA_SMS_SHORT_CODE = "20851";

    public String ENEZA_FB = "https://www.facebook.com/EnezaEducation";
    public String ENEZA_TWITTER = "https://twitter.com/enezaeducation";

    public String FirstLoginURL = "https://api-test.enezaeducation.com/"; 
    public String FirstLoginURL2 = "https://api-test.enezaeducation.com/v4/user/status/username:{username}";

    public String SecondLoginURL = "https://api-test.enezaeducation.com/v4/user/login/";
    public String SecondLoginURL2 = "https://api-test.enezaeducation.com/v4/user/login/username:{username}/password:{password}/key:{key}";

    private String ThirdLoginURL = "https://api-test.enezaeducation.com/v4/user/update/username:{username}/user_id:{user_id}/key:{key}/details:{" +
                "password:{password}"+
                "}";

    private String ForgotPasswordURL = "https://api-test.enezaeducation.com//v4/user/forgot/username:{username}/user_id:{user_id}";

    private String ResetPasswordURL = "https://api-test.enezaeducation.com/v4/user/update/username:{username}/user_id:{user_id}/details:{" +
                "password:{password}"+
                "}";

    public String USER_REG = "http://api-test.enezaeducation.com/v4/user/update/username:{username}/details:{" +
            "security_question:{security_question}," +
            "security_answer:{security_answer}," +
            "password:{password}," +
            "name:{name}," +
            "shool_text:{shool_text}," +
            "level:{level}," +
            "usertype:{usertype}" +
            "}";

    //names of JSON objects
    public String[] nameOfJSONObject = {"scopes", "login", "result", "params", "username", "token", "user_id", "mobile_number", "valid_username", "new_user", "password", "error", "success", "message"};

    //public String[] listOfJSONParentObjects = {"result", "params"};

    //pingpong url
//    private String CHAT_URL = "http://shule.enezaeducation.com/gateway/in/";
//    JSONObject jsonObject = jsonParser.getJSONFromUrl(CHAT_URL
//            + "?key=Kuber5246&from=" + username + "&text=" + answer + "&action=incoming");

    public String First = "http://shule.enezaeducation.com/gateway/in/key={key}&from={from}&text={text}&action=incoming";




    public ConnectionUtilities() {
    }
}
