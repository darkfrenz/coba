package org.i_smartway.myvg;

import android.os.Bundle;
import android.app.Activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener{



    public static final String PASSWORD = "PASSWORD";

    private static final String LOGIN_URL = "http://xviarch.hol.es/myvg1/login.php";

    private EditText editTextUserName;
    private EditText editTextPassword;

    private Button buttonLogin;
    private TextView buttonGoToRegister;

    //double klik exit
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    //boolean variable to check user is logged in or not
    //initially it is false
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_login);

        editTextUserName = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);

        buttonLogin = (Button) findViewById(R.id.buttonUserLogin);
        buttonGoToRegister = (TextView) findViewById(R.id.buttonGoToRegister);

        buttonLogin.setOnClickListener(this);
        buttonGoToRegister.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);


        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(ActivityLogin.this, ActivityHomeSidebar.class);
            startActivity(intent);
        }
    }


//    private void login(){
//        String username = editTextUserName.getText().toString().trim();
//        String password = editTextPassword.getText().toString().trim();
//        userLogin(username,password);
//    }
//
//    private void userLogin(final String username, final String password){
//        class UserLoginClass extends AsyncTask<String,Void,String>{
//            ProgressDialog loading;
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(ActivityLogin.this,"Please Wait",null,true,true);
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                loading.dismiss();
//                if(s.equalsIgnoreCase("success")){
//                    Intent intent = new Intent(ActivityLogin.this,ActivityHomeMenu.class);
//                    intent.putExtra(USER_NAME,username);
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(ActivityLogin.this,s,Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            protected String doInBackground(String... params) {
//                HashMap<String,String> data = new HashMap<>();
//                data.put("username",params[0]);
//                data.put("password",params[1]);
//
//                RegisterUserClass ruc = new RegisterUserClass();
//
//                String result = ruc.sendPostRequest(LOGIN_URL,data);
//
//                return result;
//            }
//        }
//        UserLoginClass ulc = new UserLoginClass();
//        ulc.execute(username,password);
//    }

    /////////////////////////////////////////////

    private void login(){
        //Getting values from edit texts
        final String username = editTextUserName.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if(response.equalsIgnoreCase(Config.LOGIN_SUCCESS)){
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = ActivityLogin.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Config.USERNAME_SHARED_PREF, username);

                            //Saving values to editor
                            editor.commit();

                            //Starting profile activity
                            Intent intent = new Intent(ActivityLogin.this, ActivityHomeSidebar.class);
                            intent.putExtra(Config.USER_NAME,username);
                            startActivity(intent);
                        }else{
                            //If the server response is not success
                            //Displaying an error message on toast
                            Toast.makeText(ActivityLogin.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_USERNAME, username);
                params.put(Config.KEY_PASSWORD, password);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    ////////////////////////////////////////////////////////////////


    @Override
    public void onClick(View v) {
        if(v == buttonLogin){
            login();
        }
        else if(v == buttonGoToRegister){
            Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
            ActivityLogin.this.startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        Intent exit = new Intent(Intent.ACTION_MAIN);
        exit.addCategory(Intent.CATEGORY_HOME);
        exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(exit);
    }
}