package org.i_smartway.myvg.login_helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

/**
 * Created by Akshay Raj on 13-03-2016.
 * Snow Corporation Inc.
 * www.i_smartway.org
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "AndroidLogin";

    // Login table name
    private static final String TABLE_LOGIN = "myvg";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_UID = "uid";

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_CREATED_AT = "created_at";
    // tambahan
    private static final String KEY_USERNAME = "username";
    private static final String KEY_REGION = "region";
    private static final String KEY_PHONE = "phone";

    private static final String KEY_SALDO = "saldo";
    private static final String KEY_IMAGE_URL = "profileimage";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Table Create Statements
    private static final String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_UID + " TEXT, "
            + KEY_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT UNIQUE, "
            + KEY_CREATED_AT + " TEXT, "
            + KEY_USERNAME + " TEXT, "
            + KEY_REGION + " TEXT, "
            + KEY_PHONE + " TEXT, "
            + KEY_SALDO + " TEXT, "
            + KEY_IMAGE_URL + " TEXT "
            + ")";

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LOGIN_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String uid, String name, String email, String created_at, String username, String region, String phone, String saldo, String profileimage) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_UID, uid); // uid
        values.put(KEY_NAME, name); // FirstName
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_CREATED_AT, created_at); // Created At
        values.put(KEY_USERNAME, username); // username
        values.put(KEY_REGION, region); // region
        values.put(KEY_PHONE, phone); // phone
        values.put(KEY_SALDO, saldo); // saldo
        values.put(KEY_IMAGE_URL, profileimage); // image profile

        // Inserting Row
        db.insert(TABLE_LOGIN, null, values);
        db.close(); // Closing database connection
    }

    /**
     * Storing user details in database
     * *
    public void updateProfile(String fname, String lname, String email, String mobile, String aclass,
                              String school, String profile_pic, String uid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues updateValues = new ContentValues();
        updateValues.put(KEY_FIRSTNAME, fname); // FirstName
        updateValues.put(KEY_LASTNAME, lname); // LastName
        updateValues.put(KEY_EMAIL, email); // Email
        updateValues.put(KEY_MOBILE, mobile); // Mobile Number
        updateValues.put(KEY_CLASS, aclass); // Class
        updateValues.put(KEY_SCHOOL, school); // School
        updateValues.put(KEY_PROFILE_PIC, profile_pic);

        db.update(TABLE_LOGIN, updateValues, KEY_UID + "=?", new String[] { String.valueOf(uid) });
        db.close();
    }


     /**
     * Update DATA
     * */
     public void updateSaldo(String saldo, String uid) {
     SQLiteDatabase db = this.getWritableDatabase();
     ContentValues updateValues = new ContentValues();
     updateValues.put(KEY_SALDO, saldo); // FirstName

     db.update(TABLE_LOGIN, updateValues, KEY_UID + "=?", new String[] { String.valueOf(uid) });
     db.close();
     }




    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String,String> user = new HashMap<String,String>();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            user.put("uid", cursor.getString(1));
            user.put("name", cursor.getString(2));
            user.put("email", cursor.getString(3));
            user.put("created_at", cursor.getString(4));
            user.put("username", cursor.getString(5));
            user.put("region", cursor.getString(6));
            user.put("phone", cursor.getString(7));
            user.put("saldo", cursor.getString(8));
            user.put("profileimage", cursor.getString(9));


        }
        cursor.close();
        db.close();
        // return user
        return user;
    }

    /**
     * Getting user myvg status
     * return true if rows are there in table
     * */
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();

        // return row count
        return rowCount;
    }

    /**
     * Re crate database
     * Delete all tables and create them again
     * */
    public void resetTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_LOGIN, null, null);
        db.close();
    }

}
