package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.mapper;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Product;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.ProductBuilder;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.entity.ProductEntity;

public interface ProductEntityMapper {

    ProductBuilder toDomainBuilder(ProductEntity productEntity);

    ProductEntity toEntity(Product product);
}
