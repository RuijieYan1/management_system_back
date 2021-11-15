package com.yxtl.business.util.file;

import com.yxtl.business.constant.Constant;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author px
 * @date 2021/10/19 9:33
 */
@Service
public class FileUtil {

    /**
     * @description: 生成一个唯一的ID
     * @param
     * @return: java.lang.String
     */
    public static String createUUID () {
        String s = UUID.randomUUID().toString().substring(24);
        return s.toUpperCase(); //toUpperCase()字符串小写转大写
    }

    /**
     * @description: 得到一个保证不重复的临时文件名
     * @return: java.lang.String
     */
    public static String createTmpFileName(String suffix) {
        //日期格式化输出
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String datestr = sdf.format(new Date());
        String name = datestr + "-" + createUUID() + "." + suffix; //得到日期-随机字符.文件后缀
        return name;
    }

    /**
     * @description: 得到文件的后缀名
     * @param fileName 原始文件名
     * @return: java.lang.String
     */
    public static String fileSuffix(String fileName) {
        //lastIndexOf() 方法可返回一个指定的字符串值最后出现的位置，在一个字符串中的指定位置从后向前搜索。
        int p = fileName.lastIndexOf('.');
        if(p >= 0)
        {
            return fileName.substring(p+1).toLowerCase(); //toLowerCase()字符串大写转小写
        }
        return "";
    }

    public File transferToBindFile(MultipartFile multipartFile) {
        String realName = multipartFile.getOriginalFilename();
        System.out.println("绑定--原始文件名: " + realName);
        String suffix = fileSuffix(realName);
        System.out.println("绑定--原始文件后缀名: " + suffix);
        String tempFileName = createTmpFileName(suffix);
        System.out.println("绑定--临时文件名: " + tempFileName);
        File file = new File(Constant.BINDEXCELPATH + tempFileName);
        System.out.println("绑定--文件路径: " + file);
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public File transferToUnBindFile(MultipartFile multipartFile) {
        String realName = multipartFile.getOriginalFilename();
        System.out.println("解绑--原始文件名: " + realName);
        String suffix = fileSuffix(realName);
        System.out.println("解绑--原始文件后缀名: " + suffix);
        String tempFileName = createTmpFileName(suffix);
        System.out.println("解绑--临时文件名: " + tempFileName);
        File file = new File(Constant.UNBINDEXCELPATH + tempFileName);
        System.out.println("解绑--文件路径: " + file);
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public File transferToImage(MultipartFile multipartFile) {
        String realName = multipartFile.getOriginalFilename();
        System.out.println("上传图片--原始文件名: " + realName);
        String suffix = fileSuffix(realName);
        System.out.println("上传图片--原始文件后缀名: " + suffix);
        String tempFileName = createTmpFileName(suffix);
        System.out.println("上传图片--临时文件名: " + tempFileName);
        File file = new File(Constant.IMAGEPATH + tempFileName);
        System.out.println("上传图片--文件路径: " + file);
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}
