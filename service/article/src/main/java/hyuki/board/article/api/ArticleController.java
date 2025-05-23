package hyuki.board.article.api;

import hyuki.board.article.service.ArticleService;
import hyuki.board.article.service.request.ArticleCreateRequest;
import hyuki.board.article.service.request.ArticleUpdateRequest;
import hyuki.board.article.service.response.ArticlePageResponse;
import hyuki.board.article.service.response.ArticleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleController {
  private final ArticleService articleService;

  @GetMapping("/v1/articles/{articleId}")
  public ArticleResponse read(@PathVariable Long articleId) {
    return articleService.read(articleId);
  }

  @GetMapping("/v1/articles")
  public ArticlePageResponse readAll(
      @RequestParam("boardId") Long boardId,
      @RequestParam("page") Long page,
      @RequestParam("pageSize") Long pageSize) {
    return articleService.readAll(boardId, page, pageSize);
  }
  @PostMapping("/v1/articles")
  public ArticleResponse save(@RequestBody ArticleCreateRequest request) {
    return articleService.create(request);
  }

  @PostMapping("/v1/articles/{articleId}")
  public ArticleResponse update(
      @PathVariable Long articleId,
      @RequestBody ArticleUpdateRequest request) {
    return articleService.update(articleId, request);
  }

  @DeleteMapping("/v1/articles/{articleId}")
  public void delete(@PathVariable Long articleId) {
    articleService.delete(articleId);
  }
}
