package overonix.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import overonix.entity.CurrencyDetails;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThirdSourceRequestDto {
    private String apikey;
    private String url;
    private CurrencyDetails details;
}
