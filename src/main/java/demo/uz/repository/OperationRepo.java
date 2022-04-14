package demo.uz.repository;

import demo.uz.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepo extends JpaRepository<Operation, Long> {

    @Query(nativeQuery = true, value = "select * from operations t where " +
            "(t.sender_id = :cardId or t.receiver_id = :cardId ) and t.operation_status = 'SUCCESS' " +
            "order by t.id desc " +
            "limit :size offset :start ")
    List<Operation> findAllByCard(Long cardId, Integer start, Integer size);

    @Query(nativeQuery = true, value = "select * from operations t where " +
            "(t.sender_id in(:cardIds) or t.receiver_id in(:cardIds) ) and t.operation_status = 'SUCCESS' " +
            "order by t.id desc " +
            "limit :size offset :start ")
    List<Operation> findAllByCards(List<Long> cardIds, Integer start, Integer size);

}
