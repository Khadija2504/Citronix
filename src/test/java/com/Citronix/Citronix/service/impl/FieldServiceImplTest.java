package com.Citronix.Citronix.service.impl;

import com.Citronix.Citronix.model.Farm;
import com.Citronix.Citronix.model.Field;
import com.Citronix.Citronix.repository.FieldRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class FieldServiceImplTest {

    @Mock
    private FieldRepository fieldRepository;

    @InjectMocks
    private FieldServiceImpl fieldService;

    private Farm mockFarm;
    private Field mockField;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockFarm = new Farm();
        mockFarm.setId(1);
        mockFarm.setName("Mock Farm");
        mockFarm.setArea(10.0);

        mockField = new Field();
        mockField.setId(1);
        mockField.setArea(2.0);
        mockField.setFarm(mockFarm);
    }

    @Test
    void testAddField_Valid() {
        when(fieldRepository.findByFarm(mockFarm)).thenReturn(new ArrayList<>());
        when(fieldRepository.save(mockField)).thenReturn(mockField);

        Field result = fieldService.addField(mockField);

        verify(fieldRepository, times(1)).findByFarm(mockFarm);
        verify(fieldRepository, times(1)).save(mockField);

        assertNotNull(result);
        assertEquals(mockField.getArea(), result.getArea());
    }

    @Test
    void testAddField_NoFarm() {
        mockField.setFarm(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fieldService.addField(mockField));

        assertEquals("Field must be associated with a valid farm.", exception.getMessage());
        verify(fieldRepository, never()).save(any(Field.class));
    }

    @Test
    void testAddField_AreaTooSmall() {
        mockField.setArea(0.05);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fieldService.addField(mockField));

        assertEquals("the field must have an area of at least 0.1 hec", exception.getMessage());
        verify(fieldRepository, never()).save(any(Field.class));
    }

    @Test
    void testAddField_ExceedsFarmArea() {
        List<Field> existingFields = new ArrayList<>();
        existingFields.add(new Field(5.0, mockFarm));
        existingFields.add(new Field(4.0, mockFarm));

        when(fieldRepository.findByFarm(mockFarm)).thenReturn(existingFields);

        mockField.setArea(2.0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fieldService.addField(mockField));

        assertEquals("The total fields exceeds 10 fields.", exception.getMessage());
        verify(fieldRepository, times(1)).findByFarm(mockFarm);
    }

    @Test
    void testGetFields() {
        List<Field> mockFields = List.of(mockField, new Field(3.0, mockFarm));
        when(fieldRepository.findAll()).thenReturn(mockFields);

        List<Field> result = fieldService.getFields();

        verify(fieldRepository, times(1)).findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testUpdateField_Valid() {
        Field updatedField = new Field();
        updatedField.setArea(3.0);
        updatedField.setFarm(mockFarm);

        when(fieldRepository.getReferenceById(1)).thenReturn(mockField);
        when(fieldRepository.findByFarm(mockFarm)).thenReturn(new ArrayList<>());
        when(fieldRepository.save(mockField)).thenReturn(mockField);

        Field result = fieldService.updateField(1, updatedField);

        verify(fieldRepository, times(1)).getReferenceById(1);
        verify(fieldRepository, times(1)).findByFarm(mockFarm);
        verify(fieldRepository, times(1)).save(mockField);

        assertNotNull(result);
        assertEquals(3.0, result.getArea());
    }

    @Test
    void testGetField() {
        when(fieldRepository.getReferenceById(1)).thenReturn(mockField);

        Field result = fieldService.getField(1);

        verify(fieldRepository, times(1)).getReferenceById(1);

        assertNotNull(result);
        assertEquals(mockField.getId(), result.getId());
    }
}

