package com.demo.fe.model;


import lombok.*;

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
    private Character del_yn;

    @NotBlank
    @Size(min = 0, max = 100)
    private String ins_id;
    private LocalDateTime ins_date;

    @Size(max = 100)
    private String mod_id;
    private LocalDateTime mod_date;
}
