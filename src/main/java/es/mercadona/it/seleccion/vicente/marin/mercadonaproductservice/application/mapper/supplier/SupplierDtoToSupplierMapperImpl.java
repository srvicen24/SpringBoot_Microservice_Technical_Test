package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.SupplierDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.ValidatingSupplierException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.Supplier;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierId;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierReference;
import org.springframework.stereotype.Component;

@Component
public class SupplierDtoToSupplierMapperImpl implements SupplierDtoToSupplierMapper {


    @Override
    public Supplier mapSupplierDtoToSupplier(SupplierDTO supplierDTO) {
        validateSupplier(supplierDTO);
        SupplierId supplierId = supplierDTO.id() != null ? new SupplierId(supplierDTO.id()) : null;
        return new Supplier(supplierId, new SupplierReference(supplierDTO.reference()), supplierDTO.name());
    }

    private void validateSupplier(SupplierDTO supplierDTO) {
        if (supplierDTO == null) {
            throw new ValidatingSupplierException("SupplierDTO is null");
        }
    }
}
