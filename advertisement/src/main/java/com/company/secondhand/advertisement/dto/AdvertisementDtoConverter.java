package com.company.secondhand.advertisement.dto;

import com.company.secondhand.advertisement.model.Advertisement;
import org.mapstruct.Mapper;

@Mapper
public interface AdvertisementDtoConverter {

    AdvertisementDto convertToDto(Advertisement advertisement);
    Advertisement convertToEntity(AdvertisementDto advertisementDto);
}
