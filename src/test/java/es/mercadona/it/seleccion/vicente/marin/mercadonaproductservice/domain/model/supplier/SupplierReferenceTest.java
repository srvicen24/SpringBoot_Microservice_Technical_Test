package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.InvalidSupplierReferenceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SupplierReferenceTest {

    @Test
    void givenValidReference_whenCreateSupplierReference_thenSupplierReferenceIsCreated() {
        SupplierReference supplierReference = new SupplierReference("1234567");
        assertEquals("1234567", supplierReference.value());
    }

    @Test
    void givenNullReference_whenCreateSupplierReference_thenSupplierReferenceIsCreated() {
        assertThrows(InvalidSupplierReferenceException.class, () -> new SupplierReference(null));
    }

    @Test
    void givenEmptyReference_whenCreateSupplierReference_thenSupplierReferenceIsCreated() {
        assertThrows(InvalidSupplierReferenceException.class, () -> new SupplierReference(""));
    }

    @Test
    void givenShortReference_whenCreateSupplierReference_thenSupplierReferenceIsCreated() {
        assertThrows(InvalidSupplierReferenceException.class, () -> new SupplierReference("123456"));
    }

    @Test
    void givenLongReference_whenCreateSupplierReference_thenSupplierReferenceIsCreated() {
        assertThrows(InvalidSupplierReferenceException.class, () -> new SupplierReference("12345678"));
    }

    @Test
    void givenNonNumericReference_whenCreateSupplierReference_thenSupplierReferenceIsCreated() {
        assertThrows(InvalidSupplierReferenceException.class, () -> new SupplierReference("123456a"));
    }
}
