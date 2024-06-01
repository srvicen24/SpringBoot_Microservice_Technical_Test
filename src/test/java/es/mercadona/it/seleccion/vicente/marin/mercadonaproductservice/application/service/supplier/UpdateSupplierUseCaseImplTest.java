package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.service.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.SupplierDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.supplier.SupplierDtoToSupplierMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.supplier.SupplierToSupplierDtoMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.ValidatingSupplierException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.Supplier;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateSupplierUseCaseImplTest {

    @InjectMocks
    private UpdateSupplierUseCaseImpl updateSupplierUseCaseImpl;

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private SupplierDtoToSupplierMapper supplierDtoToSupplierMapper;

    @Mock
    private SupplierToSupplierDtoMapper supplierToSupplierDtoMapper;

    private SupplierDTO supplierDTO;

    @BeforeEach
    void setUp() {
        updateSupplierUseCaseImpl = spy(new UpdateSupplierUseCaseImpl(supplierRepository, supplierDtoToSupplierMapper
                , supplierToSupplierDtoMapper));
        MockitoAnnotations.openMocks(this);
        supplierDTO = SupplierDTO.builder().name("Supplier").reference("Reference").build();
        when(supplierDtoToSupplierMapper.mapSupplierDtoToSupplier(any())).thenReturn(null);
        when(supplierToSupplierDtoMapper.mapSupplierToSupplierDto(any())).thenReturn(supplierDTO);
    }

    @Test
    void givenValidSupplierDto_whenUpdateSupplier_thenSupplierIsUpdated() {
        when(supplierRepository.updateById(any(), any())).thenReturn(Optional.of(mock(Supplier.class)));
        updateSupplierUseCaseImpl.updateSupplierById(1L, supplierDTO);
        verify(supplierRepository, times(1)).updateById(any(), any());
    }

    @Test
    void givenNullSupplierDto_whenUpdateSupplier_thenThrowValidatingSupplierException() {
        assertThrows(ValidatingSupplierException.class, () -> updateSupplierUseCaseImpl.updateSupplierById(1L, null));
    }

}
