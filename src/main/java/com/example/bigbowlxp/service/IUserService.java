package com.example.bigbowlxp.service;


import com.example.bigbowlxp.model.User;

import java.util.List;

public interface IUserService extends ICrudService<User,Long>{
    List<User> findByName(String name);
}
