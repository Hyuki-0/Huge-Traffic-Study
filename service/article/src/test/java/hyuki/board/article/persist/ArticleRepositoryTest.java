package hyuki.board.article.persist;

import hyuki.board.article.entity.Article;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ArticleRepositoryTest {

  @Autowired
  ArticleRepository articleRepository;

  @Test
  void findAllTest() {
    // when
    List<Article> articles = articleRepository.findAll(1L, 1499970L, 30L);

    // then
    log.info("article.size = {}", articles.size());
    for (Article article : articles) {
      log.info("article = {}", article);
    }
  }

  @Test
  void limitCount() {
    // when
    Long count = articleRepository.count(1L, 30L);

    log.info("count = {}", count);
  }

}