package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.InvalidSupplierException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SupplierTest {

    private SupplierId supplierId;
    private SupplierReference reference;
    private String name;

    @BeforeEach
    public void setUp() {
        supplierId = new SupplierId(1L);
        reference = new SupplierReference("1234567");
        name = "Supplier Name";
    }

    @Test
    void givenValidArgs_whenCreateSupplier_thenSupplierIsCreated() {
        Supplier supplier = new Supplier(supplierId, reference, name);
        assertEquals(1L, supplier.supplierId().value());
        assertEquals("1234567", supplier.reference().value());
        assertEquals("Supplier Name", supplier.name());
    }

    @Test
    void givenNullName_whenCreateSupplier_thenThrowInvalidSupplierException() {
        assertThrows(InvalidSupplierException.class, () -> new Supplier(supplierId, reference, null));
    }

    @Test
    void givenEmptyName_whenCreateSupplier_thenThrowInvalidSupplierException() {
        assertThrows(InvalidSupplierException.class, () -> new Supplier(supplierId, reference, ""));
    }

}
