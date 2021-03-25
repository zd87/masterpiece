import Vue from "vue"
import App from "./App.vue"
import axios from 'axios';
//tells vue that this is dev mode
Vue.config.productionTip = false

/**AXIOS**/
axios.defaults.baseURL = "http://localhost:8085";

/**ROUTER**/
import VueRouter from "vue-router"
import routes from "./router"
Vue.use(VueRouter)
const router = new VueRouter({
    mode: "history",
    base: "/",
    routes
})
router.beforeEach((to, from, next)=>{
    //if role constraint   
    if(to.meta?.requiersAuth){
        if(store.getters["auth/tokenIsValid"]){
            console.log("redirect permitted");
            return next();
        }else{
            console.log("redirect  rejected");
            return next({ path: '/login'});
        }
    }else {
        next();
    }
})

/**STORE**/
import Vuex from "vuex";
import pathify from "vuex-pathify";
import modules from "./store"
Vue.use(Vuex)
const store = new Vuex.Store({
    plugins: [pathify.plugin],
    modules: modules
})
/**INITIALIZING**/

//check authenication at every mount
store.dispatch("auth/init");

/**i18n**/
import VueI18n from "vue-i18n"
import { languages } from "./lang/index"
import { defaultLocale } from "./lang/index"
const messages = Object.assign(languages)
Vue.use(VueI18n);
export const i18n = new VueI18n({
    locale:  defaultLocale,
    fallbackLocale: "en",
    messages
});

/**VUETIFY**/
import vuetify from "./plugins/vuetify"

/**CSS**/
import "./scss/styles.scss"

/**OTHER**/
import VueClipboard from "vue-clipboard2";  
Vue.use(VueClipboard); 



const app = new Vue({
    router,
    store,
    vuetify,
    i18n,
    render: h => h(App)
}).$mount("#app")

export const {$store, $vuetify, $i18n, $router} = app;