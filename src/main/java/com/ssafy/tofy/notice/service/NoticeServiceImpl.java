package com.ssafy.tofy.notice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.tofy.freeboard.dto.FreeBoardDto;
import com.ssafy.tofy.notice.dto.NoticeDto;
import com.ssafy.tofy.notice.repository.NoticeRepository;

@Service("NoticeServiceImpl")
public class NoticeServiceImpl implements NoticeService {

	private NoticeRepository noticeRepo;
	
	public NoticeServiceImpl(NoticeRepository noticeRepo) {
		super();
		this.noticeRepo = noticeRepo;
	}

	@Override
	public void writeNotice(NoticeDto notice) throws Exception {
		noticeRepo.writeNotice(notice);
	}

	@Override
	public List<NoticeDto> listNotice() throws Exception {
		return noticeRepo.listNotice();
	}

	@Override
	public NoticeDto getNotice(String noticeNo) throws Exception {
		return noticeRepo.getNotice(noticeNo);
	}

	@Override
	public void updateHit(String noticeNo) throws Exception {
		noticeRepo.updateHit(noticeNo);
	}

	@Override
	public void updateNotice(NoticeDto notice) throws Exception {
		noticeRepo.updateNotice(notice);
	}

	@Override
	public void deleteNotice(String noticeNo) throws Exception {
		noticeRepo.deleteNotice(noticeNo);
	}
}
