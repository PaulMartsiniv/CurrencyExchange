package overonix.dto.mapper;

import org.springframework.stereotype.Component;
import overonix.dto.request.ThirdSourceRequestDto;
import overonix.dto.response.ThirdSourceResponseDto;
import overonix.entity.ThirdSource;

@Component
public class ThirdSourceMapper {
    public ThirdSourceResponseDto toResponseDto(ThirdSource source) {
        return ThirdSourceResponseDto.builder()
                .id(source.getId())
                .apikey(source.getApikey())
                .url(source.getUrl())
                .details(source.getDetails())
                .build();
    }

    public ThirdSource toModel(ThirdSourceRequestDto requestDto) {
        return ThirdSource.builder()
                .apikey(requestDto.getApikey())
                .url(requestDto.getUrl())
                .details(requestDto.getDetails())
                .build();
    }
}
