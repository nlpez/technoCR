$(document).ready(function(){
	var altura = $('.navegador').offset().top;
	
	$(window).on('scroll', function(){
		if ( $(window).scrollTop() > altura ){
			$('.navegador').addClass('menu-fixed');
		} else {
			$('.navegador').removeClass('menu-fixed');
		}
	});

});