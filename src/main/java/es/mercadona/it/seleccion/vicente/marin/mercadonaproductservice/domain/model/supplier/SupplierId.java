package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.InvalidIdException;

/**
 * Represents a unique identifier for a supplier.
 * <p>
 * This class is used to encapsulate the logic of validation and generation of supplier identifiers.
 * Each instance of this class represents a valid supplier identifier.
 * </p>
 *
 * @param value the value {@link Long} of the supplier identifier.
 * @author Vicente Marín Lorencio
 */
public record SupplierId(Long value) {

    /**
     * Creates a new instance of SupplierId with the provided value.
     * <p>
     * If the provided value is null or blank, an InvalidIdException is thrown.
     * </p>
     *
     * @param value {@link Long} the value of the supplier identifier
     * @throws InvalidIdException if the value is null or blank
     */
    public SupplierId {
        if (!isValid(value)) {
            throw new InvalidIdException(value);
        }
    }

    /**
     * Checks if the provided value is a valid supplier identifier.
     * <p>
     * A valid supplier identifier is not null and not blank.
     * </p>
     *
     * @return {@code boolean} true if the value is a valid supplier identifier, false otherwise
     */
    private boolean isValid(Long value) {
        return value != null && value > 0L;
    }
}
