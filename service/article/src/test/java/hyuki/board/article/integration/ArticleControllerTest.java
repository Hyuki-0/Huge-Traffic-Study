package hyuki.board.article.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hyuki.board.article.service.response.ArticleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArticleControllerTest {

  RestClient restClient = RestClient.create("http://localhost:8000");
  Long articleId;

  @Test
  @Order(1)
  @DisplayName("Article 업로드")
  void create() {
    ArticleResponse response = create(new ArticleCreateRequest(
        "hyuki-test",
        "test-scenario",
        1L,
        1L
    ));

    articleId = response.articleId();

    System.out.println("response = " + response);
  }

  ArticleResponse create(ArticleCreateRequest request) {
    return restClient.post()
        .uri("/v1/articles")
        .body(request)
        .retrieve()
        .body(ArticleResponse.class);
  }

  @Test
  @Order(2)
  @DisplayName("Article 변경")
  void update() {
    update(this.articleId);
    ArticleResponse response = read(this.articleId);

    System.out.println("response = " + response);
  }

  @Test
  @Order(3)
  @DisplayName("Article 삭제")
  void delete(){
    restClient.delete()
        .uri("/v1/articles/{articleId}", articleId)
        .retrieve();

  }

  @Test
  @Order(4)
  @DisplayName("삭제된 Article Read Fail")
  void readFail() {
    HttpServerErrorException exception = assertThrows(HttpServerErrorException.class, () -> {
      restClient.get()
          .uri("/v1/articles/{articleId}", articleId)
          .retrieve()
          .body(ArticleResponse.class);
    });

    assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  ArticleResponse update(Long articleId) {
    return restClient.post()
        .uri("/v1/articles/{articleId}", articleId)
        .body(new ArticleUpdateRequest(
            "hyuki-test-2",
            "test-scenario-2"
        ))
        .retrieve()
        .body(ArticleResponse.class);
  }

  ArticleResponse read(Long articleId) {
    return restClient.get()
        .uri("/v1/articles/{articleId}", articleId)
        .retrieve()
        .body(ArticleResponse.class);
  }

  @Getter
  @AllArgsConstructor
  static class ArticleCreateRequest {

    private final String title;
    private final String content;
    private final Long writerId;
    private final Long boardId;
  }

  @Getter
  @AllArgsConstructor
  static class ArticleUpdateRequest {

    private final String title;
    private final String content;
  }
}