package com.polaris.he.lipstick.importer;

import com.polaris.he.lipstick.importer.converter.UploadExtensionExtensionEnum;
import com.polaris.he.lipstick.importer.data.UploadResult;
import com.polaris.he.lipstick.importer.data.UploadValidateResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * User: hexie
 * Date: 2019-01-16 22:35
 * Description:
 */
public abstract class AbstractUploadImporter<T> implements UploadImporter<T> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    abstract protected List<T> byteConvert(byte[] data, String extension);

    abstract protected UploadValidateResult validate(List<T> data);

    abstract protected void resolved(List<T> data);

    @Override
    @SuppressWarnings("unchecked")
    public UploadResult execute(byte[] data, String extension, UploadImportListener<T> listener) {
        Assert.notNull(UploadExtensionExtensionEnum.valueOf(StringUtils.lowerCase(extension)), "不支持上传类型");

        Optional<UploadImportListener> optionalListener = Optional.ofNullable(listener);
        try {
            optionalListener.ifPresent(l -> l.onBegin(data));
            List<T> upload = byteConvert(data, extension);

            optionalListener.ifPresent(l -> l.onBeforeValidate(data, upload));
            UploadValidateResult validateResult = validate(upload);
            optionalListener.ifPresent(l -> l.onAfterValidate(upload, validateResult));

            // 判断是否异步上传，暂不支持
            boolean isAsync = optionalListener.map(l -> l.asyncOrNot(upload)).orElse(false);

            optionalListener.ifPresent(l -> l.onBeforeResolve(upload));
            resolved(upload);

            optionalListener.ifPresent(UploadImportListener::onEnd);

            return new UploadResult(isAsync ? "async" : "sync", UUID.randomUUID().toString());
        } catch (Exception e) {
            logger.error("数据上传失败", e);
            optionalListener.ifPresent(l -> l.onException(e));
            throw e;
        }
    }
}