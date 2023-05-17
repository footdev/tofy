package com.ssafy.tofy.freeboard.service;

import java.util.List;

import com.ssafy.tofy.freeboard.dto.FreeBoardCommentDto;
import com.ssafy.tofy.freeboard.dto.FreeBoardDto;

public interface FreeBoardService {
//	void deleteArticlesAll(String userId) throws Exception;
//	List<Board> sortListArticle(Map<String, String> map) throws Exception;
//	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	void writeBoard(FreeBoardDto board) throws Exception;
	List<FreeBoardDto> listBoard() throws Exception;
	FreeBoardDto getBoard(String freeBoardNo) throws Exception;
	void updateHit(String freeBoardNo) throws Exception;
	void updateBoard(FreeBoardDto board) throws Exception;
	void deleteBoard(String boardNo) throws Exception;
	List<FreeBoardCommentDto> listComment(String freeboardNo) throws Exception;
	void writeComment(FreeBoardCommentDto comment);
	void deleteBoardComment(String boardNo, String commentNo);
	void updateComment(FreeBoardCommentDto comment);
}
