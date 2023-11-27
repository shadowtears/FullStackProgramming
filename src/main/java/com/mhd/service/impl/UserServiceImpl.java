package com.mhd.service.impl;

import com.mhd.dao.UserDao;
import com.mhd.dao.impl.UserDaoImpl;
import com.mhd.domain.User;
import com.mhd.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

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
