package com.Citronix.Citronix.mapper;

import com.Citronix.Citronix.dto.FieldDTO;
import com.Citronix.Citronix.model.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FieldMapper {

    @Mapping(target = "farm", ignore = true)
    Field toEntity(FieldDTO fieldDTO);

    @Mapping(target = "farmId", source = "farm.id")
    FieldDTO toDTO(Field field);
}
