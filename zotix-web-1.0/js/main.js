
$(document).ready(function(){

/* ==========================================================================
 Sticky menu
========================================================================== */
$(".navbar").sticky({topSpacing: 0});

/* ==========================================================================
 Scroll spy and scroll filter
========================================================================== */

    $('#main-menu').onePageNav({
        currentClass: "active",
        changeHash: false,
        scrollThreshold: 0.5,
        scrollSpeed: 750,
        scrollOffset: 40,
        filter: "",
        easing: "swing" 
     });


/* ==========================================================================
 Scroll to 
========================================================================== */
 $('.home-brand').click(function(){
        $('html, body').animate({scrollTop:0}, 'slow');
        return false;
    });

 $(".btn-about").click(function() {
    $('html, body').animate({
        scrollTop: $("#about-section").offset().top
    }, 750);
    return false;
});

  $(".btn-portfolio").click(function() {
    $('html, body').animate({
        scrollTop: $("#portfolio-section").offset().top
    }, 750);
    return false;
});


$(".meta .comments a").click(function() {
    $('html, body').animate({
        scrollTop: $("#comments").offset().top
    }, 750);
    return false;
});

$(".btn-reply").click(function() {
    $('html, body').animate({
        scrollTop: $("#reply-form").offset().top
    }, 750);
    $('input').first().focus();
    return false;
});



/* ==========================================================================
 Portfolio
========================================================================== */

   /* For Bootstrap current state on portfolio sorting */

    $('.grid-controls li a').click(function (e) {
        $('ul.grid-controls li.active').removeClass('active')
        $(this).parent('li').addClass('active')
    })

/* portfolio sorting */

$(window).load(function() {
    var $container = $('.grid-wrapper');
    $container.isotope({
        filter: '*',
        animationOptions: {
            duration: 750,
            easing: 'linear',
            queue: false
        }
    });
 
$('.grid-controls li a').click(function(){
        $('.grid-controls .current').removeClass('current');
        $(this).addClass('current');
 
        var selector = $(this).attr('data-filter');
        $container.isotope({
            filter: selector,
            animationOptions: {
                duration: 750,
                easing: 'linear',
                queue: false
            }
         });
         return false;
    });
});


$(window).resize(function() {
    var $container = $('.grid-wrapper');
    $container.isotope({
        filter: '*',
        animationOptions: {
            duration: 750,
            easing: 'linear',
            queue: false
        }
    });
	
});


/* Magnific Image Popup */
$('.popup-image').magnificPopup({
          type: 'image'
        });


/* Magnific Video Popup */
$('.popup-vimeo').magnificPopup({
          disableOn: 700,
          type: 'iframe',
          mainClass: 'mfp-fade',
          removalDelay: 160,
          preloader: false,
          fixedContentPos: false
        });

/* ==========================================================================
 Blog
========================================================================== */

   /* For Bootstrap current state on blog tag sorting */

    $('.blog-controls li a').click(function (e) {
        $('ul.blog-controls li.active').removeClass('active')
        $(this).parent('li').addClass('active')
    })

    /* Blog sorting */

   $(window).load(function() {
    var $container = $('.blog-wrapper');
    $container.isotope({
        filter: '*',
        animationOptions: {
            duration: 750,
            easing: 'linear',
            queue: false
        }
    });
 
$('.blog-controls li a').click(function(){
        $('.blog-controls .current').removeClass('current');
        $(this).addClass('current');
 
        var selector = $(this).attr('data-filter');
        $container.isotope({
            filter: selector,
            animationOptions: {
                duration: 750,
                easing: 'linear',
                queue: false
            }
         });
         return false;
    });
});

$(window).resize(function() {
    var $container = $('.blog-wrapper');
    $container.isotope({
        filter: '*',
        animationOptions: {
            duration: 750,
            easing: 'linear',
            queue: false
        }
    });
});

/* ==========================================================================
 Shrink nav on scroll
========================================================================== */
$(function(){
  $('.navbar-default').data('size','big');
});

$(window).scroll(function(){
  if($(document).scrollTop() > 0)
{
    if($('.navbar-default').data('size') == 'big')
    {
        $('.navbar-default').data('size','small');
        $('.navbar-default').animate({
            padding:'0px'
        },300);
    }
}
else
  {
    if($('.navbar-default').data('size') == 'small')
      {
        $('.navbar-default').data('size','big');
        $('.navbar-default').animate({
            padding:'15px 0px'
        },300);
      }  
  }
});

/* ==========================================================================
 Animate
========================================================================== */

$('.about-section').waypoint(function() {
        $('figure').addClass( 'flipInX animated' );
    },
    {
        offset: '50%',
        triggerOnce: true   
    });

$('.services-section').waypoint(function() {
        $('.service-icons').addClass( 'flipInX animated' );
    },
    {
        offset: '50%',
        triggerOnce: true   
    });

$('.portfolio-section').waypoint(function() {
        $('.grid-wrapper').addClass( 'bounceIn animated' );
    },
    {
        offset: '50%',
        triggerOnce: true   
    });

$('.blog-teaser-section').waypoint(function() {
        $('#blog-teaser').addClass( 'bounceIn animated' );
    },
    {
        offset: '50%',
        triggerOnce: true   
    });





/*==========================================================================
Contact form 
========================================================================== */  

      $('#contact-form').validate({
        rules: {
            name: {
                minlength: 2,
                required: true
            },
            email: {
                required: true,
                email: true
            },
            message: {
                minlength: 2,
                required: true
            }
        },
        highlight: function (element) {
            $(element).closest('.control-group').removeClass('success').addClass('error');
        },
        success: function (element) {
            element.text('OK!').addClass('valid')
                .closest('.control-group').removeClass('error').addClass('success');
        }



    });


/*==========================================================================
Blog repy form 
========================================================================== */ 
    $('#reply-form').validate({
        rules: {
            name: {
                minlength: 2,
                required: true
            },
            email: {
                required: true,
                email: true
            },
            message: {
                minlength: 2,
                required: true
            }
        },
        highlight: function (element) {
            $(element).closest('.control-group').removeClass('success').addClass('error');
        },
        success: function (element) {
            element.text('OK!').addClass('valid')
                .closest('.control-group').removeClass('error').addClass('success');
        }



    });

});