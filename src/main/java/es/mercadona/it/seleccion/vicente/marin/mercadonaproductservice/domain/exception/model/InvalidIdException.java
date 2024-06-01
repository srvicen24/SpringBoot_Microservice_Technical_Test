package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model;

public class InvalidIdException extends ValidatingProductException {
    public InvalidIdException(Long id) {
        super("Invalid id: " + id);
    }
}
