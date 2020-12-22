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

        <div class="album py-5 bg-light">
            <div class="container">
                <div class="title1">最新上线</div>
                <div class="row">
                    <div v-for="o in news" class="col-md-4">
                        <the-course v-bind:course="o"></the-course>
                    </div>
                </div>

                <hr>

                <div class="title2">好课推荐</div>
                <div class="row">
                    <div v-for="o in news" class="col-md-4">
                        <the-course v-bind:course="o"></the-course>
                    </div>
                </div>
            </div>

        </div>

    </main>
</template>

<script>
    import TheCourse from "../components/the-course";
    export default {
        name: 'index',
        components: {TheCourse},
        data: function () {
            return {
                news: [],
            }
        },
        mounted() {
            let _this = this;
            _this.listNew();
        },
        methods: {
            /**
             * 查询新上好课
             */
            listNew() {
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/web/course/list-new').then((response)=>{
                    console.log("查询新上好课结果：", response);
                    let resp = response.data;
                    if (resp.success) {
                        _this.news = resp.content;
                    }
                }).catch((response)=>{
                    console.log("error：", response);
                })
            },
        }
    }
</script>

<style>
    .title1{
        margin-bottom: 2rem;
        color: #fafafa;
        letter-spacing: 0;
        text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px 4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333, 0px 8px 7px #001135;
        font-size: 2rem;
    }
    .title2{
        margin-bottom: 2rem;
        color: transparent;
        -webkit-text-stroke: 1px black;
        letter-spacing: 0.04em;
        font-size: 2rem;
    }
</style>