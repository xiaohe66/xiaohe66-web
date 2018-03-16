/**
 * 分页插件
 *
 * @author xh
 * @date 18-03-15 015
 */
(function($){

    $.fn.paging = function (max,current,event) {
        if(max === 1){
            return;
        }
        var pageCount = 5;
        var $this = $(this[0]);
        $this.html("");
        $this.addClass("paging");

        var first = $("<div class='disable'>首页</div>");
        var prev = $("<div class='disable'>上一页</div>");
        var next = $("<div>下一页</div>");
        var last = $("<div>尾页</div>");
        var inp = $("<input>");
        var btn = $("<div>确定</div>");

        $this.append(first);
        $this.append(prev);
        $this.append(next);
        $this.append(last);
        $this.append("跳至");
        $this.append(inp);
        $this.append(btn);

        var showPrev = function () {
            prev.removeClass("disable");
            first.removeClass("disable");
        };

        var showNext = function () {
            next.removeClass("disable");
            last.removeClass("disable");
        };

        var hidePrev = function () {
            prev.addClass("disable");
            first.addClass("disable");
        };

        var hideNext = function () {
            next.addClass("disable");
            last.addClass("disable");
        };

        var toPage = function (page) {
            page = parseInt(page);
            if(!isNaN(page)){
                if(page === current){
                    return;
                }
                current = page>max?max:page<1?1:page;
                showPage();
            }
        };

        var showPage = function (this0) {
            if($(this0).hasClass("disable")){
                return;
            }
            $this.find(".page").remove();

            var startPage;
            if(max<=pageCount){
                startPage=1;
            }else{
                startPage = current-2;
                if(startPage<1)startPage=1;

                if(current+pageCount-2>max){
                    startPage = max-4;
                }
            }

            for(var i=0;i<pageCount&&i<max;i++){
                var pageNum = startPage + i;
                var page = "<div class='page"+(current===pageNum?" active":"")+"'>"+pageNum+"</div>";
                next.before(page);
            }

            if(current <= 1){
                hidePrev();
            }else{
                showPrev();
            }
            if(current >= max){
                hideNext();
            }else {
                showNext();
            }

            $this.find(".page").click(function () {
                toPage($(this).text());
            });
            event(current);
        };

        showPage();

        first.click(function () {
            current=1;
            showPage(this);
        });
        prev.click(function () {
            --current;
            showPage(this);
        });
        next.click(function () {
            ++current;
            showPage(this);
        });
        last.click(function () {
            current=max;
            showPage(this);
        });
        inp.keyup(function (e) {
            if(e.keyCode === 13){
                toPage(inp.val());
                inp.val("");
            }
        });
        btn.click(function () {
            toPage(inp.val());
            inp.val("");
        });

    }

})(jQuery);