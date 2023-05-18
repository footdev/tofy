package com.ssafy.tofy.worldcup.service;

import com.ssafy.tofy.attraction.dto.AttractionDto;
import com.ssafy.tofy.worldcup.dto.WorldCupResult;
import com.ssafy.tofy.worldcup.repository.WorldCupRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class WorldCupServiceImpl implements WorldCupService {

    SqlSession sqlSession;

    public WorldCupServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<AttractionDto> pickRandomAttractions() {
        return sqlSession.getMapper(WorldCupRepository.class).pickRandomAttractions();
    }

    @Override
    public void saveWorldCupResult(WorldCupResult worldCupResult) {
        log.info("콘텐츠 id : {} 유저 식별번호 : {}", worldCupResult.getContentId(), worldCupResult.getUserNo());
        sqlSession.getMapper(WorldCupRepository.class).saveWorldCupResult(worldCupResult);
    }

    @Override
    public void deleteSelectedWorldCup(WorldCupResult[] worldCupResults) {
        sqlSession.getMapper(WorldCupRepository.class).deleteSelectedWorldCup(worldCupResults);
    }

    @Override
    public void deleteWorldCupByUserId(String userId) {
        sqlSession.getMapper(WorldCupRepository.class).deleteWorldCupByUserId(userId);
    }
}
