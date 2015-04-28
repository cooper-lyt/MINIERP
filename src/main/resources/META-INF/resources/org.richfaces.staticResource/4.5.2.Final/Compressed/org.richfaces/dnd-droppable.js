(function(C,B){B.ui=B.ui||{};
var E={rejectClass:"rf-ind-rejt",acceptClass:"rf-ind-acpt",draggingClass:"rf-ind-drag"};
var A={};
B.ui.Droppable=function(G,F){this.options={};
C.extend(this.options,A,F||{});
D.constructor.call(this,G);
this.namespace=this.namespace||"."+B.Event.createNamespace(this.name,this.id);
this.id=G;
this.parentId=this.options.parentId;
this.attachToDom(this.parentId);
this.dropElement=C(document.getElementById(this.parentId));
this.dropElement.droppable({addClasses:false});
this.dropElement.data("init",true);
B.Event.bind(this.dropElement,"drop"+this.namespace,this.drop,this);
B.Event.bind(this.dropElement,"dropover"+this.namespace,this.dropover,this);
B.Event.bind(this.dropElement,"dropout"+this.namespace,this.dropout,this)
};
B.BaseNonVisualComponent.extend(B.ui.Droppable);
var D=B.ui.Droppable.$super;
C.extend(B.ui.Droppable.prototype,(function(){return{drop:function(H){var F=H.rf.data;
if(this.accept(F.draggable)){this.__callAjax(H,F)
}var G=this.__getIndicatorObject(F.helper);
if(G){F.helper.removeClass(G.getAcceptClass());
F.helper.removeClass(G.getRejectClass())
}else{F.helper.removeClass(E.acceptClass);
F.helper.removeClass(E.rejectClass)
}},dropover:function(I){var G=I.rf.data;
var F=G.draggable;
var H=this.__getIndicatorObject(G.helper);
this.dropElement.addClass("rf-drp-hvr");
if(H){if(this.accept(F)){G.helper.removeClass(H.getRejectClass());
G.helper.addClass(H.getAcceptClass());
this.dropElement.addClass("rf-drp-hlight")
}else{G.helper.removeClass(H.getAcceptClass());
G.helper.addClass(H.getRejectClass());
this.dropElement.removeClass("rf-drp-hlight")
}}else{if(this.accept(F)){G.helper.removeClass(E.rejectClass);
G.helper.addClass(E.acceptClass);
this.dropElement.addClass("rf-drp-hlight")
}else{G.helper.removeClass(E.acceptClass);
G.helper.addClass(E.rejectClass);
this.dropElement.removeClass("rf-drp-hlight")
}}},dropout:function(I){var G=I.rf.data;
var F=G.draggable;
var H=this.__getIndicatorObject(G.helper);
this.dropElement.removeClass("rf-drp-hvr rf-drp-hlight");
if(H){G.helper.removeClass(H.getAcceptClass());
G.helper.removeClass(H.getRejectClass())
}else{G.helper.removeClass(E.acceptClass);
G.helper.removeClass(E.rejectClass)
}},accept:function(F){var H=false;
var G=F.data("type");
if(G&&this.options.acceptedTypes){C.each(this.options.acceptedTypes,function(){if(this=="@none"){return false
}if(this==G||this=="@all"){H=true;
return false
}})
}return H
},__getIndicatorObject:function(H){var G=H.attr("id");
if(G){var F=G.match(/(.*)Clone$/)[1];
return B.component(F)
}},__callAjax:function(H,G){if(G.draggable){var F=G.draggable.data("id");
var I=this.options.ajaxFunction;
if(I&&typeof I=="function"){I.call(this,H,F)
}}},destroy:function(){this.detach(this.parentId);
B.Event.unbind(this.dropElement,this.namespace);
D.destroy.call(this)
}}
})())
})(RichFaces.jQuery,window.RichFaces);