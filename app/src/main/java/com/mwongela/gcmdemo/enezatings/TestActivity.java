package com.mwongela.gcmdemo.enezatings;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.mwongela.gcmdemo.R;

import org.json.JSONException;

import java.util.HashMap;

/**
 * Created by mwongela on 7/17/15.
 */
public class TestActivity extends Activity {

    TextView tvTest = null;

    private JSONParser jsonParser = new JSONParser();
    private String URL = "https://api-test.enezaeducation.com";
    private ConnectionUtilities constant = new ConnectionUtilities();
    private MakeHttpRequest httpRequest = new MakeHttpRequest();
    private ConnectionDetector detector = null;
    private String jsonIgot = null;

    HashMap<String, String> listOfStrings= new HashMap<String, String>();
    HashMap<String, Boolean> listOfBools= new HashMap<String, Boolean>();
    HashMap<String, String> listOfParams= new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tvTest = (TextView) findViewById(R.id.tvTest);
        tvTest.setText("Initializing...");

        detector = new ConnectionDetector(this);

        if (detector.isConnectingToInternet() == false) {
           // detector.MaterialDialog("Oops...", "Data network unavailable", "Ok");
            Log.e("TestActivity", "Data network unavailable");
        } else {
            new NetworkTestActivity().execute();
        }

    }

    public class NetworkTestActivity extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("TestActivity", "Preparing to run the asynTask");
        }

        @Override
        protected String doInBackground(String... params) {

            try {

                //FirstLogin values
               // String URL = constant.FirstLoginURL + "/v4/user/status/username:" + "0724784992";
               // Log.i("TestActivity", "URL: "+ URL);

                //BuildURL
                listOfParams.put("username", "0724784992");
                String URL = httpRequest.buildURL(constant.FirstLoginURL2, listOfParams);
                Log.i("TestActivity", "FirstLoginActivity URL we built: "+ URL);

                listOfStrings= httpRequest.getListOfStringObjects(URL, constant.nameOfJSONObject);
                Log.i("TestActivity", "listOfStrings: "+ listOfStrings);

                listOfBools = httpRequest.getListOfBooleanObjects(URL, constant.nameOfJSONObject);
                Log.i("TestActivity", "listOfBools: " + listOfBools);



                /*
                username        = obj.get("username").toString();
                valid_username  = obj.getBoolean("valid_username");
                new_user        = obj.getBoolean("new_user");
                password        = obj.getBoolean("password");
                token           = obj.get("token").toString();
                user_id         = obj.get("user_id").toString();
                mobile_number   = obj.get("mobile_number").toString();
                error = json.getBoolean("error");
                */

                /************* SecondLogin Values
                 *
                 */

                URL = null;
                listOfParams.clear();
               // URL = constant.SecondLoginURL + "username:0724784992/password:qwertyuiop/key:" + constant.APP_KEY;

                //BuildURL
                listOfParams.put("username", "0724784992");
                listOfParams.put("password", "qwertyuiop");
                listOfParams.put("key", constant.APP_KEY);
                URL = httpRequest.buildURL(constant.SecondLoginURL2, listOfParams);
                Log.i("TestActivity", "SecondLoginActivity URL we built: " + URL);

                listOfStrings= httpRequest.getListOfStringObjects(URL, constant.nameOfJSONObject);
                Log.i("TestActivity", "listOfStrings: " + listOfStrings);

                listOfBools = httpRequest.getListOfBooleanObjects(URL, constant.nameOfJSONObject);
                Log.i("TestActivity", "listOfBools: " + listOfBools);

            /*
               // JSONObject json = jsonParser.getJSONFromUrl(URL + "/v4/user/login/username:" + username + "/password:" + password
               //         +"/key:" + Constant.APP_KEY);

               // JSONObject obj = json.getJSONObject("params");
               // username_received = obj.get("username").toString();

                String username_received = httpRequest.getStringObject(URL, "params", "username");
                Log.i("TestActivity", "username_received: "+ username_received);

              //  JSONObject obj_b = json.getJSONObject("scopes");
               // login = obj_b.getBoolean("login");

                String login = httpRequest.getStringObject(URL, "scopes", "login");
                Log.i("TestActivity", "login: "+ login);

               // error = json.getBoolean("error");
              //  warnings = json.getJSONArray("warnings");

                Boolean error = httpRequest.getBooleanObject(URL, "error");
                Log.i("TestActivity", "error: "+ error);

                JSONArray warnings = httpRequest.getJSONArrayObject(URL, "warnings");
                Log.i("TestActivity", "warnings: "+ warnings);

               // JSONObject obj_c = json.getJSONObject("result");
               //  success = obj_c.getBoolean("success");
               // message = obj_c.get("message").toString();
               // mobile_number_received = obj_c.get("mobile_number").toString();
               // user_token_received = obj_c.get("token").toString();
               // user_id_received = obj_c.get("user_id").toString();

                Boolean success = httpRequest.getBooleanObject(URL, "result", "success");
                Log.i("TestActivity", "success: "+ success);

                String message = httpRequest.getStringObject(URL, "result", "message");
                Log.i("TestActivity", "message: "+ message);

                String mobile_number_received = httpRequest.getStringObject(URL, "result", "mobile_number");
                Log.i("TestActivity", "mobile_number: "+ mobile_number_received);

                String user_token_received = httpRequest.getStringObject(URL, "result", "token");
                Log.i("TestActivity", "token: "+ user_token_received);

                String user_id_received = httpRequest.getStringObject(URL, "result", "user_id");
                Log.i("TestActivity", "user_id: "+ user_id_received);
            */

                //User Reg

                //BuildURL
                listOfParams.clear();
                listOfParams.put("username", "0724784992");
                listOfParams.put("security_question", "do you smell what's cooking");
                listOfParams.put("security_answer", "nope");
                listOfParams.put("password", "so-secure");
                listOfParams.put("name", "Mose Mwongela");
                listOfParams.put("shool_text", "Kitui High School");
                listOfParams.put("level", "Form 2");
                listOfParams.put("usertype", "Student");
                URL = httpRequest.buildURL(constant.USER_REG, listOfParams);
                Log.i("TestActivity", "UserRegistration URL we built: " + URL);

                listOfBools = httpRequest.getListOfBooleanObjects(URL, constant.nameOfJSONObject);
                Log.i("TestActivity", "listOfBools: " + listOfBools);

                listOfStrings = httpRequest.getListOfStringObjects(URL, constant.nameOfJSONObject);
                Log.i("TestActivity", "listOfStrings: " + listOfStrings);



            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                tvTest.setText("I'm done nigga");
                Log.e("TestActivity", "Setting text to the TextView");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
