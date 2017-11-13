package com.eshop.commom;

import com.eshop.common.utils.FastDFSClient;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by ADMIN on 2017/11/8.
 */
public class TestFastDfs {

    @Test
    public void testUploadFile() {
        try {
            ClientGlobal.init("E:\\learn\\workspace\\jdmall\\eshop-parent\\eshop-common\\src\\main\\resources\\fdfs_client.conf");
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, null);
            String[] strings = storageClient.upload_file("C:\\Users\\ADMIN\\Pictures\\广告图片\\21.jpg", "jpg", null);
            for (String string : strings) {
                System.out.println(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testFastDFSClient() {
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("E:\\learn\\workspace\\jdmall\\eshop-parent\\eshop-common\\src\\main\\resources\\fdfs_client.conf");
            String jpg = fastDFSClient.uploadFile("C:\\Users\\ADMIN\\Pictures\\广告图片\\20.jpg", "jpg");
            System.out.println(jpg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
