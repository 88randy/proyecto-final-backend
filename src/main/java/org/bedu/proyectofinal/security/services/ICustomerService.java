package org.bedu.proyectofinal.security.services;

import org.bedu.proyectofinal.dto.CustomerDTO;
import org.bedu.proyectofinal.payload.exceptions.InvalidDataException;
import org.bedu.proyectofinal.payload.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    List<CustomerDTO> findAll() throws ResourceNotFoundException;
    Optional<CustomerDTO> findById(Long id) throws ResourceNotFoundException;
    CustomerDTO save(CustomerDTO data) throws InvalidDataException;
    void update(long id, CustomerDTO data) throws Exception;
    void delete(long id) throws Exception;
}