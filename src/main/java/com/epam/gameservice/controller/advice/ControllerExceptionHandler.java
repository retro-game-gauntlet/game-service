package com.epam.gameservice.controller.advice;

import com.epam.gameservice.controller.dto.ErrorInfo;
import com.epam.gameservice.controller.dto.PlatformResponse;
import com.epam.gameservice.service.exception.PlatformNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(PlatformNotFoundException.class)
    @ResponseBody
    public PlatformResponse handlePlatformNotFound(HttpServletRequest req, Exception ex) {
        return PlatformResponse.builder()
                .errorInfos(singletonList(getErrorInfo(req, ex)))
                .build();
    }

    private ErrorInfo getErrorInfo(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURL().toString(), ex);
    }
}