<template>
	<div class="customContainer">
        <div class="mb-5 d-flex justify-pace-between align-center">
            <div class="heading-1 mr-8">Servers</div>
            <!-- download button -->
            <v-btn :href="downloadExcelUrl" text download>
                <v-icon>mdi-download</v-icon>
                Export XLSX
            </v-btn>
            <!-- upload button -->
            <input
                ref="file"
                :key="rendered"
                name="import-file"
                type="file"
                accept=".xlsx"
                hidden
                @input="handleFileUpload"
            >
            <v-btn v-if="user.isAdmin" text @click="ulpoadExcel">
                <v-icon>mdi-upload</v-icon>
                Import XLSX
            </v-btn>
        </div>
        <v-card v-if="user.isAdmin && !createServer" class="mb-5" flat>
            <v-card-text class="d-flex justify-space-between align-baseline py-0">
                <v-col cols="8" class="pa-0 d-flex align-baseline">
                    <v-text-field
                        v-model="search"
                        append-icon="mdi-magnify"
                        label="Search"
                        single-line outlined dense
                        hide-details
                    />
                    <v-switch v-model="group" class="mx-5" inset label="Group by perimeter"></v-switch>
                </v-col>
                
                <v-btn class="outlinedButton mr-3" outlined rounded @click="createServer=true">
                    <v-icon class="mr-3">mdi-plus-circle-outline</v-icon>
                    Add asset
                </v-btn>
            </v-card-text>
        </v-card>
        <CreateUpdateServer v-if="user.isAdmin && createServer" v-model="createServer" class="mb-5"  @closeCreate="createServer=false" />
        <ServerTable :search="search" :group="group" />
        <!-- confirm upload popup -->
        <ConfirmationPopup 
            v-model="confirmationPopup" 
            :text="`Are you sure you want to upload ${file.name}`" 
            title="Confirm upload" confirmBtn="Upload" @confirm="confirmUpload"
        />
    </div>
</template>

<script>
import authAxios from '@/axios';

import ServerTable from "@/components/serverTable/ServerTable.vue"
import CreateUpdateServer from "@/components/serverTable/CreateUpdateServer.vue"
import ConfirmationPopup from "@/components/shared/ConfirmationPopup.vue"
import { get, call } from "vuex-pathify"
export default {
    components: {
        ServerTable,
        CreateUpdateServer,
        ConfirmationPopup
    },
    data (){
        return {
            createServer: false,
            search:"",
            group:false,
            file:"",
            downloadExcelUrl:"http://localhost:8085/api/servers/excel",
            confirmationPopup:false,
            rendered:true //used to reload the file uploader
        }
    },
    computed: {
        servers: get("servers/servers"),
        user: get("user/user"),
    },
    mounted(){
        this.fetchServers();
    },
    methods: {
        ...call("servers", ["fetchServers", "uploadExcelFile"]),
        ulpoadExcel(){
            const uploader = this.$refs.file;
            uploader.click();
        },
        handleFileUpload(){
            this.file = this.$refs.file.files[0]; 
            this.confirmationPopup=true;
        },
        confirmUpload(confirmed){
            if (confirmed){
                let formData = new FormData();
                formData.append("import-file", this.file);
                this.uploadExcelFile(formData);
            }else{
                this.rerenderUpload();
            }
        },
        rerenderUpload(){
            this.file="";
            this.rendered =false;
            this.$nextTick(()=> this.rendered=true);
        }
    }
}
</script>
<style lang="scss" scoped>
.v-btn {
    height: 30px !important;
}
::v-deep .v-input {
    &__slot{
        min-height:30px !important;
        // .v-text-field__slot{
        //     input {
        //         padding: 0px !important;
        //     }
        // }
        .v-input__append-inner{
            margin-top: 4px !important;
        }
    }
    .v-label {
        line-height: 1 !important;
    }
}
.v-btn--text{
    text-transform: none;
}
</style>
