<template>
    <div>
        <v-dialog
            v-model="createAccountModal"
            width="400px"
            persistent
        >
            <v-card
                id="modal"
            >
                <v-card-title class="d-flex justify-center">
                    Create Account
                </v-card-title>
                <v-card-text>
                    <v-form
                        ref="form"
                        v-model="valid"
                    >
                        <v-text-field
                            v-model="nameInput"
                            :rules="nameRules"
                            prepend-inner-icon="mdi-account"
                            label="SESAME id" 
                            outlined
                            dense
                            required
                        />
                        <v-text-field
                            v-model="pwdInput"
                            type="password" 
                            :rules="pwdRules"
                            prepend-inner-icon="mdi-lock"
                            label="Password" 
                            outlined
                            dense
                            required
                        />
                        <v-text-field
                            v-model="pwdConfirmInput"
                            type="password"
                            :rules="pwdConfirmRules"
                            label="Confirm password" 
                            outlined
                            dense
                            required
                        />
                    </v-form>
                </v-card-text>
                <v-card-actions class="d-flex flex-column justify-center">
                    <v-btn
                        class="redBtn"
                        rounded
                        @click="submit"
                    > 
                        Create account 
                    </v-btn>
                    <v-btn icon class="closeBtn" @click="isActive = false">
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                    <div>
                        <span class="smallText">
                            Already have an account?
                            <a>Sign in</a>
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
            isActive: this.createAccountModal,
            valid:false,
            userExists: false,
            nameInput:"",
            pwdInput:"",
            pwdConfirmInput:"",
            nameRules: [
                !this.userExists || "Account with this id already exists",
                v=> v.length === 7 || "Please enter valid SESAME id. Ex.:a001122"
            ],
            pwdRules: [
                v=> v.length >= 5 || "Password must be at least 5 caracters"
            ],
            pwdConfirmRules: [
                v=> v === this.pwdInput || "Passwords are not matching"
            ]
        }
    },
    watch: {
        isActive(){
            this.$emit("closeModal", this.isActive);
        },
        createAccountModal(){
            this.isActive = this.createAccountModal;
        },
        nameInput(){
            console.log("from inout watch", this.userExists);
            
            this.userExists = false;
        }
    },
    methods: {
        submit(){
            const formData = {
                sesameId: this.nameInput,
                pwd: this.pwdInput
            }
            this.checkUnicity; //TODO
            if(this.valid){
                console.log("form object",formData);
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
            console.log("hello from blur");
            
            axios.get(`http://localhost:8085/create_account/check_unicity/${this.nameInput}`)
                .then(response => { 
                    this.userExists = response.data;
                })
                .catch(error => {
                    console.log("ERROR", error);
                })
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