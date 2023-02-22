package org.bedu.proyectofinal.mapper;

import org.bedu.proyectofinal.dto.ProductDTO;
import org.bedu.proyectofinal.models.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IProductMapper {
    ProductDTO toDTO(Product data);
    Product toEntity(ProductDTO data);
}
