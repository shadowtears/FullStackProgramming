package com.mhd.service.impl;

import com.mhd.dao.ListDao;
import com.mhd.dao.impl.ListDaoImpl;
import com.mhd.domain.AnimalPreference;
import com.mhd.domain.User;
import com.mhd.service.ListService;
import com.mhd.util.MySessionContext;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import static com.mhd.constant.UpLoadFileConstant.UPLOAD_PATH;
import static com.mhd.constant.UpLoadFileConstant.UPLOAD_TEMP_PATH;

/**
 * @author MouHongDa
 * @date 2023/11/26 19:20
 */
public class listServiceImpl implements ListService {
    private ListDao listDao = new ListDaoImpl();
    private static MySessionContext myc = MySessionContext.getInstance();

    public List<AnimalPreference> getAnimalPreferenceList(Integer pageNum, String username, String animal, String reason) {
        return listDao.findList(pageNum, username, animal, reason);
    }

    @Override
    public int count() {
        return listDao.countList();
    }

    public boolean upload(HttpServletRequest request) {
        //创建文件上传保存的路路径，在WEB-INF路径下是安全的，用户无法直接访问上传文件
        File uploadFile = new File(UPLOAD_PATH);
        //判断目录是否存在
        if (!uploadFile.exists()) {
            uploadFile.mkdir();
        }
        //临时路径,如果文件好过了预期大小，就把他放到一个临时文件中
        File tempFile = new File(UPLOAD_TEMP_PATH);
        //判断目录是否存在
        if (!tempFile.exists()) {
            tempFile.mkdir();
        }
        boolean uploadFlag = false;
        boolean saveFlag = false;
        AnimalPreference animalPreference = new AnimalPreference();
        try {
            //1、创建DiskFileItemFactory对象，处理文件上传路径或大小的限制
            DiskFileItemFactory factory = getDiskFileItemFactory(tempFile);
            //2、获取ServletFileUpload
            ServletFileUpload upload = getServletFileUpload(factory);
            //3、处理上传的文件
            uploadFlag = uploadParseRequest(upload, request, UPLOAD_PATH,animalPreference);
            if (uploadFlag)
                saveFlag = listDao.addList(animalPreference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadFlag & saveFlag;
    }

    public static DiskFileItemFactory getDiskFileItemFactory(File tempFile) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //通过这个工厂设置一个缓冲区，当上传的文件大于这个缓冲区的时候，将他放到临时文件中
        factory.setSizeThreshold(1024 * 1024); //缓冲区大小为1M
        factory.setRepository(tempFile);//临时文件保存的目录，需要一个File
        return factory;
    }

    public static ServletFileUpload getServletFileUpload(DiskFileItemFactory factory) {
        ServletFileUpload upload = new ServletFileUpload(factory);
        //处理乱码问题
        upload.setHeaderEncoding("utf-8");
        //设置单个文件的最大值10M
        upload.setFileSizeMax(1024 * 1024 * 10);
        //设置总共能够上传文件的大小
        upload.setSizeMax(1024 * 1024 * 10);
        return upload;
    }

    public static boolean uploadParseRequest(ServletFileUpload upload, HttpServletRequest request, String uploadPath,AnimalPreference animalPreference)
            throws FileUploadException, IOException {
        String msg = "";
        //把前端请求解析，封装成一个FileItem对象
        List<FileItem> fileItems = upload.parseRequest(request);
        String userName = "";
        //遍历每一个请求
        for (FileItem fileItem : fileItems) {
            //判断用户上传的表单是不是带文件的表单
            if (fileItem.isFormField()) {
                String name = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8");
                if (name.equals("sessionId")) {
                    HttpSession session = myc.getSession(value);
                    if (session == null)
                        return false;
                    else {
                        User user = (User) session.getAttribute("User");
                        userName = user.getUsername();
                        animalPreference.setUserName(userName);
                    }
                }
                if (name.equals("question1"))
                    animalPreference.setFavoriteAnimal(value);
                if (name.equals("question2"))
                    animalPreference.setReason(value);
//                System.out.println(name + ":" + value);
            } else {
                //拿到文件名字
                String uploadFileName = fileItem.getName();
                System.out.println("上传的文件名：" + uploadFileName);
                //如果文件名不合法(name的值为空)，那么直接continue
                if (uploadFileName.trim().equals("") || uploadFileName == null) {
                    continue;
                }
                //获得上传的文件名
                String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
                //可以使用UUID（唯一识别通用码）保证uuidPath唯一
                String uuidPath = UUID.randomUUID().toString();
                //真实存在的路径
                String realPath = uploadPath + "\\" + userName;
                //给每个文件创建一个对应的文件夹，这样的话再不改变名的基础上，重名的文件也可以被存储
                File realPathFile = new File(realPath);
                if (!realPathFile.exists()) {
                    realPathFile.mkdir();
                }
                //获得文件上传的流
                InputStream inputStream = fileItem.getInputStream();
                //创建一个文件输出流
                //realPath是真实的文件夹
                String filePath = realPath + "\\" + uuidPath + ".jpg";
                FileOutputStream fos = new FileOutputStream(filePath);
                animalPreference.setImage(filePath);
                //创建一个缓冲区
                byte[] buffer = new byte[1024 * 1024];
                //判断是否读取完毕
                int len = 0;
                while ((len = inputStream.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                //关闭流
                fos.close();
                inputStream.close();
                fileItem.delete();//上传成功，清除临时文件
            }
        }
        return true;
    }
}
