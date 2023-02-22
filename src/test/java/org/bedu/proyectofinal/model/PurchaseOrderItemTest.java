package org.bedu.proyectofinal.model;

import org.bedu.proyectofinal.dto.PurchaseOrderItemDTO;
import org.bedu.proyectofinal.payload.exceptions.NotFoundException;
import org.bedu.proyectofinal.repository.IPurchaseOrderItemRepository;
import org.bedu.proyectofinal.security.services.impl.PurchaseOrderItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class PurchaseOrderItemTest {
    @Mock
    private IPurchaseOrderItemRepository repository;

    @InjectMocks
    private PurchaseOrderItemServiceImpl service;

    /*
     * Verificar que al llamar al método findAll() se obtenga una lista vacía
     */
    @Test
    public void testFindAll_emptyList() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(service.findAll().isEmpty());
    }

    /*
     * Verificar que al llamar al método findById(1L) se obtenga un valor vacío
     */
    @Test
    public void testFindById_notFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertFalse(service.findById(1L).isPresent());
    }

    /*
     * Verificar que al llamar al método update(1L, data) con un id que no existe en el repositorio, se lance una excepción
     */
    @Test
    public void testUpdate_notFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        PurchaseOrderItemDTO data = new PurchaseOrderItemDTO();
        assertThrows(NotFoundException.class, () -> service.update(1L, data));
    }

    /*
     * Verificar que al llamar al método delete(1L) con un id que no existe en el repositorio, se lance una excepción
     */
    @Test
    public void testDelete_notFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.delete(1L));
    }



}