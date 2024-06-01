package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.persistence;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByEan(String ean);

    void deleteByEan(String ean);

}
