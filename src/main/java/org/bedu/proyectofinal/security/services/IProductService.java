package org.bedu.proyectofinal.security.services;

import org.bedu.proyectofinal.dto.ProductDTO;
import org.bedu.proyectofinal.payload.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;


public interface IProductService {
    List<ProductDTO> findAll() throws ResourceNotFoundException;
    Optional<ProductDTO> findById(Long id);
    ProductDTO save(ProductDTO data);
    void update(long id, ProductDTO data) throws Exception;
    void delete(long id) throws Exception;
}
