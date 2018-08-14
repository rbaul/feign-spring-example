package com.example.feign.client.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExampleDto {
    @NotEmpty
    private String name;
}
