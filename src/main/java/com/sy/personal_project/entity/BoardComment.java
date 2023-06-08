package com.sy.personal_project.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "board_comment")
public class BoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_comment_pid")
    private Long id;

    @Column(length = 5000)
    private String content;

    @Column(name = "cre_ps_id", length = 50)
    private String crePsId;

    @CreatedDate
    @Column(name = "cre_dtm")
    private LocalDateTime creDtm;

    @Column(name = "mod_ps_id", length = 50)
    private String modPsId;

    @LastModifiedDate
    @Column(name = "mod_dtm")
    private LocalDateTime modDtm;

    @Column(name = "del_at")
    @Type(type="yes_no")
    private Boolean delAt;

    // 댓글과 게시판 간의 다대일 관계 설정
    // ManyToOne 의 기본 FetchType 은 EAGER
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_pid")
    private Board board;

    // 논리적 삭제를 구현하기 위해 생성한 delete메서드
    public void delete() {
        this.delAt = true;
    }
}
