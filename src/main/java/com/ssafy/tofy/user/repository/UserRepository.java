package com.ssafy.tofy.user.repository;

import com.ssafy.tofy.user.dto.User;
import org.mapstruct.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserRepository {

    public void join(User user);
    public User login(User user);
    public User idCheck(String userId);
    public User userInfo(String userId) throws SQLException;
    public List<User> getAllUsers();
    public void update(User user);
    public void delete(String userId);
    public void saveRefreshToken(Map<String, String> map) throws SQLException;
    public Object getRefreshToken(String userid) throws SQLException;
    public void deleteRefreshToken(Map<String, String> map) throws SQLException;
//	public void selectTag(Map<String, Object> param);
//	public SelectTag[] getTag(String userId);
	
}
