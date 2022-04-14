package demo.uz.repository;

import demo.uz.domain.Card;
import demo.uz.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepo extends JpaRepository<Card, Long> {

    //    Native query(SQL)
    @Query(nativeQuery = true,
            value = "select * from cards t " +
                    "where t.user_id = :userId " +
                    "and t.is_active = true " +
                    "and t.currency in (:currencies)")
    List<Card> findByUserIdAAndCurrencies(Long userId, List<String> currencies);

//    @Query(
//            value = "select t from Card t " +
//                    "where t.userId = :userId " +
//                    "and t.active = true " +
//                    "and t.currency in (:currencies)")
//    List<Card> findByUserIdAAndCurrencies(Long userId, List<Currency> currencies);

    @Query(nativeQuery = true,
            value = "select t.id from cards t " +
                    "where t.user_id = :userId " +
                    "and t.is_active = true " )

    List<Long> getAllCardIdsByUserId(Long userId);




}
