package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product;

/**
 * Represents a description for a product.
 * <p>
 * This record is used to encapsulate the logic of validation and generation of product descriptions.
 * Each instance of this record represents a valid product description.
 * </p>
 *
 * @param value the value {@link String} of the product description.
 * @author Vicente Marín Lorencio
 */
public record Description(String value) {
}
