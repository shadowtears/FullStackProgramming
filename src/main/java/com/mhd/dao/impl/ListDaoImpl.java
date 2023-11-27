package com.mhd.dao.impl;

import com.mhd.constant.PageConstant;
import com.mhd.dao.ListDao;
import com.mhd.domain.AnimalPreference;
import com.mhd.domain.User;
import com.mhd.util.JDBCUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author MouHongDa
 * @date 2023/11/26 22:12
 */
public class ListDaoImpl implements ListDao {
    public boolean addList(AnimalPreference animalPreference) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "INSERT INTO AnimalPreference(username, favorite_animal, reason, image_url)VALUES (?, ?, ?, ?)";

            ps = conn.prepareStatement(sql);
//          String passWord = decode(user.getPassword());
            ps.setString(1, animalPreference.getUserName());
            ps.setString(2, animalPreference.getFavoriteAnimal());
            ps.setString(3, animalPreference.getReason());
            ps.setString(4, animalPreference.getImage());
            int update = ps.executeUpdate();
            if (update != 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return flag;
    }

    @Override
    public int countList() {
        int totalPages = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT count(*) FROM AnimalPreference";

            ps = conn.prepareStatement(sql);
//          String passWord = decode(user.getPassword());
            ResultSet set = ps.executeQuery();
            set.next();
            totalPages = set.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return totalPages;
    }

    public List<AnimalPreference> findList(Integer pageNum, String username, String animal, String reason) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AnimalPreference> list = new ArrayList<AnimalPreference>();
        try {
            conn = JDBCUtil.getConnection();
            String baseSql = "SELECT username,favorite_animal,reason,image_url FROM AnimalPreference WHERE 1=1";
            StringBuilder searchSql = new StringBuilder();
            if (!username.equals("")) {
                searchSql.append(" and username like '%").append(username).append("%'");
            }
            if (!animal.equals("")) {
                searchSql.append(" and favorite_animal like'%").append(animal).append("%'");
            }
            if (!reason.equals("")) {
                searchSql.append(" and reason like '%").append(reason).append("%'");
            }
            String sql = baseSql + searchSql + " limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (pageNum - 1) * PageConstant.PAGE_SIZE);
            ps.setInt(2, PageConstant.PAGE_SIZE);
            rs = ps.executeQuery();
            while (rs.next()) {
                AnimalPreference animalPreference = new AnimalPreference();
                animalPreference.setUserName(rs.getString("username"));
                animalPreference.setFavoriteAnimal(rs.getString("favorite_animal"));
                animalPreference.setReason(rs.getString("reason"));
                animalPreference.setImage(convertImageToBase64Str(rs.getString("image_url")));
                list.add(animalPreference);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return list;
    }

    public String convertImageToBase64Str(String imgUrl) {
        ByteArrayOutputStream baos = null;
        try {
            //获取图片类型
            String suffix = imgUrl.substring(imgUrl.lastIndexOf(".") + 1);
            //构建文件
            File imageFile = new File(imgUrl);
            //通过ImageIO把文件读取成BufferedImage对象
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            //构建字节数组输出流
            baos = new ByteArrayOutputStream();
            //写入流
            ImageIO.write(bufferedImage, "png", baos);
            //通过字节数组流获取字节数组
            byte[] bytes = baos.toByteArray();
            //获取JDK8里的编码器Base64.Encoder转为base64字符
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
