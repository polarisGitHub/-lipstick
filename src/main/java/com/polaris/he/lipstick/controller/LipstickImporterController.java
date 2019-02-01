package com.polaris.he.lipstick.controller;

import com.polaris.he.lipstick.entity.LipstickUploadData;
import com.polaris.he.framework.importer.UploadImporter;
import com.polaris.he.framework.importer.data.UploadResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * User: hexie
 * Date: 2019-01-16 21:55
 * Description:
 */
@Slf4j
@RestController
@RequestMapping("/api/lipstick/importer")
public class LipstickImporterController {

    @Resource
    private UploadImporter<LipstickUploadData> uploadImporter;

    @PostMapping("/")
    public UploadResult importer(@RequestParam("file") MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("文件为空");
        }

        UploadResult result = uploadImporter.execute(file.getBytes(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        log.info("导入数据，file={},size={},result={}", file.getName(), file.getSize(), result);
        return result;
    }
}