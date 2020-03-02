import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            component: () => import('./main.vue'),
            redirect: '/solt/parent',
        },
        {
            path: '/tranf',
            component: () => import('./tranf.vue'),
            // redirect: '/echarts',
        },
        {
            path: '/file',
            component: () => import('./upload.vue'),
            // redirect: '/echarts',
        },
        {
            path: '/pdf',
            component: () => import('./pdf.vue'),
            // redirect: '/echarts',
        },
        {
            path: '/solt/parent',
            component: () => import('./solt-parent.vue'),
            // redirect: '/echarts',
        },
    ],
});
