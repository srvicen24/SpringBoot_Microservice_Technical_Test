package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.repository;

public class SupplierNotFoundException extends EntityNotFoundException {
    public SupplierNotFoundException(Object reference) {
        super("Supplier not found. Reference: " + reference);
    }
}
