package org.i_smartway.myvg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import org.i_smartway.myvg.login_helper.DatabaseHandler;

import java.util.HashMap;

/**
 * Created by vwx on 08/24/17.
 */

public class LoginAccount extends AppCompatActivity{

    private TextView txtName, txtEmail;

    private DatabaseHandler db;
    private HashMap<String,String> user = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_account);

        //text yang ditampilkan
        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);

        db = new DatabaseHandler(getApplicationContext());
        user = db.getUserDetails();

        // Fetching user details from database
        String name = user.get("name");
        String email = user.get("email");


        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);


        // Hide Keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

}
