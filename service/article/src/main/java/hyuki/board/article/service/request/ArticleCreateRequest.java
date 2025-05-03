package hyuki.board.article.service.request;

import lombok.extern.java.Log;

public record ArticleCreateRequest (
    String title,
    String content,
    Long writerId,
    Long boardId
){

}
