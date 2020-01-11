package com.jizhao.ji_zhao_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class JoinTeamAndSendEmail extends AppCompatActivity {

    private Button buttonSend;
    private TextView textViewEmail;
    private EditText editTextTo;
    private EditText editTextSubject;
    private EditText editTextMessage;

    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_team_and_send_email);

        textViewEmail = findViewById(R.id.textViewEmail);
        editTextTo = findViewById(R.id.editTextTo);
        editTextSubject = findViewById(R.id.editTextSubject);
        editTextMessage = findViewById(R.id.editTextMessage);

        //get info from ListInfoPage about email
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        textViewEmail.setText(email);

        buttonSend = findViewById(R.id.buttonShowPwd);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
    }

    private void sendMail(){

        String recipientList = editTextTo.getText().toString();
        String[] recipients = recipientList.split(",");
        String subject = editTextSubject.getText().toString();
        String message = editTextMessage.getText().toString();

        Intent intentSend = new Intent(Intent.ACTION_SEND);
        intentSend.putExtra(Intent.EXTRA_EMAIL, recipients);
        intentSend.putExtra(Intent.EXTRA_SUBJECT, subject);
        intentSend.putExtra(Intent.EXTRA_TEXT, message);

        intentSend.setType("message/rfc822");
        startActivity(Intent.createChooser(intentSend,"Choose an email client"));

    }

}
