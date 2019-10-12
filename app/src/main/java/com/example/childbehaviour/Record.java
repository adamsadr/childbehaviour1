package com.example.childbehaviour;

import java.util.Date;

public class Record {

    private int childID;
    private String childPreferredName;
    private String behaviourCategory;
    private String behaviourDetail;
    private int pointChange;
    private String parentName;
    private String behaviourDate;
    private String lastRecordUpdate;

    public Record(int childID, String childPreferredName, String behaviourCategory, String behaviourDetail, int pointChange, String parentName, String behaviourDate, String lastRecordUpdate) {

       this.childID = childID;

        if (childPreferredName!=null) this.childPreferredName = childPreferredName;
        this.behaviourCategory = behaviourCategory;
        this.behaviourDetail = behaviourDetail;
         this.pointChange = pointChange;
         this.parentName = parentName;
        this.behaviourDate = behaviourDate;
        this.lastRecordUpdate = lastRecordUpdate;

    }

    public int getChildID() {
        return childID;
    }

    public String getChildPreferredName() {
        return childPreferredName;
    }

    public String getBehaviourCategory() {
        return behaviourCategory;
    }

    public String getBehaviourDetail() {
        return behaviourDetail;
    }

    public int getPointChange() {
        return pointChange;
    }

    public String getParentName() {
        return parentName;
    }

    public String getBehaviourDate() {
        return behaviourDate;
    }

    public String getLastRecordUpdate() {
        return lastRecordUpdate;
    }
}
