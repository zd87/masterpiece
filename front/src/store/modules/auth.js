import axios from 'axios';
import moment from "moment";
import { make } from "vuex-pathify";

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
    ...make.mutations(state)
};

const actions = {
    async authenticate({ dispatch }, formData){
        let options= {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        };
        try {
            const response = await axios.post("/oauth/token", formData, options);
            dispatch("updateToken", response.data.access_token);
            dispatch("init");
            dispatch("alert/add", {response, text:"Logged in!"}, {root:true});
        }catch(error){
            throw error;
        }
    },
    async createNewUser({ dispatch }, payload){
        try {
            const response = await axios.post("/api/create_account", payload);
            dispatch("alert/add", {response, text:"Account successfully created!"}, {root:true});
        }catch(error){
            throw error;
        }
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
            await dispatch("user/fetchUser", {}, {root:true});
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
};