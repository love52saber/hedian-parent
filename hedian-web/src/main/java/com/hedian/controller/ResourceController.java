package com.hedian.controller;

import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.util.ComUtil;
import com.hedian.util.FileUtil;
import com.hedian.util.FtpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author hedian
 * @since on 2018/5/11.
 */
@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    @Value("${ftp.host}")
    private String ftpHost;
    @Value("${ftp.port}")
    private Integer ftpPort;
    @Value("${ftp.username}")
    private String ftpUserName;
    @Value("${ftp.password}")
    private String ftpPassWord;
    @Value("${ftp.basepath}")
    private String ftpBasePath;

    @PostMapping
    public PublicResult uploadResource(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        boolean result = false;
        String newFileName = null;
        if (!ComUtil.isEmpty(multipartFile)) {
            newFileName = UUID.randomUUID() + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            // 上传文件以日期为单位分开存放，可以提高图片的查询速度
            String filePath = "/" + new SimpleDateFormat("yyyy").format(new Date()) + "/"
                    + new SimpleDateFormat("MM").format(new Date()) + "/"
                    + new SimpleDateFormat("dd").format(new Date());
            result = FtpUtil.uploadFile(ftpHost, ftpPort, ftpUserName, ftpPassWord, ftpBasePath, filePath, newFileName, multipartFile.getInputStream());
        }
        return result ? new PublicResult(PublicResultConstant.SUCCESS, newFileName) : new PublicResult(PublicResultConstant.ERROR, null);
    }

    @DeleteMapping
    public PublicResult deleteResource(@RequestParam("filePaths") List<String> filePaths) {
        if (!ComUtil.isEmpty(filePaths) && filePaths.size() != 0) {
            for (String item : filePaths) {
                if (!FileUtil.deleteUploadedFile(item)) {
                    return new PublicResult<String>(PublicResultConstant.ERROR, null);
                }
            }
        }
        return new PublicResult<String>(PublicResultConstant.SUCCESS, null);
    }

}
