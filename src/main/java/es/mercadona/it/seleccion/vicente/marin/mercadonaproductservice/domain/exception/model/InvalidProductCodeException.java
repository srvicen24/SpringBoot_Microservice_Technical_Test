package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model;

public class InvalidProductCodeException extends ValidatingProductException {
    public InvalidProductCodeException(String productCode) {
        super("Invalid product code: " + productCode);
    }
}
