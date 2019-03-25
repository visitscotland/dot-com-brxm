!function(t,e){"object"==typeof exports&&"object"==typeof module?module.exports=e():"function"==typeof define&&define.amd?define([],e):"object"==typeof exports?exports.VsInput=e():t.VsInput=e()}(window,function(){return function(t){var e={};function n(r){if(e[r])return e[r].exports;var o=e[r]={i:r,l:!1,exports:{}};return t[r].call(o.exports,o,o.exports,n),o.l=!0,o.exports}return n.m=t,n.c=e,n.d=function(t,e,r){n.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:r})},n.r=function(t){"undefined"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},n.t=function(t,e){if(1&e&&(t=n(t)),8&e)return t;if(4&e&&"object"==typeof t&&t&&t.__esModule)return t;var r=Object.create(null);if(n.r(r),Object.defineProperty(r,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var o in t)n.d(r,o,function(e){return t[e]}.bind(null,o));return r},n.n=function(t){var e=t&&t.__esModule?function(){return t.default}:function(){return t};return n.d(e,"a",e),e},n.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},n.p="",n(n.s="EU8T")}({"8YtK":function(t,e){},EU8T:function(t,e,n){"use strict";n.r(e);var r=n("TP7S"),o=n.n(r),i={name:"VsInput",status:"ready",release:"1.0.0",props:{type:{type:String,default:"text",validator:function(t){return t.match(/(text|number|email|checkbox|submit)/)}},value:{type:String,default:null},inputClass:{type:String},wrapper:{type:String,default:"div",validator:function(t){return t.match(/(div|section)/)}},wrapperClass:{type:String},placeholder:{type:String,default:null},label:{type:String,default:null},id:{type:String,default:null},disabled:{type:Boolean,default:!1},state:{type:String,default:null,validator:function(t){return t.match(/(hover|active|focus)/)}}},methods:{onInput:function(t){this.$emit("change",t)},onFocus:function(t){this.$emit("focus",t)},isCheckbox:function(){return"checkbox"===this.type}},computed:{normalisedInputClass:function(){var t=o()(this.inputClass)?this.isCheckbox()?"form-check-input":"form-control":this.inputClass;return this.state?t+" "+this.state:t},normalisedWrapperClass:function(){return o()(this.wrapperClass)?this.isCheckbox()?"form-check":"form-group":this.wrapperClass},showPreLabel:function(){return this.label&&"checkbox"!==this.type},showPostLabel:function(){return this.label&&"checkbox"===this.type}}},s=(n("h3jU"),n("KHd+")),u=n("8YtK"),a=n.n(u),l=Object(s.a)(i,function(){var t=this,e=t.$createElement,n=t._self._c||e;return n(t.wrapper,{tag:"component",class:t.normalisedWrapperClass},[t.showPreLabel?n("label",{attrs:{for:t.id}},[t._v(t._s(t.label))]):t._e(),t._v(" "),n("input",{class:t.normalisedInputClass,attrs:{id:t.id,disabled:t.disabled,type:t.type,placeholder:t.placeholder},domProps:{value:t.value},on:{input:function(e){t.onInput(e.target.value)},focus:function(e){t.onFocus(e.target.value)}}}),t._v(" "),t.showPostLabel?n("label",{attrs:{for:t.id}},[t._v(t._s(t.label))]):t._e()])},[],!1,null,"9c0cd8ac",null);"function"==typeof a.a&&a()(l),l.options.__file="VsInput.vue";e.default=l.exports},"KHd+":function(t,e,n){"use strict";function r(t,e,n,r,o,i,s,u){var a,l="function"==typeof t?t.options:t;if(e&&(l.render=e,l.staticRenderFns=n,l._compiled=!0),r&&(l.functional=!0),i&&(l._scopeId="data-v-"+i),s?(a=function(t){(t=t||this.$vnode&&this.$vnode.ssrContext||this.parent&&this.parent.$vnode&&this.parent.$vnode.ssrContext)||"undefined"==typeof __VUE_SSR_CONTEXT__||(t=__VUE_SSR_CONTEXT__),o&&o.call(this,t),t&&t._registeredComponents&&t._registeredComponents.add(s)},l._ssrRegister=a):o&&(a=u?function(){o.call(this,this.$root.$options.shadowRoot)}:o),a)if(l.functional){l._injectStyles=a;var c=l.render;l.render=function(t,e){return a.call(e),c(t,e)}}else{var p=l.beforeCreate;l.beforeCreate=p?[].concat(p,a):[a]}return{exports:t,options:l}}n.d(e,"a",function(){return r})},TP7S:function(t,e){t.exports=function(t){return void 0===t}},ZxIP:function(t,e,n){},h3jU:function(t,e,n){"use strict";var r=n("ZxIP");n.n(r).a}})});