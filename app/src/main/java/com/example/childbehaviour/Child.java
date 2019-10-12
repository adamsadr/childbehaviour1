package com.example.childbehaviour;

public class Child {

    private int childID;
    private String childPreferredName;
    private String childSurname;
    private int childTotalPoints;
    private int childPointsChange;

    public Child(int childID, String childPreferredName, String childSurname, int childTotalPoints, int childPointsChange) {

        this.childID = childID;
        this.childPreferredName = childPreferredName;
        this.childSurname = childSurname;
        this.childTotalPoints = childTotalPoints;
        this.childPointsChange = childPointsChange;

    }

    public int getChildID() {

        return childID;
    }

    public String getChildPreferredName() {
        return childPreferredName;
    }

    public String getChildSurname() {
        return childSurname;
    }

    public int getChildTotalPoints() {
        return childTotalPoints;
    }

    public void setChildTotalPoints(int childTotalPoints) {
        this.childTotalPoints = childTotalPoints;
    }

    public int getChildPointsChange() {
        return childPointsChange;
    }
}