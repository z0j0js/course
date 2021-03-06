<template>
  <div>
    <div class="space-12"></div>
    <div class="row">
      <div class="col-xs-12">
        <div class="text-center">
          <span class="btn btn-app btn-sm btn-light no-hover">
              <span class="line-height-1 bigger-170 blue"> {{course}} </span>
              <br>
              <span class="line-height-1 smaller-90"> 课程 </span>
          </span>
          <span class="btn btn-app btn-sm btn-yellow no-hover">
              <span class="line-height-1 bigger-170"> {{chapter}} </span>

              <br>
              <span class="line-height-1 smaller-90"> 大章 </span>
          </span>

          <span class="btn btn-app btn-sm btn-pink no-hover">
              <span class="line-height-1 bigger-170"> {{section}} </span>

              <br>
              <span class="line-height-1 smaller-90"> 小节 </span>
          </span>

          <span class="btn btn-app btn-sm btn-success no-hover">
              <span class="line-height-1 bigger-170"> {{member}} </span>

              <br>
              <span class="line-height-1 smaller-90"> 会员 </span>
          </span>

          <span class="btn btn-app btn-sm btn-grey no-hover">
              <span class="line-height-1 bigger-170"> {{comment}} </span>

              <br>
              <span class="line-height-1 smaller-90"> 评论 </span>
          </span>

          <span class="btn btn-app btn-sm btn-primary no-hover">
              <span class="line-height-1 bigger-170"> {{receipts}} </span>

              <br>
              <span class="line-height-1 smaller-90"> 总收益(元) </span>
          </span>
        </div>

        <div class="space-12"></div>

        <div class="hr hr2 hr-double"></div>

        <div class="space-12"></div>

      </div>

      <div class="col-sm-12 infobox-container">
        <div class="infobox infobox-green">
          <div class="infobox-icon">
            <i class="ace-icon fa fa-comments"></i>
          </div>

          <div class="infobox-data">
            <span class="infobox-data-number">{{comment}}</span>
            <div class="infobox-content">新增评论</div>
          </div>

<!--          <div class="stat stat-success">8%</div>-->
        </div>

        <div class="infobox infobox-blue">
          <div class="infobox-icon">
            <i class="ace-icon fa fa-twitter"></i>
          </div>

          <div class="infobox-data">
            <span class="infobox-data-number">{{member}}</span>
            <div class="infobox-content">新增注册会员</div>
          </div>

<!--          <div class="badge badge-success">-->
<!--            +10%-->
<!--            <i class="ace-icon fa fa-arrow-up"></i>-->
<!--          </div>-->
        </div>

        <div class="infobox infobox-pink">
          <div class="infobox-icon">
            <i class="ace-icon fa fa-shopping-cart"></i>
          </div>

          <div class="infobox-data">
            <span class="infobox-data-number">{{total}}</span>
            <div class="infobox-content">课程购买</div>
          </div>
<!--          <div class="stat stat-important">4%</div>-->
        </div>

        <div class="space-12"></div>

        <div class="hr hr2 hr-double"></div>

        <div class="space-12"></div>

      </div>

      <div class="col-sm-12">
        <div class="widget-box transparent">
          <div class="widget-header widget-header-flat">
            <h4 class="widget-title lighter">
              <i class="ace-icon fa fa-signal"></i>
              日课程销售量
            </h4>

          </div>

          <div class="widget-body">
            <div class="widget-main padding-4">
              <div id="sales-charts"></div>
            </div><!-- /.widget-main -->
          </div><!-- /.widget-body -->
        </div><!-- /.widget-box -->

        <div class="space-12"></div>

        <div class="hr hr2 hr-double"></div>

        <div class="space-12"></div>
      </div><!-- /.col -->

      <div class="col-sm-6">
        <div class="widget-box transparent">
          <div class="widget-header widget-header-flat">
            <h4 class="widget-title lighter">
              <i class="ace-icon fa fa-star orange"></i>
              课程销售
            </h4>

          </div>

          <div class="widget-body">
            <div class="widget-main no-padding">
              <table class="table table-bordered table-striped">
                <thead class="thin-border-bottom">
                <tr>
                  <th>
                    <i class="ace-icon fa fa-caret-right blue"></i>课程名称
                  </th>

                  <th>
                    <i class="ace-icon fa fa-caret-right blue"></i>价格
                  </th>

                  <th>
                    <i class="ace-icon fa fa-caret-right blue"></i>购买数
                  </th>
                  <th>
                    <i class="ace-icon fa fa-caret-right blue"></i>课程收益
                  </th>
                </tr>
                </thead>

                <tbody>
                <tr>
                  <td>计算机网络技术</td>

                  <td>
                    <b class="green">￥119.00</b>
                  </td>

                  <td>
                    <span class="label label-warning arrowed arrowed-right">6</span>
                  </td>
                  <td>
                    <b class="red">￥714.00</b>
                  </td>
                </tr>

                <tr>
                  <td>数据结构与算法</td>

                  <td>
                    <b class="green">￥188.00</b>
                  </td>

                  <td>
                    <span class="label label-warning arrowed arrowed-right">5</span>
                  </td>

                  <td>
                    <b class="red">￥940.00</b>
                  </td>
                </tr>

                <tr>
                  <td>Java 基础</td>

                  <td>
                    <b class="green">￥268.00</b>
                  </td>

                  <td>
                    <span class="label label-warning arrowed arrowed-right">4</span>
                  </td>
                  <td>
                    <b class="red">￥1072.00</b>
                  </td>
                </tr>

