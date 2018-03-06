package com.terry.Repository;

import com.terry.Bean.Pswd;
import org.springframework.data.jpa.repository.JpaRepository;

/***
 * *
 * 名称：     PwdRepository.
 * 作者：     Terry Tan
 * 创建时间：  on 2017/9/8.
 * 说明：     
 * *
 ***/

public interface PwdRepository extends JpaRepository<Pswd, String> {

    Pswd findByUsername(String name);
}
