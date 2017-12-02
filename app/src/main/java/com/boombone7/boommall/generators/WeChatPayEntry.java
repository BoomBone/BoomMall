package com.boombone7.boommall.generators;


import com.boombone7.annotations.PayEntryGenerator;
import com.boombone7.core.wechat.templates.WXPayEntryTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.boombone7.boommall",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
