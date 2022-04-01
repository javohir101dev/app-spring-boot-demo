package demo.uz.repository;

import demo.uz.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepo extends JpaRepository<Card, Long> {

//    public List<Card> getAll();

}
