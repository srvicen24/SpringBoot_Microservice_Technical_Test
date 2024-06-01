package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model;

public class InvalidSupplierException extends ValidatingSupplierException {
    public InvalidSupplierException(String reference, String name) {
        super("Invalid supplier - Reference: " + reference + " - Name: " + name);
    }
}
