package com.sy.personal_project.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter // (롬복) getter생성
@Setter // (롬복) setter생성
@EqualsAndHashCode(of = "id") // (롬복) 생성자에서 지정된 필드만을 사용하여 equals()와 hashCode() 메서드를 생성하는 기능을 제공
@NoArgsConstructor // (롬복) 파라미터가 없는 기본 생성자 생성
@AllArgsConstructor // (롬복) 모든 필드 값을 파라미터로 받는 생성자 생성
@Entity // JPA 기본 어노테이션으로 테이블과 1:1로 매칭되는 객체라는 것을 의미
@EntityListeners(AuditingEntityListener.class) //JPA Entity 에 이벤트가 발생할 관련 코드를 실행
@Table(name = "board") // DB에서의 테이블 이름
public class Board {
    @Id // entity의 id를 명시해 주는부분 (테이블 상의 Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성 전략
    @Column(name = "board_pid") //테이블에서 해당되는 컬럼 명시
    private Long id;

    @Column(length = 100)
    private String title; // DB의 컬럼과 이름이 동일하다면 @Column 어노테이션의 name 생략가능

    @Column(length = 10000)
    private String content;

    @Column(name = "cre_ps_id", length = 50)
    private String crePsId;

    @CreatedDate // 등록시간 자동적용
    @Column(name = "cre_dtm")
    private LocalDateTime creDtm;

    @Column(name = "mod_ps_id", length = 50)
    private String modPsId;

    @LastModifiedDate // 수정시간 자동적용
    @Column(name = "mod_dtm")
    private LocalDateTime modDtm;

    @Column(name = "del_at")
    @Type(type="yes_no") // Y, N값을 사용하는 Hibernate 타입으로 변환
    private Boolean delAt = false;

    /*
    cascade = CascadeType.ALL: 이 옵션은 부모 엔티티에 대한 작업(저장, 수정, 삭제 등)이
    자식 엔티티에도 일괄적으로 전파되도록 합니다.
    즉, 부모 엔티티의 상태 변화가 자식 엔티티에도 적용됩니다.
    예를 들어, 부모 엔티티를 삭제하면 연관된 자식 엔티티도 자동으로 삭제됩니다.
    이 옵션은 연관된 엔티티들 간의 일괄 작업을 편리하게 처리할 때 사용됩니다.

    orphanRemoval = true: 이 옵션은 연관된 자식 엔티티가 부모 엔티티와의 연관 관계가
    끊어지면 자동으로 삭제되도록 합니다.
    즉, 자식 엔티티가 부모와의 연관 관계를 잃을 경우 자동으로 삭제됩니다.
    이 옵션은 부모-자식 엔티티 간의 일대다 또는 일대일 관계에서 사용됩니다.
    주로 부모 엔티티와 자식 엔티티의 생명주기가 밀접하게 연관되어 있을 때 유용하게 사용됩니다.
    */

    // 게시판과 댓글 간의 일대다 관계 설정
    // OneToMany 의 기본 FetchType 은 LAZY
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    List<BoardComment> boardComments;

    // 논리적 삭제를 구현하기 위해 생성한 delete메서드
    public void delete() {
        /*EntityManager em
        em.createQuery("update board_comment bc set bc.delAt='Y' where bc.board_pid = :")*/
        this.delAt = true;
        // this.boardComments.clear(); // 연관된 댓글 목록 초기화
    }
}
