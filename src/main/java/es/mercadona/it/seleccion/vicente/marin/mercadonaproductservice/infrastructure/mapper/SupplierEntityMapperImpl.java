package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.mapper;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.Supplier;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierId;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierReference;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.entity.SupplierEntity;
import org.springframework.stereotype.Component;

@Component
public class SupplierEntityMapperImpl implements SupplierEntityMapper {

    @Override
    public Supplier toDomain(SupplierEntity supplierEntity) {
        SupplierId supplierId = new SupplierId(supplierEntity.getId());
        SupplierReference supplierReference = new SupplierReference(supplierEntity.getReference());
        return new Supplier(supplierId, supplierReference, supplierEntity.getName());
    }

    @Override
    public SupplierEntity toEntity(Supplier supplier) {
        SupplierEntity supplierEntity = new SupplierEntity();
        SupplierId supplierId = supplier.supplierId();
        if (supplierId != null) {
            supplierEntity.setId(supplierId.value());
        }
        supplierEntity.setReference(supplier.reference().value());
        supplierEntity.setName(supplier.name());
        return supplierEntity;
    }
}
