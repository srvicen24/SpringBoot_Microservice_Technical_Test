package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.ProductDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Product;

import java.util.List;

public interface ProductToProductDtoMapper {

    ProductDTO mapProductToProductDto(Product product);

    List<ProductDTO> mapProductListToProductDtoList(List<Product> list);
}
