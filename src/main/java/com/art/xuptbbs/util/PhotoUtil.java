package com.art.xuptbbs.util;

import com.art.xuptbbs.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

public class PhotoUtil {

    public static String savePhoto(MultipartFile file, String filePath){


        var originalFilename = file.getOriginalFilename();

        var typeName = originalFilename.substring(originalFilename.lastIndexOf('.'));
        var storeFilename = UUID.randomUUID().toString()+typeName;
        var dest = new File(filePath  + storeFilename);

        if ( !dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest.getAbsoluteFile());
        }catch (IOException e){
            throw new BusinessException("上传文件失败");
        }
        return storeFilename;
    }

    public static boolean deletePhoto(String filePath){
        var dest = new File(filePath);
        if (dest.exists()){
           return dest.delete();
        }
        return false;
    }
}
