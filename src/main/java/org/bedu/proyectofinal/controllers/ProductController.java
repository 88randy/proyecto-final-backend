package org.bedu.proyectofinal.controllers;

import java.util.List;
import java.util.Optional;

import lombok.SneakyThrows;
import org.bedu.proyectofinal.dto.ProductDTO;
import org.bedu.proyectofinal.security.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private IProductService service;

    @Autowired
    public ProductController(IProductService service) {
        this.service = service;
    }

    @SneakyThrows
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<ProductDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ProductDTO findById(@PathVariable Long id) {
        Optional<ProductDTO> product = service.findById(id);
        return product.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ProductDTO save(@RequestBody ProductDTO data) {
        return service.save(data);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public void update(@PathVariable("id") Long id, @RequestBody ProductDTO data) throws Exception {
        service.update(id, data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
    }
}