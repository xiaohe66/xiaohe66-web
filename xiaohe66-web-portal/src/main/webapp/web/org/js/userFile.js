$(function () {

    $("#upload").initUploadBtn(true, function (data) {

        post("/org/user/file", {
            fileId: data.fileId,
            fileName: data.fileName,
            extension: data.extension,
            fileType: 0
        }, function (json) {
            $.maskHint("上传成功", function () {
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
    var trHtml = $("tr")[1].outerHTML;

    var search = "";

    var getPaging = function (page) {
        tbody.html("");
        paging("/org/user/file?search=" + search, page, 15, function (data) {
            console.log("data", data);
            $.each(data.records, function (i, item) {
                tbody.append(trHtml);
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

    getPaging(1);

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

    $("#clear").click(function () {
        $("#search").val("");
        $("#searchBtn").click();
    });
    
    $("#rename input").keydown(function (e) {
        if (e.keyCode === 13) {
            $("#confirm").click();
        }
    });
});

$(document).on("click", ".down", function () {
    let id = $(this).parent().parent().attr("id");
    window.open("/org/user/file/down/" + id);
});

let fileId;
let fileName;
$(document).on("click", ".rename", function () {
    let tr = $(this).parent().parent();
    fileId = tr.attr("id");
    fileName = tr.find(".name").text() + tr.find(".extension").text();
    $.mask(function () {
        $("#rename").hide();
    });
    $("#rename").show();
    $("#rename input").val(fileName);
    $("#rename input").focus();
});

$(document).on("click", "#confirm", function () {
    let name = $("#rename input").val();
    if (name && name !== fileName) {
        let arr = splitFileName(name);
        put("/org/user/file", {id: fileId, fileName: arr[0], extension: arr[1]}, function (data) {
            let tr = $("#" + fileId);
            tr.find(".name").text(arr[0]);
            tr.find(".extension").text(arr[1]);
            $("#rename").hide();
            $.maskClose();
        })
    }
});

$(document).on("click", "#cencel", function () {
    $("#rename").hide();
    $.maskClose();
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