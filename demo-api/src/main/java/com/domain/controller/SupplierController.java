package com.domain.controller;

import javax.validation.Valid;
import com.domain.services.SupplierService;
import com.domain.dto.ResponseData;
import com.domain.dto.SearchData;
import com.domain.dto.SupplierData;
import com.domain.models.entities.Supplier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    public SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData supplierData, Errors errors) {

        ResponseData<Supplier> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {

                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setStatus(false);
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Supplier supplier = modelMapper.map(supplierData, Supplier.class);

        // Supplier supplier = new Supplier();
        // supplier.setName(supplierData.getName());
        // supplier.setAddress(supplierData.getAddress());
        // supplier.setEmail(supplierData.getEmail());

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);

    }

    @GetMapping
    public Iterable<Supplier> findAll() {
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public Supplier findOne(@PathVariable("id") Long id) {
        return supplierService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierData supplierData, Errors errors) {

        ResponseData<Supplier> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {

                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setStatus(false);
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Supplier supplier = modelMapper.map(supplierData, Supplier.class);

        // Supplier supplier = new Supplier();
        // supplier.setName(supplierData.getName());
        // supplier.setAddress(supplierData.getAddress());
        // supplier.setEmail(supplierData.getEmail());

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);

    }

    @PostMapping("/search/byemail")
    public Supplier findByEmail(@RequestBody SearchData searchData) {

        return supplierService.findByEmail(searchData.getSearchKey());

    }

}
