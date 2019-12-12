export default class DemoDao {
    static deleteDemo(name) {
        return new Promise((resolve, reject) => {
            Rest.get({ url: '/insert', data: {name: name}}).then(({ data: user, success, message }) => {
                if (success) {
                    resolve(user);
                } else {
                    reject(message);
                }
            });
        });
    }
}