package io.jenkins.plugins.wxwork.sdk.message;

import io.jenkins.plugins.wxwork.enums.MessageType;
import io.jenkins.plugins.wxwork.sdk.Message;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * <p>AbstractMessage</p>
 *
 * @author nekoimi 2022/07/15
 */
public abstract class AbstractMessage implements Message {
    /**
     * <p>消息类型</p>
     */
    protected final MessageType msgType;

    public AbstractMessage(MessageType msgType) {
        this.msgType = msgType;
    }

    @Override
    public MessageType messageType() {
        return msgType;
    }

    @Override
    public String toJson() {
        return "toJson";
    }

    @Override
    public byte[] toBytes() {
        return Optional.ofNullable(toJson()).orElse("").getBytes(StandardCharsets.UTF_8);
    }
}
