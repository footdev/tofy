package com.ssafy.tofy.freeboard.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tofy.freeboard.dto.FreeBoardCommentDto;
import com.ssafy.tofy.freeboard.dto.FreeBoardDto;

@Mapper
public interface FreeBoardRepository {
//	void deleteArticlesAll(String userId) throws Exception;
//	List<Board> sortListArticle(Map<String, String> map) throws Exception;
//	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	void writeBoard(FreeBoardDto board) throws Exception;
	List<FreeBoardDto> listBoard() throws Exception;
	FreeBoardDto getBoard(String boardNo) throws Exception;
	void updateHit(String boardNo) throws Exception;
	void updateBoard(FreeBoardDto board) throws Exception;
	void deleteBoard(String boardNo) throws Exception;
	List<FreeBoardCommentDto> listComment(String boardNo) throws Exception;
	void writeComment(FreeBoardCommentDto comment);
	void deleteComment(String boardNo, String commentNo);
	void updateComment(FreeBoardCommentDto comment);
}
