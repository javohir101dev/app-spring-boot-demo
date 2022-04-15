package demo.uz.repository;

import demo.uz.domain.Operation;
import demo.uz.model.ReportDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

    @Query(nativeQuery = true,
            value = "select t.number as cardNumber, t1.senderAmount as senderAmount, t2.receiverAmount as ReceiverAmount " +
                    "from cards t " +
                    "         left join (select c1.number, sum(o1.sender_amount) as senderAmount " +
                    "                    from operations o1 " +
                    "                             left join cards c1 on c1.id = o1.sender_id " +
                    "                    where o1.sender_id =:cardId and o1.operation_status = 'SUCCESS' " +
                    " and o1.operation_date between :start and :end " +
                    "                    group by c1.number) t1 on t.number = t1.number " +
                    "         left join (select c2.number , sum(o2.receiver_amount) as receiverAmount " +
                    "                    from operations o2 " +
                    "                             left join cards c2 on c2.id = o2.receiver_id " +
                    "                    where o2.receiver_id =:cardId and o2.operation_status = 'SUCCESS' " +
                    " and o2.operation_date between :start  and :end " +
                    "                    group by c2.number) t2 on t.number = t2.number " +
                    "where t.id =:cardId ")
    ReportDto getReportCardMonthly(Long cardId, LocalDateTime start, LocalDateTime end);
}
