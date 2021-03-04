<template>
    <main role="main">
        <div class="album py-5 bg-light">
            <div class="container">
                <div class="row">
                    <div v-for="o in courses" class="col-md-4">
                        <the-course v-bind:course="o"></the-course>
                    </div>
                    <h3 v-show="courses.length === 0">还未购买课程，先去试看章节看看吧</h3>
                </div>
            </div>
        </div>

    </main>
</template>

<script>
    import TheCourse from "../components/the-course";

    export default {
        components: {TheCourse},
        name: 'purchased',
        data: function () {
            return {
                courses: [],
            }
        },
        mounted() {
            let _this = this;
            if (Tool.getLoginMember().id != null){
                _this.listCourse();
            }
        },
        methods: {
            /**
             * 查询课程列表
             */
            listCourse() {
                let _this = this;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/web/member-course/purchased', {
                    memberId: Tool.getLoginMember().id,
                }).then((response) => {
                    let resp = response.data;
                    if (resp.success) {
                        _this.courses = resp.content;
                    }
                }).catch((response) => {
                    console.log("error：", response);
                })
            },

        }
    }
</script>
