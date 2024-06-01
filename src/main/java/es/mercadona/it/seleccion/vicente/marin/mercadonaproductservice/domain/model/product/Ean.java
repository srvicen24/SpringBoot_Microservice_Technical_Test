package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.InvalidEanException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierReference;

/**
 * Represents an EAN (European Article Number) for a product.
 * <p>
 * This record is used to encapsulate the logic of validation and generation of product EANs.
 * Each instance of this record represents a valid product EAN.
 * </p>
 *
 * @param value the value {@link String} of the product EAN.
 * @author Vicente Marín Lorencio
 */
public record Ean(String value) {

    /**
     * The regular expression used to validate the EAN.
     */
    private static final String EAN_REGEX = "\\d{13}";

    /**
     * Creates a new instance of Ean with the provided value.
     * <p>
     * If the provided value is null, blank, or does not match the EAN_REGEX, an InvalidEanException is thrown.
     * </p>
     *
     * @param value {@link String} the value of the product EAN
     * @throws InvalidEanException if the value is null, blank, or does not match the EAN_REGEX
     */
    public Ean {
        if (!isValid(value)) {
            throw new InvalidEanException(value);
        }
    }

    /**
     * Checks if the provided value is a valid EAN.
     * <p>
     * A valid EAN is not null, not blank, and matches the EAN_REGEX.
     * </p>
     *
     * @param value {@link String} the value to check
     * @return {@link boolean} true if the value is a valid EAN, false otherwise
     */
    private boolean isValid(String value) {
        return value != null && !value.isBlank() && value.matches(EAN_REGEX);
    }

    /**
     * Returns the supplier reference from the EAN.
     * <p>
     * The supplier reference is the first 7 characters of the EAN.
     * </p>
     *
     * @return {@link String} the supplier reference
     */
    public SupplierReference getSupplierReference() {
        String reference = value.substring(0, 7);
        return new SupplierReference(reference);
    }

    /**
     * Returns the product code from the EAN.
     * <p>
     * The product code is the characters from the 8th to the 12th of the EAN.
     * </p>
     *
     * @return {@link String} the product code
     */
    public String getProductCode() {
        return value.substring(7, 12);
    }

    /**
     * Returns the destination code from the EAN.
     * <p>
     * The destination code is the 13th character of the EAN.
     * </p>
     *
     * @return the destination code
     */
    public char getDestinationCode() {
        return value.charAt(12);
    }

}
