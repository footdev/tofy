package com.ssafy.tofy.worldcup.repository;

import com.ssafy.tofy.attraction.dto.AttractionDto;
import com.ssafy.tofy.worldcup.dto.WorldCupResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface WorldCupRepository {

    public List<AttractionDto> pickRandomAttractions();
    public void saveWorldCupResult(WorldCupResult worldCupResult);
    public void deleteSelectedWorldCup(WorldCupResult[] worldCupResults);
    public void deleteWorldCupByUserId(String userId);

}
