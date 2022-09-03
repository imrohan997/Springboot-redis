package com.example.redisdemo.dto.Person;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

  @NotNull
  private Long id;

  private String name;
  private Double height;
  private Integer weight;
  private Boolean seniorCitizen;
}
