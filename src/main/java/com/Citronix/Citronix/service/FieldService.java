package com.Citronix.Citronix.service;

import com.Citronix.Citronix.model.Field;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FieldService {
    Field addField(Field field);
    Page<Field> getFields(int page, int size);
    Field updateField(int id, Field field);
    Field getField(int id);
}
