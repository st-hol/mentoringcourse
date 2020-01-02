package com.epam.training.sportsbetting.domain.user;

import com.epam.training.sportsbetting.domain.Currency;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Player extends User {
    private String name;
    private Integer accountNumber;
    private BigDecimal balance;
    private Currency currency;
    private LocalDate birth;
}
