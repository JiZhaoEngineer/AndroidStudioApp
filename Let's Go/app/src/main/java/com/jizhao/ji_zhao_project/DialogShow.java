package com.jizhao.ji_zhao_project;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DialogShow extends DialogFragment {

    //Declare variables to hold reference to EditText used for collecting data
    private TextView textViewUserName2;
    private TextView textViewEmail2;
    private EditText editTextTeamCategory2;
    private EditText editTextHour;
    private EditText editTextMinute;
    private EditText editTextLoc2;
    private EditText editTextTeamMember;

    private Button buttonShowDelete;
    private Button buttonShowBack;

    private TeamInfo teamInfo;
    private String nameFromLoginPage = "";
    private String emailFromLoginPage = "";
    private String team_member = "";

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater infalter = getActivity().getLayoutInflater();
        final View dialogView = infalter.inflate(R.layout.dialog_show, null);
        //from ListInfoPage
        Bundle bundle = getArguments();
        try {
            if (bundle != null) {
                nameFromLoginPage = bundle.getString("name");
                emailFromLoginPage = bundle.getString("email");
                Log.i("info","in Dialog Join team_member is " + team_member);
                ListInfoPage callingActivity = (ListInfoPage) getActivity();
                Log.i("info","in Dialog Join: name is " + nameFromLoginPage + ", team_member is " + team_member + ", emailFromLogin page is " + emailFromLoginPage);
            }
        }
        catch (Exception e) {
            System.out.println("name->" + e.getMessage());
        }

        //Find reference to views on the form
        textViewUserName2 = dialogView.findViewById(R.id.textViewShowUserName2);
        textViewEmail2 = dialogView.findViewById(R.id.textViewShowEmail2);
        editTextTeamCategory2 = dialogView.findViewById(R.id.editTextShowCategory);
        editTextHour = dialogView.findViewById(R.id.editTextShowHour);
        editTextMinute = dialogView.findViewById(R.id.editTextShowMinute);
        editTextLoc2 = dialogView.findViewById(R.id.editTextShowLoc);
        editTextTeamMember = dialogView.findViewById(R.id.editTextShowNumOfMember);

        buttonShowDelete = dialogView.findViewById(R.id.buttonShowDelete);
        buttonShowBack = dialogView.findViewById(R.id.buttonShowBack);

        textViewUserName2.setText(teamInfo.getName());
        textViewEmail2.setText(teamInfo.getEmail());
        editTextTeamCategory2.setText(teamInfo.getTeam());
        editTextHour.setText(teamInfo.getHour());
        editTextMinute.setText(teamInfo.getMinute());
        editTextLoc2.setText(teamInfo.getLocation());
        editTextTeamMember.setText(teamInfo.getTeamMember());

        builder.setView(dialogView).setMessage("");

        //delete function only works when user want to delete their own info
        buttonShowDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("info", "editTextUserName2.getText().toString() is " + textViewUserName2.getText().toString() + ", name is " + nameFromLoginPage);
                if (textViewUserName2.getText().toString().equals(nameFromLoginPage)){
                    ListInfoPage callingActivity = (ListInfoPage) getActivity();
                    callingActivity.deleteTeamInfo(teamInfo);
                    Toast.makeText(getContext(), "Your information has been deleted.", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
                else {
                    Toast.makeText(getContext(), "You can only delete your own info.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //go back to activity
        buttonShowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return builder.create();
    }


    //Receive a teamInfo from Main
    public void sendSelectedUser (TeamInfo teamInfo) {
        this.teamInfo = teamInfo;
    }
}
