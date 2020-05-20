function jQuery(selector) {
  if (selector.startsWith('<')) {
    var tagName = selector.substring(1, selector.length - 1);
    var el = [document.createElement(tagName)];
  } else {
    var el = document.querySelectorAll(selector);
  }
  
  el.html = function(value) {
    if (arguments.length == 0) {
      return this[0].innerHTML;
    }
    
    for (var e of el) {
      e.innerHTML = value;
    }
    
    return this;
  };
  
  el.css = function(name, value) {
    if (arguments.length == 0) {
      return;
    }
    
    if (arguments.length == 1) {
      return this[0].style[name];
    }
    
    for (var e of el) {
      e.style[name] = value;
    }
    
    return this;
  };
    
  el.on = function(event, listener) {
    if (arguments.length < 2) {
      return;
    }

    for (var e of el) {
      e.addEventListener(event, listener);
    }
  };
  
  el.click = function(listener) {
    if (arguments.length < 1) {
      return;
    }
    
    this.on('click', listener);
    return this;
  }
  
  el.appendTo = function(parents) {
    if (arguments.length < 1) {
      return;
    }
    
    for (var e of parents) {
      for (var child of el) {
      e.appendChild(child); // 오리지날 자바스크립트 함수
      }
    }
    
    return this;
  }
  
  return el;
};

var $ = jQuery;