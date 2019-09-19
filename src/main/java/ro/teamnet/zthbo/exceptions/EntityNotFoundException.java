package ro.teamnet.zthbo.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class EntityNotFoundException extends RuntimeException {
    public String message;
    public static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
}
