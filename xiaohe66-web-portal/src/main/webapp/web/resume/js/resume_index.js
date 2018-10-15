/**
 * @author xh
 * @time 18-10-09 009
 */
$(function () {
    var panel = $(".ability .body");
    $.each(abilityJson,function (i, item) {
        var div = $("<div></div>");
        panel.append(div);

        echarts.init(div[0]).setOption(createOption(abilityJson[i]));
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
        radar: {
            shape: 'circle',
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