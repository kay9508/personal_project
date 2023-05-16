package com.sy.personal_project.repository;

import com.sy.personal_project.entity.Board;
import com.sy.personal_project.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
    @Repository 어노테이션은 Spring에서 컴포넌트 스캔을 통해 해당 클래스를 빈으로 등록할 때 사용되는 어노테이션입니다. Repository 인터페이스는 데이터 액세스 계층의 구현체를 나타내는 역할을 하므로, @Repository 어노테이션을 사용하여 Spring에게 해당 클래스를 빈으로 등록해야 합니다.

    실제로 @Repository 어노테이션을 생략해도 동작하는 경우가 있을 수 있습니다. 이는 Spring Data JPA가 Repository 인터페이스를 자동으로 구현체로 인식하여 빈으로 등록하기 때문입니다. 따라서 Spring Data JPA를 사용하는 경우에는 @Repository 어노테이션을 생략해도 상관없습니다.

    하지만, @Repository 어노테이션을 명시적으로 사용하는 것은 코드의 가독성과 명확성을 높일 수 있습니다. 또한, Spring에서 해당 클래스를 빈으로 등록하는 것을 명시적으로 나타내기 위해서도 사용할 수 있습니다. 따라서 일반적으로 @Repository 어노테이션을 사용하는 것이 권장됩니다.

    결론적으로, @Repository 어노테이션을 사용하지 않고도 동작하는 경우도 있지만, Repository 인터페이스를 나타내는 클래스에는 @Repository 어노테이션을 명시적으로 추가하는 것이 좋습니다.
*/
public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
    List<BoardComment> findAllByBoard(Board board);
    //List<BoardComment> findAllByBoardPid(Long boardPid);
}