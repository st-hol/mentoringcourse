package com.epam.training.sportsbetting.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class SportEvent {

    protected String title;

    protected LocalDateTime startDate;

    protected LocalDateTime endDate;

    protected List<Bet> bets;

    protected Result result;

    @Override
    public String toString() {
        return "SportEvent{" +
                "title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", bets=" + bets +
                ", result=" + result +
                '}';
    }
}
