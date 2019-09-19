package ro.teamnet.zthbo.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ro.teamnet.zthbo.controller.PersonController;
import ro.teamnet.zthbo.responseentity.GenericResponseEntity;
import ro.teamnet.zthbo.responseentity.PersonResponseEntity;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(basePackageClasses = PersonController.class)//(basePackages = "ro.teamnet.zthbo.controller")
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EmptyResultDataAccessException.class, EntityNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(RuntimeException ex, WebRequest request) {
//        return new ResponseEntity<>(PersonResponseEntity.builder().exception(ex).build(), HttpStatus.NOT_FOUND);
        if (ex instanceof EmptyResultDataAccessException) {
            return handleEmptyResultDataAccessException((EmptyResultDataAccessException) ex);
        }
        else if (ex instanceof EntityNotFoundException) {
            return handleEntityNotFoundException((EntityNotFoundException) ex);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
