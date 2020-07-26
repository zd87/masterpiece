<template>
    <div>
       <!--update server modal-->
        <v-dialog v-model="updateServer" width="70%">
            <CreateUpdateServer v-model="updateServer" :isUpdate="updateServer" :serverData="chosenItem" @closeUpdate="updateServer=false" />
        </v-dialog>

        <!--tooltips-->
        <!-- <v-tooltip v-model="tooltipHover" top>  
            <span>Click to copy</span>
        </v-tooltip> -->
        <v-tooltip 
            :value="tooltipClick" top 
            :position-x="tooltipX" :position-y="tooltipY"
        >  
            <span>Copied: {{ copied }}</span>
        </v-tooltip>
        <v-data-table 
            :headers="headers"
            :items="servers"
            :expanded.sync="expanded"
            item-key="id" :search="search"
            show-expand :group-by="group? 'perimeter': []"
        >
            <template v-slot:group.header="{ group, toggle, isOpen  }">
                <td :colspan="headers.length">
                    <v-btn @click="toggle" small icon>
                        <v-icon v-if="isOpen">mdi-minus</v-icon>
                        <v-icon v-else>mdi-plus</v-icon>
                    </v-btn>
                    {{ group }}
                </td>
            </template>
            <template v-slot:item.name="{ item }">
                <span :style="{cursor:'pointer'}" v-clipboard:copy="item.name" @click="onCopy($event, item.name)">{{ item.name }}</span>
            </template>
            <template v-slot:item.fullName="{ item }">
                <span :style="{cursor:'pointer'}" v-clipboard:copy="item.fullName" @click="onCopy($event, item.fullName)">{{ item.fullName }}</span>
            </template>
            <template v-slot:item.ip="{ item }">
                <span :style="{cursor:'pointer'}" v-clipboard:copy="item.ip" @click="onCopy($event, item.ip)">{{ item.ip }}</span>
            </template>
            <template v-if="user.isAdmin" v-slot:item.actions="{ item }">
                <div class="d-flex flex-column">
                    <v-btn 
                        v-for="button of buttons" :key="button.text" 
                        class="noHover px-0 py-1 tableActionIcon" text  @click="button.action(item)">
                        <v-icon size="20px mr-3">{{ button.icon }}</v-icon>
                        {{ button.text }}
                    </v-btn>
                </div>
            </template>
            <!--server attributes-->
            <template v-slot:expanded-item="{ item }">
                <td :colspan="headers.length">
                    <!-- <div v-if="item.attributes" class="d-flex justify-center">No attributes {{item.attributes.length}}</div> -->
                    <div class="d-flex flex-wrap">
                        <div v-for="(attribute, index) of item.attributes" :key="index" :style="{width:'19%'}">       
                            <span 
                                :style="{cursor:'pointer'}" v-clipboard:copy="attribute.attrValue" @click="onCopy($event, attribute.attrValue)"
                                @mouseover="tooltipHover = true" @mouseleave="tooltipHover=false" 
                            >
                                <span class="font-weight-bold">{{ attribute.attrName }}: </span>
                                <span>{{ attribute.attrValue }}</span>
                            </span>
                        </div>
                    </div>
                </td>
            </template>
        </v-data-table>
    </div>
</template>
<script>
import { get } from "vuex-pathify"
import CreateUpdateServer from "@/components/serverTable/CreateUpdateServer.vue"
export default {
    components: {
        CreateUpdateServer
    },
    props: {
        search: {
            type:String,
            default:""
        },
        group: {
            type:Boolean,
            default:true
        }
    },
    data (){
        return {
            expanded: [],
            chosenItem:{},
            updateServer:false,
            tooltipHover:false,
            tooltipClick:false,
            tooltipX:null,
            tooltipY:null,
            copied:"",
        }
    },
    computed: {
        servers: get("servers/servers"),
        user: get("user/user"),
        headers() {
            return [
                {text:"Name",       value:"name",       width:"19%",    sortable: true},
                {text:"Full name",  value:"fullName",   width:"19%",    sortable: true},
                {text:"IP",         value:"ip",         width:"19%",    sortable: true},
                {text:"Perimeter",  value:"perimeter",  width:"19%",    sortable: true},
                {text:"Country",    value:"country",    width:"19%",    sortable: true},
                {text:"",           value:"actions"},
                {text: "",          value: "data-table-expand"},
            ];
        },
        buttons() {
            return [
                {text:"Edit",   icon:"mdi-square-edit-outline", action:(item)=>this.openUpdatePopup(item)},
                {text:"Delete", icon:"mdi-trash-can-outline",   action:()=>({})}
            ];
        },
    },
    watch: {
        tooltipClick(){
            if (this.tooltipClick) setTimeout(()=>{this.tooltipClick=false},1000);
        }
    },
    methods: {
        openUpdatePopup(item){
            this.updateServer=true;
            this.chosenItem=item;
        },
        onCopy(event,text){
            //coordinates for the tooltip
            let bb = event.target.getBoundingClientRect();
            let x = bb.x + bb.width;
            if(x>window.innerWidth*0.8) x-=(bb.width+400);
            this.tooltipX=x;
            this.tooltipY=bb.y;

            this.copied=text;
            this.tooltipHover=false;
            this.tooltipClick=true;
        }
    }
}
</script>
<style lang="scss" scoped>
::v-deep .v-data-table__expanded__content {
    box-shadow: none !important;
}
</style>