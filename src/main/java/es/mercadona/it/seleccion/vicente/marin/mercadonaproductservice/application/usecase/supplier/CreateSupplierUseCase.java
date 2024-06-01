package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.SupplierDTO;

public interface CreateSupplierUseCase {
    SupplierDTO createSupplier(SupplierDTO supplierDTO);
}
