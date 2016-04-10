package com.muhiptezcan.codebourse.model;

/**
 * Created by imtezcan on 7.04.2016.
 */
public class Language {
    private String name;
    private long score;

    public Language(final String name, final long score) {
        this.name = name;
        this.score = score;
    }

    public final String getName() {
        return name;
    }

    public final long getScore() {
        return score;
    }
}
