import Vue    from 'vue';
import App    from './index.vue';
import router from './router';
import store  from './store';
import '@/plugins/iview';

import DemoDao  from '@/../public/static/dao/dao';

Vue.prototype.$DemoDao   = DemoDao;
new Vue({
    render: h => h(App),
    router,
    store,
    created() {
    }
}).$mount('#app');
