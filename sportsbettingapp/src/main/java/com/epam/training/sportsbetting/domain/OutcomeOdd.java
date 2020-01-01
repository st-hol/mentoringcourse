package com.epam.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OutcomeOdd {

    private int id;

    private BigDecimal value;

    private Outcome outcome;

    private LocalDateTime validFrom;

    private LocalDateTime validUntil;

    @Override
    public String toString() {
        return "OutcomeOdd = " + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutcomeOdd that = (OutcomeOdd) o;
        return id == that.id &&
                Objects.equals(value, that.value) &&
                Objects.equals(outcome, that.outcome) &&
                Objects.equals(validFrom, that.validFrom) &&
                Objects.equals(validUntil, that.validUntil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, outcome, validFrom, validUntil);
    }
}
