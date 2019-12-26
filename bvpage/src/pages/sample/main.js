import Vue    from 'vue';
import App    from './index.vue';
import router from './router';
import store  from './store';
import '@/plugins/iview';

import DemoDao  from '@/../public/static/js/dao/dao';
import UserDao  from '@/../public/static/js/dao/userDao';

Vue.prototype.$DemoDao   = DemoDao;
Vue.prototype.$UserDao   = UserDao;

new Vue({
    render: h => h(App),
    router,
    store,
    created() {
    }
}).$mount('#app');
