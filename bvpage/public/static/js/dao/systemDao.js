export default class systemDao {
    static deleteDemo(name) {
        return new Promise((resolve, reject) => {
            Rest.get({ url: '/insert', data: {name: name}}).then(({ success, code, data, desc}) => {
                if (success) {
                    resolve({ success, code, data, desc});
                } else {
                    reject(desc);
                }
            });
        });
    }

    /**
     * 登录
     * 
     */
    static login(username, password) {
        return new Promise((resolve, reject) => {
            Rest.create({ url: '/login', data: {username: username, password: password} }).then(({success, code, data, desc}) => {
                if (success) {
                    resolve(data);
                } else {
                    reject(desc);
                }
            });
        });
    }

    /**
     * 注销
     * 
     */
    static logout() {
        return new Promise((resolve, reject) => {
            Rest.get({ url: '/logout' }).then(({success, code, data, desc}) => {
                if (success) {
                    resolve({success, code, data, desc});
                } else {
                    reject(desc);
                }
            });
        });
    }

    /**
     * 生成二位码
     * 
     */
    static codeGenerator() {
        return new Promise((resolve, reject) => {
            Rest.get({ url: Urls.PAGE_CODE }).then(({success, code, data, desc}) => {
                if (success) {
                    resolve(data);
                } else {
                    reject(desc);
                }
            });
        });
    }
}