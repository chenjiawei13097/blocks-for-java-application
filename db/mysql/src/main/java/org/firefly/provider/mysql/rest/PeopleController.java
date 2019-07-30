package org.firefly.provider.mysql.rest;

import org.firefly.provider.mysql.domain.dto.PeopleCreateRequest;
import org.firefly.provider.mysql.domain.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/people")
public class PeopleController {
    @Autowired
    PeopleService peopleService;

    @PostMapping
    public Boolean create(@RequestBody @Valid PeopleCreateRequest peopleCreateRequest) {
        return peopleService.create(peopleCreateRequest);
    }
}
