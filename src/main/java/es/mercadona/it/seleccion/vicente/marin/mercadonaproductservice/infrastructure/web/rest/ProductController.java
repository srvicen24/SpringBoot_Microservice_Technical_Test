package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.web.rest;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.ProductDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.product.CreateProductUseCase;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.product.DeleteProductUseCase;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.product.GetProductUseCase;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.product.UpdateProductUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    @GetMapping("/ean/{ean}")
    public ResponseEntity<ProductDTO> getProductByEan(@PathVariable String ean) {
        ProductDTO product = getProductUseCase.getProductByEan(ean);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/id/{id}")

    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = getProductUseCase.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = getProductUseCase.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO savedProduct = createProductUseCase.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @DeleteMapping("/ean/{ean}")
    public ResponseEntity<Void> deleteProductByEan(@PathVariable String ean) {
        deleteProductUseCase.deleteProductByEan(ean);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        deleteProductUseCase.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/ean/{ean}")
    public ResponseEntity<ProductDTO> updateProductByEan(@PathVariable String ean, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = updateProductUseCase.updateProductByEan(ean, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<ProductDTO> updateProductById(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = updateProductUseCase.updateProductById(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }
}
