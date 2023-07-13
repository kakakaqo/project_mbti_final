package com.javalab.mbti.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.data.convert.DtoInstantiatingConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javalab.mbti.dto.BoardDto;
import com.javalab.mbti.dto.PageRequestDTO;
import com.javalab.mbti.dto.PageResultDTO;
import com.javalab.mbti.entity.Board;
import com.javalab.mbti.entity.User;
import com.javalab.mbti.service.BoardService;
import com.javalab.mbti.service.LoginService;

import groovyjarjarantlr4.v4.parse.ANTLRParser.finallyClause_return;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;
   private final LoginService loginService;

    public BoardController(BoardService boardService , LoginService loginService) {
        this.boardService = boardService;
        this.loginService = loginService;
    }

    @GetMapping("/list/{mbti}")
    public String listAndCreate(PageRequestDTO pageRequestDTO, @PathVariable("mbti") String mbti, Model model, HttpServletRequest request) {
        PageResultDTO<BoardDto, Board> result = boardService.getList(pageRequestDTO, mbti);
        model.addAttribute("result", result);
        model.addAttribute("boardDto", new BoardDto());
        model.addAttribute("mbti", mbti);

        // 로그인 상태 확인
        Map<String, Object> isLoggedInResponse = isLoggedIn(request);
        boolean isLoggedIn = (boolean) isLoggedInResponse.get("isLoggedIn");
        model.addAttribute("isLoggedIn", isLoggedIn);

        return "board/list";
    }

    @PostMapping("/list")
    public String create(@ModelAttribute @Valid BoardDto boardDto,
    					 @RequestParam ("mbti") String mbti
                         , BindingResult bindingResult,
                         Model model, HttpServletRequest request) {
        log.info("create");

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error : fieldErrors) {
                log.error(error.getField() + " " + error.getDefaultMessage());
            }

            model.addAttribute("boardDto", boardDto);

            return "board/list";
        }

        boardService.create(boardDto, mbti);
        return "redirect:/board/list/" + mbti;
    }
    
    @PostMapping("/increaseHit/{boardId}")
    @ResponseBody // JSON 응답을 반환하는 애너테이션
    public Map<String, Object> increaseHit(@PathVariable Integer boardId) {
        boardService.increaseHit(boardId);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "조회수가 증가되었습니다.");
        
        return response;
    }

	@GetMapping("/delete/{boardId}")
	public String deleteBoard(@PathVariable Integer boardId, HttpServletRequest request, @RequestParam ("mbti") String mbti) {
		// 로그인 상태 확인
		Map<String, Object> isLoggedInResponse = isLoggedIn(request);
		boolean isLoggedIn = (boolean) isLoggedInResponse.get("isLoggedIn");
		if (isLoggedIn) {
			// 현재 로그인된 사용자 아이디 확인
			HttpSession session = request.getSession();
			String sessionUserId = (String) session.getAttribute("userId");
			System.out.println("sessionUserId:" + sessionUserId);

			Board board = boardService.findBoardByBoardId(boardId);

			if (board != null) {
				User user = board.getUser();
				if (user != null) {
					String saveUserId = user.getUserId();

					if (sessionUserId.equals(saveUserId)) {
					    boolean deleted = boardService.remove(boardId, saveUserId);
					    if (deleted) {
					        System.out.println("게시글이 삭제되었습니다.");
					        System.out.println("saveUserId:" + saveUserId);
					        // 삭제 성공 시 필요한 작업 수행
					    } else {
					        System.out.println("게시글 삭제에 실패했습니다.");
					        // 삭제 실패 시 필요한 작업 수행
					    }
					} else {
					    System.out.println("권한이 없습니다.");
					    // 권한이 없는 경우 처리
					}
					
				}
			}
		}
		return "redirect:/board/list/" + mbti;
	}
    
    @GetMapping("/isLoggedIn")
    @ResponseBody
    public Map<String, Object> isLoggedIn(HttpServletRequest request) {
        // HttpSession 객체를 통해 세션에 저장된 로그인 정보를 확인하는 로직
        boolean isLoggedIn = false;
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userId") != null) {
            // 세션에 "loggedInUser" 속성이 존재하면 로그인 상태로 간주
            isLoggedIn = true;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("isLoggedIn", isLoggedIn);
      return response;
    }
}