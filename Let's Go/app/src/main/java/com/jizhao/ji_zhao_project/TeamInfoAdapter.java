package com.jizhao.ji_zhao_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeamInfoAdapter extends RecyclerView.Adapter<TeamInfoAdapter.ListItemHolder> {

    private ListInfoPage listInfoPage;
    private List<TeamInfo> teamInfoList;

    public TeamInfoAdapter (ListInfoPage listInfoPage, List<TeamInfo> teamInfoList) {
        this.listInfoPage = listInfoPage;
        this.teamInfoList = teamInfoList;
    }

    @Override
    public TeamInfoAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_info_adapter, parent, false);

        return new TeamInfoAdapter.ListItemHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListItemHolder holder, final int position) {

        final TeamInfo teamInfo = teamInfoList.get(position);
        holder.textViewName.setText(teamInfo.getName());
        holder.textViewTeam.setText(teamInfo.getTeam());
        holder.textViewLoc.setText(teamInfo.getLocation());
        holder.textViewMonth.setText(teamInfo.getYear());
        holder.textViewDay.setText(teamInfo.getMonth());
        holder.textViewYear.setText(teamInfo.getDay());
        holder.textViewHour.setText(teamInfo.getHour());
        holder.textViewMinute.setText(teamInfo.getMinute());
        holder.textViewNum.setText(teamInfo.getTeamMember());

    }

    @Override
    public int getItemCount() {
        return teamInfoList.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textViewName;
        private TextView textViewTeam;
        private TextView textViewLoc;
        private TextView textViewMonth;
        private TextView textViewDay;
        private TextView textViewYear;
        private TextView textViewHour;
        private TextView textViewMinute;
        private TextView textViewNum;

        public ListItemHolder (View view) {
            super(view);
            textViewName = view.findViewById(R.id.textViewListName);
            textViewTeam = view.findViewById(R.id.textViewListTeam);
            textViewLoc = view.findViewById(R.id.textViewListLoc);
            textViewMonth = view.findViewById(R.id.textViewListMonth);
            textViewDay = view.findViewById(R.id.textViewListDay);
            textViewYear = view.findViewById(R.id.textViewListYear);
            textViewHour = view.findViewById(R.id.textViewListHour);
            textViewMinute = view.findViewById(R.id.textViewListMinute);
            textViewNum = view.findViewById(R.id.textViewListNum);

            view.setClickable(true);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            listInfoPage.showUserInfo(getAdapterPosition());
        }
    }



}
