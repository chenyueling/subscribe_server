;(function($) {
	$.extend({
		/**
		 * BBS前端控制插件
		 * doPost:异步Post请求
		 * doGet:异步Get请求
		 */
		'acm' : {
			/**
			 * 异步Post请求
			 * 参数列表
			 * options = {method:'post',dataType:'json'}
			 * url:请求地址
			 * params:参数
			 * beforeSubmit:将在请求提交前执行
			 * afterReturn:服务器正确返回后执行
			 * success:返回带success标志执行
			 * fail:返回不带success标准时执行
			 * error:请求失败后执行
			 */
			'doPost' : function(options) {
				options = $.extend({
					method : 'post',
					dataType : 'json'
				}, options);
				$.ajax({
					url: options.url,
					data: options.params,
					dataType: options.dataType,
					type: options.method,
					beforeSend:function(json) {
						if (options.beforeSubmit) {
							var r = options.beforeSubmit();
							if (!r) {
								return false;
							}
						}
					},
					success:function(json) {
						if (options.afterReturn) {
							options.afterReturn(json);
						}
						if (!json) {
							alert('未知异常,请联系管理员');
							return false;
						}
						if (json.result != "success") {
							if (options.fail) {
								options.fail(json);
							} else {
								alert(json.data);
							}
							return false;
						} else {
							if (options.success) {
								options.success(json);
							}
							return true;
			   			}
					},
					error: function(xmlHttp) {
						if (options.error) {
							options.error(xmlHttp);
						} else {
							document.write(xmlHttp.responseText);
						}
						return false;
					}
				});
				return this;
			},
			/**
			 * 异步Get请求
			 * 参数列表
			 * options = {method:'get',dataType:'json'}
			 * url:请求地址
			 * params:参数
			 * beforeSubmit:将在请求提交前执行
			 * afterReturn:服务器正确返回后执行
			 * success:返回带success标志执行
			 * fail:返回不带success标准时执行
			 * error:请求失败后执行
			 */
			'doGet' : function(options) {
				options = $.extend({
					method : 'get',
					dataType : 'json'
				}, options);
				$.ajax({
					url: options.url,
					data: options.params,
					dataType: options.dataType,
					type: options.method,
					beforeSend:function(json) {
						if (options.beforeSubmit) {
							options.beforeSubmit();
						}
					},
					success:function(json) {
						if (options.afterReturn) {
							options.afterReturn(json);
						}
						if (!json) {
							alert('未知异常,请联系管理员');
							return false;
						}
						if (json.result != "success") {
							if (options.fail) {
								options.fail(json);
							} else {
								alert(json.data);
							}
							return false;
						} else {
							if (options.success) {
								options.success(json);
							}
							return true;
			   			}
					},
					error: function(xmlHttp) {
						if (options.error) {
							options.error();
						} else {
							document.write(xmlHttp.responseText);
						}
						return false;
					}
				});
				return this;
			},
			/**
			 * 初始化表单
			 * 参数列表
			 * options = {method:'get',dataType:'json'}
			 * url:请求地址 o
			 * beforeSubmit:将在请求提交前执行
			 * afterReturn:服务器正确返回后执行
			 * success:返回带success标志执行
			 * fail:返回不带success标准时执行
			 * error:请求失败后执行
			 */
			'initForm' : function(options) {
				options = $.extend({
					method : 'post',
					dataType : 'json'
				}, options);
				
				$(options.form).ajaxForm({
					type : options.method,
					url : options.url,
					dataType : 'json',
					beforeSubmit : function(data) {
						if (options.beforeSubmit) {
							return options.beforeSubmit(data);
						}
					},
					success : function(json) {
				    	if (options.afterReturn) {
							return options.afterReturn(json);
						}
						if (!json) {
							alert('未知异常,请联系管理员');
							return false;
						}
						if (json.result != "success") {
							if (options.fail) {
								options.fail(json);
							} else {
								alert(json.data);
							}
							return false;
						} else {
							if (options.success) {
								options.success(json);
							}
							return true;
			   			}
					},
					error : function() {  
						if (options.error) {
							options.error();
						} else {
							document.write(xmlHttp.responseText);
						}
						return false;
				    }
				});	
				return this;
			},
			
			/**
			 * 初始化表单
			 * 参数列表
			 * options = {method:'get',dataType:'json'}
			 * url:请求地址 o
			 * beforeSubmit:将在请求提交前执行
			 * afterReturn:服务器正确返回后执行
			 * success:返回带success标志执行
			 * fail:返回不带success标准时执行
			 * error:请求失败后执行
			 */
			'ajaxSubmit' : function(options) {
				options = $.extend({
					method : 'post',
					dataType : 'json'
				}, options);
				$(options.form).ajaxSubmit({
					type : options.method,
					url : options.url,
					dataType : 'json',
					beforeSubmit : function(data) {
						if (options.beforeSubmit) {
							return options.beforeSubmit(data);
						}
					},
					success : function(json) {
				    	if (options.afterReturn) {
							options.afterReturn(json);
						}
						if (!json) {
							alert('未知异常,请联系管理员');
							return false;
						}
						if (json.result != "success") {
							if (options.fail) {
								options.fail(json);
							} else {
								alert(json.data);
							}
							return false;
						} else {
							if (options.success) {
								options.success(json);
							}
							return true;
			   			}
					},
					error : function() {  
						if (options.error) {
							options.error();
						} else {
							document.write(xmlHttp.responseText);
						}
						return false;
				    }
				});	
				return this;
			},
			
			/**
			 * 初始化表单
			 * 参数列表
			 * options = {method:'post',dataType:'json'}
			 * url:请求地址 o
			 * btn:表单提交按钮ID
			 * beforeSubmit:将在请求提交前执行
			 * afterReturn:服务器正确返回后执行
			 * success:返回带success标志执行
			 * fail:返回不带success标准时执行
			 * error:请求失败后执行
			 */
			'initSubmit' : function(options) {
				options = $.extend({
					method : 'post',
					dataType : 'json'
				}, options);
				
				var result = this;

				$(options.form).ajaxForm({
					type : options.method,
					url : options.url,
					dataType : 'json',
					success : function(json) {
				    	if (options.afterReturn) {
							return options.afterReturn(json);
						}
						if (!json) {
							alert('未知异常,请联系管理员');
							return false;
						}
						if (json.result != "success") {
							if (options.fail) {
								options.fail(json);
							} else {
								alert(json.data);
							}
							return false;
						} else {
							if (options.success) {
								options.success(json);
							}
							return true;
			   			}
					},
					error : function() {  
						if (options.error) {
							options.error();
						} else {
							document.write(xmlHttp.responseText);
						}
						return false;
				    }
				});	
				
				$(options.btn).click(function() {
					var isOK = true;
					if (options.beforeSubmit) {
						isOK = options.beforeSubmit();
					}
					if (isOK == false) {
						return result;
					}
					$(options.form).submit();
				});
				
				return this;
			},
			
			/**
			 * 刷新验证码
			 * a ： 验证码a标签ID
			 * url：验证码的URL
			 */
			'refreshCode':function(options) {
				options = $.extend({
					a:'#validataCode',
					url:'randomCode'
				}, options);
				
				var timenow = new Date().getTime();  
				$(options.a).find('img').attr('src', 'randomCode?d=' + timenow);
				$("input[name='" + options.a + "']").val('');
			},

            /**
             * 初始化图片上传插件
             * @param options {editorId:'myeditor', btn:'#image'}
             * url ：图片上传路径
             * success ：插入时候执行，参数有 imageId，src
             */
            'imageUplad' : function(options) {
                options = $.extend({
                    editorId:'myeditor',
                    btn:'#image'
                }, options);
                window.UEDITOR_CONFIG.imageUrl = options.url;
                window.UEDITOR_CONFIG.imagePath = '';
                window.UEDITOR_CONFIG.maxUploadNum = 1;
                //window.UEDITOR_CONFIG.lang = 'zh-cn';
                //window.UEDITOR_CONFIG.langPath = '/WeiWei/imageUpload/lang/';
                //window.UEDITOR_CONFIG.theme = 'default';
                //window.UEDITOR_CONFIG.themePath = '/WeiWei/imageUpload/themes/';
                //window.UEDITOR_CONFIG.iframeCssUrl = '/WeiWei/imageUpload/themes/iframe.css';
                //window.UEDITOR_CONFIG.codeMirrorJsUrl = '/WeiWei/imageUpload/third-party/codemirror/codemirror.js';
                //window.UEDITOR_CONFIG.codeMirrorCssUrl = '/WeiWei/imageUpload/third-party/codemirror/codemirror.css';
                var image = {
                    editor:null,
                    init:function(editorid){
                        var _editor =this.getEditor(editorid);
                        _editor.ready(function () {
                            var result;
                            _editor.setDisabled();
                            _editor.hide();
                            _editor.addListener('uploadCompleteCallback', function(evt, data) {
                                result = data;
                            });
                            _editor.addListener('beforeInsertImage', function (t, args) {
                                if (options.success) {
                                    options.success(result);
                                }
                            });
                        });
                    },
                    getEditor:function(editorid){
                        this.editor = UE.getEditor(editorid);
                        return this.editor;
                    },
                    show:function(id){
                        var _editor = this.editor;
                        //注意这里只需要获取编辑器，无需渲染，如果强行渲染，在IE下可能会不兼容（切记）
                        //和网上一些朋友的代码不同之处就在这里
                        $(id).click(function(){
                            var image = _editor.getDialog("insertimage");
                            image.render();
                            image.open();
                        });
                    },
                    render:function(editorid){
                        var _editor = this.getEditor(editorid);
                        _editor.render();
                    }
                };
                image.init(options.editorId);
                image.show(options.btn);
            }
		
		}

	
	});
})(jQuery);