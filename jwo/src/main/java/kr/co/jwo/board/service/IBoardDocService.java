package kr.co.jwo.board.service;

import java.util.List;

import kr.co.jwo.board.dto.BoardDocDTO;

public interface IBoardDocService {
	public void add(BoardDocDTO documentDTO);
	public void edit(BoardDocDTO documentDTO);
	public void remove(int docId);
	public BoardDocDTO view(int docId);
	public List<BoardDocDTO> list(Integer mapId);
}