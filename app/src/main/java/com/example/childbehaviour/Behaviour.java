package com.example.childbehaviour;

public class Behaviour {

    private String behaviourCategory;
    private String behaviourGeneralDescription;
    private int behaviourPointLimit;
    private String behaviourType;

    public Behaviour(String behaviourCategory, String behaviourGeneralDescription, int behaviourPointLimit, String behaviourType) {

        this.behaviourCategory = behaviourCategory;
        this.behaviourGeneralDescription = behaviourGeneralDescription;
        this.behaviourPointLimit = behaviourPointLimit;
        this.behaviourType = behaviourType;

    }


    public String getBehaviourCategory() {
        return behaviourCategory;
    }

    public String getBehaviourGeneralDescription() {
        return behaviourGeneralDescription;
    }

    public int getBehaviourPointLimit() {

        return behaviourPointLimit;
    }



    public String getBehaviourType() {

        return behaviourType;
    }
}
