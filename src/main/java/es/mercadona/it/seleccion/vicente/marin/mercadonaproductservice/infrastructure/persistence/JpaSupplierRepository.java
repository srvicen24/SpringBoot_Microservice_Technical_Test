package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.persistence;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaSupplierRepository extends JpaRepository<SupplierEntity, Long> {

    void deleteByReference(String reference);

    Optional<SupplierEntity> findByReference(String reference);
}
