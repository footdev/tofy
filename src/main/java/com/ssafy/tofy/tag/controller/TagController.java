package com.ssafy.tofy.tag.controller;

import com.ssafy.tofy.tag.dto.SelectTag;
import com.ssafy.tofy.tag.dto.Tag;
import com.ssafy.tofy.tag.service.TagService;
import com.ssafy.tofy.util.Response;
import com.ssafy.tofy.util.Status;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Log4j2
@RestController
public class TagController {

    TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tag")
    public ResponseEntity<Object> getAllTags() {
        Response res = Response.builder()
                .status(Status.SUCCESS.getStatus())
                .message("success all tag get")
                .data(new HashMap<>())
                .build();
        try {
            res.getData().put("tags", tagService.getAllTags());
            log.info("태그의 모든 정보 조회 ");
        } catch (Exception e) {
            log.error("태그 목록 조회 중 에러 {}", e.getMessage());
            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("error all tag get");
        }

        return ResponseEntity.ok()
                .body(res);
    }

    @GetMapping("/tag/{userNo}")
    public ResponseEntity<Object> getUserTags(@PathVariable String userNo) {
        Response res = Response.builder()
                .status(Status.SUCCESS.getStatus())
                .message("success get user tags")
                .data(new HashMap<>())
                .build();
        log.info("{} 번의 유저 선택학 태그 호출", userNo);

        try {
            List<Tag> list = tagService.getUserTags(userNo);
            res.getData().put("tags", list);
            log.info("유저가 선택한 태그 조회 완료");

        } catch (Exception e) {
            log.error("유저가 선택한 태그 조회 에러 {}", e.getMessage());
            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("error user selected tags");
        }

        return ResponseEntity.ok()
                .body(res);
    }

    @PostMapping("/tag")
    public ResponseEntity<Object> saveSelectedTags(@RequestBody SelectTag[] selectedTags) {
        Response res = Response.builder()
                .status(Status.SUCCESS.getStatus())
                .message("success save tags")
                .data(null)
                .build();
        for (SelectTag tag : selectedTags) {
            log.info("태그 번호 = [{}] 유저 번호 = [{}]", tag.getTagNo(), tag.getUserNo());
        }

        try {
            tagService.saveSelectedTag(selectedTags);
            log.info("{} 사용자의 모든 태그 저장 완료", selectedTags[0].getUserNo());

        } catch (Exception e) {
            log.error("태그 저장 중 에러 {}", e.getMessage());
            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("error save tags");
        }

        return ResponseEntity.ok()
                .body(res);
    }
    
}
