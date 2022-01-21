package overonix.dto.mapper;

import org.springframework.stereotype.Component;
import overonix.dto.request.CurrencyExchangeRateRequestDto;
import overonix.dto.response.CurrencyExchangeRateResponseDto;
import overonix.entity.CurrencyExchangeRate;

@Component
public class CurrencyExchangeRateMapper {
    public CurrencyExchangeRateResponseDto toResponseDto(CurrencyExchangeRate exchangeRate) {
        return CurrencyExchangeRateResponseDto.builder()
                .id(exchangeRate.getId())
                .name(exchangeRate.getName())
                .rate(exchangeRate.getRate())
                .build();
    }

    public CurrencyExchangeRate toModel(CurrencyExchangeRateRequestDto requestDto) {
        return CurrencyExchangeRate.builder()
                .name(requestDto.getName())
                .rate(requestDto.getRate())
                .build();
    }
}
