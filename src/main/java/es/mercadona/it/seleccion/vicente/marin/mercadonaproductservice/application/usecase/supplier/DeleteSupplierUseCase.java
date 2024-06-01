package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.supplier;

public interface DeleteSupplierUseCase {
    void deleteSupplierByReference(String reference);

    void deleteSupplierById(Long id);
}
