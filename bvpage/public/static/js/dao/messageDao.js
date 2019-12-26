export default class messageDao {

    /**
     * 发送消息
     * 
     * @param userId
     * @param chatId
     * @param content
     * @param type
     */
    static sendMessage({ userId, chatId, content, type }) {
        return new Promise((resolve, reject) => {
            Rest.get({ url: Urls.API_SEND_MESSAGE, data: { userId: userId, chatId: chatId, content: content, type: type } })
            .then(({success, code, data, desc}) => {
                if (success) {
                    resolve(data);
                } else {
                    reject(desc);
                }
            });
        });
    }

    /**
     * 拉取消息
     * 
     * @param chatId
     */
    static pullMessages(chatId, type) {
        return new Promise((resolve, reject) => {
            Rest.get({ url: Urls.API_PULL_MESSAGE, pathVariables: { chatId: chatId, type: type } })
            .then(({success, code, data, desc}) => {
                if (success) {
                    resolve(data);
                } else {
                    reject(desc);
                }
            });
        });
    }

    /**
     * 创建聊天群
     * 
     * @param chatGroup
     */
    static createdGroup(chatGroup) {
        return new Promise((resolve, reject) => {
            Rest.create({ url: Urls.API_MESSAGE, data: chatGroup, json: true })
            .then(({success, code, data, desc}) => {
                if (success) {
                    resolve(success);
                } else {
                    reject(desc);
                }
            });
        });
    }

    /**
     * 查询当前用户加入的所有群聊
     * 
     */
    static findGroupByUserId() {
        return new Promise((resolve, reject) => {
            Rest.get({ url: Urls.API_MESSAGE })
            .then(({success, code, data, desc}) => {
                if (success) {
                    resolve(data);
                } else {
                    reject(desc);
                }
            });
        });
    }
}