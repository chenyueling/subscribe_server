	/*全局变量*/
	var pageLevel = 1;
	var secondContentPageAction_count;
	
	
	
	/*
	 * ***************************************
	 * 	Grids Action
	 * ***************************************
	 */
	/* 格子散落退场效果 */
	function gridOut(gridId) {
		//获取格子对象，以及初始化需要属性
		var gridObjs = new Array();  //格子对象数组
		gridObjs = $(".grid");
		var gridObjsId = new Array();	//格子Id数组
		var beed = new Array();	//随机数数组
		
		//生成随机数，用随机数可以弄成格子差次不齐退场效果
		for(i=0 ; i<gridObjs.length ;i++){beed[i]=Math.random()*500;}	
		
		//获取格子的名字Id
		for(i=0 ; i< gridObjs.length; i++){gridObjsId[i]=$(gridObjs[i]).attr("id");}
		
		for(i=0 ; i<gridObjs.length ; i++){
			if(gridObjsId[i]!=gridId){
				if($.browser.msie){
					$(gridObjs[i]).delay(beed[i]).animate({top:"900px",opacity:"0"},800);  //如果是IE
				}else{
					$(gridObjs[i]).delay(beed[i]).animate({top:"650px",opacity:"0"},800);
				}
			}			
		}
		
	}
	
	/* 格子菜单出场效果 */
	function getMachGrid(gridId){
		var matchGrid = new Array();
		switch(gridId){
			case "grid-scene":
				matchGrid.push("grid-magazine");
				break;
			case "grid-shop":
				matchGrid.push("grid-fun");
				break;
			case "grid-aoMenTravel":
				matchGrid.push("grid-fun","grid-food");
				break;
			case "grid-holiday":
				matchGrid.push("grid-aboutMacau");
				break;
			default:
				return false;
		}
		return matchGrid;
	}
	function showGridMenuInit() {
		var fromMenuClick = false;
		$(".grid").click(function(){
			if($(this).find('.meunclick').is(":hidden")){
				//格子菜单出场
				if(!fromMenuClick){
					//$("#ad1").animate({right:"-650px",opacity:"0"},800);
					
					//获取匹配格子
					var gridId = $(this).attr("id");
					var matchGrid = getMachGrid(gridId);   
					
					//除了当前格子，其他格子的菜单都收起来
					var outTarget = ".grid:not(#"+gridId+")";
					//$(outTarget).trigger("click");
					$(outTarget).find(".grid-menu").slideUp(function(){
						$(this).find(".li-br").remove();   //去掉下横线										 
					}); 
	
					//所有相应格子缩回来
					$(".grid:gt(3)").stop(true,true).not($("#"+matchGrid[0])).animate({top:'128px'});
					
					//相对应的格子位移
					var menuHeight = $(this).find(".grid-menu").css("height");  //获取菜单高度
					var moveHeight = menuHeight.substr(0,menuHeight.indexOf("px"));
					moveHeight-=-155;     //计算匹配格子移动高度
					for(i=0 ; i<matchGrid.length ; i++) {
						$("#"+matchGrid[i]).stop(true,true).animate({top:moveHeight});
					}
					
					
					$(this).find("ul").mCustomScrollbar("update"); //更新custom-scrollbar插件
					
					//菜单出场
					$(this).find(".grid-menu").slideDown();	

					
					//添加滚动三角星
					//scrollBar($(this).find(".grid-menu"));
					
					//这两行代码把滚动条给隐藏了	
									
					$(".mCSB_container").css("margin","0 0");  
					/*
					$(" .mCSB_scrollTools").css({
						opacity:"0",
						filter:"alpha(opacity=0)"
					});
					*/
					//生成列表下横线
					var liBr = "<div class='li-br'></div>";  
					$(this).find("li").slice(1).before(liBr);  
					
					$(this).find("ul").mCustomScrollbar("update"); //更新custom-scrollbar插件
			
					pageLevel = 2 ;
				}else{
					fromMenuClick = false;
				}
			}else{
				//格子菜单退场
				if(!fromMenuClick){
					
					var gridId = $(this).attr("id");
					var matchGrid = getMachGrid(gridId);   //获取匹配格子
					for(i=0 ; i<matchGrid.length ; i++) {
						$("#"+matchGrid[i]).stop(true,true);
						$("#"+matchGrid[i]).animate({top:'128px'});
					}
					
					$(this).find("img").hide();
					$(this).find(".grid-menu").slideUp(function(){
						$(this).find(".li-br").remove();   //去掉下横线
					});
					
					pageLevel = 1 ;
				}else{
					fromMenuClick = false;
				}
			}
		});
		$('.meunclick').click(function(){
			fromMenuClick = true;
		});
	}

	/* 格子菜单滑动条效果 */
	function gridMenuSlider(){
		$(".grid-menu li").mouseover(function(){
			var $this = $(this);
			var gridId = $this.parents(".grid").attr("id");
			var gridName = gridId.substr(5);
			var addClass = "alpha-"+ gridName;
			$this.addClass(addClass);
			
		});
		$(".grid-menu li").mouseout(function(){
			var $this = $(this);
			var gridId = $this.parents(".grid").attr("id");
			var gridName = gridId.substr(5);
			var addClass = "alpha-"+ gridName;
			$this.removeClass(addClass);
		});	
	}
	
	/* 格子菜单滚动效果 */
	function scrollBar($menuObj){
		//动态添加滚动三角星
		
		$menuObj.find("ul").mCustomScrollbar("update"); //更新custom-scrollbar插件
		$menuObj.find(".baricon-up").remove();
		$menuObj.find(".baricon-down").remove();	
		var bariconUp = "<div class='baricon-up '><a class='mCSB_buttonUp'></a></div>";
		var bariconDown = "<div class='baricon-down mCSB_buttonDown'><a class='mCSB_buttonDown'></a></div>";
		var display = $menuObj.find(".mCSB_scrollTools").css("display");
		if(!(display=='none')){
			$menuObj.prepend(bariconUp);
			$menuObj.append(bariconDown);
			$menuObj.find("ul").css("max-height","110px");
			
			
			//为三角星添加动作
			var $upButton = $menuObj.find(".baricon-up a");
			var $downButton = $menuObj.find(".baricon-down a");
			var $container = $menuObj.find(".mCSB_container");
			var $containerTop = $container.css("top");
			$upButton.mousedown(function(){
				var containerTop = $container.css("top");
				var containerTopNum = parseInt(containerTop.substr(0,containerTop.indexOf("px")));
				if(containerTopNum<-20){
					$container.stop(true,true).animate({top:"+=35px"});	
				}
			});
			$downButton.mousedown(function(){
				var containerTop = $container.css("top");
				var containerTopNum = parseInt(containerTop.substr(0,containerTop.indexOf("px")));
				var containerHeight = $container.height();
				containerHeight-=125;
				if(containerTopNum > -containerHeight){
					$container.stop(true,true).animate({top:"-=35px"});		
				}
			});
			
		}
	}
	
	/* 二级文章点击事件初始化 */
	function gridTextClickInit(){
		$(".grid .grid-text-title").click(function(){
			var textId = $(this).attr("id");
			var gridId = $(this).parents(".grid").attr("id");
			//如果格子是Shop、Fun、Food的话则退出
			if(gridId=="grid-shop" || gridId=="grid-fun" || gridId=="grid-food") {
				var textName = $(this).attr('name');
				var language = $(this).attr('language');
				secondContentPageAction(gridId,textName,language);
				var gridName = gridId.substr(5);
				$("#navImg").css('background','url(images/navImg-'+gridName+'.jpg');
				return ;
			}
			var mainOffset  = $("#main").offset();	
			var left = mainOffset.left+33+"px";
			var gridObj = $(this).parents(".grid");		
			var thisGridOffset = gridObj.offset();
			var gridMenu = gridObj.find(".grid-menu");
			//其余格子退场
			gridOut(gridId);
			//导航条淡去
			//$("#navbar").fadeOut();
			//天气预报淡去
			//$("#weather").fadeOut();
			//收回菜单
			//gridObj.trigger("click");
			gridMenu.slideUp();
			
			
			
			//该格子移动动画
			gridObj.offset({
				top:thisGridOffset.top,left:thisGridOffset.left 
			}).delay(500).animate({
				top:'0px',
				left:'-270px',
				margin:'0px',
				width:'121px'
			},1000,function(){
				//gridObj.trigger("click"); //展开菜单
				gridMenu.slideDown();
				$("#text-main").delay(300).slideDown();  //文章出场动画
				//$("#textframe").contents().find(".content").mCustomScrollbar("update");//更新custom-scrollbar插件
				$(".text-page-ads").css('display','block');
				$("#ad3").delay(400).fadeIn();
				$("#ad4").delay(400).fadeIn();
				if(gridId=="grid-aoMenTravel" || gridId=="grid-fun"){
					
					gridObj.find(".block-bg").fadeOut(1000);
					gridObj.find(".block-bg-min").fadeIn(1000);
					var gridMenuBg = "url(images/"+gridId+"-min-shadow.png) no-repeat";
					gridObj.find(".grid-menu").css('background',gridMenuBg);
				}
			});
			
			//动画结束解除菜单文章点击事件
			$(".grid-text-title").unbind("click");
			//$(".grid-text-title").siblings().unbind("click");
			//解除格子移动动画
			gridObj.unbind("click");		
			
			var $thisTextTitle = $(".grid").find(".grid-text-title");
			thirdGridTextClickInit($thisTextTitle);
			
			//将点击的文章load入iframe
			var id = $(this).attr('id');
			var langStr = $(".lg-active").html();
			var language = $(this).attr('language');
			var url = "showArticle?id=" + id + "&language=" + language;
			$("#textframe").attr('src', url);
			
			pageLevel = 3 ;
		});
	}
	
	/* 第二种三级界面的动作(第二种二级文章点击事件) */
	function secondContentPageAction(gridId,textName,language){
		gridOut(null);
		$("#text-main").delay(300).slideDown();  //文章出场动画
		$("#text-main").css({left:'82px',width:'701px'});
		var $gridObj = $('#'+gridId);
		var gridName = gridId.substr(5);
		iframeStaticSwitch(gridName,textName,language);
		
		$("#articleNav").css('display','none');
		$("#articleNav2").css('display','block');
		//$("#textframe").contents().find(".content").mCustomScrollbar("update");//更新custom-scrollbar插件
		$(".text-page-ads").css('display','block');
		$("#ad3").delay(400).fadeIn();
		$("#ad4").delay(400).fadeIn();
	}
	
	/* 三级文章点击初始化 */
	function thirdGridTextClickInit($thisTextTitle){
		$thisTextTitle.click(function(){
			$("#text-main").slideUp(); 
			/*
			*	从后台获取文章的数据
			*	并重新加载iframe
			*/
			var id = $(this).attr('id');
			var language = $(this).attr('language');
			var url = "showArticle?id=" + id + "&language=" + language;
			$("#textframe").attr('src', url);
			$("#text-main").delay(300).slideDown(); 
		});
	}
	
	//load入菜单数据
	function loadMenu(language) {
		$.getJSON('showMenu', {language:language}, function(json) {
			var scene = json.scene;
			var shop = json.shop;
			var aoMenTravel = json.aoMenTravel;
			var holiday = json.holiday;
			var magazine = json.magazine;
			var fun = json.fun;
			var food = json.food;
			var aboutMacau = json.aboutMacau;
			
			loadMenu2('#grid-holiday', holiday);
			loadMenu2('#grid-aoMenTravel', aoMenTravel);
			loadMenu2('#grid-shop', shop);
			loadMenu2('#grid-scene', scene);
			loadMenu2('#grid-aboutMacau', aboutMacau);
			loadMenu2('#grid-food', food);
			loadMenu2('#grid-fun', fun);
			loadMenu2('#grid-magazine', magazine);
			
			gridTextClickInit(); //格子进入文章页动画初始化
			gridMenuSlider(); //格子菜单滑动条动画
			
			$("#grid-shop .grid-text-title").each(function(i){//为菜单编号
				$(this).attr('name',i);
			});
			$("#grid-food .grid-text-title").each(function(i){//为菜单编号
				$(this).attr('name',i);
			});
			$("#grid-fun .grid-text-title").each(function(i){//为菜单编号
				$(this).attr('name',i);
			});
		});
	}
	
	//load入菜单数据子方法
	function loadMenu2(box, obj) {
		var html = "";
		for (var i=0; i<obj.length; i++) {
			html += "<li id='" + obj[i].id +"' language='CN' class='grid-text-title'><a>" + obj[i].title + "</a></li>";
		}
		$(box).find('.content').html(html);
	}
	
	
	
	/*
	 * ***************************************
	 *  Index Navigation Action
	 * ***************************************
	 */
	/* 导航条动画 */
	function navbarInit(){
		$(".nav-list li").hover(function(){
			var thisId = $(this).attr('id');
			var lg = $("#language-english").hasClass('lg-active');
			if(thisId == 'nav-list-vip' || thisId == 'nav-list-callus'){
				if(lg){$(this).stop(true,true).animate({top:"-66px"});}
				else{$(this).stop(true,true).animate({top:"-58px"});}
			}else{
				$(this).stop(true,true).animate({top:"-58px"});
			}
			//$(this).find(".nav-list-text").show();	
			//$(this).find(".nav-list-icon").hide();	
		},function(){
			$(this).animate({top:"0px"});
			//$(this).find(".nav-list-icon").show();	
			//$(this).find(".nav-list-text").hide();
		})
	}
	 
	/* 导航条菜单出场效果 */
	function showNavMenuInit() {
		var fromMenuClick = false;
		$(".nav-list-text").click(function(){
			//获取对应菜单
			var listId = $(this).parent().attr("id");
			var menuId = "#"+listId+"-menu";
			var $menuObj = $(menuId);
					
			if($menuObj.is(":hidden")){
				//格子菜单出场
				if(!fromMenuClick){ 					
					
					//菜单出场
					$menuObj.slideDown();	
					
					//生成列表下横线
					var liBr = "<div class='li-br'></div>";  
					$menuObj.find("li").slice(1).before(liBr);  
					
					//菜单定位
					var navOffset = $("#nav").offset();
					var menuTop = (navOffset.top-(-40))+"px";
					$menuObj.css("top",menuTop);
			
				}else{
					fromMenuClick = false;
				}
			}else{
				//格子菜单退场
				if(!fromMenuClick){
					$menuObj.slideUp(function(){
						$(this).find(".li-br").remove();   //去掉下横线
					});
	
				}else{
					fromMenuClick = false;
				}
			}
		});
		$('.meunclick').click(function(){
			fromMenuClick = true;
		});
	}

	/* 导航条菜单滑动条效果 */
	function navMenuSlider(){
		$(".nav-menu li").mouseover(function(){
			var $this = $(this);
			$this.addClass("alpha-navMenu");
			
		});
		$(".nav-menu li").mouseout(function(){
			var $this = $(this);
			$this.removeClass("alpha-navMenu");
		});	
	}
	
	/* 导航条标题点击效果 */
	function navTextClickInit(){
		$(".nav-menu li").click(function(){
			var $thisMenu = $(this).parents(".nav-menu");
			$thisMenu.slideUp();
		});
	}
	
	/* 导航条菜单退出效果 */
	function navOtherAnimateInit(){
		$(".nav-menu").hover(function(){
   
		},function(){
		     $(this).slideUp();
		     $(this).find(".li-br").remove();
		});
		
	}
	
	/* 导航条红色滑动条效果 */
	function navRedSliderInit(){
		$(".nav-list li").each(function(i){
			$(this).attr("no",i);
		});
		
		$(".nav-list").mouseover(function(){
			$("#nav-list-slider").show();
		});
		$(".nav-list").mouseout(function(){
			$("#nav-list-slider").hide();
		});
		
		$(".nav-list li").hover(function(){
			var liNo = $(this).attr('no');
			var numNo = parseInt(liNo);
			var left = 328+(106*numNo);
			left+="px";
			$("#nav-list-slider").delay(1000).stop(true,false).animate({left:left});

		});
	}
	

	
	/*
	 * ***************************************
	 * Content Page Navigation Action
	 * ***************************************
	 */
	/* 三级文章导航条红色滑动条效果 */
	function articleNavRedSliderInit(){
		$(".articleNav-list li").each(function(i){
			$(this).attr("no",i);
		});
		
		$(".articleNav-list").mouseover(function(){
			$("#articleNav-list-slider").show();
		});
		$(".articleNav-list").mouseout(function(){
			$("#articleNav-list-slider").hide();
		});
		
		$(".articleNav-list li").hover(function(){
			var liNo = $(this).attr('no');
			var numNo = parseInt(liNo);
			var left = 14+(67*numNo);
			left+="px";
			$("#articleNav-list-slider").delay(1000).stop(true,false).animate({left:left});

		});
	}
	
	/* 三级文章导航条标题点击效果 */
	function articleNavTitleClickInit(){
		
		$(".articleNav-list li").not($("#articleNav-list-fun,#articleNav-list-food,#articleNav-list-shop")).click(function(){
			var $this = $(this);
			
			
			/*所有格子都到了left:-270px;top:0px*/
			$(".grid").css({
				top:'0px',
				left:'-270px',
				margin:'0px',
				width:'121px',
				opacity:'1',
				display:'none'
			});
			
			/*获取目标格子*/
			var name = $this.attr('id').substr(16);
			console.log(name);
			
			var targetGridId = "grid-" + name;
			var $targetGrid = $("#"+targetGridId);
			$(".grid").not($targetGrid).fadeOut();  //除目标格子，其他格子隐藏
			$targetGrid.fadeIn().find(".grid-menu").slideDown(); //目标格子淡现，并且菜单出现
			
			/*文章更换*/
			$("#text-main").slideUp();
			var id = $targetGrid.find(".grid-text-title").first().attr('id');
			var language = $("input[name='language']").val();
			var url = "showArticle?id=" + id + "&language=" + language;
			$("#textframe").attr('src', url);
			$("#text-main").delay(300).slideDown(); 
			
			/*更换背景*/
			//var gridId = $(this).attr("id");
			var bgId = "#bg-"+targetGridId.substr(5);
			var outTarget = "#bgics .show:not("+bgId+")";
			$("#bgics img").stop(true,true);
			$(bgId).fadeIn(1000).removeClass("hidden").addClass("show");	
			$(outTarget).fadeOut(1000).removeClass("show").addClass("hidden");
			
			/*休闲娱乐和澳门任我行的格子背景更换，以及菜单背景更换*/
			if(targetGridId=="grid-aoMenTravel" || targetGridId=="grid-fun"){
				$targetGrid.find(".block-bg").fadeOut(1000);
				$targetGrid.find(".block-bg-min").fadeIn(1000);
				var gridMenuBg = "url(images/"+targetGridId+"-min-shadow.png) no-repeat";
				$targetGrid.find(".grid-menu").css('background',gridMenuBg);
			}
			/*菜单*/
			//生成列表下横线
			$targetGrid.find(".li-br").remove();
			var liBr = "<div class='li-br'></div>";  
			$targetGrid.find("li").slice(1).before(liBr);  
			
			$targetGrid.find("ul").mCustomScrollbar("update"); //更新custom-scrollbar插件
			
			//接触格子点击事件
			$targetGrid.unbind("click");
		});
	}
	
	/* 第二种三级文章导航条标题点击效果 & 文章标题点击效果 */
	function secondArticleNavTitleClickInit(){
		$(".articleNav2-list li").click(function(){
			var $this = $(this);
			thisId = $this.attr("id");
			if(thisId == "articleNav2-list-shop" || thisId == "articleNav2-list-fun" || thisId == "articleNav2-list-food"){
				//如果点击的仍然是美食、食物、娱乐版块的，则静态换内容
				var $thisMenu = $("#"+thisId+"-menu");
				var $thisLi = $("#"+thisId);
				var liPosition = $thisLi.position();
				var thisName = thisId.substr(17);
				$thisMenu.slideDown();
				$thisMenu.find(".grid-text-title").each(function(i){
					$(this).attr('name',i);
				});
				
				//生成列表下横线
				$thisMenu.find(".li-br").remove();
				var liBr = "<div class='li-br'></div>";  
				$thisMenu.find("li").slice(1).before(liBr);  
				//菜单定位
				var menuLeft = liPosition.left-(-80);
				menuLeft+="px";
				$thisMenu.css({top:"40px",left:menuLeft});
				//菜单点击事件
				$thisMenu.find("li").click(function(){
					var name = $(this).attr('name');
					var language = $(this).attr('language');
					console.log("You click menu li of :"+thisName);
					//iframe切换
					var $textMain = $("#text-main");
					$textMain.slideUp(function(){
						iframeStaticSwitch(thisName,name,language);
						$(this).slideDown();
					});
					/*更换背景*/
					changeBackGround(thisName);	
					$("#navImg").css('background','url(images/navImg-'+thisName+'.jpg');
				});	
				return;
			}
			
			/*所有格子都到了left:-270px;top:0px*/
			$(".grid").css({
				top:'0px',
				left:'-270px',
				margin:'0px',
				width:'121px',
				opacity:'1',
				display:'none'
			});

			
			
			/*获取目标格子*/
			var name = $this.attr('id').substr(17);
			console.log(name);
			
			var targetGridId = "grid-" + name;
			var $targetGrid = $("#"+targetGridId);
			console.log(targetGridId);
			
			/*文章更换*/
			$("#text-main").slideUp(function(){
				//iframe切换
				var $textMain = $("#text-main");
				$("#text-main").css({left:'21.48%',width:'54.68%'});
				$("#articleNav").css('display','block');
				$("#articleNav2").css('display','none');
				var id = $targetGrid.find(".grid-text-title").first().attr('id');
				var language = $(this).attr('language');
				var url = "showArticle?id=" + id + "&language=" + language;
				$("#textframe").attr('src', url);
				$(this).slideDown(); 
				$targetGrid.fadeIn().find(".grid-menu").slideDown(); //目标格子淡现，并且菜单出现

			});
			
			
			/*更换背景*/
			//var gridId = $(this).attr("id");
			var bgId = "#bg-"+targetGridId.substr(5);
			var outTarget = "#bgics .show:not("+bgId+")";
			$("#bgics img").stop(true,true);
			$(bgId).fadeIn(1000).removeClass("hidden").addClass("show");	
			$(outTarget).fadeOut(1000).removeClass("show").addClass("hidden");
			
			/*休闲娱乐和澳门任我行的格子背景更换，以及菜单背景更换*/
			if(targetGridId=="grid-aoMenTravel" || targetGridId=="grid-fun"){
				$targetGrid.find(".block-bg").fadeOut(1000);
				$targetGrid.find(".block-bg-min").fadeIn(1000);
				var gridMenuBg = "url(images/"+targetGridId+"-min-shadow.png) no-repeat";
				$targetGrid.find(".grid-menu").css('background',gridMenuBg);
			}
			/*菜单*/
			//生成列表下横线
			$targetGrid.find(".li-br").remove();
			var liBr = "<div class='li-br'></div>";  
			$targetGrid.find("li").slice(1).before(liBr);  
			
			$targetGrid.find("ul").mCustomScrollbar("update"); //更新custom-scrollbar插件
			
			//接触格子点击事件
			$targetGrid.unbind("click");
		});
	}

	/* 第一种三级文章导航条标题点击效果 & 文章标题点击效果  */
	function firstArticleNavTitleClickInit(gridName){
		
		var $liShop = $("#articleNav-list-"+gridName);
		var $menuObj = $("#articleNav-list-"+gridName+"-menu");
		$liShop.unbind("click");
		$menuObj.find(".grid-text-title").each(function(i){
			$(this).attr('name',i);
		});

		//菜单出场
		$liShop.click(function(){	
			var shopPosition = $liShop.position();
			$menuObj.slideDown();
			//生成列表下横线
			$menuObj.find(".li-br").remove();
			var liBr = "<div class='li-br'></div>";  
			$menuObj.find("li").slice(1).before(liBr);  
			//菜单定位
			var menuLeft = shopPosition.left+"px";
			$menuObj.css({top:"40px",left:menuLeft});
			//菜单点击事件
			$menuObj.find("li").click(function(){
				var name = $(this).attr('name');
				var language = $(this).attr('language');
				console.log("You click menu li of :"+gridName);		
				$(".grid").fadeOut();
				//iframe切换
				var $textMain = $("#text-main");
				$textMain.slideUp(function(){
					$("#text-main").css({left:'82px',width:'701px'});
					$("#articleNav").css('display','none');
					$("#articleNav2").css('display','block');
					iframeStaticSwitch(gridName,name,language);
					$(this).slideDown();
				});
				/*更换背景*/
				changeBackGround(gridName);
				$("#navImg").css('background','url(images/navImg-'+gridName+'.jpg');
			});
		});	
	}
		
	
	/* 
	 * ***************************************
	 * Advertisement Action
	 * ***************************************
	 */
	/* 广告效果初始化 */
	function adInit(){
		
		/* 顶部广告栏动画(top-ad) */ 
		$(".adclose1").click(function(){
			$(this).parent().animate({top:"-65px"},300);
		});	
		$("#top-ad").mouseover(function(){
			$(this).animate({top:"0px"},300);
		});
				
		/* 广告栏动画 (ad*) */ 
		$(".adclose2").click(function(){
			$(this).parent().animate({right:"-650px",opacity:"0"},800);
		});	
		
	}
	
	
	
	/* 
	 * ***************************************
	 * Other Action
	 * ***************************************
	 */
	/* 返回首页（刷新返回） */
	function myrefresh() 
	{ 
		//window.location.reload(); 
		location.reload();
		//window.history.go(0);
		//return false;
		//location.reload( true );
		//window.location.href = window.location;		
		//history.go(0);
		//location.reload();
		//location=location;
		//location.assign(location);
		//window.navigate(location);
		//location.replace(location);
		//window.history.back();
	} 
	function backToIndex(){
		myrefresh();
	}
	
	/* 天气预报 */
	function weatherInit(){
		$("#weather .left").click(function(){
			nowWtr = $("#weatherSlider").attr('nowWtr');
			nowWtr = parseInt(nowWtr);		
			if(nowWtr>1 && nowWtr<=3){
				nowWtr--;
				//console.info("你按了left，这是nowWtr:"+nowWtr);
				$("#weatherSlider").animate({left:'+=110px'},"normal");
				$("#weatherSlider").attr('nowWtr',nowWtr);
			}else{
				//console.info("移动不了");
			}
		});
		$("#weather .right").click(function(){
			nowWtr = $("#weatherSlider").attr('nowWtr');
			nowWtr = parseInt(nowWtr);		
			if(nowWtr>=1 && nowWtr<3){
				nowWtr++;
				//console.info("你按了right，这是nowWtr:"+nowWtr);
				$("#weatherSlider").animate({left:'-=110px'},"normal")
				$("#weatherSlider").attr('nowWtr',nowWtr);
			}else{
				//console.info("移动不了");
			}
		});
		$.getJSON('showWeather', {language:'CN'}, function(json) {
			if (json) {
				$('#todayTemp').find('span').text(json.temp);
				$('#weatherSlider').find("div[name='1']").find('.wtr-dayName').text('今天');
				$('#weatherSlider').find("div[name='1']").find('.wtr-temp').text(json.lowTemp1 + '℃/' + json.highTemp1 + '℃');
				$('#weatherSlider').find("div[name='1']").find('.wtr-city').text('澳门');
				$('#weatherSlider').find("div[name='1']").find('.wtr-statu').text(json.cloud1);
				$('#weatherSlider').find("div[name='1']").find('.wtr-wind').text(json.wind1);
				
				$('#weatherSlider').find("div[name='2']").find('.wtr-dayName').text('明天');
				$('#weatherSlider').find("div[name='2']").find('.wtr-temp').text(json.lowTemp2 + '℃/' + json.highTemp2 + '℃');
				$('#weatherSlider').find("div[name='2']").find('.wtr-city').text('澳门');
				$('#weatherSlider').find("div[name='2']").find('.wtr-statu').text(json.cloud2);
				$('#weatherSlider').find("div[name='2']").find('.wtr-wind').text(json.wind2);
				
				$('#weatherSlider').find("div[name='3']").find('.wtr-dayName').text('后天');
				$('#weatherSlider').find("div[name='3']").find('.wtr-temp').text(json.lowTemp3 + '℃/' + json.highTemp3 + '℃');
				$('#weatherSlider').find("div[name='3']").find('.wtr-city').text('澳门');
				$('#weatherSlider').find("div[name='3']").find('.wtr-statu').text(json.cloud3);
				$('#weatherSlider').find("div[name='3']").find('.wtr-wind').text(json.wind3);
			}
			
		});
	}
	
	/* 中英文切换 */
	function changeFront(){
		var $lgCN = $("#language-chinese");
		var $lgEN = $("#language-english");

		/* 全局中英文标识切换  */ 
		$lgCN.click(function(){
			$("input[type='language']").val('CN');
		});
		$lgCN.click(function() {
			$("input[type='language']").val('EN');
		});

		/* Comment格子的菜单都收起来 */
		$("#language-chinese , #language-english").click(function(){
			var outTarget = ".grid";
			
			if(pageLevel == 2){
				$(outTarget).find(".grid-menu").slideUp(function(){
					$(this).find(".li-br").remove();   //去掉下横线	
				}); 
				$(outTarget).slice(4).animate({top:'128px'});
			}
			
			if(pageLevel == 3){
				//生成列表下横线
				$(outTarget).find(".grid-menu").slideUp(function(){
					$(this).delay(100).slideDown(); 
					
				}); 	
			}
		});

		/* 导航条中英切换 */
		$lgCN.click(function(){
			$(this).addClass("lg-active");
			$lgEN.removeClass("lg-active");
			$("#nav-list-info .nav-list-text").text("实用资讯");
			$("#nav-list-friendlink .nav-list-text").text("友情链接");
			$("#nav-list-callus .nav-list-text").text("联系我们");
			$("#nav-list-vip .nav-list-text").text("会员专享");
			$("#nav-list-maps .nav-list-text").text("澳门地图");
			$("#nav-list-info-menu li a").each(function(i){
				switch(i){
				case 0:
					$(this).text("澳门紧急电话");
					break;
				case 1:
					$(this).text("澳门巴士资讯");
					break;
				}
			});
			$("#nav-list-vip-menu li a").each(function(i){
				switch(i){
				case 0:
					$(this).text("会员注册");
					break;
				case 1:
					$(this).text("会员专享");
					break;
				case 2:
					$(this).text("互动专区");
					break;
				}
			});
		});
		$lgEN.click(function(){
			$(this).addClass("lg-active");
			$lgCN.removeClass("lg-active");
			$("#nav-list-info .nav-list-text").text("Info");
			$("#nav-list-friendlink .nav-list-text").text("Links");
			$("#nav-list-callus .nav-list-text").text("Contact us");
			$("#nav-list-vip .nav-list-text").text("Member interaction");
			$("#nav-list-maps .nav-list-text").text("Maps");
			$("#nav-list-info-menu li a").each(function(i){
				switch(i){
				case 0:
					$(this).text("Macau Emergency Call");
					break;
				case 1:
					$(this).text("Macau Bus Information");
					break;
				}
			});
			$("#nav-list-vip-menu li a").each(function(i){
				switch(i){
				case 0:
					$(this).text("Sign Up");
					break;
				case 1:
					$(this).text("Members Special");
					break;
				case 2:
					$(this).text("Interactive Zone");
					break;
				}
			});
		});
			
		/* 色块中英文切换 */
		$lgCN.click(function(){
			var $grids = $(".grid");
			$grids.each(function(){
				var thisGridId = $(this).attr("id");
				var bgStr = "url(images/"+thisGridId+".png) no-repeat";
				var bgMinStr = "url(images/"+thisGridId+"-min.png) no-repeat";
				$(this).find(".block-bg ").css("background",bgStr);
				$(this).find(".block-bg-min").css("background",bgMinStr);
			});
		});
		$lgEN.click(function(){
			var $grids = $(".grid");
			$grids.each(function(){
				var thisGridId = $(this).attr("id");
				var bgStr = "url(images/"+thisGridId+"-en.png) no-repeat";
				var bgMinStr = "url(images/"+thisGridId+"-min-en.png) no-repeat";
				$(this).find(".block-bg ").css("background",bgStr);
				$(this).find(".block-bg-min").css("background",bgMinStr);
			});
		});		
		
		/* 天气预报中英文切换 */
		$lgCN.click(function(){
			$.getJSON('showWeather', {language:'CN'}, function(json) {
				if (json) {
					$('#todayTemp').find('span').text(json.temp);
					$('#weatherSlider').find("div[name='1']").find('.wtr-dayName').text('今天');
					$('#weatherSlider').find("div[name='1']").find('.wtr-temp').text(json.lowTemp1 + '℃/' + json.highTemp1 + '℃');
					$('#weatherSlider').find("div[name='1']").find('.wtr-city').text('澳门');
					$('#weatherSlider').find("div[name='1']").find('.wtr-statu').text(json.cloud1);
					$('#weatherSlider').find("div[name='1']").find('.wtr-wind').text(json.wind1);
					
					$('#weatherSlider').find("div[name='2']").find('.wtr-dayName').text('明天');
					$('#weatherSlider').find("div[name='2']").find('.wtr-temp').text(json.lowTemp2 + '℃/' + json.highTemp2 + '℃');
					$('#weatherSlider').find("div[name='2']").find('.wtr-city').text('澳门');
					$('#weatherSlider').find("div[name='2']").find('.wtr-statu').text(json.cloud2);
					$('#weatherSlider').find("div[name='2']").find('.wtr-wind').text(json.wind2);
					
					$('#weatherSlider').find("div[name='3']").find('.wtr-dayName').text('后天');
					$('#weatherSlider').find("div[name='3']").find('.wtr-temp').text(json.lowTemp3 + '℃/' + json.highTemp3 + '℃');
					$('#weatherSlider').find("div[name='3']").find('.wtr-city').text('澳门');
					$('#weatherSlider').find("div[name='3']").find('.wtr-statu').text(json.cloud3);
					$('#weatherSlider').find("div[name='3']").find('.wtr-wind').text(json.wind3);
				}			
			});
			$(".wtr-day").css('font-size','11pt');
		});
		$lgEN.click(function(){
			$.getJSON('showWeather', {language:'EN'}, function(json) {
				if (json) {
					$('#todayTemp').find('span').text(json.temp);
					$('#weatherSlider').find("div[name='1']").find('.wtr-dayName').text('Today');
					$('#weatherSlider').find("div[name='1']").find('.wtr-temp').text(json.lowTemp1 + '℃/' + json.highTemp1 + '℃');
					$('#weatherSlider').find("div[name='1']").find('.wtr-city').text('Macao');
					$('#weatherSlider').find("div[name='1']").find('.wtr-statu').text(json.cloud1);
					$('#weatherSlider').find("div[name='1']").find('.wtr-wind').text(json.wind1);
					
					$('#weatherSlider').find("div[name='2']").find('.wtr-dayName').text('Tomorrow');
					$('#weatherSlider').find("div[name='2']").find('.wtr-temp').text(json.lowTemp2 + '℃/' + json.highTemp2 + '℃');
					$('#weatherSlider').find("div[name='2']").find('.wtr-city').text('Macao');
					$('#weatherSlider').find("div[name='2']").find('.wtr-statu').text(json.cloud2);
					$('#weatherSlider').find("div[name='2']").find('.wtr-wind').text(json.wind2);
					
					$('#weatherSlider').find("div[name='3']").find('.wtr-dayName').text('Acquired');
					$('#weatherSlider').find("div[name='3']").find('.wtr-temp').text(json.lowTemp3 + '℃/' + json.highTemp3 + '℃');
					$('#weatherSlider').find("div[name='3']").find('.wtr-city').text('Macao');
					$('#weatherSlider').find("div[name='3']").find('.wtr-statu').text(json.cloud3);
					$('#weatherSlider').find("div[name='3']").find('.wtr-wind').text(json.wind3);
				}
			});
			$(".wtr-day").css('font-size','8.6pt');
		});	
		
		/* 文章题目中英文切换 */
		$lgCN.click(function(){
			$("input[name='language']").val('CN');
			$.getJSON('reflushMenu', {language:'CN'}, function(json) {
				var all = json.all;
				for (var i=0; i<all.length; i++) {
					var id = '#' + all[i].id;
					$(id).find('a').html(all[i].title);
					$(id).attr('language', 'CN');
				}
			});
		});
		$lgEN.click(function(){
			$("input[name='language']").val('EN');
			$.getJSON('reflushMenu', {language:'EN'}, function(json) {
				var all = json.all;
				for (var i=0; i<all.length; i++) {
					var id = '#' + all[i].id;
					$(id).find('a').html(all[i].title);
					$(id).attr('language', 'EN');
				}
			});
		});	
	
		/* 三级文章页导航条中英切换 */
		$lgCN.click(function(){
			$("#articleNav-list-scene span").text("景点观光");
			$("#articleNav-list-food span").text("特色美食");
			$("#articleNav-list-fun span").text("休闲娱乐");
			$("#articleNav-list-shop span").text("购物天堂");
			$("#articleNav-list-holiday span").text("节日盛事");
			$("#articleNav-list-aboutMacau span").text("认识澳门");
			$("#articleNav-list-aoMenTravel span").text("澳门任我行");
			$("#articleNav-list-magazine span").text("期刊搜索");
			$(".articleNav-list-text").css('font-size','10pt');
			$(".articleNav-list li").css('width','67px');
			//购物天堂    导航菜单
			$("#articleNav-list-shop-menu li").each(function(i){
				switch(i){
				case 0:
					$(this).text("大型购物商场");
					$(this).attr('language','CN');
					break;
				case 1:
					$(this).text("分区购物");
					$(this).attr('language','CN');
					break;
				case 2:
					$(this).text("分类购物");
					$(this).attr('language','CN');
					break;
				}
			});
			//特色美食    导航菜单
			$("#articleNav-list-food-menu li").each(function(i){
				switch(i){
				case 0:
					$(this).text("澳门小食手信");
					$(this).attr('language','CN');
					break;
				case 1:
					$(this).text("澳门土生本地菜");
					$(this).attr('language','CN');
					break;
				case 2:
					$(this).text("澳门地道特色小食手信推介");
					$(this).attr('language','CN');
					break;
				case 3:
					$(this).text("澳门饕餮食肆介绍");
					$(this).attr('language','CN');
					break;
				}
			});
			//娱乐    导航菜单
			$("#articleNav-list-fun-menu li").each(function(i){
				switch(i){
				case 0:
					$(this).text("文娱宴会");
					$(this).attr('language','CN');
					break;
				case 1:
					$(this).text("文创产业区");
					$(this).attr('language','CN');
					break;
				case 2:
					$(this).text("家庭乐缤纷");
					$(this).attr('language','CN');
					break;
				case 3:
					$(this).text("酒店度假村");
					$(this).attr('language','CN');
					break;
				case 4:
					$(this).text("不夜城澳门");
					$(this).attr('language','CN');
					break;
				}
			});
		
		
		});
		$lgEN.click(function(){
			$("#articleNav-list-scene span").text("Tourism");
			$("#articleNav-list-food span").text("Dining");
			$("#articleNav-list-fun span").text("Shows & Entertainment");
			$("#articleNav-list-shop span").text("Shopping");
			$("#articleNav-list-holiday span").text("Festivities");
			$("#articleNav-list-aboutMacau span").text("I Love Macau");
			$("#articleNav-list-aoMenTravel span").text("Culture");
			$("#articleNav-list-magazine span").text("Search");
			$(".articleNav-list-text").css('font-size','8pt');
			$(".articleNav-list li").css('width','63px');
			//购物天堂     导航菜单
			$("#articleNav-list-shop-menu li").each(function(i){
				switch(i){
				case 0:
					$(this).text("Shopping Malls");
					$(this).attr('language','EN');
					break;
				case 1:
					$(this).text("Shopping By Zones");
					$(this).attr('language','EN');
					break;
				case 2:
					$(this).text("Shopping By Category");
					$(this).attr('language','EN');
					break;
				}
			});
			//特色美食    导航菜单
			$("#articleNav-list-food-menu li").each(function(i){
				switch(i){
				case 0:
					$(this).text("Macau's Gastronomy");
					$(this).attr('language','EN');
					break;
				case 1:
					$(this).text("Macanese Cuisine");
					$(this).attr('language','EN');
					break;
				case 2:
					$(this).text("Unique Macau Snacks& Souvenirs");
					$(this).attr('language','EN');
					break;
				case 3:
					$(this).text("Macau Restaurant Recommendation");
					$(this).attr('language','EN');
					break;
				}
			});
			//娱乐    导航菜单
			$("#articleNav-list-fun-menu li").each(function(i){
				switch(i){
				case 0:
					$(this).text("Evening Entertainment");
					$(this).attr('language','EN');
					break;
				case 1:
					$(this).text("Cultural And Creative Zone");
					$(this).attr('language','EN');
					break;
				case 2:
					$(this).text("Family Fun");
					$(this).attr('language','EN');
					break;
				case 3:
					$(this).text("Hotels & Resorts");
					$(this).attr('language','EN');
					break;
				case 4:
					$(this).text("Nightlife Macau");
					$(this).attr('language','EN');
					break;
				}
			});
		
		});
		/* 三级文章页第二种导航条中英切换 */
		$lgCN.click(function(){
			$("#articleNav2-list-scene span").text("景点观光");
			$("#articleNav2-list-food span").text("特色美食");
			$("#articleNav2-list-fun span").text("休闲娱乐");
			$("#articleNav2-list-shop span").text("购物天堂");
			$("#articleNav2-list-holiday span").text("节日盛事");
			$("#articleNav2-list-aboutMacau span").text("认识澳门");
			$("#articleNav2-list-aoMenTravel span").text("澳门任我行");
			$("#articleNav2-list-magazine span").text("期刊搜索");
			$(".articleNav2-list-text").css('font-size','10pt');
			$(".articleNav2-list li").css('width','67px');
			//导航菜单
			$("#articleNav2-list-shop-menu li").each(function(i){
				switch(i){
				case 0:
					$(this).text("大型购物商场");
					$(this).attr('language','CN');
					break;
				case 1:
					$(this).text("分区购物");
					$(this).attr('language','CN');
					break;
				case 2:
					$(this).text("分类购物");
					$(this).attr('language','CN');
					break;
				}
			});
			//特色美食    导航菜单
			$("#articleNav2-list-food-menu li").each(function(i){
				switch(i){
				case 0:
					$(this).text("澳门小食手信");
					$(this).attr('language','CN');
					break;
				case 1:
					$(this).text("澳门土生本地菜");
					$(this).attr('language','CN');
					break;
				case 2:
					$(this).text("澳门地道特色小食手信推介");
					$(this).attr('language','CN');
					break;
				case 3:
					$(this).text("澳门饕餮食肆介绍");
					$(this).attr('language','CN');
					break;
				}
			});
			//娱乐    导航菜单
			$("#articleNav2-list-fun-menu li").each(function(i){
				switch(i){
				case 0:
					$(this).text("文娱宴会");
					$(this).attr('language','CN');
					break;
				case 1:
					$(this).text("文创产业区");
					$(this).attr('language','CN');
					break;
				case 2:
					$(this).text("家庭乐缤纷");
					$(this).attr('language','CN');
					break;
				case 3:
					$(this).text("酒店度假村");
					$(this).attr('language','CN');
					break;
				case 4:
					$(this).text("不夜城澳门");
					$(this).attr('language','CN');
					break;
				}
			});
		
		});
		$lgEN.click(function(){
			$("#articleNav2-list-scene span").text("Tourism");
			$("#articleNav2-list-food span").text("Dining");
			$("#articleNav2-list-fun span").text("Shows & Entertainment");
			$("#articleNav2-list-shop span").text("Shopping");
			$("#articleNav2-list-holiday span").text("Festivities");
			$("#articleNav2-list-aboutMacau span").text("I Love Macau");
			$("#articleNav2-list-aoMenTravel span").text("Culture");
			$("#articleNav2-list-magazine span").text("Search");
			$(".articleNav2-list-text").css('font-size','8pt');
			$(".articleNav2-list li").css('width','63px');
			//导航菜单
			$("#articleNav2-list-shop-menu li").each(function(i){
				switch(i){
				case 0:
					$(this).text("Shopping Malls");
					$(this).attr('language','EN');
					break;
				case 1:
					$(this).text("Shopping By Zones");
					$(this).attr('language','EN');
					break;
				case 2:
					$(this).text("Shopping By Category");
					$(this).attr('language','EN');
					break;
				}
			});
			//特色美食    导航菜单
			$("#articleNav2-list-food-menu li").each(function(i){
				switch(i){
				case 0:
					$(this).text("Macau's Gastronomy");
					$(this).attr('language','EN');
					break;
				case 1:
					$(this).text("Macanese Cuisine");
					$(this).attr('language','EN');
					break;
				case 2:
					$(this).text("Unique Macau Snacks& Souvenirs");
					$(this).attr('language','EN');
					break;
				case 3:
					$(this).text("Macau Restaurant Recommendation");
					$(this).attr('language','EN');
					break;
				}
			});
			//娱乐    导航菜单
			$("#articleNav2-list-fun-menu li").each(function(i){
				switch(i){
				case 0:
					$(this).text("Evening Entertainment");
					$(this).attr('language','EN');
					break;
				case 1:
					$(this).text("Cultural And Creative Zone");
					$(this).attr('language','EN');
					break;
				case 2:
					$(this).text("Family Fun");
					$(this).attr('language','EN');
					break;
				case 3:
					$(this).text("Hotels & Resorts");
					$(this).attr('language','EN');
					break;
				case 4:
					$(this).text("Nightlife Macau");
					$(this).attr('language','EN');
					break;
				}
			});
		
		});
		
		
	}
	
	/* 背景更新动画 */
	function bgChange(){
		/* 主页背景变模糊 */
		$("#bg-fuzzy").delay(600).fadeIn(2000);
		$("#bg-clearness").delay(2400).fadeOut("slow");	
		
		/* 为格子点击事件实现相应背景更换效果 */
		$(".grid").click(function(){	
			var gridId = $(this).attr("id");
			var bgId = "#bg-"+gridId.substr(5);
			var outTarget = "#bgics .show:not("+bgId+")";
			$("#bgics img").stop(true,true);
			$(bgId).fadeIn(1000).removeClass("hidden").addClass("show");	
			$(outTarget).fadeOut(1000).removeClass("show").addClass("hidden");
		});
	}
	
	/* 页面布局初始化 */
	function winInit() {
		winHeight = $(window).height();
		winWidth = $(window).width();
		var left;
		var top;
		if(winWidth >= 1024 ){left = (winWidth-1024)/2;}
		if(winHeight >= 768) {top = (winHeight-768)/2;}
		$('#main').css({height:winHeight,left:left});
		$('#bg').css({height:winHeight,left:left,width:"1024px",height:"768px"});   //去掉页面会变成自适应
	};
	$(function(){
		winInit();
		$(window).resize(function(){
			winInit();
		});
	});
	
	
	
	
	/*
	 * ***************************************
	 * Tool Method
	 * ***************************************
	 */
	/* 更换背景方法 */
	function changeBackGround(gridName){
		var bgId = "#bg-"+gridName;
		var outTarget = "#bgics .show:not("+bgId+")";
		$("#bgics img").stop(true,true);
		$(bgId).fadeIn(1000).removeClass("hidden").addClass("show");	
		$(outTarget).fadeOut(1000).removeClass("show").addClass("hidden");
	}
	
	/* iframe内容框静态修改 */
	function iframeStaticSwitch(gridName,textNum,langaue){
		
		var src=gridName+'-'+textNum+'-iframe-'+langaue+'.html';
		console.log("iframe URL :"+src);
		$("#textframe").attr('src',src);
	}
	
	
	