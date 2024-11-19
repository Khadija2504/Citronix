package com.Citronix.Citronix.service.impl;

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
        return fieldRepository.save(field);
    }

    @Override
    public List<Field> getFields() {
        return fieldRepository.findAll();
    }

    @Override
    public Field updateField(int id, Field field) {
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
