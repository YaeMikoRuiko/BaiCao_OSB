package com.wen.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;

import com.wen.common.Result;
import com.wen.common.ThumbnailConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;

import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@RestController
@RequestMapping("/files")
public class FileController {
    @Value("${server.port}")
    private String port;

    private static final String ip = "http://localhost";

    /**
     * 上传接口
     * @param file
     * @return
     */

    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();//获取源文件的名称
        //名称去除类型,先获取后缀
        System.out.println(originalFilename);
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //删除后缀
        // 将后缀修改为.jpg
        String newFilename = originalFilename.replace(extension, ".jpg") ;
//        定义文件的唯一标识（前缀）
        String flag = IdUtil.fastSimpleUUID();
        String rootFilePath = "E:/Java/vue-OS/vue/public/src_img/" + flag + "_" + newFilename;//获取文件上传的路径
           System.out.println("地址="+rootFilePath);
        try {
            // 读取上传的图片
            BufferedImage originalImage = ImageIO.read(file.getInputStream());

            // 调整图片尺寸为100x150
            BufferedImage resizedImage = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);
            resizedImage.getGraphics().
                    drawImage(originalImage.
                            getScaledInstance(150, 150, BufferedImage.SCALE_SMOOTH), 0, 0, 150, 150, null);

            // 将调整后的图片保存到指定路径
            File outputfile = new File(rootFilePath);
            ImageIO.write(resizedImage, "jpg", outputfile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = "/src_img/"+ flag + "_" + newFilename;
        return Result.success(url);//返回结果url
    }

    @PostMapping("/uploadLunbo")
    public Result<?> uploadLunbo(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();//获取源文件的名称
        //名称去除类型,先获取后缀
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //删除后缀
        // 将后缀修改为.png
        String newFilename = originalFilename.replace(extension, ".jpg") ;
        System.out.println(newFilename);
        // 定义文件的唯一标识（前缀）
        String flag = IdUtil.fastSimpleUUID();
        String rootFilePath = "E:/Java/vue-OS/vue/public/src_img/" + flag + "_" + newFilename;//获取文件上传的路径
        System.out.println(rootFilePath);
        try {
            FileUtil.writeBytes(file.getBytes(),rootFilePath);//把文件写入该路径
        } catch (IOException e) {
            e.printStackTrace();
        }
        //上传时对图片进行压缩
        String pic="/src_img/"+flag+"_"+newFilename;// 原图地址
        String rec="E:/Java/vue-OS/vue/public"+pic;
        String tar="E:/Java/vue-OS/vue/public/img";//压缩图地址
        ThumbnailConfig.of(new File(rec)).identifyCompress(ThumbnailConfig.ratios[1])
                .outputFormat("jpg").toFiles(new File(tar), null);
        String url = "/img/"+ flag + "_" + newFilename;
        return Result.success(url);//返回结果url
    }

}
