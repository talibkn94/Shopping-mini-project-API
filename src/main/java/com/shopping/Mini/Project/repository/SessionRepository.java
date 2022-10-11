package com.shopping.Mini.Project.repository;

import com.shopping.Mini.Project.entity.JwtSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<JwtSession,Integer> {


    List<JwtSession> findAllByEmail(String email);
}
