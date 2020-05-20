function jQuery(selector) {
  var el = document.querySelectorAll(selector);
  
  el.html = function(value) {
    if (arguments.length == 0) {
      return this[0].innerHTML;
    }
    for (var e of el) { // 반복할 수 있는 iterable 객체 el
      e.innerHTML = value;
    }
  };
  
  return el;
};

var $ = jQuery;