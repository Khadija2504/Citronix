package com.Citronix.Citronix.service.impl;

import com.Citronix.Citronix.model.Farm;
import com.Citronix.Citronix.model.Field;
import com.Citronix.Citronix.repository.FieldRepository;
import com.Citronix.Citronix.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new IllegalArgumentException("Field must be associated with a valid farm.");
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
        return fieldRepository.save(field);
    }


    @Override
    public List<Field> getFields() {
        return fieldRepository.findAll();
    }

    @Override
    public Field updateField(int id, Field field) {
        Farm farm = field.getFarm();

        if (farm == null) {
            throw new IllegalArgumentException("Field must be associated with a valid farm.");
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
        Field fieldFind = getField(id);
        fieldFind.setArea(field.getArea());
        fieldFind.setFarm(field.getFarm());
        return fieldRepository.save(fieldFind);
    }

    @Override
    public Field getField(int id) {
        return fieldRepository.getReferenceById(id);
    }
}
