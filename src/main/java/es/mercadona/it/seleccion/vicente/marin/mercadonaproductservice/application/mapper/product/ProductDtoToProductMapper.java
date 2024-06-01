package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.ProductDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Product;

public interface ProductDtoToProductMapper {

    Product mapProductDtoToProduct(ProductDTO productDTO);
}
