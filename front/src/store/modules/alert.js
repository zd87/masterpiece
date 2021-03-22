import { make } from "vuex-pathify";

const state = {
    alert:""
};
const mutations = {
    ...make.mutations(["alert"])
};

const actions = {
    
};

export default {
    namespaced:true,
    state,
    mutations,
    actions
}
