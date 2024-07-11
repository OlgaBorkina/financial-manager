package project.rate.currency.dto;

import java.time.LocalDate;
import java.util.Map;

import lombok.Getter;


@Getter
public class RateDtoQ {
		String base;
		LocalDate date;
		Map<String, Double> rates;
	
}
