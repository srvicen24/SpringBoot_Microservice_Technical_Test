package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.SupplierDTO;

import java.util.List;

public interface GetSupplierUseCase {
    SupplierDTO getSupplierByReference(String reference);

    SupplierDTO getSupplierById(Long id);

    List<SupplierDTO> getAllSuppliers();
}
