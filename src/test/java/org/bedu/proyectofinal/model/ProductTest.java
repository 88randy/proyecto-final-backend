package org.bedu.proyectofinal.model;

import java.math.BigDecimal;
import org.bedu.proyectofinal.models.Product;
import org.bedu.proyectofinal.repository.IProductRepository;
import org.bedu.proyectofinal.security.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductTest {
    @Mock
    private IProductRepository repository;

    @InjectMocks
    private ProductServiceImpl service;

    /*
     * Test verifica si el método delete() de la clase ProductService está funcionando correctamente
     */
    @Test
    public void testDelete() throws Exception {
        Product product = new Product(1L, "product1", BigDecimal.valueOf(25.50));

        when(repository.findById(1L)).thenReturn(Optional.of(product));

        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    /*
     * Test para comprobar que se establece correctamente el valor
     * del atributo "name" en un objeto de la clase Product
     */
    @Test
    public void testSetProduct() {
        Product product = new Product();
        product.setName("Gorra");
        assertEquals("Gorra", product.getName());
    }

    /*
     * Test para comprobar que se genera correctamente un valor
     * para el atributo "id" al crear un objeto de la clase Product
     */
    @Test
    public void testIdGeneration() {
        Product product = new Product(1L, "product1", BigDecimal.valueOf(25.50));
        assertNotNull(product.getId());
    }

    /*
     * Test para comprobar que se genera correctamente un valor
     * para el atributo "price" al crear un objeto de la clase Product
     */
    @Test
    public void testPriceGeneration() {
        Product product = new Product(1L, "product1", BigDecimal.valueOf(25.50));
        assertNotNull(product.getPrice());
        assertEquals(BigDecimal.valueOf(25.50), product.getPrice());
    }

    /*
     * Test para comprobar que se realiza correctamente la suma de dos valores
     * en el atributo "price" de un objeto de la clase Product
     */
    @Test
    public void testPriceSum() {
        Product product = new Product(1L, "product1", BigDecimal.valueOf(25.50));
        double newValue = 5.25 + product.getPrice().doubleValue();
        product.setPrice(BigDecimal.valueOf(newValue));
        assertEquals(BigDecimal.valueOf(30.75), product.getPrice());
    }

}