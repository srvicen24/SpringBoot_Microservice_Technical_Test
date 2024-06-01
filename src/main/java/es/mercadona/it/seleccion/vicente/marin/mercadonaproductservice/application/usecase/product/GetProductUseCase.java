package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.ProductDTO;

import java.util.List;

public interface GetProductUseCase {
    ProductDTO getProductByEan(String value);

    ProductDTO getProductById(Long value);

    List<ProductDTO> getAllProducts();
}
