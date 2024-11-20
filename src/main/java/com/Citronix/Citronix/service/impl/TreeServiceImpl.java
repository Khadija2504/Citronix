package com.Citronix.Citronix.service.impl;

import com.Citronix.Citronix.model.Tree;
import com.Citronix.Citronix.repository.TreeRepository;
import com.Citronix.Citronix.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreeServiceImpl implements TreeService {
    @Autowired
    private TreeRepository treeRepository;

    @Override
    public Tree addtree(Tree tree) {
        return treeRepository.save(tree);
    }
}
