<template>
    <div class="customContainer d-flex justify-center align-center">  
        <v-card id="modal" flat>
            <v-card-title class="d-flex justify-center">
                {{ updateVsCreate.title }}
            </v-card-title>
            <v-card-text>
                <v-form
                    ref="form"
                    v-model="valid"
                >
                    <v-text-field
                        v-if="create"
                        v-model="firstnameInput"
                        :label="labels.firstname"
                        :placeholder="placeholders.firstname"
                        outlined dense required
                        prepend-inner-icon="mdi-account"
                    />
                    <v-text-field
                        v-if="create"
                        v-model="lastnameInput"
                        :label="labels.lastname"
                        :placeholder="placeholders.lastname"
                        outlined dense required
                        prepend-inner-icon="mdi-account"
                    />
                    <v-text-field
                        v-model="loginInput"
                        :rules="validation.name"
                        prepend-inner-icon="mdi-account"
                        :label="labels.login"
                        :placeholder="placeholders.login"
                        outlined dense required
                    />
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
                        @keydown.enter="!create? submit() :''"
                    />
                    <v-text-field
                        v-if="create"
                        v-model="pwdConfirmInput"
                        :type="showConfirmPwd? 'type':'password'"
                        :rules="validation.pwdConfirmation"
                        prepend-inner-icon="mdi-lock"
                        :append-icon="showConfirmPwd ? 'mdi-eye' : 'mdi-eye-off'"
                        :label="labels.confirmPwd" 
                        :placeholder="placeholders.confirmPwd"
                        outlined dense required
                        @click:append="showConfirmPwd = !showConfirmPwd"
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
                    {{ updateVsCreate.title }} 
                </v-btn>
                <v-btn v-if="isModal" icon class="closeIconBtn" @click="close">
                    <v-icon>mdi-close</v-icon>
                </v-btn>
                <div>
                    <span class="smallText">
                        {{ updateVsCreate.switchFormsText }}
                        <a @click="create=!create">{{ updateVsCreate.switchFormsBtn }}</a>
                    </span>
                </div>
            </v-card-actions>
        </v-card>
    </div>
</template>
<script>
import axios from 'axios';
import { get, call } from "vuex-pathify"
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
            showPwd:false,
            showConfirmPwd:false,
            firstnameInput:"",
            lastnameInput:"",
            loginInput:"",
            pwdInput:"",
            pwdConfirmInput:"",
            validation: {
                name: [
                    /**A leter followed by 6 letters */
                    v=> /[a-zA-Z][0-9]{6}/.test(v) || this.$t("account.validation.name.pattern"),
                ],
                pwd: [
                    /**Password must be at least 5 caracters long, contain upper and lower cases and at least one digit */
                    v=> /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{5,}$/.test(v) || this.$t("account.validation.pwd.pattern")
                ],
                pwdConfirmation: [
                    v=> v === this.pwdInput || this.$t("account.validation.confirmPwd.noMatch")
                ]

            },
            labels: {
                firstname: this.$t("account.labels.firstname"),
                lastname: this.$t("account.labels.lastname"),
                login: this.$t("account.labels.login"),
                pwd: this.$t("account.labels.pwd"),
                confirmPwd: this.$t("account.labels.confirmPwd")
            },
            placeholders: {
                firstname: this.$t("account.placeholders.firstname"),
                lastname: this.$t("account.placeholders.lastname"),
                login: this.$t("account.placeholders.login"),
                pwd: this.$t("account.placeholders.pwd"),
                confirmPwd: this.$t("account.placeholders.confirmPwd")
            }
        }
    },
    computed: {
        updateVsCreate() {
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
        },
        authenticated: get("auth/token")
    },
    watch: {
        authenticated(){
            if (this.authenticated) this.redirectToMain();
        }
    },
    methods: {
        ...call("user", ["fetchUser"]),
        ...call("auth", ["authenticate"]),
        close(){
            this.isActive=false;
            this.$emit("closeModal", this.isActive);
        },
        submit(){    
            this.create ? this.createAccount() : this.loginAccount();
        },
        loginAccount(){
            let formData = new FormData();
            formData.set("username",`${this.loginInput}`);
            formData.set("password",`${this.pwdInput}`);
            formData.set("client_id","my-client-app");
            formData.set("grant_type","password");

            this.authenticate(formData);
        },
        createAccount(){
            const payload = {
                firstname:this.firstnameInput,
                lastname:this.lastnameInput,
                login: this.loginInput,
                password: this.pwdInput
            }
            axios.post(`http://localhost:8085/api/create_account`, payload) 
                .then(() => { this.loginAccount() })
                .catch(error => {
                    console.log("ERROR", error);
                })
        },
        redirectToMain(){
            this.$router.push({name:"assets"})
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