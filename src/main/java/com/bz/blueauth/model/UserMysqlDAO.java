package com.bz.blueauth.model;

import java.util.List;

import com.bz.blueauth.dto.pojo.UserPO;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMysqlDAO extends CrudRepository<UserPO, Long> {
   // List<UserPO> findByUserId(String userId);
   // List<UserPO> findByEmail(String email);

   // @Query("SELECT user_name,email,password,first_name,last_name FROM user WHERE user_name=:criteria or email=:criteria")
    List<UserPO> findByUseridOrEmail(@Param("email") String email, @Param("user_name") String userid);
}