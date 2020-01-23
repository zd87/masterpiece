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
                    <p class="pTitle">SESAME id</p>
                    <v-text-field
                        v-model="nameInput" 
                        outlined
                        dense
                    />
                    <p class="pTitle">Password</p>
                    <v-text-field
                        v-model="pswInput" 
                        outlined
                        dense
                    />
                    <p class="pTitle">Confirm password</p>
                    <v-text-field
                        v-model="pswConfirmInput" 
                        outlined
                        dense
                    />
                </v-card-text>
                <v-card-actions>
                    <v-btn
                        @click="isActive = false"
                    > 
                        Cancel 
                    </v-btn>
                    <v-btn
                        class="redBtn"
                        :light="false"
                        @click="isActive = false"
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
            pswConfirmInput:""
        }
    },
    watch: {
        isActive(){
            this.$emit("closeModal", this.isActive);
        },
        createAccountModal(){
            this.isActive = this.createAccountModal;
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
    .v-btn {
        &.redBtn{
            background-color: #d11d53;
            color: white;
        }
    }
    .closeBtn {
        position: absolute;
        top:0;
        right:0;
    }
</style>