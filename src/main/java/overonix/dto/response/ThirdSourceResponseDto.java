package overonix.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import overonix.entity.CurrencyDetails;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThirdSourceResponseDto {
    Long id;
    String apikey;
    String url;
    CurrencyDetails details;
}
