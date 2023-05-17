package com.ssafy.tofy.freeboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.tofy.freeboard.dto.FreeBoardCommentDto;
import com.ssafy.tofy.freeboard.dto.FreeBoardDto;
import com.ssafy.tofy.freeboard.repository.FreeBoardRepository;

@Service("FreeBoardServiceImpl")
public class FreeBoardServiceImpl implements FreeBoardService {
	
	private FreeBoardRepository freeBoardRepo;
	
	public FreeBoardServiceImpl(FreeBoardRepository freeBoardRepo) {
		super();
		this.freeBoardRepo = freeBoardRepo;
	}

	@Override
	public void writeBoard(FreeBoardDto board) throws Exception {
		freeBoardRepo.writeBoard(board);
	}

	@Override
	public List<FreeBoardDto> listBoard() throws Exception {
		return freeBoardRepo.listBoard();
	}

	@Override
	public FreeBoardDto getBoard(String boardNo) throws Exception {
		return freeBoardRepo.getBoard(boardNo);
	}

	@Override
	public void updateHit(String boardNo) throws Exception {
		freeBoardRepo.updateHit(boardNo);
	}

	@Override
	public void updateBoard(FreeBoardDto board) throws Exception {
		freeBoardRepo.updateBoard(board);
	}

	@Override
	public List<FreeBoardCommentDto> listComment(String boardNo) throws Exception {
		return freeBoardRepo.listComment(boardNo);
	}

	@Override
	public void deleteBoard(String boardNo) throws Exception {
		freeBoardRepo.deleteBoard(boardNo);
	}

	@Override
	public void deleteBoardComment(String boardNo, String commentNo) {
		freeBoardRepo.deleteComment(boardNo, commentNo);
	}

	@Override
	public void updateComment(FreeBoardCommentDto comment) {
		freeBoardRepo.updateComment(comment);
	}

	@Override
	public void writeComment(FreeBoardCommentDto comment) {
		freeBoardRepo.writeComment(comment);
	}
}
