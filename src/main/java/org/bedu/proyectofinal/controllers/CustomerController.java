package org.bedu.proyectofinal.controllers;

import java.util.List;
import java.util.Optional;

import lombok.SneakyThrows;
import org.bedu.proyectofinal.dto.CustomerDTO;
import org.bedu.proyectofinal.security.services.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private ICustomerService service;

    @Autowired
    public CustomerController(ICustomerService service) {
        this.service = service;
    }

    @SneakyThrows
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<CustomerDTO> findAll() {
        return service.findAll();
    }

    @SneakyThrows
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public CustomerDTO findById(@PathVariable Long id) {
        Optional<CustomerDTO> customer = service.findById(id);
        return customer.get();
    }

    @SneakyThrows
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public CustomerDTO save(@RequestBody CustomerDTO data) {
        return service.save(data);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody CustomerDTO data) {
        try{
            service.update(id, data);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try{
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
