// State   : 所有的状态都要存储到 state 对象中
// Mutation: 应该是同步的操作
// Action  : 可以是异步的操作
// Getters : 从 state 中派生出一些状态方便访问，例如对列表进行过滤并计数
// 参考文档 https://vuex.vuejs.org/zh-cn/getters.html

import Vue  from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        token: null,
    },
    mutations: {
        // 登录成功将, token保存在localStorage中
        SET_LOGIN: (state, data) => {
            localStorage.setItem('JWT_TOKEN', data)
            state.token = data;
        },
        // 退出登录将, token清空
        SET_LOGOUT: (state) => {
            localStorage.removeItem('JWT_TOKEN');
            state.token = null
        }
    }
});
