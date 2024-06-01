package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.ProductDTO;

public interface UpdateProductUseCase {
    ProductDTO updateProductById(Long id, ProductDTO productDTO);

    ProductDTO updateProductByEan(String ean, ProductDTO productDTO);
}
