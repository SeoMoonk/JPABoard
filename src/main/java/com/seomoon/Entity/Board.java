package com.seomoon.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Board {

    @Id
    public Long articleNo;

    public String title;

    public String content;

    @CreatedDate
    public LocalDateTime writeDate;

    public String write_id;

}
