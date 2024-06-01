package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.service.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.ProductDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.product.ProductDtoToProductMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.product.ProductToProductDtoMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.product.CreateProductUseCase;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Product;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private ProductRepository productRepository;
    private ProductDtoToProductMapper productDtoToProductMapper;
    private ProductToProductDtoMapper productToProductDtoMapper;

    @CachePut(value = "products", key = "#productDTO.ean")
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productDtoToProductMapper.mapProductDtoToProduct(productDTO);
        Product savedProduct = productRepository.save(product);
        return productToProductDtoMapper.mapProductToProductDto(savedProduct);
    }

}
