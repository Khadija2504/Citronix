package com.Citronix.Citronix.mapper;

import com.Citronix.Citronix.dto.HarvestDTO;
import com.Citronix.Citronix.model.Harvest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HarvestMapper {

    @Mapping(source = "field.id", target = "fieldId")
    HarvestDTO toDto(Harvest harvest);

    @Mapping(source = "fieldId", target = "field.id")
    Harvest toEntity(HarvestDTO harvestDTO);
}
