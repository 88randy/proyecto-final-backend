package org.bedu.proyectofinal.model;

import org.bedu.proyectofinal.dto.PurchaseOrderDTO;
import org.bedu.proyectofinal.payload.exceptions.NotFoundException;
import org.bedu.proyectofinal.repository.IPurchaseOrderRepository;
import org.bedu.proyectofinal.security.services.impl.PurchaseOrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PurchaseOrderTest {
    @Mock
    private IPurchaseOrderRepository repository;

    @InjectMocks
    private PurchaseOrderServiceImpl service;

    /*
     * Test para verificar que el método findById() devuelve un Optional vacío cuando no se encuentra una orden de compra con el id especificado
     */
    @Test
    public void testFindById_notFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertFalse(service.findById(1L).isPresent());
    }

    @Test
    public void testSave_invalidData() {
        assertThrows(RuntimeException.class, () -> service.save(null));
    }

    /*
     * Test para verificar que el método update() lanza una excepción cuando no se encuentra una orden de compra con el id especificado
     */
    @Test
    public void testUpdate_notFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.update(1L, new PurchaseOrderDTO()));
    }

    /*Test para verificar que el método delete() lanza una excepción cuando no se encuentra una orden de compra con el id especificado:
     */
    @Test
    public void testDelete_notFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.delete(1L));
    }

}
