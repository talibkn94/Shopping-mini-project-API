package com.shopping.Mini.Project.service;

import com.shopping.Mini.Project.entity.JwtSession;
import com.shopping.Mini.Project.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Component
public class JwtSessionService {
    @Autowired
    private SessionRepository sessionRepository;
    public void tokenSave(String email, String token)throws Exception

    {
        JwtSession saveToken= new JwtSession();
        saveToken.setEmail(email);
        saveToken.setToken(token);
        sessionRepository.save(saveToken);
    }
    public String checkToken(String email, String token)
    {
        List<JwtSession> check=sessionRepository.findAllByEmail(email);
        check=(ArrayList<JwtSession>)check.stream().filter(findToken ->findToken.getToken().equals(token)).collect(Collectors.toList());

//        JwtSession check=sessionRepository.findByEmail(email);
        if (check!=null)
       {
           return "Token is valid Go......";
       }
       return null;
    }
    public void changePasswordMethod(String email,String token) throws Exception {
        //find all by email id
        List<JwtSession> deleteFiles=sessionRepository.findAllByEmail(email);
        // Delete all token have same email
        if (deleteFiles == null){
            throw new Exception("Email is no found");
        }
        sessionRepository.deleteAll(deleteFiles);
        JwtSession saveToken = new JwtSession();
        saveToken.setToken(token);
        saveToken.setEmail(email);
        sessionRepository.save(saveToken);
    }
}
