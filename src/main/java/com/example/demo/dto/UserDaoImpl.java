package com.example.demo.dto;

import com.example.demo.validator.MyAnnot;
import com.example.demo.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserDaoImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @MyAnnot
    @Transactional
    public User saveUser(User user){
        entityManager.persist(user);
//        System.out.println(1/0);
        return user;
    }



    public User findUser(Long id){
        return (User) entityManager.find(User.class, id);
    }

    public void deleteUser(User user){
        entityManager.remove(user);
    }

    public void updateUser(User user){
        entityManager.merge(user);
    }
}
