package com.seomoon.bulletin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Getter
@Component
@Setter
@SuperBuilder
@NoArgsConstructor
public class Bulletin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_no")
    private Long id;

    @Column(length=100)
    private String title;

    @Column(length=2000)
    private String content;

    @CreationTimestamp
    private LocalDateTime writeDate;

    private String writer;

}