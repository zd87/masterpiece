import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import VueI18n from 'vue-i18n'

import { languages } from './lang/index'
import { defaultLocale } from './lang/index'
const messages = Object.assign(languages)

import "./scss/global.scss"

Vue.config.productionTip = false

Vue.use(VueI18n);
export const i18n = new VueI18n({
    locale:  defaultLocale,
    fallbackLocale: 'en',
    messages
});


new Vue({
    router,
    store,
    vuetify,
    i18n,
    render: h => h(App)
}).$mount('#app')
