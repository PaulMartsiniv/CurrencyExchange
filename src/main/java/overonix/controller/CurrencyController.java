package overonix.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import overonix.dto.mapper.CurrencyExchangeRateMapper;
import overonix.dto.response.CurrencyExchangeRateResponseDto;
import overonix.service.CurrencyExchangeRateService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/currency", produces = MediaType.APPLICATION_JSON_VALUE)
public class CurrencyController {
    CurrencyExchangeRateService currencyService;
    CurrencyExchangeRateMapper currencyExchangeRateMapper;

    @GetMapping(value = "/get_all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CurrencyExchangeRateResponseDto> findAll(@RequestParam Map<String, String> params) {
        return currencyService.findAll(params).stream()
                .map(currencyExchangeRateMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/available", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAvailableCurrencyCodes() {
        return currencyService.getAvailableCurrencyCodes();
    }

    @GetMapping(value = "/get_history", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CurrencyExchangeRateResponseDto> getExchangeRatesHistory(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date.trim());
        return currencyService.getExchangeRatesHistory(localDate).stream()
                .map(currencyExchangeRateMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/createHistoryUrl", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createHistoryUrl(@RequestParam String base, @RequestParam String date_from,
                                   @RequestParam String date_to) {
        String url = "&base_currency=" + base + "&date_from=" + date_from
                + "&date_to=" + date_to;
        return url;
    }
}
