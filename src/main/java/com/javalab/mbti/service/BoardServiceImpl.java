package com.javalab.mbti.service;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.javalab.mbti.dto.BoardDto;
import com.javalab.mbti.dto.PageRequestDTO;
import com.javalab.mbti.dto.PageResultDTO;
import com.javalab.mbti.dto.UserDTO;
import com.javalab.mbti.entity.Board;
import com.javalab.mbti.entity.User;
import com.javalab.mbti.repository.BoardRepository;
import com.javalab.mbti.repository.UserRepository;

@Service
public class BoardServiceImpl implements BoardService{

	// 의존성 주입
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;

	public BoardServiceImpl(BoardRepository boardRepository, 
							UserRepository userRepository) {
		this.boardRepository = boardRepository;
		this.userRepository = userRepository;
	}

	// 후기 목록 조회 [페이징]
	@Override
	public PageResultDTO<BoardDto, Board> getList(PageRequestDTO requestDTO, String mbti) {
		Pageable pageable = requestDTO.getPageable(Sort.by("boardId").descending());
		//Page<Board> result = boardRepository.findAll(pageable);
		Page<Board> result = boardRepository.findBoardByMbti(mbti, pageable);
		Function<Board, BoardDto> fn = (entity -> entityToDto(entity));
		return new PageResultDTO<>(result, fn);
	}

	// 후기 등록
	@Override
	public Board create(BoardDto dto, String mbti) {
		Board entity = dtoToEntity(dto, mbti);
		return boardRepository.save(entity);
	}

	@Override
	public boolean remove(Integer boardId, String userId) {
		// 1. 삭제할 보드아이디 조회
		Optional<Board> board = boardRepository.findById(boardId);
		// 2. 존재할 경우 삭제 처리
		if (board.isPresent()) {
			boardRepository.deleteById(boardId);
			return true;
		} else {
			return false;
		}
	}
	
	 @Override
	    public void increaseHit(Integer boardId) {
	        Optional<Board> optionalBoard = boardRepository.findById(boardId);
	        if (optionalBoard.isPresent()) {
	            Board board = optionalBoard.get();
	            board.setBoardHit(board.getBoardHit() + 1);
	            boardRepository.save(board);
	        }
	    }

	public boolean isLoggedIn(HttpServletRequest request) {

		// 예시: 세션에서 로그인 정보를 가져오는 로직
		HttpSession session = request.getSession(false);
		if (session != null) {
			// 세션에 로그인 정보가 있는지 확인
			Object user = session.getAttribute("user");
			if (user != null) {
				// 로그인 정보가 있다면 로그인 상태로 간주
				return true;
			}
		}
		return false;
	}
	
	  @Override
	    public Board findBoardByBoardId(Integer boardId) {
	        return boardRepository.findById(boardId).orElse(null);
	    }

//	@Override
//	public Board findBoardByUserId(String userId) {
//	    // BoardRepository를 사용하여 userId로 게시글을 조회하는 로직을 구현
//	    Optional<Board> optionalBoard = boardRepository.findBoardByUserId(userId);
//	    return optionalBoard.orElse(null);
//	}
	
}
