<template>
  <div>
    <div class="echart2" id="chart1" :style="myChartStyle"></div>
    <div class="echart" id="chart" :style="myChartStyle"></div>
  </div>
</template>

<script>
import * as echarts from "echarts";

export default {
  data() {
    return {
      xData: ["水稻", "玉米", "花生", "小麦", "甘蔗"], //横坐标，作物类型
      yData: [23, 24, 18, 25, 27], //数据
      myChartStyle: { float: "left", height: "400px", width: "100%" } ,//图表样式
    };
  },
  mounted() {
    this.initEcharts();
    this.drawLine();
  },
  methods: {
    drawLine(){
// 基于准备好的dom，初始化echarts实例
      let myChart = echarts.init(document.getElementById('chart1'))
// 绘制图表
      myChart.setOption({
        title:{
          // x: "36%",     //X坐标
          // y: "29%",    //Y坐标
          text: "农田总量",   //主标题
          textStyle: {
            color: 'rgb(215,193,165)'
          }
        },

        color:['#4472C5','#ED7C30','#80FF80','#FF8096','#800080'],//配置颜色
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          orient: 'vertical',
          x: 'right',
          data:['水田','旱地','水浇田','梯田','圩田'],
          textStyle: {//------------此处更改表格中的字体颜色
            fontSize: '12',
            color:'#fff'
          }
        },
        series: [
          {
            name:'访问来源',
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
              normal: {
                show: false,
                position: 'center'
              },
              emphasis: {
                show: true,
                textStyle: {
                  fontSize: '30',
                  fontWeight: 'bold'
                }
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data:[
              {value:335, name:'水田'},
              {value:310, name:'旱地'},
              {value:234, name:'水浇田'},
              {value:135, name:'梯田'},
              {value:1548, name:'圩田'}
            ],
          }
        ]




      });
    },
    initEcharts() {
      // 柱状图
      const mulColumnZZTData = {
        xAxis: {
          data: this.xData,
          axisLabel: {
            color: 'rgb(215,193,165)' // 修改为所需的颜色值，比如蓝色
          }
        },
        // 图例
        legend: {
          data: ["数量"],
          textStyle: {
            color: "rgb(215,193,165)"
          },
          top: "0%"
        },
        yAxis: {
          type: 'value',
          axisTick: {
            show: false //去除刻度线
          },
          axisLabel: {
            color: "white" //文本颜色
          },
          axisLine: {
            show: false //去除轴线
          },

          splitLine: {
            lineStyle: {
              color: "#012f4a"
            }
          }

        },
        title: {
          text: '作物统计',
          Top: 'center',
          textStyle: {
            color: 'rgb(215,193,165)'
          }
        },
        toolbox: {
          show: true,
          feature: {
            dataZoom: {
              yAxisIndex: 'none'
            },
            magicType: { type: ['line', 'bar'] },
            restore: {},
            saveAsImage: {}
          }
        },
        series: [
          {
            type: "bar", //形状为柱状图
            data: this.yData,
            name: "数量", // legend属性
            label: {
              // 柱状图上方文本标签，默认展示数值信息
              show: true,
              position: "top",
              color:"rgb(215,193,165)"
            }
          },

        ]
      };
      const myChart = echarts.init(document.getElementById("chart"));
      myChart.setOption(mulColumnZZTData);
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    },
  },
}

</script>
