package overonix.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import overonix.dto.mapper.CurrencyDetailsMapper;
import overonix.dto.response.CurrencyDetailsResponseDto;
import overonix.service.CurrencyDetailsService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/details")
public class DetailsController {
    CurrencyDetailsService detailsService;
    CurrencyDetailsMapper detailsMapper;

    @GetMapping(value = "/get_all_details", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CurrencyDetailsResponseDto> findAll(@RequestParam Map<String, String> params) {
        return detailsService.findAll(params).stream()
                .map(detailsMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
