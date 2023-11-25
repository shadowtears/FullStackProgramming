package com.mhd.dao;

import com.mhd.domain.User;

/**
 * @author MouHongDa
 * @date 2023/11/25 16:00
 */
public interface UserDao {
    boolean login(User user);
}
