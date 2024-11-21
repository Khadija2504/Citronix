package com.Citronix.Citronix.mapper;

import com.Citronix.Citronix.dto.FarmDTO;
import com.Citronix.Citronix.model.Farm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FarmMapper {
    Farm toEntity(FarmDTO farmDTO);
    FarmDTO toDTO(Farm farm);
}
