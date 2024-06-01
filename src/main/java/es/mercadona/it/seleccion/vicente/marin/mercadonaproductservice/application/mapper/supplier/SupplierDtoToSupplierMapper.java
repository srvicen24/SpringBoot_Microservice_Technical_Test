package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.SupplierDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.Supplier;

public interface SupplierDtoToSupplierMapper {

    Supplier mapSupplierDtoToSupplier(SupplierDTO supplierDTO);
}
