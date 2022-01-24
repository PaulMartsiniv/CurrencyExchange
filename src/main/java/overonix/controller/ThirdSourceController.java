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
import overonix.dto.mapper.ThirdSourceMapper;
import overonix.dto.response.ThirdSourceResponseDto;
import overonix.service.ThirdSourceService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/source")
public class ThirdSourceController {
    private static final Logger logger = LogManager.getLogger(ThirdSourceController.class);
    ThirdSourceService sourceService;
    ThirdSourceMapper sourceMapper;

    @GetMapping(value = "/get_all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ThirdSourceResponseDto> findAll(@RequestParam Map<String, String> params) {
        logger.info("findAll method in class ThirdSourceController was called."
                + " Params: params = {}", params);
        return sourceService.findAll(params).stream()
                .map(sourceMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
