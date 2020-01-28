package com.epam.training.sportsbetting.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SportEvent that = (SportEvent) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
        //&&
//                Objects.equals(bets, that.bets) &&
        //               Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, startDate, endDate, bets, result);
    }
}
