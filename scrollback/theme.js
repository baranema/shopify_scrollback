window.theme = window.theme || {};

window.theme = window.theme || {};

theme.Sections = function Sections() {}



$("#PageContainer").css("top", document.getElementById("shopify-section-header").offsetHeight + "px");

$(window).bind("load", function() {

  // Is in viewport  
  $(".template-index #PageContainer").css("top", document.getElementById("shopify-section-header").offsetHeight + "px");
  
  function isOnScreen(elem) {
    if( elem.length == 0 ) {
    return;
    }

    var $window = jQuery(window)
    var viewport_top = $window.scrollTop()
    var viewport_height = $window.height()
    var viewport_bottom = viewport_top + viewport_height - 100
    var $elem = jQuery(elem)
    var top = $elem.offset().top
    var height = $elem.height()
    var bottom = top + height
    return (top >= viewport_top && top < viewport_bottom) ||
    (bottom > viewport_top && bottom <= viewport_bottom) ||
    (height > viewport_height && top <= viewport_top && bottom >= viewport_bottom)
  }

  $( ".index-section" ).each(function( index ) {

    if( isOnScreen( jQuery( this) ) ) { 
      $(this).css("opacity", "1");
    }

    if ($("body").hasClass("template-index")) {
      if ($(this).attr("id").indexOf('shopify-section-feature-row') != -1) {
        if ($(this).next().attr("id").indexOf('shopify-section-feature-row') != -1) {
          $(this).css("padding-bottom", "0");
        }
      } 
    }
  });
// product on hover
