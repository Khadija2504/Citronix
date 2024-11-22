package com.Citronix.Citronix.mapper;

import com.Citronix.Citronix.dto.SaleDTO;
import com.Citronix.Citronix.model.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    @Mapping(source = "harvest.id", target = "harvestId")
    SaleDTO toDto(Sale sale);
    @Mapping(source = "harvestId", target = "harvest.id")
    Sale toEntity(SaleDTO saleDTO);
}
