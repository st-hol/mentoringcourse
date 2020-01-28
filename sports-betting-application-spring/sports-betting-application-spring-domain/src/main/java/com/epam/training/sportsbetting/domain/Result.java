package com.epam.training.sportsbetting.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Result {

    private List<Outcome> winnerOutcomes;

}
