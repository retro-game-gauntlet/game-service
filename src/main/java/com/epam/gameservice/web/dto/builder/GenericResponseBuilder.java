package com.epam.gameservice.web.dto.builder;

import com.epam.gameservice.web.dto.Data;
import com.epam.gameservice.web.dto.Response;
import org.springframework.stereotype.Component;

@Component
public class GenericResponseBuilder {

    public <T> Response<T> buildResponse(T attributes) {
        return Response.<T>builder()
                .data(Data.<T>builder().attributes(attributes).build())
                .build();
    }
}