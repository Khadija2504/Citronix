package com.Citronix.Citronix.service.impl;

import com.Citronix.Citronix.exception.EntityNotFoundException;
import com.Citronix.Citronix.model.Sale;
import com.Citronix.Citronix.repository.SaleRepository;
import com.Citronix.Citronix.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Override
    public Sale addSale(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public Sale updateSale(int id, Sale sale) {
        Sale existingSale = getSale(id);
        existingSale.setSaleDate(sale.getSaleDate());
        existingSale.setCustomer(sale.getCustomer());
        existingSale.setUnitPrice(sale.getUnitPrice());
        existingSale.setHarvest(sale.getHarvest());
        return saleRepository.save(existingSale);
    }

    @Override
    public Sale getSale(int id) {
        return saleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("sale not found"));
    }

    @Override
    public Page<Sale> getAllSales(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return saleRepository.findAll(pageable);
    }
}
