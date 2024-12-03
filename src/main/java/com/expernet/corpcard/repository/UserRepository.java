package com.expernet.corpcard.repository;

import com.expernet.corpcard.entity.Room;
import com.expernet.corpcard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    @Query(value = "SELECT * FROM tb_user tu ",
            nativeQuery = true)
    List<User> findAllByUserIdNotInAddInfoQuery();
}
