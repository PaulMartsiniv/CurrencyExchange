package overonix.dto.response;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import overonix.entity.CurrencyExchangeRate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDetailsResponseDto {
    private Long id;
    private String base;
    private LocalDate date;
    private List<CurrencyExchangeRate> rates;
}
