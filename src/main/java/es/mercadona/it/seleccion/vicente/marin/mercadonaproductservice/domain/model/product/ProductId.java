package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.InvalidIdException;

/**
 * Represents a unique identifier for a product.
 * <p>
 * This class is used to encapsulate the logic of validation and generation of product identifiers.
 * Each instance of this class represents a valid product identifier.
 * </p>
 *
 * @param value the value {@link Long} of the product identifier.
 * @author Vicente Marín Lorencio
 */
public record ProductId(Long value) {

    /**
     * Creates a new instance of ProductId with the provided value.
     * <p>
     * If the provided value is null or blank, an InvalidIdException is thrown.
     * </p>
     *
     * @param value {@link Long} the value of the product identifier
     * @throws InvalidIdException if the value is null or blank
     */
    public ProductId {
        if (!isValid(value)) {
            throw new InvalidIdException(value);
        }
    }

    /**
     * Checks if the provided value is a valid product identifier.
     * <p>
     * A valid product identifier is not null and not blank.
     * </p>
     *
     * @param value {@link Long} the value to check
     * @return {@code boolean} true if the value is a valid product identifier, false otherwise
     */
    private boolean isValid(Long value) {
        return value != null && value > 0L;
    }

}
