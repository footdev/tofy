package com.ssafy.tofy.freeboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tofy.freeboard.dto.FreeBoardCommentDto;
import com.ssafy.tofy.freeboard.dto.FreeBoardDto;
import com.ssafy.tofy.freeboard.service.FreeBoardService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = {"*"})
public class FreeBoardController {
	private FreeBoardService freeBoardService;

	public FreeBoardController(@Qualifier("FreeBoardServiceImpl") FreeBoardService freeBoardService) {
		super();
		this.freeBoardService = freeBoardService;
	}
	
	@GetMapping("/freeboard")
	public ResponseEntity<Object> getFreeBoards() {
		try {
			log.info("자유게시판 리스트 출력 호출");
			List<FreeBoardDto> list = freeBoardService.listBoard();
			
			System.out.println(list.size());
			for(int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			for (FreeBoardDto freeBoardDto : list) {
				log.info(freeBoardDto.toString());
			}
			
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/freeboard")
	public ResponseEntity<Object> writeBoard(@RequestBody FreeBoardDto freeboard) {
		try {
			freeBoardService.writeBoard(freeboard);
			log.info("게시물 생성 완료");
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/freeboard/{freeboardNo}")
	public ResponseEntity<Object> getBoard(@PathVariable String freeboardNo) {
		try {
			freeBoardService.updateHit(freeboardNo);
			
			FreeBoardDto freeBoardDto = freeBoardService.getBoard(freeboardNo);
			log.info(freeboardNo + "번 게시글 조회");
			return ResponseEntity.ok().body(freeBoardDto);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/freeboard/{freeboardNo}")
	public ResponseEntity<Object> deleteBoard(@PathVariable String freeboardNo) {
		try {
			freeBoardService.deleteBoard(freeboardNo);
			log.info(freeboardNo + "번 게시글 삭제");
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/freeboard")
	public ResponseEntity<Object> modifyBoard(@RequestBody FreeBoardDto freeboard) {
		try {
			log.info(freeboard.getFreeBoardNo() + "번 게시글 수정");
			freeBoardService.updateBoard(freeboard);
			
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.info(freeboard.getFreeBoardNo() + "번 게시글 수정 에러");
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/freeboard/{freeboardNo}/comment")
	public ResponseEntity<Object> writeComment(@PathVariable String freeboardNo, @RequestBody FreeBoardCommentDto comment) {
		try {
			freeBoardService.writeComment(comment);
			log.info(freeboardNo + "번 게시물 댓글 생성");
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	@GetMapping("/freeboard/{freeboardNo}/comment")
	public ResponseEntity<Object> getFreeBoardComment(@PathVariable String freeboardNo) {
		try {
			List<FreeBoardCommentDto> list = freeBoardService.listComment(freeboardNo);
			log.info(freeboardNo + "번 게시물 댓글 조회");
			
			System.out.println(list.size());
			for (FreeBoardCommentDto freeBoardComment : list) {
				log.info(freeBoardComment.toString());
			}
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/freeboard/{freeboardNo}/comment")
	public ResponseEntity<Object> modifyFreeBoardComment(@PathVariable String freeboardNo, @RequestBody FreeBoardCommentDto comment) {
		try {
			freeBoardService.updateComment(comment);
			
			log.info(freeboardNo + "번 게시물 댓글 수정");
			
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/freeboard/{freeboardNo}/comment/{commentNo}")
	public ResponseEntity<Object> deleteBoardComment(@PathVariable String freeboardNo, @PathVariable String commentNo) {
		try {
			log.info(freeboardNo + "번 게시글 삭제");
			freeBoardService.deleteBoardComment(freeboardNo, commentNo);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
