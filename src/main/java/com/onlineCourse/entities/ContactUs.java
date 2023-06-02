package com.onlineCourse.entities;

import lombok.*;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Lazy(value = false)
@Table(name = "contactus")
public class ContactUs {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        private Integer id;

        @Column(name = "name")
        @Size(min = 10, max = 200, message = "Name must be between 10 and 200 characters")
        private String name;

        @NotNull(message = "Email is Mandatory.")
        @Email(message = "Email should be valid")
        @Column(name = "email")
        private String email;

        @Column(name = "message")
        @Size(min = 10, max = 200, message = "Name must be between 10 and 200 characters")
        private String message;
}
