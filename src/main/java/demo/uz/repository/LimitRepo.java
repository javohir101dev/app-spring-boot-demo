package demo.uz.repository;

import demo.uz.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitRepo extends JpaRepository<Limit, Long> {
}
