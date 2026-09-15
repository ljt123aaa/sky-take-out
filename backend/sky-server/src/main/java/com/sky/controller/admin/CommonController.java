package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Slf4j
@Api(tags = "通用接口")
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result upload(MultipartFile file) throws IOException {
        log.info("文件上传:{}", file);
        try {
            String originalFilename = file.getOriginalFilename();
            String fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

            String filePath = aliOssUtil.upload(file.getBytes(), fileName);
            return Result.success(filePath);
        }catch (Exception e){
            log.error("文件上传失败:{}",e);
            e.printStackTrace(); // 强制在控制台打印红色错误堆栈
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
