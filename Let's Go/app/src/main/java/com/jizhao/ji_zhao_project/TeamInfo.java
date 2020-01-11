package com.jizhao.ji_zhao_project;

public class TeamInfo {
    //Fields for TeamInfo Class
    private int _id;
    private String name;
    private String email;
    private String team;
    private String month;
    private String day;
    private String year;
    private String hour;
    private String minute;
    private String loc;
    private String team_member;
    /**
     * Constructor for teamInfo class
     * @param idIn
     * @param nameIn
     * @param emailIn
     * @param teamIn
     * @param monthIn
     * @param dayIn
     * @param hourIn
     * @param minuteIn
     * @param locIn
     * @param team_memberIn
     */
    //need constructor
    public TeamInfo(int idIn, String nameIn, String emailIn, String teamIn, String monthIn, String dayIn,
                    String yearIn, String hourIn, String minuteIn, String locIn, String team_memberIn) {
        this._id = idIn;
        this.name = nameIn;
        this.email = emailIn;
        this.team = teamIn;
        this.month = monthIn;
        this.day = dayIn;
        this.year = yearIn;
        this.hour = hourIn;
        this.minute = minuteIn;
        this.loc = locIn;
        this.team_member = team_memberIn;
    }

    //set/get value to all variables
    public void setId (int newId) {
        this._id = newId;
    }
    public int getId() {
        return _id;
    }

    public void setName (String newName) {
        this.name = newName;
    }
    public String getName() {
        return name;
    }

    public void setEmail (String newEmail) {
        this.email = newEmail;
    }
    public String getEmail() {
        return email;
    }

    public void setTeam (String newTeam) {
        this.team = newTeam; }
    public String getTeam() {
        return team;
    }

    public void setMonth (String newMonth) {
        this.month = newMonth;
    }
    public String getMonth() {
        return month;
    }

    public void setDay (String newDay) {
        this.day = newDay;
    }
    public String getDay() {
        return day;
    }

    public void setYear (String newYear) {
        this.year = newYear;
    }
    public String getYear() {
        return year;
    }

    public void setHour (String newHour) {
        this.hour = newHour;
    }
    public String getHour() {
        return hour;
    }

    public void setMinute (String newMinute) {
        this.minute = newMinute;
    }
    public String getMinute() {
        return minute;
    }

    public void setTeamMember (String newTeamMember) {
        this.team_member = newTeamMember;
    }
    public String getTeamMember() {
        return team_member;
    }

    public void setLocation (String newLocation) {
        this.loc = newLocation;
    }
    public String getLocation() {
        return loc;
    }

}
