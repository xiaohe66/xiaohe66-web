$(function () {

    let table = layui.table
        , form = layui.form
        , layer = layui.layer;

    table.render({
        elem: '#table'
        , title: "照片墙"
        , url: '/love/photo'
        // , cellMinWidth: 20
        , height: 'full-200'
        , page: true
        , loading: true
        , cols: [[
            {field: 'id', title: 'ID', width: "5%", fixed: 'left'}
            , {field: 'id', title: '缩略图', width: "6%", align: "center", fixed: 'left', templet: "#photoImg"}
            , {field: 'name', title: '照片名称', width: "15%", fixed: 'left',}
            , {field: 'one', title: '第一段话', width: "25%"}
            , {field: 'two', title: '第二段话', width: "25%"}
            , {field: 'three', title: '第三段话', width: "25%"}
            , {field: 'sort', title: '排序', width: "3%", fixed: 'right'}
            , {field: 'isShow', title: '是否显示', width: "6%", fixed: 'right', templet: '#isShow'}
            , {title: '操作', width: "8%", toolbar: '#operate', fixed: 'right'}
        ]]
        , parseData: function (result) {
            return {
                "code": result.code,
                "msg": result.msg,
                "count": result.data.total,
                "data": result.data.records
            };
        }
        , response: {
            statusCode: 100
        }
    });

    table.on('tool(photo)', function (obj) {
        console.log("测试", obj.data);
        if (obj.event === "edit") {
            console.log("编辑");
        } else if (obj.event === "del") {
            layer.confirm('确定删除吗', function (index) {
                del("/love/photo/" + obj.data.id, function (data) {
                    obj.del();
                    layer.close(index);
                });
            });
        }
    });


    form.verify({
        photoName: function (value, dom) {
            if (value && value.length > 10) {
                return "搜索名称不能超过10个字符";
            }
        }
    });

    form.on('switch(changeIsShow)', function (obj) {
        let data = {id: obj.value, isShow: obj.elem.checked};
        console.log("changeIsShow", data);
        put("/love/photo", data, function (result) {
            layer.msg("更改成功");
        })
    });

    form.on("submit(search)", function (data) {
        table.reload('table', {
            where: data.field
        });
        return false;
    });

});