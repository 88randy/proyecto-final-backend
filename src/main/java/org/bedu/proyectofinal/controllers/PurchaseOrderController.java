package org.bedu.proyectofinal.controllers;

import java.util.List;
import java.util.Optional;

import org.bedu.proyectofinal.dto.PurchaseOrderDTO;
import org.bedu.proyectofinal.security.services.IPurchaseOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    private IPurchaseOrderService service;

    @Autowired
    public PurchaseOrderController(IPurchaseOrderService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<PurchaseOrderDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public PurchaseOrderDTO findById(@PathVariable Long id) {
        Optional<PurchaseOrderDTO> product = service.findById(id);
        return product.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public PurchaseOrderDTO save(@RequestBody PurchaseOrderDTO data) {
        return service.save(data);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public void update(@PathVariable("id") Long id, @RequestBody PurchaseOrderDTO data) throws Exception {
        service.update(id, data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
    }

}
