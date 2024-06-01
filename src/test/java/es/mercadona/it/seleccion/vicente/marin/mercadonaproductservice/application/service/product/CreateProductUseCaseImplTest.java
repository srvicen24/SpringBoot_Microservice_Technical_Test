package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.service.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.ProductDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.product.ProductDtoToProductMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.product.ProductToProductDtoMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.ValidatingProductException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Product;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateProductUseCaseImplTest {

    @InjectMocks
    private CreateProductUseCaseImpl createProductUseCaseImpl;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductDtoToProductMapper productDtoToProductMapper;

    @Mock
    private ProductToProductDtoMapper productToProductDtoMapper;

    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        createProductUseCaseImpl = spy(new CreateProductUseCaseImpl(productRepository, productDtoToProductMapper,
                productToProductDtoMapper));
        MockitoAnnotations.openMocks(this);
        productDTO = ProductDTO.builder()
                               .ean("1234567890123")
                               .name("Product")
                               .description("Description")
                               .price(1.0)
                               .build();
        Product product = mock(Product.class);
        when(productDtoToProductMapper.mapProductDtoToProduct(any())).thenReturn(product);
        when(productRepository.save(any())).thenReturn(product);
        when(productToProductDtoMapper.mapProductToProductDto(any())).thenReturn(productDTO);
    }

    @Test
    void givenValidProductDto_whenCreateProduct_thenProductIsCreated() {
        createProductUseCaseImpl.createProduct(productDTO);
        verify(productRepository, times(1)).save(any());
    }

    @Test
    void givenNullProductDto_whenCreateProduct_thenThrowValidatingProductException() {
        when(productDtoToProductMapper.mapProductDtoToProduct(null)).thenThrow(ValidatingProductException.class);
        assertThrows(ValidatingProductException.class, () -> createProductUseCaseImpl.createProduct(null));
    }

}
