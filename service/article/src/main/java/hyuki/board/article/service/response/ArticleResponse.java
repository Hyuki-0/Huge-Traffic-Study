package hyuki.board.article.service.response;

import hyuki.board.article.entity.Article;

public record ArticleResponse(
    Long articleId,
    String title,
    String content,
    Long boardId,
    Long writerId,
    String createdAt,
    String updatedAt
) {

  public static ArticleResponse from(Article article) {
    return new ArticleResponse(
        article.getArticleId(),
        article.getTitle(),
        article.getContent(),
        article.getBoardId(),
        article.getWriterId(),
        article.getCreatedAt().toString(),
        article.getModifiedAt().toString()
    );
  }
}
