import axios from 'axios';
import { make, commit } from "vuex-pathify";

const state = {
    data:[]
};

const mutations = {
    ...make.mutations(["data"])
};

const actions = {
    async  postData({ },payload){
        try{
            const response = await axios.post("", payload)
            console.log("response: ", response);
            commit("setData", response);
        }catch(error){
            console.error(error);
        }

    }
};

export default {
    state,
    mutations,
    actions
}
