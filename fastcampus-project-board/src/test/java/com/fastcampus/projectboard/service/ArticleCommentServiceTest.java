package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.ArticleComment;
import com.fastcampus.projectboard.dto.ArticleCommentDto;
import com.fastcampus.projectboard.repository.ArticleCommentRepository;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;
    @Mock private ArticleCommentRepository articleCommentRepository;
    @Mock private ArticleRepository articleRepository;

    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트를 반환")
    @Test
    void givenArticleId_whenSearchingArticleComments_thenReturnsComments(){
        //given
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.of(
                Article.of("title", "content", "hashtag")));

        //when
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(articleId);
        //then
        assertThat(articleComments).isNotNull();

    }

    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다.")
    @Test
    void givenCommentInfo_whenSavingArticleComments_thenSavesComments(){
        //given
        Long articleId = 1L;
        given(articleCommentRepository.save(
                ArticleComment.of(
                        Article.of("title", "content", "hashtag"), "댓글 내용")))
                .willReturn(null);

        //when
        sut.saveArticleComment(articleId);
        //then

    }

    @DisplayName("댓글 Id를 입력하면, 댓글을 삭제한다.")
    @Test
    void givenCommentId_whenDeletingArticleComments_thenDeletesComments(){
        //given
        Long articleId = 1L;
        willDoNothing().given(articleCommentRepository).delete(ArticleComment.of(Article.of("title", "content", "hashtag"), "댓글 내용"));

        //when
        sut.deleteArticleComment(articleId);
        //then
        then(articleRepository).should().delete(any(Article.class));
    }

}