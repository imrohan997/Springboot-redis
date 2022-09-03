package com.example.redisdemo.controller;

import com.example.redisdemo.dto.Person.Person;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

  @Autowired
  RedisTemplate redisTemplate;

  private static final String KEY_PREFIX = "Person::";

  @PostMapping("/setValue")
  public void setValue(@Valid @RequestBody Person person) {
    String key = KEY_PREFIX + person.getId();
    redisTemplate.opsForValue().set(key, person);
  }

  @GetMapping("/getValue/{id}")
  public Person getValue(@PathVariable Integer id) {
    return (Person) redisTemplate.opsForValue().get(KEY_PREFIX + id);
  }

  @GetMapping("/getAllValues") // Getting all the person
  public List<Person> getAllPerson() {
    String pattern = KEY_PREFIX + "*";
    return (List<Person>) redisTemplate
        .keys(pattern)
        .stream()
        .sorted()
        .map(key -> redisTemplate.opsForValue().get(key))
        .collect(Collectors.toList());
    //------------------------------------- HASH -------------------------------------


  }
}
