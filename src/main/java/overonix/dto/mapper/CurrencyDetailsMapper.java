package overonix.dto.mapper;

import org.springframework.stereotype.Component;
import overonix.dto.request.CurrencyDetailsRequestDto;
import overonix.dto.response.CurrencyDetailsResponseDto;
import overonix.entity.CurrencyDetails;

@Component
public class CurrencyDetailsMapper {
    public CurrencyDetailsResponseDto toResponseDto(CurrencyDetails currencyDetails) {
        return CurrencyDetailsResponseDto.builder()
                .id(currencyDetails.getId())
                .base(currencyDetails.getBase())
                .date(currencyDetails.getDate())
                .build();
    }

    public CurrencyDetails toModel(CurrencyDetailsRequestDto requestDto) {
        return CurrencyDetails.builder()
                .base(requestDto.getBase())
                .date(requestDto.getDate())
                .build();
    }
}
