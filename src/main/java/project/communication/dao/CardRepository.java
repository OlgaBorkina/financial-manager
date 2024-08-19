package project.communication.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import project.communication.model.Card;
import java.math.BigInteger;



public interface CardRepository extends JpaRepository<Card, BigInteger> {
	Optional<Card> findByCardNumber(BigInteger bigInteger);
	

}
