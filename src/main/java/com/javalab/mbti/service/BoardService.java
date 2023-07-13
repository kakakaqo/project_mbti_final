package com.javalab.mbti.service;


import com.javalab.mbti.dto.BoardDto;
import com.javalab.mbti.dto.PageRequestDTO;
import com.javalab.mbti.dto.PageResultDTO;
import com.javalab.mbti.entity.Board;
import com.javalab.mbti.entity.Mbti;
import com.javalab.mbti.entity.User;

public interface BoardService {

	PageResultDTO<BoardDto, Board> getList(PageRequestDTO requestDTO, String mbti);
	Board create(BoardDto board, String mbti);
	boolean remove(Integer boardId, String userId);
	void increaseHit(Integer boardId);
	  Board findBoardByBoardId(Integer boardId);
	
	default Board dtoToEntity(BoardDto dto, String mbti) {
		
		// 화면에서 전달된 boardId로 user 객체 생성
		User user = User.builder().userId(dto.getUserId()).build();
		
		Board entity = Board.builder()
							.boardContent(dto.getBoardContent())
							.boardHit(dto.getBoardHit())
							.user(user)
							.mbti(new Mbti(mbti))
							.build();
		return entity;
	}

	/*
	 *  DTO <-- Entity 전환을 위한 default 메소드
	 *  default 메소드는 기존의 인터페이스를 구현해서 사용하는 Impl클래스들이
	 *  의무적으로 구현하지 않아도 오류가 발생하지 않는다.
	 */

	default BoardDto entityToDto(Board entity) {
		
	    BoardDto dto = BoardDto.builder()
			            .boardId(entity.getBoardId())
			            .boardContent(entity.getBoardContent())
			            .boardHit(entity.getBoardHit())
			            .regDate(entity.getRegDate())
			            //.userId(entity.getUser().getUserId())
			            .userId(entity.getUser() != null ? entity.getUser().getUserId() : null)
			            .build();
	    return dto;
	}
	
}
