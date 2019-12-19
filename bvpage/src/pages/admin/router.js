import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [{
            path: '/',
            name: 'login',
            component: () => import('./Login.vue'),
        },
        {
            path: '/main',
            name: 'main',
            component: () => import('@/components/Menu'),
            children: [
                {
                    path: '/user',
                    name: 'user',
                    component: () => import('./subpage/user/users.vue'),
                    children: [
                        {
                            path: '/user-list',
                            name: 'user-list',
                            component: () => import('./subpage/user/user-list.vue'),
                        },
                        {
                            path: '/role-list',
                            name: 'role-list',
                            component: () => import('./subpage/user/role-list.vue'),
                        }
                    ]
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
