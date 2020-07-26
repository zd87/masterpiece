<template>
    <v-app>
        <v-content>
            <Header />
            <router-view></router-view>
            <Alert/>
        </v-content>
    </v-app>
</template>

<script>
import Header from "@/components/banners/Header.vue"
import Alert from "@/components/banners/Alert.vue"
export default {
    name: 'App',

    components: {
        Header,
        Alert
    },

    data: () => ({
        //
    })
}
</script>
