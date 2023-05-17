package com.ssafy.tofy.util;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class Response {

    String status;
    String message;
    Map<Object, Object> data;

}
