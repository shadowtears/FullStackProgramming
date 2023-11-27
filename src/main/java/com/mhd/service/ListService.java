package com.mhd.service;

import com.mhd.domain.AnimalPreference;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author MouHongDa
 * @date 2023/11/26 19:18
 */
public interface ListService {
    boolean upload(HttpServletRequest request);

    List<AnimalPreference> getAnimalPreferenceList(Integer pageNum, String username, String animal, String reason);

    int count();
}
