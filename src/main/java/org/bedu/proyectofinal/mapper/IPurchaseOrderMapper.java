package org.bedu.proyectofinal.mapper;

import org.bedu.proyectofinal.dto.PurchaseOrderDTO;
import org.bedu.proyectofinal.models.PurchaseOrder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IPurchaseOrderMapper {
    PurchaseOrderDTO toDTO(PurchaseOrder data);
    PurchaseOrder toEntity(PurchaseOrderDTO data);
}