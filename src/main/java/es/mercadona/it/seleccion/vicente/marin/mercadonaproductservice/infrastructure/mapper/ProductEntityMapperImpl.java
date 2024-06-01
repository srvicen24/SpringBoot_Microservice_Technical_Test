package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.mapper;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Product;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.ProductBuilder;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.SupplierRepository;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.entity.ProductEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductEntityMapperImpl implements ProductEntityMapper {

    private final SupplierRepository supplierRepository;

    @Override
    public ProductBuilder toDomainBuilder(ProductEntity productEntity) {
        return Product.builder()
                      .id(productEntity.getId())
                      .ean(productEntity.getEan())
                      .name(productEntity.getName())
                      .description(productEntity.getDescription())
                      .price(productEntity.getPrice())
                      .supplierRepository(supplierRepository);
    }

    @Override
    public ProductEntity toEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.productId());
        productEntity.setEan(product.ean());
        productEntity.setName(product.name());
        productEntity.setDescription(product.description());
        productEntity.setPrice(product.price());
        return productEntity;
    }
}
