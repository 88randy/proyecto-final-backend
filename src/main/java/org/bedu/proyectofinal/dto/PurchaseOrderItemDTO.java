package org.bedu.proyectofinal.dto;

import lombok.Data;
import org.bedu.proyectofinal.models.Product;
import org.bedu.proyectofinal.models.PurchaseOrder;

@Data
public class PurchaseOrderItemDTO {
    private Long id;
    private Product product;
    private Integer quantity;
    private PurchaseOrder purchaseOrder;
}
