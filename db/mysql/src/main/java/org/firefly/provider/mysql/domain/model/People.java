package org.firefly.provider.mysql.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class People {
    private int id;
    private String name;
    private Integer age;
}
