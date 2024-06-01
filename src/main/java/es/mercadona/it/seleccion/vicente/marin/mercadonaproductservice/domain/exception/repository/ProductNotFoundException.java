package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.repository;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException(Object id) {
        super("Product not found: " + id);
    }
}
