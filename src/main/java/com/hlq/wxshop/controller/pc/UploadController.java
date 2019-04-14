package com.hlq.wxshop.controller.pc;

import com.alibaba.fastjson.JSONObject;
import com.hlq.wxshop.common.constant.ComConstant;
import com.hlq.wxshop.common.controller.BaseController;
import com.hlq.wxshop.utils.UUIDUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:HLQ
 * @Date:2019/4/13 17:00
 */
@RestController
@RequestMapping("/pc/upload")
public class UploadController extends BaseController {

    @PostMapping("/uploadImg")
    public Object uploadImg(@PathVariable(value="file") MultipartFile file, HttpServletRequest request){
        String fileName = file.getOriginalFilename();
        String fileExtension = fileName.substring(fileName.indexOf("."));
        String realName = UUIDUtil.getUUID() + fileExtension;
        String uploadPath = ComConstant.IMAGEPATH  + new SimpleDateFormat("yyyyMMdd").format(new Date()) +File.separator;
        //获得程序当前路径
        String realPath = System.getProperty("user.dir") + uploadPath;
        try{
            File f = new File(realPath);
            if (!f.exists()) {
                f.mkdirs();
            }
            File uploadFile = new File(realPath, realName);
            file.transferTo(uploadFile);
        }catch (IllegalStateException | IOException e){
            e.printStackTrace();
        }
        String sqlPath=uploadPath+realName;
        String savePath = getWebRoot() + ComConstant.FILESPATH + uploadPath + realName;
        System.out.println(savePath);
        savePath=savePath.replaceAll("\\\\","/");
        System.out.println(savePath);
        JSONObject obj=new JSONObject();
        obj.put("src", savePath);
        obj.put("sqlPath", sqlPath);
        return obj;

    }
}
