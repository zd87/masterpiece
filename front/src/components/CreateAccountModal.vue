<template>
    <div>
        <v-dialog
            v-model="createAccountModal"
            width="500px"
        >
            <v-card
                id="modal"
            >
                <v-card-title>
                    Create Account
                </v-card-title>
                <v-card-text>
                    <v-form
                        ref="form"
                        
                    >
                        <p class="pTitle">SESAME id</p>
                        <v-text-field
                            v-model="nameInput"
                            :rules="nameRules" 
                            outlined
                            dense
                            required
                        />
                        <p class="pTitle">Password</p>
                        <v-text-field
                            v-model="pswInput" 
                            :rules="pswRules"
                            outlined
                            dense
                            required
                        />
                        <p class="pTitle">Confirm password</p>
                        <v-text-field
                            v-model="pswConfirmInput"
                            :rules="pswConfirmRules" 
                            outlined
                            dense
                            required
                        />
                    </v-form>
                </v-card-text>
                <v-card-actions>
                    <v-btn
                        rounded
                        @click="isActive = false"
                    > 
                        Cancel 
                    </v-btn>
                    <v-btn
                        class="redBtn"
                        rounded
                        @submit="submit"
                    > 
                        Create account 
                    </v-btn>
                    <v-btn icon class="closeBtn" @click="isActive = false">
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>
<script>
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
            nameInput:"",
            pswInput:"",
            pswConfirmInput:"",
            nameRules: [
                v => v.length = 9 || "Invalid format. Ex.: a000000"
            ],
            pswRules: [],
            pswConfirmRules: []
        }
    },
    watch: {
        isActive(){
            this.$emit("closeModal", this.isActive);
        },
        createAccountModal(){
            this.isActive = this.createAccountModal;
        }
    },
    methods: {
        submit(){
            const formData = {
                id: this.nameInput,
                psw: this.pswInput
            }
            console.log("form object",formData);
            
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
                margin: 0 auto;
            }
            .pTitle {
                font-size: 1rem;
            }
        }
    }
    
    .redBtn{
        background-color: #d11d53;
        color: white;
        padding: 10px;
    }
    
    .closeBtn {
        position: absolute;
        top:0;
        right:0;
    }
</style>