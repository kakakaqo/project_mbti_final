package com.javalab.mbti.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javalab.mbti.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	@Transactional    
	@Query("UPDATE Board b SET b.boardHit = b.boardHit + 1 WHERE b.boardId = :boardId")
	void increaseHit(@Param("boardId") Integer boardId);

	@Query("select b from Board b where b.mbti.mbtiName = :mbti")
	Page<Board> findBoardByMbti(@Param("mbti") String mbti, Pageable pageable);
	
//	@Query("select b, u from Board b left join b.user u where b.boardId = :boardId")
//	Object getBoardWithUser(@Param("boardId") Integer boardId);
}
