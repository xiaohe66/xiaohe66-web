/**
 * @author xh
 * @time 18-10-09 009
 */
$(function () {
    var items = [
        {
            title:"后端技术",
            names:["aop","asp","shrio","mybatis","maven"],
            values:[80,80,80,90,80]
        },
        {
            title:"前端技术",
            names:["jQuery","html","css"],
            values:[95,80,80]
        },
        {
            title:"数据库",
            names:["mysql","oracle","sql server"],
            values:[90,80,80]
        },
        {
            title:"其它",
            names:["node.js","office","ps"],
            values:[70,80,80]
        }
    ];

    var panel = $(".ability .body");
    $.each(items,function (i, item) {
        var div = $("<div></div>");
        panel.append(div);

        echarts.init(div[0]).setOption(createOption(items[i]));
    });
});

function createOption(item) {
    var indicator = [];
    $.each(item.names,function (i, data) {
        indicator.push({max:100,name:data});
    });
    var option = {
        title: {
            text:item.title
        },
        tooltip: {},
        /*legend: {
            data: ['预算分配']
        },*/
        radar: {
            // shape: 'circle',
            name: {
                textStyle: {
                    color: '#fff',
                    backgroundColor: '#999',
                    borderRadius: 3,
                    padding: [3, 5]
               }
            },
            indicator:indicator
        },
        series: [{
            type: 'radar',
            areaStyle: {normal: {}},
            itemStyle: {normal: {areaStyle: {type: 'default'}}},
            tooltip:{
                formatter:function (params) {
                    var result = "熟练度";
                    $.each(item.names,function (i, data) {
                        result += "<br>"+data+" : "+item.values[i]+"%";
                    });
                    return result;
                }
            },
            data : [{
                value : item.values
                ,name : '熟练度'
            }]
        }]
    };
    return option;
}