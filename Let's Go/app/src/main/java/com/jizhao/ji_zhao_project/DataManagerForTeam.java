package com.jizhao.ji_zhao_project;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataManagerForTeam {

    private SQLiteDatabase db;
    private String team_table = "teamInfoTable";

    /**
     * DataManagerForTeam constructor which initializes the database.
     * @param context reference to the calling activity.
     */

    public DataManagerForTeam(Context context){
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public class MySQLiteOpenHelper extends SQLiteOpenHelper {
        /**
         * Constructor os used to initialize the database name, and version number
         * @param context
         */
        public MySQLiteOpenHelper (Context context){
            super(context, "teamInfoTable.db", null,1);
            Log.i("info", "1-DataManager: user info has been saved");
        }

        @Override
        public void  onCreate(SQLiteDatabase db) {
            try {
                Log.i("info", "2-DataManager: onCreateTest");

                String newTable = "create table " + team_table + " ("
                        + "_id integer primary key autoincrement not null, "
                        + "name text not null, "
                        + "email text not null, "
                        + "team text not null, "
                        + "month text not null, "
                        + "day text not null, "
                        + "year text not null, "
                        + "hour text not null, "
                        + "minute text not null, "
                        + "loc text not null, "
                        + "team_member text not null)";
                db.execSQL(newTable);
            }
            catch (SQLException e) {
                Log.i("info", "In DataManager onCreate Method");
                Log.i("info", e.getMessage());
            }
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    /**
     * Executes select query sorted by name.
     * @return cursor to the result table.
     */
    public Cursor selectAll() {
        Cursor cursor = null;
        try {
            String query = "select * from " + team_table + " order by name";
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
     * @param team Team category
     * @param month User set month
     * @param day User set day
     * @param year User set year
     * @param hour User set hour
     * @param minute User set minute
     * @param loc User set location
     * @param team_member User set how many people they need in their team
     */
    public void insert(String name, String email, String team, String month, String day, String year,
                       String hour, String minute, String loc, String team_member) {
        try {
            String query = "insert into " + team_table
                    +"(name, email, team, month, day, year, hour, minute, loc, team_member) values"
                    + "( '" + name + "','" + email + "', '" + team + "', '" + month + "','" + day + "', '"
                    + year + "','" + hour + "', '" + minute + "','"+ loc +"', '" + team_member + "' )";
            db.execSQL(query);
        }
        catch (SQLException e) {
            Log.i("info", "In DataManager insert method");
            Log.i("info", e.getMessage());
        }
        Log.i("info", "Added new " + team_table + name);
    }

    //delete user info
    public void delete(int _id, String name) {

        try {
            String query = "delete from " + team_table + " where _id = " + _id;
            db.execSQL(query);
        }
        catch (SQLException e) {
            Log.i ("info", "In DataManager delete method");
            Log.i ("info", e.getMessage());
        }
        Log.i ("info", "Delete number " + _id + ", Delete contact " + name);
    }


    //update user info
    public void update (int _id, String team, String month, String day, String year,
                        String hour, String minute, String loc, String team_member) {
        try {
            String query = "update " + team_table + " set team = '" + team + "', month = '" + month + "', day = '" + day
                    + "', year = '" + year + "', hour = '" + hour + "', minute = '" + minute
                    +"', loc = '" + loc + "', team_member = '" + team_member + "' where _id = " + _id;
            db.execSQL(query);
        }
        catch (SQLException e) {
            Log.i ("info", "In DataManager edit method");
            Log.i ("info", e.getMessage());
        }
        Log.i ("info",  "Update " + team_table +  "  " + _id);
    }
}
