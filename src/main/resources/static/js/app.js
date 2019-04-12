layui.define(['jquery', 'layer'], function(exports) {
	var $ = layui.jquery,
	layer = layui.layer;
    /**
     * 新开窗口
     * @param title 窗口标题
     * @param url 接口
     * @param w 窗口宽度
     * @param h 窗口高度
     * @return
     */
	window.appOpen = function(title, url, w, h) {
		if(title == null || title == '') {
			title = false;
		}
		if(url == null || url == '') {
			url = "404.html";
		}
		if(w == null || w == '') {
			w = ($(window).width());
		}
		if(h == null || h == '') {
			h = ($(window).height());
		}
		layer.open({
			type: 2,
			area: [w + 'px', h + 'px'],
			fix: false, //不固定
			maxmin: true,
			shadeClose: true,
			shade: 0.4,
			title: title,
			content: context + url
		});
	}
    /**
     * 关闭窗口
     * @param func 回调函数
     * @return
     */
	window.appClose = function(func) {
		if (typeof func === 'function') {
			func();
		}
		// 获得frame索引
		var index = parent.layer.getFrameIndex(window.name);
		//关闭当前frame
		parent.layer.close(index);
	}
    /**
     * 编辑
     * @param url 接口
     * @param data json格式数据
     * @param msg 提示
     * @return
     */
	window.appEdit = function(url, data, tip) {
		tip = tip || '确认要提交吗？';
		layer.confirm(tip, function() {
			$.ajax({
				url: context + url,
				type: 'post',
				dataType: 'json',
				data: data,
				success: function(result) {
					if (result.success) {
						layer.msg('操作成功', {
							icon: 1,
							time: 1000
						}, function() {
							//获得frame索引
							var index = parent.layer.getFrameIndex(window.name);
							//关闭当前frame
							parent.layer.close(index);
							window.parent.location.reload();
						});
					} else {
						layer.msg(result.msg, {
							icon: 5,
							time: 1000
						});
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					layer.msg(textStatus, {
						icon: 5,
						time: 1000
					});
                    // alert(XMLHttpRequest.status);
                    // alert(XMLHttpRequest.readyState);
                    // alert(textStatus);
				},
				beforeSend: function() {
					loading = layer.load(0, {
						shade: [0.1,'#fff']// 0.1透明度的白色背景
					});
				},			
				complete: function() {
					layer.close(loading);
					/* layer.msg('努力加载中', {
							icon:16,
							shade:[0.1, 'red'],
							time:false  //取消自动关闭
					}) */
				}
			});
		});
	}
    /**
     * 删除
     * @param url 接口
     * @param id 主键
     * @param func 回调函数
     * @param msg 提示
     * @return
     */
	window.appDelete = function(url, id, func, tip) {
		if ($.trim(id).length == 0) {
			layer.msg('选择不能为空', {
				icon: 5,
				time: 1000
			});
			return;
		}
		tip = tip || '确认要删除吗？';
		layer.confirm(tip, function() {
			$.ajax({
				url: context + url,
				type: 'get',
				dataType: 'json',
				data: {id : id},
				success: function(result) {
					if (result.success) {
						if (typeof func === "function") {
							func();
						}
						layer.msg('操作成功', {
							icon: 1,
							time: 1000
						}, function() {});
					} else {
						layer.msg(result.msg, {
							icon: 5,
							time: 1000
						});
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					layer.alert(textStatus, {icon:5});
				},
				beforeSend: function() {
					loading = layer.load(0, {
						shade: [0.1,'#fff']// 0.1透明度的白色背景
					});
				},
				complete: function() {
					layer.close(loading);
				}
			});
		});
	}
    /**
     * 批量删除
     * @param url 接口
     * @param ids 主键
     * @param func 回调函数
     * @param msg 提示
     * @return
     */
	window.appDeleteBatch = function(url, ids, func, tip) {
		if ($.trim(ids).length == 0) {
			layer.msg('选择不能为空', {
				icon: 5,
				time: 1000
			});
			return;
		}
		tip = tip || '确认要批量删除吗？';
		layer.confirm(tip, function() {
			$.ajax({
				url: context + url,
				type: 'get',
				dataType: 'json',
				data: {ids : ids},
				success: function(result) {
					if (result.success) {
						if (typeof func === "function") {
							func();
						}
						layer.msg('操作成功', {
							icon: 1,
							time: 1000
						}, function() {});
					} else {
						layer.msg(result.msg, {
							icon: 5,
							time: 1000
						});
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					layer.alert(textStatus, {icon:5});
				},
				beforeSend: function() {
					loading = layer.load(0, {
						shade: [0.1,'#fff']// 0.1透明度的白色背景
					});
				},
				complete: function() {
					layer.close(loading);
				}
			});
		});
	}
    /**
     * ajax确认操作
     * @param method 请求
     * @param url 接口
     * @param func 回调函数
     * @param msg 提示
     * @return
     */
	window.appConfirmAjax = function(method, url, params, func, tip) {
		tip = tip || '确认要操作吗？';
		layer.confirm(tip, function() {
			$.ajax({
				url: context + url,
				type: method,
				dataType: 'json',
				data: params,
				success: function(result) {
					if (result.success) {
						if (typeof func === "function") {
							func(result);
						}
						layer.msg('操作成功', {
							icon: 1,
							time: 1000
						}, function() {});
					} else {
						layer.msg(result.msg, {
							icon: 5,
							time: 1000
						});
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					layer.alert(textStatus, {icon:5});
				},
				beforeSend: function() {
					loading = layer.load(0, {
						shade: [0.1,'#fff']// 0.1透明度的白色背景
					});
				},
				complete: function() {
					layer.close(loading);
				}
			});
		});
	}
    /**
     * ajax操作
     * @param method 请求
     * @param url 接口
     * @param func 回调函数
     * @param tip 提示
     * @return
     */
	window.appAjax = function(method, url, params, func, tip) {
		tip = tip || '操作成功';
		$.ajax({
			url: context + url,
			type: method,
			dataType: 'json',
			data: params,
			success: function(result) {
				if (result.success) {
					if (typeof func === "function") {
						func(result);
					}
					layer.msg(tip, {
						icon: 1,
						time: 1000
					}, function() {});
				} else {
					layer.msg(result.msg, {
						icon: 5,
						time: 1000
					});
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				layer.alert(textStatus, {icon:5});
			},
			beforeSend: function() {
				loading = layer.load(0, {
					shade: [0.1,'#fff']// 0.1透明度的白色背景
				});
			},
			complete: function() {
				layer.close(loading);
			}
		});
	}
	var $pageForm;
	window.appPage = {
		setForm : function(id) {
			$pageForm = $('#' + id);
		},
		getForm : function() {
			return $pageForm;
		},
		submitForm : function() {
			$pageForm.submit();
		},		
		init : function(current, size, id) {
			this.setForm(id);
			this.update(current, size);
		},
		update : function(current, size) {
			$pageForm.find('input[name="current"]').val(current);
			$pageForm.find('input[name="size"]').val(size);
		}
	};
	exports('app', {});
});