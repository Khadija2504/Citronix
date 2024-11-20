package com.Citronix.Citronix.service.impl;

import com.Citronix.Citronix.model.Tree;
import com.Citronix.Citronix.repository.TreeRepository;
import com.Citronix.Citronix.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {
    @Autowired
    private TreeRepository treeRepository;

    @Override
    public Tree addtree(Tree tree) {
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
