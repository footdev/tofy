package com.ssafy.tofy.tag.service;

import com.ssafy.tofy.tag.dto.SelectTag;
import com.ssafy.tofy.tag.dto.Tag;
import com.ssafy.tofy.tag.repository.TagRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    SqlSession sqlSession;

    public TagServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Tag> getAllTags() throws Exception {
        return sqlSession.getMapper(TagRepository.class).getAllTags();
    }

    @Override
    public void deleteTagsByUserNo(String userNo) throws Exception {
        sqlSession.getMapper(TagRepository.class).deleteTagsByUserNo(userNo);
    }

    @Override
    public void saveSelectedTag(SelectTag[] selectedTags) throws Exception {
        sqlSession.getMapper(TagRepository.class).saveSelectedTag(selectedTags);
    }

    @Override
    public void updateSelectedTag(SelectTag[] selectedTags, String userNo) throws Exception {
        deleteTagsByUserNo(userNo);
        saveSelectedTag(selectedTags);
    }
}
