package kr.co.jwo.board.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.jwo.board.dao.IBoardDocDAO;
import kr.co.jwo.board.dto.BoardDocDTO;
import kr.co.jwo.common.dao.BaseDaoSupport;

@Repository
public class BoardDocDAOImpl extends BaseDaoSupport implements IBoardDocDAO{

	@Override
	public void insert(BoardDocDTO documentDTO) {
		this.getSqlSession().insert("BoardDoc.insertData", documentDTO);
	}

	@Override
	public void update(BoardDocDTO documentDTO) {
		this.getSqlSession().update("BoardDoc.updateData", documentDTO);
		
	}

	@Override
	public void delete(int docId) {
		this.getSqlSession().delete("BoardDoc.deleteData", docId);
		
	}

	@Override
	public BoardDocDTO selectOne(int docId) {
		return this.getSqlSession().selectOne("BoardDoc.selectOneData", docId);
	}

	@Override
	public List<BoardDocDTO> selectList(Integer mapId) {
		return this.getSqlSession().selectList("BoardDoc.selectListByMapId", mapId);
	}

	
	
}
