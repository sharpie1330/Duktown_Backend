package com.duktown.domain.dormCert.entity;

import com.duktown.domain.user.entity.User;
import com.duktown.global.type.HallName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@Table(name = "dorm_cert")
public class DormCert {
    @Id
    @GeneratedValue
    @Column(name = "dorm_cert_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String imgUrl;

    private Boolean certified;

    @Column(nullable = false)
    private String studentId;

    @Enumerated(value = STRING)
    @Column(nullable = false)
    private HallName hallName;

    @Column(nullable = false)
    private String unit;
}
