package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model;

public class InvalidEanException extends ValidatingProductException {
    public InvalidEanException(String ean) {
        super("Invalid EAN: " + ean);
    }
}
