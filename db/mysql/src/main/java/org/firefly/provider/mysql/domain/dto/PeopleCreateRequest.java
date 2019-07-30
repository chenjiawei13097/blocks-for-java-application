package org.firefly.provider.mysql.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.firefly.provider.mysql.domain.model.People;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PeopleCreateRequest {
    @NotEmpty
    @Size(max = 30)
    private String name;
    @NotNull
    private Integer age;

    public People toPeople() {
        return People.builder().name(name).age(age).build();
    }
}
