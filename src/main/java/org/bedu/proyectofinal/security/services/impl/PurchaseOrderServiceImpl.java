package org.bedu.proyectofinal.security.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


import org.bedu.proyectofinal.dto.PurchaseOrderDTO;
import org.bedu.proyectofinal.mapper.IPurchaseOrderMapper;
import org.bedu.proyectofinal.models.PurchaseOrder;
import org.bedu.proyectofinal.models.PurchaseOrderItem;
import org.bedu.proyectofinal.payload.exceptions.InvalidDataException;
import org.bedu.proyectofinal.payload.exceptions.NotFoundException;
import org.bedu.proyectofinal.repository.IPurchaseOrderRepository;
import org.bedu.proyectofinal.security.services.IPurchaseOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

    private IPurchaseOrderMapper mapper;
    private IPurchaseOrderRepository repository;
    private static Logger logger = LoggerFactory.getLogger(PurchaseOrderServiceImpl.class);

    @Autowired
    public PurchaseOrderServiceImpl(IPurchaseOrderMapper mapper, IPurchaseOrderRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<PurchaseOrderDTO> findAll() {
        List<PurchaseOrder> purchaseOrder = repository.findAll();
        return purchaseOrder.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toUnmodifiableList());
    }

    public Optional<PurchaseOrderDTO> findById(Long id) {
        Optional<PurchaseOrder> result = repository.findById(id);
        return result.map(purchaseOrder -> mapper.toDTO(purchaseOrder));
    }


    public PurchaseOrderDTO save(PurchaseOrderDTO data) {
        if (data == null) {
            try {
                throw new InvalidDataException("Los datos proporcionados son inválidos", 400);
            } catch (InvalidDataException e) {
                throw new RuntimeException(e);
            }
        }
        PurchaseOrder entity = mapper.toEntity(data);
        for (PurchaseOrderItem item : entity.getItems()) {
            item.setPurchaseOrder(entity);
        }

        return mapper.toDTO(repository.save(entity));
    }

    public void update(long id, PurchaseOrderDTO data) throws Exception {
        Optional<PurchaseOrder> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new NotFoundException(404, "No puede actualizar una orden de compra que no existe");
        }

        PurchaseOrder purchaseOrder = result.get();
        Set<PurchaseOrderItem> items = data.getItems();

        purchaseOrder.setCustomer(data.getCustomer());
        purchaseOrder.getItems().clear();

        for (PurchaseOrderItem item : items) {
            item.setPurchaseOrder(purchaseOrder);
            purchaseOrder.getItems().add(item);
        }
        repository.save(purchaseOrder);
    }

    public void delete(long id) throws Exception {
        Optional<PurchaseOrder> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new NotFoundException(404, "No puede borrar una orden de compra que no existe");
        }

        repository.deleteById(id);
    }
}