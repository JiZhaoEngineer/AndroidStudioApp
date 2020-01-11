package com.jizhao.ji_zhao_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    private DataManager dm;
    private SQLiteDatabase db;
//    private DataManager.MySQLiteOpenHelper mySQLiteOpenHelper;
    private EditText editTextNameOrEmail;
//    private EditText editTextEmail;
    private EditText editTextPassword;
//    private Button buttonForgetPwd;

    private List<UserInfo> userInfoList;
    private UserInfo userInfo;
    private List<TeamInfo> teamInfoList;
    private TeamInfo teamInfo;
    public static MainActivity mainActivity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set background
        getWindow().setBackgroundDrawableResource(R.drawable.background1);
        //access DataManager
        dm = new DataManager(this);
//        mySQLiteOpenHelper = dm.new MySQLiteOpenHelper(this);

        mainActivity = this;
        userInfoList = new ArrayList<UserInfo>();
        teamInfoList = new ArrayList<TeamInfo>();

        Button buttonSignUp = findViewById(R.id.buttonLoginPageSignUp);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            DialogSignUp dialogSignUp = new DialogSignUp();
            dialogSignUp.show(getSupportFragmentManager(),"");
            }
        });
    }

    public void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData() {
        Cursor cursor = dm.selectAll();

        int userInfoCount = cursor.getCount();

        Log.i("info", "In ListUser Page: Number of infoTable " + userInfoCount);
        userInfoList.clear();

        if (userInfoCount > 0) {
            while (cursor.moveToNext()) {
                int _id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String phone = cursor.getString(3);
                String password = cursor.getString(4);
                String address = cursor.getString(5);
                String city = cursor.getString(6);
                String state = cursor.getString(7);
                String zip = cursor.getString(8);
                userInfo = new UserInfo(_id, name, email, phone, password, address, city, state, zip);

                System.out.println("in MainActivity loadData-> name: " + name + ", email: " + email + ", password: " + password);

                userInfoList.add(userInfo);
            }
        }
    }

    public void createNewUserInfo (UserInfo userInfo) {
        String name = userInfo.getName();
        String email = userInfo.getEmail();
        String phone = userInfo.getPhone();
        String password = userInfo.getPassword();
        String address = userInfo.getAdd();
        String city = userInfo.getCity();
        String state = userInfo.getState();
        String zip = userInfo.getZip();

        dm.insert(name, email, phone, password, address, city, state, zip);
        loadData();

        Log.i("info", "user " + name + " has been added");
    }

    public boolean signupCheck(String name) {
        return dm.nameCheck(name);
    }

    public void buttonLogin(View v) {
        int _id;
        String name, email, pwd, phone, address, city, state, zip;
        //check user use username or email login
        editTextNameOrEmail = findViewById(R.id.editTextLogInInfo);
        String nameorEmail = editTextNameOrEmail.getText().toString();
        if (nameOrEmailCheck(nameorEmail)){
            if (dm.nameCheck(nameorEmail)){
                name = nameorEmail;
                email = dm.fromNameGetEmail(nameorEmail);
            }
            else {
                email = nameorEmail;
                name = dm.fromEmailGetName(nameorEmail);
            }
            //get password
            editTextPassword = findViewById(R.id.editTextPwd);
            pwd = editTextPassword.getText().toString();
            try {
                _id = userInfo.getId();
                phone = userInfo.getPhone();
                address = userInfo.getAdd();
                city = userInfo.getCity();
                state = userInfo.getState();
                zip = userInfo.getZip();
            }
            catch (Exception e){
                _id = 0;
                phone = "";
                address = "";
                city = "Denver";
                state = "CO";
                zip = "";
            }

            System.out.println("buttonLogin in MainActivity:  _id: " + _id + ", name: " + name + ", email: " + email + ", phone: " + phone
                    + ", pwd: " + pwd + ", add: " + address + ", city: " + city);
            if (dm.loginCheck(name, email, pwd)) {
                Toast.makeText(MainActivity.this, "Log in Succeed", Toast.LENGTH_SHORT).show();
                //to listInfoPage
                Intent intent = new Intent(this, ListInfoPage.class);
                intent.putExtra("userInfo", new UserInfo(_id, name, email, phone, pwd, address, city, state, zip));
                startActivity(intent);
            }
            else {
                Toast.makeText(MainActivity.this, "Incorrect username or password.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(MainActivity.this, "Username or password not match the record.", Toast.LENGTH_SHORT).show();
        }


    }
    public void forgetPwd(View v){
        Intent intent = new Intent(this, ForgetPwd.class);
        startActivity(intent);
    }

    //check username or email entered by user match the record or not;
    public boolean nameOrEmailCheck(String nameorEmail){

        if (dm.nameCheck(nameorEmail) || dm.emailCheck(nameorEmail)){
            return true;
        }

        else {
            Toast.makeText(this, "username or email not match the record!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
