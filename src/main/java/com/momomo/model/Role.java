package com.momomo.model;

/**
 * Created by Monica on 2017-03-20.
 */
public enum Role {
    ADMIN(3), ASSESSOR(2), BASIC(1);

    private final int level;

    Role(int level) {
        this.level = level;
    }

    public int getNumVal() {
        return level;
    }

    @Override
    public String toString(){
        switch(this){
            case ADMIN:
                return "Administrator";
            case ASSESSOR:
                return "Assessor";
            case BASIC:
                return "Basic";
        }
        return super.toString();
    }
}
