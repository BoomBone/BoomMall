package com.boombone7.boommall.generators;


import com.boombone7.annotations.EntryGenerator;
import com.boombone7.core.wechat.templates.WXEntryTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.boombone7.boommall",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
