package hyuki.board.article.service;

import hyuki.board.article.entity.Article;
import hyuki.board.article.persist.ArticleRepository;
import hyuki.board.article.service.request.ArticleCreateRequest;
import hyuki.board.article.service.request.ArticleUpdateRequest;
import hyuki.board.article.service.response.ArticleResponse;
import hyuki.board.snowflake.Snowflake;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleService {

  private final Snowflake snowflake = new Snowflake();
  private final ArticleRepository articleRepository;

  @Transactional
  public ArticleResponse create(ArticleCreateRequest request) {
    Article article = articleRepository.save(
        Article.create(snowflake.nextId(), request.title(), request.content(), request.writerId(), request.boardId())
    );

    return ArticleResponse.from(article);
  }

  @Transactional
  public ArticleResponse update(Long articleId, ArticleUpdateRequest request) {
    Article article = articleRepository.findById(articleId)
        .orElseThrow(() -> new IllegalArgumentException("Article not found"));

    article.update(request.title(), request.content());

    return ArticleResponse.from(article);
  }

  public ArticleResponse read(Long articleId) {
    return ArticleResponse.from(articleRepository.findById(articleId)
        .orElseThrow(() -> new IllegalArgumentException("Article not found")));
  }

  @Transactional
  public void delete(Long articleId) {
   articleRepository.deleteById(articleId);
  }
}
