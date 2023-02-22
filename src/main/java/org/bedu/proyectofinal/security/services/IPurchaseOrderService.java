package org.bedu.proyectofinal.security.services;

import org.bedu.proyectofinal.dto.PurchaseOrderDTO;

import java.util.List;
import java.util.Optional;


public interface IPurchaseOrderService {
    List<PurchaseOrderDTO> findAll();
    Optional<PurchaseOrderDTO> findById(Long id);
    PurchaseOrderDTO save(PurchaseOrderDTO data);
    void update(long id, PurchaseOrderDTO data) throws Exception;
    void delete(long id) throws Exception;
}
