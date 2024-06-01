package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.service.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.ProductDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.product.ProductToProductDtoMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.product.GetProductUseCase;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.repository.ProductNotFoundException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Ean;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Product;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.ProductId;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetProductUseCaseImpl implements GetProductUseCase {

    private ProductRepository productRepository;

    private ProductToProductDtoMapper productToProductDtoMapper;

    @Cacheable(value = "products", key = "#ean")
    @Override
    public ProductDTO getProductByEan(String ean) {
        Optional<Product> product = productRepository.findByEan(new Ean(ean));
        if (product.isEmpty()) {
            throw new ProductNotFoundException(ean);
        }
        return productToProductDtoMapper.mapProductToProductDto(product.get());
    }

    @Cacheable(value = "products", key = "#id")
    @Override
    public ProductDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(new ProductId(id));
        if (product.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        return productToProductDtoMapper.mapProductToProductDto(product.get());
    }

    @Cacheable(value = "products", key = "#root.methodName")
    @Override
    public List<ProductDTO> getAllProducts() {
        return productToProductDtoMapper.mapProductListToProductDtoList(productRepository.findAll());
    }
}
