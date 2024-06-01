package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.service.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.SupplierDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.supplier.SupplierToSupplierDtoMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.repository.SupplierNotFoundException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.Supplier;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierId;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierReference;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetSupplierUseCaseImplTest {

    @InjectMocks
    private GetSupplierUseCaseImpl getSupplierUseCaseImpl;

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private SupplierToSupplierDtoMapper supplierToSupplierDtoMapper;

    private SupplierDTO supplierDTO;
    private SupplierReference supplierReference;
    private SupplierId supplierId;
    private Supplier supplier;

    @BeforeEach
    void setUp() {
        getSupplierUseCaseImpl = spy(new GetSupplierUseCaseImpl(supplierRepository, supplierToSupplierDtoMapper));
        MockitoAnnotations.openMocks(this);
        supplierDTO = SupplierDTO.builder().name("Supplier").reference("Reference").build();
        supplierReference = new SupplierReference("1234567");
        supplierId = new SupplierId(1L);
        supplier = new Supplier(supplierId, supplierReference, "Supplier");
        when(supplierToSupplierDtoMapper.mapSupplierToSupplierDto(supplier)).thenReturn(supplierDTO);
        when(supplierToSupplierDtoMapper.mapSupplierListToSupplierDtoList(anyList())).thenReturn(List.of(supplierDTO));
    }

    @Test
    void givenValidSupplierReference_whenGetSupplier_thenSupplierIsReturned() {
        when(supplierRepository.findByReference(supplierReference)).thenReturn(Optional.of(supplier));
        SupplierDTO supplier = getSupplierUseCaseImpl.getSupplierByReference(supplierReference.value());
        verify(supplierRepository, times(1)).findByReference(supplierReference);
        assertNotNull(supplier);
    }

    @Test
    void givenInvalidSupplierReference_whenGetSupplier_thenThrowSupplierNotFoundException() {
        when(supplierRepository.findByReference(supplierReference)).thenThrow(SupplierNotFoundException.class);
        assertThrows(SupplierNotFoundException.class,
                () -> getSupplierUseCaseImpl.getSupplierByReference(supplierReference.value()));
    }

    @Test
    void givenValidSupplierId_whenGetSupplier_thenSupplierIsReturned() {
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));
        SupplierDTO supplier = getSupplierUseCaseImpl.getSupplierById(supplierId.value());
        verify(supplierRepository, times(1)).findById(supplierId);
        assertNotNull(supplier);
    }

    @Test
    void givenInvalidSupplierId_whenGetSupplier_thenThrowSupplierNotFoundException() {
        when(supplierRepository.findById(supplierId)).thenThrow(SupplierNotFoundException.class);
        assertThrows(SupplierNotFoundException.class, () -> getSupplierUseCaseImpl.getSupplierById(supplierId.value()));
    }

    @Test
    void givenGetSupplierUseCaseImpl_whenGetAllSuppliers_thenAllSuppliersAreReturned() {
        when(supplierRepository.findAll()).thenReturn(List.of(supplier));
        List<SupplierDTO> suppliers = getSupplierUseCaseImpl.getAllSuppliers();
        verify(supplierRepository, times(1)).findAll();
        assertFalse(suppliers.isEmpty());
    }

}
