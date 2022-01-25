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
    private Long id;
    private String apikey;
    private String url;
    private CurrencyDetails details;
}
