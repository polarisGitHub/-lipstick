package com.polaris.he.application.mvc.argument;

import com.polaris.he.application.utils.EncryptionUtils;
import com.polaris.he.framework.annotation.PathVariableEncryption;
import com.polaris.he.framework.utils.SpringContextUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.PathVariableMethodArgumentResolver;

/**
 * User: hexie
 * Date: 2019-02-02 23:53
 * Description:
 */
public class PathVariableEncryptionMethodArgumentResolver extends PathVariableMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PathVariableEncryption.class);
    }

    @Override
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
        String ret = (String) super.resolveName(name, parameter, request);
        return (ret != null ? EncryptionUtils.AESDecode(ret, SpringContextUtils.getProperty("encryption.aes.password")) : null);
    }

    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        PathVariableEncryption ann = parameter.getParameterAnnotation(PathVariableEncryption.class);
        Assert.state(ann != null, "No PathVariableEncryption annotation");
        return new PathVariableEncryptionNamedValueInfo(ann);
    }

    private static class PathVariableEncryptionNamedValueInfo extends NamedValueInfo {
        public PathVariableEncryptionNamedValueInfo(PathVariableEncryption annotation) {
            super(annotation.name(), annotation.required(), ValueConstants.DEFAULT_NONE);
        }
    }
}