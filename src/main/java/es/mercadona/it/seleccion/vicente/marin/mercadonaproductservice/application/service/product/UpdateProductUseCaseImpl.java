package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.service.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.ProductDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.product.ProductDtoToProductMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.product.ProductToProductDtoMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.product.UpdateProductUseCase;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.ValidatingProductException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.repository.ProductNotFoundException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Ean;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Product;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.ProductId;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    private ProductRepository productRepository;
    private ProductDtoToProductMapper productDtoToProductMapper;
    private ProductToProductDtoMapper productToProductDtoMapper;

    @CachePut(value = "products", key = "#productDTO.ean")
    @Override
    public ProductDTO updateProductById(Long id, ProductDTO productDTO) {
        validateProduct(productDTO);
        Product product = productDtoToProductMapper.mapProductDtoToProduct(productDTO);
        ProductId productId = new ProductId(id);
        Optional<Product> updatedProduct = productRepository.updateById(productId, product);
        if (updatedProduct.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        return productToProductDtoMapper.mapProductToProductDto(updatedProduct.get());
    }

    @CachePut(value = "products", key = "#productDTO.ean")
    @Override
    public ProductDTO updateProductByEan(String ean, ProductDTO productDTO) {
        validateProduct(productDTO);
        Product product = productDtoToProductMapper.mapProductDtoToProduct(productDTO);
        Ean eanValue = new Ean(ean);
        Optional<Product> updatedProduct = productRepository.updateByEan(eanValue, product);
        if (updatedProduct.isEmpty()) {
            throw new ProductNotFoundException(ean);
        }
        return productToProductDtoMapper.mapProductToProductDto(updatedProduct.get());
    }

    private void validateProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            throw new ValidatingProductException("ProductDTO is null");
        }
    }
}
