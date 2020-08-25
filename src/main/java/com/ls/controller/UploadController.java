package com.ls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {

    /**
     * 单文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return "file is empty";
        }
        file.transferTo(new File("d:\\ls\\"+file.getOriginalFilename()));
        return "success";
    }

    /**
     * 多文件上传
     * @param multipartFile
     * @return
     */
//    @RequestMapping("upload")
//    public String upload(@RequestParam("file") MultipartFile[] multipartFile){
//        for(MultipartFile file : multipartFile){
//            if(!file.isEmpty()){
//                try {
//                    file.transferTo(new File("d:\\ls\\"+file.getOriginalFilename()));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return "success";
//    }

//    @RequestMapping("/upload")
//    @ResponseBody
//    public String upload(@RequestParam("file") MultipartFile file){
//        if(file.isEmpty()){
//            return "file is empty";
//        }
//        String fileName = file.getOriginalFilename();
//        //设置文件存储路径
//        String filePath = "d:\\ls\\";
//        String path = filePath + fileName;
//        File dest = new File(path);
//        //检测是否存在目录
//        if(!dest.getParentFile().exists()){
//            dest.getParentFile().mkdirs();
//        }
//        try {
//            //文件写入
//            file.transferTo(dest);
//            return "upload success";
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "upload failure";
//    }

}
