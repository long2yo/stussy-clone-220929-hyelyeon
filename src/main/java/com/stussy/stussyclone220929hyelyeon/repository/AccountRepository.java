package com.stussy.stussyclone220929hyelyeon.repository;

import com.stussy.stussyclone220929hyelyeon.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountRepository {
    public int save(User user);

    //User 찾기
    public User findUserByEmail(String email);
}
