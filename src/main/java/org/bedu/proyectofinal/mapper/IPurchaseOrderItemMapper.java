package org.bedu.proyectofinal.mapper;

import org.bedu.proyectofinal.dto.PurchaseOrderItemDTO;
import org.bedu.proyectofinal.models.PurchaseOrderItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IPurchaseOrderItemMapper {
    PurchaseOrderItemDTO toDTO(PurchaseOrderItem data);
    PurchaseOrderItem toEntity(PurchaseOrderItemDTO data);
}
