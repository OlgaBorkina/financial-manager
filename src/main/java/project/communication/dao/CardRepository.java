package project.communication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import project.communication.model.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {

}
