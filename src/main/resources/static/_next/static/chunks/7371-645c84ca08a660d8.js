"use strict";(self.webpackChunk_N_E=self.webpackChunk_N_E||[]).push([[7371],{40933:function(e,t,n){n.d(t,{Z:function(){return r}});/**
 * @license lucide-react v0.368.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */let r=(0,n(33480).Z)("Clock",[["circle",{cx:"12",cy:"12",r:"10",key:"1mglay"}],["polyline",{points:"12 6 12 12 16 14",key:"68esgv"}]])},63550:function(e,t,n){n.d(t,{Z:function(){return r}});/**
 * @license lucide-react v0.368.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */let r=(0,n(33480).Z)("Ellipsis",[["circle",{cx:"12",cy:"12",r:"1",key:"41hilf"}],["circle",{cx:"19",cy:"12",r:"1",key:"1wjl8i"}],["circle",{cx:"5",cy:"12",r:"1",key:"1pcz8c"}]])},47390:function(e,t,n){n.d(t,{Z:function(){return r}});/**
 * @license lucide-react v0.368.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */let r=(0,n(33480).Z)("MessageSquare",[["path",{d:"M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z",key:"1lielz"}]])},42365:function(e,t,n){n.d(t,{Z:function(){return r}});/**
 * @license lucide-react v0.368.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */let r=(0,n(33480).Z)("Paperclip",[["path",{d:"m21.44 11.05-9.19 9.19a6 6 0 0 1-8.49-8.49l8.57-8.57A4 4 0 1 1 18 8.84l-8.59 8.57a2 2 0 0 1-2.83-2.83l8.49-8.48",key:"1u3ebp"}]])},92513:function(e,t,n){n.d(t,{Z:function(){return r}});/**
 * @license lucide-react v0.368.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */let r=(0,n(33480).Z)("Plus",[["path",{d:"M5 12h14",key:"1ays0h"}],["path",{d:"M12 5v14",key:"s699le"}]])},54817:function(e,t,n){n.d(t,{Z:function(){return r}});/**
 * @license lucide-react v0.368.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */let r=(0,n(33480).Z)("Search",[["circle",{cx:"11",cy:"11",r:"8",key:"4ej97u"}],["path",{d:"m21 21-4.3-4.3",key:"1qie3q"}]])},74697:function(e,t,n){n.d(t,{Z:function(){return r}});/**
 * @license lucide-react v0.368.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */let r=(0,n(33480).Z)("X",[["path",{d:"M18 6 6 18",key:"1bl5f8"}],["path",{d:"m6 6 12 12",key:"d8bk6v"}]])},45348:function(e,t,n){n.d(t,{M:function(){return x}});var r=n(57437),o=n(2265),l=n(5050),i=n(30458),c=n(9033),u=n(67797),s=n(59306),f=n(29791);function a(e,t){if("function"==typeof e)return e(t);null!=e&&(e.current=t)}class h extends o.Component{getSnapshotBeforeUpdate(e){let t=this.props.childRef.current;if(t&&e.isPresent&&!this.props.isPresent){let e=t.offsetParent,n=(0,s.R)(e)&&e.offsetWidth||0,r=(0,s.R)(e)&&e.offsetHeight||0,o=this.props.sizeRef.current;o.height=t.offsetHeight||0,o.width=t.offsetWidth||0,o.top=t.offsetTop,o.left=t.offsetLeft,o.right=n-o.width-o.left,o.bottom=r-o.height-o.top}return null}componentDidUpdate(){}render(){return this.props.children}}function p(e){var t,n;let{children:l,isPresent:i,anchorX:c,anchorY:u,root:s}=e,p=(0,o.useId)(),d=(0,o.useRef)(null),m=(0,o.useRef)({width:0,height:0,top:0,left:0,right:0,bottom:0}),{nonce:y}=(0,o.useContext)(f._),g=function(...e){return o.useCallback(function(...e){return t=>{let n=!1,r=e.map(e=>{let r=a(e,t);return n||"function"!=typeof r||(n=!0),r});if(n)return()=>{for(let t=0;t<r.length;t++){let n=r[t];"function"==typeof n?n():a(e[t],null)}}}}(...e),e)}(d,null!==(n=null===(t=l.props)||void 0===t?void 0:t.ref)&&void 0!==n?n:null==l?void 0:l.ref);return(0,o.useInsertionEffect)(()=>{let{width:e,height:t,top:n,left:r,right:o,bottom:l}=m.current;if(i||!d.current||!e||!t)return;d.current.dataset.motionPopId=p;let f=document.createElement("style");y&&(f.nonce=y);let a=null!=s?s:document.head;return a.appendChild(f),f.sheet&&f.sheet.insertRule('\n          [data-motion-pop-id="'.concat(p,'"] {\n            position: absolute !important;\n            width: ').concat(e,"px !important;\n            height: ").concat(t,"px !important;\n            ").concat("left"===c?"left: ".concat(r):"right: ".concat(o),"px !important;\n            ").concat("bottom"===u?"bottom: ".concat(l):"top: ".concat(n),"px !important;\n          }\n        ")),()=>{a.contains(f)&&a.removeChild(f)}},[i]),(0,r.jsx)(h,{isPresent:i,childRef:d,sizeRef:m,children:o.cloneElement(l,{ref:g})})}let d=e=>{let{children:t,initial:n,isPresent:l,onExitComplete:c,custom:s,presenceAffectsLayout:f,mode:a,anchorX:h,anchorY:d,root:y}=e,g=(0,i.h)(m),k=(0,o.useId)(),x=!0,v=(0,o.useMemo)(()=>(x=!1,{id:k,initial:n,isPresent:l,custom:s,onExitComplete:e=>{for(let t of(g.set(e,!0),g.values()))if(!t)return;c&&c()},register:e=>(g.set(e,!1),()=>g.delete(e))}),[l,g,c]);return f&&x&&(v={...v}),(0,o.useMemo)(()=>{g.forEach((e,t)=>g.set(t,!1))},[l]),o.useEffect(()=>{l||g.size||!c||c()},[l]),"popLayout"===a&&(t=(0,r.jsx)(p,{isPresent:l,anchorX:h,anchorY:d,root:y,children:t})),(0,r.jsx)(u.O.Provider,{value:v,children:t})};function m(){return new Map}var y=n(73241);let g=e=>e.key||"";function k(e){let t=[];return o.Children.forEach(e,e=>{(0,o.isValidElement)(e)&&t.push(e)}),t}let x=e=>{let{children:t,custom:n,initial:u=!0,onExitComplete:s,presenceAffectsLayout:f=!0,mode:a="sync",propagate:h=!1,anchorX:p="left",anchorY:m="top",root:x}=e,[v,Z]=(0,y.oO)(h),E=(0,o.useMemo)(()=>k(t),[t]),C=h&&!v?[]:E.map(g),R=(0,o.useRef)(!0),b=(0,o.useRef)(E),w=(0,i.h)(()=>new Map),M=(0,o.useRef)(new Set),[P,j]=(0,o.useState)(E),[z,S]=(0,o.useState)(E);(0,c.L)(()=>{R.current=!1,b.current=E;for(let e=0;e<z.length;e++){let t=g(z[e]);C.includes(t)?(w.delete(t),M.current.delete(t)):!0!==w.get(t)&&w.set(t,!1)}},[z,C.length,C.join("-")]);let _=[];if(E!==P){let e=[...E];for(let t=0;t<z.length;t++){let n=z[t],r=g(n);C.includes(r)||(e.splice(t,0,n),_.push(n))}return"wait"===a&&_.length&&(e=_),S(k(e)),j(E),null}let{forceRender:I}=(0,o.useContext)(l.p);return(0,r.jsx)(r.Fragment,{children:z.map(e=>{let t=g(e),o=(!h||!!v)&&(E===z||C.includes(t));return(0,r.jsx)(d,{isPresent:o,initial:(!R.current||!!u)&&void 0,custom:n,presenceAffectsLayout:f,mode:a,root:x,onExitComplete:o?void 0:()=>{if(M.current.has(t)||(M.current.add(t),!w.has(t)))return;w.set(t,!0);let e=!0;w.forEach(t=>{t||(e=!1)}),e&&(null==I||I(),S(b.current),h&&(null==Z||Z()),s&&s())},anchorX:p,anchorY:m,children:e},t)})})}}}]);