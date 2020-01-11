package com.jizhao.ji_zhao_project;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;

public class DialogCreate extends DialogFragment {

    //Declare variables to hold reference to EditText used for collecting data
    private EditText editTextCreateTeam;
    private EditText editTextCreateMonth;
    private EditText editTextCreateYear;
    private EditText editTextCreateDay;
    private EditText editTextCreateHour;
    private EditText editTextCreateMinute;
    private EditText editTextCreateLoc;
    private EditText editTextCreateNumOfMember;
    private Button buttonCreate;

    private String name;
    private String email;


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_create, null);

        //Find reference to views on the form
        editTextCreateTeam = dialogView.findViewById(R.id.editTextCreateCategory);
        editTextCreateMonth = dialogView.findViewById(R.id.editTextCreateMonth);
        editTextCreateDay = dialogView.findViewById(R.id.editTextCreateDay);
        editTextCreateYear = dialogView.findViewById(R.id.editTextCreateYear);
        editTextCreateHour = dialogView.findViewById(R.id.editTextCreateHour);
        editTextCreateMinute = dialogView.findViewById(R.id.editTextCreateMinute);
        editTextCreateLoc = dialogView.findViewById(R.id.editTextCreateLoc);
        editTextCreateNumOfMember = dialogView.findViewById(R.id.editTextCreateNumOfMember);

        buttonCreate = dialogView.findViewById(R.id.buttonCreate);

        builder.setView(dialogView).setMessage(" ");

        //Handle the Create Button for save user's info
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getArguments();
                try {
                    if (bundle != null) {
                        name = bundle.getString("name");
                        email = bundle.getString("email");
                    }
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                String team = editTextCreateTeam.getText().toString();
                String month = editTextCreateMonth.getText().toString();
                String day = editTextCreateDay.getText().toString();
                String year = editTextCreateYear.getText().toString();
                String hour = editTextCreateHour.getText().toString();
                String minute = editTextCreateMinute.getText().toString();
                String loc = editTextCreateLoc.getText().toString();
                String number = editTextCreateNumOfMember.getText().toString();

                TeamInfo teamInfo = new TeamInfo(0, name, email, team, month, day, year, hour, minute, loc, number);

                ListInfoPage callingActivity = (ListInfoPage) getActivity();
                callingActivity.createNewTeam(teamInfo);

                Log.i("info", "IN DialogSignUp: CREATED A NEW USER INFO");
                dismiss();
            }
        });
        return builder.create();
    }


}
