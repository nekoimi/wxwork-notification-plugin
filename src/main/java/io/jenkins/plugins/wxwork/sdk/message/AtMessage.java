package io.jenkins.plugins.wxwork.sdk.message;

import io.jenkins.plugins.wxwork.enums.MessageType;
import io.jenkins.plugins.wxwork.sdk.Message;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * <p>AtMessage</p>
 *
 * @author nekoimi 2022/07/15
 */
public abstract class AtMessage extends AbstractMessage {

    public AtMessage(MessageType msgType) {
        super(msgType);
    }

    public static class AtMessageBody {
        /**
         * <p>文本内容，最长不超过2048个字节，必须是utf8编码</p>
         * <p>markdown内容，最长不超过4096个字节，必须是utf8编码</p>
         */
        private String content;

        /**
         * <p>手机号列表，提醒手机号对应的群成员(@某个成员)，@all表示提醒所有人</p>
         */
        private Set<String> mentionedMobileList;

        public AtMessageBody() {
        }

        public AtMessageBody(String content, Set<String> mentionedMobileList) {
            this.content = content;
            this.mentionedMobileList = mentionedMobileList;
        }

        public String getContent() {
            return content;
        }

        public Set<String> getMentionedMobileList() {
            return mentionedMobileList;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setMentionedMobileList(Set<String> mentionedMobileList) {
            this.mentionedMobileList = mentionedMobileList;
        }

        @Override
        public String toString() {
            return "Body{" +
                    "content='" + content + '\'' +
                    ", mentionedMobileList=" + mentionedMobileList +
                    '}';
        }
    }

    public abstract static class AtMessageBuilder {
        private String content;
        private Set<String> mentionedMobileList;

        public AtMessageBuilder() {
            this.mentionedMobileList = new HashSet<>();
        }

        public AtMessageBuilder(String content, Set<String> mentionedMobileList) {
            this.content = content;
            this.mentionedMobileList = Optional.ofNullable(mentionedMobileList).orElse(new HashSet<>());
        }

        /**
         * <p>构建消息</p>
         *
         * @param body
         * @return
         */
        abstract protected Message messageBuild(AtMessageBody body);

        public AtMessageBuilder content(String content) {
            this.content = content;
            return this;
        }

        public AtMessageBuilder at(Set<String> mentionedMobileList) {
            this.mentionedMobileList = mentionedMobileList;
            return this;
        }

        public AtMessageBuilder addAt(String mobile) {
            this.mentionedMobileList.add(mobile);
            return this;
        }

        public Message build() {
            return messageBuild(new AtMessageBody(content, mentionedMobileList));
        }
    }
}
