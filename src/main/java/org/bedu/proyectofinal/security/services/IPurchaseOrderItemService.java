package org.bedu.proyectofinal.security.services;

import org.bedu.proyectofinal.dto.PurchaseOrderItemDTO;

import java.util.List;
import java.util.Optional;


public interface IPurchaseOrderItemService {
    List<PurchaseOrderItemDTO> findAll();
    Optional<PurchaseOrderItemDTO> findById(Long id);
    PurchaseOrderItemDTO save(PurchaseOrderItemDTO data);
    void update(long id, PurchaseOrderItemDTO data) throws Exception;
    void delete(long id) throws Exception;
}
