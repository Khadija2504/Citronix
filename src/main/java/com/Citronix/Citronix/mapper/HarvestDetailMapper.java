package com.Citronix.Citronix.mapper;

import com.Citronix.Citronix.dto.HarvestDetailDTO;
import com.Citronix.Citronix.model.HarvestDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HarvestDetailMapper {

    HarvestDetailMapper INSTANCE = Mappers.getMapper(HarvestDetailMapper.class);

    @Mapping(source = "harvest.id", target = "harvestId")
    @Mapping(source = "tree.id", target = "treeId")
    HarvestDetailDTO toDto(HarvestDetail harvestDetail);

    @Mapping(source = "harvestId", target = "harvest.id")
    @Mapping(source = "treeId", target = "tree.id")
    HarvestDetail toEntity(HarvestDetailDTO harvestDetailDTO);
}
