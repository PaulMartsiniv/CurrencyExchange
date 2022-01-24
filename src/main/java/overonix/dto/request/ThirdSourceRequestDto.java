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
    String apikey;
    String url;
    CurrencyDetails details;
}
