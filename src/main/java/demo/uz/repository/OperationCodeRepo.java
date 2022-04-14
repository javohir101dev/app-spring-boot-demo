package demo.uz.repository;

import demo.uz.domain.OperationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationCodeRepo extends JpaRepository<OperationCode, Long> {

    @Query(nativeQuery = true, value = "select * from operation_codes t where name = :name")
    Optional<OperationCode> byName(String name);

}
