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
}