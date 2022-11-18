package com.epam.gameservice.controller.advice;

import com.epam.gameservice.controller.dto.error.ErrorInfo;
import com.epam.gameservice.controller.dto.error.ErrorResponse;
import com.epam.gameservice.exception.GameNotFoundException;
import com.epam.gameservice.exception.PlatformNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({PlatformNotFoundException.class, GameNotFoundException.class})
    @ResponseBody
    public ErrorResponse handlePlatformNotFound(HttpServletRequest req, Exception ex) {
        return ErrorResponse.builder()
                .errorInfos(singletonList(getErrorInfo(req, ex)))
                .build();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorResponse handleInternalServerError(HttpServletRequest req, Exception ex) {
        return ErrorResponse.builder()
                .errorInfos(singletonList(getErrorInfo(req, ex)))
                .build();
    }

    private ErrorInfo getErrorInfo(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURL().toString(), ex);
    }
}