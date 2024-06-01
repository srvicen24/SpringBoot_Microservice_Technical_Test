package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.InvalidIdException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.InvalidSupplierException;

/**
 * Represents a supplier in the system.
 * <p>
 * This record is used to encapsulate the logic of validation of supplier references and names.
 * Each instance of this record represents a valid supplier with a valid reference and name.
 * </p>
 *
 * @param reference the reference {@link String} of the supplier.
 * @param name      the name {@link String} of the supplier.
 * @author Vicente Marín Lorencio
 */
public record Supplier(SupplierId supplierId, SupplierReference reference, String name) {

    /**
     * Creates a new instance of Supplier with the provided reference and name.
     * <p>
     * If the provided reference or name is null, blank, or does not match the REFERENCE_REGEX, an
     * InvalidSupplierException is thrown.
     * </p>
     *
     * @param reference  {@link String} the reference of the supplier
     * @param name       {@link String} the name of the supplier
     * @param supplierId {@link SupplierId} the id of the supplier
     * @throws InvalidSupplierException if the reference or name is null, blank, or does not match the
     *                                  REFERENCE_REGEX
     * @throws InvalidIdException       if the supplierId is null
     */
    public Supplier {
        if (reference == null) {
            throw new InvalidSupplierException(null, name);
        }
        if (!isValidName(name)) {
            throw new InvalidSupplierException(reference.value(), name);
        }
    }

    /**
     * Checks if the provided name is a valid supplier name.
     * <p>
     * A valid supplier name is not null and not blank.
     * </p>
     *
     * @param name {@link String} the name to check
     * @return {@link boolean} true if the name is a valid supplier name, false otherwise
     */
    private boolean isValidName(String name) {
        return name != null && !name.isBlank();
    }

}
