package iducs.javaweb.blog202112004.model;

import lombok.*;

import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Blog { //model 객체 : dto, vo 객체
    //객체 정의 방식: Beans, POJO(Plain Old Java Object)
    private long id;
    private String title;
    private String content;
    private String author;
    private String email;
    private String regdate;
}
