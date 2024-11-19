package com.Citronix.Citronix.service;

import com.Citronix.Citronix.model.Field;

import java.util.List;

public interface FieldService {
    Field addField(Field field);
    List<Field> getFields();
    Field updateField(int id, Field field);
    Field getField(int id);
}
