package overonix.dao;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import overonix.entity.CurrencyDetails;

@Repository
public interface CurrencyDetailsDao extends JpaRepository<CurrencyDetails, Long>,
        JpaSpecificationExecutor<CurrencyDetails> {
    List<CurrencyDetails> findCurrencyDetailsByDate(LocalDate data);
}
