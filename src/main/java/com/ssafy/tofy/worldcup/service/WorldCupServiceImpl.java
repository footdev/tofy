package com.ssafy.tofy.worldcup.service;

import com.ssafy.tofy.attraction.dto.AttractionDto;
import com.ssafy.tofy.worldcup.dto.WorldCupResult;
import com.ssafy.tofy.worldcup.repository.WorldCupRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public List<WorldCupResult> pickWorldCupResultsByUserNo(String userNo) {
        return sqlSession.getMapper(WorldCupRepository.class).pickWorldCupResultsByUserNo(userNo);
    }

    @Override
    public void saveWorldCupResult(WorldCupResult worldCupResult) {
        log.info("콘텐츠 id : {} 유저 식별번호 : {}", worldCupResult.getContentId(), worldCupResult.getUserNo());
        sqlSession.getMapper(WorldCupRepository.class).saveWorldCupResult(worldCupResult);
    }

    @Override
    public void deleteSelectedWorldCup(WorldCupResult[] worldCupResults) {
        for (WorldCupResult worldCupResult: worldCupResults) {
            log.info("삭제 할 유저 식별 번호 : {}", worldCupResult.getUserNo());
            log.info("삭제 할 여행지 식별 번호 : {}", worldCupResult.getWorldCupNo());
        }
        sqlSession.getMapper(WorldCupRepository.class).deleteSelectedWorldCup(worldCupResults);
    }

    @Override
    public void deleteWorldCupByUserNo(String userNo) {
        sqlSession.getMapper(WorldCupRepository.class).deleteWorldCupByUserNo(userNo);
    }

    @Override
    public void increaseWinCntByTag(Map<String, Object> params) {
        sqlSession.getMapper(WorldCupRepository.class).increaseWinCntByTag(params);
    }
}
