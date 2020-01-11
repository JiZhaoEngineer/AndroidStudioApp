package com.jizhao.ji_zhao_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListInfoPage extends AppCompatActivity{

    private RecyclerView recyclerView;
    private TeamInfoAdapter teamInfoAdapter;

    private DataManagerForTeam dmForTeam;
    private List<TeamInfo> teamInfoList;

    private String name;
    private String email;

    private Button buttonCreate;
    private Button buttonSwitchAccount;
    private Button buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_info_page);

        getWindow().setBackgroundDrawableResource(R.drawable.main_background);

        dmForTeam = new DataManagerForTeam(this);
        teamInfoList = new ArrayList<TeamInfo>();
        teamInfoAdapter = new TeamInfoAdapter(this, teamInfoList);

        //get login username
        Intent intent = getIntent();
        UserInfo userInfo = (UserInfo) intent.getSerializableExtra("userInfo");
        name = userInfo.getName();
        email = userInfo.getEmail();
        buttonCreate = findViewById(R.id.buttonCreate);
        buttonSwitchAccount = findViewById(R.id.buttonSwitchAccount);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ListInfoPage, createB","name = " + name + ", email = " + email);
                DialogCreate dialog = new DialogCreate();
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("email", email);
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(),"");
            }
        });

        recyclerView = findViewById(R.id.recyclerView);

        teamInfoAdapter = new TeamInfoAdapter(this, teamInfoList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(teamInfoAdapter);
    }

    public void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData() {
        Cursor cursor = dmForTeam.selectAll();

        int userInfoCount = cursor.getCount();

        Log.i("info", "In ListUser Page: Number of userInfo " + userInfoCount);
        teamInfoList.clear();

        if (userInfoCount > 0) {
            while (cursor.moveToNext()) {
                int _id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String team = cursor.getString(3);
                String month = cursor.getString(4);
                String day = cursor.getString(5);
                String year = cursor.getString(6);
                String hour = cursor.getString(7);
                String minute = cursor.getString(8);
                String loc = cursor.getString(9);
                String team_member = cursor.getString(10);

                TeamInfo teamInfo = new TeamInfo(_id, name, email, team, month, day, year, hour, minute, loc, team_member);
                System.out.println("in ListInfoPage loadData-> name: " + name + ", email: " + email);

                teamInfoList.add(teamInfo);
            }
        }
        teamInfoAdapter.notifyDataSetChanged();
    }

    public void createNewTeam (TeamInfo teamInfo) {
        String name = teamInfo.getName();
        String email = teamInfo.getEmail();
        String team = teamInfo.getTeam();
        String month = teamInfo.getMonth();
        String day = teamInfo.getDay();
        String year = teamInfo.getYear();
        String hour = teamInfo.getHour();
        String minute = teamInfo.getMinute();
        String loc = teamInfo.getLocation();
        String team_member = teamInfo.getTeamMember();

        dmForTeam.insert(name, email, team,  month, day, year, hour, minute, loc, team_member);
        loadData();

        Log.i("info", "user " + name + "has been added");
    }


    public void deleteTeamInfo (TeamInfo teamInfo) {

        int _id = teamInfo.getId();
        String name = teamInfo.getName();

        dmForTeam.delete(_id, name);
        loadData();

        Log.i("info", "In ListUserInfoPage: user " + name + " has been deleted");
    }

    public void updateTeamInfo(TeamInfo teamInfo) {
        int _id = teamInfo.getId();
        String team = teamInfo.getTeam();
        String month = teamInfo.getMonth();
        String day = teamInfo.getDay();
        String year = teamInfo.getYear();
        String hour = teamInfo.getHour();
        String minute = teamInfo.getMinute();
        String loc = teamInfo.getLocation();
        String team_member = teamInfo.getTeamMember();

        dmForTeam.update(_id, team,  month, day, year, hour, minute, loc, team_member);
        loadData();
        Log.i("info", "In ListUserInfoPage: team_member " + team_member + " has been updated");

    }


    public void showUserInfo (int teamInfoToShow) {
        DialogShow dialog = new DialogShow();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("email", email);
        dialog.setArguments(bundle);
        dialog.sendSelectedUser(teamInfoList.get(teamInfoToShow));
        dialog.show(getSupportFragmentManager(), "");
    }


    //go to log in page
    public void switchAccount(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void buttonMap(View v){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void sendEmail(View v){
        Intent intent = new Intent(this, JoinTeamAndSendEmail.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}
