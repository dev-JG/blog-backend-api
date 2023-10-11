package com.blog.model.entity;

import com.blog.model.enums.LogStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@DynamicUpdate
@NoArgsConstructor
@Table(name = "keyword_search_log")
@Entity
public class keywordSearchLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Column(name = "count")
    private int count;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private LogStatus status;

    @Column(name = "last_search_date")
    private LocalDateTime lastSearchDate;
}
