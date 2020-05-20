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
  
  el.css = function(name, value) {
    if (arguments.length == 0) {
      return;
    }
    
    if (arguments.length == 1) {
      return this[0].style[name]; // jquery는 여러 개 중 0번째 값만 리턴한다.
    }
    
    for (var e of el) {
      e.style[name] = value;
    }
  };
    
  el.on = function(event, listener) {
    if (arguments.length < 2) {
      return;
    }

    for (var e of el) {
      e.addEventListener(event, listener);
    }
  };
  
  return el;
};

var $ = jQuery;