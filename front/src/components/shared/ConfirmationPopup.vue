<template>
    <v-dialog v-model="value" persistent width="50%">
        <v-card>
            <v-card-title class="mb-2">{{ title }}</v-card-title>
            <v-card-text>
                <p>{{ text }}</p>
            </v-card-text>
            <v-card-actions class="d-flex justify-end pa-4 pt-0">
                <!-- cancel button -->
                <v-btn text rounded @click="close"> {{ "Cancel" }}</v-btn>
                <!-- confirm button -->
                <v-btn class="mx-5" color="darkPink white--text" rounded depressed @click="confirm">
                    {{ confirmBtn }}
                </v-btn>
                 <!--close button-->
                <v-btn class="closeIcon pa-0 ma-0" icon :ripple="false" @click="close">
                    <v-icon>mdi-close</v-icon>
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>
<script>
export default {
    props: {
        value: {
            type: Boolean,
            default: false
        },
        title: {
            type: String,
            default: ""
        },
        text: {
            type: String,
            default: ""
        },
        confirmBtn: {
            type: String,
            default: ""
        }
    },
    methods: {
        confirm(){
            this.$emit("confirm", true);
            this.$emit("input", false);
        },
        close(){
            this.$emit("confirm", false);
            this.$emit("input", false);
        }
    }
    
};
</script>
<style lang="scss" scoped>

</style>