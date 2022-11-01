package com.example.bank.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="secret_questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SecretQuestion {
    @Id
    @GeneratedValue
    private int id;

    // To-Do
}
