import Vue    from 'vue';
import App    from './index.vue';
import router from './router';
import store  from './store';
import '@/plugins/iview';

import SystemDao  from '@/../public/static/js/dao/systemDao';

Vue.config.productionTip = false;
Vue.prototype.$SystemDao   = SystemDao;

//路由拦截器
//to为向后走的路由对象，包括路由的完整信息
//from为从哪跳来的路由对象
//next()控制路由向下走，重新定义路由跳转的路由nconsole.log(name);ext(‘路由路径)
router.beforeEach((to, from, next) => {
    if (to.name === 'login') {
        next();
    } else {
        // 校验 token 是否存在
        if (localStorage.getItem('JWT_TOKEN')) {
            // console.log('ininni');
            next();
        } else {
            // console.log('goggoo');
            next('/');
        }
    }
});

// 添加请求拦截器
axios.interceptors.request.use(config => {
    // 该代码块主要执行在发送请求之前做些什么
    // 判断是否存在token，如果存在的话，则每个http header都加上token
    if (localStorage.JWT_TOKEN) {
        console.log('执行一次axios请求');
        // config.headers['bv_token'] = localStorage.JWT_TOKEN;
    }
    return config;
},err => {
    return Promise.reject(err);
});

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
    console.log('响应一次axios请求');
    // 对响应数据做点什么
    return response;
  }, function (error) {
    // 对响应错误做点什么
    return Promise.reject(error);
  });

new Vue({
    render: h => h(App),
    router,
    store,
    created() {
    }
}).$mount('#app');
