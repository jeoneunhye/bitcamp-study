function jQuery(selector) {
  if (selector.startsWith('<')) {
    var tagName = selector.substring(1, selector.length - 1); // <button> 총 8개 문자열 인덱스 0 ~ 7
    var el = [document.createElement(tagName)]; // 한 개 짜리가 만들어 저장됨
  } else { // let : 블럭 내에서만 사용 가능 - 사용 범위를 좁힘 var : 함수 안에서 사용되는 로컬 변수
    var el = document.querySelectorAll(selector);
  }
  
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
  
  el.click = function(listener) {
    if (arguments.length < 1) {
      return;
    }
    
    this.on('click', listener);
  }
  
  el.append = function(childs) { // 제이쿼리에서 만든 모든 태그는 배열에 담김
    if (arguments.length < 1) {
      return;
    }
    
    for (var e of el) { // 부모들을 꺼내서
      for (var child of childs) {
      e.appendChild(child); // 자식들을 붙임
      }
    }
  }
  
  return el;
};

var $ = jQuery;