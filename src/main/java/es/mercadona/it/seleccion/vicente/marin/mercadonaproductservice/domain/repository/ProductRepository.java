package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Ean;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.Product;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product.ProductId;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for the Product entity.
 * <p>
 * This interface defines the contract for a repository that manages Product entities.
 * It provides methods to find, save, delete, and update Products, as well as to find all Products.
 * </p>
 *
 * @author Vicente Marín Lorencio
 */
public interface ProductRepository {

    /**
     * Finds a Product by its EAN.
     *
     * @param ean {@link Ean} the EAN of the Product to find
     * @return {@link Product} the Product with the provided EAN
     */
    Optional<Product> findByEan(Ean ean);

    /**
     * Saves a Product.
     *
     * @param product {@link Product} the Product to save
     */
    Product save(Product product);

    /**
     * Deletes a Product by its EAN.
     *
     * @param ean {@link Ean} the EAN of the Product to delete
     */
    void deleteByEan(Ean ean);

    /**
     * Updates a Product.
     *
     * @param ean     {@link Ean} the EAN of the Product to update
     * @param product {@link Product} the Product to update
     */
    Optional<Product> updateByEan(Ean ean, Product product);

    /**
     * Updates a Product by its ID.
     *
     * @param id      {@link ProductId} the ID of the Product to update
     * @param product {@link Product} the Product to update
     * @return {@link Product} the updated Product
     */
    Optional<Product> updateById(ProductId id, Product product);

    /**
     * Finds all Products.
     *
     * @return {@link List<Product>} a list of all Products
     */
    List<Product> findAll();

    /**
     * Finds a Product by its ID.
     *
     * @param id {@link ProductId} the ID of the Product to find
     * @return {@link Product} the Product with the provided ID
     */
    Optional<Product> findById(ProductId id);

    /**
     * Deletes a Product by its ID.
     *
     * @param id {@link ProductId} the ID of the Product to delete
     */
    void deleteById(ProductId id);
}
