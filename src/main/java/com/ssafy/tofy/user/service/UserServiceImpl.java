package com.ssafy.tofy.user.service;

import com.ssafy.tofy.user.dto.User;
import com.ssafy.tofy.user.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
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
        Map<String, String> map = new HashMap<String, String>();
        map.put("userid", userId);
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
        sqlSession.getMapper(UserRepository.class).deleteRefreshToken(map);
    }
}
