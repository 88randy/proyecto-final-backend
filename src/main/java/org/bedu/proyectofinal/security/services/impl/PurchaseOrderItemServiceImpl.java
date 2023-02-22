package org.bedu.proyectofinal.security.services.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bedu.proyectofinal.dto.PurchaseOrderItemDTO;
import org.bedu.proyectofinal.mapper.IPurchaseOrderItemMapper;
import org.bedu.proyectofinal.models.PurchaseOrderItem;
import org.bedu.proyectofinal.payload.exceptions.NotFoundException;
import org.bedu.proyectofinal.repository.IPurchaseOrderItemRepository;
import org.bedu.proyectofinal.security.services.IPurchaseOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PurchaseOrderItemServiceImpl implements IPurchaseOrderItemService {

    private IPurchaseOrderItemMapper mapper;
    private IPurchaseOrderItemRepository repository;

    @Autowired
    public PurchaseOrderItemServiceImpl(IPurchaseOrderItemMapper mapper, IPurchaseOrderItemRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<PurchaseOrderItemDTO> findAll() {
        List<PurchaseOrderItem> purchaseOrderItem = repository.findAll();

        List<PurchaseOrderItemDTO> mappedPurchaseOrderItem = new LinkedList<>();

        for (PurchaseOrderItem c : purchaseOrderItem) {
            mappedPurchaseOrderItem.add(mapper.toDTO(c));
        }

        return mappedPurchaseOrderItem;
    }

    public Optional<PurchaseOrderItemDTO> findById(Long id) {
        return repository.findById(id).map(purchaseOrderItem -> mapper.toDTO(purchaseOrderItem));
    }

    public PurchaseOrderItemDTO save(PurchaseOrderItemDTO data) {
        PurchaseOrderItem entity = mapper.toEntity(data);
        return mapper.toDTO(repository.save(entity));
    }

    public void update(long id, PurchaseOrderItemDTO data) throws Exception {
        Optional<PurchaseOrderItem> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new NotFoundException(404, "No se puede actualizar un item de la orden de compra que no existe");
        }

        PurchaseOrderItem purchaseOrderItem = result.get();

        purchaseOrderItem.setProduct(data.getProduct());
        purchaseOrderItem.setQuantity(data.getQuantity());
        purchaseOrderItem.setPurchaseOrder(data.getPurchaseOrder());

        repository.save(purchaseOrderItem);
    }

    public void delete(long id) throws Exception {
        Optional<PurchaseOrderItem> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new NotFoundException(404, "No se puede borrar un item de la orden de compra que no existe");
        }

        repository.deleteById(id);
    }
}
