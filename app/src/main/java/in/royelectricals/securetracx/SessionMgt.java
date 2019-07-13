package in.royelectricals.securetracx;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

public class SessionMgt {

    SharedPreferences sharedPreferences;
    Editor editor;
    Context context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREFER_NAME = "AndroidSecurePref";
    // All Shared Preferences Keys
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    // Phone address (make variable public to access from outside)
    public static final String KEY_PHONE = "phone";
    // Password (make variable public to access from outside)
    public static final String KEY_PASSWORD = "password";

    //Construcor
    SessionMgt(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    //Session Start
    public void sessionStart(String email, String password){
        try {


            editor.putString(KEY_EMAIL, email);
            editor.putString(KEY_PASSWORD, password);
            editor.putBoolean(IS_USER_LOGIN, true);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            editor.commit();
        }
    }
    public void sessionStart(String email, String phone, String password){
        try {


            editor.putString(KEY_PHONE, phone);
            editor.putString(KEY_EMAIL, email);
            editor.putString(KEY_PASSWORD, password);
            editor.putBoolean(IS_USER_LOGIN, true);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            editor.commit();
        }
    }
    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     * */
    public boolean checkLogin(){
        // Check login status
        if(!this.isUserLoggedIn()){

            // user is not logged in redirect him to Login Activity
            Intent intent = new Intent(context, LoginActivity.class);

            // Closing all the Activities from stack
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            context.startActivity(intent);

            return true;
        }
        return false;
    }
    // Check for login
    public boolean isUserLoggedIn(){
        return sharedPreferences.getBoolean(IS_USER_LOGIN, false);
    }

    /**
     * Clear session details
     * */
    public void logout(){

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent intent = new Intent(context, LoginActivity.class);

        // Closing all the Activities
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);


        // Staring Login Activity
        context.startActivity(intent);
    }
    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        // user name
        user.put(KEY_NAME, sharedPreferences.getString(KEY_EMAIL, null));

        // user email id
        user.put(KEY_EMAIL, sharedPreferences.getString(KEY_PASSWORD, null));

        // return user
        return user;
    }

}
