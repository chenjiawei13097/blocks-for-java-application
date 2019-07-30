package org.firefly.provider.mysql.domain.service.dao;

import org.firefly.provider.mysql.domain.model.People;

public interface PeopleRepository {
    Boolean save(People people);
}
