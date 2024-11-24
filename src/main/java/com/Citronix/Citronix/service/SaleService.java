package com.Citronix.Citronix.service;

import com.Citronix.Citronix.model.Sale;
import org.springframework.data.domain.Page;

public interface SaleService {
    Sale addSale(Sale sale);
    Sale updateSale(int id, Sale sale);
    Sale getSale(int id);
    Page<Sale> getAllSales(int page, int size);
}
