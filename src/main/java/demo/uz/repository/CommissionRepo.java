package demo.uz.repository;

import demo.uz.domain.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepo extends JpaRepository<Commission, Long> {
}
