<template>
    <main role="main">

                <section class="jumbotron text-center">
                    <div class="container">
                        <h1 class="jumbotron-heading">在线教育平台</h1>
                        <p class="lead text-muted">这是我的毕业设计。我相信，一分耕耘一分收获。<br>一只向往体面生活的代码狗 🐕。 </p>
                        <p>
                            <router-link to="/list" class="btn btn-primary my-2 p-3">点击进入所有课程</router-link>
                        </p>
                    </div>
                </section>
    </main>
</template>

<script>
    export default {
        name: 'about',
        components: {},
        data: function () {
            return {

            }
        },
        mounted() {
            let _this = this;
        },
        methods: {

        }
    }
</script>

<style>

</style>