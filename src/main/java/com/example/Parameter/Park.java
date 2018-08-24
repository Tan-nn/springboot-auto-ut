package com.example.Parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by tan on 03/04/2017.
 */
public class Park {

    @JsonProperty("card_id")
    @NotBlank
    private String cardId;

    @JsonProperty("start_time")
    @NotNull
    private Long startTime;

    @JsonProperty("end_time")
    @NotNull
    private Long endTime;

    public String getCardId() {
        return cardId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;

    }

}
