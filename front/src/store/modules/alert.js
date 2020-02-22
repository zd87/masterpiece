const state = {
    alert:""
};
const mutations = {
    setAlert(state, data){
        state.alert = data;
    }
};

const actions = {
    
};

export default {
    namespaced:true,
    state,
    mutations,
    actions
}
