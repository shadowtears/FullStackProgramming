package com.mhd.util;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.codec.Base64;
import com.mhd.domain.Captcha;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author MouHongDa
 * @date 2023/11/25 21:19
 */
public class MyCaptchaUtil {
    public Captcha generateCaptchaUtil() {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 5);
        String code = circleCaptcha.getCode();
        Captcha captcha = new Captcha();
        captcha.setCode(code);
        captcha.setImg(circleCaptcha.getImageBase64());
        return captcha;
    }

}
