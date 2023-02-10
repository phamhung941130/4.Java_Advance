package com.vti.configuration.exception;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    @NonNull
    private String message;
    @NonNull
    private String detailMessage;

    private Object error;

    @NonNull
    private Integer code;

    @NonNull
    private String moreInformation;

}
