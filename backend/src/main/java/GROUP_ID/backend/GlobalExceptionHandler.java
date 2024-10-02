package GROUP_ID.backend;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import GROUP_ID.backend.exceptions.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GlobalExceptionResponse handleNotFoundException(NotFoundException exception) {
        return new GlobalExceptionResponse()
            .setHttpStatus(HttpStatus.NOT_FOUND)
            .setMessage(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GlobalExceptionResponse handleOtherException(Exception exception) {
        return new GlobalExceptionResponse()
            .setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
            .setMessage("An error occured: " + exception.getMessage());
    }

    // Send not found resource requests to the frontend and let it deal with it

    @ExceptionHandler(NoResourceFoundException.class)
    public ModelAndView handleNoResourceFoundException(Exception exception) {
        return new ModelAndView("forward:/index.html");
    }

}
