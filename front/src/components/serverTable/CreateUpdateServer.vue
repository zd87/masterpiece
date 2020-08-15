<template>
    <div>
        <v-card flat>
            <v-card-title>{{ updateVSCreate.title }}
            </v-card-title>
            <v-card-text>
                <v-form v-model="isValid" ref="form">
                    <!--essential fields-->
                    <v-row>
                        <v-col sm="12" md="6" class="py-0 px-3">
                            <p>Name*</p>
                            <v-text-field v-model="server.name" outlined dense :rules="required" />
                        </v-col>
                        <v-col sm="12" md="6" class="py-0 px-3">
                            <p>Full name*</p>
                            <v-text-field v-model="server.fullName" outlined dense :rules="required" />
                        </v-col>
                        <v-col sm="12" md="6" class="py-0 px-3">
                            <p>IP*</p>
                            <v-text-field v-model="server.ip" outlined dense :rules="required" />
                        </v-col>
                        <v-col sm="12" md="6" class="py-0 px-3">
                            <p>Perimeter*</p>
                            <v-text-field v-model="server.perimeter" outlined dense :rules="required" />
                        </v-col>
                        <v-col sm="12" md="6" class="py-0 px-3">
                            <p>Country*</p>
                            <v-text-field v-model="server.country" outlined dense :rules="required" />
                        </v-col>
                    </v-row>
                    <v-divider class="my-5" />
                    <!--additional parameters-->
                    <p class="font-weight-bold mb-7">Additional parameters</p>
                    <v-data-table
                        v-if="server.attributes.length>0"
                        v-model="server.attributes"
                        :headers="headers" :items="server.attributes"
                        hide-default-footer no-data-text="No additional attributes"
                    >
                        <template v-slot:item.attrName="{ item }">
                            <v-combobox 
                                v-model="item.attrName" :items="attributeOptions.names" outlined dense :rules="required"
                            /> 
                        </template>
                        <template v-slot:item.attrValue="{ item }">
                            <v-combobox 
                                v-model="item.attrValue" :items="attributeOptions.values" outlined dense :rules="required"
                            />
                        </template>
                        <template v-slot:item.actions="{ item }">
                            <v-btn text class="noHover px-0 py-1 tableActionIcon" @click="deleteAttribute(item)">
                                <v-icon size="20px mr-3">mdi-trash-can-outline</v-icon>
                                Delete
                            </v-btn>
                        </template>

                    </v-data-table>
                </v-form>

                <!--add parameter button-->
                <v-btn outlined :style="{borderStyle: 'dashed', backgroundColor:'white'}" @click="addAttribute"> 
                    <v-icon left :style="{border: '1px solid', borderRadius:'50%'}">mdi-plus</v-icon>
                    Add parameter
                </v-btn>
            </v-card-text>
            <v-card-actions class="d-flex justify-end pa-4">
                <!--cancel button-->
                <v-btn class="mx-5" text rounded :ripple="false" @click="updateVSCreate.close">
                    Close
                </v-btn>
                <!--submit button-->
                <v-btn 
                    class="mx-5" color="darkPink white--text" rounded depressed
                    :disabled="!isValid" @click="submit"
                >
                    {{ updateVSCreate.title }}
                </v-btn>
                <!--close button-->
                <v-btn class="closeIcon pa-0 ma-0" icon :ripple="false" @click="updateVSCreate.close">
                    <v-icon>mdi-close</v-icon>
                </v-btn>
            </v-card-actions>
        </v-card>
    </div>
</template>
<script>
import axios from "axios";
import { get, call } from "vuex-pathify"

export default {
    props: {
        value:{
            type: Boolean,
            default: false
        },
        serverData: {
            type: Object,
            default: ()=>({})
        },
        isUpdate: {
            type: Boolean,
            default: false
        }
    },
    data(){
        return {
            addedAttributes: [],
            isValid:false,
            server: {
                id:null,
                name:"",
                fullName:"",
                ip:"",
                perimeter:"",
                attributes:[]
            },
            required: [v=> !!v || this.$t("validation.required")]
        }
    },
    computed: {
        attributeOptions: get("servers/attributeOptions"),
        updateVSCreate(){
            return this.isUpdate ?
            {
                title: "Update asset",
                close: ()=> this.$emit("closeUpdate")
            } : {
                title: "Create asset",
                close: ()=> this.$emit("closeCreate")
            };
        },
        headers() {
            return [
                {text:"Attribute name",     value:"attrName"},
                {text:"Attribute value",    value:"attrValue"},
                {text:"",                   value:"actions",    width:"5%"}
            ];
        },
    },
    watch: {
        value(){
            if(this.value){
                this.updateContent();
            }
        }
    },
    mounted(){
        this.updateContent();
    },
    methods: {
        ...call("servers", ["fetchAtrributeNames", "fetchServers"]),
        updateContent(){
            this.fetchAtrributeNames();
            if (this.isUpdate) this.server = JSON.parse(JSON.stringify(this.serverData));
        },
        addAttribute(){
            this.server.attributes.push({
                attrName:"",
                attrValue:""
            })
        },
        deleteAttribute(item){
            let index=this.server.attributes.indexOf(item);
            this.server.attributes.splice(index,1);
        },
        submit(){
            let url = this.isUpdate ? `http://localhost:8085/api/servers/${this.server.id}` :"http://localhost:8085/api/servers"
            axios.post(url, this.server)
                .then(() => { 
                    console.log("post form object",this.server);
                    this.fetchServers();
                })
                .catch(error => {
                    console.log("ERROR", error);
                })
            this.updateVSCreate.close();
        }

    }
};
</script>
<style lang="scss" scoped>
.v-data-table {
    ::v-deep td {
        padding: 16px 16px 0 0 !important;
        vertical-align: baseline;
        border-bottom: none !important;
    }
    ::v-deep th {
        height: auto !important;
        padding: 0 !important;
        font-size: 1rem !important;
        font-weight: normal !important;
    }
}
</style>