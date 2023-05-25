package com.ssafy.tofy.worldcup.controller;

import com.ssafy.tofy.attraction.dto.AttractionDto;
import com.ssafy.tofy.util.Response;
import com.ssafy.tofy.util.Status;
import com.ssafy.tofy.worldcup.dto.WorldCupResult;
import com.ssafy.tofy.worldcup.service.WorldCupService;

import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
public class WorldCupController {

    WorldCupService worldCupService;

    public WorldCupController(WorldCupService worldCupService) {
        this.worldCupService = worldCupService;
    }

    @GetMapping("/worldcup")
    public ResponseEntity<Object> pickRandomAttractions() {
        Response res = Response.builder()
                .status(Status.SUCCESS.getStatus())
                .message("success randomly picked Attractions")
                .data(new HashMap<>())
                .build();

        try {
            List<AttractionDto> attractions = worldCupService.pickRandomAttractions();
            res.getData().put("attractions", attractions);
            log.info("랜덤한 16개의 관광지 불러오기 성공 ");
        } catch (Exception e) {
            log.error("랜덤한 16개의 관광지 불러오는데에 에러 발생");
            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("error randomly picked Attractions");
        }

        return ResponseEntity.ok()
                .body(res);
    }

    //유저의 월드컵 결과 리스트 조회
    @GetMapping("/worldcup/{userNo}")
    public ResponseEntity<Object> pickWorldCupResultsByUserNo(@PathVariable String userNo) {
        Response res = Response.builder()
                .status(Status.SUCCESS.getStatus())
                .message("success picked worldCupResults")
                .data(new HashMap<>())
                .build();

        List<WorldCupResult> list = new ArrayList<>();
        try {
            list = worldCupService.pickWorldCupResultsByUserNo(userNo);
            res.getData().put("results", list);

            if (list == null || list.isEmpty()) {
                res.setStatus(Status.FAIL.getStatus());
                res.setMessage("There is no worldcup result");
            }
            log.info("식별 번호 {}번의 사용자의 {}개의 월드컵 결과 조회 완료", userNo, list.size());
        } catch (Exception e) {
            log.error("사용자의 월드컵 결과 조회 중 에러 발생 {}", e.getMessage());
            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("pickWorldCupResults error");
        }

        return ResponseEntity.ok()
                .body(res);
    }

    //월드컵 결과 저장
    @PostMapping("/worldcup")
    public ResponseEntity<Object> saveWorldCupResult(@RequestBody WorldCupResult worldCupResult) {
        Response res = Response.builder()
                .status(Status.SUCCESS.getStatus())
                .message("success to save worldcup result")
                .data(null)
                .build();

        log.info("월드컵 결과 생성 contentId = {} userNo = {}", worldCupResult.getContentId(), worldCupResult.getUserNo());

        try {
            worldCupService.saveWorldCupResult(worldCupResult);
            log.info("월드컵 결과 저장 성공");
        } catch (Exception e) {
            log.error("월드컵 결과 저장 중 에러 발생 !! {}", e.getMessage());
            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("worldcup result save error");
        }

        return ResponseEntity.ok()
                .body(res);
    }

    //월드컵 우승 여행지의 우승 횟수 태그별 업데이트
    /*
    *
    *@param contentId = 우승 여행지 id
    *@param tags  = 해당 월드컵을 실행한 유저의 태그번호들 (배열)
    *
    */
    @PostMapping("/worldcup/win")
    public ResponseEntity<Object> increaseWinCntByTag(@RequestBody Map<String, Object> params) {
        Response res = Response.builder()
                .status(Status.SUCCESS.getStatus())
                .message("success win count by tag update")
                .data(null)
                .build();

        log.info("{}번 우승 여행지 우승 횟수 업데이트 호출", params.get("contentId"));

        try {
            worldCupService.increaseWinCntByTag(params);
            log.info("우승 횟수 업데이트 성공");
        } catch (Exception e) {
            log.error("우승 횟수 업데이트 에러 {}", e.getMessage());
            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("error win count by tag update");
        }

        return ResponseEntity.ok()
                .body(res);
    }

    @DeleteMapping("/worldcup")
    public ResponseEntity<Object> deleteWorldCupResult(@RequestBody Map<String, WorldCupResult[]> worldCupResults) {
        Response res = Response.builder()
                .status(Status.SUCCESS.getStatus())
                .message("success to delete worldcup result")
                .data(null)
                .build();

        try {
            worldCupService.deleteSelectedWorldCup(worldCupResults.get("contents"));
            log.info("선택한 {} 개의 월드컵 결과들 삭제 완료", worldCupResults.get("contents").length);
        } catch (Exception e) {
            log.error("선택한 월드컵 결과 삭제 중 에러 발생 {}", e.getMessage());
            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("selected worldcup result delete error");
        }

        return ResponseEntity.ok()
                .body(res);
    }

    @DeleteMapping("/worldcup/{userNo}")
    public ResponseEntity<Object> deleteWorldCupByUserNo(@PathVariable String userNo) {
        Response res = Response.builder()
                .status(Status.SUCCESS.getStatus())
                .message("success to save worldcup result")
                .data(null)
                .build();

        try {
            worldCupService.deleteWorldCupByUserNo(userNo);
            log.info("해당 유저의 모든 월드컵 결과 삭제 완료");
        } catch (Exception e) {
            log.error("유저의 모든 월드컵 결과 삭제 중 에러 발생 {}", e.getMessage());
            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("worldcup result by userID delete error");
        }

        return ResponseEntity.ok()
                .body(res);
    }

}
