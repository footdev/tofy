package com.ssafy.tofy.notice.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
