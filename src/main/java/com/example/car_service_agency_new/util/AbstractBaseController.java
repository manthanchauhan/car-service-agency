package com.example.car_service_agency_new.util;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import java.time.Instant;
import java.util.HashMap;

@ControllerAdvice
public class AbstractBaseController {
    public <Any> ResponseEntity<Any> makeResponse(Any obj) {
        return this.makeResponse(obj, HttpStatus.OK);
    }

    public <Any> ResponseEntity<Any> makeResponse(Any obj, HttpStatus httpStatus) {
        return new ResponseEntity<>(obj, httpStatus);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Object handleException(Exception ex, WebRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new HashMap<String, String>() {{
            put("timestamp", Instant.now().toString());
            put("error", ex.getClass().getSimpleName());
            put("message", ex.getMessage());
        }};
    }
}
