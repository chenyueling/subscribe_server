/* 为每个modal弹出框的表单添加提交事件 */
$(function(){
	/*
	$(".modal .btn-submit").click(function(){
		$(this).parent().prev().find("form").submit();
	});
	
	$("form").validation();
	*/
	
	$(".control-group input").val("");
	
});


/**
 * 获取RSA明钥
 * @return
 */
function getRSAKey() { 
    setMaxDigits(131); 
    return new RSAKeyPair("10001", "", "81595192cea41428a8606f15902d0c451100be86022086b5700ff404d9229b01a4d22830af5a53305bdcdde99ec52269bb6dace76cc22bbfec14793afc2f6bc5db4da2922a316de3b330b40ccd3e77e4a1afd65096db5c066734324260825ef9c7d19fb1572e2ff876e4b42f3572ed38aac5d4cb2c2f64598ffae93ab3a2ed59"); 
}