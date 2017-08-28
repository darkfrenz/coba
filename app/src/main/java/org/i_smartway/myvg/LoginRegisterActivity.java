package org.i_smartway.myvg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.i_smartway.myvg.login_helper.Functions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Akshay Raj on 6/16/2016.
 * akshay@i_smartway.org
 * www.i_smartway.org
 */

public class LoginRegisterActivity extends AppCompatActivity {
    private static final String TAG = LoginRegisterActivity.class.getSimpleName();

    private Button btnRegister, btnLinkToLogin;
    private TextInputLayout inputName, inputEmail, inputPassword;
    private ProgressDialog pDialog;

    //tambahan
    private  TextInputLayout inputUsername, inputRegion, inputPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register);

        //DEKLARASI
        inputName = (TextInputLayout) findViewById(R.id.rTextName);
        inputEmail = (TextInputLayout) findViewById(R.id.rTextEmail);
        inputPassword = (TextInputLayout) findViewById(R.id.rTextPassword);
        //tambahan
        inputUsername = (TextInputLayout) findViewById(R.id.rTextUsername);
        inputRegion = (TextInputLayout) findViewById(R.id.rTextRegion);
        inputPhone = (TextInputLayout) findViewById(R.id.rTextPhone);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Hide Keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        init();
    }

    private void init() {
        // Login button Click Event
        btnRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                // Hide Keyboard
                Functions.hideSoftKeyboard(LoginRegisterActivity.this);

                String name = inputName.getEditText().getText().toString().trim();
                String email = inputEmail.getEditText().getText().toString().trim();
                String password = inputPassword.getEditText().getText().toString().trim();

                String username = inputUsername.getEditText().getText().toString().trim();
                String region = inputRegion.getEditText().getText().toString().trim();
                String phone = inputPhone.getEditText().getText().toString().trim();


                // Check for empty data in the form
                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !username.isEmpty() && !region.isEmpty() && !phone.isEmpty()) {
                    if (Functions.isValidEmailAddress(email)) {
                        registerUser(name, email, password, username, region, phone);
                    } else {
                        Toast.makeText(getApplicationContext(), "Email is not valid!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter your details!", Toast.LENGTH_LONG).show();
                }
            }

        });

        // Link to Register Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(LoginRegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void registerUser(final String name, final String email, final String password, final String username, final String region, final String phone) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Functions.REGISTER_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response);
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        Functions logout = new Functions();
                        logout.logoutUser(getApplicationContext());

                        Bundle b = new Bundle();
                        b.putString("email", email);
                        Intent i = new Intent(LoginRegisterActivity.this, LoginEmailVerify.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtras(b);
                        startActivity(i);
                        pDialog.dismiss();
                        finish();

                    } else {
                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {



            //SIMPAN DATA KE DATABASE
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                //tambahan
                params.put("username", username);
                params.put("region", region);
                params.put("phone", phone);

                return params;
            }

        };

        // Adding request to request queue
        LoginMyApplication.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}