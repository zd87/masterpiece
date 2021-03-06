<template>
    <div>
        <v-card flat>
            <v-card-title>{{ updateVSCreate.title }}</v-card-title>
            <ValidationErrorsCard v-if="validationErrors" :errors="validationErrors" />
            <v-card-text>
                <v-form v-model="isValid">
                    <!--essential fields-->
                    <v-row>
                        <v-col sm="12" md="6" class="py-0 px-3">
                            <label for="newServer_name">Name*</label>
                            <v-text-field 
                                v-model="server.name" id="newServer_name" 
                                outlined dense :rules="required.concat(length45)" 
                            />
                        </v-col>
                        <v-col sm="12" md="6" class="py-0 px-3">
                            <label for="newServer_fullName">Full name*</label>
                            <v-text-field 
                                v-model="server.fullName" id="newServer_fullName" 
                                outlined dense :rules="required.concat(length45)" 
                            />
                        </v-col>
                        <v-col sm="12" md="6" class="py-0 px-3">
                            <label for="newServer_ip">IP*</label>
                            <v-combobox 
                                v-model="server.ip.ip" id="newServer_ip" 
                                :items="attributeOptions.ips" 
                                outlined dense :rules="required.concat(length45)"
                            />
                        </v-col>
                        <v-col sm="12" md="6" class="py-0 px-3">
                            <label for="newServer_perimeter">Perimeter*</label>
                            <v-combobox 
                                v-model="server.perimeter.name" id="newServer_perimeter" 
                                :items="attributeOptions.perimeters" 
                                outlined dense :rules="required.concat(length45)"
                            />
                        </v-col>
                        <v-col sm="12" md="6" class="py-0 px-3">
                            <label for="newServer_country">Country*</label>
                            <v-combobox 
                                v-model="server.country.name" id="newServer_country" 
                                :items="attributeOptions.countries" 
                                outlined dense :rules="required.concat(length45)"
                            />
                        </v-col>
                    </v-row>
                    <v-divider class="my-5" />
                    <!--additional parameters-->
                    <p class="font-weight-bold mb-4">Additional attributes</p>
                    <v-data-table
                        v-if="server.attributes.length>0"
                        v-model="server.attributes"
                        :headers="headers" :items="server.attributes"
                        hide-default-footer no-data-text="No additional attributes"
                        :items-per-page="-1"
                    >
                        <template v-slot:item.name="{ item }">
                            <v-combobox 
                                v-model="item.name" :items="attributeNameOptions" 
                                outlined dense :rules="required.concat(length45)"
                            /> 
                        </template>
                        <template v-slot:item.value="{ item }">
                            <v-combobox 
                                v-model="item.value" :items="attributeOptions.attributes[item.name]" 
                                outlined dense :rules="required.concat(length255)"
                            />
                        </template>
                        <template v-slot:item.actions="{ item }">
                            <v-btn 
                                text class="noHover px-0 py-1 tableActionIcon" 
                                @click="deleteAttribute(item)"
                            >
                                <v-icon size="20px mr-3">mdi-trash-can-outline</v-icon>
                                Delete
                            </v-btn>
                        </template>

                    </v-data-table>
                </v-form>
                <!--add parameter button-->
                <v-btn 
                    outlined :style="{borderStyle: 'dashed', backgroundColor:'white'}" 
                    @click="addAttribute"
                > 
                    <v-icon left :style="{border: '1px solid', borderRadius:'50%'}">mdi-plus</v-icon>
                    Add attribute
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
import authAxios from '@/axios';
import { get, call } from "vuex-pathify"
import ValidationErrorsCard from "@/components/shared/ValidationErrorsCard.vue"
export default {
    components: {
        ValidationErrorsCard
    },
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
                ip:{
                    ip:""
                },
                perimeter:{
                    name:""
                },
                country:{
                    name:""
                },
                attributes:[]
            },
            required: [v=> !!v || this.$t("validation.required")],
            length45:[v=> v.length <= 45 || this.$t("validation.length45")],
            length255: [v=> v.length <= 255 || this.$t("validation.length255")],
            validationErrors:null
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
                {text:"Attribute name",     value:"name"},
                {text:"Attribute value",    value:"value"},
                {text:"",                   value:"actions",    width:"5%"}
            ];
        },
        attributeNameOptions(){
            return Object.keys(this.attributeOptions.attributes);
        }
    },
    watch: {
        value(){
            if(this.value){
                this.updateContent();
            }
            if(this.validationErrors) this.resetAllErrors();
        }
    },
    mounted(){
        this.updateContent();
    },
    methods: {
        ...call("servers", ["fetchAtrributeOptions", "fetchServers"]),
        async updateContent(){
            await this.fetchAtrributeOptions();
            //deep copy to avoid modifying the server in the parent
            if (this.isUpdate) this.server = JSON.parse(JSON.stringify(this.serverData));
        },
        addAttribute(){
            this.server.attributes.push({
                name:"",
                value:""
            })
        },
        deleteAttribute(item){
            let index=this.server.attributes.indexOf(item);
            this.server.attributes.splice(index,1);
        },
        async submit(){
            let url = this.isUpdate ? `/servers/${this.server.id}` :"/servers";
            let method = this.isUpdate ? authAxios.put : authAxios.post;
            try {
                if(this.validationErrors) this.validationErrors=null; //if previous validation errors, reset them
                const response = await method(url, this.server);
                this.$store.dispatch("alert/add", {response, text:"Changes successfully saved!"});
                this.fetchServers();
                this.updateVSCreate.close();
            }catch(error){
                this.validationErrors=error.response.data;
            }
        },
        resetAllErrors(){
            this.validationErrors = null;
        }
    }
};
</script>
<style lang="scss" scoped>
.v-data-table {
    ::v-deep td {
        padding: 0 16px 0 0 !important;
        vertical-align: baseline;
        border-bottom: none !important;
    }
    ::v-deep th {
        height: auto !important;
        padding: 0 0 10px 0 !important;
        font-size: 1rem !important;
        font-weight: normal !important;
        border-bottom: none !important;
    }
}
::v-deep .v-input {
    &__slot{
        min-height:30px !important;
        .v-text-field__slot{
            input {
                padding: 0px !important;
            }
        }
        .v-select__slot{
            input {
                padding: 0px !important;
            }
            .v-input__append-inner{
                margin-top: 4px !important;
            }
        }
    }
    &__control {
        .v-text-field__details{
            margin-bottom: 2px !important;
        }
    }
}
</style>