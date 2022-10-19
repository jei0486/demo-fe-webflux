package com.demo.fe.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Data
public class Board {

    private Long seq;

    @NotBlank
    @Size(min = 0, max = 200)
    private String subject;

    @Size(max = 2000)
    private String content;
    private int hits;

    @NotBlank
    @Size(min = 0, max = 100)
    private String ins_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime ins_date;

    @Size(max = 100)
    private String mod_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime mod_date;
}
