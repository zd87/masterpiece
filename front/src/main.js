import Vue from "vue"
import App from "./App.vue"
import axios from 'axios';
//tells vue that this is dev mode
Vue.config.productionTip = false

/**AXIOS**/
axios.defaults.baseURL = "http://localhost:8085";

/**STORE**/
import Vuex from "vuex";
import pathify from "vuex-pathify";
import modules from "./store"
Vue.use(Vuex)
const store = new Vuex.Store({
    plugins: [pathify.plugin],
    modules: modules
})

/**ROUTER**/
import VueRouter from "vue-router"
import routes from "./router"
Vue.use(VueRouter)
const router = new VueRouter({
    mode: "history",
    base: "/",
    routes
})
router.beforeEach(async (to, from, next)=>{
    /**INITIALIZING**/
    if (!store.state.auth.token) await store.dispatch("auth/init"); 
    //if authorisation constraint   
    if(to.meta?.requiersAuth){
        // if user logged in and token is valid
        if(store.getters["auth/tokenIsValid"] ){
            // check the roles for the target page
            let hasRequiredRole = await to.meta.roles.some(role => store.state.user.user.roles?.includes(role));
            if (!hasRequiredRole){ 
                return next({path: "/"});}
                console.log("redirect permitted");
            return next();
        // if user is not logged in or token has expired, then redirect to login page
        }else{
            console.log("redirect  rejected");
            return next({ path: "/login"});
        }
    }else {
        next();
    }
})

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