<template>
	<div class="customContainer">
        <div class="mb-5 d-flex justify-pace-between align-center">
            <div class="heading-1 ">User Management</div>
        </div>
        
        <!--user table-->
        <v-data-table
            :headers="headers"
            :items="users"
        >
            <template v-slot:item.roles="{ item }">
                <div v-for="role of item.roles" :key="role.code">{{ role.code }}</div>
            </template>
            <!-- <template v-slot:item.actions="{ item }">
                <div class="d-flex flex-column">
                    <v-btn 
                        v-for="button of buttons" :key="button.text" 
                        class="noHover px-0 py-1 tableActionIcon" text  @click="button.action(item)">
                        <v-icon size="20px mr-3">{{ button.icon }}</v-icon>
                        {{ button.text }}
                    </v-btn>
                </div>
            </template> -->
        </v-data-table>
        <!--modal update roles-->
    </div>
</template>

<script>
import { get, call } from "vuex-pathify"
export default {
    components: {

    },
    data (){
        return {
    
        }
    },
    computed: {
        users: get("userManagement/users"),
        headers() {
            return [
                {text:"First name", value:"firstname",  width:"30%",    sortable: true},
                {text:"Last name",  value:"lastname",   width:"30%",    sortable: true},
                {text:"Roles",      value:"roles",      width:"30%",    sortable: false},
                // {text:"",           value:"actions"}
            ];
        },
        // buttons() {
        //     return [
        //         {text:"Edit",   icon:"mdi-square-edit-outline", action:(item)=>this.openUpdatePopup(item)},
        //         {text:"Delete", icon:"mdi-trash-can-outline",   action:()=>({})}
        //     ];
        // },
    },
    mounted(){
        this.fetchUsers();
    },
    methods: {
        ...call("userManagement", ["fetchUsers"]),
        openUpdatePopup(item){
            
        },
    }
}
</script>
<style lang="scss" scoped>

</style>
