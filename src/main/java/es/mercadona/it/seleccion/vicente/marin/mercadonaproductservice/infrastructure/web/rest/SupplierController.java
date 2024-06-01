package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.web.rest;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.SupplierDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.supplier.CreateSupplierUseCase;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.supplier.DeleteSupplierUseCase;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.supplier.GetSupplierUseCase;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.supplier.UpdateSupplierUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@AllArgsConstructor
public class SupplierController {

    private final CreateSupplierUseCase createSupplierUseCase;
    private final DeleteSupplierUseCase deleteSupplierUseCase;
    private final GetSupplierUseCase getSupplierUseCase;
    private final UpdateSupplierUseCase updateSupplierUseCase;

    @GetMapping("/id/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable Long id) {
        SupplierDTO supplier = getSupplierUseCase.getSupplierById(id);
        return ResponseEntity.ok(supplier);
    }

    @GetMapping("/reference/{reference}")
    public ResponseEntity<SupplierDTO> getSupplierByReference(@PathVariable String reference) {
        SupplierDTO supplier = getSupplierUseCase.getSupplierByReference(reference);
        return ResponseEntity.ok(supplier);
    }

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        List<SupplierDTO> suppliers = getSupplierUseCase.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        SupplierDTO savedSupplier = createSupplierUseCase.createSupplier(supplierDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSupplier);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteSupplierById(@PathVariable Long id) {
        deleteSupplierUseCase.deleteSupplierById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/reference/{reference}")
    public ResponseEntity<Void> deleteSupplierByReference(@PathVariable String reference) {
        deleteSupplierUseCase.deleteSupplierByReference(reference);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<SupplierDTO> updateSupplierById(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) {
        SupplierDTO updatedSupplier = updateSupplierUseCase.updateSupplierById(id, supplierDTO);
        return ResponseEntity.ok(updatedSupplier);
    }

    @PutMapping("/reference/{reference}")
    public ResponseEntity<SupplierDTO> updateSupplierByReference(@PathVariable String reference,
                                                                 @RequestBody SupplierDTO supplierDTO) {
        SupplierDTO updatedSupplier = updateSupplierUseCase.updateSupplierByReference(reference, supplierDTO);
        return ResponseEntity.ok(updatedSupplier);
    }

}
