package com.ssafy.tofy.tag.repository;

import com.ssafy.tofy.tag.dto.SelectTag;
import com.ssafy.tofy.tag.dto.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagRepository {
    public List<Tag> getAllTags() throws Exception;
    public void deleteTagsByUserNo(String userNo) throws Exception;
    public void saveSelectedTag(SelectTag[] selectedTags) throws Exception;
    public List<Tag> getUserTags(String userNo) throws Exception;
}
