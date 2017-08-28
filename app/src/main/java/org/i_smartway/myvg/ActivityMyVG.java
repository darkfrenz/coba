package org.i_smartway.myvg;

/**
 * Created by vwx on 08/16/17.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ActivityMyVG extends AppCompatActivity implements View.OnClickListener{

    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_myvg);

        btnBack = (ImageView) findViewById(R.id.back);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnBack){
            onBackPressed();
        }
    }
}
