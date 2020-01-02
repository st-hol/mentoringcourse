package com.epam.training.sportsbetting.domain;

import com.epam.training.sportsbetting.domain.user.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wager {

    private Player player;

    private OutcomeOdd outcomeOdd;

    private BigDecimal amount;

    private Currency currency;

    private LocalDateTime creationTime;

    private boolean isProcessed;

    private boolean isWinner;

    @Override
    public String toString() {
        return "Wager{" + outcomeOdd +
                ", amount=" + amount +
                ", isWinner=" + isWinner +
                '}';
    }
}
