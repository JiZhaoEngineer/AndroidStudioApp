package com.jizhao.ji_zhao_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ForgetPwd extends AppCompatActivity {

    private EditText editTextUserNameOrEmail;
    private DataManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        dm = new DataManager(this);
    }

    public void buttonGetPwd (View v) {
        editTextUserNameOrEmail = findViewById(R.id.editTextUserNameOrEmail);
        String nameOrEmail = editTextUserNameOrEmail.getText().toString();
        Log.i("info", "In ForgetPwd: name " + nameOrEmail);

        if (dm.nameCheck(nameOrEmail) || dm.emailCheck(nameOrEmail)) {
            String password = dm.fromNameGetPwd(nameOrEmail, nameOrEmail);
            Intent outIntent = new Intent(this, MainActivity.class);
            Toast.makeText(ForgetPwd.this,"Your password is " + password , Toast.LENGTH_SHORT).show();
            startActivity(outIntent);
        }
        else {
            Toast.makeText(ForgetPwd.this,"No such name or email. Please check again!", Toast.LENGTH_SHORT).show();

        }
    }
}
