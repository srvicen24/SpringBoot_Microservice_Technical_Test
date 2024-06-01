package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.InvalidIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductIdTest {

    @Test
    void givenValidId_whenCreateProductId_thenProductIdIsCreated() {
        ProductId productId = new ProductId(1L);
        assertEquals(1L, productId.value());
    }

    @Test
    void givenNullId_whenCreateProductId_thenThrowInvalidIdException() {
        assertThrows(InvalidIdException.class, () -> new ProductId(null));
    }

    @Test
    void givenZeroId_whenCreateProductId_thenThrowInvalidIdException() {
        assertThrows(InvalidIdException.class, () -> new ProductId(0L));
    }

    @Test
    void givenNegativeId_whenCreateProductId_thenThrowInvalidIdException() {
        assertThrows(InvalidIdException.class, () -> new ProductId(-1L));
    }

}
