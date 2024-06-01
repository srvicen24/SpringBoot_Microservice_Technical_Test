package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.EmptyNameException;

/**
 * Represents a name for a product.
 * <p>
 * This record is used to encapsulate the logic of validation of product names.
 * Each instance of this record represents a valid product name.
 * </p>
 *
 * @param value the value {@link String} of the product name.
 * @author Vicente Marín Lorencio
 */
public record Name(String value) {

    /**
     * Creates a new instance of Name with the provided value.
     * <p>
     * If the provided value is null or blank, an EmptyNameException is thrown.
     * </p>
     *
     * @param value {@link String} the value of the product name
     * @throws EmptyNameException if the value is null or blank
     */
    public Name {
        if (value == null || value.isBlank()) {
            throw new EmptyNameException(value);
        }
    }
}
