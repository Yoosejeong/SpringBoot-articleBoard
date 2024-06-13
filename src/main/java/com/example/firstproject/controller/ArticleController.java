package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        //System.out.println(form.toString());
        log.info(form.toString());
        //1. DTO 를 엔티티로 변환
        Article article = form.toEntity();
        //System.out.println(article.toString());  //DTO가 엔티티로 잘 변환되는지 확인 출력
        log.info(article.toString());
        //2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString()); //article이 DB에 잘 저장되는지 확인 출력
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        //1.id를 조회하여 데이터 가져오기
        //Optional<Article> articleEntity =  articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //2.모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        //3.뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        //1. 모든 데이터 가져오기
        //첫번째 방법은 List<Article>로 다운캐스팅함
        //List<Article> articleEntityList = (List<Article>)articleRepository.findAll();
        List<Article> articleEntityList = articleRepository.findAll();
        //2. 모델에 데이터 등록하기 ( 전달할 데이터 묶음인 articleEntityList를 articleList라는 이름으로 등록
        model.addAttribute("articleList", articleEntityList);
        //3. 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){

        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse((null));
        //모델에 데이터 등록하기
        //이렇게하면 DB에서 가져온 데이터를 article이라는 이름으로 뷰페이지에서 사용가능
        model.addAttribute("article", articleEntity);
        //뷰 페이지 설정하기
        return "articles/edit";
        }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        //1.DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        //2.엔티티를 DB에 저장하기
        //2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //2-2. 기존 데이터 값을 갱신하기
        if(target != null){
            articleRepository.save(articleEntity); //엔티티를 DB에 저장(갱신)
        }
        //3.수정 결과 페이지로 리다이렉트
        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다!");
        //1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. 대상 엔티티 삭제하기
        if (target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다!");
        }
        //3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles";

    }

    }

