package cinimex.org.controllers;

import cinimex.org.exception.LogicException;
import cinimex.org.utils.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {
    private static final String DEFAULT_ERROR_MESSAGE = "При обработке произошла непредвиденная ошибка.";

    @ExceptionHandler(Exception.class)
    public Response handleException(HttpServletRequest request, Exception exception) {
        return Response.fail(DEFAULT_ERROR_MESSAGE);
    }

    @ExceptionHandler(LogicException.class)
    public Response handleLogicException(HttpServletRequest request, LogicException exception) {
        return Response.fail(exception.getMessage());
    }

}
