package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.InvalidProductCodeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductCodeTest {

    @Test
    void givenValidCode_whenCreateProductCode_thenProductCodeIsCreated() {
        ProductCode productCode = new ProductCode("12345");
        assertEquals("12345", productCode.value());
    }

    @Test
    void givenShortCode_whenCreateProductCode_thenInvalidProductCodeExceptionIsThrown() {
        assertThrows(InvalidProductCodeException.class, () -> new ProductCode("1234"));
    }

    @Test
    void givenLongCode_whenCreateProductCode_thenInvalidProductCodeExceptionIsThrown() {
        assertThrows(InvalidProductCodeException.class, () -> new ProductCode("123456"));
    }

    @Test
    void givenNullCode_whenCreateProductCode_thenInvalidProductCodeExceptionIsThrown() {
        assertThrows(InvalidProductCodeException.class, () -> new ProductCode(null));
    }

    @Test
    void givenNonNumericCode_whenCreateProductCode_thenInvalidProductCodeExceptionIsThrown() {
        assertThrows(InvalidProductCodeException.class, () -> new ProductCode("1234a"));
    }

    @Test
    void givenEmptyCode_whenCreateProductCode_thenInvalidProductCodeExceptionIsThrown() {
        assertThrows(InvalidProductCodeException.class, () -> new ProductCode(""));
    }

}
