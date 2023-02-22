package org.bedu.proyectofinal.controllers;

import java.util.List;
import java.util.Optional;

import org.bedu.proyectofinal.dto.PurchaseOrderItemDTO;
import org.bedu.proyectofinal.security.services.IPurchaseOrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/purchase-order-items")
public class PurchaseOrderItemController {

    private IPurchaseOrderItemService service;

    @Autowired
    public PurchaseOrderItemController(IPurchaseOrderItemService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<PurchaseOrderItemDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public PurchaseOrderItemDTO findById(@PathVariable Long id) {
        Optional<PurchaseOrderItemDTO> product = service.findById(id);
        return product.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public PurchaseOrderItemDTO save(@RequestBody PurchaseOrderItemDTO data) {
        return service.save(data);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public void update(@PathVariable("id") Long id, @RequestBody PurchaseOrderItemDTO data) throws Exception {
        service.update(id, data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
    }

}
