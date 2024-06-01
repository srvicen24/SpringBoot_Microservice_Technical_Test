package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product;


import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.InvalidDestinationException;
import lombok.Getter;

/**
 * Represents a destination for a product.
 * <p>
 * This enum is used to encapsulate the logic of validation and generation of product destinations.
 * Each instance of this enum represents a valid product destination.
 * </p>
 *
 * @author Vicente Marín Lorencio
 */
@Getter
public enum Destination {
    /**
     * Represents the "Colmenas" destination.
     */
    COLMENAS("Colmenas", '0'),

    /**
     * Represents the "Mercadona España" destination.
     */
    MERCADONA_ESPANA("Mercadona España", '1', '2', '3', '4', '5'),

    /**
     * Represents the "Mercadona Portugal" destination.
     */
    MERCADONA_PORTUGAL("Mercadona Portugal", '6'),

    /**
     * Represents the "Almacenes" destination.
     */
    ALMACENES("Almacenes", '8'),

    /**
     * Represents the "Oficinas Mercadona" destination.
     */
    OFICINAS_MERCADONA("Oficinas Mercadona", '9');

    /**
     * The description of the destination.
     */
    private final String description;

    /**
     * The code(s) associated with the destination.
     */
    private final char[] code;

    /**
     * Constructs a new Destination with the provided description and code(s).
     *
     * @param description the description of the destination
     * @param code        the code(s) associated with the destination
     */
    Destination(String description, char... code) {
        this.description = description;
        this.code = code;
    }

    /**
     * Returns the Destination associated with the provided code.
     * <p>
     * If no Destination is associated with the provided code, an InvalidDestinationExceptionValidating is thrown.
     * </p>
     *
     * @param code the code to find the associated Destination
     * @return the Destination associated with the provided code
     * @throws InvalidDestinationException if no Destination is associated with the provided code
     */
    public static Destination fromCode(char code) {
        for (Destination destination : Destination.values()) {
            for (char c : destination.code) {
                if (c == code) {
                    return destination;
                }
            }
        }
        throw new InvalidDestinationException(code);
    }
}
