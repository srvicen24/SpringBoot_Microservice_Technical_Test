package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.ProductDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.ValidatingProductException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Product;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductDtoToProductMapperImpl implements ProductDtoToProductMapper {

    private final SupplierRepository supplierRepository;

    public Product mapProductDtoToProduct(ProductDTO productDTO) {
        validateProduct(productDTO);
        return buildProduct(productDTO);
    }

    private Product buildProduct(ProductDTO productDTO) {
        return Product.builder()
                      .id(productDTO.id())
                      .ean(productDTO.ean())
                      .name(productDTO.name())
                      .description(productDTO.description())
                      .price(productDTO.price())
                      .supplierRepository(supplierRepository)
                      .build();
    }

    private void validateProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            throw new ValidatingProductException("ProductDTO is null");
        }
    }
}
