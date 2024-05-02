package com.example.trivia_app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AppDatabase.USER_TABLE)
public class Score {

    @PrimaryKey(autoGenerate = true)
    private int mDBid;

    private int mUserId;


    private int score;


    public Score(int mDBid, int mUserId, int score) {
        this.mDBid = mDBid;
        this.mUserId = mUserId;
        this.score = score;
    }


    public int getmDBid() {
        return mDBid;
    }

    public void setmDBid(int mDBid) {
        this.mDBid = mDBid;
    }

    public int getmUserId() {
        return mUserId;
    }

    public void setmUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void upScore(int score){
        this.score++;
    }

    @Override
    public String toString() {
        return "Score{" +
                "mDBid=" + mDBid +
                ", mUserId=" + mUserId +
                ", score=" + score +
                '}';
    }
}
