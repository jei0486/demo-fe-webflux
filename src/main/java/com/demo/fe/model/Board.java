package com.demo.fe.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board {

    private Long seq;

    @NotBlank
    @Size(min = 0, max = 200, message = "제목은 최소 0자,최대 200자 작성해주세요.")
    private String subject;

    @NotBlank
    @Size(max = 2000, message = "내용은 최대 2000자 작성해주세요.")
    private String content;
    private Integer hits;

    @NotBlank
    @Size(min = 0, max = 100, message = "작성자를 입력해주세요.")
    private String createdId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;

    @Size(max = 100)
    private String modifiedId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime modifiedDate;
}
