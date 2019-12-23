export default class userDao {

    /**
     * 查询所有用户
     * 
     */
    static findUsers() {
        return new Promise((resolve, reject) => {
            Rest.get({ url: Urls.API_USER }).then(({success, code, data, desc}) => {
                if (success) {
                    resolve(data);
                } else {
                    reject(desc);
                }
            });
        });
    }

    /**
     * 查询当前登录用户
     * 
     */
    static findCurrentUser() {
        return new Promise((resolve, reject) => {
            Rest.get({ url: Urls.API_CURRENT_USER }).then(({success, code, data, desc}) => {
                if (success) {
                    resolve(data);
                } else {
                    reject(desc);
                }
            });
        });
    }
}