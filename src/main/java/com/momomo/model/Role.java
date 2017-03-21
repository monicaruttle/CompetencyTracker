package com.momomo.model;

/**
 * Created by Monica on 2017-03-20.
 */
public enum Role {
    ADMIN, ACCESSOR, BASIC;

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
