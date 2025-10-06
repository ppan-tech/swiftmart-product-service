package com.swiftmart.swmartproductserv.advices;

import com.swiftmart.swmartproductserv.dtos.ProductNotFoundErrorDTO;
import com.swiftmart.swmartproductserv.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/*
    Global Exception Handler to manage common exceptions across the application.
*/

@Slf4j // This creates 'private static final Logger log = LoggerFactory.getLogger(MyApiController.class);'
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles the common NullPointerException, for robustness of the application ,and avoids exposing stack traces to clients.
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500 Status
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        // Log the exception details (this is important! for debugging)
        log.error("An unexpected null pointer exception occurred", ex);

        // Returns a clean, user-friendly error response
        return new ResponseEntity<>("An internal server error occurred due to missing data.",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundErrorDTO> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        //code to handle the exception & beatify the response
        ProductNotFoundErrorDTO errorDTO = new ProductNotFoundErrorDTO();
        errorDTO.setMessage(productNotFoundException.getMessage());

        return new ResponseEntity<>(errorDTO, HttpStatusCode.valueOf(404));
    }
}
