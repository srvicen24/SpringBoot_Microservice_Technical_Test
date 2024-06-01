package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.service.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.product.DeleteProductUseCase;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Ean;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.ProductId;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private ProductRepository productRepository;

    @CacheEvict(value = "products", key = "#ean")
    @Override
    public void deleteProductByEan(String ean) {
        productRepository.deleteByEan(new Ean(ean));
    }

    @CacheEvict(value = "products", key = "#id")
    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(new ProductId(id));
    }
}
