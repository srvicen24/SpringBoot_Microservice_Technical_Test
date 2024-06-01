package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.repository.SupplierNotFoundException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.Supplier;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierReference;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.SupplierRepository;
import lombok.NoArgsConstructor;

/**
 * Builder for the Product class.
 * <p>
 * This builder is used to create a new instance of Product using the builder pattern.
 * Each instance of this builder can be used to create a new Product with the provided details.
 * </p>
 *
 * @author Vicente Marín Lorencio
 */
@NoArgsConstructor
public final class ProductBuilder {

    private Long id;
    private String ean;
    private String name;
    private String description;
    private double price;
    private String supplierId;
    private SupplierRepository supplierRepository;

    /**
     * Sets the id of the product to be built.
     *
     * @param id the id of the product
     * @return this builder
     */
    public ProductBuilder id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the EAN of the product to be built.
     *
     * @param ean the EAN of the product
     * @return this builder
     */
    public ProductBuilder ean(String ean) {
        this.ean = ean;
        return this;
    }

    /**
     * Sets the name of the product to be built.
     *
     * @param name the name of the product
     * @return this builder
     */
    public ProductBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the description of the product to be built.
     *
     * @param description the description of the product
     * @return this builder
     */
    public ProductBuilder description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the price of the product to be built.
     *
     * @param price the price of the product
     * @return this builder
     */
    public ProductBuilder price(double price) {
        this.price = price;
        return this;
    }

    /**
     * Sets the supplier repository to find the supplier of the product to be built.
     *
     * @param supplierRepository the supplier repository
     * @return this builder
     */
    public ProductBuilder supplierRepository(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
        return this;
    }

    /**
     * Builds a new instance of Product with the provided details.
     * <p>
     * If the supplier cannot be found in the supplier repository, a SupplierNotFoundException is thrown.
     * </p>
     *
     * @return a new instance of Product
     * @throws SupplierNotFoundException if the supplier cannot be found in the supplier repository
     */
    public Product build() {
        ProductId newProductId = this.id == null ? null : new ProductId(this.id);
        Ean newEan = new Ean(this.ean);
        SupplierReference supplierReference = newEan.getSupplierReference();
        Supplier newSupplier = supplierRepository.findByReference(supplierReference)
                                                 .orElseThrow(() -> new SupplierNotFoundException(supplierReference.value()));
        ProductCode newProductCode = new ProductCode(newEan.getProductCode());
        Destination newDestination = Destination.fromCode(newEan.getDestinationCode());
        Name newName = new Name(this.name);
        Description newDescription = new Description(this.description);
        Price newPrice = new Price(this.price);
        return new Product(newProductId, newEan, newSupplier, newProductCode, newDestination, newName, newDescription
                , newPrice);
    }
}
