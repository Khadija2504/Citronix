package com.Citronix.Citronix.service;

import com.Citronix.Citronix.model.Sale;

import java.util.List;

public interface SaleService {
    Sale addSale(Sale sale);
    Sale updateSale(int id, Sale sale);
    Sale getSale(int id);
    List<Sale> getAllSales();
}
