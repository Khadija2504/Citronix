package com.Citronix.Citronix.service;

import com.Citronix.Citronix.model.Tree;
import com.Citronix.Citronix.model.Tree;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TreeService {
    Tree addtree(Tree tree);
    Page<Tree> getTrees(int page, int size);
    Tree updateTree(int id, Tree tree);
    Tree getTree(int id);
    boolean deleteTree(int id);
}
