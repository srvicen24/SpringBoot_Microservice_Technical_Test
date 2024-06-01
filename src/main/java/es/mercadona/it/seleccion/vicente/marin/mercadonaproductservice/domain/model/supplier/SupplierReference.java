package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.InvalidSupplierException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.InvalidSupplierReferenceException;


/**
 * Represents a unique reference for a supplier.
 * <p>
 * This record is used to encapsulate the logic of validation of supplier references.
 * Each instance of this record represents a valid supplier reference.
 * </p>
 *
 * @param value the value {@link String} of the supplier reference.
 * @author Vicente Marín Lorencio
 */
public record SupplierReference(String value) {

    /**
     * The regular expression used to validate the supplier reference.
     */
    private static final String REFERENCE_REGEX = "\\d{7}";

    /**
     * Creates a new instance of SupplierReference with the provided value.
     * <p>
     * If the provided value is null, blank, or does not match the REFERENCE_REGEX, an InvalidSupplierException is
     * thrown.
     * </p>
     *
     * @param value {@link String} the value of the supplier reference
     * @throws InvalidSupplierException if the value is null, blank, or does not match the REFERENCE_REGEX
     */
    public SupplierReference {
        if (!isValidReference(value)) {
            throw new InvalidSupplierReferenceException(value);
        }
    }

    /**
     * Checks if the provided reference is a valid supplier reference.
     * <p>
     * A valid supplier reference is not null, not blank, and matches the REFERENCE_REGEX.
     * </p>
     *
     * @param reference {@link String} the reference to check
     * @return {@link boolean} true if the reference is a valid supplier reference, false otherwise
     */
    private boolean isValidReference(String reference) {
        return reference != null && !reference.isBlank() && reference.matches(REFERENCE_REGEX);
    }
}
