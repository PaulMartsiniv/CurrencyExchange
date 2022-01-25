package overonix.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(DetailsController.class);
    CurrencyDetailsService detailsService;
    CurrencyDetailsMapper detailsMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CurrencyDetailsResponseDto> findAll(@RequestParam Map<String, String> params) {
        logger.info("findAll method in class DetailsController was called."
                + " Params: params = {}", params);
        return detailsService.findAll(params).stream()
                .map(detailsMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/date-between", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CurrencyDetailsResponseDto> findAllByDateBetween(@RequestParam String from,
                                                          @RequestParam String to) {
        logger.info("between method in class DetailsController was called."
                + " Params: params = {} - {}", from, to);
        return detailsService.findAllByDateBetween(from, to).stream()
                .map(detailsMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
