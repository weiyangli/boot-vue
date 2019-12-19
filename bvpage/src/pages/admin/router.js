import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [{
            path: '/',
            name: 'login',
            component: () => import('@/components/Menu'),
        },
        {
            path: '/main',
            name: 'main',
            component: () => import('./main.vue'),
            children: [
                {
                    path: '/user',
                    name: 'user',
                    component: () => import('./subpage/user/users.vue'),
                },
                {
                    path: '/activity',
                    name: 'activity',
                    component: () => import('./subpage/activity/activities.vue'),
                },
                {
                    path: '/analysis',
                    name: 'analysis',
                    component: () => import('./subpage/statistic-analysis/analysis.vue'),
                },
            ]
        }
    ],
});
