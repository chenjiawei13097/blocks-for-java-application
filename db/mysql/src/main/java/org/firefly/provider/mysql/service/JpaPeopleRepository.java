package org.firefly.provider.mysql.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.firefly.provider.mysql.domain.model.People;
import org.firefly.provider.mysql.domain.service.dao.PeopleRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public interface JpaPeopleRepository extends CrudRepository<JpaPeopleRepository.DbPeople, Long>, PeopleRepository {
    @Override
    default Boolean save(People people) {
        DbPeople dbPeople = DbPeople.fromPeople(people);
        DbPeople createdDbPeople = save(dbPeople);
        return createdDbPeople != null;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    @Entity(name = "people")
    @Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
    class DbPeople {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(length = 32)
        private int id;
        @Column(length = 30)
        private String name;
        private Integer age;

        public static DbPeople fromPeople(People people) {
            return DbPeople.builder().id(people.getId()).name(people.getName()).age(people.getAge()).build();
        }
    }
}
