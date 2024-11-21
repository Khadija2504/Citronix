package com.Citronix.Citronix.service.impl;

import com.Citronix.Citronix.model.Field;
import com.Citronix.Citronix.model.Tree;
import com.Citronix.Citronix.repository.TreeRepository;
import com.Citronix.Citronix.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {
    @Autowired
    private TreeRepository treeRepository;

    @Override
    public Tree addtree(Tree tree) {
        LocalDate plantingDate = tree.getPlantingDate();
        int plantingMonth = plantingDate.getMonthValue();

        if (plantingMonth < 3 || plantingMonth > 5) {
            throw new IllegalArgumentException("the trees should be planted between march and may");
        }

        Field field = tree.getField();
        double fieldAreaInHectares = field.getArea();
        long currentTreeCount = treeRepository.findByField(field).size();

        if ((currentTreeCount + 1) > (fieldAreaInHectares * 100)) {
            throw new IllegalArgumentException("the max trees of 100 trees per hectare is exceeded for this field");
        }
        return treeRepository.save(tree);
    }

    @Override
    public List<Tree> getTrees() {
        return treeRepository.findAll();
    }

    @Override
    public Tree updateTree(int id, Tree tree) {
        Tree treeFind = getTree(id);
        treeFind.setPlantingDate(tree.getPlantingDate());
        treeFind.setField(tree.getField());
        LocalDate plantingDate = tree.getPlantingDate();
        int plantingMonth = plantingDate.getMonthValue();
        if (plantingMonth < 3 || plantingMonth > 5) {
            throw new IllegalArgumentException("the trees should be planted between march and may");
        }
        return treeRepository.save(treeFind);
    }

    @Override
    public Tree getTree(int id) {
        return treeRepository.findById(id).orElseThrow(() -> new RuntimeException("Field not found"));
    }

    @Override
    public boolean deleteTree(int id) {
        Tree treeFind = getTree(id);
        treeRepository.delete(treeFind);
        return true;
    }
}
