<!-- GreenBar 伸展 -->
function showGreenBar(id,name,typeId){
	$("*").stop();
	$("#ColumnContainer").empty().css("height","770px"); //清空商品
	$(".greenbar").remove();
	$(".left .goods-stream").hide();
	$(".title p").empty().hide();
	$("#titlebar .title").hide();
	var $div1 =$("<div class='greenbar'></div>");
	var $div2 = $("<div class='greenbar'></div>");
	$div1.css({
		"width":"0",
		"height":"40px",
		"background":"#89C23D",
		"float":"right",
		"position":"relative",
		"top":(id)*42
	});
	$div2.css({
		"width":"8px",
		"height":0,
		"background":"#89C23D",
		"float":"right",
		"position":"absolute",
		"z-index":"2",
		"top":id*42,
		"right":"37px"
	});
	$("#titlebar .title").css({
		"position":"relative",
		"width":"0"
	});
	
	$(".left").prepend($div1);
	$(".left").prepend($div2);
	
	
	/*animate的回调函数来做*/
	$div1.animate({width:'45px'},250,function(){
		$div2.animate({height:(id-1)*42,top:'42px'},150,function(){
			$("#titlebar .title").show().animate({width:'100%'},1000,function(){
				$(".title p").html(name).fadeIn(function(){
					$(".left .goods-stream").fadeIn(function(){
						showGoods(typeId);
					});
				});
			});
		});
	});
	
			
	/*		
	$div1.animate({width:'45px'},500);
	$div2.delay(450).animate({height:(id-1)*42,top:'42px'},100);
	$("#titlebar .title").show().delay(550).animate({width:'100%'},1000);
	$(".title p").html("分类"+id).delay(1600).fadeIn();
	$(".left .goods-stream").delay(1500).fadeIn();
	*/
};


/*Kissy WaterFall 商品的导入*/
function showGoods(typeId){
KISSY.use("waterfall,ajax,node,button", function (S, Waterfall, io,  Node, Button) {
	var $ = Node.all;

	var tpl = $('#tpl').html(),
		nextpage = 1,
		rows = 20,
		page = 1,
		waterfall = new Waterfall.Loader({
			container:"#ColumnContainer",
			load:function (success, end) {
				$('#loadingPins').show();
				S.ajax({
					data:{
						'rows':rows,
						'page':page,
						'firstLevelType':typeId
					},
					url:'storeGoodsWaterfull',
					dataType:"json",
					jsonp:"jsoncallback",
					success:function (d) {
						// 如果数据错误, 则立即结束
						var tip = d.tip;
						if (tip.message != "") {
							alert(tip.message);
							end();
							return;
						}
						
						
						// 如果到最后一页了, 也结束加载
						nextpage = page + 1;
						if (nextpage > ((d.total/rows)+2)) {
							end();
							return;
						}
						page = nextpage;
						// 拼装每页数据
						var items = [];
						S.each(d.rows, function (item) {
							/*所用到的字段：
							**price
							**height
							**collection
							**title
							**src
							**id
							*/
							items.push(S.substitute(tpl,item));
						});
						success(items);
					},
					complete:function () {
						$('#loadingPins').hide();
					}
				});
			},
			minColCount:2,
			colWidth:155
			//align:'left' // right, center (default)
		});
	waterfall.on('adjustComplete', function () {
		S.log('after adjust complete!');
	});
	waterfall.on('addComplete', function () {
		S.log('after add complete!');
	});
	// scrollTo
	$('#BackToTop').on('click', function (e) {
		e.halt();
		e.preventDefault();
		$(window).stop();
		$(window).animate({
			scrollTop:0
		}, 1, "easeOut");
	});

	var b1 = new Button({
		content:"停止加载",
		render:"#button_container"
	});

	// 点击按钮后, 停止瀑布图效果
	b1.render();

	b1.on("click", function () {
		waterfall.destroy();
	});

	$("#ColumnContainer").delegate("click", ".del", function (event) {
		var w = $(event.currentTarget).parent(".ks-waterfall");
		waterfall.removeItem(w, {
			callback:function () {
				alert("删除完毕");
			}
		});
	});


	$("#ColumnContainer").delegate("click", ".grow", function (event) {
		var w = $(event.currentTarget).parent(".ks-waterfall");
		waterfall.adjustItem(w, {
			process:function () {
				w.append("<p>i grow height by 100</p>");
			},
			callback:function () {
				alert("调整完毕");
			}
		});
	});
	
	/*
	$("#ColumnContainer").delegate("hover",".collect", function ( event ) {
		var w = $(event.currentTarget).children("span");
		if (event.type == 'mouseenter') {
			w.replaceWith("<span class='collects'>收藏</span>");
			w.css("text-indent","3px");
		} else {
			w.replaceWith("<span class='collectionAmount'>9</span>");
			w.css("text-indent","13px");
		}
	});
	*/
	
	var collect;
	$('#ColumnContainer').delegate("mouseover", ".collect", function (event) {
		var w = $(event.currentTarget).children("span");
		var text = w.text();
		if(text >= 0){
			collect = text;
		}
		
		w.replaceWith("<span class='collects'>收藏</span>");
		//w.css("text-indent","3px");
	});
	
	$('#ColumnContainer').delegate("mouseout", ".collect", function (event) {
		var w = $(event.currentTarget).children("span");
		w.replaceWith("<span class='collectionAmount'>"+collect+"</span>");
		//w.css("text-indent","13px");
	});
	
});
}


$(function(){
	$(".left .goods-stream").hide();
	var name = $("#sort-1 p").text();
	var firstTypeId = $("#sort-1").attr("typeId");
	showGreenBar(1,name, firstTypeId);
	$(".sidebar a").click(function(){		
		var id = $(this).attr("id").substr(5);
		var firstTypeId = $(this).attr("typeId");
		var name = $(this).text();
		showGreenBar(id,name,firstTypeId);	
	});		
});
