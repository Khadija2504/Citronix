package com.Citronix.Citronix.controller;

import com.Citronix.Citronix.dto.SaleDTO;
import com.Citronix.Citronix.mapper.SaleMapper;
import com.Citronix.Citronix.model.Sale;
import com.Citronix.Citronix.service.HarvestService;
import com.Citronix.Citronix.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/sale")
@RestController
public class SaleController {
    @Autowired
    private SaleService saleService;
    @Autowired
    private SaleMapper saleMapper;
    @Autowired
    private HarvestService harvestService;

    @PostMapping("/recordSale")
    public ResponseEntity<?> recordSale(@Valid @RequestBody SaleDTO saleDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        Sale sale = saleMapper.toEntity(saleDTO);
        sale.calculateRevenue(harvestService.getHarvestById(saleDTO.getHarvestId()).getTotalQuantity());
        Sale savedSale = saleService.addSale(sale);
        SaleDTO savedSaleDTO = saleMapper.toDto(savedSale);
        return ResponseEntity.status(HttpStatus.OK).body(savedSaleDTO);
    }

    @PutMapping("/{id}/updateSale")
    public ResponseEntity<?> updateSale(@Valid @RequestBody SaleDTO saleDTO, BindingResult result, @PathVariable int id) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        Sale sale = saleMapper.toEntity(saleDTO);
        sale.calculateRevenue(harvestService.getHarvestById(saleDTO.getHarvestId()).getTotalQuantity());
        Sale updatedSale = saleService.updateSale(id, sale);
        SaleDTO updatedSaleDTO = saleMapper.toDto(updatedSale);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSaleDTO);
    }

    @GetMapping("/viewSalesHistory")
    public ResponseEntity<?> viewSalesHistory() {
        List<Sale> sales = saleService.getAllSales();
        return ResponseEntity.status(HttpStatus.OK).body(sales);
    }
}
