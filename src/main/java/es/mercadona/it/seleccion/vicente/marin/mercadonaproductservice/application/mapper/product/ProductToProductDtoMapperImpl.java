package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.ProductDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.supplier.SupplierToSupplierDtoMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.ValidatingProductException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductToProductDtoMapperImpl implements ProductToProductDtoMapper {

    private final SupplierToSupplierDtoMapper supplierToSupplierDtoMapper;

    @Override
    public ProductDTO mapProductToProductDto(Product product) {
        validateProduct(product);
        return buildProductDTO(product);
    }

    @Override
    public List<ProductDTO> mapProductListToProductDtoList(List<Product> list) {
        return list.stream().map(this::buildProductDTO).toList();
    }

    private void validateProduct(Product product) {
        if (product == null) {
            throw new ValidatingProductException("Product is null");
        }
    }

    private ProductDTO buildProductDTO(Product product) {
        return ProductDTO.builder()
                         .id(product.productId())
                         .ean(product.ean())
                         .name(product.name())
                         .description(product.description())
                         .price(product.price())
                         .productCode(product.productCode())
                         .Destination(product.destination().getDescription())
                         .supplierDTO(supplierToSupplierDtoMapper.mapSupplierToSupplierDto(product.supplier()))
                         .build();

    }
}
