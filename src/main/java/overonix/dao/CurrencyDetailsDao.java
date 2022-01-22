package overonix.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import overonix.entity.CurrencyDetails;

@Repository
public interface CurrencyDetailsDao extends JpaRepository<CurrencyDetails, Long>,
        JpaSpecificationExecutor<CurrencyDetails> {
}
