package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.service.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Ean;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.ProductId;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class DeleteProductUseCaseImplTest {

    @InjectMocks
    private DeleteProductUseCaseImpl deleteProductUseCaseImpl;

    @Mock
    private ProductRepository productRepository;

    private Ean ean;
    private ProductId productId;

    @BeforeEach
    void setUp() {
        deleteProductUseCaseImpl = spy(new DeleteProductUseCaseImpl(productRepository));
        MockitoAnnotations.openMocks(this);
        doNothing().when(productRepository).deleteByEan(any());
        doNothing().when(productRepository).deleteById(any());
        ean = new Ean("1234567890123");
        productId = new ProductId(1L);
    }

    @Test
    void givenValidEan_whenDeleteProduct_thenProductIsDeleted() {
        deleteProductUseCaseImpl.deleteProductByEan(ean.value());
        verify(productRepository, times(1)).deleteByEan(ean);
    }

    @Test
    void givenValidProductId_whenDeleteProduct_thenProductIsDeleted() {
        deleteProductUseCaseImpl.deleteProductById(productId.value());
        verify(productRepository, times(1)).deleteById(productId);
    }
}
