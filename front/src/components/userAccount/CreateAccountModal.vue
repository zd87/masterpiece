<template>
    <div>
        <v-dialog
            v-model="createAccountModal"
            width="400px"
            persistent
        >
            <v-card id="modal">
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
                            :rules="validation.name"
                            prepend-inner-icon="mdi-account"
                            :label="labels.name"
                            :placeholder="placeholders.name"
                            outlined
                            dense
                            required
                            @blur="checkUnicity"
                        />
                        <v-text-field
                            v-model="pwdInput"
                            :type="showPwd? 'type':'password'" 
                            :rules="validation.pwd"
                            prepend-inner-icon="mdi-lock"
                            :append-icon="showPwd ? 'mdi-eye' : 'mdi-eye-off'"
                            :label="labels.pwd"
                            :placeholder="placeholders.pwd"
                            outlined
                            dense
                            required
                            @click:append="showPwd = !showPwd"
                        />
                        <v-text-field
                            v-if="create"
                            v-model="pwdConfirmInput"
                            type="password"
                            :rules="validation.pwdConfirmation"
                            :label="labels.confirmPwd" 
                            :placeholder="placeholders.confirmPwd"
                            outlined
                            dense
                            required
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
                    <v-btn icon class="closeBtn" @click="close">
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                    <div>
                        <span class="smallText">
                            {{ variable.switchFormsText }}
                            <a @click="create=!create">{{ variable.switchFormsBtn }}</a>
                        </span>
                    </div>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>
<script>
import axios from 'axios';
export default {
    props: {
        createAccountModal: {
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
        close(){
            this.isActive=false;
            this.$emit("closeModal", this.isActive);
        },
        submit(){
            const formData = {
                sesameId: this.nameInput,
                pwd: this.pwdInput
            }
            this.checkUnicity; //TODO
            if(this.valid){
                console.log("post form object",formData);
                axios.post(`http://localhost:8085/create_account`, formData)
                    .then(response => { 
                        this.userExists = response.data;
                    })
                    .catch(error => {
                        console.log("ERROR", error);
                    })
            }
            console.log("valid", this.valid);
            
            
        },
        checkUnicity(){
            let boolean;
            axios.get(`http://localhost:8085/create_account/check_unicity/${this.nameInput}`)
                .then(response => { 
                    boolean = response.data;
                    this.userExists= response.data;                    
                })
                .catch(error => {
                    console.log("ERROR", error);
                })
            return boolean;
        }
    }
}
</script>
<style lang="scss" scoped>
    #modal {
        background-color: white;
        position:relative;
        .v-card {
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
    }
    button {
	&.v-btn {
		padding: 0 20px !important;
	}
	&.redBtn{
		background-color: #e3345a !important;
        color: white;
        width: 100%;
        margin-bottom: 24px; 
	}
}
.closeBtn {
	position: absolute;
	top:0;
	right:0;
}
.smallText {
    font-size: 0.9rem;
}
</style>