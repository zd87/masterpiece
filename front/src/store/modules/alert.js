import { make } from "vuex-pathify";
const codeAlert= {
    unknownStatus:{
        type:"error",
        text: "An unknown error has occured.",
        icon:"mdi-help-rhombus"
    },
    200:{
        type:"success",
        text: "Successfully completed!",
        icon:"mdi-check-circle"
    },
    400:{
        type:"error",
        text: "Bad request",
        icon:"mdi-help-rhombus"
    },
    401:{
        type:"error",
        text: "Action unauthorized",
        icon:"mdi-help-rhombus"
    },
    403:{
        type:"error",
        text: "Forbidden directory",
        icon:"mdi-help-rhombus"
    },
    404:{
        type:"error",
        text: "Not found",
        icon:"mdi-help-rhombus"
    },
    500:{
        type:"error",
        text: "Server error",
        icon:"mdi-server-off"
    }
}
const state = {
    alert:"",
    alerts:[],
    historyLength:10,
    defaultTimeout:10000
};
const mutations = {
    ...make.mutations(state),
    disable(state, index){
        state.alerts[index].active=false;
    },
    push(state,alert){
        state.alerts = [
            ...state.alerts,
            {
                timeout:state.defaultTimeout,
                active:true,
                type:"info",
                ...alert
            }
        ].slice(state.historyLength*-1);
    }
};

const actions = {
    add({commit}, {response, text}){
        if(codeAlert[response?.status]){
            /**build an alert based on the standard template*/
            let alert = {...codeAlert[response.status]};
            if(text) alert.text = text;
            /**add a unique id*/
            alert.uid = `${Date.now()}-${alert.type}-${Math.floor(Math.random()*1000)}`; 
            commit("push", alert);
            /**An alert will auto-disable in 10 sec (see defaultTimeout)*/
            setTimeout(()=>{
                const index = state.alerts.findIndex(el=> el.uid === alert.uid);
                if(index >-1 ){
                    commit("disable", index);
                }
            }, state.defaultTimeout);
        }
    },
    remove({commit}, index){
        commit("disable", index);
    }
    
};

export default {
    namespaced:true,
    state,
    mutations,
    actions
}
