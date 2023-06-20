package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.dto.UserAccountDto;
import com.fastcampus.projectboard.dto.request.ArticleCommentRequest;
import com.fastcampus.projectboard.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
@Slf4j
public class ArticleCommentController {
    
    private final ArticleCommentService articleCommentService;
    
    
    @PostMapping("/new")
    public String postNewArticleComment(ArticleCommentRequest articleCommentRequest) {
        log.warn("댓글 저장 요청중");
        articleCommentService.saveArticleComment(
                articleCommentRequest.toDto(
                        UserAccountDto.of(
                                "kun", "chh9450", "kun@mail.com", "Kun", "I am kun."
                        )));
        log.warn("댓글 저장 성공");
        return "redirect:/articles/" + articleCommentRequest.articleId();
    }
    
    @PostMapping("/{commentId}/delete")
    public String deleteArticleComment(@PathVariable Long commentId, Long articleId) {
        articleCommentService.deleteArticleComment(commentId);
        
        return "redirect:/articles/" + articleId;
    }
}
