package com.epam.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
}
