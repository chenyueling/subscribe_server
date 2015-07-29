/* jquery.xPic 图片管理插件  */

;(function($) {
	$.fn.extend({
		/**
		 * options = {width:150, height:150}
		 * width:父窗口的宽
		 * height:父窗口的高
		 */
		'fixParent' : function(options) {
			return this.each(function() {
				options = $.extend({
					width : '150',
					height : '150',
				}, options);
				$(this).load(function() {
					var img = $(this);
					var imgHeight = img.height();
					var imgWidth = img.width();		
					var m_left = 0;
					var m_top = 0;
					var boxWidth = options.width;
					var boxHeight = options.height - 16;
					
					if (imgHeight > boxHeight || imgWidth >boxWidth) {
						var rank;
						if (imgWidth > imgHeight) {
							rank = imgWidth/boxWidth;
							imgHeight = imgHeight/rank;
							m_top = (boxHeight-2-imgHeight)/2;
							imgWidth = boxWidth;
						} else {
							rank = imgHeight/boxHeight;
							imgWidth = imgWidth/rank;
							m_left = (boxWidth-2-imgWidth)/2;
							imgHeight = boxHeight;
						}	
					} else {
						m_top = (108-imgHeight)/2;
						m_left = (128-imgWidth)/2; 
					}
					$(img).css({
						height:imgHeight + 'px',
						width:imgWidth + 'px',
						marginTop: m_top + 'px '
					});	
					return this;
				});
			});
		},
		'fixParentFirst' : function(options) {
			return this.each(function() {
				options = $.extend({
					width : '150',
					height : '150',
				}, options);

				var img = $(this);
				var imgHeight = img.height();
				var imgWidth = img.width();		
				var m_left = 0;
				var m_top = 0;
				var boxWidth = options.width;
				var boxHeight = options.height - 16;
				
				if (imgHeight > boxHeight || imgWidth >boxWidth) {
					var rank;
					if (imgWidth > imgHeight) {
						rank = imgWidth/boxWidth;
						imgHeight = imgHeight/rank;
						m_top = (boxHeight-2-imgHeight)/2;
						imgWidth = boxWidth;
					} else {
						rank = imgHeight/boxHeight;
						imgWidth = imgWidth/rank;
						m_left = (boxWidth-2-imgWidth)/2;
						imgHeight = boxHeight;
					}	
				} else {
					m_top = (108-imgHeight)/2;
					m_left = (128-imgWidth)/2; 
				}
				$(img).css({
					height:imgHeight + 'px',
					width:imgWidth + 'px',
					marginTop: m_top + 'px '
				});	
				return this;
			});
		}
	});
	

})(jQuery);