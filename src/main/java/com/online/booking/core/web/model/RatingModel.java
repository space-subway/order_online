package com.online.booking.core.web.model;

import com.online.booking.core.domain.Rating;

public class RatingModel {
    private int oneStarCount;
    private int twoStarCount;
    private int threeStarCount;
    private int fourStarCount;
    private int fiveStarCount;

    public RatingModel() {
    }

    public RatingModel(int oneStarCount, int twoStarCount, int threeStarCount, int fourStarCount, int fiveStarCount) {
        this.oneStarCount   = oneStarCount;
        this.twoStarCount   = twoStarCount;
        this.threeStarCount = threeStarCount;
        this.fourStarCount  = fourStarCount;
        this.fiveStarCount  = fiveStarCount;
    }

    public RatingModel(Rating rating) {
        this.oneStarCount   = rating.getOneStarCount();
        this.twoStarCount   = rating.getTwoStarCount();
        this.threeStarCount = rating.getThreeStarCount();
        this.fourStarCount  = rating.getFourStarCount();
        this.fiveStarCount  = rating.getFiveStarCount();
    }

    public int getOneStarCount() {
        return oneStarCount;
    }

    public void setOneStarCount(int oneStarCount) {
        this.oneStarCount = oneStarCount;
    }

    public int getTwoStarCount() {
        return twoStarCount;
    }

    public void setTwoStarCount(int twoStarCount) {
        this.twoStarCount = twoStarCount;
    }

    public int getThreeStarCount() {
        return threeStarCount;
    }

    public void setThreeStarCount(int threeStarCount) {
        this.threeStarCount = threeStarCount;
    }

    public int getFourStarCount() {
        return fourStarCount;
    }

    public void setFourStarCount(int fourStarCount) {
        this.fourStarCount = fourStarCount;
    }

    public int getFiveStarCount() {
        return fiveStarCount;
    }

    public void setFiveStarCount(int fiveStarCount) {
        this.fiveStarCount = fiveStarCount;
    }
}
