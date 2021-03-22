module.exports = {
    'transpileDependencies': [
        'vuetify'
    ],
    chainWebpack: config => {
        ["vue-modules", "normal-modules", "normal"].forEach((match) => {
            config.module
            .rule("sass")
            .oneOf(match)
            .use("sass-loader")
            .tap(opt => {
                    Object.assign(opt, {
                        prependData: "@import '@/scss/global.scss';",
                    })
                });
        });
    }
}
