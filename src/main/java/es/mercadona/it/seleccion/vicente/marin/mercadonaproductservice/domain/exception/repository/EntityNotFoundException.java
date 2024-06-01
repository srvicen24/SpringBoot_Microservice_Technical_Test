package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.repository;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
