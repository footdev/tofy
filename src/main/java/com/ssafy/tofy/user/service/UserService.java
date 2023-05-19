package com.ssafy.tofy.user.service;

import com.ssafy.tofy.user.dto.SelectTag;
import com.ssafy.tofy.user.dto.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserService {

    public void join(User user);
    public User login(User user);
    public User idCheck(String userId);
    public User userInfo(String userId) throws SQLException;
    public List<User> getAllUsers();
    public void update(User user);
    public void delete(String userId);
    public void saveRefreshToken(String userId, String refreshToken) throws SQLException;
    public Object getRefreshToken(String userid) throws SQLException;
    public void deleteRefreshToken(String userId) throws SQLException;
	public void selectTag(Map<String, Object> param);
	public SelectTag[] getTag(String userId);

}
