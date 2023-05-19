package com.ssafy.tofy.user.service;

import com.ssafy.tofy.user.dto.SelectTag;
import com.ssafy.tofy.user.dto.User;
import com.ssafy.tofy.user.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    SqlSession sqlSession;

    @Autowired
    public UserServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void join(User user) {
        sqlSession.getMapper(UserRepository.class).join(user);
    }

    @Override
    public User login(User user) {
        return sqlSession.getMapper(UserRepository.class).login(user);
    }

    @Override
    public User idCheck(String userId) {
        return sqlSession.getMapper(UserRepository.class).idCheck(userId);
    }

    @Override
    public User userInfo(String userId) throws SQLException {
        return sqlSession.getMapper(UserRepository.class).userInfo(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return sqlSession.getMapper(UserRepository.class).getAllUsers();
    }

    @Override
    public void update(User user) {
        sqlSession.getMapper(UserRepository.class).update(user);
    }

    @Override
    public void delete(String userId) {
        sqlSession.getMapper(UserRepository.class).delete(userId);
    }

    @Override
    public void saveRefreshToken(String userId, String refreshToken) throws SQLException {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("token", refreshToken);
        sqlSession.getMapper(UserRepository.class).saveRefreshToken(map);
    }

    @Override
    public Object getRefreshToken(String userid) throws SQLException {
        return sqlSession.getMapper(UserRepository.class).getRefreshToken(userid);
    }

    @Override
    public void deleteRefreshToken(String userId) throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("token", null);
//        sqlSession.getMapper(UserRepository.class).deleteRefreshToken(map);
    }

	@Override
	public void selectTag(Map<String, Object> param) {
		log.info("{} 태그 선택", param.get("userId"));
		sqlSession.getMapper(UserRepository.class).selectTag(param);
	}
	
	@Override
	public SelectTag[] getTag(String userId) {
		log.info("{} 회원입니다.", userId);
		return sqlSession.getMapper(UserRepository.class).getTag(userId);
	}

}
