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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateProductUseCaseImplTest {

    @InjectMocks
    private UpdateProductUseCaseImpl updateProductUseCaseImpl;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductDtoToProductMapper productDtoToProductMapper;

    @Mock
    private ProductToProductDtoMapper productToProductDtoMapper;

    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        updateProductUseCaseImpl = spy(new UpdateProductUseCaseImpl(productRepository, productDtoToProductMapper,
                productToProductDtoMapper));
        MockitoAnnotations.openMocks(this);
        productDTO = ProductDTO.builder()
                               .ean("1234567890123")
                               .name("Product")
                               .description("Description")
                               .price(1.0)
                               .build();
        when(productDtoToProductMapper.mapProductDtoToProduct(any())).thenReturn(null);
        when(productToProductDtoMapper.mapProductToProductDto(any())).thenReturn(productDTO);
    }

    @Test
    void givenValidProductDto_whenUpdateProduct_thenProductIsUpdated() {
        when(productRepository.updateById(any(), any())).thenReturn(Optional.of(mock(Product.class)));
        updateProductUseCaseImpl.updateProductById(1L, productDTO);
        verify(productRepository, times(1)).updateById(any(), any());
    }

    @Test
    void givenNullProductDto_whenUpdateProduct_thenThrowValidatingProductException() {
        assertThrows(ValidatingProductException.class, () -> updateProductUseCaseImpl.updateProductById(1L, null));
    }

}
