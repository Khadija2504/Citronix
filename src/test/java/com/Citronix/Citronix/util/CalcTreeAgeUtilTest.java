package com.Citronix.Citronix.util;

import com.Citronix.Citronix.dto.TreeDTO;
import com.Citronix.Citronix.model.Field;
import com.Citronix.Citronix.model.Farm;
import com.Citronix.Citronix.model.Tree;
import com.Citronix.Citronix.model.enums.TreeStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalcTreeAgeUtilTest {

    @Test
    void testCalcTreeAge_ValidDate() {
        LocalDate plantingDate = LocalDate.of(2000, 1, 1);
        int age = CalcTreeAgeUtil.calcTreeAge(plantingDate);

        assertEquals(24, age, "the tree age should be 24 year");
    }

    @Test
    void testCalcTreeAge_FutureDate() {
        LocalDate plantingDate = LocalDate.of(2050, 1, 1);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> CalcTreeAgeUtil.calcTreeAge(plantingDate),
                "Expected exception for future planting date"
        );

        assertEquals("plantingDate cannot be in the future", exception.getMessage());
    }

    @Test
    void testProductivity_YoungTree() {
        Tree tree = mock(Tree.class);
        Field field = mock(Field.class);
        Farm farm = mock(Farm.class);

        when(tree.getId()).thenReturn(1);
        when(tree.getPlantingDate()).thenReturn(LocalDate.of(2023, 1, 1));
        when(tree.getField()).thenReturn(field);
        when(field.getId()).thenReturn(10);
        when(field.getArea()).thenReturn(5.0);
        when(field.getFarm()).thenReturn(farm);
        when(farm.getId()).thenReturn(100);
        when(farm.getName()).thenReturn("Mock Farm");
        when(farm.getArea()).thenReturn(50.0);
        when(farm.getCreationDate()).thenReturn(LocalDate.of(2000, 1, 1));
        when(farm.getLocation()).thenReturn("Mock Location");

        TreeDTO result = CalcTreeAgeUtil.productivity(tree);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(10, result.getFieldId());
        assertEquals(100, result.getFields().getFarm().getId());
        assertEquals(TreeStatus.YOUNG, result.getStatus());
        assertNull(result.getAlert(), "the young tree should not have an alert");
    }

    @Test
    void testProductivity_MatureTree() {
        Tree tree = mock(Tree.class);
        Field field = mock(Field.class);
        Farm farm = mock(Farm.class);

        when(tree.getId()).thenReturn(2);
        when(tree.getPlantingDate()).thenReturn(LocalDate.of(2015, 1, 1));
        when(tree.getField()).thenReturn(field);
        when(field.getId()).thenReturn(20);
        when(field.getArea()).thenReturn(8.0);
        when(field.getFarm()).thenReturn(farm);
        when(farm.getId()).thenReturn(200);
        when(farm.getName()).thenReturn("Mock Farm 2");
        when(farm.getArea()).thenReturn(80.0);
        when(farm.getCreationDate()).thenReturn(LocalDate.of(1990, 1, 1));
        when(farm.getLocation()).thenReturn("Mock Location 2");

        TreeDTO result = CalcTreeAgeUtil.productivity(tree);

        assertNotNull(result);
        assertEquals(2, result.getId());
        assertEquals(20, result.getFieldId());
        assertEquals(TreeStatus.MATURE, result.getStatus());
        assertNull(result.getAlert(), "the mature tree should not have an alert");
    }

    @Test
    void testProductivity_OldTree() {
        Tree tree = mock(Tree.class);
        Field field = mock(Field.class);
        Farm farm = mock(Farm.class);

        when(tree.getId()).thenReturn(3);
        when(tree.getPlantingDate()).thenReturn(LocalDate.of(1995, 1, 1));
        when(tree.getField()).thenReturn(field);
        when(field.getId()).thenReturn(30);
        when(field.getArea()).thenReturn(10.0);
        when(field.getFarm()).thenReturn(farm);
        when(farm.getId()).thenReturn(300);
        when(farm.getName()).thenReturn("Mock Farm 3");
        when(farm.getArea()).thenReturn(100.0);
        when(farm.getCreationDate()).thenReturn(LocalDate.of(1980, 1, 1));
        when(farm.getLocation()).thenReturn("Mock Location 3");

        TreeDTO result = CalcTreeAgeUtil.productivity(tree);

        assertNotNull(result);
        assertEquals(3, result.getId());
        assertEquals(30, result.getFieldId());
        assertEquals(TreeStatus.OLD, result.getStatus());
        assertNotNull(result.getAlert(), "the old tree should have an alert");
        assertEquals("the tree with id 3 has reached age 20 or older and is no longer productive", result.getAlert());
    }
}
