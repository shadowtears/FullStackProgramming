package com.mhd.dao;

import com.mhd.domain.AnimalPreference;

import java.util.List;

/**
 * @author MouHongDa
 * @date 2023/11/26 22:11
 */
public interface ListDao {
    boolean addList(AnimalPreference animalPreference);

    List<AnimalPreference> findList(Integer page, String username, String animal, String reason);

    int countList();
}
