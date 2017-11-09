package com.eshop.manager.controller;

import com.eshop.common.utils.FastDFSClient;
import com.eshop.common.utils.PictureResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by ADMIN on 2017/10/30.
 */
@Controller
@RequestMapping("/pic")
public class PictureController {
    @Value("${FDFS_SERVER_URL}")
    private String FDFS_SERVER_URL;


    @RequestMapping("/upload")
    @ResponseBody
    public PictureResult uploadPicture(MultipartFile uploadFile) throws IOException {
        try {
            //接收上传的文件
            //取扩展名
            String originalFilename = uploadFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //上传到图片服务器
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:fdfs_client.conf");
            String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            url = FDFS_SERVER_URL + url;
            //响应上传图片的url
            PictureResult pictureResult = new PictureResult(0,url);
            return pictureResult;
        } catch (Exception e) {
            e.printStackTrace();
            PictureResult pictureResult = new PictureResult(1,"图片上传失败");
            return pictureResult;
        }
    }
}
