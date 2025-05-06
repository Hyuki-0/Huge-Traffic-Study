package hyuki.board.article.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageLimitCalculator {

  /**
   * @Param Long page
   * @Param Long pageSize
   * @Param Long movablePageCount
   *
   * @See ((page - 1) / movablePageCount + 1) * pageSize * movablePageCount + 1
   */
  public static Long calculatePageLimit(Long page, Long pageSize, Long movablePageCount) {
    return ((page - 1) / movablePageCount + 1) * pageSize * movablePageCount + 1;
  }
}
