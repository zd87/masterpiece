import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../pages/Home.vue'
import RouterView from "../pages/RouterView.vue";
import { i18n } from "../main.js";

Vue.use(VueRouter)

const routes = [
    {
        path: "/:lang",
        component: RouterView,
        beforeEnter(to, from, next) {
            const lang = to.params.lang;
            if (!["en", "fr"].includes(lang)) return next("en");
            if (i18n.locale !== lang) {
            i18n.locale = lang;
            }
            return next();
        },
        children: [
            {
                path: '/',
                name: 'home',
                component: Home
            },
        //   {
        //     path: '/about',
        //     name: 'about',
        //     // route level code-splitting
        //     // this generates a separate chunk (about.[hash].js) for this route
        //     // which is lazy-loaded when the route is visited.
        //     component: () => import(/* webpackChunkName: "about" */ '../pages/About.vue')
        //   }

        ]
    },
    {
        path: "*",
        redirect: "/de"
    }
    
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
