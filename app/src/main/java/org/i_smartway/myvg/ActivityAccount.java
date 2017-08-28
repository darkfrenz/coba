package org.i_smartway.myvg;

/**
 * Created by vwx on 08/16/17.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.i_smartway.myvg.login_helper.DatabaseHandler;

import java.util.HashMap;

public class ActivityAccount extends AppCompatActivity implements View.OnClickListener{

    private ImageView btnBack;
    private TextView Nama,Email,Hp,Region,Saldo;

    private DatabaseHandler db;
    private HashMap<String,String> user = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_account);

        btnBack = (ImageView) findViewById(R.id.back);

        Nama = (TextView) findViewById(R.id.FullName);
        Email = (TextView) findViewById(R.id.Email);
        Hp = (TextView) findViewById(R.id.NoHp);
        Region = (TextView) findViewById(R.id.Region);
        Saldo = (TextView) findViewById(R.id.Saldo);

        db = new DatabaseHandler(getApplicationContext());
        user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");
        String phone = user.get("phone");
        String region = user.get("region");
        String saldo = user.get("saldo");

        // Displaying the user details on the screen
        Nama.setText(name);
        Email.setText(email);
        Hp.setText(phone);
        Region.setText(region);
        Saldo.setText(saldo);


        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnBack){
            onBackPressed();
        }
    }

}
