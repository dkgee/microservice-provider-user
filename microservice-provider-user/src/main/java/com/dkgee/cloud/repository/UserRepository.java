package com.dkgee.cloud.repository;

import com.dkgee.cloud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 描述：
 * 作者: JinHuaTao
 * 时间：2017/10/16 9:16
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
