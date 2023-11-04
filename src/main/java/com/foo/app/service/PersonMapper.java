package com.foo.app.service;

import com.foo.app.db.PersonEntity;
import com.foo.app.rest.PersonDto;
import com.foo.app.rest.PersonInDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Map DTOs to JPA Entities and vice-versa
 */
@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDto entityToDTO(PersonEntity project);

    List<PersonDto> entityToDTO(Iterable<PersonEntity> project);

    PersonEntity  dtoToEntity(PersonInDto source);

    /*
     * As of MapStruct 1.5.5 there is no way to copy only non-null source fields to target
     */
    default void dtoToEntity(PersonInDto source, @MappingTarget PersonEntity target) {
        if (source != null) {
            if (StringUtils.hasLength(source.firstName())) {
                target.setFirstName(source.firstName());
            }

            if (StringUtils.hasLength(source.lastName())) {
                target.setLastName(source.lastName());
            }

            if (source.birthDate() != null) {
                target.setBirthDate(source.birthDate());
            }

            if (StringUtils.hasLength(source.country())) {
                target.setCountry(source.country());
            }
        }
    }
}

