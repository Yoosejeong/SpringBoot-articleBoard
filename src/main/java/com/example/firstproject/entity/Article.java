package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor //기본 생성사 추가 어노테이션
public class Article {
    @Id //엔티티의 대푯값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 생성 기능 추가(숫자 자동으로 매김)
    private  Long id;
    @Column //title 필드 선언. DB 테이블의 title 열과 연결
    private String title;
    @Column
    private String content;


    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }
}
