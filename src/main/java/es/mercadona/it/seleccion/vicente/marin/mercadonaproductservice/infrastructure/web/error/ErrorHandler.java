package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.web.error;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.ValidatingProductException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.ValidatingSupplierException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.repository.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        ErrorMessage errorMessage = ErrorMessage.builder()
                                                .timestamp(Instant.now())
                                                .message(exception.getMessage())
                                                .status(HttpStatus.NOT_FOUND.toString())
                                                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler({ValidatingProductException.class, ValidatingSupplierException.class})
    public ResponseEntity<ErrorMessage> handleValidatingProductException(Exception exception) {
        log.error(exception.getMessage(), exception);
        ErrorMessage errorMessage = ErrorMessage.builder()
                                                .timestamp(Instant.now())
                                                .message(exception.getMessage())
                                                .status(HttpStatus.BAD_REQUEST.toString())
                                                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error(exception.getMessage(), exception);
        ErrorMessage errorMessage = ErrorMessage.builder()
                                                .timestamp(Instant.now())
                                                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                                                .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                                                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
