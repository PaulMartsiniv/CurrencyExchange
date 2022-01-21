package overonix.dao;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import overonix.entity.CurrencyExchangeRate;

@Repository
public interface CurrencyExchangeRateDao extends JpaRepository<CurrencyExchangeRate, Long>,
        JpaSpecificationExecutor<CurrencyExchangeRate> {

    List<CurrencyExchangeRate> findAll();

    @Query(value = "SELECT c.name FROM CurrencyExchangeRate c")
    List<String> getAvailableCurrencyCodes();

    @Query(value = "SELECT cd.base, cd.date, cr.name, cr.rate "
            + "FROM CurrencyExchangeRate AS cr "
            + "JOIN CurrencyDetails as cd "
            + "WHERE cd.date =:data")
    List<CurrencyExchangeRate> getExchangeRatesHistory(LocalDate data);
}
