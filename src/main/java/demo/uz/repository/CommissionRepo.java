package demo.uz.repository;

import demo.uz.domain.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepo extends JpaRepository<Commission, Long> {

    @Query(nativeQuery = true, value = "select * from commissions t where t.operation_code = :operationCode")
    Commission byOperationCode(String operationCode);

}
