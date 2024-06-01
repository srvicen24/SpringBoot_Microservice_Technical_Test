package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.InvalidEanException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.InvalidProductCodeException;

/**
 * Represents a product code in the system.
 * <p>
 * This record is used to encapsulate the logic of validation of product codes.
 * Each instance of this record represents a valid product code.
 * </p>
 *
 * @param value the value {@link String} of the product code.
 * @author Vicente Marín Lorencio
 */
public record ProductCode(String value) {

    /**
     * The regular expression used to validate the product code.
     */
    private static final String PRODUCT_CODE_REGEX = "\\d{5}";

    /**
     * Creates a new instance of ProductCode with the provided value.
     * <p>
     * If the provided value is null or blank, an InvalidEanException is thrown.
     * </p>
     *
     * @param value {@link String} the value of the product code
     * @throws InvalidEanException if the value is null or blank
     */
    public ProductCode {
        if (!isValid(value)) {
            throw new InvalidProductCodeException(value);
        }
    }

    /**
     * Checks if the provided value is a valid product code.
     * <p>
     * A valid product code is not null, not blank, and matches the PRODUCT_CODE_REGEX.
     * </p>
     *
     * @param value {@link String} the value to check
     * @return {@code boolean} true if the value is a valid product code, false otherwise
     */
    private boolean isValid(String value) {
        return value != null && !value.isBlank() && value.matches(PRODUCT_CODE_REGEX);
    }
}
