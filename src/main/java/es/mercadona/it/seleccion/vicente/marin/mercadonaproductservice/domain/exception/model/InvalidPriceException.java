package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model;

public class InvalidPriceException extends ValidatingProductException {
    public InvalidPriceException(double price) {
        super("The price cannot be less than or equal to 0: " + price);
    }
}
