package com.ls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class DownloadController {

    /**
     * 使用ResponseEntity进行文件下载  未实现
     * @param request
     * @return
     */
//    @RequestMapping("/download")
//    public ResponseEntity<byte[]> download(HttpServletRequest request){
//
//        //获取要下载的文件的路径(可以是本地文件或者服务器地址)
//        ServletContext servletContext = request.getServletContext();
//        //本次项目使用的是本地的一个文件
//        String realPath = servletContext.getRealPath("D:\\ls\\1.txt");
//        //通过io流对文件进行读写
//        FileInputStream fileInputStream = null;
//        byte[] bytes = null;
//        try {
//            fileInputStream = new FileInputStream(realPath);
//            //创建byte数组进行存储字节
//            bytes = new byte[fileInputStream.available()];
//            fileInputStream.read(bytes);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                fileInputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        //设置响应头
//        HttpHeaders httpHeaders = new HttpHeaders();
//        //描述文件类型以及地址(如果文件通过参数传递可直接进行拼串)
//        httpHeaders.set("Context-Disposition","attachment:filename=1.txt");
//        return new ResponseEntity<byte[]>(bytes,httpHeaders, HttpStatus.OK);
//    }


    /**
     * 使用Java中的File文件资源进行下载文件
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        // 设置文件名，根据业务需要替换成要下载的文件名
        String fileName = "1.txt";
        if (fileName != null) {
            //设置文件路径
            String realPath = "D:\\ls\\";
            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");
                // 设置响应头，描述文件类型以及文件地址
                response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fileInputStream = null;
                //创建了缓冲流进行读写
                BufferedInputStream bufferedInputStream = null;
                try {
                    fileInputStream = new FileInputStream(file);
                    bufferedInputStream = new BufferedInputStream(fileInputStream);
                    //创建输出对象
                    OutputStream outputStream = response.getOutputStream();
                    int length = 0;
                    while ((length = bufferedInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, length);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}
