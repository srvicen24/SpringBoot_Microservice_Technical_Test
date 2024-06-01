package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.mapper;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.Supplier;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.entity.SupplierEntity;

public interface SupplierEntityMapper {

    Supplier toDomain(SupplierEntity supplierEntity);

    SupplierEntity toEntity(Supplier supplier);
}
