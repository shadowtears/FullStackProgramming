package com.mhd.service.impl;

import com.mhd.dao.UserDao;
import com.mhd.dao.impl.UserDaoImpl;
import com.mhd.domain.User;
import com.mhd.service.UserService;

/**
 * @author MouHongDa
 * @date 2023/11/25 16:24
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();


    public boolean login(User user)  {
        return userDao.login(user);
    }
}
