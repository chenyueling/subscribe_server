var loaded = false;

function showIn(){//正常加载
	if(loaded){
		loaded = false;
		$("#foo").fadeOut();
		$("#bg").delay(500).fadeIn();
		$("#main").delay(500).fadeIn();	
	}
}
function showInTimeout(){//加载超时
	if(loaded){
		loaded = false;
		$("#foo").fadeOut();
		$("#bg").delay(500).fadeIn();
		$("#main").delay(500).fadeIn();	
	}
}
function spinInit(){//loading圆圈初始化
	var opts = {
	  lines: 11, // The number of lines to draw
	  length: 8, // The length of each line
	  width: 4, // The line thickness
	  radius: 10, // The radius of the inner circle
	  corners: 0.3, // Corner roundness (0..1)
	  rotate: 0, // The rotation offset
	  direction: 1, // 1: clockwise, -1: counterclockwise
	  color: '#ffffff', // #rgb or #rrggbb or array of colors
	  speed: 1.7, // Rounds per second
	  trail: 50, // Afterglow percentage
	  shadow: false, // Whether to render a shadow
	  hwaccel: false, // Whether to use hardware acceleration
	  className: 'spinner', // The CSS class to assign to the spinner
	  zIndex: 2e9, // The z-index (defaults to 2000000000)
	  top: 'auto', // Top position relative to parent in px
	  left: 'auto' // Left position relative to parent in px
	};
	var target = document.getElementById('foo');
	var spinner = new Spinner(opts).spin(target);
}
setTimeout("showInTimeout()",30000);//如果加载超过30秒，就执行showInTimeout()
$(function() {
	$("#bg").hide();
	$("#main").hide();
	spinInit();
	var wheight = $(window).height();
	var top = wheight/2+"px";
	$("#foo").css({position:'absolute',top:top});	
});

window.onload = function(){
	loaded = true;
	showIn();
}