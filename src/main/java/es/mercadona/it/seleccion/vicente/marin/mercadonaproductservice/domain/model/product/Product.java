package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.product;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.Supplier;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Product {

    private final ProductId productId;
    private final Ean ean;
    private final Supplier supplier;
    private final ProductCode productCode;
    private final Destination destination;
    private Name name;
    private Description description;
    private Price price;

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public Long productId() {
        return productId != null ? productId.value() : null;
    }

    public String ean() {
        return ean.value();
    }

    public String productCode() {
        return productCode.value();
    }

    public String name() {
        return name.value();
    }

    public String description() {
        return description.value();
    }

    public Destination destination() {
        return destination;
    }

    public Supplier supplier() {
        return supplier;
    }

    public double price() {
        return price.value();
    }

    public void changeName(String name) {
        this.name = new Name(name);
    }

    public void changeDescription(String description) {
        this.description = new Description(description);
    }

    public void changePrice(double price) {
        this.price = new Price(price);
    }
}
