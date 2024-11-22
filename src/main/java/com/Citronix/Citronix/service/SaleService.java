package com.Citronix.Citronix.service;

import com.Citronix.Citronix.model.Sale;

public interface SaleService {
    Sale addSale(Sale sale);
    Sale updateSale(int id, Sale sale);
    Sale getSale(int id);
}
