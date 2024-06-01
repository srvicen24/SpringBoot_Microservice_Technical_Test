package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model;

public class InvalidSupplierReferenceException extends ValidatingSupplierException {
    public InvalidSupplierReferenceException(String reference) {
        super("Invalid supplier reference: " + reference);
    }
}
