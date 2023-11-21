package com.company.secondhand.advertisement.dto;

import com.company.secondhand.advertisement.model.Advertisement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("Advertisement And AdvertisementDTO Converter Test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdvertisementDtoConverterTest {

    private AdvertisementDtoConverter converter;

    @BeforeAll
    public void setUp() {
        converter = Mappers.getMapper(AdvertisementDtoConverter.class);
    }

    @ParameterizedTest(name = "When input is: {0} " +
            "Then expected output should be: {1}")
    @MethodSource("testDataProvider")
    public void testConvertToDto(final Advertisement advertisement,
                                 final AdvertisementDto expectedAdvertisementDto) {

        final var advertisementDto = converter.convertToDto(advertisement);

        assertEquals(expectedAdvertisementDto, advertisementDto);

    }

    @ParameterizedTest(name = "When input is: {0} " +
            "Then expected output should be: {1}")
    @MethodSource("testDataProviderV2")
    public void testConvertToEntity(final AdvertisementDto advertisementDto,
                                    final Advertisement expectedAdvertisement) {

        final var advertisement = converter.convertToEntity(advertisementDto);

        assertEquals(expectedAdvertisement, advertisement);

    }

    static Stream<Arguments> testDataProviderV2() {
        final var date = LocalDateTime.now();
        return Stream.of(
                arguments(new AdvertisementDto("ad-id",
                                "title",
                                "description1",
                                BigDecimal.valueOf(123),
                                date,
                                date,
                                987L,
                                Set.of("hashtag1", "hashtag2")),
                        new Advertisement("ad-id",
                                "title",
                                "description1",
                                BigDecimal.valueOf(123),
                                date,
                                date,
                                987L,
                                Set.of("hashtag1", "hashtag2"))),
                arguments(new AdvertisementDto("ad-id",
                                "title2",
                                "description1",
                                BigDecimal.valueOf(123),
                                date,
                                date,
                                987L,
                                Set.of("hashtag1", "hashtag2")),
                        new Advertisement("ad-id",
                                "title2",
                                "description1",
                                BigDecimal.valueOf(123),
                                date,
                                date,
                                987L,
                                Set.of("hashtag1", "hashtag2")))
        );

    }

    static Stream<Arguments> testDataProvider() {
        final var date = LocalDateTime.now();
        return Stream.of(
                arguments(new Advertisement("ad-id",
                                "title",
                                "description1",
                                BigDecimal.valueOf(123),
                                date,
                                date,
                                987L,
                                Set.of("hashtag1", "hashtag2")),
                        new AdvertisementDto("ad-id",
                                "title",
                                "description1",
                                BigDecimal.valueOf(123),
                                date,
                                date,
                                987L,
                                Set.of("hashtag1", "hashtag2"))),
                arguments(new Advertisement("ad-id",
                                "title2",
                                "description1",
                                BigDecimal.valueOf(123),
                                date,
                                date,
                                987L,
                                Set.of("hashtag1", "hashtag2")),
                        new AdvertisementDto("ad-id",
                                "title2",
                                "description1",
                                BigDecimal.valueOf(123),
                                date,
                                date,
                                987L,
                                Set.of("hashtag1", "hashtag2")))
        );

    }

}
