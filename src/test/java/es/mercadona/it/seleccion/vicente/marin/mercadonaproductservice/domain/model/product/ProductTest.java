package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.*;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.Supplier;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierId;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierReference;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class ProductTest {

    @Mock
    private SupplierRepository supplierRepository;

    private Long id;
    private String ean;
    private String name;
    private String description;
    private double price;

    @BeforeEach
    public void setUp() {
        id = 1L;
        ean = "1234567890123";
        name = "product";
        description = "product description";
        price = 1.0;
        MockitoAnnotations.openMocks(this);
        SupplierId supplierId = new SupplierId(1L);
        SupplierReference reference = new SupplierReference("1234567");
        when(supplierRepository.findByReference(any())).thenReturn(Optional.of(new Supplier(supplierId, reference,
                "supplier")));
    }

    @Test
    void givenZeroId_whenBuildProduct_thenThrowInvalidIdException() {
        id = 0L;

        assertThrows(InvalidIdException.class, () -> Product.builder()
                                                            .id(id)
                                                            .ean(ean)
                                                            .name(name)
                                                            .description(description)
                                                            .price(price)
                                                            .supplierRepository(supplierRepository)
                                                            .build());
    }

    @Test
    void givenNegativeId_whenBuildProduct_thenThrowInvalidIdException() {
        id = -1L;

        assertThrows(InvalidIdException.class, () -> Product.builder()
                                                            .id(id)
                                                            .ean(ean)
                                                            .name(name)
                                                            .description(description)
                                                            .price(price)
                                                            .supplierRepository(supplierRepository)
                                                            .build());
    }

    @Test
    void givenValidEan_whenBuildProduct_thenProductIsCreated() {

        Product product = Product.builder()
                                 .id(id)
                                 .ean(ean)
                                 .name(name)
                                 .description(description)
                                 .price(price)
                                 .supplierRepository(supplierRepository)
                                 .build();

        assertEquals(ean, product.ean());
        assertEquals(name, product.name());
        assertEquals(description, product.description());
        assertEquals(price, product.price());
        assertEquals("supplier", product.supplier().name());
        assertEquals("1234567", product.supplier().reference().value());
        assertEquals("89012", product.productCode());
    }

    @Test
    void givenShortEan_whenBuildProduct_thenThrowInvalidEanException() {
        ean = "123456789";

        assertThrows(InvalidEanException.class, () -> Product.builder()
                                                             .id(id)
                                                             .ean(ean)
                                                             .name(name)
                                                             .description(description)
                                                             .price(price)
                                                             .supplierRepository(supplierRepository)
                                                             .build());
    }

    @Test
    void givenLongEan_whenBuildProduct_thenThrowInvalidEanException() {
        ean = "12345678901234589418";

        assertThrows(InvalidEanException.class, () -> Product.builder()
                                                             .id(id)
                                                             .ean(ean)
                                                             .name(name)
                                                             .description(description)
                                                             .price(price)
                                                             .supplierRepository(supplierRepository)
                                                             .build());
    }

    @Test
    void givenNonNumericEan_whenBuildProduct_thenThrowInvalidEanException() {
        ean = "1234567vf012a";

        assertThrows(InvalidEanException.class, () -> Product.builder()
                                                             .id(id)
                                                             .ean(ean)
                                                             .name(name)
                                                             .description(description)
                                                             .price(price)
                                                             .supplierRepository(supplierRepository)
                                                             .build());
    }

    @Test
    void givenNullEan_whenBuildProduct_thenThrowInvalidEanException() {
        ean = null;

        assertThrows(InvalidEanException.class, () -> Product.builder()
                                                             .id(id)
                                                             .ean(ean)
                                                             .name(name)
                                                             .description(description)
                                                             .price(price)
                                                             .supplierRepository(supplierRepository)
                                                             .build());
    }

    @Test
    void givenEmptyEan_whenBuildProduct_thenThrowInvalidEanException() {
        ean = "";

        assertThrows(InvalidEanException.class, () -> Product.builder()
                                                             .id(id)
                                                             .ean(ean)
                                                             .name(name)
                                                             .description(description)
                                                             .price(price)
                                                             .supplierRepository(supplierRepository)
                                                             .build());
    }

    @Test
    void givenLastDigitSeven_whenBuildProduct_thenThrowInvalidDestinationException() {
        ean = "1234567890127";

        assertThrows(InvalidDestinationException.class, () -> Product.builder()
                                                                     .id(id)
                                                                     .ean(ean)
                                                                     .name(name)
                                                                     .description(description)
                                                                     .price(price)
                                                                     .supplierRepository(supplierRepository)
                                                                     .build());
    }

    @Test
    void givenNegativePrice_whenBuildProduct_thenThrowInvalidPriceException() {
        Double price = -1.0;

        assertThrows(InvalidPriceException.class, () -> Product.builder()
                                                               .id(id)
                                                               .ean(ean)
                                                               .name(name)
                                                               .description(description)
                                                               .price(price)
                                                               .supplierRepository(supplierRepository)
                                                               .build());
    }

    @Test
    void givenZeroPrice_whenBuildProduct_thenThrowInvalidPriceException() {
        Double price = 0.0;

        assertThrows(InvalidPriceException.class, () -> Product.builder()
                                                               .id(id)
                                                               .ean(ean)
                                                               .name(name)
                                                               .description(description)
                                                               .price(price)
                                                               .supplierRepository(supplierRepository)
                                                               .build());
    }

    @Test
    void givenEmptyName_whenBuildProduct_thenThrowEmptyNameException() {
        String name = "";

        assertThrows(EmptyNameException.class, () -> Product.builder()
                                                            .id(id)
                                                            .ean(ean)
                                                            .name(name)
                                                            .description(description)
                                                            .price(price)
                                                            .supplierRepository(supplierRepository)
                                                            .build());
    }

    @Test
    void givenNullName_whenBuildProduct_thenThrowEmptyNameException() {
        String name = null;

        assertThrows(EmptyNameException.class, () -> Product.builder()
                                                            .id(id)
                                                            .ean(ean)
                                                            .name(name)
                                                            .description(description)
                                                            .price(price)
                                                            .supplierRepository(supplierRepository)
                                                            .build());
    }

    @Test
    void givenEmptyDescription_whenBuildProduct_thenProductIsCreated() {
        String description = "";

        Product product = Product.builder()
                                 .id(id)
                                 .ean(ean)
                                 .name(name)
                                 .description(description)
                                 .price(price)
                                 .supplierRepository(supplierRepository)
                                 .build();

        assertEquals(ean, product.ean());
        assertEquals(name, product.name());
        assertEquals(description, product.description());
        assertEquals(price, product.price());
        assertEquals("supplier", product.supplier().name());
        assertEquals("1234567", product.supplier().reference().value());
    }

    @Test
    void givenNullDescription_whenBuildProduct_thenProductIsCreated() {
        String description = null;

        Product product = Product.builder()
                                 .id(id)
                                 .ean(ean)
                                 .name(name)
                                 .description(description)
                                 .price(price)
                                 .supplierRepository(supplierRepository)
                                 .build();

        assertEquals(ean, product.ean());
        assertEquals(name, product.name());
        assertEquals(description, product.description());
        assertEquals(price, product.price());
        assertEquals("supplier", product.supplier().name());
        assertEquals("1234567", product.supplier().reference().value());
    }

    @Test
    void givenLastDigitZero_whenBuildProduct_thenDestinationIsColmenas() {
        ean = "1234567890120";

        Product product = Product.builder()
                                 .id(id)
                                 .ean(ean)
                                 .name(name)
                                 .description(description)
                                 .price(price)
                                 .supplierRepository(supplierRepository)
                                 .build();

        assertEquals(Destination.COLMENAS, product.destination());
    }

    @Test
    void givenLastDigitOne_whenBuildProduct_thenDestinationIsMercadonaEspana() {
        ean = "1234567890121";

        Product product = Product.builder()
                                 .id(id)
                                 .ean(ean)
                                 .name(name)
                                 .description(description)
                                 .price(price)
                                 .supplierRepository(supplierRepository)
                                 .build();

        assertEquals(Destination.MERCADONA_ESPANA, product.destination());
    }

    @Test
    void givenLastDigitSix_whenBuildProduct_thenDestinationIsMercadonaPortugal() {
        ean = "1234567890126";

        Product product = Product.builder()
                                 .id(id)
                                 .ean(ean)
                                 .name(name)
                                 .description(description)
                                 .price(price)
                                 .supplierRepository(supplierRepository)
                                 .build();

        assertEquals(Destination.MERCADONA_PORTUGAL, product.destination());
    }

    @Test
    void givenLastDigitEight_whenBuildProduct_thenDestinationIsAlmacenes() {
        ean = "1234567890128";

        Product product = Product.builder()
                                 .id(id)
                                 .ean(ean)
                                 .name(name)
                                 .description(description)
                                 .price(price)
                                 .supplierRepository(supplierRepository)
                                 .build();

        assertEquals(Destination.ALMACENES, product.destination());
    }

    @Test
    void givenLastDigitNine_whenBuildProduct_thenDestinationIsOficinasMercadona() {
        ean = "1234567890129";

        Product product = Product.builder()
                                 .id(id)
                                 .ean(ean)
                                 .name(name)
                                 .description(description)
                                 .price(price)
                                 .supplierRepository(supplierRepository)
                                 .build();

        assertEquals(Destination.OFICINAS_MERCADONA, product.destination());
    }

    @Test
    void givenFirstsDigits8437008_whenBuildProduct_thenSupplierIsMercadona() {
        ean = "8437008123456";
        supplierRepository = spy(SupplierRepository.class);

        Product product = Product.builder()
                                 .id(id)
                                 .ean(ean)
                                 .name(name)
                                 .description(description)
                                 .price(price)
                                 .supplierRepository(supplierRepository)
                                 .build();

        assertEquals("Hacendado", product.supplier().name());
        assertEquals("8437008", product.supplier().reference().value());
    }

}
