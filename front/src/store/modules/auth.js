import axios from 'axios';
import moment from "moment";

const state = {
    token:null,
    tokenData:{}
};

const getters = {
    tokenIsValid(){
        return moment().unix() < state.tokenData.exp;
    }
};

const mutations = {
    SET_TOKEN(state, data){
        state.token = data;
    },

    SET_TOKEN_DATA(state, data){
        state.tokenData = data;
    }
};

const actions = {
    authenticate({ dispatch }, formData){
        let options= {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        };
        axios.post(`http://localhost:8085/oauth/token`, formData, options) 
            .then(response => { 
                dispatch("updateToken", response.data.access_token);
                dispatch("init");
            })
            .catch(error => {
                console.log("ERROR", error);
            })
    },
    parseToken({ commit }){
        let parsed = JSON.parse(atob(localStorage.token.split('.')[1]));
        commit("SET_TOKEN_DATA", parsed)
    },
    updateToken({ commit }, token){
        commit("SET_TOKEN", token);
        localStorage.token=token;

        //when logout, reset token data and delete token from local storage
        if(!token) {
            commit("SET_TOKEN_DATA", {});
            localStorage.removeItem("token");
        }
    },
    async init({ getters, dispatch, commit }){
        if (localStorage.token) await dispatch("parseToken");
        if (getters.tokenIsValid) {
            dispatch("user/fetchUser", {}, {root:true});
            commit("SET_TOKEN", localStorage.token);
        }
    }
};

export default {
    namespaced:true,
    state,
    mutations,
    actions,
    getters
}