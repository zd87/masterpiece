import { make } from "vuex-pathify";
// import axios from "axios";
import { $store } from '@/main'
const state = {
    navItems:[
        {
            label: "About",
            to:"/about"
        },
        {
            label: "Assets",
            to:"/",
            meta: {requiresAuth:true}
        }
    ],
};

const getters = {

};

const mutations = {
    ...make.mutations([])
};

const actions = {
    
};

export default {
    namespaced:true,
    state,
    getters,
    mutations,
    actions
}