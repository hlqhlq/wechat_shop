/*
 * @Date:2019-01-20
 * +----------------------------------------------------------------------
 * | Weadmin [ 后台管理模板 ]
 * | 基于Layui http://www.layui.com/
 * +----------------------------------------------------------------------
 */
layui.define(['jquery'], function(exports) {
	var $ = layui.jquery;
	var $pageForm;
	window.PageFormUtil = {
		setForm : function(id) {
			$pageForm = $('#' + id);
		},
		getForm : function() {
			return $pageForm;
		},
		initPage : function(current, size, id) {
			this.setForm(id);
			this.updatePage(current, size);
		},
		updatePage : function(current, size) {
			$pageForm.find('input[name="current"]').val(current);
			$pageForm.find('input[name="size"]').val(size);
		},
		submitForm : function() {
			$pageForm.submit();
		}
	};
	exports('pageForm', {});
});