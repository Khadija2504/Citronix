package com.Citronix.Citronix.mapper;

import com.Citronix.Citronix.dto.TreeDTO;
import com.Citronix.Citronix.model.Tree;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TreeMapper {

    @Mapping(target = "field", ignore = true)
    Tree toEntity(TreeDTO treeDTO);

    @Mapping(target = "fieldId", source = "field.id")
    TreeDTO toDTO(Tree tree);
}
