package com.boombone7.boommall.generators;

import com.boombone7.annotations.AppRegisterGenerator;
import com.boombone7.core.wechat.templates.AppRegisterTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.boombone7.boommall",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
