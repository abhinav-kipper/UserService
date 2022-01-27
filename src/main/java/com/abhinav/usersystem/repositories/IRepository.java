package com.abhinav.usersystem.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface IRepository<Data> {

    void save(Data data);

    Data update(Data data);

    Boolean findEmail(String emailAddress);

    Boolean findPhone(Long phone);

    Data retrieve(String id);

    void delete(String userId);
}