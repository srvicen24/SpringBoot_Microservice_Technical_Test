package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.SupplierDTO;

public interface UpdateSupplierUseCase {
    SupplierDTO updateSupplierById(Long id, SupplierDTO supplierDTO);

    SupplierDTO updateSupplierByReference(String reference, SupplierDTO supplierDTO);
}
