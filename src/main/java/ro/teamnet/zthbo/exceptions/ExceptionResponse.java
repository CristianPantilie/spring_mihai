package ro.teamnet.zthbo.exceptions;

import lombok.Builder;
import org.springframework.web.context.request.WebRequest;

@Builder
public class ExceptionResponse {

    private RuntimeException exception;
    private WebRequest requestBody;

}
