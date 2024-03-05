package com.example.functional;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResult {
    private Boolean status;
    private String message;
    private Integer value;
}
