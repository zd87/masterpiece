import Vue from "vue"
import Vuetify from "vuetify/lib"

Vue.use(Vuetify)

export default new Vuetify({
	icons: {
		iconfont: "mdi"
	},
    theme: {
        themes: {
			light: {
				primary: "#3b4357",
				secondary: "#e60028",
				accent: "#ed3850",
				error: "#e60028",
				info: "#2196f3",
				success:"#4caf50",
				warning:"#ffc107",

				black:"333333",
				white:"ffffff",

				blueZodiac: "#3b4357",
				darkPink: "#f05b6f"
			},
		},
      },
})
