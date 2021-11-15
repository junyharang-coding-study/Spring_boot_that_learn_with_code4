package org.study.junyharang.entity;

import lombok.*;

import javax.persistence.*;

@ToString(exclude = "movie")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity public class MovieImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    private String uuid;
    private String imgName;
    private String path;

    @ManyToOne(fetch = FetchType.LAZY) private Movie movie;

} // class 끝
