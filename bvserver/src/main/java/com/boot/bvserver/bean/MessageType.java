package com.boot.bvserver.bean;

public enum MessageType {
    MESSAGE_USER(1, "message_user"),           // 普通消息
    MESSAGE_GROUP(2, "message_group"),         // 群组消息
    MESSAGE_System(3, "message_system");       // 系统消息

    private int code;
    private String desc;

    MessageType (int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 根据 code 获取 value
     *
     * @param code
     * @return
     */
    public static String getValueByCode(int code) {
        for (MessageType messageType : MessageType.values()) {
            if (code == messageType.getCode()) {
                return messageType.getDesc();
            }
        }
        return null;
    }
}
