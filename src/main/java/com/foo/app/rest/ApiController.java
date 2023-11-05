package com.foo.app.rest;

import com.foo.app.db.PersonEntityRepository;
import com.foo.app.service.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/persons")
public class ApiController {

    private final PersonMapper mapper;
    private final PersonEntityRepository repository;

    @PostMapping
    public ResponseEntity<PersonDto> create(@RequestBody final PersonDto dto) {
        var entity = mapper.dtoToEntity(dto);
        var resEntity = repository.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDTO(resEntity));
    }

    @GetMapping("/{id}")
    public PersonDto read(@PathVariable final long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return mapper.entityToDTO(entity);
    }

    @GetMapping(path = "/all")
    public List<PersonDto> readAll() {
        return mapper.entityToDTO(repository.findAll());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PersonDto> update(@PathVariable Long id, @RequestBody PersonDto dto) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        mapper.dtoToEntity(dto, entity);
        var updatedEntity = repository.save(entity);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.entityToDTO(updatedEntity));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.delete(entity);
    }


}