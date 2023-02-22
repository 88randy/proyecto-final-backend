package org.bedu.proyectofinal.security.services.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bedu.proyectofinal.dto.CustomerDTO;
import org.bedu.proyectofinal.mapper.ICustomerMapper;
import org.bedu.proyectofinal.models.Customer;
import org.bedu.proyectofinal.payload.exceptions.InvalidDataException;
import org.bedu.proyectofinal.payload.exceptions.NotFoundException;
import org.bedu.proyectofinal.repository.ICustomerRepository;
import org.bedu.proyectofinal.security.services.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements ICustomerService {
    private ICustomerMapper mapper;
    private ICustomerRepository repository;
    private static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);


    @Autowired
    public CustomerServiceImpl(ICustomerMapper mapper, ICustomerRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<CustomerDTO> findAll()  {
        List<Customer> customers = repository.findAll();
        List<CustomerDTO> mappedCustomers = new LinkedList<>();
        for (Customer c : customers) {
            mappedCustomers.add(mapper.toDTO(c));
        }
        return mappedCustomers;
    }

    public Optional<CustomerDTO> findById(Long id) {
        Optional<Customer> result = repository.findById(id);
        return result.map(customer -> mapper.toDTO(customer));
    }

    public CustomerDTO save(CustomerDTO data) throws InvalidDataException {
        Customer entity = mapper.toEntity(data);
        return mapper.toDTO(repository.save(entity));
    }

    public void update(long id, CustomerDTO data) throws NotFoundException {
        Optional<Customer> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new NotFoundException(404, "No se puede actualizar un cliente que no existe");
        }

        if (data == null) {
            try {
                throw new InvalidDataException("Los datos proporcionados son inválidos", 400);
            } catch (InvalidDataException e) {
                throw new RuntimeException(e);
            }
        }

        Customer customer = result.get();

        customer.setCustomer(data.getCustomer());

        repository.save(customer);
    }

    public void delete(long id) throws NotFoundException {
        Optional<Customer> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new NotFoundException(404, "No se puede borrar un cliente que no existe");
        }

        repository.deleteById(id);
    }

}

