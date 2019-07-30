package org.firefly.provider.mysql.domain.service;

import org.firefly.provider.mysql.domain.dto.PeopleCreateRequest;
import org.firefly.provider.mysql.domain.model.People;
import org.firefly.provider.mysql.domain.service.dao.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeopleService {
    @Autowired
    private PeopleRepository peopleRepository;

    public Boolean create(PeopleCreateRequest peopleCreateRequest) {
        People people = peopleCreateRequest.toPeople();
        return peopleRepository.save(people);
    }
}
