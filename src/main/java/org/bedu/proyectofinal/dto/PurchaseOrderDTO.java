package org.bedu.proyectofinal.dto;

import java.time.LocalDateTime;
import java.util.Set;


import lombok.Data;
import org.bedu.proyectofinal.models.Customer;
import org.bedu.proyectofinal.models.PurchaseOrderItem;

@Data
public class PurchaseOrderDTO {
    private Long id;
    private LocalDateTime date;
    private Customer customer;
    private Set<PurchaseOrderItem> items;
}