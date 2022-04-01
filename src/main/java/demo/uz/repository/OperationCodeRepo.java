package demo.uz.repository;

import demo.uz.domain.OperationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationCodeRepo extends JpaRepository<OperationCode, Long> {
}
