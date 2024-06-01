package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.Supplier;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierId;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierReference;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.util.Constant;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for the Supplier entity.
 * <p>
 * This interface defines the contract for a repository that manages Supplier entities.
 * It provides a method to find a Supplier by its reference.
 * </p>
 *
 * @author Vicente Marín Lorencio
 */
public interface SupplierRepository {

    /**
     * Finds a Supplier by its reference.
     * <p>
     * This method returns an Optional of Supplier. If a Supplier with the provided reference exists,
     * it is returned. Otherwise, an empty Optional is returned.
     * Currently, this method only returns a Supplier for the Mercadona supplier reference.
     * </p>
     *
     * @param reference {@link String} the reference of the Supplier to find
     * @return {@link Optional<Supplier>} the Supplier with the provided reference, or an empty Optional if no such
     * Supplier exists
     */
    default Optional<Supplier> findByReference(SupplierReference reference) {
        if (Constant.MERCADONA_SUPPLIER_REFERENCE.equals(reference.value())) {
            SupplierId supplierId = new SupplierId(1L);
            return Optional.of(new Supplier(supplierId, reference, "Hacendado"));
        }
        return Optional.empty();
    }

    /**
     * Saves a Supplier.
     * <p>
     * This method persists the provided Supplier entity into the database.
     * </p>
     *
     * @param supplier {@link Supplier} the Supplier entity to save
     */
    Supplier save(Supplier supplier);

    /**
     * Deletes a Supplier by its reference.
     * <p>
     * This method removes the Supplier entity with the provided reference from the database.
     * </p>
     *
     * @param reference {@link String} the reference of the Supplier to delete
     */
    void deleteByReference(SupplierReference reference);

    /**
     * Updates a Supplier.
     * <p>
     * This method updates the provided Supplier entity in the database.
     * </p>
     *
     * @param id       {@link SupplierId} the Supplier entity to update
     * @param supplier {@link Supplier} the Supplier entity to update
     */
    Optional<Supplier> updateById(SupplierId id, Supplier supplier);

    /**
     * Updates a Supplier.
     * <p>
     * This method updates the provided Supplier entity in the database.
     * </p>
     *
     * @param reference {@link SupplierReference} the Supplier entity to update
     * @param supplier  {@link Supplier} the Supplier entity to update
     */
    Optional<Supplier> updateByReference(SupplierReference reference, Supplier supplier);

    /**
     * Finds all Suppliers.
     * <p>
     * This method retrieves all Supplier entities from the database.
     * </p>
     *
     * @return {@link List<Supplier>} a list of all Supplier entities
     */
    List<Supplier> findAll();

    /**
     * Finds a Supplier by its ID.
     * <p>
     * This method retrieves the Supplier entity with the provided ID from the database.
     * </p>
     *
     * @param supplierId {@link SupplierId} the ID of the Supplier to find
     * @return {@link Optional<Supplier>} the Supplier with the provided ID, or an empty Optional if no such Supplier
     * exists
     */
    Optional<Supplier> findById(SupplierId supplierId);

    /**
     * Deletes a Supplier by its ID.
     * <p>
     * This method removes the Supplier entity with the provided ID from the database.
     * </p>
     *
     * @param supplierId {@link SupplierId} the ID of the Supplier to delete
     */
    void deleteById(SupplierId supplierId);
}
