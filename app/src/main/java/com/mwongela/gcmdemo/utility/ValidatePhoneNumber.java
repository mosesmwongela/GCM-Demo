package com.mwongela.gcmdemo.utility;

import android.util.Log;

/**
 * Created by mwongela on 7/22/15.
 *
 * Validates phone number
 *
 * Formats accepted are
 *  [plus][country code][7][subscriber code][six digits]    example +254724784992
 *  [country code][7][subscriber code][six digits]          example 254724784992
 *  [0][7][subscriber code][six digits]                     example 0724784992
 *
 */
public class ValidatePhoneNumber {

    private String[] countryCodes = {"254"};

    private String[] SafariomProviderCodes = {"00","01","02","03","04","05","06","07","08","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"};
    private String[] AirtelProviderCodes = {"30","31","32","33","34","35","36","37","38","39","85","86","87","88","89"};
    private String[] YuProviderCodes = {"50","51","52","53","54","55","56"};
    private String[] OrangeProviderCodes = {"70","71","72","73","74","75"};

    private final String TAG = "ValidatePhoneNumber";


    public ValidatePhoneNumber(){

    }

    public boolean isPhoneNumberValid(String phone){

        //check length
        if(checkLength(phone)==false){
            return false;
        }

        //check if it is numeric
        if(isNumeric(phone)==false){
            return false;
        }

        //check length
        int length = getLength(phone);

        // +254724784992
        if(length==13){

            //first character must be a +
            if(checkForCharXatPositionY(phone, '+', 0)==false){
                return false;
            }

            //check for country code
            String countryCode = phone.substring(1,4);
            if (checkCountryCode(countryCode)==false){
                return false;
            }

            //forth char must be a 7
            if(checkForCharXatPositionY(phone, '7', 4)==false){
                return false;
            }

            //check for a valid 22, 23, 24 etc -- what do we call these? providerCodes? SubscriberCode?
            //check if it is a safaricom number
            String ServiceProviderCode = phone.substring(5,7);
            if(isSubscriberCodeFromAllowedSubscriber(ServiceProviderCode)==false){
                return false;
            }
        }

        // 254724784992
        if(length==12){

            //check for country code
            String countryCode = phone.substring(0,3);
            if(checkCountryCode(countryCode) == false) {
                return false;
            }

            //third char must be a 7
            if(checkForCharXatPositionY(phone, '7', 3)==false){
                return false;
            }

            //check for a valid 22, 23, 24 etc -- what do we call these? providerCodes? SubscriberCode?
            //check if it is a safaricom number
            String ServiceProviderCode = phone.substring(4,6);
            if(isSubscriberCodeFromAllowedSubscriber(ServiceProviderCode)==false){
                return false;
            }
        }

        // 0724784992
        if(length==10){

            //first character must be a 0
            if(checkForCharXatPositionY(phone, '0', 0)==false){
                return false;
            }

            //second char must be a 7
            if(checkForCharXatPositionY(phone, '7', 1)==false){
                return false;
            }

            //check for a valid 22, 23, 24 etc -- what do we call these? providerCodes? SubscriberCode?
            //check if it is a safaricom number
            String ServiceProviderCode = phone.substring(2,4);
            if(isSubscriberCodeFromAllowedSubscriber(ServiceProviderCode)==false){
                return false;
            }
        }

        //this phone number has made it through thick and thin
        // it should be valid
        return true;
    }

    //check for a given digit in a given position in the phone number
    //check if seven is in the said position
    public boolean checkForCharXatPositionY(String phone, char x, int y){
        if(phone.charAt(y)!=x){
            System.out.println("the char " + x + " is not in the given position: "+y);
            return false;
        }
        return true;
    }

    //get length
    public int getLength(String phone) {
        Log.i(TAG, "length is: " + phone.trim().length());
        return phone.trim().length();
    }

    //check Length
    public boolean checkLength(String phone){
        int l = getLength(phone);
        if(l==13){
            return true;
        }
        if(l==12){
            return true;
        }
        if(l==10){
            return true;
        }
        System.out.println("length " + l + " is invalid");
        return false;
    }

    //check country code
    public boolean checkCountryCode(String countryCode){
        for(int i=0; i<countryCodes.length; i++){
            if(countryCode.equalsIgnoreCase(countryCodes[i])){
                Log.i(TAG, countryCode + " country code is valid");
                return true;
            }else{
                System.out.println(countryCode + "Country code is invalid");
            }
        }
        return false;
    }

    //check if phone number is really a number
    //we allow the plus (+) sign at index 0 only
    public boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if(c=='+') {
                // this is a plus so leave it alone
                Log.i(TAG, c + " this is a plus so leave it alone");
            }else{
                if (!Character.isDigit(c)){
                    System.out.println(c + " is a not digit");
                    return false;
                }else{
                    Log.i(TAG, c + " is a digit");
                }
            }
        }
        return true;
    }

    //Check if the subscriber code is valid
    // checking through all service provider codes - or whatever they are called
    private boolean isSubscriberCodeValid(String ServiceProviderCode){
        for(int i=0; i<SafariomProviderCodes.length; i++){
            if(ServiceProviderCode.equalsIgnoreCase(SafariomProviderCodes[i])){
                Log.i(TAG, "found in safaricom provider codes");
                return true;
            }else{
                System.out.println("not found in Safaricom provider codes");
            }
        }
        for(int i=0; i<AirtelProviderCodes.length; i++){
            if(ServiceProviderCode.equalsIgnoreCase(AirtelProviderCodes[i])){
                Log.i(TAG, "found in airtel provider codes");
                return true;
            }else{
                System.out.println("not found in Airtel provider codes");
            }
        }
        for(int i=0; i<YuProviderCodes.length; i++){
            if(ServiceProviderCode.equalsIgnoreCase(YuProviderCodes[i])){
                Log.i(TAG, "found in YU provider codes");
                return true;
            }else{
                System.out.println("not found in YU provider codes");
            }
        }
        for(int i=0; i<OrangeProviderCodes.length; i++){
            if(ServiceProviderCode.equalsIgnoreCase(OrangeProviderCodes[i])){
                Log.i(TAG, "found in Orange provider codes");
                return true;
            }else{
                System.out.println("not found in Orange provider codes");
            }
        }
        return false;
    }


    //Checks if phone number if found in the allowed subscriber
    //for now we are checking for safaricom numbers
    private boolean isSubscriberCodeFromAllowedSubscriber(String ServiceProviderCode){
        for(int i=0; i<SafariomProviderCodes.length; i++){
            if(ServiceProviderCode.equalsIgnoreCase(SafariomProviderCodes[i])){
                Log.i(TAG, "found in safaricom provider codes");
                return true;
            }else{
                System.out.println("not found in safaricom provider codes");
            }
        }
        return false;
    }
}