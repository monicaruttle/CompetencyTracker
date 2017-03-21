package com.momomo.model;

/**
 * Created by Monica on 2017-03-20.
 */
public enum Role {
    ADMIN(1), ACCESSOR(2), BASIC(3);

    private int level;

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
            case ACCESSOR:
                return "Accessor";
            case BASIC:
                return "Basic";
        }
        return super.toString();
    }
}
