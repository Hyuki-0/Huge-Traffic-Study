package hyuki.board.article.persist;

import static org.junit.jupiter.api.Assertions.*;

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
    List<Article> articles = articleRepository.findAll(1L, 1499970L, 30L);
    log.info("article.size = {}", articles.size());
    for (Article article : articles) {
      log.info("article = {}", article);
    }
  }
}