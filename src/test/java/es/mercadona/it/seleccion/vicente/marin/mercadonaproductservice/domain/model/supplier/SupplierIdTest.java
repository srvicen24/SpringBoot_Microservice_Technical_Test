package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.InvalidIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SupplierIdTest {

    @Test
    void givenValidId_whenCreateSupplierId_thenSupplierIdIsCreated() {
        SupplierId supplierId = new SupplierId(1L);
        assertNotNull(supplierId.value());
    }

    @Test
    void givenNullId_whenCreateSupplierId_thenThrowInvalidIdException() {
        assertThrows(InvalidIdException.class, () -> new SupplierId(null));
    }

    @Test
    void givenZeroId_whenCreateSupplierId_thenThrowInvalidIdException() {
        assertThrows(InvalidIdException.class, () -> new SupplierId(0L));
    }

    @Test
    void givenNegativeId_whenCreateSupplierId_thenThrowInvalidIdException() {
        assertThrows(InvalidIdException.class, () -> new SupplierId(0L));
    }

}
