<template>
    <div>
        <section class="presentation d-flex justify-center align-center">
            <v-col cols="6">
                <h1 class="fw-400 text-center">Managing servers</h1>
                <ul class="list text-center">
                    <li class="list__item">Create, update, delete</li>
                    <li class="list__item">Quick search</li>
                </ul>
            </v-col>
            <v-col cols="6">
                <img
                    class="firstImage"
                    src="@/assets/section-1-intro.jpg"
                    alt=""
                />
            </v-col>
        </section>
        <section class="inverted presentation d-flex justify-center align-center">
            <v-col cols="6" class="d-flex justify-center">
                <img
                    class="secondImage "
                    src="@/assets/excel_logo.png"
                    alt=""
                />
            </v-col>
            <v-col cols="6">
                <h1 class="fw-400">Spreadsheet</h1>
                <ul class="list">
                    <li class="list__item">Import</li>
                    <li class="list__item">Export</li>
                </ul>
            </v-col>
        </section>
        
    </div>
</template>
<script>
export default {
    
};
</script>
<style lang="scss" scoped>
.presentation {
    padding: 30px 0;
    overflow:hidden;
    background-image: radial-gradient(#212121 5%, transparent 5%);
        background-position: 0 0;
        background-size: 10px 10px;
}
.inverted {
    background-color: #3b4357;
    color:white;
}

.list{
    line-height: 1.7;
    font-size: 16px;
    font-weight: 300;
    list-style-type: none;
    padding:0;
    margin:0;
    &__item{
        &::before{
            content:"• "
        }
    }
}
.firstImage{
    height:auto;
    width:400px;

}
.secondImage{
    height: auto;
    width: 300px;
}
</style>