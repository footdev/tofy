package com.ssafy.tofy.notice.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tofy.freeboard.dto.FreeBoardDto;
import com.ssafy.tofy.notice.dto.NoticeDto;

@Mapper
public interface NoticeService {
	void writeNotice(NoticeDto notice) throws Exception;
	List<NoticeDto> listNotice() throws Exception;
	NoticeDto getNotice(String noticeNo) throws Exception;
	void updateHit(String noticeNo) throws Exception;
	void updateNotice(NoticeDto notice) throws Exception;
	void deleteNotice(String noticeNo) throws Exception;
}
