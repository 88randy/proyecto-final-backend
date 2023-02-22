package org.bedu.proyectofinal.repository;

import org.bedu.proyectofinal.models.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

}
