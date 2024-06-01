package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model;

public class InvalidDestinationException extends ValidatingProductException {
    public InvalidDestinationException(char destinationCode) {
        super("Invalid destination code: " + destinationCode);
    }
}
