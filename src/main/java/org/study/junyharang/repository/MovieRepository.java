package org.study.junyharang.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.study.junyharang.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    // 페이징 처리
    @Query("select m, mi, avg(coalesce(r.grade,0) ), count (distinct r) from Movie m "
    + "left outer join MovieImage mi on mi.movie = m "
            + "left outer join Review r on r.movie = m group by m")
    Page<Object[]> getListPage(Pageable pageable);

    // 특정 영화 조회
    @Query("select m, mi from Movie m left outer join MovieImage mi on mi.movie = m "
    + "where m.mno = :mno")
    List<Object[]> getMovieWithAll(@Param("mno") Long mno);


} // interface 끝
