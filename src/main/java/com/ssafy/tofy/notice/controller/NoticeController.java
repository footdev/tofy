package com.ssafy.tofy.notice.controller;

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

import com.ssafy.tofy.freeboard.dto.FreeBoardDto;
import com.ssafy.tofy.notice.dto.NoticeDto;
import com.ssafy.tofy.notice.service.NoticeService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = {"*"})
public class NoticeController {
	
	private NoticeService noticeService;

	public NoticeController(@Qualifier("NoticeServiceImpl") NoticeService noticeService) {
		super();
		this.noticeService = noticeService;
	}
	
	@GetMapping("/notice")
	public ResponseEntity<Object> getNotices() {
		try {
			log.info("공지사항 리스트 출력 호출");
			List<NoticeDto> list = noticeService.listNotice();
			
			System.out.println(list.size());
			for (NoticeDto noticeDto : list) {
				log.info(noticeDto.toString());
			}

			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/notice/{noticeNo}")
	public ResponseEntity<Object> getBoard(@PathVariable String noticeNo) {
		try {
			noticeService.updateHit(noticeNo);
			
			NoticeDto noticeDto = noticeService.getNotice(noticeNo);
			
			log.info(noticeDto.getNoticeNo() + "번 공지 조회");
			return ResponseEntity.ok().body(noticeDto);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/notice")
	public ResponseEntity<Object> writeBoard(@RequestBody NoticeDto notice) {
		try {
			noticeService.writeNotice(notice);
			log.info("공지사항 생성 완료");
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/notice")
	public ResponseEntity<Object> modifyBoard(@RequestBody NoticeDto notice) {
		try {
			noticeService.updateNotice(notice);
			log.info(notice.getNoticeNo() + "번 공지 수정");
			
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.info(notice.getNoticeNo() + "번 공지 수정 에러");
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/notice/{noticeNo}")
	public ResponseEntity<Object> deleteBoardComment(@PathVariable String noticeNo) {
		try {
			log.info(noticeNo + "번 공직삭항 삭제");
			noticeService.deleteNotice(noticeNo);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
