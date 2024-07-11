package project.rate.currency.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import project.rate.currency.model.Rate;

public interface RateCurrencyRepository extends JpaRepository<Rate,LocalDateTime>{

}
