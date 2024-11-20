package com.Citronix.Citronix.service;

import com.Citronix.Citronix.model.Tree;
import com.Citronix.Citronix.model.Tree;

import java.util.List;

public interface TreeService {
    Tree addtree(Tree tree);
    List<Tree> getTrees();
    Tree updateTree(int id, Tree tree);
    Tree getTree(int id);
    boolean deleteTree(int id);
}
