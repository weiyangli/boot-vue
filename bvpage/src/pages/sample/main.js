import Vue    from 'vue';
import App    from './index.vue';
import router from './router';
import store  from './store';

new Vue({
    render: h => h(App),
    router,
    store,
    created() {
    }
}).$mount('#app');
