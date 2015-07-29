Cufon.replace('h1', { fontFamily: 'titt', hover: true });
Cufon.replace('h2', { fontFamily: 'titt', hover: true });
Cufon.replace('h3', { fontFamily: 'titt', hover: true });
Cufon.replace('h4', { fontFamily: 'titt', hover: true });
Cufon.replace('h5', { fontFamily: 'titt', hover: true });
Cufon.replace('h6', { fontFamily: 'titt', hover: true });
Cufon.replace('.menu ul li a', { fontFamily: 'titt', hover: true });
Cufon.replace('.read_more_button a', { fontFamily: 'titt', hover: true });

$(document).ready(function() {
	$(".menu ul li ul li:last-child").addClass("last_sublist_item");
	$(".menu ul li ul li ul li:last-child a").addClass("no_bcg");
});

$(document).ready(function() { 
	$(".menu ul li ul li:has(ul li)").find("a:first").append('<span>&nbsp;&gt;</span>');
});

$(document).ready(function() {  
	$('.box').hover(function(){
		$(".cover", this).stop().animate({top:'100px'},{queue:false,duration:300});
	}, function() {
		$(".cover", this).stop().animate({top:'200px'},{queue:false,duration:300});
	});
});

$(document).ready(function() {  
	$('.magic_cap .box').hover(function(){
		$(this).find('img').animate({"opacity": "0.55"}, {queue:false,duration:300});
	}, function() {
		$(this).find('img').animate({"opacity": "1"}, {queue:false,duration:300});

	});
});

$(function() {
    // These first three lines of code compensate for Javascript being turned on and off. 
    // It simply changes the submit input field from a type of "submit" to a type of "button".

    var paraTag = $('input#submit').parent('p');
    $(paraTag).children('input').remove();
    $(paraTag).append('<input type="button" name="submit" id="submit" value="Send" />');

    $('#form_main input#submit').click(function() {
        $('#form_main').append('<img src="css/images/ajax-loader.gif" class="loaderIcon" alt="Loading..." />');

        var name = $('input#name').val();
        var email = $('input#email').val();
        var comments = $('textarea#comments').val();

        $.ajax({
            type: 'post',
            url: 'sendEmail.php',
            data: 'name=' + name + '&email=' + email + '&comments=' + comments,

            success: function(results) {
                $('#form_main img.loaderIcon').fadeOut(5000);
                $('ul#form_response').html(results);
            }
        }); // end ajax
    });
});

$(document).ready(function() {  
	$('.search_click').toggle(function(){
		$('.search_field').slideDown(200);
		$('#search_input').focus();
	}, function() {
		$('.search_field').slideUp(200);

	});
});

function mainmenu(){
	$(".menu ul li ul").css({display: "none"}); // Opera Fix
	$(".menu ul li:has(ul)").bind("mouseenter",function(){
		$('.enclosing').css({visibility: "hidden"});
		$(this).find('.enclosing').css({visibility: "visible"});
	});
	$(".menu ul li").bind("mouseenter",function(){
		$(this).find('ul:first').removeClass('enclosing');
		$(this).find('ul:first').stop(true, true).delay(50).slideDown(200);
	}).bind("mouseleave",function(){
		$(this).find('ul:first').addClass('enclosing');
		$(this).find('ul:first').stop(true, true).delay(800).fadeOut(0);
	});
}

$(document).ready(function(){
	mainmenu();
});

	$(window).load(function() {
	$('#slider').nivoSlider();
});
$(function() {
	var zIndexNumber = 1000;
	$('div').each(function() {
		$(this).css('zIndex', zIndexNumber);
		zIndexNumber -= 10;
	});
});