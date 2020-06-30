import Vue from "vue";
import Vuex from "vuex";
import pathify from "vuex-pathify";
import account from "./modules/account";
import alert from "./modules/alert";
import servers from "./modules/servers";

Vue.use(Vuex)

export default new Vuex.Store({
    plugins: [pathify.plugin],
    modules: {
        account,
        alert,
        servers
    }
})