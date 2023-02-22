package org.bedu.proyectofinal.repository;

import org.bedu.proyectofinal.models.PurchaseOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Long>{

}