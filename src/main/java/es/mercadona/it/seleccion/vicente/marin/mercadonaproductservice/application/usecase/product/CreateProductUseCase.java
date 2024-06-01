package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.ProductDTO;

public interface CreateProductUseCase {

    ProductDTO createProduct(ProductDTO productDTO);
}
