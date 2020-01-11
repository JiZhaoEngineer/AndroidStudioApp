package com.jizhao.ji_zhao_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class DialogSignUp extends DialogFragment {

    //Declare variables to hold reference to EditText used for collecting data
    private EditText editTextSignupName;
    private EditText editTextSignupEmail;
    private EditText editTextSignupPhone;
    private EditText editTextSignupPwd;
    private EditText editTextSignupAdd;
    private EditText editTextSignupCity;
    private EditText editTextSignupState;
    private EditText editTextSignupZip;
    private Button buttonSignupSaveInfo;


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_sign_up, null);

        //Find reference to views on the form
        editTextSignupName = dialogView.findViewById(R.id.editTextSignupName);
        editTextSignupEmail = dialogView.findViewById(R.id.editTextSignupEmail);
        editTextSignupPhone = dialogView.findViewById(R.id.editTextSignupPhone);
        editTextSignupPwd = dialogView.findViewById(R.id.editTextSignupPwd);
        editTextSignupAdd = dialogView.findViewById(R.id.editTextSignupAdd);
        editTextSignupCity = dialogView.findViewById(R.id.editTextSignupCity);
        editTextSignupState = dialogView.findViewById(R.id.editTextSignupState);
        editTextSignupZip = dialogView.findViewById(R.id.editTextSignupZip);

        buttonSignupSaveInfo = dialogView.findViewById(R.id.buttonSignupSaveInfo);

        builder.setView(dialogView).setMessage(" ");

        //Handle the Sign up Button for save user's info
        buttonSignupSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextSignupName.getText().toString().toLowerCase();
                String email = editTextSignupEmail.getText().toString();
                String phone = editTextSignupPhone.getText().toString();
                String password = editTextSignupPwd.getText().toString();
                String address = editTextSignupAdd.getText().toString();
                String city = editTextSignupCity.getText().toString();
                String state = editTextSignupState.getText().toString();
                String zip = editTextSignupZip.getText().toString();

                MainActivity callingActivity = (MainActivity) getActivity();
                if (isNull(name) && isNull(email) && isNull(password)) {
                    if (callingActivity.signupCheck(name)) {
                        Toast.makeText(getContext(), "User name already exists. Please choose a new name.",Toast.LENGTH_SHORT).show();
                        Log.i("info", "IN SignUpActivity: failed");
                    }
                    else {
                        Toast.makeText(getContext(), "Sign up Succeed!",Toast.LENGTH_SHORT).show();
                        UserInfo userInfo = new UserInfo(0, name, email, phone, password, address, state, city, zip);
                        callingActivity.createNewUserInfo(userInfo);
                        Log.i("info", "IN DialogSignUp: CREATED A NEW USER INFO");
                        dismiss();
                    }
                }
                else {
                    Toast.makeText(getContext(), "Please enter the required part!",Toast.LENGTH_SHORT).show();
                }

            }
        });
        return builder.create();
    }

    //check if user enter the required part
    public boolean isNull(String str){
        if (str.trim().isEmpty()){
            return false;
        }
        return true;
    }
}
