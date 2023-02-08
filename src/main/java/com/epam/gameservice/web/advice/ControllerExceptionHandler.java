package com.epam.gameservice.web.advice;

import com.epam.gameservice.business.exception.GameNotFoundException;
import com.epam.gameservice.business.exception.PlatformNotFoundException;
import com.epam.gameservice.web.dto.Response;
import com.epam.gameservice.web.dto.error.ErrorInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({PlatformNotFoundException.class, GameNotFoundException.class})
    @ResponseBody
    public Response<ErrorInfo> handleNotFoundException(HttpServletRequest req, Exception ex) {
        return Response.<ErrorInfo>builder()
                .errorInfos(singletonList(getErrorInfo(req, ex)))
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response<ErrorInfo> handleRequestBodyValidationException(HttpServletRequest req, MethodArgumentNotValidException ex) {
        List<ErrorInfo> errorInfos = ex.getBindingResult().getAllErrors().stream()
                .map(e -> new ErrorInfo(req.getRequestURL().toString(), e.getDefaultMessage())).toList();
        return Response.<ErrorInfo>builder()
                .errorInfos(errorInfos)
                .build();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response<ErrorInfo> handleInternalServerError(HttpServletRequest req, Exception ex) {
        return Response.<ErrorInfo>builder()
                .errorInfos(singletonList(getErrorInfo(req, ex)))
                .build();
    }

    private ErrorInfo getErrorInfo(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURL().toString(), ex.getLocalizedMessage());
    }
}