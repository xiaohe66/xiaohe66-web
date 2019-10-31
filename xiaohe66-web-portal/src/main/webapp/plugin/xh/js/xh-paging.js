/**
 * 分页插件
 *
 * @author xiaohe
 * @date 18-03-15 015
 */
(function ($) {

    $.fn.paging = function (max, current, event) {
        if (max === 1) {
            return;
        }
        let pageCount = 5;
        let $this = $(this[0]);
        $this.html("");
        $this.addClass("paging");

        let first = $("<div class='disable'>首页</div>");
        let prev = $("<div class='disable'>上一页</div>");
        let next = $("<div>下一页</div>");
        let last = $("<div>尾页</div>");
        let inp = $("<input>");
        let btn = $("<div>确定</div>");

        $this.append(first);
        $this.append(prev);
        $this.append(next);
        $this.append(last);
        $this.append("跳至");
        $this.append(inp);
        $this.append(btn);
        $this.append("共"+max+"页");

        let showPrev = function () {
            prev.removeClass("disable");
            first.removeClass("disable");
        };

        let showNext = function () {
            next.removeClass("disable");
            last.removeClass("disable");
        };

        let hidePrev = function () {
            prev.addClass("disable");
            first.addClass("disable");
        };

        let hideNext = function () {
            next.addClass("disable");
            last.addClass("disable");
        };

        let toPage = function (page) {
            page = parseInt(page);
            if (!isNaN(page)) {
                // 若是当前页面，是否继续操作
                /*if (page === current) {
                    return;
                }*/
                current = page > max ? max : page < 1 ? 1 : page;
                ev();
            }
        };

        let showPage = function () {
            $this.find(".page").remove();

            let startPage;
            if (max <= pageCount) {
                startPage = 1;
            } else {
                startPage = current - 2;
                if (startPage < 1) startPage = 1;

                if (current + pageCount - 2 > max) {
                    startPage = max - 4;
                }
            }

            for (let i = 0; i < pageCount && i < max; i++) {
                let pageNum = startPage + i;
                let page = "<div class='page" + (current === pageNum ? " active" : "") + "'>" + pageNum + "</div>";
                next.before(page);
            }

            if (current <= 1) {
                hidePrev();
            } else {
                showPrev();
            }
            if (current >= max) {
                hideNext();
            } else {
                showNext();
            }

            $this.find(".page").click(function () {
                toPage($(this).text());
            });
        };

        let ev = function (this0) {
            if ($(this0).hasClass("disable")) {
                return;
            }
            showPage(this0);
            event(current);
        };

        showPage();

        first.click(function () {
            current = 1;
            ev(this);
        });
        prev.click(function () {
            --current;
            ev(this);
        });
        next.click(function () {
            ++current;
            ev(this);
        });
        last.click(function () {
            current = max;
            ev(this);
        });
        inp.keyup(function (e) {
            if (e.keyCode === 13) {
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