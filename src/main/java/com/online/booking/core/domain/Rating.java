package com.online.booking.core.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Document
public class Rating {

    @Min(0) @Max(5)
    private Integer oneStarCount;

    @Min(0) @Max(5)
    private Integer twoStarCount;

    @Min(0) @Max(5)
    private Integer threeStarCount;

    @Min(0) @Max(5)
    private Integer fourStarCount;

    @Min(0) @Max(5)
    private Integer fiveStarCount;

    public Rating(Integer oneStarCount, Integer twoStarCount, Integer threeStarCount, Integer fourStarCount, Integer fiveStarCount) {
        this.oneStarCount = oneStarCount;
        this.twoStarCount = twoStarCount;
        this.threeStarCount = threeStarCount;
        this.fourStarCount = fourStarCount;
        this.fiveStarCount = fiveStarCount;
    }

    public Integer getOneStarCount() {
        return oneStarCount;
    }

    public Integer getTwoStarCount() {
        return twoStarCount;
    }

    public Integer getThreeStarCount() {
        return threeStarCount;
    }

    public Integer getFourStarCount() {
        return fourStarCount;
    }

    public Integer getFiveStarCount() {
        return fiveStarCount;
    }
}
