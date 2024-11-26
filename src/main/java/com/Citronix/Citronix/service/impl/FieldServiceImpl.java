package com.Citronix.Citronix.service.impl;

import com.Citronix.Citronix.exception.EntityInvalidFarmException;
import com.Citronix.Citronix.exception.EntityInvalidFieldArea;
import com.Citronix.Citronix.exception.EntityNotFoundException;
import com.Citronix.Citronix.model.Farm;
import com.Citronix.Citronix.model.Field;
import com.Citronix.Citronix.repository.FieldRepository;
import com.Citronix.Citronix.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldRepository fieldRepository;

    @Override
    public Field addField(Field field) {
        Farm farm = field.getFarm();

        if (farm == null) {
            throw new EntityInvalidFarmException("Field must be associated with a valid farm");
        }

        if (field.getArea() < 0.1) {
            throw new EntityInvalidFieldArea("the field must have an area of at least 0.1 hec");
        }

        if (field.getArea() > (farm.getArea() / 2)) {
            throw new EntityInvalidFieldArea("the field must not exceed 50% of the total farm's area");
        }

        List<Field> fields = fieldRepository.findByFarm(farm);
        double totalFieldsArea = 0.0;
        double totalFields = 0;
        if (!fields.isEmpty()) {
            totalFieldsArea = fields.stream()
                    .mapToDouble(Field::getArea)
                    .sum();
            totalFields = fields.size();
        }

        if (totalFieldsArea + field.getArea() > farm.getArea()) {
            throw new IllegalArgumentException("The total area of fields exceeds the farm's area.");
        }
        if(totalFields >= 2) {
            throw new IllegalArgumentException("The total fields exceeds 10 fields.");
        }
        return fieldRepository.save(field);
    }


    @Override
    public Page<Field> getFields(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return fieldRepository.findAll(pageable);
    }

    @Override
    public Field updateField(int id, Field field) {
        Field fieldFind = getField(id);
        Farm farm = field.getFarm();

        if (farm == null) {
            throw new IllegalArgumentException("Field must be associated with a valid farm.");
        }

        if (field.getArea() < 0.1) {
            throw new IllegalArgumentException("the field must have an area of at least 0.1 hec");
        }

        if (field.getArea() > (farm.getArea() / 2)) {
            throw new IllegalArgumentException("the field must not exceed 50% of the total farm's area");
        }

        List<Field> fields = fieldRepository.findByFarm(farm);
        double totalFieldsArea = 0.0;
        if (!fields.isEmpty()) {
            totalFieldsArea = fields.stream()
                    .mapToDouble(Field::getArea)
                    .sum();
        }

        if (totalFieldsArea + field.getArea() > farm.getArea()) {
            throw new IllegalArgumentException("The total area of fields exceeds the farm's area.");
        }
        fieldFind.setArea(field.getArea());
        fieldFind.setFarm(field.getFarm());
        return fieldRepository.save(fieldFind);
    }

    @Override
    public Field getField(int id) {
        return fieldRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("field not found"));
    }
}
