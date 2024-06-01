package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model;

public class EmptyNameException extends ValidatingProductException {

    public EmptyNameException(String name) {
        super("The name of the product cannot be empty or null: " + name);
    }
}
