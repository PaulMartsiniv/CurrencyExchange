package overonix.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import overonix.entity.ThirdSource;

@Repository
public interface ThirdSourceDao extends JpaRepository<ThirdSource, Long> {
}
