package com.ssafy.tofy.tag.service;

import com.ssafy.tofy.tag.dto.SelectTag;
import com.ssafy.tofy.tag.dto.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TagService {

    public List<Tag> getAllTags() throws Exception;
    public void deleteTagsByUserNo(String userNo) throws Exception;
    public void saveSelectedTag(SelectTag[] selectedTags) throws Exception;
    public void updateSelectedTag(SelectTag[] selectedTags, String userNo) throws Exception;
}
