package com.thuillier.guillaume.topquiz.model;

import android.support.annotation.Nullable;

public class Player {

    @Nullable
    private final String name;
    private final int score;

    public Player(@Nullable String name, int score){

        this.name = name;
        this.score = score;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString(){

        if (name != null) {
            return name + " : " + score;
        } else {
            return "";
        }
    }
}
