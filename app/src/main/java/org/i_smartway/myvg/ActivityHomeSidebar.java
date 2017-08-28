package org.i_smartway.myvg;

/**
 * Created by vwx on 08/16/17.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.i_smartway.myvg.login_helper.DatabaseHandler;
import org.i_smartway.myvg.login_helper.Functions;
import org.i_smartway.myvg.login_helper.SessionManager;

import java.util.HashMap;


public class ActivityHomeSidebar extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener ,View.OnClickListener {

    private ImageView MenuVacation;
    private TextView NamaUser,NamaUser2;

    private SessionManager session;
    private DatabaseHandler db;
    private ProgressDialog pDialog;
    private HashMap<String,String> user = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        MenuVacation = (ImageView) findViewById(R.id.MenuVacation);
        MenuVacation.setOnClickListener(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        db = new DatabaseHandler(getApplicationContext());
        user = db.getUserDetails();


        NamaUser = (TextView) findViewById(R.id.namaUser);
        NamaUser2 = (TextView) findViewById(R.id.namaUser2);
//        NamaUser.setText(Config.USERNAME_SHARED_PREF);
        Intent intent = getIntent();

//        String username = intent.getStringExtra(Config.USER_NAME);
//        NamaUser.setText("Selamat Datang "+username);
        String name = user.get("name");
        NamaUser.setText("Welcome "+name);
//        NamaUser2.setText(name);

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logout();
        }
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            Intent exit = new Intent(Intent.ACTION_MAIN);
            exit.addCategory(Intent.CATEGORY_HOME);
            exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(exit);
        }

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            Intent intent = new Intent(ActivityHomeSidebar.this, ActivityAccount.class);
            ActivityHomeSidebar.this.startActivity(intent);

        } else if (id == R.id.nav_myvg) {
            Intent intent = new Intent(ActivityHomeSidebar.this, ActivityMyVG.class);
            ActivityHomeSidebar.this.startActivity(intent);

        } else if (id == R.id.nav_topup) {
            Intent intent = new Intent(ActivityHomeSidebar.this, ActivityTopUpSaldo.class);
            ActivityHomeSidebar.this.startActivity(intent);

        } else if (id == R.id.nav_logout) {
            logout();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Logout function
//    private void logout(){
//        //Creating an alert dialog to confirm logout
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        alertDialogBuilder.setMessage("Are you sure you want to logout?");
//        alertDialogBuilder.setPositiveButton("Yes",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface arg0, int arg1) {
//
//                        //Getting out sharedpreferences
//                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//                        //Getting editor
//                        SharedPreferences.Editor editor = preferences.edit();
//
//                        //Puting the value false for loggedin
//                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);
//
//                        //Putting blank value to email
//                        editor.putString(Config.USERNAME_SHARED_PREF, "");
//
//                        //Saving the sharedpreferences
//                        editor.commit();
//
//                        //Starting login activity
//                        Intent intent = new Intent(ActivityHomeSidebar.this, ActivityLogin.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                });
//
//        alertDialogBuilder.setNegativeButton("No",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface arg0, int arg1) {
//
//                    }
//                });
//
//        //Showing the alert dialog
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//
//    }


//    //MENU LOGUT TOOLBAR
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //Adding our menu_logout to toolbar
//        getMenuInflater().inflate(R.menu.menu_logout, menu);
//        return true;
//    }


    //LOG OUT PADA SIDEBAR
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {
            //calling logout method when the logout button is clicked
            logout();
        }
        return super.onOptionsItemSelected(item);
    }


    //NEW LOGOUT
    private void logout() {
        session.setLogin(false);
        // Launching the myvg activity
        Functions logout = new Functions();
        logout.logoutUser(getApplicationContext());
        Intent intent = new Intent(ActivityHomeSidebar.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }





    //MENU UTAMA
    @Override
    public void onClick(View v) {
        if(v == MenuVacation){
            Intent intent = new Intent(ActivityHomeSidebar.this, ActivityVgVacation.class);
            ActivityHomeSidebar.this.startActivity(intent);
        }
    }



}
