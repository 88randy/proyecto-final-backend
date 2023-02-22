package org.bedu.proyectofinal.model;


import lombok.SneakyThrows;

import org.bedu.proyectofinal.models.Customer;
import org.bedu.proyectofinal.repository.ICustomerRepository;
import org.bedu.proyectofinal.security.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CustomerTest {
    @Mock
    private ICustomerRepository repository;

    @InjectMocks
    private CustomerServiceImpl service;


    /*
     * Test verifica si el método delete() de la clase CustomerService está funcionando correctamente
     */
    @Test
    public void testDelete() throws Exception {
        Customer customer = new Customer(1L, "customer1");

        when(repository.findById(1L)).thenReturn(Optional.of(customer));

        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    /*
     * Test para comprobar que se establece correctamente el valor
     * del atributo "customer" en un objeto de la clase Customer
     */
    @Test
    public void testSetCustomer() {
        Customer customer = new Customer();
        customer.setCustomer("Pepe Pecas Pica Papas");
        assertEquals("Pepe Pecas Pica Papas", customer.getCustomer());
    }

    /*
     * Test para comprobar que se genera correctamente un valor
     * para el atributo "id" al crear un objeto de la clase Customer
     */
    @Test
    public void testIdGeneration() {
        Customer customer = new Customer(1l, "Pepito");
        assertNotNull(customer.getId());
    }

    /*
     * Test para comprobar que se llama al método "findAll()" del repositorio al ejecutar el método "findAll()" del servicio
     */
    @SneakyThrows
    @Test
    public void testFindAll() {
        service.findAll();
        verify(repository, times(1)).findAll();

    }

    /*
     * Test para comprobar que se llama al método "findById()" del repositorio al ejecutar el método "findById()" del servicio
     */

    @SneakyThrows
    @Test
    public void testFindById() {
        service.findById(1L);
        verify(repository, times(1)).findById(1L);

    }


}
