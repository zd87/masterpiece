import axios from 'axios';

const state = {
    data:[]
};
const mutations = {
    setData(state, data){
        state.data = data;
    }
};

const actions = {
    async  postData({},payload){
        try{
            const response = await axios.post("", payload)
            console.log("response: ", response);
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
