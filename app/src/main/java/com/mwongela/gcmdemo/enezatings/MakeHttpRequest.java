package com.mwongela.gcmdemo.enezatings;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by mwongela on 7/7/15.
 */
public class MakeHttpRequest {

    private JSONParser jsonParser = new JSONParser();                                  //instance of the JSON parser class
    private JSONObject parentJsonObject = null, childJsonObject = null;               //temporary JSON Object :-)

    HashMap<String, String> listOfStrings= new HashMap<String, String>();  //Store all the strings here.
                                                                            //Using a hash map so that i can give 'string' keys to my values

    HashMap<String, Boolean> listOfBools= new HashMap<String, Boolean>();

    public MakeHttpRequest() {
        Log.i("MakeHttpRequest", "Constructing MakeHttpRequest");
    }

    /**
     * Get a child String from the parent
     *
     * @param URL url of which you get json from.
     * @param nameOfParentObject the name of the JSONArray.
     * @param nameOfChildObject the name of the object contained in the JSONArray.
     *
     * @throws JSONException - from handling the JSON obviously
     */

    public String getStringObject(String URL, String nameOfParentObject, String nameOfChildObject) throws JSONException {
        parentJsonObject = jsonParser.getJSONFromUrl(URL);
        childJsonObject = parentJsonObject.getJSONObject(nameOfParentObject);
        return childJsonObject.getString(nameOfChildObject);
    }

    public HashMap<String, String> getListOfStringObjects(String URL, String[] listOfJSONObjects) throws JSONException {

        parentJsonObject = jsonParser.getJSONFromUrl(URL);

        for(int h=0; h<listOfJSONObjects.length; h++) {
            for (int i = 0; i < listOfJSONObjects.length; i++) {
                try {
                    childJsonObject = parentJsonObject.getJSONObject(listOfJSONObjects[h]);
                    listOfStrings.put(listOfJSONObjects[i], childJsonObject.getString(listOfJSONObjects[i]));
                } catch (JSONException e) {
                    try {
                        listOfStrings.put(listOfJSONObjects[i], parentJsonObject.getString(listOfJSONObjects[i]));
                    } catch (JSONException ex) {
                        //When we reach here and we still cannot find you then you do not exist. lets log you.
                        //Log.e("MakeHttpRequest", "We cannot find "+listOfJSONObjects[i]);
                    }
                }
            }
        }
        return listOfStrings;
    }

    public HashMap<String, Boolean> getListOfBooleanObjects(String URL, String[] listOfJSONObjects) throws JSONException {

        parentJsonObject = jsonParser.getJSONFromUrl(URL);

        for(int h=0; h<listOfJSONObjects.length; h++) {
            for (int i = 0; i < listOfJSONObjects.length; i++) {
                try {
                    childJsonObject = parentJsonObject.getJSONObject(listOfJSONObjects[h]);
                    listOfBools.put(listOfJSONObjects[i], childJsonObject.getBoolean(listOfJSONObjects[i]));
                } catch (JSONException e) {
                    try {
                        listOfBools.put(listOfJSONObjects[i], parentJsonObject.getBoolean(listOfJSONObjects[i]));
                    } catch (JSONException ex) {
                        //When we reach here and we still cannot find you then you do not exist. lets log you.
                        //Log.e("MakeHttpRequest", "We cannot find "+listOfJSONObjects[i]);
                    }
                }
            }
        }
        return listOfBools;
    }

    public Boolean getBooleanObject(String URL, String nameOfParentObject, String nameOfChildObject) throws JSONException {
        parentJsonObject = jsonParser.getJSONFromUrl(URL);
        childJsonObject = parentJsonObject.getJSONObject(nameOfParentObject);
        return childJsonObject.getBoolean(nameOfChildObject);
    }

    public Boolean getBooleanObject(String URL, String nameOfParentObject) throws JSONException {
        parentJsonObject = jsonParser.getJSONFromUrl(URL);
        return parentJsonObject.getBoolean(nameOfParentObject);
    }

    public JSONArray getJSONArrayObject(String URL, String nameOfParentObject) throws JSONException {
        parentJsonObject = jsonParser.getJSONFromUrl(URL);
        return parentJsonObject.getJSONArray(nameOfParentObject);
    }

    public String buildURL(String URL, HashMap<String, String> listOfParams){

        String nameOfParam = null, argument = null;
        int numerOfParams = countOccurrences(URL, '{');

        // Get name of param, which is the key in the listOfParams
        // Get the value with this key
        // Now append the value in the URL
        for(int i=numerOfParams; i>0; i--){
            nameOfParam = getNameOfParam(i, URL);
            argument = listOfParams.get(nameOfParam);
            try {
                String s = "details:{";
                URL = appendURL(URL, nameOfParam, argument);
            } catch(Exception ex){
                    //This argument is not one of the parameters
                    Log.e("MakeHttpRequest", "Cannot find "+nameOfParam+ " in the params. Sorry!");
            }
        }

        try{
            URL = URL.replaceAll(" ", "%20"); //replace the colons
            URL = URL.replace("{", "%7B"); //replace the {
            URL = URL.replace("}", "%7D"); //replace the }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return URL;
    }

    // This method will replce the parameter with the argument
    // TAkes the name of the param and the argument and the URL
    // Will get the string to replce form its name then replce it with the argument
    public String appendURL(String URL, String nameOfParam, String argument){
        String stringToReplace =  "{"+nameOfParam+"}";
        return URL.replace(stringToReplace, argument);
    }

    /* With the nth index of { we can get the name of the param
     * First get the index of the last '/'
     * or the last '{' or ',' -- meaning this param is a child param, meaning we'll add a comma to the argument
     * the substring the URL using this indexes
    */
    public String getNameOfParam(int numerOfParams, String URL){
        int j = nthIndexOf(URL, "{", numerOfParams), k = j, l=0;
        String nameOfParam = null;

        while( (URL.charAt(k-1)!='/' && URL.charAt(k-1)!='{' && URL.charAt(k-1)!=',')){
            k--;
        }
        l=k;
        try {
            nameOfParam = URLEncoder.encode(new StringBuilder(URL.substring(l,j-1)).toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return nameOfParam;
    }

    /*
     * This method counts the numer of occurrances of '{'
     * Which gives the number of parameters in the URL
     */
    public static int countOccurrences(String haystack, char needle)
    {
        int count = 0;
        for (int i=0; i < haystack.length(); i++)
        {
            if (haystack.charAt(i) == needle)
            {
                count++;
            }
        }
        return count;
    }


    /*
     * Here we are finding the nth index of the '{'
     * if we have 3 parameters, then we start by getting the 3rd index of '{'
     * then use that value in the BuildURL
     */
    public static int nthIndexOf(String source, String sought, int n) {
        int index = source.indexOf(sought);
        if (index == -1) return -1;

        for (int i = 1; i < n; i++) {
            index = source.indexOf(sought, index + 1);
            if (index == -1) return -1;
        }
        return index;
    }
}
