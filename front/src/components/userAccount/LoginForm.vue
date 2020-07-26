<template>
    <div class="customContainer d-flex justify-center align-center">  
        <v-card id="modal" flat>
            <v-card-title class="d-flex justify-center">
                {{ variable.title }}
            </v-card-title>
            <v-card-text>
                <v-form
                    ref="form"
                    v-model="valid"
                >
                    <v-text-field
                        v-model="nameInput"
                        
                        prepend-inner-icon="mdi-account"
                        :label="labels.name"
                        :placeholder="placeholders.name"
                        outlined dense required
                    />
                    <!-- <v-text-field
                        v-model="nameInput"
                        :rules="validation.name"
                        prepend-inner-icon="mdi-account"
                        :label="labels.name"
                        :placeholder="placeholders.name"
                        outlined
                        dense
                        required
                        @blur="checkUnicity"
                    /> -->
                    <v-text-field
                        v-model="pwdInput"
                        :type="showPwd? 'type':'password'" 
                        :rules="validation.pwd"
                        prepend-inner-icon="mdi-lock"
                        :append-icon="showPwd ? 'mdi-eye' : 'mdi-eye-off'"
                        :label="labels.pwd"
                        :placeholder="placeholders.pwd"
                        outlined dense required
                        @click:append="showPwd = !showPwd"
                    />
                    <v-text-field
                        v-if="create"
                        v-model="pwdConfirmInput"
                        type="password"
                        :rules="validation.pwdConfirmation"
                        :label="labels.confirmPwd" 
                        :placeholder="placeholders.confirmPwd"
                        outlined dense required
                    />
                </v-form>
            </v-card-text>
            <v-card-actions class="d-flex flex-column justify-center">
                <v-btn
                    class="redBtn"
                    :disabled="!valid"
                    rounded
                    @click="submit"
                > 
                    {{ variable.title }} 
                </v-btn>
                <!-- <v-btn
                    class="redBtn"
                    rounded
                    @click="submit3"
                > 
                    authentication
                </v-btn> -->
                <v-btn v-if="isModal" icon class="closeIconBtn" @click="close">
                    <v-icon>mdi-close</v-icon>
                </v-btn>
                <div>
                    <span class="smallText">
                        {{ variable.switchFormsText }}
                        <a @click="create=!create">{{ variable.switchFormsBtn }}</a>
                    </span>
                </div>
                <!--authorisation exercise-->
                <!-- <div class="d-flex flex-column">
                    <a @click="submit2('http://localhost:9090/api/public/hello')">/api/public/hello</a>
                    <a @click="submit2('http://localhost:9090/api/userInfo')">/api/userInfo</a>
                    <a @click="submit2('http://localhost:9090/api/private/user')">/api/private/user</a>
                    <a @click="submit2('http://localhost:9090/api/private/admin')">/api/private/admin</a>
                    <a @click="submit2('http://localhost:9090/api/private/authenticated')">/api/private/authenticated</a>
                </div> -->
            </v-card-actions>
        </v-card>
    </div>
</template>
<script>
import axios from 'axios';
import { call } from "vuex-pathify"
export default {
    props: {
        isModal: {
            type: Boolean,
            default: false
        }
    },
    data(){
        return {
            isActive: false,
            create: false,
            valid:false,
            userExists: false,
            showPwd:false,
            nameInput:"",
            pwdInput:"",
            pwdConfirmInput:"",
            validation: {
                name: [
                    v=> /[a-zA-Z][0-9]{6}/.test(v) || this.$t("account.validation.name.pattern"),
                    ()=> !this.userExists || this.$t("account.validation.name.unicity"),
                ],
                pwd: [
                    v=> /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{5,}$/.test(v) || this.$t("account.validation.pwd.pattern")
                ],
                pwdConfirmation: [
                    v=> v === this.pwdInput || this.$t("account.validation.confirmPwd.noMatch")
                ]

            },
            labels: {
                name: this.$t("account.labels.name"),
                pwd: this.$t("account.labels.pwd"),
                confirmPwd: this.$t("account.labels.confirmPwd")
            },
            placeholders: {
                name: this.$t("account.placeholders.name"),
                pwd: this.$t("account.placeholders.pwd"),
                confirmPwd: this.$t("account.placeholders.confirmPwd")
            }
        }
    },
    computed: {
        variable() {
            return this.create? 
                {
                    title:this.$t("account.create.title"),
                    switchFormsText:this.$t("account.create.switchFormsText"),
                    switchFormsBtn: this.$t("account.create.switchFormsBtn")
                }:{
                    title:this.$t("account.login.title"),
                    switchFormsText:this.$t("account.login.switchFormsText"),
                    switchFormsBtn: this.$t("account.login.switchFormsBtn")
                }
        }
    },
    watch: {
    },
    methods: {
        ...call("user", ["fetchUser","fetchUser2"]),
        close(){
            this.isActive=false;
            this.$emit("closeModal", this.isActive);
        },
        submit(){    
            this.create ? this.createAccount() : this.loginAccount();
        },
        async loginAccount(){
            let formData = new FormData();
            formData.set("username",`${this.nameInput}`);
            formData.set("password",`${this.pwdInput}`);
            formData.set("client_id","my-client-app");
            formData.set("grant_type","password");

            let options= {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            };

            await axios.post(`http://localhost:8085/oauth/token`, formData, options) //autorisation exercise 
                .then(response => { 
                    localStorage.token=response.data.access_token;
                    this.fetchUser().then(this.redirectToMain());
                })
                .catch(error => {
                    console.log("ERROR", error);
                })
        },
        createAccount(){
            const payload = {
                login: this.nameInput,
                password: this.pwdInput
            }
            axios.post(`http://localhost:8085/api/create_account`, payload) 
                .then(response => { 
                    this.userExists = response.data;
                })
                .catch(error => {
                    console.log("ERROR", error);
                })
        },
        redirectToMain(){
            this.$router.push({name:"assets"});
        },
        //autorisation exercise 
        submit2(url){
            let options= {
                headers: {
                    "Authorization" : "Bearer "+localStorage.token
                }
            };
            
            axios.get(url, options) 
                .then(response => { 
                    console.log(response);
                })
                .catch(error => {
                    console.log("ERROR", error);
                })
            
        },
        //autorisation exercise 
        submit3(){
            let formData = new FormData();
            formData.set("username","SHAKESPEARE");
            formData.set("password","password");
            formData.set("client_id","my-client-app");
            formData.set("grant_type","password");
            
            let options= {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            };

            axios.post(`http://localhost:9090/oauth/token`, formData, options) //autorisation exercise 
                .then(response => { 
                    console.log(response);
                    localStorage.token=response.data.access_token;
                    this.fetchUser2();
                })
                .catch(error => {
                    console.log("ERROR", error);
                })

        }
    }
}
</script>
<style lang="scss" scoped>
    .v-card {
        width: 500px;
        &__title {
            font-size: 1.5rem;
            padding:24px;
        }
        &__actions{
            padding: 0 24px 24px;
        }
        .pTitle {
            font-size: 1rem;
        }
    }
    
    button {
	&.v-btn {
		padding: 0 20px !important;
	}
	&.redBtn{
        background-color: #e3345a !important;
        // background-color: $dark-pink !important;
        color: white;
        width: 100%;
        margin-bottom: 24px; 
	}
}
.closeIconBtn {
	position: absolute;
	top:0;
	right:0;
}
.smallText {
    font-size: 0.9rem;
}
</style>