<!--                <tr>-->
<!--                  <td>开发工具IDEA从入门到爱不释手</td>-->

<!--                  <td>-->
<!--                    <b class="green">￥299.00</b>-->
<!--                  </td>-->

<!--                  <td>-->
<!--                    <span class="label label-success arrowed arrowed-right">1,654</span>-->
<!--                  </td>-->
<!--                  <td>-->
<!--                    <b class="red">￥188.00</b>-->
<!--                  </td>-->
<!--                </tr>-->

<!--                <tr>-->
<!--                  <td>Spring Boot核心技术</td>-->

<!--                  <td>-->
<!--                    <b class="green">￥68.00</b>-->
<!--                  </td>-->

<!--                  <td>-->
<!--                    <span class="label label-success arrowed arrowed-right">1,254</span>-->
<!--                  </td>-->
<!--                  <td>-->
<!--                    <b class="red">￥188.00</b>-->
<!--                  </td>-->
<!--                </tr>-->
                </tbody>
              </table>
            </div><!-- /.widget-main -->
          </div><!-- /.widget-body -->
        </div><!-- /.widget-box -->
      </div><!-- /.col -->
      <div class="col-sm-6">
        <div class="widget-box transparent">
          <div class="widget-header widget-header-flat">
            <h4 class="widget-title lighter">
              <i class="ace-icon fa fa-star orange"></i>
              分类销售
            </h4>

          </div>

          <div class="widget-body">
            <div class="widget-main">
              <div id="piechart-placeholder"></div>

            </div><!-- /.widget-main -->
          </div><!-- /.widget-body -->
        </div><!-- /.widget-box -->
      </div><!-- /.col -->
    </div><!-- /.row -->
  </div>
</template>

<script>
  export default {
    name: "welcome",
    data: function() {
      return {
        course: '',
        chapter: '',
        section: '',
        member: '',
        comment: '',
        receipts: '',
        total:'',
      }
    },
    mounted: function () {
      let _this = this;
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("welcome-sidebar");

      _this.getCourse();
      _this.getChapter();
      _this.getSection();
      _this.getMember();
      _this.getComment();
      _this.getReceipts();
      _this.getReceipts();
      _this.getTotal();

      _this.drawSaleChart();
      _this.drawPieChart();

    },
    methods: {
      /**
       * 获取课程数
       */
      getCourse() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/admin/course/getTotal', {
        }).then((response)=>{
          let resp = response.data;
          _this.course = resp.content;
        })
      },

      /**
       * 获取大章数
       */
      getChapter() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/admin/chapter/getTotal', {
        }).then((response)=>{
          let resp = response.data;
          _this.chapter = resp.content;
        })
      },

      /**
       * 获取小节数
       */
      getSection() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/admin/section/getTotal', {
        }).then((response)=>{
          let resp = response.data;
          _this.section = resp.content;
        })
      },

      /**
       * 获取会员数
       */
      getMember() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/admin/member/getTotal', {
        }).then((response)=>{
          let resp = response.data;
          _this.member = resp.content;
        })
      },

      /**
       * 获取评论数
       */
      getComment() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/web/comment/getTotal', {
        }).then((response)=>{
          let resp = response.data;
          _this.comment = resp.content;
        })
      },

      /**
       * 获取订单数
       */
      getTotal() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/admin/memberCourse/getTotal', {
        }).then((response)=>{
          let resp = response.data;
          _this.total = resp.content;
        })
      },

      /**
       * 获取总收益
       */
      getReceipts() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/admin/memberCourse/getReceipts', {
        }).then((response)=>{
          let resp = response.data;
          _this.receipts = resp.content;
        })
      },

      /**
       * 获取三十天统计购课数，绘制折线统计图
       */
      drawSaleChart() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/admin/memberCourse/statistics', {
        }).then((response)=>{
          let resp = response.data;
          let d1 = resp.content;
          let d2 = [];
          let sales_charts = $('#sales-charts').css({'width':'100%' , 'height':'220px'});
          $.plot("#sales-charts", [
            { label: "上个月30天", data: d1 },
            // { label: "最近30天", data: d1 },
            // { label: "上一周期", data: d2 },
          ], {
            hoverable: true,
            shadowSize: 0,
            series: {
              lines: { show: true },
              points: { show: true }
            },
            xaxis: {
              tickLength: 0
            },
            yaxis: {
              tickLength: 0
            },
            grid: {
              backgroundColor: { colors: [ "#fff", "#fff" ] },
              borderWidth: 1,
              borderColor:'#555'
            }
          });
        })
      },

      drawPieChart() {
        let placeholder = $('#piechart-placeholder').css({'width':'90%' , 'min-height':'180px'});
        let data = [
          { label: "前端技术",  data: 38.7, color: "#68BC31"},
          { label: "后端技术",  data: 24.5, color: "#2091CF"},
          { label: "计算机基础",  data: 18.6, color: "#DA5430"},
          { label: "其它",  data: 10, color: "#FEE074"},
        ];
        $.plot(placeholder, data, {
          series: {
            pie: {
              show: true,
              tilt:0.8,
              highlight: {
                opacity: 0.25
              },
              stroke: {
                color: '#fff',
                width: 2
              },
              startAngle: 2
            }
          },
          legend: {
            show: true,
            position: "ne",
            labelBoxBorderColor: null,
            margin:[-30,15]
          }
          ,
          grid: {
            hoverable: true,
            clickable: true
          }
        })
      },
    }
  }
</script>
