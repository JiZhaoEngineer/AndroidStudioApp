package com.jizhao.ji_zhao_project;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataManager {

    private SQLiteDatabase db;
    private String userInfoTable = "userInfoTable";


    /**
     * DataManager constructor which initializes the database.
     * @param context reference to the calling activity.
     */

    public DataManager(Context context){
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public class MySQLiteOpenHelper extends SQLiteOpenHelper {
        /**
         * Constructor os used to initialize the database name, and version number
         * @param context
         */
        public MySQLiteOpenHelper (Context context){
            super(context, "basicInfoTable.db", null,1);
            Log.i("info", "1-DataManager: user info has been saved");
        }

        @Override
        public void  onCreate(SQLiteDatabase db) {
            try {
                Log.i("info", "2-DataManager: onCreateTest");

                String newTable = "create table " + userInfoTable + " ("
                        + "_id integer primary key autoincrement not null, "
                        + "name text not null, "
                        + "email text not null, "
                        + "phone text, "
                        + "password text not null, "
                        + "address text, "
                        + "city text, "
                        + "state text, "
                        + "zip text)";
                db.execSQL(newTable);
            }
            catch (SQLException e) {
                Log.i("info", "In DataManager onCreate Method");
                Log.i("info", e.getMessage());
            }
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            Log.i("SingleDBAdapter", "Upgrading database from version " + oldVersion
//                    + " to "
//                    + newVersion + ", which will destroy all old data");
//            db.execSQL("drop table if exists " + "  infoTable  ");
//            onCreate(db);
        }
    }

    /**
     * Executes select query sorted by name.
     * @return cursor to the result table.
     */
    public Cursor selectAll() {
        Cursor cursor = null;
        try {
            String query = "select * from " + userInfoTable + " order by name";
            cursor = db.rawQuery(query, null);
        }
        catch (Exception e) {
            Log.i("info", "In DataManager selectAll method");
            Log.i("info", e.getMessage());
        }
        Log.i("info", "In DataManager: Loaded data " + cursor.getCount());
        return cursor;
    }

    /**
     * Insert a new row into the database
     * @param name User name that the user set
     * @param email Email address of the user
     * @param phone Phone number of the user
     * @param password password that the user set
     * @param address Address of the user
     * @param city City of the user
     * @param state State of the user
     * @param zip Zip code of the user
     */
    public void insert(String name, String email, String phone, String password, String address, String city,
                       String state, String zip){
        try {
            String query = "insert into " + userInfoTable
                    +"(name, email, phone, password, address, city, state, zip) values"
                    + "( '" + name + "','" + email + "', '"+ phone + "', '"+ password +"', '" + address + "', '"
                    + city + "', '" + state + "', '" + zip + "')";
            db.execSQL(query);
        }
        catch (SQLException e) {
            Log.i("info", "In DataManager insert method");
            Log.i("info", e.getMessage());
        }
        Log.i("info", "IN insert: Added new " + userInfoTable +  " " + name);
    }

    //delete user info
    public void delete(int _id, String name) {

        try {
            String query = "delete from " + userInfoTable + " where _id = " + _id;
            db.execSQL(query);
        }
        catch (SQLException e) {
            Log.i ("info", "In DataManager delete method");
            Log.i ("info", e.getMessage());
        }
        Log.i ("info", "Delete number " + _id + ", Delete contact " + name);
    }


    public boolean loginCheck(String name, String email, String pwd) {
        String query = "select * from " + userInfoTable + " where (name=? or email =?) and password=?";
        Cursor cursor = db.rawQuery(query, new String[] {name, email, pwd});
        if (cursor.moveToFirst()){
            cursor.close();
            return true;
        }
        return false;
    }

    //for user who forget password
    //user can find their password by email address
    public boolean emailCheck(String email) {
        String query = "select * from " + userInfoTable + " where email = ?";
        Cursor cursor = db.rawQuery(query, new String[] {email});
        if (cursor.getCount() > 0){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    //check if name has already existed
    public boolean nameCheck(String name) {
        String query = "select * from " + userInfoTable +" where name = ?";
        Cursor cursor = db.rawQuery(query, new String[] {name});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public String fromNameGetPwd(String name, String email) {
        Cursor cursor = null;
        String query = "select password from " + userInfoTable + " where name =? or email = ? " ;
        cursor = db.rawQuery(query, new String[] {name, email});
        cursor.moveToNext();
        String password = cursor.getString(0);

        return password;
    }

    public String fromNameGetEmail(String name) {
        Cursor cursor = null;
        String query = "select email from " + userInfoTable + " where name =? " ;
        cursor = db.rawQuery(query, new String[] {name});
        cursor.moveToNext();
        String email = cursor.getString(0);

        return email;
    }

    public String fromEmailGetName(String email) {
        Cursor cursor = null;
        String query = "select name from " + userInfoTable + " where email =? " ;
        cursor = db.rawQuery(query, new String[] {email});
        cursor.moveToNext();
        String name = cursor.getString(0);

        return name;
    }

}
