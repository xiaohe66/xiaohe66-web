/**
 *
 *
 * @author xh
 * @date 18-03-15 015
 */
$(function () {
    var E = window.wangEditor;
    editor = new E(".tool",".editor");
    editor.customConfig.zIndex = 0;
    editor.create();
});