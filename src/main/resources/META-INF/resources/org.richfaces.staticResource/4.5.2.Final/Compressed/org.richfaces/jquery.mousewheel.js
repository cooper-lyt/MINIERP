/* Copyright (c) 2013 Brandon Aaron (http://brandon.aaron.sh)
 * Licensed under the MIT License (LICENSE.txt).
 *
 * Version: 3.1.12
 *
 * Requires: jQuery 1.2.2+
 */
(function(A){if(typeof define==="function"&&define.amd){define(["jquery"],A)
}else{if(typeof exports==="object"){module.exports=A
}else{A(jQuery)
}}}(function(C){var D=["wheel","mousewheel","DOMMouseScroll","MozMousePixelScroll"],J=("onwheel" in document||document.documentMode>=9)?["wheel"]:["mousewheel","DomMouseScroll","MozMousePixelScroll"],H=Array.prototype.slice,I,B;
if(C.event.fixHooks){for(var E=D.length;
E;
){C.event.fixHooks[D[--E]]=C.event.mouseHooks
}}var F=C.event.special.mousewheel={version:"3.1.12",setup:function(){if(this.addEventListener){for(var L=J.length;
L;
){this.addEventListener(J[--L],K,false)
}}else{this.onmousewheel=K
}C.data(this,"mousewheel-line-height",F.getLineHeight(this));
C.data(this,"mousewheel-page-height",F.getPageHeight(this))
},teardown:function(){if(this.removeEventListener){for(var L=J.length;
L;
){this.removeEventListener(J[--L],K,false)
}}else{this.onmousewheel=null
}C.removeData(this,"mousewheel-line-height");
C.removeData(this,"mousewheel-page-height")
},getLineHeight:function(M){var L=C(M),N=L["offsetParent" in C.fn?"offsetParent":"parent"]();
if(!N.length){N=C("body")
}return parseInt(N.css("fontSize"),10)||parseInt(L.css("fontSize"),10)||16
},getPageHeight:function(L){return C(L).height()
},settings:{adjustOldDeltas:true,normalizeOffset:true}};
C.fn.extend({mousewheel:function(L){return L?this.bind("mousewheel",L):this.trigger("mousewheel")
},unmousewheel:function(L){return this.unbind("mousewheel",L)
}});
function K(L){var O=L||window.event,U=H.call(arguments,1),W=0,Q=0,P=0,T=0,S=0,R=0;
L=C.event.fix(O);
L.type="mousewheel";
if("detail" in O){P=O.detail*-1
}if("wheelDelta" in O){P=O.wheelDelta
}if("wheelDeltaY" in O){P=O.wheelDeltaY
}if("wheelDeltaX" in O){Q=O.wheelDeltaX*-1
}if("axis" in O&&O.axis===O.HORIZONTAL_AXIS){Q=P*-1;
P=0
}W=P===0?Q:P;
if("deltaY" in O){P=O.deltaY*-1;
W=P
}if("deltaX" in O){Q=O.deltaX;
if(P===0){W=Q*-1
}}if(P===0&&Q===0){return 
}if(O.deltaMode===1){var V=C.data(this,"mousewheel-line-height");
W*=V;
P*=V;
Q*=V
}else{if(O.deltaMode===2){var N=C.data(this,"mousewheel-page-height");
W*=N;
P*=N;
Q*=N
}}T=Math.max(Math.abs(P),Math.abs(Q));
if(!B||T<B){B=T;
if(A(O,T)){B/=40
}}if(A(O,T)){W/=40;
Q/=40;
P/=40
}W=Math[W>=1?"floor":"ceil"](W/B);
Q=Math[Q>=1?"floor":"ceil"](Q/B);
P=Math[P>=1?"floor":"ceil"](P/B);
if(F.settings.normalizeOffset&&this.getBoundingClientRect){var M=this.getBoundingClientRect();
S=L.clientX-M.left;
R=L.clientY-M.top
}L.deltaX=Q;
L.deltaY=P;
L.deltaFactor=B;
L.offsetX=S;
L.offsetY=R;
L.deltaMode=0;
U.unshift(L,W,Q,P);
if(I){clearTimeout(I)
}I=setTimeout(G,200);
return(C.event.dispatch||C.event.handle).apply(this,U)
}function G(){B=null
}function A(M,L){return F.settings.adjustOldDeltas&&M.type==="mousewheel"&&L%120===0
}}));