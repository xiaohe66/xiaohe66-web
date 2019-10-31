$(function () {

    $("#upload").initUploadBtn(true, function (data) {

        post("/org/user/file", {
            fileId: data.fileId,
            fileName: data.fileName,
            extension: data.extension,
            fileType: 0
        }, function (json) {
            $.maskHint("上传成功",function () {
                location.reload();
            })
        });
    });

    if (hasEdit) {
        $(".btnTd").append("<a href='javascript:' class='rename'>重命名</a>");
    }
    if (hasDelete) {
        $(".btnTd").append("<a href='javascript:' class='delete'>删除</a>");
    }

    var tbody = $("#file_tab").find("tbody");
    var html = $("tr")[1].outerHTML;

    var search = "";

    var getPaging = function (page) {
        tbody.html("");
        paging("/org/user/file?search=" + search, page, 15, function (data) {
            console.log("data", data);
            $.each(data.records, function (i, item) {
                tbody.append(html);
                var tr = tbody.find("tr:last");
                tr.attr("id", item.id);

                tr.find(".name").text(item.fileName);
                tr.find(".extension").text(item.extension);
                var tds = tr.find("td");
                tds.eq(1).text(item.createTime);
                tds.eq(2).text(item.fileSize);
                tds.eq(3).text(item.createUserName);
            });
            $("#paging").paging(data.pages, data.current, getPaging);
        });
    };

    $("#searchBtn").click(function () {
        var val = $("#search").val();
        if (search !== val) {
            search = val;
            getPaging(1);
        }
    });

    $("#search").keydown(function (e) {
        if (e.keyCode === 13) {
            var val = $(this).val();
            if (search !== val) {
                search = val;
                getPaging(1);
            }
        }
    });

    $("#paging").paging(max, num, getPaging);
});

$(document).on("click", ".down", function () {
    let id = $(this).parent().parent().attr("id");
    window.open("/org/user/file/down/" + id);
});

$(document).on("click", ".rename", function () {
    let id = $(this).parent().parent().attr("id");
    alert("改名弹窗");
});

$(document).on("click", ".delete", function () {
    let tr = $(this).parent().parent();
    let id = tr.attr("id");
    let isDelete = confirm("确定要删除吗?");
    if (isDelete) {
        del("/org/user/file/" + id, function () {
            alert("删除成功");
            tr.remove();
        });
    }
});