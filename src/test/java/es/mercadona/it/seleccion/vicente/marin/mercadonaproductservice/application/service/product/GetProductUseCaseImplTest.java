package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.service.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.ProductDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.product.ProductToProductDtoMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.repository.ProductNotFoundException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Ean;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Product;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.ProductId;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.ProductRepository;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetProductUseCaseImplTest {

    @InjectMocks
    private GetProductUseCaseImpl getProductUseCase;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductToProductDtoMapper productToProductDtoMapper;

    private Ean ean;
    private ProductId productId;
    private ProductDTO productDTO;
    private Product product;

    @BeforeEach
    void setUp() {
        getProductUseCase = spy(new GetProductUseCaseImpl(productRepository, productToProductDtoMapper));
        MockitoAnnotations.openMocks(this);
        ean = new Ean("1234567890123");
        productId = new ProductId(1L);
        productDTO = ProductDTO.builder()
                               .ean("1234567890123")
                               .name("Product")
                               .description("Description")
                               .price(1.0)
                               .build();
        SupplierRepository supplierRepository = spy(SupplierRepository.class);
        product = Product.builder()
                         .id(1L)
                         .ean("8437008123456")
                         .name("Product")
                         .description("Description")
                         .price(1.0)
                         .supplierRepository(supplierRepository)
                         .build();
        when(productToProductDtoMapper.mapProductToProductDto(any())).thenReturn(productDTO);
        when(productToProductDtoMapper.mapProductListToProductDtoList(anyList())).thenReturn(List.of(productDTO));
    }

    @Test
    void givenValidEan_whenGetProduct_thenProductIsReturned() {
        when(productRepository.findByEan(ean)).thenReturn(Optional.of(product));
        ProductDTO product = getProductUseCase.getProductByEan(ean.value());
        verify(productRepository, times(1)).findByEan(ean);
        assertNotNull(product);
    }

    @Test
    void givenInvalidEan_whenGetProduct_thenThrowProductNotFoundException() {
        when(productRepository.findByEan(ean)).thenThrow(ProductNotFoundException.class);
        assertThrows(ProductNotFoundException.class, () -> getProductUseCase.getProductByEan(ean.value()));
    }

    @Test
    void givenValidProductId_whenGetProduct_thenProductIsReturned() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        ProductDTO product = getProductUseCase.getProductById(productId.value());
        verify(productRepository, times(1)).findById(productId);
        assertNotNull(product);
    }

    @Test
    void givenInvalidProductId_whenGetProduct_thenThrowProductNotFoundException() {
        when(productRepository.findById(productId)).thenThrow(ProductNotFoundException.class);
        assertThrows(ProductNotFoundException.class, () -> getProductUseCase.getProductById(productId.value()));
    }

    @Test
    void givenGetProductUseCaseImpl_whenGetAllProducts_thenAllProductsAreReturned() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        List<ProductDTO> products = getProductUseCase.getAllProducts();
        verify(productRepository, times(1)).findAll();
        assertFalse(products.isEmpty());
    }
}
