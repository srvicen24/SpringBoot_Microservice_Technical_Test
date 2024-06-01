package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.product;

public interface DeleteProductUseCase {

    void deleteProductByEan(String ean);

    void deleteProductById(Long id);
}
