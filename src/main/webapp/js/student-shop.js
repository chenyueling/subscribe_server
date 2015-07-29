
KISSY.use("waterfall,ajax,node,button", function (S, Waterfall, io,  Node, Button) {
    var $ = Node.all;
    var tpl = $('#tpl').html(),
	nextpage = 1,
	waterfall = new Waterfall.Loader({
		container:"#ColumnContainer",
		load:function (success, end) {
			$('#loadingPins').show();
			S.ajax({
				data:{
					'method':'flickr.photos.search',
					'api_key':'5d93c2e473e39e9307e86d4a01381266',
					'tags':'rose',
					'page':nextpage,
					'per_page':20,
					'format':'json'
				},
				url:'http://api.flickr.com/services/rest/',
				dataType:"jsonp",
				jsonp:"jsoncallback",
				success:function (d) {
					// 如果数据错误, 则立即结束
					if (d.stat !== 'ok') {
						alert('load data error!');
						end();
						return;
					}
					// 如果到最后一页了, 也结束加载
					nextpage = d.photos.page + 1;
					if (nextpage > d.photos.pages) {
						end();
						return;
					}
					// 拼装每页数据
					var items = [];
					S.each(d.photos.photo, function (item) {
						/*所用到的字段：
						**price
						**height
						**collection
						**title
						**src
						*/
						item.height = Math.round(Math.random() * (300 - 180) + 180); // fake height	
						item.collection = 10;	//测试用
						item.price = 1800;		//测试用
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
		colWidth:175
		//align:'left' // right, center (default)
	});
    waterfall.on('adjustComplete', function () {
        S.log('after adjust complete!');
    });
	//加载一页数据完成后触发的事件
    waterfall.on('addComplete', function () {
        S.log('after add complete!');
		waterfall.pause();
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
	//加载更多按钮
	$("#button_container_more").on('click',function(){	
		waterfall = new Waterfall.Loader({
			container:"#ColumnContainer",
			load:function (success, end) {
				$('#loadingPins').show();
				$('.loader').hide();
				S.ajax({
					data:{
						'method':'flickr.photos.search',
						'api_key':'5d93c2e473e39e9307e86d4a01381266',
						'tags':'rose',
						'page':nextpage,
						'per_page':20,
						'format':'json'
					},
					url:'http://api.flickr.com/services/rest/',
					dataType:"jsonp",
					jsonp:"jsoncallback",
					success:function (d) {
						// 如果数据错误, 则立即结束
						if (d.stat !== 'ok') {
							alert('load data error!');
							end();
							return;
						}
						// 如果到最后一页了, 也结束加载
						nextpage = d.photos.page + 1;
						if (nextpage > d.photos.pages) {
							end();
							return;
						}
						// 拼装每页数据
						var items = [];
						S.each(d.photos.photo, function (item) {
							/*所用到的字段：
							**price
							**height
							**collection
							**title
							**src
							*/
							item.height = Math.round(Math.random() * (300 - 180) + 180); // fake height	
							item.collection = 10;	//测试用
							item.price = 1800;		//测试用
							items.push(S.substitute(tpl,item));
						});
						success(items);
					},
					complete:function () {
						$('#loadingPins').hide();
						$('.loader').show();
					}
				});
			},
			minColCount:2,
			colWidth:175
			//align:'left' // right, center (default)
		});
		waterfall.on('addComplete', function () {
        	S.log('after add complete!');
			waterfall.pause();
    	});		
	});
	
	//收藏按钮功能
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
