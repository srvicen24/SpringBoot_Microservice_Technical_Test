package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.repository;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Ean;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Product;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.ProductId;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.ProductRepository;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.entity.ProductEntity;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.mapper.ProductEntityMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.persistence.JpaProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;
    private final ProductEntityMapper productEntityMapper;

    @Override
    public Optional<Product> findByEan(Ean ean) {
        Optional<ProductEntity> product = jpaProductRepository.findByEan(ean.value());
        return product.map(this::getDomain);
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productEntityMapper.toEntity(product);
        ProductEntity savedProduct = jpaProductRepository.save(productEntity);
        return this.getDomain(savedProduct);
    }

    @Override
    @Transactional
    public void deleteByEan(Ean ean) {
        jpaProductRepository.deleteByEan(ean.value());
    }

    @Override
    public Optional<Product> updateByEan(Ean ean, Product product) {
        Optional<ProductEntity> productEntity = jpaProductRepository.findByEan(ean.value());
        return updateProduct(product, productEntity);
    }


    @Override
    public Optional<Product> updateById(ProductId id, Product product) {
        Optional<ProductEntity> productEntity = jpaProductRepository.findById(id.value());
        return updateProduct(product, productEntity);
    }

    private Optional<Product> updateProduct(Product product, Optional<ProductEntity> productEntity) {
        if (productEntity.isEmpty()) {
            return Optional.empty();
        }
        ProductEntity updatedProduct = productEntityMapper.toEntity(product);
        updatedProduct.setId(productEntity.get().getId());
        ProductEntity savedProduct = jpaProductRepository.save(updatedProduct);
        return Optional.of(this.getDomain(savedProduct));
    }

    @Override
    public List<Product> findAll() {
        return jpaProductRepository.findAll().stream().map(this::getDomain).toList();
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        Optional<ProductEntity> product = jpaProductRepository.findById(id.value());
        return product.map(this::getDomain);
    }

    private Product getDomain(ProductEntity productEntity) {
        return productEntityMapper.toDomainBuilder(productEntity).build();
    }

    @Override
    public void deleteById(ProductId id) {
        jpaProductRepository.deleteById(id.value());
    }
}
