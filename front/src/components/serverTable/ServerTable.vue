<template>
    <div>
       
        <!-- <v-tooltip v-model="tooltipHover" top>  
            <span>Click to copy</span>
        </v-tooltip> -->
        <v-tooltip v-model="tooltipClick" top :close-delay="2" open-on-hover >  
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
            
            <template v-slot:item.actions="{ item }">
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
                    <div class="d-flex flex-wrap">
                        <v-col v-for="(attribute, index) of item.attributes" :key="index" cols="2">       
                            <span 
                                :style="{cursor:'pointer'}" v-clipboard:copy="attribute.attrValue" @click="onCopy(attribute.attrValue)"
                                @mouseover="tooltipHover = true" @mouseleave="tooltipHover=false" 
                            >
                                <span class="font-weight-bold">{{ attribute.attrName }}: </span>
                                <span>{{ attribute.attrValue }}</span>
                            </span>
                        </v-col>
                    </div>
                </td>
            </template>
        </v-data-table>
    </div>
</template>
<script>
import { get } from "vuex-pathify"
export default {
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
            headers: [
                {text:"Name",       value:"name",       /*width:"23%",*/    sortable: true},
                {text:"Full name",  value:"fullName",   /*width:"23%",*/    sortable: true},
                {text:"IP",         value:"ip",         /*width:"23%",*/    sortable: true},
                {text:"Perimeter",  value:"perimeter",    /*width:"23%",*/    sortable: true},
                {text:"Country",    value:"country",    /*width:"23%",*/    sortable: true},
                {text:"",           value:"actions"},
                {text: "",          value: "data-table-expand", width: "5%" },
            ],
            buttons: [
                {text:"Edit",   icon:"mdi-square-edit-outline", action:(item)=>this.openMetaPopup(item)},
                {text:"Delete", icon:"mdi-trash-can-outline",   action:()=>({})}
            ],
            expanded: [],
            chosenItem:{},
            tooltipHover:false,
            tooltipClick:false,
            tooltipX:null,
            tooltipY:null,
            copied:""
        }
    },
    computed: {
        servers: get("servers/servers"),
    },
    methods: {
        openMetaPopup(item){
            this.metaPopup=true;
            this.chosenItem=item;
        },
        onCopy(text){
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