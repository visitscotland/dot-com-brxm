(window.webpackJsonp_name_=window.webpackJsonp_name_||[]).push([[50],{"4sxf":function(t,i,e){"use strict";e.d(i,"a",function(){return l});var s=e("y5Xb"),n=e("z9Gb"),o=e("I4kH"),h=e("C66O"),a=e("M3LF"),c={passive:!0,capture:!1},l=s.a.extend({name:"BCollapse",mixins:[n.a,o.a],model:{prop:"visible",event:"input"},props:{id:{type:String,required:!0},isNav:{type:Boolean,default:!1},accordion:{type:String,default:null},visible:{type:Boolean,default:!1},tag:{type:String,default:"div"}},data:function(){return{show:this.visible,transitioning:!1}},computed:{classObject:function(){return{"navbar-collapse":this.isNav,collapse:!this.transitioning,show:this.show&&!this.transitioning}}},watch:{visible:function(t){t!==this.show&&(this.show=t)},show:function(t,i){t!==i&&this.emitState()}},created:function(){this.show=this.visible},mounted:function(){var t=this;this.show=this.visible,this.listenOnRoot("bv::toggle::collapse",this.handleToggleEvt),this.listenOnRoot("bv::collapse::accordion",this.handleAccordionEvt),this.isNav&&(this.setWindowEvents(!0),this.handleResize()),this.$nextTick(function(){t.emitState()}),this.listenOnRoot("bv::request::collapse::state",function(i){i===t.id&&t.$nextTick(t.emitSync)})},updated:function(){this.emitSync()},deactivated:function(){this.isNav&&this.setWindowEvents(!1)},activated:function(){this.isNav&&this.setWindowEvents(!0),this.emitSync()},beforeDestroy:function(){this.show=!1,this.isNav&&h.g&&this.setWindowEvents(!1)},methods:{setWindowEvents:function(t){var i=t?a.e:a.d;i(window,"resize",this.handleResize,c),i(window,"orientationchange",this.handleResize,c)},toggle:function(){this.show=!this.show},onEnter:function(t){t.style.height=0,Object(a.m)(t),t.style.height=t.scrollHeight+"px",this.transitioning=!0,this.$emit("show")},onAfterEnter:function(t){t.style.height=null,this.transitioning=!1,this.$emit("shown")},onLeave:function(t){t.style.height="auto",t.style.display="block",t.style.height=Object(a.g)(t).height+"px",Object(a.m)(t),this.transitioning=!0,t.style.height=0,this.$emit("hide")},onAfterLeave:function(t){t.style.height=null,this.transitioning=!1,this.$emit("hidden")},emitState:function(){this.$emit("input",this.show),this.$root.$emit("bv::collapse::state",this.id,this.show),this.accordion&&this.show&&this.$root.$emit("bv::collapse::accordion",this.id,this.accordion)},emitSync:function(){this.$root.$emit("bv::collapse::sync::state",this.id,this.show)},checkDisplayBlock:function(){var t=Object(a.i)(this.$el,"show");Object(a.o)(this.$el,"show");var i="block"===Object(a.h)(this.$el).display;return t&&Object(a.a)(this.$el,"show"),i},clickHandler:function(t){var i=t.target;this.isNav&&i&&"block"===Object(a.h)(this.$el).display&&(Object(a.l)(i,".nav-link,.dropdown-item")||Object(a.b)(".nav-link,.dropdown-item",i))&&(this.checkDisplayBlock()||(this.show=!1))},handleToggleEvt:function(t){t===this.id&&this.toggle()},handleAccordionEvt:function(t,i){this.accordion&&i===this.accordion&&(t===this.id?this.show||this.toggle():this.show&&this.toggle())},handleResize:function(){this.show="block"===Object(a.h)(this.$el).display}},render:function(t){var i=t(this.tag,{class:this.classObject,directives:[{name:"show",value:this.show}],attrs:{id:this.id||null},on:{click:this.clickHandler}},[this.normalizeSlot("default")]);return t("transition",{props:{enterClass:"",enterActiveClass:"collapsing",enterToClass:"",leaveClass:"",leaveActiveClass:"collapsing",leaveToClass:""},on:{enter:this.onEnter,afterEnter:this.onAfterEnter,leave:this.onLeave,afterLeave:this.onAfterLeave}},[i])}})},z9Gb:function(t,i,e){"use strict";e("ma9I");i.a={methods:{listenOnRoot:function(t,i){var e=this;return this.$root.$on(t,i),this.$on("hook:beforeDestroy",function(){e.$root.$off(t,i)}),this},listenOnRootOnce:function(t,i){var e=this;return this.$root.$once(t,i),this.$on("hook:beforeDestroy",function(){e.$root.$off(t,i)}),this},emitOnRoot:function(t){for(var i,e=arguments.length,s=new Array(e>1?e-1:0),n=1;n<e;n++)s[n-1]=arguments[n];return(i=this.$root).$emit.apply(i,[t].concat(s)),this}}}}}]);