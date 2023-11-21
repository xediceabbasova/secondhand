package com.company.secondhand.advertisement.service;

import com.company.secondhand.advertisement.dto.AdvertisementDto;
import com.company.secondhand.advertisement.dto.AdvertisementDtoConverter;
import com.company.secondhand.advertisement.dto.CreateAdvertisementRequest;
import com.company.secondhand.advertisement.model.Advertisement;
import com.company.secondhand.advertisement.repository.AdvertisementRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementDtoConverter converter;

    public AdvertisementService(AdvertisementRepository advertisementRepository, AdvertisementDtoConverter converter) {
        this.advertisementRepository = advertisementRepository;
        this.converter = Mappers.getMapper(AdvertisementDtoConverter.class);
    }

    public AdvertisementDto createAdvertisement(CreateAdvertisementRequest request) {

        Advertisement advertisement = new Advertisement(request.title(),
                request.description(),
                request.price(),
                request.userId(), //TODO check if the user does exist
                request.hashtag());
        advertisementRepository.save(advertisement);
        return converter.convertToDto(advertisement);
    }
}
