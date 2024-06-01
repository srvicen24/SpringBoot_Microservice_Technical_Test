package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.InvalidPriceException;

/**
 * Represents a price for a product.
 * <p>
 * This record is used to encapsulate the logic of validation of product prices.
 * Each instance of this record represents a valid product price.
 * </p>
 *
 * @param value the value {@code double} of the product price.
 * @author Vicente Marín Lorencio
 */
public record Price(double value) {

    /**
     * Creates a new instance of Price with the provided value.
     * <p>
     * If the provided value is less than 0, an InvalidPriceException is thrown.
     * </p>
     *
     * @param value {@code double} the value of the product price
     * @throws InvalidPriceException if the value is less than 0
     */
    public Price {
        if (value <= 0) {
            throw new InvalidPriceException(value);
        }
    }
}
