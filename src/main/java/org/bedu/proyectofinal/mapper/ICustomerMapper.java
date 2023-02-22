package org.bedu.proyectofinal.mapper;

import org.bedu.proyectofinal.dto.CustomerDTO;
import org.bedu.proyectofinal.models.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ICustomerMapper {
    CustomerDTO toDTO(Customer data);
    Customer toEntity(CustomerDTO data);
}
