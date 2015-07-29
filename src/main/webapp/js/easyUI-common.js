/*验证字段*/
$(function(){
	$.extend($.fn.validatebox.defaults.rules, {   
		minLength: {   
			validator: function(value, param){   
				return value.length >= param[0];   
			},   
			message: '输入内容长度不少于{0}'  
		},
		isName:{
			validator:function(value,param){
				var isChinese = /^[\u4E00-\u9FFF]+$/.test(value);
				var length = value.length >= param[0];
				return (isChinese && length);
			},
			message: '请输入至少{0}个汉字'
		},
		amongInt:{
		   validator:function(value, param) {
				var isInt = (/^[0-9]*$/.test( value ));//验证是否为整数
				if(isInt && value>param[1] || value<param[0]){return false;}
				return isInt;
		   },
		   message:'请输入{0}-{1}之间的整数数字'
		},
		isNumber:{
			validator:function(value,param){
				var isNum = (/^[0-9]+(.[0-9]{0,3})?$/.test(value));//验证有0-3位小数的正实数
				return isNum;
			},
			message:'请输入有0-3位小数的正数'
		},
		newPassword: {   
			validator: function(value, param){  			
				var oldP=$("input[name=oldPassword]").val();
				var newP=$("input[name=password]").val();
				if(value.length < param[0]){
					param[1] = '输入内容长度不少于6';
					return false;
				}					
				if(oldP==newP){
					param[1] = '新密码和旧密码不能相同';
					return false;
				}
				return true;					
			},   
			message: '{1}'  
		},
		affirmNewPassword:{
			validator:function(value,param){
				var newP=$("input[name=password]").val();
				var affNewP=$("input[name=affirmNewPassword]").val();
				if(value.length < param[0]){
					param[1] = '输入内容长度不少于6';
					return false;
				}
				if(affNewP!=newP){
					param[1] = '两次输入的密码不相同';
					return false;
				}
				return true;	
			},
			message:'{1}'
		}
		
	});
});