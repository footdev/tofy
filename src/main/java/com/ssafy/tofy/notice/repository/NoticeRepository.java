package com.ssafy.tofy.notice.repository;

import java.util.List;

import com.ssafy.tofy.freeboard.dto.FreeBoardDto;
import com.ssafy.tofy.notice.dto.NoticeDto;

public interface NoticeRepository {
//	void deleteArticlesAll(String userId) throws Exception;
//	List<Board> sortListArticle(Map<String, String> map) throws Exception;
//	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	void writeNotice(NoticeDto notice) throws Exception;
	List<NoticeDto> listNotice() throws Exception;
	FreeBoardDto getNotice(String noticeNo) throws Exception;
	void updateHit(String noticeNo) throws Exception;
	void updateNotice(NoticeDto notice) throws Exception;
	void deleteNotice(String noticeNo) throws Exception;
}
