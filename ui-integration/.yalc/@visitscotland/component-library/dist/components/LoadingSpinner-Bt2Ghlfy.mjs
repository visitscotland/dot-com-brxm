import { defineComponent as ne, computed as I, h as ut, Teleport as Ko, ref as te, watch as Ue, onMounted as xa, nextTick as Mn, toRef as U, createBlock as X, openBlock as P, resolveDynamicComponent as Ve, normalizeClass as Q, withCtx as ee, renderSlot as W, createTextVNode as Te, toDisplayString as Ee, toValue as Pt, inject as Gt, mergeModels as ct, useModel as Lt, withDirectives as Wr, createElementBlock as j, unref as K, Fragment as rt, renderList as ur, vModelSelect as ew, mergeProps as xe, createCommentVNode as $e, createVNode as Rt, useAttrs as fs, getCurrentInstance as ds, useSlots as fr, onActivated as tw, normalizeProps as Wt, createElementVNode as je, vModelRadio as nw, provide as Sa, normalizeStyle as dn, vModelCheckbox as rw, vShow as cs, guardReactiveProps as An, watchEffect as aw, onBeforeUnmount as ow, withModifiers as ga, withKeys as Rr, onUnmounted as iw, shallowRef as Ac, render as Cc, shallowReadonly as Tr, getCurrentScope as Tc, onScopeDispose as Ec, readonly as Jl, Transition as lw, customRef as sw, resolveComponent as qt, createStaticVNode as uw } from "vue";
import { defineStore as fw, mapState as dw } from "pinia";
const rn = (a, r) => {
  const n = a.__vccOpts || a;
  for (const [o, l] of r)
    n[o] = l;
  return n;
};
var cw = {}, pw = Object.defineProperty, vw = (a, r, n) => r in a ? pw(a, r, { enumerable: !0, configurable: !0, writable: !0, value: n }) : a[r] = n, Bn = (a, r, n) => (vw(a, typeof r != "symbol" ? r + "" : r, n), n);
const kc = (a) => I(() => {
  const r = Pt(a);
  return r ? `justify-content-${r}` : "";
}), Jo = (a, r) => I(() => {
  const n = Pt(a), o = Pt(r);
  return n === !0 ? "true" : typeof n == "string" ? n : o === !1 ? "true" : n === !1 ? "false" : void 0;
});
class ba {
  constructor(r, n = {}) {
    if (Bn(this, "cancelable", !0), Bn(this, "componentId", null), Bn(this, "_defaultPrevented", !1), Bn(this, "eventType", ""), Bn(this, "nativeEvent", null), Bn(this, "_preventDefault"), Bn(this, "relatedTarget", null), Bn(this, "target", null), !r)
      throw new TypeError(
        `Failed to construct '${this.constructor.name}'. 1 argument required, ${arguments.length} given.`
      );
    Object.assign(this, ba.Defaults, n, { eventType: r }), this._preventDefault = function() {
      this.cancelable && (this.defaultPrevented = !0);
    };
  }
  // Readable by everyone,
  // But only overwritten by inherrited constructors
  get defaultPrevented() {
    return this._defaultPrevented;
  }
  set defaultPrevented(r) {
    this._defaultPrevented = r;
  }
  // I think this is right
  // We want to be able to have it callable to everyone,
  // But only overwritten by inherrited constructors
  get preventDefault() {
    return this._preventDefault;
  }
  // This may not be correct, because it doesn't get correct type inferences in children
  // Ex overwrite this.preventDefault = () => true is valid. Could be a TS issue
  set preventDefault(r) {
    this._preventDefault = r;
  }
  static get Defaults() {
    return {
      cancelable: !0,
      componentId: null,
      eventType: "",
      nativeEvent: null,
      relatedTarget: null,
      target: null
    };
  }
}
class ps extends ba {
  constructor(r, n = {}) {
    super(r, n), Bn(this, "trigger", null), Object.assign(this, ba.Defaults, n, { eventType: r });
  }
  static get Defaults() {
    return {
      ...super.Defaults,
      trigger: null
    };
  }
}
const gw = (a, r = {}) => {
  const n = (l = []) => {
    const { activeElement: s } = document;
    return s && !l.some((f) => f === s) ? s : null;
  }, o = (l) => l === n();
  try {
    a.focus(r);
  } catch (l) {
    console.error(l);
  }
  return o(a);
}, Un = (a) => ((a == null ? void 0 : a()) ?? []).length === 0, hw = (a) => {
  if (a.getAttribute("display") === "none")
    return !1;
  const r = a.getBoundingClientRect();
  return !!(r && r.height > 0 && r.width > 0);
}, mw = (a) => {
  const r = window.getComputedStyle(a), n = r.transitionDelay.split(",")[0] || "", o = r.transitionDuration.split(",")[0] || "", l = Number(n.slice(0, -1)) * 1e3, s = Number(o.slice(0, -1)) * 1e3;
  return l + s;
}, Oc = typeof window < "u" && typeof document < "u" && typeof navigator < "u", $c = ["top", "right", "bottom", "left"], cd = ["start", "end"], pd = /* @__PURE__ */ $c.reduce((a, r) => a.concat(r, r + "-" + cd[0], r + "-" + cd[1]), []), nn = Math.min, ft = Math.max, Do = Math.round, Ao = Math.floor, Wn = (a) => ({
  x: a,
  y: a
}), yw = {
  left: "right",
  right: "left",
  bottom: "top",
  top: "bottom"
}, bw = {
  start: "end",
  end: "start"
};
function Xl(a, r, n) {
  return ft(a, nn(r, n));
}
function kn(a, r) {
  return typeof a == "function" ? a(r) : a;
}
function jt(a) {
  return a.split("-")[0];
}
function tn(a) {
  return a.split("-")[1];
}
function Rc(a) {
  return a === "x" ? "y" : "x";
}
function vs(a) {
  return a === "y" ? "height" : "width";
}
function qr(a) {
  return ["top", "bottom"].includes(jt(a)) ? "y" : "x";
}
function gs(a) {
  return Rc(qr(a));
}
function Fc(a, r, n) {
  n === void 0 && (n = !1);
  const o = tn(a), l = gs(a), s = vs(l);
  let f = l === "x" ? o === (n ? "end" : "start") ? "right" : "left" : o === "start" ? "bottom" : "top";
  return r.reference[s] > r.floating[s] && (f = Mo(f)), [f, Mo(f)];
}
function _w(a) {
  const r = Mo(a);
  return [zo(a), r, zo(r)];
}
function zo(a) {
  return a.replace(/start|end/g, (r) => bw[r]);
}
function ww(a, r, n) {
  const o = ["left", "right"], l = ["right", "left"], s = ["top", "bottom"], f = ["bottom", "top"];
  switch (a) {
    case "top":
    case "bottom":
      return n ? r ? l : o : r ? o : l;
    case "left":
    case "right":
      return r ? s : f;
    default:
      return [];
  }
}
function xw(a, r, n, o) {
  const l = tn(a);
  let s = ww(jt(a), n === "start", o);
  return l && (s = s.map((f) => f + "-" + l), r && (s = s.concat(s.map(zo)))), s;
}
function Mo(a) {
  return a.replace(/left|right|bottom|top/g, (r) => yw[r]);
}
function Sw(a) {
  return {
    top: 0,
    right: 0,
    bottom: 0,
    left: 0,
    ...a
  };
}
function hs(a) {
  return typeof a != "number" ? Sw(a) : {
    top: a,
    right: a,
    bottom: a,
    left: a
  };
}
function Dr(a) {
  return {
    ...a,
    top: a.y,
    left: a.x,
    right: a.x + a.width,
    bottom: a.y + a.height
  };
}
function vd(a, r, n) {
  let {
    reference: o,
    floating: l
  } = a;
  const s = qr(r), f = gs(r), d = vs(f), p = jt(r), g = s === "y", m = o.x + o.width / 2 - l.width / 2, w = o.y + o.height / 2 - l.height / 2, S = o[d] / 2 - l[d] / 2;
  let b;
  switch (p) {
    case "top":
      b = {
        x: m,
        y: o.y - l.height
      };
      break;
    case "bottom":
      b = {
        x: m,
        y: o.y + o.height
      };
      break;
    case "right":
      b = {
        x: o.x + o.width,
        y: w
      };
      break;
    case "left":
      b = {
        x: o.x - l.width,
        y: w
      };
      break;
    default:
      b = {
        x: o.x,
        y: o.y
      };
  }
  switch (tn(r)) {
    case "start":
      b[f] -= S * (n && g ? -1 : 1);
      break;
    case "end":
      b[f] += S * (n && g ? -1 : 1);
      break;
  }
  return b;
}
const Bw = async (a, r, n) => {
  const {
    placement: o = "bottom",
    strategy: l = "absolute",
    middleware: s = [],
    platform: f
  } = n, d = s.filter(Boolean), p = await (f.isRTL == null ? void 0 : f.isRTL(r));
  let g = await f.getElementRects({
    reference: a,
    floating: r,
    strategy: l
  }), {
    x: m,
    y: w
  } = vd(g, o, p), S = o, b = {}, y = 0;
  for (let x = 0; x < d.length; x++) {
    const {
      name: B,
      fn: E
    } = d[x], {
      x: F,
      y: L,
      data: $,
      reset: k
    } = await E({
      x: m,
      y: w,
      initialPlacement: o,
      placement: S,
      strategy: l,
      middlewareData: b,
      rects: g,
      platform: f,
      elements: {
        reference: a,
        floating: r
      }
    });
    m = F ?? m, w = L ?? w, b = {
      ...b,
      [B]: {
        ...b[B],
        ...$
      }
    }, k && y <= 50 && (y++, typeof k == "object" && (k.placement && (S = k.placement), k.rects && (g = k.rects === !0 ? await f.getElementRects({
      reference: a,
      floating: r,
      strategy: l
    }) : k.rects), {
      x: m,
      y: w
    } = vd(g, S, p)), x = -1);
  }
  return {
    x: m,
    y: w,
    placement: S,
    strategy: l,
    middlewareData: b
  };
};
async function zr(a, r) {
  var n;
  r === void 0 && (r = {});
  const {
    x: o,
    y: l,
    platform: s,
    rects: f,
    elements: d,
    strategy: p
  } = a, {
    boundary: g = "clippingAncestors",
    rootBoundary: m = "viewport",
    elementContext: w = "floating",
    altBoundary: S = !1,
    padding: b = 0
  } = kn(r, a), y = hs(b), x = d[S ? w === "floating" ? "reference" : "floating" : w], B = Dr(await s.getClippingRect({
    element: (n = await (s.isElement == null ? void 0 : s.isElement(x))) == null || n ? x : x.contextElement || await (s.getDocumentElement == null ? void 0 : s.getDocumentElement(d.floating)),
    boundary: g,
    rootBoundary: m,
    strategy: p
  })), E = w === "floating" ? {
    ...f.floating,
    x: o,
    y: l
  } : f.reference, F = await (s.getOffsetParent == null ? void 0 : s.getOffsetParent(d.floating)), L = await (s.isElement == null ? void 0 : s.isElement(F)) ? await (s.getScale == null ? void 0 : s.getScale(F)) || {
    x: 1,
    y: 1
  } : {
    x: 1,
    y: 1
  }, $ = Dr(s.convertOffsetParentRelativeRectToViewportRelativeRect ? await s.convertOffsetParentRelativeRectToViewportRelativeRect({
    elements: d,
    rect: E,
    offsetParent: F,
    strategy: p
  }) : E);
  return {
    top: (B.top - $.top + y.top) / L.y,
    bottom: ($.bottom - B.bottom + y.bottom) / L.y,
    left: (B.left - $.left + y.left) / L.x,
    right: ($.right - B.right + y.right) / L.x
  };
}
const Aw = (a) => ({
  name: "arrow",
  options: a,
  async fn(r) {
    const {
      x: n,
      y: o,
      placement: l,
      rects: s,
      platform: f,
      elements: d,
      middlewareData: p
    } = r, {
      element: g,
      padding: m = 0
    } = kn(a, r) || {};
    if (g == null)
      return {};
    const w = hs(m), S = {
      x: n,
      y: o
    }, b = gs(l), y = vs(b), x = await f.getDimensions(g), B = b === "y", E = B ? "top" : "left", F = B ? "bottom" : "right", L = B ? "clientHeight" : "clientWidth", $ = s.reference[y] + s.reference[b] - S[b] - s.floating[y], k = S[b] - s.reference[b], z = await (f.getOffsetParent == null ? void 0 : f.getOffsetParent(g));
    let H = z ? z[L] : 0;
    (!H || !await (f.isElement == null ? void 0 : f.isElement(z))) && (H = d.floating[L] || s.floating[y]);
    const ie = $ / 2 - k / 2, se = H / 2 - x[y] / 2 - 1, Ae = nn(w[E], se), Ie = nn(w[F], se), Ce = Ae, Re = H - x[y] - Ie, q = H / 2 - x[y] / 2 + ie, ge = Xl(Ce, q, Re), de = !p.arrow && tn(l) != null && q !== ge && s.reference[y] / 2 - (q < Ce ? Ae : Ie) - x[y] / 2 < 0, Se = de ? q < Ce ? q - Ce : q - Re : 0;
    return {
      [b]: S[b] + Se,
      data: {
        [b]: ge,
        centerOffset: q - ge - Se,
        ...de && {
          alignmentOffset: Se
        }
      },
      reset: de
    };
  }
});
function Cw(a, r, n) {
  return (a ? [...n.filter((o) => tn(o) === a), ...n.filter((o) => tn(o) !== a)] : n.filter((o) => jt(o) === o)).filter((o) => a ? tn(o) === a || (r ? zo(o) !== o : !1) : !0);
}
const Tw = function(a) {
  return a === void 0 && (a = {}), {
    name: "autoPlacement",
    options: a,
    async fn(r) {
      var n, o, l;
      const {
        rects: s,
        middlewareData: f,
        placement: d,
        platform: p,
        elements: g
      } = r, {
        crossAxis: m = !1,
        alignment: w,
        allowedPlacements: S = pd,
        autoAlignment: b = !0,
        ...y
      } = kn(a, r), x = w !== void 0 || S === pd ? Cw(w || null, b, S) : S, B = await zr(r, y), E = ((n = f.autoPlacement) == null ? void 0 : n.index) || 0, F = x[E];
      if (F == null)
        return {};
      const L = Fc(F, s, await (p.isRTL == null ? void 0 : p.isRTL(g.floating)));
      if (d !== F)
        return {
          reset: {
            placement: x[0]
          }
        };
      const $ = [B[jt(F)], B[L[0]], B[L[1]]], k = [...((o = f.autoPlacement) == null ? void 0 : o.overflows) || [], {
        placement: F,
        overflows: $
      }], z = x[E + 1];
      if (z)
        return {
          data: {
            index: E + 1,
            overflows: k
          },
          reset: {
            placement: z
          }
        };
      const H = k.map((se) => {
        const Ae = tn(se.placement);
        return [se.placement, Ae && m ? (
          // Check along the mainAxis and main crossAxis side.
          se.overflows.slice(0, 2).reduce((Ie, Ce) => Ie + Ce, 0)
        ) : (
          // Check only the mainAxis.
          se.overflows[0]
        ), se.overflows];
      }).sort((se, Ae) => se[1] - Ae[1]), ie = ((l = H.filter((se) => se[2].slice(
        0,
        // Aligned placements should not check their opposite crossAxis
        // side.
        tn(se[0]) ? 2 : 3
      ).every((Ae) => Ae <= 0))[0]) == null ? void 0 : l[0]) || H[0][0];
      return ie !== d ? {
        data: {
          index: E + 1,
          overflows: k
        },
        reset: {
          placement: ie
        }
      } : {};
    }
  };
}, Ew = function(a) {
  return a === void 0 && (a = {}), {
    name: "flip",
    options: a,
    async fn(r) {
      var n, o;
      const {
        placement: l,
        middlewareData: s,
        rects: f,
        initialPlacement: d,
        platform: p,
        elements: g
      } = r, {
        mainAxis: m = !0,
        crossAxis: w = !0,
        fallbackPlacements: S,
        fallbackStrategy: b = "bestFit",
        fallbackAxisSideDirection: y = "none",
        flipAlignment: x = !0,
        ...B
      } = kn(a, r);
      if ((n = s.arrow) != null && n.alignmentOffset)
        return {};
      const E = jt(l), F = jt(d) === d, L = await (p.isRTL == null ? void 0 : p.isRTL(g.floating)), $ = S || (F || !x ? [Mo(d)] : _w(d));
      !S && y !== "none" && $.push(...xw(d, x, y, L));
      const k = [d, ...$], z = await zr(r, B), H = [];
      let ie = ((o = s.flip) == null ? void 0 : o.overflows) || [];
      if (m && H.push(z[E]), w) {
        const Ce = Fc(l, f, L);
        H.push(z[Ce[0]], z[Ce[1]]);
      }
      if (ie = [...ie, {
        placement: l,
        overflows: H
      }], !H.every((Ce) => Ce <= 0)) {
        var se, Ae;
        const Ce = (((se = s.flip) == null ? void 0 : se.index) || 0) + 1, Re = k[Ce];
        if (Re)
          return {
            data: {
              index: Ce,
              overflows: ie
            },
            reset: {
              placement: Re
            }
          };
        let q = (Ae = ie.filter((ge) => ge.overflows[0] <= 0).sort((ge, de) => ge.overflows[1] - de.overflows[1])[0]) == null ? void 0 : Ae.placement;
        if (!q)
          switch (b) {
            case "bestFit": {
              var Ie;
              const ge = (Ie = ie.map((de) => [de.placement, de.overflows.filter((Se) => Se > 0).reduce((Se, pe) => Se + pe, 0)]).sort((de, Se) => de[1] - Se[1])[0]) == null ? void 0 : Ie[0];
              ge && (q = ge);
              break;
            }
            case "initialPlacement":
              q = d;
              break;
          }
        if (l !== q)
          return {
            reset: {
              placement: q
            }
          };
      }
      return {};
    }
  };
};
function gd(a, r) {
  return {
    top: a.top - r.height,
    right: a.right - r.width,
    bottom: a.bottom - r.height,
    left: a.left - r.width
  };
}
function hd(a) {
  return $c.some((r) => a[r] >= 0);
}
const kw = function(a) {
  return a === void 0 && (a = {}), {
    name: "hide",
    options: a,
    async fn(r) {
      const {
        rects: n
      } = r, {
        strategy: o = "referenceHidden",
        ...l
      } = kn(a, r);
      switch (o) {
        case "referenceHidden": {
          const s = await zr(r, {
            ...l,
            elementContext: "reference"
          }), f = gd(s, n.reference);
          return {
            data: {
              referenceHiddenOffsets: f,
              referenceHidden: hd(f)
            }
          };
        }
        case "escaped": {
          const s = await zr(r, {
            ...l,
            altBoundary: !0
          }), f = gd(s, n.floating);
          return {
            data: {
              escapedOffsets: f,
              escaped: hd(f)
            }
          };
        }
        default:
          return {};
      }
    }
  };
};
function Pc(a) {
  const r = nn(...a.map((s) => s.left)), n = nn(...a.map((s) => s.top)), o = ft(...a.map((s) => s.right)), l = ft(...a.map((s) => s.bottom));
  return {
    x: r,
    y: n,
    width: o - r,
    height: l - n
  };
}
function Ow(a) {
  const r = a.slice().sort((l, s) => l.y - s.y), n = [];
  let o = null;
  for (let l = 0; l < r.length; l++) {
    const s = r[l];
    !o || s.y - o.y > o.height / 2 ? n.push([s]) : n[n.length - 1].push(s), o = s;
  }
  return n.map((l) => Dr(Pc(l)));
}
const $w = function(a) {
  return a === void 0 && (a = {}), {
    name: "inline",
    options: a,
    async fn(r) {
      const {
        placement: n,
        elements: o,
        rects: l,
        platform: s,
        strategy: f
      } = r, {
        padding: d = 2,
        x: p,
        y: g
      } = kn(a, r), m = Array.from(await (s.getClientRects == null ? void 0 : s.getClientRects(o.reference)) || []), w = Ow(m), S = Dr(Pc(m)), b = hs(d);
      function y() {
        if (w.length === 2 && w[0].left > w[1].right && p != null && g != null)
          return w.find((B) => p > B.left - b.left && p < B.right + b.right && g > B.top - b.top && g < B.bottom + b.bottom) || S;
        if (w.length >= 2) {
          if (qr(n) === "y") {
            const Ae = w[0], Ie = w[w.length - 1], Ce = jt(n) === "top", Re = Ae.top, q = Ie.bottom, ge = Ce ? Ae.left : Ie.left, de = Ce ? Ae.right : Ie.right, Se = de - ge, pe = q - Re;
            return {
              top: Re,
              bottom: q,
              left: ge,
              right: de,
              width: Se,
              height: pe,
              x: ge,
              y: Re
            };
          }
          const B = jt(n) === "left", E = ft(...w.map((Ae) => Ae.right)), F = nn(...w.map((Ae) => Ae.left)), L = w.filter((Ae) => B ? Ae.left === F : Ae.right === E), $ = L[0].top, k = L[L.length - 1].bottom, z = F, H = E, ie = H - z, se = k - $;
          return {
            top: $,
            bottom: k,
            left: z,
            right: H,
            width: ie,
            height: se,
            x: z,
            y: $
          };
        }
        return S;
      }
      const x = await s.getElementRects({
        reference: {
          getBoundingClientRect: y
        },
        floating: o.floating,
        strategy: f
      });
      return l.reference.x !== x.reference.x || l.reference.y !== x.reference.y || l.reference.width !== x.reference.width || l.reference.height !== x.reference.height ? {
        reset: {
          rects: x
        }
      } : {};
    }
  };
};
async function Rw(a, r) {
  const {
    placement: n,
    platform: o,
    elements: l
  } = a, s = await (o.isRTL == null ? void 0 : o.isRTL(l.floating)), f = jt(n), d = tn(n), p = qr(n) === "y", g = ["left", "top"].includes(f) ? -1 : 1, m = s && p ? -1 : 1, w = kn(r, a);
  let {
    mainAxis: S,
    crossAxis: b,
    alignmentAxis: y
  } = typeof w == "number" ? {
    mainAxis: w,
    crossAxis: 0,
    alignmentAxis: null
  } : {
    mainAxis: 0,
    crossAxis: 0,
    alignmentAxis: null,
    ...w
  };
  return d && typeof y == "number" && (b = d === "end" ? y * -1 : y), p ? {
    x: b * m,
    y: S * g
  } : {
    x: S * g,
    y: b * m
  };
}
const Lc = function(a) {
  return a === void 0 && (a = 0), {
    name: "offset",
    options: a,
    async fn(r) {
      var n, o;
      const {
        x: l,
        y: s,
        placement: f,
        middlewareData: d
      } = r, p = await Rw(r, a);
      return f === ((n = d.offset) == null ? void 0 : n.placement) && (o = d.arrow) != null && o.alignmentOffset ? {} : {
        x: l + p.x,
        y: s + p.y,
        data: {
          ...p,
          placement: f
        }
      };
    }
  };
}, Fw = function(a) {
  return a === void 0 && (a = {}), {
    name: "shift",
    options: a,
    async fn(r) {
      const {
        x: n,
        y: o,
        placement: l
      } = r, {
        mainAxis: s = !0,
        crossAxis: f = !1,
        limiter: d = {
          fn: (B) => {
            let {
              x: E,
              y: F
            } = B;
            return {
              x: E,
              y: F
            };
          }
        },
        ...p
      } = kn(a, r), g = {
        x: n,
        y: o
      }, m = await zr(r, p), w = qr(jt(l)), S = Rc(w);
      let b = g[S], y = g[w];
      if (s) {
        const B = S === "y" ? "top" : "left", E = S === "y" ? "bottom" : "right", F = b + m[B], L = b - m[E];
        b = Xl(F, b, L);
      }
      if (f) {
        const B = w === "y" ? "top" : "left", E = w === "y" ? "bottom" : "right", F = y + m[B], L = y - m[E];
        y = Xl(F, y, L);
      }
      const x = d.fn({
        ...r,
        [S]: b,
        [w]: y
      });
      return {
        ...x,
        data: {
          x: x.x - n,
          y: x.y - o
        }
      };
    }
  };
}, Pw = function(a) {
  return a === void 0 && (a = {}), {
    name: "size",
    options: a,
    async fn(r) {
      const {
        placement: n,
        rects: o,
        platform: l,
        elements: s
      } = r, {
        apply: f = () => {
        },
        ...d
      } = kn(a, r), p = await zr(r, d), g = jt(n), m = tn(n), w = qr(n) === "y", {
        width: S,
        height: b
      } = o.floating;
      let y, x;
      g === "top" || g === "bottom" ? (y = g, x = m === (await (l.isRTL == null ? void 0 : l.isRTL(s.floating)) ? "start" : "end") ? "left" : "right") : (x = g, y = m === "end" ? "top" : "bottom");
      const B = b - p[y], E = S - p[x], F = !r.middlewareData.shift;
      let L = B, $ = E;
      if (w) {
        const z = S - p.left - p.right;
        $ = m || F ? nn(E, z) : z;
      } else {
        const z = b - p.top - p.bottom;
        L = m || F ? nn(B, z) : z;
      }
      if (F && !m) {
        const z = ft(p.left, 0), H = ft(p.right, 0), ie = ft(p.top, 0), se = ft(p.bottom, 0);
        w ? $ = S - 2 * (z !== 0 || H !== 0 ? z + H : ft(p.left, p.right)) : L = b - 2 * (ie !== 0 || se !== 0 ? ie + se : ft(p.top, p.bottom));
      }
      await f({
        ...r,
        availableWidth: $,
        availableHeight: L
      });
      const k = await l.getDimensions(s.floating);
      return S !== k.width || b !== k.height ? {
        reset: {
          rects: !0
        }
      } : {};
    }
  };
};
function Tn(a) {
  return ms(a) ? (a.nodeName || "").toLowerCase() : "#document";
}
function Ft(a) {
  var r;
  return (a == null || (r = a.ownerDocument) == null ? void 0 : r.defaultView) || window;
}
function On(a) {
  var r;
  return (r = (ms(a) ? a.ownerDocument : a.document) || window.document) == null ? void 0 : r.documentElement;
}
function ms(a) {
  return a instanceof Node || a instanceof Ft(a).Node;
}
function En(a) {
  return a instanceof Element || a instanceof Ft(a).Element;
}
function cn(a) {
  return a instanceof HTMLElement || a instanceof Ft(a).HTMLElement;
}
function md(a) {
  return typeof ShadowRoot > "u" ? !1 : a instanceof ShadowRoot || a instanceof Ft(a).ShadowRoot;
}
function Ba(a) {
  const {
    overflow: r,
    overflowX: n,
    overflowY: o,
    display: l
  } = Kt(a);
  return /auto|scroll|overlay|hidden|clip/.test(r + o + n) && !["inline", "contents"].includes(l);
}
function Lw(a) {
  return ["table", "td", "th"].includes(Tn(a));
}
function ys(a) {
  const r = bs(), n = Kt(a);
  return n.transform !== "none" || n.perspective !== "none" || (n.containerType ? n.containerType !== "normal" : !1) || !r && (n.backdropFilter ? n.backdropFilter !== "none" : !1) || !r && (n.filter ? n.filter !== "none" : !1) || ["transform", "perspective", "filter"].some((o) => (n.willChange || "").includes(o)) || ["paint", "layout", "strict", "content"].some((o) => (n.contain || "").includes(o));
}
function Iw(a) {
  let r = Mr(a);
  for (; cn(r) && !Xo(r); ) {
    if (ys(r))
      return r;
    r = Mr(r);
  }
  return null;
}
function bs() {
  return typeof CSS > "u" || !CSS.supports ? !1 : CSS.supports("-webkit-backdrop-filter", "none");
}
function Xo(a) {
  return ["html", "body", "#document"].includes(Tn(a));
}
function Kt(a) {
  return Ft(a).getComputedStyle(a);
}
function Yo(a) {
  return En(a) ? {
    scrollLeft: a.scrollLeft,
    scrollTop: a.scrollTop
  } : {
    scrollLeft: a.pageXOffset,
    scrollTop: a.pageYOffset
  };
}
function Mr(a) {
  if (Tn(a) === "html")
    return a;
  const r = (
    // Step into the shadow DOM of the parent of a slotted node.
    a.assignedSlot || // DOM Element detected.
    a.parentNode || // ShadowRoot detected.
    md(a) && a.host || // Fallback.
    On(a)
  );
  return md(r) ? r.host : r;
}
function Ic(a) {
  const r = Mr(a);
  return Xo(r) ? a.ownerDocument ? a.ownerDocument.body : a.body : cn(r) && Ba(r) ? r : Ic(r);
}
function _a(a, r, n) {
  var o;
  r === void 0 && (r = []), n === void 0 && (n = !0);
  const l = Ic(a), s = l === ((o = a.ownerDocument) == null ? void 0 : o.body), f = Ft(l);
  return s ? r.concat(f, f.visualViewport || [], Ba(l) ? l : [], f.frameElement && n ? _a(f.frameElement) : []) : r.concat(l, _a(l, [], n));
}
function Nc(a) {
  const r = Kt(a);
  let n = parseFloat(r.width) || 0, o = parseFloat(r.height) || 0;
  const l = cn(a), s = l ? a.offsetWidth : n, f = l ? a.offsetHeight : o, d = Do(n) !== s || Do(o) !== f;
  return d && (n = s, o = f), {
    width: n,
    height: o,
    $: d
  };
}
function _s(a) {
  return En(a) ? a : a.contextElement;
}
function Ir(a) {
  const r = _s(a);
  if (!cn(r))
    return Wn(1);
  const n = r.getBoundingClientRect(), {
    width: o,
    height: l,
    $: s
  } = Nc(r);
  let f = (s ? Do(n.width) : n.width) / o, d = (s ? Do(n.height) : n.height) / l;
  return (!f || !Number.isFinite(f)) && (f = 1), (!d || !Number.isFinite(d)) && (d = 1), {
    x: f,
    y: d
  };
}
const Nw = /* @__PURE__ */ Wn(0);
function Vc(a) {
  const r = Ft(a);
  return !bs() || !r.visualViewport ? Nw : {
    x: r.visualViewport.offsetLeft,
    y: r.visualViewport.offsetTop
  };
}
function Vw(a, r, n) {
  return r === void 0 && (r = !1), !n || r && n !== Ft(a) ? !1 : r;
}
function lr(a, r, n, o) {
  r === void 0 && (r = !1), n === void 0 && (n = !1);
  const l = a.getBoundingClientRect(), s = _s(a);
  let f = Wn(1);
  r && (o ? En(o) && (f = Ir(o)) : f = Ir(a));
  const d = Vw(s, n, o) ? Vc(s) : Wn(0);
  let p = (l.left + d.x) / f.x, g = (l.top + d.y) / f.y, m = l.width / f.x, w = l.height / f.y;
  if (s) {
    const S = Ft(s), b = o && En(o) ? Ft(o) : o;
    let y = S, x = y.frameElement;
    for (; x && o && b !== y; ) {
      const B = Ir(x), E = x.getBoundingClientRect(), F = Kt(x), L = E.left + (x.clientLeft + parseFloat(F.paddingLeft)) * B.x, $ = E.top + (x.clientTop + parseFloat(F.paddingTop)) * B.y;
      p *= B.x, g *= B.y, m *= B.x, w *= B.y, p += L, g += $, y = Ft(x), x = y.frameElement;
    }
  }
  return Dr({
    width: m,
    height: w,
    x: p,
    y: g
  });
}
const Dw = [":popover-open", ":modal"];
function Dc(a) {
  return Dw.some((r) => {
    try {
      return a.matches(r);
    } catch {
      return !1;
    }
  });
}
function zw(a) {
  let {
    elements: r,
    rect: n,
    offsetParent: o,
    strategy: l
  } = a;
  const s = l === "fixed", f = On(o), d = r ? Dc(r.floating) : !1;
  if (o === f || d && s)
    return n;
  let p = {
    scrollLeft: 0,
    scrollTop: 0
  }, g = Wn(1);
  const m = Wn(0), w = cn(o);
  if ((w || !w && !s) && ((Tn(o) !== "body" || Ba(f)) && (p = Yo(o)), cn(o))) {
    const S = lr(o);
    g = Ir(o), m.x = S.x + o.clientLeft, m.y = S.y + o.clientTop;
  }
  return {
    width: n.width * g.x,
    height: n.height * g.y,
    x: n.x * g.x - p.scrollLeft * g.x + m.x,
    y: n.y * g.y - p.scrollTop * g.y + m.y
  };
}
function Mw(a) {
  return Array.from(a.getClientRects());
}
function zc(a) {
  return lr(On(a)).left + Yo(a).scrollLeft;
}
function Hw(a) {
  const r = On(a), n = Yo(a), o = a.ownerDocument.body, l = ft(r.scrollWidth, r.clientWidth, o.scrollWidth, o.clientWidth), s = ft(r.scrollHeight, r.clientHeight, o.scrollHeight, o.clientHeight);
  let f = -n.scrollLeft + zc(a);
  const d = -n.scrollTop;
  return Kt(o).direction === "rtl" && (f += ft(r.clientWidth, o.clientWidth) - l), {
    width: l,
    height: s,
    x: f,
    y: d
  };
}
function Uw(a, r) {
  const n = Ft(a), o = On(a), l = n.visualViewport;
  let s = o.clientWidth, f = o.clientHeight, d = 0, p = 0;
  if (l) {
    s = l.width, f = l.height;
    const g = bs();
    (!g || g && r === "fixed") && (d = l.offsetLeft, p = l.offsetTop);
  }
  return {
    width: s,
    height: f,
    x: d,
    y: p
  };
}
function Ww(a, r) {
  const n = lr(a, !0, r === "fixed"), o = n.top + a.clientTop, l = n.left + a.clientLeft, s = cn(a) ? Ir(a) : Wn(1), f = a.clientWidth * s.x, d = a.clientHeight * s.y, p = l * s.x, g = o * s.y;
  return {
    width: f,
    height: d,
    x: p,
    y: g
  };
}
function yd(a, r, n) {
  let o;
  if (r === "viewport")
    o = Uw(a, n);
  else if (r === "document")
    o = Hw(On(a));
  else if (En(r))
    o = Ww(r, n);
  else {
    const l = Vc(a);
    o = {
      ...r,
      x: r.x - l.x,
      y: r.y - l.y
    };
  }
  return Dr(o);
}
function Mc(a, r) {
  const n = Mr(a);
  return n === r || !En(n) || Xo(n) ? !1 : Kt(n).position === "fixed" || Mc(n, r);
}
function qw(a, r) {
  const n = r.get(a);
  if (n)
    return n;
  let o = _a(a, [], !1).filter((d) => En(d) && Tn(d) !== "body"), l = null;
  const s = Kt(a).position === "fixed";
  let f = s ? Mr(a) : a;
  for (; En(f) && !Xo(f); ) {
    const d = Kt(f), p = ys(f);
    !p && d.position === "fixed" && (l = null), (s ? !p && !l : !p && d.position === "static" && l && ["absolute", "fixed"].includes(l.position) || Ba(f) && !p && Mc(a, f)) ? o = o.filter((g) => g !== f) : l = d, f = Mr(f);
  }
  return r.set(a, o), o;
}
function Gw(a) {
  let {
    element: r,
    boundary: n,
    rootBoundary: o,
    strategy: l
  } = a;
  const s = [...n === "clippingAncestors" ? qw(r, this._c) : [].concat(n), o], f = s[0], d = s.reduce((p, g) => {
    const m = yd(r, g, l);
    return p.top = ft(m.top, p.top), p.right = nn(m.right, p.right), p.bottom = nn(m.bottom, p.bottom), p.left = ft(m.left, p.left), p;
  }, yd(r, f, l));
  return {
    width: d.right - d.left,
    height: d.bottom - d.top,
    x: d.left,
    y: d.top
  };
}
function jw(a) {
  const {
    width: r,
    height: n
  } = Nc(a);
  return {
    width: r,
    height: n
  };
}
function Kw(a, r, n) {
  const o = cn(r), l = On(r), s = n === "fixed", f = lr(a, !0, s, r);
  let d = {
    scrollLeft: 0,
    scrollTop: 0
  };
  const p = Wn(0);
  if (o || !o && !s)
    if ((Tn(r) !== "body" || Ba(l)) && (d = Yo(r)), o) {
      const w = lr(r, !0, s, r);
      p.x = w.x + r.clientLeft, p.y = w.y + r.clientTop;
    } else
      l && (p.x = zc(l));
  const g = f.left + d.scrollLeft - p.x, m = f.top + d.scrollTop - p.y;
  return {
    x: g,
    y: m,
    width: f.width,
    height: f.height
  };
}
function bd(a, r) {
  return !cn(a) || Kt(a).position === "fixed" ? null : r ? r(a) : a.offsetParent;
}
function Hc(a, r) {
  const n = Ft(a);
  if (!cn(a) || Dc(a))
    return n;
  let o = bd(a, r);
  for (; o && Lw(o) && Kt(o).position === "static"; )
    o = bd(o, r);
  return o && (Tn(o) === "html" || Tn(o) === "body" && Kt(o).position === "static" && !ys(o)) ? n : o || Iw(a) || n;
}
const Jw = async function(a) {
  const r = this.getOffsetParent || Hc, n = this.getDimensions;
  return {
    reference: Kw(a.reference, await r(a.floating), a.strategy),
    floating: {
      x: 0,
      y: 0,
      ...await n(a.floating)
    }
  };
};
function Xw(a) {
  return Kt(a).direction === "rtl";
}
const Yw = {
  convertOffsetParentRelativeRectToViewportRelativeRect: zw,
  getDocumentElement: On,
  getClippingRect: Gw,
  getOffsetParent: Hc,
  getElementRects: Jw,
  getClientRects: Mw,
  getDimensions: jw,
  getScale: Ir,
  isElement: En,
  isRTL: Xw
};
function Zw(a, r) {
  let n = null, o;
  const l = On(a);
  function s() {
    var d;
    clearTimeout(o), (d = n) == null || d.disconnect(), n = null;
  }
  function f(d, p) {
    d === void 0 && (d = !1), p === void 0 && (p = 1), s();
    const {
      left: g,
      top: m,
      width: w,
      height: S
    } = a.getBoundingClientRect();
    if (d || r(), !w || !S)
      return;
    const b = Ao(m), y = Ao(l.clientWidth - (g + w)), x = Ao(l.clientHeight - (m + S)), B = Ao(g), E = {
      rootMargin: -b + "px " + -y + "px " + -x + "px " + -B + "px",
      threshold: ft(0, nn(1, p)) || 1
    };
    let F = !0;
    function L($) {
      const k = $[0].intersectionRatio;
      if (k !== p) {
        if (!F)
          return f();
        k ? f(!1, k) : o = setTimeout(() => {
          f(!1, 1e-7);
        }, 100);
      }
      F = !1;
    }
    try {
      n = new IntersectionObserver(L, {
        ...E,
        // Handle <iframe>s
        root: l.ownerDocument
      });
    } catch {
      n = new IntersectionObserver(L, E);
    }
    n.observe(a);
  }
  return f(!0), s;
}
function Uc(a, r, n, o) {
  o === void 0 && (o = {});
  const {
    ancestorScroll: l = !0,
    ancestorResize: s = !0,
    elementResize: f = typeof ResizeObserver == "function",
    layoutShift: d = typeof IntersectionObserver == "function",
    animationFrame: p = !1
  } = o, g = _s(a), m = l || s ? [...g ? _a(g) : [], ..._a(r)] : [];
  m.forEach((E) => {
    l && E.addEventListener("scroll", n, {
      passive: !0
    }), s && E.addEventListener("resize", n);
  });
  const w = g && d ? Zw(g, n) : null;
  let S = -1, b = null;
  f && (b = new ResizeObserver((E) => {
    let [F] = E;
    F && F.target === g && b && (b.unobserve(r), cancelAnimationFrame(S), S = requestAnimationFrame(() => {
      var L;
      (L = b) == null || L.observe(r);
    })), n();
  }), g && !p && b.observe(g), b.observe(r));
  let y, x = p ? lr(a) : null;
  p && B();
  function B() {
    const E = lr(a);
    x && (E.x !== x.x || E.y !== x.y || E.width !== x.width || E.height !== x.height) && n(), x = E, y = requestAnimationFrame(B);
  }
  return n(), () => {
    var E;
    m.forEach((F) => {
      l && F.removeEventListener("scroll", n), s && F.removeEventListener("resize", n);
    }), w == null || w(), (E = b) == null || E.disconnect(), b = null, p && cancelAnimationFrame(y);
  };
}
const Qw = Tw, Wc = Fw, qc = Ew, Gc = Pw, e1 = kw, t1 = Aw, n1 = $w, r1 = (a, r, n) => {
  const o = /* @__PURE__ */ new Map(), l = {
    platform: Yw,
    ...n
  }, s = {
    ...l.platform,
    _c: o
  };
  return Bw(a, r, {
    ...l,
    platform: s
  });
};
function a1(a) {
  return a != null && typeof a == "object" && "$el" in a;
}
function Yl(a) {
  if (a1(a)) {
    const r = a.$el;
    return ms(r) && Tn(r) === "#comment" ? null : r;
  }
  return a;
}
function o1(a) {
  return {
    name: "arrow",
    options: a,
    fn(r) {
      const n = Yl(K(a.element));
      return n == null ? {} : t1({
        element: n,
        padding: a.padding
      }).fn(r);
    }
  };
}
function jc(a) {
  return typeof window > "u" ? 1 : (a.ownerDocument.defaultView || window).devicePixelRatio || 1;
}
function _d(a, r) {
  const n = jc(a);
  return Math.round(r * n) / n;
}
function Kc(a, r, n) {
  n === void 0 && (n = {});
  const o = n.whileElementsMounted, l = I(() => {
    var H;
    return (H = K(n.open)) != null ? H : !0;
  }), s = I(() => K(n.middleware)), f = I(() => {
    var H;
    return (H = K(n.placement)) != null ? H : "bottom";
  }), d = I(() => {
    var H;
    return (H = K(n.strategy)) != null ? H : "absolute";
  }), p = I(() => {
    var H;
    return (H = K(n.transform)) != null ? H : !0;
  }), g = I(() => Yl(a.value)), m = I(() => Yl(r.value)), w = te(0), S = te(0), b = te(d.value), y = te(f.value), x = Ac({}), B = te(!1), E = I(() => {
    const H = {
      position: b.value,
      left: "0",
      top: "0"
    };
    if (!m.value)
      return H;
    const ie = _d(m.value, w.value), se = _d(m.value, S.value);
    return p.value ? {
      ...H,
      transform: "translate(" + ie + "px, " + se + "px)",
      ...jc(m.value) >= 1.5 && {
        willChange: "transform"
      }
    } : {
      position: b.value,
      left: ie + "px",
      top: se + "px"
    };
  });
  let F;
  function L() {
    g.value == null || m.value == null || r1(g.value, m.value, {
      middleware: s.value,
      placement: f.value,
      strategy: d.value
    }).then((H) => {
      w.value = H.x, S.value = H.y, b.value = H.strategy, y.value = H.placement, x.value = H.middlewareData, B.value = !0;
    });
  }
  function $() {
    typeof F == "function" && (F(), F = void 0);
  }
  function k() {
    if ($(), o === void 0) {
      L();
      return;
    }
    if (g.value != null && m.value != null) {
      F = o(g.value, m.value, L);
      return;
    }
  }
  function z() {
    l.value || (B.value = !1);
  }
  return Ue([s, f, d], L, {
    flush: "sync"
  }), Ue([g, m], k, {
    flush: "sync"
  }), Ue(l, z, {
    flush: "sync"
  }), Tc() && Ec($), {
    x: Tr(w),
    y: Tr(S),
    strategy: Tr(b),
    placement: Tr(y),
    middlewareData: Tr(x),
    isPositioned: Tr(B),
    floatingStyles: E,
    update: L
  };
}
const i1 = /* @__PURE__ */ new Set([
  "background",
  "cite",
  "href",
  "itemtype",
  "longdesc",
  "poster",
  "src",
  "xlink:href"
]), l1 = /^aria-[\w-]*$/i, s1 = /^(?:(?:https?|mailto|ftp|tel|file|sms):|[^#&/:?]*(?:[#/?]|$))/i, u1 = /^data:(?:image\/(?:bmp|gif|jpeg|jpg|png|tiff|webp)|video\/(?:mpeg|mp4|ogg|webm)|audio\/(?:mp3|oga|ogg|opus));base64,[\d+/a-z]+=*$/i, f1 = (a, r) => {
  const n = a.nodeName.toLowerCase();
  return r.includes(n) ? i1.has(n) ? !!(s1.test(a.nodeValue || "") || u1.test(a.nodeValue || "")) : !0 : r.filter((o) => o instanceof RegExp).some((o) => o.test(n));
}, Fr = {
  // Global attributes allowed on any supplied element below.
  "*": ["class", "dir", "id", "lang", "role", l1],
  a: ["target", "href", "title", "rel"],
  area: [],
  b: [],
  br: [],
  col: [],
  code: [],
  div: [],
  em: [],
  hr: [],
  h1: [],
  h2: [],
  h3: [],
  h4: [],
  h5: [],
  h6: [],
  i: [],
  img: ["src", "srcset", "alt", "title", "width", "height"],
  li: [],
  ol: [],
  p: [],
  pre: [],
  s: [],
  small: [],
  span: [],
  sub: [],
  sup: [],
  strong: [],
  u: [],
  ul: []
}, Pr = (a, r, n) => {
  if (!a.length)
    return a;
  const o = new window.DOMParser().parseFromString(a, "text/html"), l = o.body.querySelectorAll("*");
  for (const s of l) {
    const f = s.nodeName.toLowerCase();
    if (!Object.keys(r).includes(f)) {
      s.remove();
      continue;
    }
    const d = s.attributes, p = [...r["*"] || [], ...r[f] || []];
    for (const g of d)
      f1(g, p) || s.removeAttribute(g.nodeName);
  }
  return o.body.innerHTML;
};
function Zo(a) {
  return Tc() ? (Ec(a), !0) : !1;
}
function d1(a, r) {
  if (typeof Symbol < "u") {
    const n = { ...a };
    return Object.defineProperty(n, Symbol.iterator, {
      enumerable: !1,
      value() {
        let o = 0;
        return {
          next: () => ({
            value: r[o++],
            done: o > r.length
          })
        };
      }
    }), n;
  } else
    return Object.assign([...r], a);
}
function Cn(a) {
  return typeof a == "function" ? a() : K(a);
}
const Jc = typeof window < "u" && typeof document < "u";
typeof WorkerGlobalScope < "u" && globalThis instanceof WorkerGlobalScope;
const c1 = (a) => typeof a < "u", p1 = Object.prototype.toString, v1 = (a) => p1.call(a) === "[object Object]", ar = () => {
}, Zl = /* @__PURE__ */ g1();
function g1() {
  var a, r;
  return Jc && ((a = window == null ? void 0 : window.navigator) == null ? void 0 : a.userAgent) && (/iP(ad|hone|od)/.test(window.navigator.userAgent) || ((r = window == null ? void 0 : window.navigator) == null ? void 0 : r.maxTouchPoints) > 2 && /iPad|Macintosh/.test(window == null ? void 0 : window.navigator.userAgent));
}
function h1(a, r) {
  function n(...o) {
    return new Promise((l, s) => {
      Promise.resolve(a(() => r.apply(this, o), { fn: r, thisArg: this, args: o })).then(l).catch(s);
    });
  }
  return n;
}
function m1(a, r = {}) {
  let n, o, l = ar;
  const s = (f) => {
    clearTimeout(f), l(), l = ar;
  };
  return (f) => {
    const d = Cn(a), p = Cn(r.maxWait);
    return n && s(n), d <= 0 || p !== void 0 && p <= 0 ? (o && (s(o), o = null), Promise.resolve(f())) : new Promise((g, m) => {
      l = r.rejectOnCancel ? m : g, p && !o && (o = setTimeout(() => {
        n && s(n), o = null, g(f());
      }, p)), n = setTimeout(() => {
        o && s(o), o = null, g(f());
      }, d);
    });
  };
}
function y1(a) {
  const r = /* @__PURE__ */ Object.create(null);
  return (n) => r[n] || (r[n] = a(n));
}
const b1 = /-(\w)/g, _1 = y1((a) => a.replace(b1, (r, n) => n ? n.toUpperCase() : ""));
function w1(...a) {
  if (a.length !== 1)
    return U(...a);
  const r = a[0];
  return typeof r == "function" ? Jl(sw(() => ({ get: r, set: ar }))) : te(r);
}
function x1(a, r = 200, n = {}) {
  return h1(
    m1(r, n),
    a
  );
}
function Ot(a, r = {}) {
  const {
    method: n = "parseFloat",
    radix: o,
    nanToZero: l
  } = r;
  return I(() => {
    let s = Cn(a);
    return typeof s == "string" && (s = Number[n](s, o)), l && Number.isNaN(s) && (s = 0), s;
  });
}
function Xc(a = {}) {
  const {
    inheritAttrs: r = !0
  } = a, n = Ac(), o = /* @__PURE__ */ ne({
    setup(s, { slots: f }) {
      return () => {
        n.value = f.default;
      };
    }
  }), l = /* @__PURE__ */ ne({
    inheritAttrs: r,
    setup(s, { attrs: f, slots: d }) {
      return () => {
        var p;
        if (!n.value && cw.NODE_ENV !== "production")
          throw new Error("[VueUse] Failed to find the definition of reusable template");
        const g = (p = n.value) == null ? void 0 : p.call(n, { ...S1(f), $slots: d });
        return r && (g == null ? void 0 : g.length) === 1 ? g[0] : g;
      };
    }
  });
  return d1(
    { define: o, reuse: l },
    [o, l]
  );
}
function S1(a) {
  const r = {};
  for (const n in a)
    r[_1(n)] = a[n];
  return r;
}
function zn(a) {
  var r;
  const n = Cn(a);
  return (r = n == null ? void 0 : n.$el) != null ? r : n;
}
const Aa = Jc ? window : void 0;
function $t(...a) {
  let r, n, o, l;
  if (typeof a[0] == "string" || Array.isArray(a[0]) ? ([n, o, l] = a, r = Aa) : [r, n, o, l] = a, !r)
    return ar;
  Array.isArray(n) || (n = [n]), Array.isArray(o) || (o = [o]);
  const s = [], f = () => {
    s.forEach((m) => m()), s.length = 0;
  }, d = (m, w, S, b) => (m.addEventListener(w, S, b), () => m.removeEventListener(w, S, b)), p = Ue(
    () => [zn(r), Cn(l)],
    ([m, w]) => {
      if (f(), !m)
        return;
      const S = v1(w) ? { ...w } : w;
      s.push(
        ...n.flatMap((b) => o.map((y) => d(m, b, y, S)))
      );
    },
    { immediate: !0, flush: "post" }
  ), g = () => {
    p(), f();
  };
  return Zo(g), g;
}
let wd = !1;
function Yc(a, r, n = {}) {
  const { window: o = Aa, ignore: l = [], capture: s = !0, detectIframe: f = !1 } = n;
  if (!o)
    return ar;
  Zl && !wd && (wd = !0, Array.from(o.document.body.children).forEach((m) => m.addEventListener("click", ar)), o.document.documentElement.addEventListener("click", ar));
  let d = !0;
  const p = (m) => l.some((w) => {
    if (typeof w == "string")
      return Array.from(o.document.querySelectorAll(w)).some((S) => S === m.target || m.composedPath().includes(S));
    {
      const S = zn(w);
      return S && (m.target === S || m.composedPath().includes(S));
    }
  }), g = [
    $t(o, "click", (m) => {
      const w = zn(a);
      if (!(!w || w === m.target || m.composedPath().includes(w))) {
        if (m.detail === 0 && (d = !p(m)), !d) {
          d = !0;
          return;
        }
        r(m);
      }
    }, { passive: !0, capture: s }),
    $t(o, "pointerdown", (m) => {
      const w = zn(a);
      d = !p(m) && !!(w && !m.composedPath().includes(w));
    }, { passive: !0 }),
    f && $t(o, "blur", (m) => {
      setTimeout(() => {
        var w;
        const S = zn(a);
        ((w = o.document.activeElement) == null ? void 0 : w.tagName) === "IFRAME" && !(S != null && S.contains(o.document.activeElement)) && r(m);
      }, 0);
    })
  ].filter(Boolean);
  return () => g.forEach((m) => m());
}
function B1(a) {
  return typeof a == "function" ? a : typeof a == "string" ? (r) => r.key === a : Array.isArray(a) ? (r) => a.includes(r.key) : () => !0;
}
function tr(...a) {
  let r, n, o = {};
  a.length === 3 ? (r = a[0], n = a[1], o = a[2]) : a.length === 2 ? typeof a[1] == "object" ? (r = !0, n = a[0], o = a[1]) : (r = a[0], n = a[1]) : (r = !0, n = a[0]);
  const {
    target: l = Aa,
    eventName: s = "keydown",
    passive: f = !1,
    dedupe: d = !1
  } = o, p = B1(r);
  return $t(l, s, (g) => {
    g.repeat && Cn(d) || p(g) && n(g);
  }, f);
}
function A1(a) {
  return JSON.parse(JSON.stringify(a));
}
const Co = typeof globalThis < "u" ? globalThis : typeof window < "u" ? window : typeof global < "u" ? global : typeof self < "u" ? self : {}, To = "__vueuse_ssr_handlers__", C1 = /* @__PURE__ */ T1();
function T1() {
  return To in Co || (Co[To] = Co[To] || {}), Co[To];
}
function E1(a, r) {
  return C1[a] || r;
}
function fn(a, r = {}) {
  const { initialValue: n = !1, focusVisible: o = !1 } = r, l = te(!1), s = I(() => zn(a));
  $t(s, "focus", (d) => {
    var p, g;
    (!o || (g = (p = d.target).matches) != null && g.call(p, ":focus-visible")) && (l.value = !0);
  }), $t(s, "blur", () => l.value = !1);
  const f = I({
    get: () => l.value,
    set(d) {
      var p, g;
      !d && l.value ? (p = s.value) == null || p.blur() : d && !l.value && ((g = s.value) == null || g.focus());
    }
  });
  return Ue(
    s,
    () => {
      f.value = n;
    },
    { immediate: !0, flush: "post" }
  ), { focused: f };
}
function kl(a) {
  return typeof Window < "u" && a instanceof Window ? a.document.documentElement : typeof Document < "u" && a instanceof Document ? a.documentElement : a;
}
const k1 = {
  page: (a) => [a.pageX, a.pageY],
  client: (a) => [a.clientX, a.clientY],
  screen: (a) => [a.screenX, a.screenY],
  movement: (a) => a instanceof Touch ? null : [a.movementX, a.movementY]
};
function O1(a = {}) {
  const {
    type: r = "page",
    touch: n = !0,
    resetOnTouchEnds: o = !1,
    initialValue: l = { x: 0, y: 0 },
    window: s = Aa,
    target: f = s,
    scroll: d = !0,
    eventFilter: p
  } = a;
  let g = null;
  const m = te(l.x), w = te(l.y), S = te(null), b = typeof r == "function" ? r : k1[r], y = (k) => {
    const z = b(k);
    g = k, z && ([m.value, w.value] = z, S.value = "mouse");
  }, x = (k) => {
    if (k.touches.length > 0) {
      const z = b(k.touches[0]);
      z && ([m.value, w.value] = z, S.value = "touch");
    }
  }, B = () => {
    if (!g || !s)
      return;
    const k = b(g);
    g instanceof MouseEvent && k && (m.value = k[0] + s.scrollX, w.value = k[1] + s.scrollY);
  }, E = () => {
    m.value = l.x, w.value = l.y;
  }, F = p ? (k) => p(() => y(k), {}) : (k) => y(k), L = p ? (k) => p(() => x(k), {}) : (k) => x(k), $ = p ? () => p(() => B(), {}) : () => B();
  if (f) {
    const k = { passive: !0 };
    $t(f, ["mousemove", "dragover"], F, k), n && r !== "movement" && ($t(f, ["touchstart", "touchmove"], L, k), o && $t(f, "touchend", E, k)), d && r === "page" && $t(s, "scroll", $, { passive: !0 });
  }
  return {
    x: m,
    y: w,
    sourceType: S
  };
}
function xd(a, r = {}) {
  const {
    handleOutside: n = !0,
    window: o = Aa
  } = r, l = r.type || "page", { x: s, y: f, sourceType: d } = O1(r), p = te(a ?? (o == null ? void 0 : o.document.body)), g = te(0), m = te(0), w = te(0), S = te(0), b = te(0), y = te(0), x = te(!0);
  let B = () => {
  };
  return o && (B = Ue(
    [p, s, f],
    () => {
      const E = zn(p);
      if (!E)
        return;
      const {
        left: F,
        top: L,
        width: $,
        height: k
      } = E.getBoundingClientRect();
      w.value = F + (l === "page" ? o.pageXOffset : 0), S.value = L + (l === "page" ? o.pageYOffset : 0), b.value = k, y.value = $;
      const z = s.value - w.value, H = f.value - S.value;
      x.value = $ === 0 || k === 0 || z < 0 || H < 0 || z > $ || H > k, (n || !x.value) && (g.value = z, m.value = H);
    },
    { immediate: !0 }
  ), $t(document, "mouseleave", () => {
    x.value = !0;
  })), {
    x: s,
    y: f,
    sourceType: d,
    elementX: g,
    elementY: m,
    elementPositionX: w,
    elementPositionY: S,
    elementHeight: b,
    elementWidth: y,
    isOutside: x,
    stop: B
  };
}
function Zc(a) {
  const r = window.getComputedStyle(a);
  if (r.overflowX === "scroll" || r.overflowY === "scroll" || r.overflowX === "auto" && a.clientWidth < a.scrollWidth || r.overflowY === "auto" && a.clientHeight < a.scrollHeight)
    return !0;
  {
    const n = a.parentNode;
    return !n || n.tagName === "BODY" ? !1 : Zc(n);
  }
}
function $1(a) {
  const r = a || window.event, n = r.target;
  return Zc(n) ? !1 : r.touches.length > 1 ? !0 : (r.preventDefault && r.preventDefault(), !1);
}
const Eo = /* @__PURE__ */ new WeakMap();
function R1(a, r = !1) {
  const n = te(r);
  let o = null;
  Ue(w1(a), (f) => {
    const d = kl(Cn(f));
    if (d) {
      const p = d;
      Eo.get(p) || Eo.set(p, p.style.overflow), n.value && (p.style.overflow = "hidden");
    }
  }, {
    immediate: !0
  });
  const l = () => {
    const f = kl(Cn(a));
    !f || n.value || (Zl && (o = $t(
      f,
      "touchmove",
      (d) => {
        $1(d);
      },
      { passive: !1 }
    )), f.style.overflow = "hidden", n.value = !0);
  }, s = () => {
    var f;
    const d = kl(Cn(a));
    !d || !n.value || (Zl && (o == null || o()), d.style.overflow = (f = Eo.get(d)) != null ? f : "", Eo.delete(d), n.value = !1);
  };
  return Zo(s), I({
    get() {
      return n.value;
    },
    set(f) {
      f ? l() : s();
    }
  });
}
function F1(a, r, n, o = {}) {
  var l, s, f;
  const {
    clone: d = !1,
    passive: p = !1,
    eventName: g,
    deep: m = !1,
    defaultValue: w,
    shouldEmit: S
  } = o, b = ds(), y = n || (b == null ? void 0 : b.emit) || ((l = b == null ? void 0 : b.$emit) == null ? void 0 : l.bind(b)) || ((f = (s = b == null ? void 0 : b.proxy) == null ? void 0 : s.$emit) == null ? void 0 : f.bind(b == null ? void 0 : b.proxy));
  let x = g;
  x = x || `update:${r.toString()}`;
  const B = (L) => d ? typeof d == "function" ? d(L) : A1(L) : L, E = () => c1(a[r]) ? B(a[r]) : w, F = (L) => {
    S ? S(L) && y(x, L) : y(x, L);
  };
  if (p) {
    const L = E(), $ = te(L);
    let k = !1;
    return Ue(
      () => a[r],
      (z) => {
        k || (k = !0, $.value = B(z), Mn(() => k = !1));
      }
    ), Ue(
      $,
      (z) => {
        !k && (z !== a[r] || m) && F(z);
      },
      { deep: m }
    ), $;
  } else
    return I({
      get() {
        return E();
      },
      set(L) {
        F(L);
      }
    });
}
const P1 = ["id"], L1 = ["id"], I1 = ["innerHTML"], N1 = ["innerHTML"], V1 = /* @__PURE__ */ ne({
  inheritAttrs: !1,
  __name: "BPopover",
  props: /* @__PURE__ */ ct({
    boundary: { default: "clippingAncestors" },
    boundaryPadding: { default: void 0 },
    click: { type: Boolean, default: !1 },
    content: { default: void 0 },
    customClass: { default: "" },
    delay: { default: () => ({ show: 100, hide: 300 }) },
    floatingMiddleware: { default: void 0 },
    hide: { type: Boolean, default: void 0 },
    html: { type: Boolean, default: !1 },
    id: { default: void 0 },
    inline: { type: Boolean, default: !1 },
    manual: { type: Boolean, default: !1 },
    modelValue: { type: Boolean },
    noAutoClose: { type: Boolean, default: !1 },
    noFade: { type: Boolean, default: !1 },
    noFlip: { type: Boolean, default: !1 },
    noHide: { type: Boolean, default: !1 },
    noShift: { type: Boolean, default: !1 },
    noSize: { type: Boolean, default: !1 },
    noninteractive: { type: Boolean, default: !1 },
    offset: { default: null },
    placement: { default: "top" },
    persistent: { type: Boolean, default: !1 },
    realtime: { type: Boolean, default: !1 },
    reference: { default: null },
    strategy: { default: "absolute" },
    target: { default: null },
    title: { default: void 0 },
    tooltip: { type: Boolean, default: !1 },
    variant: { default: null },
    teleportDisabled: { default: !1 },
    teleportTo: { default: void 0 }
  }, {
    modelValue: { type: Boolean, default: !1 },
    modelModifiers: {}
  }),
  emits: /* @__PURE__ */ ct(["hidden", "hide", "hide-prevented", "show", "show-prevented", "shown"], ["update:modelValue"]),
  setup(a, { expose: r, emit: n }) {
    const o = a, l = n, s = Lt(a, "modelValue"), f = te(s.value), d = te(s.value);
    aw(() => {
      s.value = f.value;
    }), Ue(s, (Y) => {
      Y !== f.value && (Y ? Ke() : Me(new Event("update:modelValue")));
    });
    const p = et(() => o.id, "popover"), g = te(!1), m = te(null), w = te(null), S = te(null), b = te(null), y = te(null), x = I(
      () => o.title ? Pr(o.title, Fr) : ""
    ), B = I(
      () => o.content ? Pr(o.content, Fr) : ""
    ), E = U(() => o.placement.startsWith("auto")), F = Ot(() => o.offset ?? NaN), L = I(
      () => o.boundary === "document" || o.boundary === "viewport" ? void 0 : o.boundary
    ), $ = I(
      () => o.boundary === "document" || o.boundary === "viewport" ? o.boundary : void 0
    ), k = te({}), z = I(() => {
      if (o.floatingMiddleware !== void 0)
        return o.floatingMiddleware;
      const Y = o.offset !== null ? F.value : o.tooltip ? 6 : 8, me = [Lc(Y)];
      return o.noFlip === !1 && !E.value && me.push(
        qc({
          boundary: L.value,
          rootBoundary: $.value,
          padding: o.boundaryPadding
        })
      ), E.value && me.push(
        Qw({
          alignment: o.placement.split("-")[1] || void 0,
          boundary: L.value,
          rootBoundary: $.value,
          padding: o.boundaryPadding
        })
      ), o.noShift === !1 && me.push(
        Wc({
          boundary: L.value,
          rootBoundary: $.value,
          padding: o.boundaryPadding
        })
      ), o.noHide === !1 && me.push(
        e1({
          boundary: L.value,
          rootBoundary: $.value,
          padding: o.boundaryPadding
        })
      ), o.inline === !0 && me.push(n1()), me.push(o1({ element: S, padding: 10 })), o.noSize === !1 && me.push(
        Gc({
          boundary: L.value,
          rootBoundary: $.value,
          padding: o.boundaryPadding,
          apply({ availableWidth: De, availableHeight: mt }) {
            k.value = {
              maxHeight: mt ? `${mt}px` : void 0,
              maxWidth: De ? `${De}px` : void 0
            };
          }
        })
      ), me;
    }), H = U(
      () => E.value ? void 0 : o.placement
    ), { floatingStyles: ie, middlewareData: se, placement: Ae, update: Ie } = Kc(w, m, {
      placement: H,
      middleware: z,
      strategy: U(() => o.strategy),
      whileElementsMounted: (...Y) => Uc(...Y, { animationFrame: o.realtime })
    }), Ce = te({ position: "absolute" });
    Ue(se, () => {
      var Y;
      if (o.noHide === !1 && ((Y = se.value.hide) != null && Y.referenceHidden ? g.value = !0 : g.value = !1), se.value.arrow) {
        const { x: me, y: De } = se.value.arrow;
        Ce.value = {
          position: "absolute",
          top: De ? `${De}px` : "",
          left: me ? `${me}px` : ""
        };
      }
    });
    const Re = I(() => {
      const Y = o.tooltip ? "tooltip" : "popover";
      return [
        Y,
        `b-${Y}`,
        {
          [`b-${Y}-${o.variant}`]: o.variant !== null,
          show: f.value && !g.value,
          "pe-none": !f.value,
          fade: !o.noFade,
          "d-none": !f.value && o.noFade,
          [`${o.customClass}`]: o.customClass !== void 0,
          [`bs-${Y}-${z1(Ae.value)}`]: Ae.value !== void 0
        }
      ];
    }), { isOutside: q } = xd(m), { isOutside: ge } = xd(b), de = (Y) => {
      const me = Y ?? new Event("click");
      f.value ? Me(me) : Ke();
    }, Se = (Y, me = {}) => new ps(Y, {
      cancelable: !1,
      target: m.value || null,
      relatedTarget: null,
      trigger: null,
      ...me,
      componentId: p.value
    });
    let pe;
    const Ke = () => {
      const Y = Se("show", { cancelable: !0 });
      if (l("show", Y), Y.defaultPrevented) {
        l("show-prevented");
        return;
      }
      d.value = !0, Mn(() => {
        var me;
        Ie(), pe = setTimeout(
          () => {
            Ie(), f.value = !0, Mn(() => {
              l("shown", Se("shown"));
            });
          },
          typeof o.delay == "number" ? o.delay : ((me = o.delay) == null ? void 0 : me.show) || 0
        );
      });
    }, Me = (Y) => {
      var me;
      const De = Se("hide", { cancelable: !0 });
      if (l("hide", De), De.defaultPrevented) {
        l("hide-prevented");
        return;
      }
      pe && (clearTimeout(pe), pe = void 0);
      const mt = typeof o.delay == "number" ? o.delay : ((me = o.delay) == null ? void 0 : me.hide) || 0;
      setTimeout(() => {
        var dr, pt;
        (Y == null ? void 0 : Y.type) === "click" || (Y == null ? void 0 : Y.type) === "forceHide" || (Y == null ? void 0 : Y.type) === "update:modelValue" && o.manual || !o.noninteractive && q.value && ge.value && !((dr = m.value) != null && dr.contains(document == null ? void 0 : document.activeElement)) && !((pt = b.value) != null && pt.contains(document == null ? void 0 : document.activeElement)) || o.noninteractive && ge.value ? (f.value = !1, Mn(() => {
          setTimeout(
            () => {
              d.value = !1;
            },
            m.value ? mw(m.value) : 150
          ), l("hidden", Se("hidden"));
        })) : setTimeout(
          () => {
            Me(Y);
          },
          mt < 50 ? 50 : mt
        );
      }, mt);
    };
    r({
      hide: Me,
      show: Ke,
      toggle: de
    });
    const Pe = () => {
      var Y;
      if (o.target) {
        const me = Td(o.target);
        me ? b.value = me : console.warn("Target element not found", o.target);
      } else
        b.value = (Y = y.value) == null ? void 0 : Y.nextElementSibling;
      if (o.reference) {
        const me = Td(o.reference);
        me ? w.value = me : console.warn("Reference element not found", o.reference);
      } else
        w.value = b.value;
      if (!(!b.value || o.manual) && Oc) {
        if (b.value.addEventListener("forceHide", Me), o.click) {
          b.value.addEventListener("click", de);
          return;
        }
        b.value.addEventListener("pointerenter", Ke), b.value.addEventListener("pointerleave", Me), b.value.addEventListener("focus", Ke), b.value.addEventListener("blur", Me);
      }
    }, it = () => {
      b.value && (b.value.removeEventListener("forceHide", Me), b.value.removeEventListener("click", de), b.value.removeEventListener("pointerenter", Ke), b.value.removeEventListener("pointerleave", Me), b.value.removeEventListener("focus", Ke), b.value.removeEventListener("blur", Me));
    };
    return Yc(
      m,
      () => {
        f.value && o.click && !o.noAutoClose && !o.manual && Me(new Event("clickOutside"));
      },
      { ignore: [b] }
    ), Ue([() => o.click, () => o.target, () => o.reference], () => {
      it(), Pe();
    }), xa(Pe), ow(it), (Y, me) => (P(), j(rt, null, [
      je("span", {
        id: K(p) + "_placeholder",
        ref_key: "placeholder",
        ref: y
      }, null, 8, P1),
      W(Y.$slots, "target", {
        show: Ke,
        hide: Me,
        toggle: de,
        showState: f.value
      }),
      (P(), X(Ko, {
        to: Y.teleportTo,
        disabled: !Y.teleportTo || Y.teleportDisabled
      }, [
        d.value || o.persistent ? (P(), j("div", xe({
          key: 0,
          id: K(p)
        }, Y.$attrs, {
          ref_key: "element",
          ref: m,
          class: Re.value,
          role: "tooltip",
          tabindex: "-1",
          style: K(ie)
        }), [
          je("div", {
            ref_key: "arrow",
            ref: S,
            class: Q(`${o.tooltip ? "tooltip" : "popover"}-arrow`),
            style: dn(Ce.value),
            "data-popper-arrow": ""
          }, null, 6),
          je("div", {
            class: "overflow-auto",
            style: dn(k.value)
          }, [
            Y.title || Y.$slots.title ? (P(), j(rt, { key: 0 }, [
              o.html ? (P(), j("div", {
                key: 1,
                class: Q(["position-sticky top-0", o.tooltip ? "tooltip-inner" : "popover-header"]),
                innerHTML: x.value
              }, null, 10, I1)) : (P(), j("div", {
                key: 0,
                class: Q(["position-sticky top-0", o.tooltip ? "tooltip-inner" : "popover-header"])
              }, [
                W(Y.$slots, "title", {}, () => [
                  Te(Ee(Y.title), 1)
                ])
              ], 2))
            ], 64)) : $e("", !0),
            o.tooltip && !Y.$slots.title && !Y.title || !o.tooltip ? (P(), j(rt, { key: 1 }, [
              o.html ? (P(), j("div", {
                key: 1,
                class: Q(o.tooltip ? "tooltip-inner" : "popover-body"),
                innerHTML: B.value
              }, null, 10, N1)) : (P(), j("div", {
                key: 0,
                class: Q(o.tooltip ? "tooltip-inner" : "popover-body")
              }, [
                W(Y.$slots, "default", {}, () => [
                  Te(Ee(Y.content), 1)
                ])
              ], 2))
            ], 64)) : $e("", !0)
          ], 4)
        ], 16, L1)) : $e("", !0)
      ], 8, ["to", "disabled"]))
    ], 64));
  }
}), D1 = ({
  top: a,
  end: r,
  start: n,
  alignCenter: o,
  alignEnd: l
}) => {
  const s = a ? "top" : n ? "left" : r ? "right" : "bottom", f = l ? "end" : o ? null : "start";
  return `${s}${f ? `-${f}` : ""}`;
}, z1 = (a) => {
  const [r] = a.split("-");
  switch (r) {
    case "left":
      return "start";
    case "right":
      return "end";
    default:
      return r;
  }
}, Ql = (a) => typeof a != "object" || a.active !== !1, Sd = (a, r) => {
  if (!Ql(a))
    return {};
  const n = typeof a > "u" || typeof a == "object" && !a.title && !a.content, o = r.getAttribute("title") || r.getAttribute("data-original-title");
  return n ? o ? (r.removeAttribute("title"), r.setAttribute("data-original-title", o), {
    content: Pr(o, Fr)
  }) : {} : typeof a == "string" ? {
    content: Pr(a, Fr)
  } : {
    title: a != null && a.title ? Pr(a == null ? void 0 : a.title, Fr) : void 0,
    content: a != null && a.content ? Pr(a == null ? void 0 : a.content, Fr) : void 0
  };
}, Bd = (a, r) => ({
  target: r,
  modelValue: a.modifiers.show,
  inline: a.modifiers.inline,
  click: a.modifiers.click,
  realtime: a.modifiers.realtime,
  persistent: a.modifiers.persistent,
  placement: a.modifiers.left ? "left" : a.modifiers.right ? "right" : a.modifiers.bottom ? "bottom" : a.modifiers.top ? "top" : void 0,
  html: !0,
  ...typeof a.value == "object" ? a.value : {},
  ...a.modifiers.interactive ? { noninteractive: !1 } : {},
  title: null,
  content: null
}), Ad = (a, r, n) => {
  var o;
  const l = document.createElement("span");
  r.modifiers.body ? document.body.appendChild(l) : r.modifiers.child ? a.appendChild(l) : (o = a.parentNode) == null || o.insertBefore(l, a.nextSibling), Cc(ut(V1, n), l), a.$__element = l;
}, Cd = (a) => {
  const r = a.$__element;
  r && Cc(null, r), setTimeout(() => {
    r == null || r.remove();
  }, 0), delete a.$__element;
}, Qo = (a, r, n) => r.concat(["sm", "md", "lg", "xl", "xxl"]).reduce((o, l) => (o[a ? `${a}${l.charAt(0).toUpperCase() + l.slice(1)}` : l] = n, o), {}), Qc = (a, r, n, o = n) => Object.keys(r).reduce((l, s) => (a[s] && l.push(
  [o, s.replace(n, ""), a[s]].filter((f) => f && typeof f != "boolean").join("-").toLowerCase()
), l), []), Td = (a) => {
  if (a)
    return typeof a == "string" ? typeof document > "u" ? void 0 : document.getElementById(a) ?? document.querySelector(a) ?? void 0 : a.$el ?? a;
}, M1 = /^[0-9]*\.?[0-9]+$/, Ol = /\s+/, H1 = (a) => {
  const r = a.trim();
  return r.charAt(0).toUpperCase() + r.slice(1);
}, U1 = (a) => !!(a.href || a.to), ep = Symbol("tabs"), tp = Symbol("progress"), np = Symbol("checkboxGroup"), rp = Symbol("radioGroup"), ap = Symbol("collapse"), op = Symbol("collapse"), ip = Symbol("navbar"), W1 = Symbol("breadcrumbPlugin"), q1 = Symbol("modalManagerPlugin"), G1 = Symbol("idPluginKey"), ha = (a, r = {}, n = {}) => {
  const o = [a];
  let l;
  for (let s = 0; s < o.length && !l; s++) {
    const f = o[s];
    l = n[f];
  }
  return l && typeof l == "function" ? l(r) : l;
}, j1 = (a, r) => Object.keys(a).filter((n) => !r.map((o) => o.toString()).includes(n)).reduce((n, o) => ({ ...n, [o]: a[o] }), {}), lp = (a, r) => [...r].reduce(
  (n, o) => (n[o] = a[o], n),
  {}
), Er = (a, r, n) => {
  const o = r.split(/[.[\]]/g);
  let l = a;
  for (const s of o) {
    if (l == null)
      return n;
    s.trim() !== "" && (l = l[s]);
  }
  return l === void 0 ? n : l;
}, Ed = (a, r) => r + (a ? H1(a) : ""), ws = (a, r) => {
  const n = U(r), o = U(a), l = U(() => U1(o.value)), s = I(
    () => l.value ? lp(
      o.value,
      n.value ?? [
        "active",
        "activeClass",
        "append",
        "href",
        "rel",
        "replace",
        "routerComponentName",
        "target",
        "to",
        "variant",
        "opacity",
        "opacityHover",
        "underlineVariant",
        "underlineOffset",
        "underlineOffsetHover",
        "underlineOpacity",
        "underlineOpacityHover"
      ]
    ) : {}
  );
  return { computedLink: l, computedLinkProps: s };
}, K1 = () => ({ ...Gt(W1) }), Hn = (a) => I(() => {
  let r = Pt(a);
  return r = {
    ...r,
    variant: r.variant ?? null,
    bgVariant: r.bgVariant ?? null,
    textVariant: r.textVariant ?? null
  }, {
    [`text-bg-${r.variant}`]: r.variant !== null,
    [`text-${r.textVariant}`]: r.textVariant !== null && r.variant === null,
    [`bg-${r.bgVariant}`]: r.bgVariant !== null && r.variant === null
  };
}), Ca = (a) => I(() => {
  const r = Pt(a);
  return r === !0 ? "is-valid" : r === !1 ? "is-invalid" : null;
}), sp = (a) => I(() => {
  const r = Pt(a);
  return {
    "form-check": r.plain === !1 && r.button === !1 && r.hasDefaultSlot,
    "form-check-reverse": r.reverse === !0,
    "form-check-inline": r.inline === !0,
    "form-switch": r.switch === !0,
    [`form-control-${r.size}`]: r.size !== void 0 && r.size !== "md" && r.button === !1
  };
}), up = (a) => {
  const r = U(a), n = Ca(() => r.value.state ?? null);
  return I(() => [
    n.value,
    {
      "form-check-input": r.value.plain === !1 && r.value.button === !1,
      "btn-check": r.value.button === !0
    }
  ]);
}, fp = (a) => I(() => {
  const r = Pt(a);
  return {
    "form-check-label": r.plain === !1 && r.button === !1,
    btn: r.button === !0,
    [`btn-${r.buttonVariant}`]: r.button === !0 && r.buttonVariant !== void 0 && r.buttonVariant !== null,
    [`btn-${r.size}`]: r.button && r.size && r.size !== "md"
  };
}), dp = (a) => {
  const r = U(a), n = Jo(
    () => r.value.ariaInvalid,
    () => r.value.state
  );
  return I(() => ({
    "aria-invalid": n.value,
    "aria-required": r.value.required === !0 ? !0 : void 0
  }));
}, cp = (a) => I(() => {
  const r = Pt(a);
  return {
    "was-validated": r.validated === !0,
    "btn-group": r.buttons === !0 && r.stacked === !1,
    "btn-group-vertical": r.stacked === !0 && r.buttons === !0,
    [`btn-group-${r.size}`]: r.size !== void 0
  };
}), et = (a, r) => I(() => Pt(a) || J1(r)), J1 = (a = "") => `__BVID__${Gt(G1, () => Math.random().toString().slice(2, 8))()}___BV_${a}__`, pp = (a, r) => {
  const n = te(null), o = F1(a, "modelValue", r, { passive: !0 }), l = et(() => a.id, "input"), s = Ot(() => a.debounce ?? 0), f = Ot(() => a.debounceMaxWait ?? NaN), d = x1(
    (b) => {
      o.value = b;
    },
    () => a.lazy === !0 ? 0 : s.value,
    { maxWait: () => a.lazy === !0 ? NaN : f.value }
  ), p = (b, y = !1) => {
    a.lazy === !0 && y === !1 || d(b);
  }, { focused: g } = fn(n, {
    initialValue: a.autofocus
  }), m = (b, y, x = !1) => a.formatter !== void 0 && (!a.lazyFormatter || x) ? a.formatter(b, y) : b, w = (b) => a.trim ? b.trim() : a.number ? Number.parseFloat(b) : b;
  xa(() => {
    var b;
    n.value && (n.value.value = ((b = o.value) == null ? void 0 : b.toString()) ?? "");
  }), tw(() => {
    Mn(() => {
      a.autofocus && (g.value = !0);
    });
  });
  const S = Jo(
    () => a.ariaInvalid,
    () => a.state
  );
  return {
    input: n,
    computedId: l,
    computedAriaInvalid: S,
    onInput: (b) => {
      const { value: y } = b.target, x = m(y, b);
      if (b.defaultPrevented) {
        b.preventDefault();
        return;
      }
      const B = w(x);
      p(B);
    },
    onChange: (b) => {
      const { value: y } = b.target, x = m(y, b);
      if (b.defaultPrevented) {
        b.preventDefault();
        return;
      }
      const B = w(x);
      o.value !== B && p(x, !0);
    },
    onBlur: (b) => {
      if (!a.lazy && !a.lazyFormatter)
        return;
      const { value: y } = b.target, x = m(y, b, !0), B = w(x);
      o.value !== B && p(x, !0);
    },
    focus: () => {
      a.disabled || (g.value = !0);
    },
    blur: () => {
      a.disabled || (g.value = !1);
    }
  };
}, vp = (a, r) => {
  const n = (s) => typeof s == "object" && s !== null && "label" in s, o = (s) => {
    if (typeof s == "string")
      return { value: s, text: s };
    if (typeof s == "number")
      return { value: s, text: `${s}` };
    if (s instanceof Date)
      return { value: s, text: s.toLocaleString() };
    const f = Er(s, r.valueField), d = Er(s, r.textField), p = Er(s, r.htmlField), g = Er(s, r.disabledField), m = r.optionsField ? Er(s, r.optionsField) : void 0;
    return m !== void 0 ? {
      label: Er(s, r.labelField) || d,
      options: m
    } : {
      value: f,
      text: d,
      html: p,
      disabled: g
    };
  }, l = (s) => s.map((f) => o(f));
  return { normalizedOptions: I(() => l(Pt(a))), isComplex: n };
}, $l = "modal-open", X1 = () => {
  const a = Gt(q1), r = (o) => {
    a == null || a.removeStack(o), a == null || a.removeRegistry(o);
  }, n = E1("updateHTMLAttrs", (o, l, s) => {
    const f = typeof o == "string" ? window == null ? void 0 : window.document.querySelector(o) : zn(o);
    f && (l === "class" ? f.classList.toggle($l, s === $l) : f.setAttribute(l, s));
  });
  return Zo(() => {
    n("body", "class", "");
  }), Ue(
    () => a == null ? void 0 : a.countStack.value,
    (o) => {
      o !== void 0 && n("body", "class", o > 0 ? $l : "");
    }
  ), {
    ...a,
    dispose: r
  };
}, Y1 = (a) => {
  const { pushRegistry: r, pushStack: n, removeStack: o, stack: l, dispose: s, countStack: f } = X1(), d = ds();
  if (!d || d.type.__name !== "BModal")
    throw new Error("useModalManager must only use in BModal component");
  return r == null || r(d), Zo(() => {
    s(d);
  }), Ue(
    a,
    (p, g) => {
      p ? n == null || n(d) : g && !p && (o == null || o(d));
    },
    { immediate: !0 }
  ), {
    activePosition: I(
      () => l == null ? void 0 : l.value.findIndex((p) => {
        var g, m;
        return ((g = p.exposed) == null ? void 0 : g.id) === ((m = d.exposed) == null ? void 0 : m.id);
      })
    ),
    activeModalCount: f
  };
}, Z1 = (a, r = "px") => I(() => {
  const n = Pt(a), o = Pt(r);
  return M1.test(String(n)) ? `${Number(n)}${o}` : n;
}), gp = (a) => {
  const r = (n, o) => {
    const l = o === null ? "" : `${o}-`;
    return n === "circle" ? `${l}rounded-circle` : n === "pill" ? `${l}rounded-pill` : typeof n == "number" || n === "0" || n === "1" || n === "2" || n === "3" || n === "4" || n === "5" ? `${l}rounded-${n}` : n === "none" ? `${l}rounded-0` : n === "sm" ? `${l}rounded-1` : n === "lg" ? `${l}rounded-5` : `${l}rounded`;
  };
  return I(() => {
    const n = Pt(a);
    return {
      [`${r(n.rounded, null)}`]: !!n.rounded,
      [`${r(n.roundedTop, "top")}`]: !!n.roundedTop,
      [`${r(n.roundedBottom, "bottom")}`]: !!n.roundedBottom,
      [`${r(n.roundedStart, "start")}`]: !!n.roundedStart,
      [`${r(n.roundedEnd, "end")}`]: !!n.roundedEnd
    };
  });
}, Q1 = (a, r) => {
  const n = Jl(U(a)), o = Jl(U(r)), l = U(() => !o.value);
  xa(() => {
    const s = R1(
      document.body,
      n.value && l.value
    );
    Ue([n, l], ([f, d]) => {
      s.value = f && d;
    });
  });
}, hp = /* @__PURE__ */ ne({
  __name: "BTransition",
  props: {
    appear: { type: Boolean, default: !1 },
    mode: { default: void 0 },
    noFade: { type: Boolean, default: !1 },
    transProps: { default: void 0 }
  },
  emits: ["after-appear", "after-enter", "after-leave", "appear", "before-appear", "before-enter", "before-leave", "enter", "appear-cancelled", "enter-cancelled", "leave", "leave-cancelled"],
  setup(a, { emit: r }) {
    const n = a, o = r, l = I(() => {
      const f = {
        name: "",
        enterActiveClass: "",
        enterToClass: "",
        leaveActiveClass: "",
        leaveToClass: "showing",
        enterFromClass: "showing",
        leaveFromClass: ""
      }, d = {
        ...f,
        enterActiveClass: "fade showing",
        leaveActiveClass: "fade showing"
      };
      return n.noFade ? f : d;
    }), s = I(() => ({ mode: n.mode, css: !0, ...l.value }));
    return (f, d) => (P(), X(lw, xe({ ...s.value, ...f.transProps }, {
      appear: n.appear,
      onAfterAppear: d[0] || (d[0] = (p) => o("after-appear", p)),
      onAfterEnter: d[1] || (d[1] = (p) => o("after-enter", p)),
      onAfterLeave: d[2] || (d[2] = (p) => o("after-leave", p)),
      onAppear: d[3] || (d[3] = (p) => o("appear", p)),
      onBeforeAppear: d[4] || (d[4] = (p) => o("before-appear", p)),
      onBeforeEnter: d[5] || (d[5] = (p) => o("before-enter", p)),
      onBeforeLeave: d[6] || (d[6] = (p) => o("before-leave", p)),
      onEnter: d[7] || (d[7] = (p) => o("enter", p)),
      onAppearCancelled: d[8] || (d[8] = (p) => o("appear-cancelled", p)),
      onEnterCancelled: d[9] || (d[9] = (p) => o("enter-cancelled", p)),
      onLeave: d[10] || (d[10] = (p) => o("leave", p)),
      onLeaveCancelled: d[11] || (d[11] = (p) => o("leave-cancelled", p))
    }), {
      default: ee(() => [
        W(f.$slots, "default")
      ]),
      _: 3
    }, 16, ["appear"]));
  }
}), ex = ["type", "disabled", "aria-label"], tx = /* @__PURE__ */ ne({
  __name: "BCloseButton",
  props: {
    ariaLabel: { default: "Close" },
    disabled: { type: Boolean, default: !1 },
    type: { default: "button" }
  },
  emits: ["click"],
  setup(a, { emit: r }) {
    const n = a, o = r;
    return (l, s) => (P(), j("button", {
      type: l.type,
      class: "btn-close",
      disabled: n.disabled,
      "aria-label": l.ariaLabel,
      onClick: s[0] || (s[0] = (f) => o("click", f))
    }, null, 8, ex));
  }
}), kd = "active", ei = /* @__PURE__ */ ne({
  __name: "BLink",
  props: {
    active: { type: Boolean, default: void 0 },
    activeClass: { default: "router-link-active" },
    append: { type: Boolean, default: !1 },
    disabled: { type: Boolean, default: !1 },
    exactActiveClass: { default: "router-link-exact-active" },
    href: { default: void 0 },
    icon: { type: Boolean, default: !1 },
    opacity: { default: void 0 },
    opacityHover: { default: void 0 },
    rel: { default: void 0 },
    replace: { type: Boolean, default: !1 },
    routerComponentName: { default: "router-link" },
    routerTag: { default: "a" },
    target: { default: void 0 },
    to: { default: void 0 },
    underlineOffset: { default: void 0 },
    underlineOffsetHover: { default: void 0 },
    underlineOpacity: { default: void 0 },
    underlineOpacityHover: { default: void 0 },
    underlineVariant: { default: null },
    variant: { default: null }
  },
  emits: ["click"],
  setup(a, { emit: r }) {
    const n = a, o = r, l = fs(), s = Gt(ap, null), f = Gt(ip, null), d = ds(), p = I(() => {
      const y = n.routerComponentName.split("-").map((x) => x.charAt(0).toUpperCase() + x.slice(1)).join("");
      return (d == null ? void 0 : d.appContext.app.component(y)) === void 0 || n.disabled || !n.to ? "a" : n.routerComponentName;
    }), g = I(() => {
      const y = "#";
      if (n.href)
        return n.href;
      if (typeof n.to == "string")
        return n.to || y;
      const { to: x } = n;
      if (x !== void 0 && "path" in x) {
        const B = x.path || "", E = x.query ? `?${Object.keys(x.query).map((L) => {
          var $;
          return `${L}=${($ = x.query) == null ? void 0 : $[L]}`;
        }).join("=")}` : "", F = !x.hash || x.hash.charAt(0) === "#" ? x.hash || "" : `#${x.hash}`;
        return `${B}${E}${F}` || y;
      }
      return y;
    }), m = I(() => ({
      [`link-${n.variant}`]: n.variant !== null,
      [`link-opacity-${n.opacity}`]: n.opacity !== void 0,
      [`link-opacity-${n.opacityHover}-hover`]: n.opacityHover !== void 0,
      [`link-underline-${n.underlineVariant}`]: n.underlineVariant !== null,
      [`link-offset-${n.underlineOffset}`]: n.underlineOffset !== void 0,
      [`link-offset-${n.underlineOffsetHover}-hover`]: n.underlineOffsetHover !== void 0,
      [`link-underline-opacity-${n.underlineOpacity}`]: n.underlineOpacity !== void 0,
      [`link-underline-opacity-${n.underlineOpacityHover}-hover`]: n.underlineOpacityHover !== void 0,
      "icon-link": n.icon === !0
    })), w = I(() => ({
      class: m.value,
      to: n.to,
      href: g.value,
      target: n.target,
      rel: n.target === "_blank" ? n.rel ?? "noopener" : void 0,
      tabindex: n.disabled ? "-1" : typeof l.tabindex > "u" ? null : l.tabindex,
      "aria-disabled": n.disabled ? !0 : null
    })), S = I(() => ({
      [kd]: n.active,
      disabled: n.disabled
    })), b = (y) => {
      var x, B, E;
      if (n.disabled) {
        y.preventDefault(), y.stopImmediatePropagation();
        return;
      }
      (((x = s == null ? void 0 : s.isNav) == null ? void 0 : x.value) === !0 && f === null || f !== null && ((B = f.autoClose) == null ? void 0 : B.value) === !0) && ((E = s == null ? void 0 : s.hide) == null || E.call(s)), o("click", y);
    };
    return (y, x) => p.value === "router-link" ? (P(), X(Ve(p.value), xe({ key: 0 }, w.value, { custom: "" }), {
      default: ee(({ href: B, navigate: E, isActive: F, isExactActive: L }) => [
        (P(), X(Ve(y.routerTag), xe({
          href: B,
          class: {
            [kd]: n.active,
            [y.activeClass]: F,
            [y.exactActiveClass]: L
          }
        }, y.$attrs, {
          onClick: ($) => {
            E($), b($);
          }
        }), {
          default: ee(() => [
            W(y.$slots, "default")
          ]),
          _: 2
        }, 1040, ["href", "class", "onClick"]))
      ]),
      _: 3
    }, 16)) : (P(), X(Ve(p.value), xe({
      key: 1,
      class: S.value
    }, w.value, { onClick: b }), {
      default: ee(() => [
        W(y.$slots, "default")
      ]),
      _: 3
    }, 16, ["class"]));
  }
}), nx = ["aria-valuenow", "aria-valuemax"], rx = /* @__PURE__ */ ne({
  __name: "BProgressBar",
  props: {
    animated: { type: Boolean, default: !1 },
    label: { default: void 0 },
    labelHtml: { default: void 0 },
    max: { default: void 0 },
    precision: { default: 0 },
    showProgress: { type: Boolean, default: !1 },
    showValue: { type: Boolean, default: !1 },
    striped: { type: Boolean, default: !1 },
    value: { default: 0 },
    variant: { default: null },
    bgVariant: { default: null },
    textVariant: { default: null }
  },
  setup(a) {
    const r = a, n = Gt(tp, null), o = Hn(r), l = I(() => [
      o.value,
      {
        "progress-bar-animated": r.animated || (n == null ? void 0 : n.animated.value),
        "progress-bar-striped": r.striped || (n == null ? void 0 : n.striped.value) || r.animated || (n == null ? void 0 : n.animated.value)
      }
    ]), s = Ot(() => r.precision), f = Ot(() => r.value), d = Ot(() => r.max ?? NaN), p = Ot(() => (n == null ? void 0 : n.max.value) ?? NaN), g = I(
      () => r.labelHtml !== void 0 ? r.labelHtml : r.showValue || n != null && n.showValue.value ? f.value.toFixed(s.value) : r.showProgress || n != null && n.showProgress.value ? (f.value * 100 / (d.value || 100)).toFixed(s.value) : r.label !== void 0 ? r.label : ""
    ), m = I(
      () => p.value ? `${f.value * 100 / p.value}%` : d.value ? `${f.value * 100 / d.value}%` : typeof r.value == "string" ? r.value : `${r.value}%`
    );
    return (w, S) => (P(), j("div", {
      class: Q(["progress-bar", l.value]),
      role: "progressbar",
      "aria-valuenow": w.value,
      "aria-valuemin": "0",
      "aria-valuemax": w.max,
      style: dn({ width: m.value })
    }, [
      W(w.$slots, "default", {}, () => [
        Te(Ee(g.value), 1)
      ])
    ], 14, nx));
  }
}), bE = /* @__PURE__ */ ne({
  __name: "BProgress",
  props: {
    height: { default: void 0 },
    animated: { type: Boolean, default: void 0 },
    max: { default: 100 },
    precision: { default: void 0 },
    showProgress: { type: Boolean, default: void 0 },
    showValue: { type: Boolean, default: void 0 },
    striped: { type: Boolean, default: void 0 },
    value: { default: void 0 },
    variant: { default: void 0 },
    bgVariant: { default: void 0 },
    textVariant: { default: void 0 }
  },
  setup(a) {
    const r = a;
    return Sa(tp, {
      animated: U(() => r.animated),
      max: U(() => r.max),
      showProgress: U(() => r.showProgress),
      showValue: U(() => r.showValue),
      striped: U(() => r.striped)
    }), (n, o) => (P(), j("div", {
      class: "progress",
      style: dn({ height: n.height })
    }, [
      W(n.$slots, "default", {}, () => [
        Rt(rx, {
          animated: n.animated,
          max: n.max,
          precision: n.precision,
          "show-progress": n.showProgress,
          "show-value": n.showValue,
          striped: n.striped,
          value: n.value,
          variant: n.variant,
          "text-variant": n.textVariant,
          "bg-variant": n.bgVariant
        }, null, 8, ["animated", "max", "precision", "show-progress", "show-value", "striped", "value", "variant", "text-variant", "bg-variant"])
      ])
    ], 4));
  }
}), ax = {
  key: 0,
  class: "visually-hidden"
}, mp = /* @__PURE__ */ ne({
  __name: "BSpinner",
  props: {
    label: { default: void 0 },
    role: { default: "status" },
    small: { type: Boolean, default: !1 },
    tag: { default: "span" },
    type: { default: "border" },
    variant: { default: null }
  },
  setup(a) {
    const r = a, n = fr(), o = I(() => [
      `spinner-${r.type}`,
      {
        [`spinner-${r.type}-sm`]: r.small,
        [`text-${r.variant}`]: r.variant !== null
      }
    ]), l = U(() => !Un(n.label));
    return (s, f) => (P(), X(Ve(s.tag), {
      class: Q(o.value),
      role: s.label || l.value ? s.role : null,
      "aria-hidden": s.label || l.value ? null : !0
    }, {
      default: ee(() => [
        s.label || l.value ? (P(), j("span", ax, [
          W(s.$slots, "label", {}, () => [
            Te(Ee(s.label), 1)
          ])
        ])) : $e("", !0)
      ]),
      _: 3
    }, 8, ["class", "role", "aria-hidden"]));
  }
}), Nr = /* @__PURE__ */ ne({
  __name: "BButton",
  props: /* @__PURE__ */ ct({
    loading: { type: Boolean, default: !1 },
    loadingFill: { type: Boolean, default: !1 },
    loadingText: { default: "Loading..." },
    pill: { type: Boolean, default: !1 },
    pressed: { type: Boolean },
    size: { default: "md" },
    squared: { type: Boolean, default: !1 },
    tag: { default: "button" },
    type: { default: "button" },
    variant: { default: "secondary" },
    active: { type: Boolean, default: !1 },
    activeClass: { default: void 0 },
    append: { type: Boolean, default: void 0 },
    disabled: { type: Boolean, default: void 0 },
    exactActiveClass: { default: void 0 },
    href: { default: void 0 },
    icon: { type: Boolean, default: void 0 },
    opacity: { default: void 0 },
    opacityHover: { default: void 0 },
    rel: { default: void 0 },
    replace: { type: Boolean, default: void 0 },
    routerComponentName: { default: void 0 },
    routerTag: { default: void 0 },
    target: { default: void 0 },
    to: { default: void 0 },
    underlineOffset: { default: void 0 },
    underlineOffsetHover: { default: void 0 },
    underlineOpacity: { default: void 0 },
    underlineOpacityHover: { default: void 0 },
    underlineVariant: { default: void 0 }
  }, {
    pressed: { type: Boolean, default: void 0 },
    pressedModifiers: {}
  }),
  emits: /* @__PURE__ */ ct(["click"], ["update:pressed"]),
  setup(a, { emit: r }) {
    const n = a, o = r, l = Lt(a, "pressed"), { computedLink: s, computedLinkProps: f } = ws(n, [
      "active-class",
      "exact-active-class",
      "replace",
      "routerComponentName",
      "routerTag"
    ]), d = U(() => typeof n.pressed == "boolean"), p = U(
      () => n.tag === "button" && n.href === void 0 && n.to === void 0
    ), g = U(() => n.to !== void 0), m = U(() => n.href !== void 0 ? !1 : !p.value), w = I(() => g.value ? f.value : []), S = I(() => [
      [`btn-${n.size}`],
      {
        [`btn-${n.variant}`]: n.variant !== null,
        active: n.active || n.pressed,
        "rounded-pill": n.pill,
        "rounded-0": n.squared,
        disabled: n.disabled
      }
    ]), b = U(() => g.value ? ei : n.href ? "a" : n.tag), y = (x) => {
      if (n.disabled) {
        x.preventDefault(), x.stopPropagation();
        return;
      }
      o("click", x), d.value && (l.value = !n.pressed);
    };
    return (x, B) => (P(), X(Ve(b.value), xe({ class: "btn" }, w.value, {
      class: S.value,
      "aria-disabled": m.value ? n.disabled : null,
      "aria-pressed": d.value ? n.pressed : null,
      autocomplete: d.value ? "off" : null,
      disabled: p.value ? n.disabled : null,
      href: x.href,
      rel: K(s) ? x.rel : null,
      role: m.value || K(s) ? "button" : null,
      target: K(s) ? x.target : null,
      type: p.value ? x.type : null,
      to: p.value ? null : x.to,
      append: K(s) ? x.append : null,
      onClick: y
    }), {
      default: ee(() => [
        n.loading ? W(x.$slots, "loading", { key: 0 }, () => [
          n.loadingFill ? $e("", !0) : (P(), j(rt, { key: 0 }, [
            Te(Ee(x.loadingText), 1)
          ], 64)),
          W(x.$slots, "loading-spinner", {}, () => [
            Rt(mp, {
              small: x.size !== "lg",
              label: n.loadingFill ? x.loadingText : void 0
            }, null, 8, ["small", "label"])
          ])
        ]) : W(x.$slots, "default", { key: 1 })
      ]),
      _: 3
    }, 16, ["class", "aria-disabled", "aria-pressed", "autocomplete", "disabled", "href", "rel", "role", "target", "type", "to", "append"]));
  }
}), ox = /* @__PURE__ */ ne({
  __name: "BOverlay",
  props: {
    bgColor: { default: void 0 },
    blur: { default: "2px" },
    fixed: { type: Boolean, default: !1 },
    noCenter: { type: Boolean, default: !1 },
    noFade: { type: Boolean, default: !1 },
    noSpinner: { type: Boolean, default: !1 },
    noWrap: { type: Boolean, default: !1 },
    opacity: { default: 0.85 },
    overlayTag: { default: "div" },
    show: { type: Boolean, default: !1 },
    spinnerSmall: { type: Boolean, default: !1 },
    spinnerType: { default: "border" },
    spinnerVariant: { default: void 0 },
    variant: { default: "light" },
    wrapTag: { default: "div" },
    zIndex: { default: 10 },
    rounded: { type: [Boolean, String, Number], default: !1 },
    roundedTop: { type: [Boolean, String, Number], default: void 0 },
    roundedBottom: { type: [Boolean, String, Number], default: void 0 },
    roundedStart: { type: [Boolean, String, Number], default: void 0 },
    roundedEnd: { type: [Boolean, String, Number], default: void 0 }
  },
  emits: ["click", "hidden", "shown"],
  setup(a, { emit: r }) {
    const n = a, o = r, l = { top: 0, left: 0, bottom: 0, right: 0 }, s = gp(() => ({
      rounded: n.rounded,
      roundedTop: n.roundedTop,
      roundedBottom: n.roundedBottom,
      roundedStart: n.roundedStart,
      roundedEnd: n.roundedEnd
    })), f = U(
      () => n.variant !== null && !n.bgColor ? `bg-${n.variant}` : ""
    ), d = U(() => n.show ? !0 : null), p = I(() => ({
      type: n.spinnerType,
      variant: n.spinnerVariant,
      small: n.spinnerSmall
    })), g = I(() => ({
      ...l,
      zIndex: n.zIndex || 10
    })), m = I(() => ({
      "position-absolute": !n.noWrap || !n.fixed,
      "position-fixed": n.noWrap && n.fixed
    })), w = I(() => [f.value, s.value]), S = I(() => ({
      ...l,
      opacity: n.opacity,
      backgroundColor: n.bgColor || void 0,
      backdropFilter: n.blur ? `blur(${n.blur})` : void 0
    })), b = I(
      () => n.noCenter ? l : {
        top: "50%",
        left: "50%",
        transform: "translateX(-50%) translateY(-50%)"
      }
    );
    return (y, x) => (P(), X(Ve(y.wrapTag), {
      class: "b-overlay-wrap position-relative",
      "aria-busy": d.value
    }, {
      default: ee(() => [
        W(y.$slots, "default"),
        Rt(hp, {
          "no-fade": y.noFade,
          "trans-props": { enterToClass: "show" },
          name: "fade",
          onAfterEnter: x[1] || (x[1] = (B) => o("shown")),
          onAfterLeave: x[2] || (x[2] = (B) => o("hidden"))
        }, {
          default: ee(() => [
            n.show ? (P(), X(Ve(y.overlayTag), {
              key: 0,
              class: Q(["b-overlay", m.value]),
              style: dn(g.value),
              onClick: x[0] || (x[0] = (B) => o("click", B))
            }, {
              default: ee(() => [
                je("div", {
                  class: Q(["position-absolute", w.value]),
                  style: dn(S.value)
                }, null, 6),
                je("div", {
                  class: "position-absolute",
                  style: dn(b.value)
                }, [
                  W(y.$slots, "overlay", Wt(An(p.value)), () => [
                    n.noSpinner ? $e("", !0) : (P(), X(mp, Wt(xe({ key: 0 }, p.value)), null, 16))
                  ])
                ], 4)
              ]),
              _: 3
            }, 8, ["class", "style"])) : $e("", !0)
          ]),
          _: 3
        }, 8, ["no-fade"])
      ]),
      _: 3
    }, 8, ["aria-busy"]));
  }
}), ix = ["id", "aria-labelledby", "aria-describedby"], lx = ["id"], Od = 1056, sx = /* @__PURE__ */ ne({
  inheritAttrs: !1,
  __name: "BModal",
  props: /* @__PURE__ */ ct({
    autoFocus: { type: Boolean, default: !0 },
    autoFocusButton: { default: void 0 },
    body: { default: void 0 },
    backdropVariant: { default: void 0 },
    bodyAttrs: { default: void 0 },
    bodyBgVariant: { default: null },
    bodyClass: { default: null },
    bodyScrolling: { type: Boolean, default: !1 },
    bodyTextVariant: { default: null },
    bodyVariant: { default: null },
    busy: { type: Boolean, default: !1 },
    buttonSize: { default: "md" },
    cancelDisabled: { type: Boolean, default: !1 },
    cancelTitle: { default: "Cancel" },
    cancelVariant: { default: "secondary" },
    centered: { type: Boolean, default: !1 },
    contentClass: { default: void 0 },
    dialogClass: { default: void 0 },
    footerBgVariant: { default: null },
    footerBorderVariant: { default: null },
    footerClass: { default: void 0 },
    footerTextVariant: { default: null },
    footerVariant: { default: null },
    fullscreen: { type: [Boolean, String], default: !1 },
    headerBgVariant: { default: null },
    headerBorderVariant: { default: null },
    headerClass: { default: void 0 },
    headerCloseClass: { default: void 0 },
    headerCloseLabel: { default: "Close" },
    headerCloseVariant: { default: "secondary" },
    headerTextVariant: { default: null },
    headerVariant: { default: null },
    hideBackdrop: { type: Boolean, default: !1 },
    hideFooter: { type: Boolean, default: !1 },
    hideHeader: { type: Boolean, default: !1 },
    hideHeaderClose: { type: Boolean, default: !1 },
    id: { default: void 0 },
    lazy: { type: Boolean, default: !1 },
    modalClass: { default: void 0 },
    modelValue: { type: Boolean },
    noCloseOnBackdrop: { type: Boolean, default: !1 },
    noCloseOnEsc: { type: Boolean, default: !1 },
    noFade: { type: Boolean, default: !1 },
    okDisabled: { type: Boolean, default: !1 },
    okOnly: { type: Boolean, default: !1 },
    okTitle: { default: "Ok" },
    okVariant: { default: "primary" },
    scrollable: { type: Boolean, default: !1 },
    size: { default: "md" },
    title: { default: void 0 },
    titleClass: { default: void 0 },
    titleSrOnly: { type: Boolean, default: !1 },
    titleTag: { default: "h5" },
    transProps: { default: void 0 },
    teleportDisabled: { default: !1 },
    teleportTo: { default: "body" }
  }, {
    modelValue: { type: Boolean, default: !1 },
    modelModifiers: {}
  }),
  emits: /* @__PURE__ */ ct(["cancel", "close", "hidden", "hide", "hide-prevented", "ok", "show", "show-prevented", "shown"], ["update:modelValue"]),
  setup(a, { expose: r, emit: n }) {
    const o = a, l = n, s = fr(), f = et(() => o.id, "modal"), d = Lt(a, "modelValue"), p = te(null), g = te(null), m = te(null), w = te(null), S = te(d.value), b = te(!1);
    tr(
      "Escape",
      () => {
        pe("esc");
      },
      { target: p }
    ), Q1(d, () => o.bodyScrolling);
    const { focused: y } = fn(p, {
      initialValue: d.value && o.autoFocusButton === void 0
    }), { focused: x } = fn(g, {
      initialValue: d.value && o.autoFocusButton === "ok"
    }), { focused: B } = fn(m, {
      initialValue: d.value && o.autoFocusButton === "cancel"
    }), { focused: E } = fn(w, {
      initialValue: d.value && o.autoFocusButton === "close"
    }), F = I(() => [
      o.modalClass,
      {
        fade: !o.noFade,
        show: S.value
      }
    ]), L = U(
      () => o.lazy === !1 || o.lazy === !0 && b.value === !0 || o.lazy === !0 && d.value === !0
    ), $ = U(
      () => o.backdropVariant !== void 0 ? o.backdropVariant : o.hideBackdrop ? "transparent" : "dark"
    ), k = U(() => !Un(s["header-close"])), z = I(() => [
      o.dialogClass,
      {
        "modal-fullscreen": o.fullscreen === !0,
        [`modal-fullscreen-${o.fullscreen}-down`]: typeof o.fullscreen == "string",
        [`modal-${o.size}`]: o.size !== "md",
        "modal-dialog-centered": o.centered,
        "modal-dialog-scrollable": o.scrollable
      }
    ]), H = Hn(() => ({
      bgVariant: o.bodyBgVariant,
      textVariant: o.bodyTextVariant,
      variant: o.bodyVariant
    })), ie = I(() => [o.bodyClass, H.value]), se = Hn(() => ({
      bgVariant: o.headerBgVariant,
      textVariant: o.headerTextVariant,
      variant: o.headerVariant
    })), Ae = I(() => [
      o.headerClass,
      se.value,
      {
        [`border-${o.headerBorderVariant}`]: o.headerBorderVariant !== null
      }
    ]), Ie = I(() => ({
      variant: k.value ? o.headerCloseVariant : void 0,
      class: o.headerCloseClass
    })), Ce = Hn(() => ({
      bgVariant: o.footerBgVariant,
      textVariant: o.footerTextVariant,
      variant: o.footerVariant
    })), Re = I(() => [
      o.footerClass,
      Ce.value,
      {
        [`border-${o.footerBorderVariant}`]: o.footerBorderVariant !== null
      }
    ]), q = I(() => [
      o.titleClass,
      {
        "visually-hidden": o.titleSrOnly
      }
    ]), ge = U(() => o.cancelDisabled || o.busy), de = U(() => o.okDisabled || o.busy), Se = (le, qe = {}) => new ps(le, {
      cancelable: !1,
      target: p.value || null,
      relatedTarget: null,
      trigger: null,
      ...qe,
      componentId: f.value
    });
    Ue(d, (le, qe) => {
      le !== qe && (le === !0 ? Ke() : pe());
    });
    const pe = (le = "") => {
      if (le === "backdrop" && o.noCloseOnBackdrop || le === "esc" && o.noCloseOnEsc) {
        l("hide-prevented");
        return;
      }
      const qe = Se("hide", { cancelable: le !== "", trigger: le });
      if (le === "ok" && l(le, qe), le === "cancel" && l(le, qe), le === "close" && l(le, qe), l("hide", qe), qe.defaultPrevented) {
        l("hide-prevented"), d.value || (d.value = !0);
        return;
      }
      d.value && (d.value = !1);
    }, Ke = () => {
      if (S.value)
        return;
      const le = Se("show", { cancelable: !0 });
      if (l("show", le), le.defaultPrevented) {
        d.value && (d.value = !1), l("show-prevented");
        return;
      }
      d.value || (d.value = !0);
    }, Me = () => {
      o.autoFocus !== !1 && (o.autoFocusButton === "ok" ? x.value = !0 : o.autoFocusButton === "close" ? E.value = !0 : o.autoFocusButton === "cancel" ? B.value = !0 : y.value = !0);
    }, Pe = () => {
      Ke();
    }, it = () => {
      S.value = !0, Me(), l("shown", Se("shown")), o.lazy === !0 && (b.value = !0);
    }, Y = () => {
      S.value = !1;
    }, me = () => {
      l("hidden", Se("hidden")), o.lazy === !0 && (b.value = !1);
    }, { activePosition: De, activeModalCount: mt } = Y1(S), dr = I(() => ({
      // Make sure that newly opened modals have a higher z-index than currently active ones.
      // All active modals have a z-index of ('defaultZIndex' - 'stackSize' - 'positionInStack').
      //
      // This means inactive modals will already be higher than active ones when opened.
      "z-index": S.value ? Od - (((mt == null ? void 0 : mt.value) ?? 0) - ((De == null ? void 0 : De.value) ?? 0)) : Od
    }));
    $t(p, "bv-toggle", () => {
      d.value ? pe() : Ke();
    });
    const pt = I(() => ({
      cancel: () => {
        pe("cancel");
      },
      close: () => {
        pe("close");
      },
      hide: pe,
      ok: () => {
        pe("ok");
      },
      visible: d.value
    }));
    return r({
      hide: pe,
      id: f,
      show: Ke
    }), (le, qe) => {
      var qn;
      return P(), X(Ko, {
        to: le.teleportTo,
        disabled: o.teleportDisabled
      }, [
        Rt(hp, xe({ "no-fade": !0 }, le.transProps, {
          "trans-props": { enterToClass: "show", ...(qn = le.transProps) == null ? void 0 : qn.transProps },
          onBeforeEnter: Pe,
          onAfterEnter: it,
          onLeave: Y,
          onAfterLeave: me
        }), {
          default: ee(() => [
            Wr(je("div", xe({
              id: K(f),
              ref_key: "element",
              ref: p,
              class: ["modal", F.value],
              role: "dialog",
              "aria-labelledby": o.hideHeader ? void 0 : `${K(f)}-label`,
              "aria-describedby": `${K(f)}-body`,
              tabindex: "-1"
            }, le.$attrs, { style: dr.value }), [
              je("div", {
                class: Q(["modal-dialog", z.value])
              }, [
                L.value ? (P(), j("div", {
                  key: 0,
                  class: Q(["modal-content", le.contentClass])
                }, [
                  o.hideHeader ? $e("", !0) : (P(), j("div", {
                    key: 0,
                    class: Q(["modal-header", Ae.value])
                  }, [
                    W(le.$slots, "header", Wt(An(pt.value)), () => [
                      (P(), X(Ve(le.titleTag), {
                        id: `${K(f)}-label`,
                        class: Q(["modal-title", q.value])
                      }, {
                        default: ee(() => [
                          W(le.$slots, "title", Wt(An(pt.value)), () => [
                            Te(Ee(le.title), 1)
                          ], !0)
                        ]),
                        _: 3
                      }, 8, ["id", "class"])),
                      o.hideHeaderClose ? $e("", !0) : (P(), j(rt, { key: 0 }, [
                        k.value ? (P(), X(Nr, xe({ key: 0 }, Ie.value, {
                          onClick: qe[0] || (qe[0] = (pn) => pe("close"))
                        }), {
                          default: ee(() => [
                            W(le.$slots, "header-close", {}, void 0, !0)
                          ]),
                          _: 3
                        }, 16)) : (P(), X(tx, xe({
                          key: 1,
                          "aria-label": le.headerCloseLabel
                        }, Ie.value, {
                          onClick: qe[1] || (qe[1] = (pn) => pe("close"))
                        }), null, 16, ["aria-label"]))
                      ], 64))
                    ], !0)
                  ], 2)),
                  je("div", xe({
                    id: `${K(f)}-body`,
                    class: ["modal-body", ie.value]
                  }, le.bodyAttrs), [
                    W(le.$slots, "default", Wt(An(pt.value)), () => [
                      Te(Ee(le.body), 1)
                    ], !0)
                  ], 16, lx),
                  o.hideFooter ? $e("", !0) : (P(), j("div", {
                    key: 1,
                    class: Q(["modal-footer", Re.value])
                  }, [
                    W(le.$slots, "footer", Wt(An(pt.value)), () => [
                      W(le.$slots, "cancel", Wt(An(pt.value)), () => [
                        o.okOnly ? $e("", !0) : (P(), X(Nr, {
                          key: 0,
                          ref_key: "cancelButton",
                          ref: m,
                          disabled: ge.value,
                          size: le.buttonSize,
                          variant: le.cancelVariant,
                          onClick: qe[2] || (qe[2] = (pn) => pe("cancel"))
                        }, {
                          default: ee(() => [
                            Te(Ee(le.cancelTitle), 1)
                          ]),
                          _: 1
                        }, 8, ["disabled", "size", "variant"]))
                      ], !0),
                      W(le.$slots, "ok", Wt(An(pt.value)), () => [
                        Rt(Nr, {
                          ref_key: "okButton",
                          ref: g,
                          disabled: de.value,
                          size: le.buttonSize,
                          variant: le.okVariant,
                          onClick: qe[3] || (qe[3] = (pn) => pe("ok"))
                        }, {
                          default: ee(() => [
                            Te(Ee(le.okTitle), 1)
                          ]),
                          _: 1
                        }, 8, ["disabled", "size", "variant"])
                      ], !0)
                    ], !0)
                  ], 2))
                ], 2)) : $e("", !0)
              ], 2),
              W(le.$slots, "backdrop", {}, () => [
                Rt(ox, {
                  variant: $.value,
                  show: d.value,
                  "no-spinner": "",
                  fixed: "",
                  "no-wrap": "",
                  blur: null,
                  onClick: qe[4] || (qe[4] = (pn) => pe("backdrop"))
                }, null, 8, ["variant", "show"])
              ], !0)
            ], 16, ix), [
              [cs, d.value]
            ])
          ]),
          _: 3
        }, 16, ["trans-props"])
      ], 8, ["to", "disabled"]);
    };
  }
}), yp = (a, r) => {
  const n = a.__vccOpts || a;
  for (const [o, l] of r)
    n[o] = l;
  return n;
}, _E = /* @__PURE__ */ yp(sx, [["__scopeId", "data-v-8a2b2b28"]]), wE = /* @__PURE__ */ ne({
  __name: "BBadge",
  props: {
    dotIndicator: { type: Boolean, default: !1 },
    pill: { type: Boolean, default: !1 },
    tag: { default: "span" },
    textIndicator: { type: Boolean, default: !1 },
    active: { type: Boolean, default: void 0 },
    activeClass: { default: void 0 },
    append: { type: Boolean, default: void 0 },
    disabled: { type: Boolean, default: void 0 },
    exactActiveClass: { default: void 0 },
    href: { default: void 0 },
    icon: { type: Boolean, default: void 0 },
    opacity: { default: void 0 },
    opacityHover: { default: void 0 },
    rel: { default: void 0 },
    replace: { type: Boolean, default: void 0 },
    routerComponentName: { default: void 0 },
    target: { default: void 0 },
    to: { default: void 0 },
    underlineOffset: { default: void 0 },
    underlineOffsetHover: { default: void 0 },
    underlineOpacity: { default: void 0 },
    underlineOpacityHover: { default: void 0 },
    underlineVariant: { default: void 0 },
    variant: { default: "secondary" },
    bgVariant: { default: null },
    textVariant: { default: null }
  },
  setup(a) {
    const r = a, n = Hn(r), { computedLink: o, computedLinkProps: l } = ws(r, [
      "active",
      "activeClass",
      "append",
      "disabled",
      "href",
      "rel",
      "replace",
      "routerComponentName",
      "target",
      "to",
      "opacity",
      "opacityHover",
      "underlineVariant",
      "underlineOffset",
      "underlineOffsetHover",
      "underlineOpacity",
      "underlineOpacityHover",
      "icon"
    ]), s = U(() => o.value ? ei : r.tag), f = I(() => [
      n.value,
      {
        active: r.active,
        disabled: r.disabled,
        "rounded-pill": r.pill,
        "position-absolute top-0 start-100 translate-middle": r.textIndicator || r.dotIndicator,
        "p-2 border border-light rounded-circle": r.dotIndicator,
        "text-decoration-none": o.value
      }
    ]);
    return (d, p) => (P(), X(Ve(s.value), xe({
      class: ["badge", f.value]
    }, K(l)), {
      default: ee(() => [
        W(d.$slots, "default")
      ]),
      _: 3
    }, 16, ["class"]));
  }
}), ux = /* @__PURE__ */ ne({
  __name: "BBreadcrumbItem",
  props: {
    ariaCurrent: { default: "location" },
    text: { default: void 0 },
    active: { type: Boolean, default: !1 },
    activeClass: { default: void 0 },
    append: { type: Boolean, default: void 0 },
    disabled: { type: Boolean, default: void 0 },
    exactActiveClass: { default: void 0 },
    href: { default: void 0 },
    icon: { type: Boolean, default: void 0 },
    opacity: { default: void 0 },
    opacityHover: { default: void 0 },
    rel: { default: void 0 },
    replace: { type: Boolean, default: void 0 },
    routerComponentName: { default: void 0 },
    target: { default: void 0 },
    to: { default: void 0 },
    underlineOffset: { default: void 0 },
    underlineOffsetHover: { default: void 0 },
    underlineOpacity: { default: void 0 },
    underlineOpacityHover: { default: void 0 },
    underlineVariant: { default: void 0 },
    variant: { default: void 0 }
  },
  emits: ["click"],
  setup(a, { emit: r }) {
    const n = a, o = r, l = I(() => ({
      active: n.active
    })), s = U(() => n.active ? "span" : ei), f = U(() => n.active ? n.ariaCurrent : void 0), d = I(
      () => s.value !== "span" ? lp(n, [
        "active",
        "activeClass",
        "append",
        "disabled",
        "href",
        "rel",
        "replace",
        "routerComponentName",
        "target",
        "to",
        "variant",
        "opacity",
        "opacityHover",
        "underlineVariant",
        "underlineOffset",
        "underlineOffsetHover",
        "underlineOpacity",
        "underlineOpacityHover",
        "icon"
      ]) : {}
    ), p = (g) => {
      if (n.disabled || n.active) {
        g.preventDefault(), g.stopImmediatePropagation();
        return;
      }
      n.disabled || o("click", g);
    };
    return (g, m) => (P(), j("li", {
      class: Q(["breadcrumb-item", l.value])
    }, [
      (P(), X(Ve(s.value), xe({ "aria-current": f.value }, d.value, { onClick: p }), {
        default: ee(() => [
          W(g.$slots, "default", {}, () => [
            Te(Ee(g.text), 1)
          ])
        ]),
        _: 3
      }, 16, ["aria-current"]))
    ], 2));
  }
}), fx = { "aria-label": "breadcrumb" }, dx = { class: "breadcrumb" }, xE = /* @__PURE__ */ ne({
  __name: "BBreadcrumb",
  props: {
    items: { default: void 0 }
  },
  setup(a) {
    const r = a, n = K1(), o = I(() => {
      var l;
      const s = r.items || ((l = n.items) == null ? void 0 : l.value) || [];
      let f = !1;
      return s.map((d, p) => (typeof d == "string" && (d = { text: d }, p < s.length - 1 && (d.href = "#")), d.active && (f = !0), !d.active && !f && (d.active = p + 1 === s.length), d));
    });
    return (l, s) => (P(), j("nav", fx, [
      je("ol", dx, [
        W(l.$slots, "prepend"),
        (P(!0), j(rt, null, ur(o.value, (f, d) => (P(), X(ux, xe({
          key: d,
          ref_for: !0
        }, f), {
          default: ee(() => [
            Te(Ee(f.text), 1)
          ]),
          _: 2
        }, 1040))), 128)),
        W(l.$slots, "default"),
        W(l.$slots, "append")
      ])
    ]));
  }
}), cx = ["src", "width", "height", "srcset", "sizes", "loading"], bp = /* @__PURE__ */ ne({
  __name: "BImg",
  props: {
    blank: { type: Boolean, default: !1 },
    blankColor: { default: "transparent" },
    block: { type: Boolean, default: !1 },
    center: { type: Boolean, default: !1 },
    end: { type: Boolean, default: !1 },
    fluid: { type: Boolean, default: !1 },
    fluidGrow: { type: Boolean, default: !1 },
    height: { default: void 0 },
    lazy: { type: Boolean, default: !1 },
    sizes: { default: void 0 },
    src: { default: void 0 },
    srcset: { default: void 0 },
    start: { type: Boolean, default: !1 },
    thumbnail: { type: Boolean, default: !1 },
    width: { default: void 0 },
    rounded: { type: [Boolean, String, Number], default: !1 },
    roundedTop: { type: [Boolean, String, Number], default: void 0 },
    roundedBottom: { type: [Boolean, String, Number], default: void 0 },
    roundedStart: { type: [Boolean, String, Number], default: void 0 },
    roundedEnd: { type: [Boolean, String, Number], default: void 0 }
  },
  setup(a) {
    const r = a, n = Ot(() => r.height ?? NaN), o = Ot(() => r.width ?? NaN), l = gp(() => ({
      rounded: r.rounded,
      roundedTop: r.roundedTop,
      roundedBottom: r.roundedBottom,
      roundedStart: r.roundedStart,
      roundedEnd: r.roundedEnd
    })), s = I(
      () => typeof r.srcset == "string" ? r.srcset.split(",").filter((S) => S).join(",") : Array.isArray(r.srcset) ? r.srcset.filter((S) => S).join(",") : void 0
    ), f = I(
      () => typeof r.sizes == "string" ? r.sizes.split(",").filter((S) => S).join(",") : Array.isArray(r.sizes) ? r.sizes.filter((S) => S).join(",") : void 0
    ), d = I(() => {
      const S = Number.isNaN(o.value) ? void 0 : o.value, b = Number.isNaN(n.value) ? void 0 : n.value;
      return r.blank ? S !== void 0 && b === void 0 ? { height: S, width: S } : S === void 0 && b !== void 0 ? { height: b, width: b } : { height: 1, width: 1 } : {
        width: S,
        height: b
      };
    }), p = U(
      () => w(d.value.width, d.value.height, r.blankColor)
    ), g = U(
      () => r.start ? "float-start" : r.end ? "float-end" : r.center ? "mx-auto" : void 0
    ), m = I(() => [
      l.value,
      {
        "img-thumbnail": r.thumbnail,
        "img-fluid": r.fluid || r.fluidGrow,
        "w-100": r.fluidGrow,
        [`${g.value}`]: g.value !== void 0,
        "d-block": r.block || r.center
      }
    ]), w = (S, b, y) => `data:image/svg+xml;charset=UTF-8,${encodeURIComponent(`<svg width="${S}" height="${b}" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 ${S} ${b}" preserveAspectRatio="none">
    <rect width="100%" height="100%" style="fill:${y};"></rect>
    </svg>`)}`;
    return (S, b) => (P(), j("img", {
      class: Q(m.value),
      src: r.blank ? p.value : S.src,
      width: d.value.width || void 0,
      height: d.value.height || void 0,
      srcset: r.blank ? void 0 : s.value,
      sizes: r.blank ? void 0 : f.value,
      loading: r.lazy ? "lazy" : "eager"
    }, null, 10, cx));
  }
}), px = /* @__PURE__ */ ne({
  __name: "BCardImg",
  props: {
    bottom: { type: Boolean, default: !1 },
    top: { type: Boolean, default: !1 },
    blank: { type: Boolean, default: void 0 },
    blankColor: { default: void 0 },
    block: { type: Boolean, default: void 0 },
    center: { type: Boolean, default: void 0 },
    end: { type: Boolean, default: void 0 },
    fluid: { type: Boolean, default: void 0 },
    fluidGrow: { type: Boolean, default: void 0 },
    height: { default: void 0 },
    lazy: { type: Boolean, default: void 0 },
    sizes: { default: void 0 },
    src: { default: void 0 },
    srcset: { default: void 0 },
    start: { type: Boolean, default: void 0 },
    thumbnail: { type: Boolean, default: void 0 },
    width: { default: void 0 },
    rounded: { type: [Boolean, String, Number], default: void 0 },
    roundedTop: { type: [Boolean, String, Number], default: void 0 },
    roundedBottom: { type: [Boolean, String, Number], default: void 0 },
    roundedStart: { type: [Boolean, String, Number], default: void 0 },
    roundedEnd: { type: [Boolean, String, Number], default: void 0 }
  },
  setup(a) {
    const r = a, n = U(
      () => r.top ? "card-img-top" : r.end ? "card-img-right" : r.bottom ? "card-img-bottom" : r.start ? "card-img-left" : "card-img"
    ), o = I(() => j1(r, ["bottom", "top", "end", "start"]));
    return (l, s) => (P(), X(bp, xe(o.value, { class: n.value }), null, 16, ["class"]));
  }
}), vx = ["innerHTML"], _p = /* @__PURE__ */ ne({
  __name: "BCardHeadFoot",
  props: {
    borderVariant: { default: null },
    html: { default: void 0 },
    tag: { default: "div" },
    text: { default: void 0 },
    variant: { default: null },
    bgVariant: { default: null },
    textVariant: { default: null }
  },
  setup(a) {
    const r = a, n = Hn(r), o = I(() => [
      n.value,
      {
        [`border-${r.borderVariant}`]: r.borderVariant !== null
      }
    ]);
    return (l, s) => (P(), X(Ve(l.tag), {
      class: Q(o.value)
    }, {
      default: ee(() => [
        l.html ? (P(), j("div", {
          key: 0,
          innerHTML: l.html
        }, null, 8, vx)) : W(l.$slots, "default", { key: 1 }, () => [
          Te(Ee(l.text), 1)
        ])
      ]),
      _: 3
    }, 8, ["class"]));
  }
}), gx = /* @__PURE__ */ ne({
  __name: "BCardHeader",
  props: {
    borderVariant: { default: void 0 },
    html: { default: void 0 },
    tag: { default: "div" },
    text: { default: void 0 },
    variant: { default: void 0 },
    bgVariant: { default: void 0 },
    textVariant: { default: void 0 }
  },
  setup(a) {
    const r = a;
    return (n, o) => (P(), X(_p, xe({ class: "card-header" }, r), {
      default: ee(() => [
        W(n.$slots, "default")
      ]),
      _: 3
    }, 16));
  }
}), hx = /* @__PURE__ */ ne({
  __name: "BCardTitle",
  props: {
    tag: { default: "h4" },
    text: { default: void 0 }
  },
  setup(a) {
    return (r, n) => (P(), X(Ve(r.tag), { class: "card-title" }, {
      default: ee(() => [
        W(r.$slots, "default", {}, () => [
          Te(Ee(r.text), 1)
        ])
      ]),
      _: 3
    }));
  }
}), mx = /* @__PURE__ */ ne({
  __name: "BCardSubtitle",
  props: {
    text: { default: void 0 },
    tag: { default: "h6" },
    textVariant: { default: "body-secondary" }
  },
  setup(a) {
    const r = a, n = I(() => ({
      [`text-${r.textVariant}`]: r.textVariant !== null
    }));
    return (o, l) => (P(), X(Ve(o.tag), {
      class: Q(["card-subtitle mb-2", n.value])
    }, {
      default: ee(() => [
        W(o.$slots, "default", {}, () => [
          Te(Ee(o.text), 1)
        ])
      ]),
      _: 3
    }, 8, ["class"]));
  }
}), yx = /* @__PURE__ */ ne({
  __name: "BCardBody",
  props: {
    overlay: { type: Boolean, default: !1 },
    subtitle: { default: void 0 },
    subtitleTag: { default: "h4" },
    subtitleTextVariant: { default: void 0 },
    tag: { default: "div" },
    text: { default: void 0 },
    title: { default: void 0 },
    titleTag: { default: "h4" },
    variant: { default: null },
    bgVariant: { default: null },
    textVariant: { default: null }
  },
  setup(a) {
    const r = a, n = fr(), o = Hn(r), l = U(() => !Un(n.title)), s = U(() => !Un(n.subtitle)), f = I(() => [
      o.value,
      {
        "card-img-overlay": r.overlay
      }
    ]);
    return (d, p) => (P(), X(Ve(d.tag), {
      class: Q(["card-body", f.value])
    }, {
      default: ee(() => [
        d.title || l.value ? (P(), X(hx, {
          key: 0,
          tag: d.titleTag
        }, {
          default: ee(() => [
            W(d.$slots, "title", {}, () => [
              Te(Ee(d.title), 1)
            ])
          ]),
          _: 3
        }, 8, ["tag"])) : $e("", !0),
        d.subtitle || s.value ? (P(), X(mx, {
          key: 1,
          tag: d.subtitleTag,
          "text-variant": d.subtitleTextVariant
        }, {
          default: ee(() => [
            W(d.$slots, "subtitle", {}, () => [
              Te(Ee(d.subtitle), 1)
            ])
          ]),
          _: 3
        }, 8, ["tag", "text-variant"])) : $e("", !0),
        W(d.$slots, "default", {}, () => [
          Te(Ee(d.text), 1)
        ])
      ]),
      _: 3
    }, 8, ["class"]));
  }
}), bx = /* @__PURE__ */ ne({
  __name: "BCardFooter",
  props: {
    borderVariant: { default: void 0 },
    html: { default: void 0 },
    tag: { default: "div" },
    text: { default: void 0 },
    variant: { default: void 0 },
    bgVariant: { default: void 0 },
    textVariant: { default: void 0 }
  },
  setup(a) {
    const r = a;
    return (n, o) => (P(), X(_p, xe({ class: "card-footer" }, r), {
      default: ee(() => [
        W(n.$slots, "default", {}, () => [
          Te(Ee(n.text), 1)
        ])
      ]),
      _: 3
    }, 16));
  }
}), SE = /* @__PURE__ */ ne({
  __name: "BCard",
  props: {
    align: { default: void 0 },
    bodyBgVariant: { default: void 0 },
    bodyClass: { default: void 0 },
    bodyTag: { default: "div" },
    bodyText: { default: "" },
    bodyTextVariant: { default: void 0 },
    borderVariant: { default: null },
    footer: { default: void 0 },
    footerBgVariant: { default: void 0 },
    footerBorderVariant: { default: void 0 },
    footerClass: { default: void 0 },
    footerHtml: { default: "" },
    footerTag: { default: "div" },
    footerTextVariant: { default: void 0 },
    footerVariant: { default: null },
    header: { default: void 0 },
    headerBgVariant: { default: void 0 },
    headerBorderVariant: { default: void 0 },
    headerClass: { default: void 0 },
    headerHtml: { default: "" },
    headerTag: { default: "div" },
    headerTextVariant: { default: void 0 },
    headerVariant: { default: null },
    imgAlt: { default: void 0 },
    imgBottom: { type: Boolean, default: !1 },
    imgEnd: { type: Boolean, default: !1 },
    imgHeight: { default: void 0 },
    imgSrc: { default: void 0 },
    imgStart: { type: Boolean, default: !1 },
    imgTop: { type: Boolean, default: !1 },
    imgWidth: { default: void 0 },
    noBody: { type: Boolean, default: !1 },
    overlay: { type: Boolean, default: !1 },
    subtitle: { default: void 0 },
    subtitleTag: { default: "h6" },
    subtitleTextVariant: { default: "body-secondary" },
    tag: { default: "div" },
    title: { default: void 0 },
    titleTag: { default: "h4" },
    variant: { default: null },
    bgVariant: { default: null },
    textVariant: { default: null }
  },
  setup(a) {
    const r = a, n = fr(), o = U(() => !Un(n.header)), l = U(() => !Un(n.footer)), s = Hn(r), f = I(() => [
      s.value,
      {
        [`text-${r.align}`]: r.align !== void 0,
        [`border-${r.borderVariant}`]: r.borderVariant !== null,
        "flex-row": r.imgStart,
        "flex-row-reverse": r.imgEnd
      }
    ]), d = I(() => ({
      src: r.imgSrc,
      alt: r.imgAlt,
      height: r.imgHeight,
      width: r.imgWidth,
      bottom: r.imgBottom,
      end: r.imgEnd,
      start: r.imgStart,
      top: r.imgTop
    })), p = Xc();
    return (g, m) => (P(), X(Ve(g.tag), {
      class: Q(["card", f.value])
    }, {
      default: ee(() => [
        Rt(K(p).define, null, {
          default: ee(() => [
            W(g.$slots, "img", {}, () => [
              g.imgSrc ? (P(), X(px, Wt(xe({ key: 0 }, d.value)), null, 16)) : $e("", !0)
            ])
          ]),
          _: 3
        }),
        r.imgBottom ? $e("", !0) : (P(), X(K(p).reuse, { key: 0 })),
        g.header || o.value || g.headerHtml ? (P(), X(gx, {
          key: 1,
          "bg-variant": g.headerBgVariant,
          variant: g.headerVariant,
          "border-variant": g.headerBorderVariant,
          html: g.headerHtml,
          tag: g.headerTag,
          "text-variant": g.headerTextVariant,
          class: Q(g.headerClass)
        }, {
          default: ee(() => [
            W(g.$slots, "header", {}, () => [
              Te(Ee(g.header), 1)
            ])
          ]),
          _: 3
        }, 8, ["bg-variant", "variant", "border-variant", "html", "tag", "text-variant", "class"])) : $e("", !0),
        r.noBody ? W(g.$slots, "default", { key: 3 }, () => [
          Te(Ee(g.bodyText), 1)
        ]) : (P(), X(yx, {
          key: 2,
          overlay: g.overlay,
          "bg-variant": g.bodyBgVariant,
          tag: g.bodyTag,
          "text-variant": g.bodyTextVariant,
          subtitle: g.subtitle,
          "subtitle-tag": g.subtitleTag,
          "subtitle-text-variant": g.subtitleTextVariant,
          title: g.title,
          "title-tag": g.titleTag,
          class: Q(g.bodyClass)
        }, {
          default: ee(() => [
            W(g.$slots, "default", {}, () => [
              Te(Ee(g.bodyText), 1)
            ])
          ]),
          _: 3
        }, 8, ["overlay", "bg-variant", "tag", "text-variant", "subtitle", "subtitle-tag", "subtitle-text-variant", "title", "title-tag", "class"])),
        g.footer || l.value || g.footerHtml ? (P(), X(bx, {
          key: 4,
          "bg-variant": g.footerBgVariant,
          "border-variant": g.footerBorderVariant,
          variant: g.footerVariant,
          html: g.footerHtml,
          tag: g.footerTag,
          "text-variant": g.footerTextVariant,
          class: Q(g.footerClass)
        }, {
          default: ee(() => [
            W(g.$slots, "footer", {}, () => [
              Te(Ee(g.footer), 1)
            ])
          ]),
          _: 3
        }, 8, ["bg-variant", "border-variant", "variant", "html", "tag", "text-variant", "class"])) : $e("", !0),
        r.imgBottom ? (P(), X(K(p).reuse, { key: 5 })) : $e("", !0)
      ]),
      _: 3
    }, 8, ["class"]));
  }
}), $d = Qo("", [], { type: [Boolean, String, Number], default: !1 }), Rd = Qo("offset", [""], { type: [String, Number], default: null }), Fd = Qo("order", [""], { type: [String, Number], default: null }), _x = ne({
  name: "BCol",
  slots: Object,
  props: {
    col: { type: Boolean, default: !1 },
    // Generic flexbox .col (xs)
    cols: { type: [String, Number], default: null },
    // .col-[1-12]|auto (xs)
    ...$d,
    offset: { type: [String, Number], default: null },
    ...Rd,
    order: { type: [String, Number], default: null },
    ...Fd,
    alignSelf: { type: String, default: null },
    tag: { type: String, default: "div" }
  },
  setup(a) {
    const r = [
      { content: $d, propPrefix: "cols", classPrefix: "col" },
      { content: Rd, propPrefix: "offset" },
      { content: Fd, propPrefix: "order" }
    ], n = I(
      () => r.flatMap((o) => Qc(a, o.content, o.propPrefix, o.classPrefix))
    );
    return {
      computedClasses: I(() => [
        n.value,
        {
          col: a.col || !n.value.some((o) => /^col-/.test(o)) && !a.cols,
          [`col-${a.cols}`]: !!a.cols,
          [`offset-${a.offset}`]: !!a.offset,
          [`order-${a.order}`]: !!a.order,
          [`align-self-${a.alignSelf}`]: !!a.alignSelf
        }
      ])
    };
  }
});
function wx(a, r, n, o, l, s) {
  return P(), X(Ve(a.tag), {
    class: Q(a.computedClasses)
  }, {
    default: ee(() => [
      W(a.$slots, "default")
    ]),
    _: 3
  }, 8, ["class"]);
}
const ya = /* @__PURE__ */ yp(_x, [["render", wx]]), xx = /* @__PURE__ */ ne({
  __name: "BContainer",
  props: {
    fluid: { type: [Boolean, String], default: !1 },
    gutterX: { default: void 0 },
    gutterY: { default: void 0 },
    tag: { default: "div" }
  },
  setup(a) {
    const r = a, n = Ot(() => r.gutterX ?? NaN, { method: "parseInt" }), o = Ot(() => r.gutterY ?? NaN, { method: "parseInt" }), l = I(() => ({
      container: r.fluid === !1,
      "container-fluid": r.fluid === !0,
      [`container-${r.fluid}`]: typeof r.fluid == "string",
      [`gx-${n.value}`]: !Number.isNaN(n.value),
      [`gy-${o.value}`]: !Number.isNaN(o.value)
    }));
    return (s, f) => (P(), X(Ve(s.tag), {
      class: Q(l.value)
    }, {
      default: ee(() => [
        W(s.$slots, "default")
      ]),
      _: 3
    }, 8, ["class"]));
  }
}), Sx = { class: "visually-hidden" }, Bx = ["aria-labelledby", "role"], BE = /* @__PURE__ */ ne({
  __name: "BDropdown",
  props: /* @__PURE__ */ ct({
    ariaLabel: { default: void 0 },
    autoClose: { type: [Boolean, String], default: !0 },
    boundary: { default: "clippingAncestors" },
    boundaryPadding: { default: void 0 },
    center: { type: Boolean, default: !1 },
    disabled: { type: Boolean, default: !1 },
    dropend: { type: Boolean, default: !1 },
    dropstart: { type: Boolean, default: !1 },
    dropup: { type: Boolean, default: !1 },
    end: { type: Boolean, default: !1 },
    floatingMiddleware: { default: void 0 },
    id: { default: void 0 },
    isNav: { type: Boolean, default: !1 },
    lazy: { type: Boolean, default: !1 },
    menuClass: { default: void 0 },
    modelValue: { type: Boolean },
    noCaret: { type: Boolean, default: !1 },
    noFlip: { type: Boolean, default: !1 },
    noShift: { type: Boolean, default: !1 },
    noSize: { type: Boolean, default: !1 },
    offset: { default: 0 },
    role: { default: "menu" },
    size: { default: "md" },
    split: { type: Boolean, default: !1 },
    splitButtonType: { default: "button" },
    splitClass: { default: void 0 },
    splitDisabled: { type: Boolean, default: void 0 },
    splitHref: { default: void 0 },
    splitTo: { default: void 0 },
    splitVariant: { default: void 0 },
    strategy: { default: "absolute" },
    text: { default: void 0 },
    toggleClass: { default: void 0 },
    toggleText: { default: "Toggle dropdown" },
    variant: { default: "secondary" },
    teleportDisabled: { default: !1 },
    teleportTo: { default: void 0 }
  }, {
    modelValue: { type: Boolean, default: !1 },
    modelModifiers: {}
  }),
  emits: /* @__PURE__ */ ct(["click", "hidden", "hide", "hide-prevented", "show", "show-prevented", "shown", "toggle"], ["update:modelValue"]),
  setup(a, { expose: r, emit: n }) {
    const o = a, l = n, s = et(() => o.id, "dropdown"), f = Lt(a, "modelValue"), d = U(
      () => typeof o.offset == "string" || typeof o.offset == "number" ? o.offset : NaN
    ), p = Ot(d), g = te(null), m = te(null), w = te(null), S = te(null), b = I(
      () => o.boundary === "document" || o.boundary === "viewport" ? void 0 : o.boundary
    ), y = I(
      () => o.boundary === "document" || o.boundary === "viewport" ? o.boundary : void 0
    ), x = U(() => o.split ? m.value : w.value);
    tr(
      "Escape",
      () => {
        f.value = !f.value;
      },
      { target: x }
    ), tr(
      "Escape",
      () => {
        f.value = !f.value;
      },
      { target: g }
    );
    const B = (q, ge) => {
      var de, Se, pe, Ke, Me, Pe, it;
      if ((Se = g.value) != null && Se.contains((de = q.target) == null ? void 0 : de.closest("form")) || /input|select|option|textarea|form/i.test((pe = q.target) == null ? void 0 : pe.tagName))
        return;
      if (q.preventDefault(), !f.value) {
        open(), Mn(() => B(q, ge));
        return;
      }
      const Y = (Ke = g.value) == null ? void 0 : Ke.querySelectorAll(".dropdown-item:not(.disabled):not(:disabled)");
      if (Y)
        if ((Me = g.value) != null && Me.contains(document.activeElement)) {
          const me = g.value.querySelector(".dropdown-item:focus"), De = Array.prototype.indexOf.call(Y, me) + ge;
          De >= 0 && De < (Y == null ? void 0 : Y.length) && ((Pe = Y[De]) == null || Pe.focus());
        } else
          (it = Y[ge === -1 ? Y.length - 1 : 0]) == null || it.focus();
    };
    tr("ArrowUp", (q) => B(q, -1), { target: x }), tr("ArrowDown", (q) => B(q, 1), { target: x }), tr("ArrowUp", (q) => B(q, -1), { target: g }), tr("ArrowDown", (q) => B(q, 1), { target: g });
    const E = I(
      () => D1({
        top: o.dropup,
        start: o.dropstart,
        end: o.dropend,
        alignCenter: o.center,
        alignEnd: o.end
      })
    ), F = te({}), L = I(() => {
      if (o.floatingMiddleware !== void 0)
        return o.floatingMiddleware;
      const q = typeof o.offset == "string" || typeof o.offset == "number" ? p.value : o.offset, ge = [Lc(q)];
      return o.noFlip === !1 && ge.push(
        qc({
          boundary: b.value,
          rootBoundary: y.value,
          padding: o.boundaryPadding
        })
      ), o.noShift === !1 && ge.push(
        Wc({
          boundary: b.value,
          rootBoundary: y.value,
          padding: o.boundaryPadding
        })
      ), o.noSize === !1 && ge.push(
        Gc({
          boundary: b.value,
          rootBoundary: y.value,
          padding: o.boundaryPadding,
          apply({ availableWidth: de, availableHeight: Se }) {
            F.value = {
              maxHeight: Se && f.value ? `${Se}px` : void 0,
              maxWidth: de && f.value ? `${de}px` : void 0
            };
          }
        })
      ), ge;
    }), { update: $, floatingStyles: k } = Kc(x, g, {
      placement: E,
      middleware: L,
      strategy: U(() => o.strategy),
      whileElementsMounted: Uc
    }), z = I(() => ({
      dropup: o.dropup,
      dropend: o.dropend,
      dropstart: o.dropstart,
      "position-static": o.boundary !== "clippingAncestors" && !o.isNav
    })), H = I(() => [
      o.split ? o.splitClass : o.toggleClass,
      {
        "nav-link": o.isNav,
        "dropdown-toggle": !o.split,
        "dropdown-toggle-no-caret": o.noCaret && !o.split,
        show: o.split ? void 0 : f.value
      }
    ]), ie = () => {
      Re();
    }, se = (q) => {
      o.split ? l("click", q) : ie();
    };
    Yc(
      g,
      () => {
        f.value && (o.autoClose === !0 || o.autoClose === "outside") && Re();
      },
      { ignore: [m, w] }
    );
    const Ae = () => {
      f.value && (o.autoClose === !0 || o.autoClose === "inside") && Re();
    }, Ie = () => {
      f.value && Re();
    }, Ce = () => {
      f.value || Re();
    }, Re = () => {
      var q;
      l("toggle");
      const ge = f.value, de = new ps(ge ? "hide" : "show");
      if (l(ge ? "hide" : "show", de), de.defaultPrevented) {
        l(ge ? "hide-prevented" : "show-prevented");
        return;
      }
      f.value = !ge, l(ge ? "hidden" : "shown"), (q = S.value) == null || q.dispatchEvent(new Event("forceHide"));
    };
    return Ue(f, () => {
      $();
    }), r({
      hide: Ie,
      show: Ce,
      toggle: Re
    }), Sa(op, {
      id: s,
      show: Ce,
      hide: Ie,
      toggle: Re,
      visible: U(() => f.value),
      isNav: U(() => o.isNav)
    }), (q, ge) => (P(), j("div", {
      ref_key: "wrapper",
      ref: S,
      class: Q([z.value, "btn-group"])
    }, [
      Rt(Nr, {
        id: K(s),
        ref_key: "splitButton",
        ref: w,
        variant: q.splitVariant || q.variant,
        size: q.size,
        class: Q(H.value),
        disabled: o.splitDisabled || q.disabled,
        type: q.splitButtonType,
        "aria-label": q.ariaLabel,
        "aria-expanded": o.split ? void 0 : f.value,
        "aria-haspopup": o.split ? void 0 : "menu",
        href: o.split ? q.splitHref : void 0,
        to: o.split && q.splitTo ? q.splitTo : void 0,
        onClick: se
      }, {
        default: ee(() => [
          W(q.$slots, "button-content", {}, () => [
            Te(Ee(q.text), 1)
          ])
        ]),
        _: 3
      }, 8, ["id", "variant", "size", "class", "disabled", "type", "aria-label", "aria-expanded", "aria-haspopup", "href", "to"]),
      o.split ? (P(), X(Nr, {
        key: 0,
        ref_key: "button",
        ref: m,
        variant: q.variant,
        size: q.size,
        disabled: q.disabled,
        class: Q([[q.toggleClass, { show: f.value }], "dropdown-toggle-split dropdown-toggle"]),
        "aria-expanded": f.value,
        "aria-haspopup": "menu",
        onClick: ie
      }, {
        default: ee(() => [
          je("span", Sx, [
            W(q.$slots, "toggle-text", {}, () => [
              Te(Ee(q.toggleText), 1)
            ])
          ])
        ]),
        _: 3
      }, 8, ["variant", "size", "disabled", "class", "aria-expanded"])) : $e("", !0),
      (P(), X(Ko, {
        to: q.teleportTo,
        disabled: !q.teleportTo || q.teleportDisabled
      }, [
        !o.lazy || f.value ? Wr((P(), j("ul", {
          key: 0,
          ref_key: "floating",
          ref: g,
          style: dn([K(k), F.value]),
          class: Q(["dropdown-menu overflow-auto", [q.menuClass, { show: f.value }]]),
          "aria-labelledby": K(s),
          role: q.role,
          onClick: Ae
        }, [
          W(q.$slots, "default", {
            hide: Ie,
            show: Ce
          })
        ], 14, Bx)), [
          [cs, o.lazy || f.value]
        ]) : $e("", !0)
      ], 8, ["to", "disabled"]))
    ], 2));
  }
}), AE = /* @__PURE__ */ ne({
  inheritAttrs: !1,
  __name: "BDropdownItem",
  props: {
    linkClass: { default: void 0 },
    wrapperAttrs: { default: void 0 },
    active: { type: Boolean, default: void 0 },
    activeClass: { default: void 0 },
    append: { type: Boolean, default: void 0 },
    disabled: { type: Boolean, default: void 0 },
    exactActiveClass: { default: void 0 },
    href: { default: void 0 },
    icon: { type: Boolean, default: void 0 },
    opacity: { default: void 0 },
    opacityHover: { default: void 0 },
    rel: { default: void 0 },
    replace: { type: Boolean, default: void 0 },
    routerComponentName: { default: void 0 },
    target: { default: void 0 },
    to: { default: void 0 },
    underlineOffset: { default: void 0 },
    underlineOffsetHover: { default: void 0 },
    underlineOpacity: { default: void 0 },
    underlineOpacityHover: { default: void 0 },
    underlineVariant: { default: void 0 },
    variant: { default: null }
  },
  emits: ["click"],
  setup(a, { emit: r }) {
    const n = a, o = r, { class: l, ...s } = fs(), { computedLink: f, computedLinkProps: d } = ws(n), p = I(() => [
      n.linkClass,
      {
        active: n.active,
        disabled: n.disabled,
        [`text-${n.variant}`]: n.variant !== null
      }
    ]), g = U(() => f.value ? ei : n.href ? "a" : "button"), m = Gt(ap, null), w = Gt(op, null), S = Gt(ip, null), b = (y) => {
      var x, B, E;
      o("click", y), S !== null && ((x = S == null ? void 0 : S.autoClose) == null ? void 0 : x.value) === !0 && ((B = m == null ? void 0 : m.hide) == null || B.call(m)), (E = w == null ? void 0 : w.hide) == null || E.call(w);
    };
    return (y, x) => (P(), j("li", xe({
      role: "presentation",
      class: K(l)
    }, y.wrapperAttrs), [
      (P(), X(Ve(g.value), xe({
        class: ["dropdown-item", p.value],
        disabled: n.disabled,
        "aria-disabled": n.disabled ? !0 : null,
        "aria-current": n.active ? !0 : null,
        href: g.value === "a" ? y.href : null,
        rel: y.rel,
        role: "menuitem",
        type: g.value === "button" ? "button" : null,
        target: y.target
      }, { ...K(d), ...s }, { onClick: b }), {
        default: ee(() => [
          W(y.$slots, "default")
        ]),
        _: 3
      }, 16, ["class", "disabled", "aria-disabled", "aria-current", "href", "rel", "type", "target"]))
    ], 16));
  }
}), Pd = /* @__PURE__ */ ne({
  __name: "BFormInvalidFeedback",
  props: {
    ariaLive: { default: void 0 },
    forceShow: { type: Boolean, default: !1 },
    id: { default: void 0 },
    role: { default: void 0 },
    state: { type: [Boolean, null], default: null },
    tag: { default: "div" },
    text: { default: void 0 },
    tooltip: { type: Boolean, default: !1 }
  },
  setup(a) {
    const r = a, n = U(() => r.forceShow === !0 || r.state === !1), o = I(() => ({
      "d-block": n.value,
      "invalid-feedback": !r.tooltip,
      "invalid-tooltip": r.tooltip
    }));
    return (l, s) => (P(), X(Ve(l.tag), {
      id: l.id,
      role: l.role,
      "aria-live": l.ariaLive,
      "aria-atomic": l.ariaLive ? !0 : void 0,
      class: Q(o.value)
    }, {
      default: ee(() => [
        W(l.$slots, "default", {}, () => [
          Te(Ee(l.text), 1)
        ])
      ]),
      _: 3
    }, 8, ["id", "role", "aria-live", "aria-atomic", "class"]));
  }
}), Rl = /* @__PURE__ */ ne({
  __name: "BFormRow",
  props: {
    tag: { default: "div" }
  },
  setup(a) {
    return (r, n) => (P(), X(Ve(r.tag), { class: "row d-flex flex-wrap" }, {
      default: ee(() => [
        W(r.$slots, "default")
      ]),
      _: 3
    }));
  }
}), Ld = /* @__PURE__ */ ne({
  __name: "BFormText",
  props: {
    id: { default: void 0 },
    inline: { type: Boolean, default: !1 },
    tag: { default: "small" },
    text: { default: void 0 },
    textVariant: { default: "body-secondary" }
  },
  setup(a) {
    const r = a, n = I(() => ({
      [`text-${r.textVariant}`]: r.textVariant !== null,
      "form-text": !r.inline
    }));
    return (o, l) => (P(), X(Ve(o.tag), {
      id: o.id,
      class: Q(n.value)
    }, {
      default: ee(() => [
        W(o.$slots, "default", {}, () => [
          Te(Ee(o.text), 1)
        ])
      ]),
      _: 3
    }, 8, ["id", "class"]));
  }
}), Id = /* @__PURE__ */ ne({
  __name: "BFormValidFeedback",
  props: {
    ariaLive: { default: void 0 },
    forceShow: { type: Boolean, default: !1 },
    id: { default: void 0 },
    role: { default: void 0 },
    state: { type: [Boolean, null], default: null },
    tag: { default: "div" },
    text: { default: void 0 },
    tooltip: { type: Boolean, default: !1 }
  },
  setup(a) {
    const r = a, n = U(() => r.forceShow === !0 || r.state === !0), o = I(() => ({
      "d-block": n.value,
      "valid-feedback": !r.tooltip,
      "valid-tooltip": r.tooltip
    }));
    return (l, s) => (P(), X(Ve(l.tag), {
      id: l.id,
      role: l.role,
      "aria-live": l.ariaLive,
      "aria-atomic": l.ariaLive ? !0 : void 0,
      class: Q(o.value)
    }, {
      default: ee(() => [
        W(l.$slots, "default", {}, () => [
          Te(Ee(l.text), 1)
        ])
      ]),
      _: 3
    }, 8, ["id", "role", "aria-live", "aria-atomic", "class"]));
  }
}), wp = ne({
  name: "ComponentOrEmpty",
  slots: Object,
  props: {
    tag: {
      type: String,
      default: "div"
    },
    to: {
      type: [String, Object],
      default: null
    },
    skip: {
      type: Boolean,
      default: !1
    }
  },
  setup(a, { slots: r, attrs: n }) {
    return () => {
      var o, l, s;
      return a.skip ? (o = r.default) == null ? void 0 : o.call(r) : a.tag === "Teleport" ? ut(Ko, { to: a.to }, [(l = r.default) == null ? void 0 : l.call(r)]) : ut(a.tag, { ...n }, [(s = r.default) == null ? void 0 : s.call(r)]);
    };
  }
}), Ax = ["id", "disabled", "required", "name", "form", "aria-label", "aria-labelledby", "aria-required", "value", "true-value", "false-value", "indeterminate"], Cx = ["for"], Tx = /* @__PURE__ */ ne({
  inheritAttrs: !1,
  __name: "BFormCheckbox",
  props: /* @__PURE__ */ ct({
    ariaLabel: { default: void 0 },
    ariaLabelledby: { default: void 0 },
    autofocus: { type: Boolean, default: !1 },
    button: { type: Boolean, default: !1 },
    buttonGroup: { type: Boolean, default: !1 },
    buttonVariant: { default: null },
    disabled: { type: Boolean, default: !1 },
    form: { default: void 0 },
    id: { default: void 0 },
    indeterminate: { type: Boolean },
    inline: { type: Boolean, default: !1 },
    modelValue: { type: [Array, Set, String, Boolean, Object, Number, null] },
    name: { default: void 0 },
    plain: { type: Boolean, default: !1 },
    required: { type: Boolean, default: void 0 },
    reverse: { type: Boolean, default: !1 },
    size: { default: void 0 },
    state: { type: [Boolean, null], default: null },
    switch: { type: Boolean, default: !1 },
    uncheckedValue: { type: [Array, Set, String, Boolean, Object, Number, null], default: !1 },
    value: { type: [String, Boolean, Array, Set, Object, Number, null], default: !0 }
  }, {
    modelValue: { type: [Array, Set, String, Boolean, Object, Number, null], default: void 0 },
    modelModifiers: {},
    indeterminate: { type: Boolean, default: !1 },
    indeterminateModifiers: {}
  }),
  emits: ["update:modelValue", "update:indeterminate"],
  setup(a, { expose: r }) {
    const n = a, o = fr(), l = Lt(a, "modelValue"), s = Lt(a, "indeterminate"), f = et(() => n.id, "form-check"), d = Gt(np, null), p = te(null), { focused: g } = fn(p, {
      initialValue: n.autofocus
    }), m = U(() => !Un(o.default)), w = I({
      get: () => (d == null ? void 0 : d.modelValue.value) ?? l.value,
      set: (F) => {
        if (F !== void 0) {
          if (s.value = !1, d !== null && Array.isArray(F)) {
            d.modelValue.value = F;
            return;
          }
          l.value = F;
        }
      }
    }), S = U(
      () => !!(n.name ?? (d == null ? void 0 : d.name.value)) && (n.required || (d == null ? void 0 : d.required.value))
    ), b = U(() => n.buttonGroup || ((d == null ? void 0 : d.buttons.value) ?? !1)), y = I(() => ({
      plain: n.plain || ((d == null ? void 0 : d.plain.value) ?? !1),
      button: n.button || ((d == null ? void 0 : d.buttons.value) ?? !1),
      inline: n.inline || ((d == null ? void 0 : d.inline.value) ?? !1),
      reverse: n.reverse || ((d == null ? void 0 : d.reverse.value) ?? !1),
      switch: n.switch || ((d == null ? void 0 : d.switch.value) ?? !1),
      state: n.state || (d == null ? void 0 : d.state.value),
      size: n.size ?? (d == null ? void 0 : d.size.value) ?? "md",
      // This is where the true default is made
      buttonVariant: n.buttonVariant ?? (d == null ? void 0 : d.buttonVariant.value) ?? "secondary",
      // This is where the true default is made
      hasDefaultSlot: m.value
    })), x = sp(y), B = up(y), E = fp(y);
    return r({
      blur: () => {
        g.value = !1;
      },
      element: p,
      focus: () => {
        g.value = !0;
      }
    }), (F, L) => (P(), X(wp, {
      skip: b.value,
      class: Q(K(x))
    }, {
      default: ee(() => {
        var $, k, z;
        return [
          Wr(je("input", xe({ id: K(f) }, F.$attrs, {
            ref_key: "input",
            ref: p,
            "onUpdate:modelValue": L[0] || (L[0] = (H) => w.value = H),
            class: K(B),
            type: "checkbox",
            disabled: n.disabled || (($ = K(d)) == null ? void 0 : $.disabled.value),
            required: S.value || void 0,
            name: F.name || ((k = K(d)) == null ? void 0 : k.name.value),
            form: F.form || ((z = K(d)) == null ? void 0 : z.form.value),
            "aria-label": F.ariaLabel,
            "aria-labelledby": F.ariaLabelledby,
            "aria-required": S.value || void 0,
            value: F.value,
            "true-value": F.value,
            "false-value": F.uncheckedValue,
            indeterminate: n.indeterminate
          }), null, 16, Ax), [
            [rw, w.value]
          ]),
          m.value || n.plain === !1 ? (P(), j("label", {
            key: 0,
            for: K(f),
            class: Q(K(E))
          }, [
            W(F.$slots, "default")
          ], 10, Cx)) : $e("", !0)
        ];
      }),
      _: 3
    }, 8, ["skip", "class"]));
  }
}), Ex = ["id"], kx = ["innerHTML"], CE = /* @__PURE__ */ ne({
  __name: "BFormCheckboxGroup",
  props: /* @__PURE__ */ ct({
    ariaInvalid: { type: [Boolean, String], default: void 0 },
    autofocus: { type: Boolean, default: !1 },
    buttonVariant: { default: "secondary" },
    buttons: { type: Boolean, default: !1 },
    disabled: { type: Boolean, default: !1 },
    disabledField: { default: "disabled" },
    form: { default: void 0 },
    htmlField: { default: "html" },
    id: { default: void 0 },
    modelValue: {},
    name: { default: void 0 },
    options: { default: () => [] },
    plain: { type: Boolean, default: !1 },
    required: { type: Boolean, default: !1 },
    reverse: { type: Boolean, default: !1 },
    size: { default: "md" },
    stacked: { type: Boolean, default: !1 },
    state: { type: [Boolean, null], default: null },
    switches: { type: Boolean, default: !1 },
    textField: { default: "text" },
    validated: { type: Boolean, default: !1 },
    valueField: { default: "value" }
  }, {
    modelValue: {
      default: () => []
    },
    modelModifiers: {}
  }),
  emits: ["update:modelValue"],
  setup(a, { expose: r }) {
    const n = a, o = Lt(a, "modelValue"), l = et(() => n.id, "checkbox"), s = et(() => n.name, "checkbox"), f = te(null), { focused: d } = fn(f, {
      initialValue: n.autofocus
    });
    Sa(np, {
      modelValue: o,
      switch: U(() => n.switches),
      buttonVariant: U(() => n.buttonVariant),
      form: U(() => n.form),
      name: s,
      state: U(() => n.state),
      plain: U(() => n.plain),
      size: U(() => n.size),
      inline: U(() => !n.stacked),
      reverse: U(() => n.reverse),
      required: U(() => n.required),
      buttons: U(() => n.buttons),
      disabled: U(() => n.disabled)
    });
    const p = I(
      () => n.options.map(
        (S, b) => typeof S == "string" || typeof S == "number" ? {
          props: {
            value: S,
            disabled: n.disabled
          },
          text: S.toString(),
          html: void 0,
          self: Symbol(`checkboxGroupOptionItem${b}`)
        } : {
          props: {
            value: S[n.valueField],
            disabled: S[n.disabledField],
            ...S.props ? S.props : {}
          },
          text: S[n.textField],
          html: S[n.htmlField],
          self: Symbol(`checkboxGroupOptionItem${b}`)
        }
      )
    ), g = I(() => ({
      required: n.required,
      ariaInvalid: n.ariaInvalid,
      state: n.state,
      validated: n.validated,
      buttons: n.buttons,
      stacked: n.stacked,
      size: n.size
    })), m = dp(g), w = cp(g);
    return r({
      blur: () => {
        d.value = !1;
      },
      focus: () => {
        d.value = !0;
      }
    }), (S, b) => (P(), j("div", xe(K(m), {
      id: K(l),
      ref_key: "element",
      ref: f,
      role: "group",
      class: [K(w), "bv-no-focus-ring"],
      tabindex: "-1"
    }), [
      W(S.$slots, "first"),
      (P(!0), j(rt, null, ur(p.value, (y) => (P(), X(Tx, xe({
        key: y.self,
        ref_for: !0
      }, y.props), {
        default: ee(() => [
          y.html ? (P(), j("span", {
            key: 0,
            innerHTML: y.html
          }, null, 8, kx)) : (P(), j(rt, { key: 1 }, [
            Te(Ee(y.text), 1)
          ], 64))
        ]),
        _: 2
      }, 1040))), 128)),
      W(S.$slots, "default")
    ], 16, Ex));
  }
}), xp = ["input", "select", "textarea"], Ox = xp.map((a) => `${a}:not([disabled])`).join(), $x = [...xp, "a", "button", "label"], Rx = "label", Fx = "invalid-feedback", Px = "valid-feedback", Lx = "description", Ix = "default", Nx = ne({
  components: { BCol: ya, BFormInvalidFeedback: Pd, BFormRow: Rl, BFormText: Ld, BFormValidFeedback: Id },
  props: {
    ariaInvalid: { type: [Boolean, String], default: void 0 },
    contentCols: { type: [Boolean, String, Number], default: void 0 },
    contentColsLg: { type: [Boolean, String, Number], default: void 0 },
    contentColsMd: { type: [Boolean, String, Number], default: void 0 },
    contentColsSm: { type: [Boolean, String, Number], default: void 0 },
    contentColsXl: { type: [Boolean, String, Number], default: void 0 },
    description: { type: [String], default: void 0 },
    disabled: { type: Boolean, default: !1 },
    feedbackAriaLive: { type: String, default: "assertive" },
    id: { type: String, default: void 0 },
    invalidFeedback: { type: String, default: void 0 },
    label: { type: String, default: void 0 },
    labelAlign: { type: [Boolean, String, Number], default: void 0 },
    labelAlignLg: { type: [Boolean, String, Number], default: void 0 },
    labelAlignMd: { type: [Boolean, String, Number], default: void 0 },
    labelAlignSm: { type: [Boolean, String, Number], default: void 0 },
    labelAlignXl: { type: [Boolean, String, Number], default: void 0 },
    labelClass: { type: [Array, Object, String], default: void 0 },
    labelCols: { type: [Boolean, String, Number], default: void 0 },
    labelColsLg: { type: [Boolean, String, Number], default: void 0 },
    labelColsMd: { type: [Boolean, String, Number], default: void 0 },
    labelColsSm: { type: [Boolean, String, Number], default: void 0 },
    labelColsXl: { type: [Boolean, String, Number], default: void 0 },
    labelFor: { type: String, default: void 0 },
    labelSize: { type: String, default: void 0 },
    labelSrOnly: { type: Boolean, default: !1 },
    state: { type: Boolean, default: null },
    tooltip: { type: Boolean, default: !1 },
    validFeedback: { type: String, default: void 0 },
    validated: { type: Boolean, default: !1 },
    floating: { type: Boolean, default: !1 }
  },
  setup(a) {
    const r = ["xs", "sm", "md", "lg", "xl"], n = (S, b) => r.reduce((y, x) => {
      const B = Ed(x === "xs" ? "" : x, `${b}Align`), E = S[B] || null;
      return E && (x === "xs" ? y.push(`text-${E}`) : y.push(`text-${x}-${E}`)), y;
    }, []), o = (S, b) => (
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
      r.reduce((y, x) => {
        const B = Ed(x === "xs" ? "" : x, `${b}Cols`);
        let E = S[B];
        if (E = E === "" ? !0 : E || !1, typeof E != "boolean" && E !== "auto") {
          const F = Number.parseInt(E);
          E = Number.isNaN(F) ? 0 : F, E = E > 0 ? E : !1;
        }
        return E && (x === "xs" ? y[typeof E == "boolean" ? "col" : "cols"] = E : y[x || (typeof E == "boolean" ? "col" : "cols")] = E), y;
      }, {})
    ), l = te(null), s = (S, b = null) => {
      if (Oc && a.labelFor && l.value !== null) {
        const y = l.value.querySelector(`#${CSS.escape(a.labelFor)}`);
        if (y) {
          const x = "aria-describedby", B = (S || "").split(Ol), E = (b || "").split(Ol), F = (y.getAttribute(x) || "").split(Ol).filter((L) => !E.includes(L)).concat(B).filter((L, $, k) => k.indexOf(L) === $).filter((L) => L).join(" ").trim();
          F ? y.setAttribute(x, F) : y.removeAttribute(x);
        }
      }
    }, f = I(() => o(a, "content")), d = I(() => n(a, "label")), p = I(() => o(a, "label")), g = I(
      () => (
        // Determine if the form group will be rendered horizontal
        // based on the existence of 'content-col' or 'label-col' props
        Object.keys(f.value).length > 0 || Object.keys(p.value).length > 0
      )
    ), m = Ca(() => a.state), w = Jo(
      () => a.ariaInvalid,
      () => a.state
    );
    return Ue(
      () => null,
      (S, b) => {
        S !== b && s(S, b);
      }
    ), xa(() => {
      Mn(() => {
        s(null);
      });
    }), {
      ariaDescribedby: null,
      computedAriaInvalid: w,
      contentColProps: f,
      isHorizontal: g,
      labelAlignClasses: d,
      labelColProps: p,
      onLegendClick: (S) => {
        if (a.labelFor || l.value === null)
          return;
        const { target: b } = S, y = b ? b.tagName : "";
        if ($x.indexOf(y) !== -1)
          return;
        const x = [...l.value.querySelectorAll(Ox)].filter(hw), [B] = x;
        x.length === 1 && B instanceof HTMLElement && gw(B);
      },
      stateClass: m
    };
  },
  render() {
    const a = this.$props, r = this.$slots, n = et(), o = !a.labelFor;
    let l = null;
    const s = ha(Rx, {}, r) || a.label, f = s ? et(void 0, "_BV_label_").value : null;
    if (s || this.isHorizontal) {
      const $ = o ? "legend" : "label";
      if (a.labelSrOnly)
        s && (l = ut(
          $,
          {
            class: "visually-hidden",
            id: f,
            for: a.labelFor || null
          },
          s
        )), this.isHorizontal ? l = ut(ya, this.labelColProps, { default: () => l }) : l = ut("div", {}, [l]);
      else {
        const k = {
          onClick: o ? this.onLegendClick : null,
          ...this.isHorizontal ? this.labelColProps : {},
          tag: this.isHorizontal ? $ : null,
          id: f,
          for: a.labelFor || null,
          tabIndex: o ? "-1" : null,
          class: [
            this.isHorizontal ? "col-form-label" : "form-label",
            {
              "bv-no-focus-ring": o,
              "col-form-label": this.isHorizontal || o,
              "pt-0": !this.isHorizontal && o,
              "d-block": !this.isHorizontal && !o,
              [`col-form-label-${a.labelSize}`]: !!a.labelSize
            },
            this.labelAlignClasses,
            a.labelClass
          ]
        };
        this.isHorizontal ? l = ut(ya, k, { default: () => s }) : l = ut($, k, s);
      }
    }
    let d = null;
    const p = ha(Fx, {}, r) || this.invalidFeedback, g = p ? et(void 0, "_BV_feedback_invalid_").value : void 0;
    p && (d = ut(
      Pd,
      {
        ariaLive: a.feedbackAriaLive,
        id: g,
        state: a.state,
        tooltip: a.tooltip
      },
      { default: () => p }
    ));
    let m = null;
    const w = ha(Px, {}, r) || this.validFeedback, S = w ? et(void 0, "_BV_feedback_valid_").value : void 0;
    w && (m = ut(
      Id,
      {
        ariaLive: a.feedbackAriaLive,
        id: S,
        state: a.state,
        tooltip: a.tooltip
      },
      { default: () => w }
      // validFeedbackContent
    ));
    let b = null;
    const y = ha(Lx, {}, r) || this.description, x = y ? et(void 0, "_BV_description_").value : void 0;
    y && (b = ut(
      Ld,
      {
        id: x
      },
      { default: () => y }
    ));
    const B = this.ariaDescribedby = [
      x,
      a.state === !1 ? g : null,
      a.state === !0 ? S : null
    ].filter(($) => $).join(" ") || null, E = [
      ha(Ix, { ariaDescribedby: B, descriptionId: x, id: n, labelId: f }, r) || "",
      d,
      m,
      b
    ];
    !this.isHorizontal && a.floating && E.push(l);
    let F = ut(
      "div",
      {
        ref: "content",
        class: [
          {
            "form-floating": !this.isHorizontal && a.floating
          }
        ]
      },
      E
    );
    this.isHorizontal && (F = ut(ya, { ref: "content", ...this.contentColProps }, { default: () => E }));
    const L = {
      class: [
        this.stateClass,
        {
          "was-validated": a.validated
        }
      ],
      id: et(() => a.id).value,
      disabled: o ? a.disabled : null,
      role: o ? null : "group",
      "aria-invalid": this.computedAriaInvalid,
      // Only apply `aria-labelledby` if we are a horizontal fieldset
      // as the legend is no longer a direct child of fieldset
      "aria-labelledby": o && this.isHorizontal ? f : null
    };
    return this.isHorizontal && !o ? ut(Rl, L, { default: () => [l, F] }) : ut(
      o ? "fieldset" : "div",
      L,
      this.isHorizontal && o ? [ut(Rl, null, { default: () => [l, F] })] : this.isHorizontal || !a.floating ? [l, F] : [F]
    );
  }
}), Vx = ["id", "value", "name", "form", "type", "disabled", "placeholder", "required", "autocomplete", "readonly", "min", "max", "step", "list", "aria-required", "aria-invalid"], TE = /* @__PURE__ */ ne({
  __name: "BFormInput",
  props: {
    max: { default: void 0 },
    min: { default: void 0 },
    step: { default: void 0 },
    type: { default: "text" },
    ariaInvalid: { type: [Boolean, String], default: void 0 },
    autocomplete: { default: void 0 },
    autofocus: { type: Boolean, default: !1 },
    disabled: { type: Boolean, default: !1 },
    form: { default: void 0 },
    formatter: { type: Function, default: void 0 },
    id: { default: void 0 },
    lazy: { type: Boolean, default: !1 },
    lazyFormatter: { type: Boolean, default: !1 },
    list: { default: void 0 },
    modelValue: { default: "" },
    name: { default: void 0 },
    number: { type: Boolean, default: !1 },
    placeholder: { default: void 0 },
    plaintext: { type: Boolean, default: !1 },
    readonly: { type: Boolean, default: !1 },
    required: { type: Boolean, default: !1 },
    size: { default: void 0 },
    state: { type: [Boolean, null], default: null },
    trim: { type: Boolean, default: !1 },
    debounce: { default: 0 },
    debounceMaxWait: { default: NaN }
  },
  emits: ["update:modelValue"],
  setup(a, { expose: r, emit: n }) {
    const o = a, l = n, { input: s, computedId: f, computedAriaInvalid: d, onInput: p, onChange: g, onBlur: m, focus: w, blur: S } = pp(o, l), b = Ca(() => o.state), y = te(!1), x = I(() => {
      const B = o.type === "range", E = o.type === "color";
      return [
        b.value,
        {
          "form-control-highlighted": y.value,
          "form-range": B,
          "form-control": E || !o.plaintext && !B,
          "form-control-color": E,
          "form-control-plaintext": o.plaintext && !B && !E,
          [`form-control-${o.size}`]: !!o.size
        }
      ];
    });
    return r({
      blur: S,
      element: s,
      focus: w
    }), (B, E) => (P(), j("input", {
      id: K(f),
      ref_key: "input",
      ref: s,
      value: B.modelValue,
      class: Q(x.value),
      name: B.name || void 0,
      form: B.form || void 0,
      type: B.type,
      disabled: o.disabled,
      placeholder: B.placeholder,
      required: o.required || void 0,
      autocomplete: B.autocomplete || void 0,
      readonly: o.readonly || o.plaintext,
      min: B.min,
      max: B.max,
      step: B.step,
      list: B.type !== "password" ? B.list : void 0,
      "aria-required": o.required || void 0,
      "aria-invalid": K(d),
      onInput: E[0] || (E[0] = (F) => K(p)(F)),
      onChange: E[1] || (E[1] = (F) => K(g)(F)),
      onBlur: E[2] || (E[2] = (F) => K(m)(F))
    }, null, 42, Vx));
  }
}), Dx = ["id", "disabled", "required", "name", "form", "aria-label", "aria-labelledby", "value", "aria-required"], zx = ["for"], Sp = /* @__PURE__ */ ne({
  inheritAttrs: !1,
  __name: "BFormRadio",
  props: /* @__PURE__ */ ct({
    ariaLabel: { default: void 0 },
    ariaLabelledby: { default: void 0 },
    autofocus: { type: Boolean, default: !1 },
    button: { type: Boolean, default: !1 },
    buttonGroup: { type: Boolean, default: !1 },
    buttonVariant: { default: null },
    disabled: { type: Boolean, default: !1 },
    form: { default: void 0 },
    id: { default: void 0 },
    inline: { type: Boolean, default: !1 },
    modelValue: { type: [Boolean, String, Array, Object, Number, null] },
    name: { default: void 0 },
    plain: { type: Boolean, default: !1 },
    required: { type: Boolean, default: !1 },
    reverse: { type: Boolean, default: !1 },
    size: { default: void 0 },
    state: { type: [Boolean, null], default: null },
    value: { type: [Boolean, String, Array, Object, Number, null], default: !0 }
  }, {
    modelValue: { type: [Boolean, String, Array, Object, Number, null], default: void 0 },
    modelModifiers: {}
  }),
  emits: ["update:modelValue"],
  setup(a, { expose: r }) {
    const n = a, o = fr(), l = Lt(a, "modelValue"), s = et(() => n.id, "form-check"), f = Gt(rp, null), d = te(null), { focused: p } = fn(d, {
      initialValue: n.autofocus
    }), g = U(() => !Un(o.default)), m = I({
      get: () => (f == null ? void 0 : f.modelValue.value) ?? l.value,
      set: (E) => {
        if (E !== void 0) {
          if (f !== null) {
            f.modelValue.value = E;
            return;
          }
          l.value = E;
        }
      }
    }), w = U(
      () => !!(n.name ?? (f == null ? void 0 : f.name.value)) && (n.required || (f == null ? void 0 : f.required.value))
    ), S = U(() => n.buttonGroup || ((f == null ? void 0 : f.buttons.value) ?? !1)), b = I(() => ({
      plain: n.plain || ((f == null ? void 0 : f.plain.value) ?? !1),
      button: n.button || ((f == null ? void 0 : f.buttons.value) ?? !1),
      inline: n.inline || ((f == null ? void 0 : f.inline.value) ?? !1),
      state: n.state || (f == null ? void 0 : f.state.value),
      reverse: n.reverse || ((f == null ? void 0 : f.reverse.value) ?? !1),
      size: n.size ?? (f == null ? void 0 : f.size.value) ?? "md",
      // This is where the true default is made
      buttonVariant: n.buttonVariant ?? (f == null ? void 0 : f.buttonVariant.value) ?? "secondary",
      // This is where the true default is made
      hasDefaultSlot: g.value
    })), y = sp(b), x = up(b), B = fp(b);
    return r({
      blur: () => {
        p.value = !1;
      },
      element: d,
      focus: () => {
        p.value = !0;
      }
    }), (E, F) => (P(), X(wp, {
      skip: S.value,
      class: Q(K(y))
    }, {
      default: ee(() => {
        var L, $, k;
        return [
          Wr(je("input", xe({ id: K(s) }, E.$attrs, {
            ref_key: "input",
            ref: d,
            "onUpdate:modelValue": F[0] || (F[0] = (z) => m.value = z),
            class: K(x),
            type: "radio",
            disabled: n.disabled || ((L = K(f)) == null ? void 0 : L.disabled.value),
            required: w.value || void 0,
            name: E.name || (($ = K(f)) == null ? void 0 : $.name.value),
            form: E.form || ((k = K(f)) == null ? void 0 : k.form.value),
            "aria-label": E.ariaLabel,
            "aria-labelledby": E.ariaLabelledby,
            value: E.value,
            "aria-required": w.value || void 0
          }), null, 16, Dx), [
            [nw, m.value]
          ]),
          g.value || n.plain === !1 ? (P(), j("label", {
            key: 0,
            for: K(s),
            class: Q(K(B))
          }, [
            W(E.$slots, "default")
          ], 10, zx)) : $e("", !0)
        ];
      }),
      _: 3
    }, 8, ["skip", "class"]));
  }
}), Mx = ["id"], Hx = ["innerHTML"], Ux = /* @__PURE__ */ ne({
  __name: "BFormRadioGroup",
  props: /* @__PURE__ */ ct({
    ariaInvalid: { type: [Boolean, String], default: void 0 },
    autofocus: { type: Boolean, default: !1 },
    buttonVariant: { default: "secondary" },
    buttons: { type: Boolean, default: !1 },
    disabled: { type: Boolean, default: !1 },
    disabledField: { default: "disabled" },
    form: { default: void 0 },
    htmlField: { default: "html" },
    id: { default: void 0 },
    modelValue: { type: [Boolean, String, Array, Object, Number, null] },
    name: { default: void 0 },
    options: { default: () => [] },
    plain: { type: Boolean, default: !1 },
    required: { type: Boolean, default: !1 },
    reverse: { type: Boolean, default: !1 },
    size: { default: "md" },
    stacked: { type: Boolean, default: !1 },
    state: { type: [Boolean, null], default: null },
    textField: { default: "text" },
    validated: { type: Boolean, default: !1 },
    valueField: { default: "value" }
  }, {
    modelValue: { type: [Boolean, String, Array, Object, Number, null], default: null },
    modelModifiers: {}
  }),
  emits: ["update:modelValue"],
  setup(a, { expose: r }) {
    const n = a, o = Lt(a, "modelValue"), l = et(() => n.id, "radio"), s = et(() => n.name, "checkbox"), f = te(null), { focused: d } = fn(f, {
      initialValue: n.autofocus
    });
    Sa(rp, {
      modelValue: o,
      buttonVariant: U(() => n.buttonVariant),
      form: U(() => n.form),
      name: s,
      buttons: U(() => n.buttons),
      state: U(() => n.state),
      plain: U(() => n.plain),
      size: U(() => n.size),
      inline: U(() => !n.stacked),
      reverse: U(() => n.reverse),
      required: U(() => n.required),
      disabled: U(() => n.disabled)
    });
    const p = I(
      () => n.options.map(
        (S, b) => typeof S == "string" || typeof S == "number" ? {
          value: S,
          disabled: n.disabled,
          text: S.toString(),
          html: void 0,
          self: Symbol(`radioGroupOptionItem${b}`)
        } : {
          value: S[n.valueField],
          disabled: S[n.disabledField],
          ...S.props ? S.props : {},
          text: S[n.textField],
          html: S[n.htmlField],
          self: Symbol(`radioGroupOptionItem${b}`)
        }
      )
    ), g = I(() => ({
      required: n.required,
      ariaInvalid: n.ariaInvalid,
      state: n.state,
      validated: n.validated,
      buttons: n.buttons,
      stacked: n.stacked,
      size: n.size
    })), m = dp(g), w = cp(g);
    return r({
      blur: () => {
        d.value = !1;
      },
      focus: () => {
        d.value = !0;
      }
    }), (S, b) => (P(), j("div", xe(K(m), {
      id: K(l),
      ref_key: "element",
      ref: f,
      role: "radiogroup",
      class: [K(w), "bv-no-focus-ring"],
      tabindex: "-1"
    }), [
      W(S.$slots, "first"),
      (P(!0), j(rt, null, ur(p.value, (y) => (P(), X(Sp, {
        key: y.self,
        disabled: y.disabled,
        value: y.value
      }, {
        default: ee(() => [
          y.html ? (P(), j("span", {
            key: 0,
            innerHTML: y.html
          }, null, 8, Hx)) : (P(), j(rt, { key: 1 }, [
            Te(Ee(y.text), 1)
          ], 64))
        ]),
        _: 2
      }, 1032, ["disabled", "value"]))), 128)),
      W(S.$slots, "default")
    ], 16, Mx));
  }
}), Wx = ["value", "disabled"], Bp = /* @__PURE__ */ ne({
  __name: "BFormSelectOption",
  props: {
    disabled: { type: Boolean, default: !1 },
    value: { default: void 0 }
  },
  setup(a) {
    const r = a;
    return (n, o) => (P(), j("option", {
      value: n.value,
      disabled: r.disabled
    }, [
      W(n.$slots, "default")
    ], 8, Wx));
  }
}), qx = ["label"], Gx = ["innerHTML"], jx = /* @__PURE__ */ ne({
  __name: "BFormSelectOptionGroup",
  props: {
    disabledField: { default: "disabled" },
    htmlField: { default: "html" },
    label: { default: void 0 },
    options: { default: () => [] },
    textField: { default: "text" },
    valueField: { default: "value" }
  },
  setup(a) {
    const r = a, { normalizedOptions: n } = vp(() => r.options, r), o = I(() => n.value);
    return (l, s) => (P(), j("optgroup", { label: l.label }, [
      W(l.$slots, "first"),
      (P(!0), j(rt, null, ur(o.value, (f, d) => (P(), X(Bp, xe({
        key: d,
        disabled: f.disabled,
        value: f.value,
        ref_for: !0
      }, l.$attrs), {
        default: ee(() => [
          f.html ? (P(), j("span", {
            key: 0,
            innerHTML: f.html
          }, null, 8, Gx)) : (P(), j(rt, { key: 1 }, [
            Te(Ee(f.text), 1)
          ], 64))
        ]),
        _: 2
      }, 1040, ["disabled", "value"]))), 128)),
      W(l.$slots, "default")
    ], 8, qx));
  }
}), Kx = ["id", "name", "form", "multiple", "size", "disabled", "required", "aria-required", "aria-invalid"], Jx = ["innerHTML"], EE = /* @__PURE__ */ ne({
  __name: "BFormSelect",
  props: /* @__PURE__ */ ct({
    ariaInvalid: { type: [Boolean, String], default: void 0 },
    autofocus: { type: Boolean, default: !1 },
    disabled: { type: Boolean, default: !1 },
    disabledField: { default: "disabled" },
    form: { default: void 0 },
    htmlField: { default: "html" },
    id: { default: void 0 },
    labelField: { default: "label" },
    modelValue: { type: [Boolean, String, Array, Object, Number, null] },
    multiple: { type: Boolean, default: !1 },
    name: { default: void 0 },
    options: { default: () => [] },
    optionsField: { default: "options" },
    plain: { type: Boolean, default: !1 },
    required: { type: Boolean, default: !1 },
    selectSize: { default: 0 },
    size: { default: "md" },
    state: { type: [Boolean, null], default: null },
    textField: { default: "text" },
    valueField: { default: "value" }
  }, {
    modelValue: {
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
      default: ""
    },
    modelModifiers: {}
  }),
  emits: ["update:modelValue"],
  setup(a, { expose: r }) {
    const n = a, o = Lt(a, "modelValue"), l = et(() => n.id, "input"), s = Ot(() => n.selectSize), f = Ca(() => n.state), d = te(null), { focused: p } = fn(d, {
      initialValue: n.autofocus
    }), g = I(() => [
      f.value,
      {
        "form-control": n.plain,
        [`form-control-${n.size}`]: n.size !== "md" && n.plain,
        "form-select": !n.plain,
        [`form-select-${n.size}`]: n.size !== "md" && !n.plain
      }
    ]), m = U(
      () => s.value || n.plain ? s.value : void 0
    ), w = Jo(
      () => n.ariaInvalid,
      () => n.state
    ), { normalizedOptions: S, isComplex: b } = vp(() => n.options, n), y = I(
      () => S.value
    ), x = I({
      get: () => o.value,
      set: (B) => {
        o.value = B;
      }
    });
    return r({
      blur: () => {
        p.value = !1;
      },
      element: d,
      focus: () => {
        p.value = !0;
      }
    }), (B, E) => Wr((P(), j("select", {
      id: K(l),
      ref_key: "input",
      ref: d,
      "onUpdate:modelValue": E[0] || (E[0] = (F) => x.value = F),
      class: Q(g.value),
      name: B.name,
      form: B.form || void 0,
      multiple: n.multiple || void 0,
      size: m.value,
      disabled: n.disabled,
      required: n.required || void 0,
      "aria-required": n.required || void 0,
      "aria-invalid": K(w)
    }, [
      W(B.$slots, "first"),
      (P(!0), j(rt, null, ur(y.value, (F, L) => (P(), j(rt, { key: L }, [
        K(b)(F) ? (P(), X(jx, {
          key: 0,
          label: F.label,
          options: F.options,
          "value-field": B.valueField,
          "text-field": B.textField,
          "html-field": B.htmlField,
          "disabled-field": B.disabledField
        }, null, 8, ["label", "options", "value-field", "text-field", "html-field", "disabled-field"])) : (P(), X(Bp, {
          key: 1,
          value: F.value,
          disabled: F.disabled
        }, {
          default: ee(() => [
            F.html ? (P(), j("span", {
              key: 0,
              innerHTML: F.html
            }, null, 8, Jx)) : (P(), j(rt, { key: 1 }, [
              Te(Ee(F.text), 1)
            ], 64))
          ]),
          _: 2
        }, 1032, ["value", "disabled"]))
      ], 64))), 128)),
      W(B.$slots, "default")
    ], 10, Kx)), [
      [ew, x.value]
    ]);
  }
}), Xx = ["id", "name", "form", "value", "disabled", "placeholder", "required", "autocomplete", "readonly", "aria-required", "aria-invalid", "rows", "wrap"], kE = /* @__PURE__ */ ne({
  __name: "BFormTextarea",
  props: {
    noResize: { type: Boolean, default: !1 },
    rows: { default: 2 },
    wrap: { default: "soft" },
    ariaInvalid: { type: [Boolean, String], default: void 0 },
    autocomplete: { default: void 0 },
    autofocus: { type: Boolean, default: !1 },
    disabled: { type: Boolean, default: !1 },
    form: { default: void 0 },
    formatter: { type: Function, default: void 0 },
    id: { default: void 0 },
    lazy: { type: Boolean, default: !1 },
    lazyFormatter: { type: Boolean, default: !1 },
    list: { default: void 0 },
    modelValue: { default: "" },
    name: { default: void 0 },
    number: { type: Boolean, default: !1 },
    placeholder: { default: void 0 },
    plaintext: { type: Boolean, default: !1 },
    readonly: { type: Boolean, default: !1 },
    required: { type: Boolean, default: !1 },
    size: { default: void 0 },
    state: { type: [Boolean, null], default: null },
    trim: { type: Boolean, default: !1 },
    debounce: { default: 0 },
    debounceMaxWait: { default: NaN }
  },
  emits: ["update:modelValue"],
  setup(a, { expose: r, emit: n }) {
    const o = a, l = n, { input: s, computedId: f, computedAriaInvalid: d, onInput: p, onChange: g, onBlur: m, focus: w, blur: S } = pp(o, l), b = Ca(() => o.state), y = I(() => [
      b.value,
      o.plaintext ? "form-control-plaintext" : "form-control",
      {
        [`form-control-${o.size}`]: !!o.size
      }
    ]), x = I(() => ({
      resize: o.noResize ? "none" : void 0
    }));
    return r({
      blur: S,
      element: s,
      focus: w
    }), (B, E) => (P(), j("textarea", {
      id: K(f),
      ref_key: "input",
      ref: s,
      class: Q(y.value),
      name: B.name || void 0,
      form: B.form || void 0,
      value: B.modelValue ?? void 0,
      disabled: o.disabled,
      placeholder: B.placeholder,
      required: o.required || void 0,
      autocomplete: B.autocomplete || void 0,
      readonly: o.readonly || o.plaintext,
      "aria-required": B.required || void 0,
      "aria-invalid": K(d),
      rows: B.rows,
      style: dn(x.value),
      wrap: B.wrap || void 0,
      onInput: E[0] || (E[0] = (F) => K(p)(F)),
      onChange: E[1] || (E[1] = (F) => K(g)(F)),
      onBlur: E[2] || (E[2] = (F) => K(m)(F))
    }, null, 46, Xx));
  }
}), OE = {
  mounted(a, r) {
    const n = Ql(r.value);
    if (!n)
      return;
    const o = Sd(r.value, a);
    !o.content && !o.title || Ad(a, r, {
      noninteractive: !0,
      ...Bd(r, a),
      title: o.title ?? o.content ?? "",
      tooltip: n
    });
  },
  updated(a, r) {
    const n = Ql(r.value);
    if (!n)
      return;
    const o = Sd(r.value, a);
    !o.content && !o.title || (Cd(a), Ad(a, r, {
      noninteractive: !0,
      ...Bd(r, a),
      title: o.title ?? o.content ?? "",
      tooltip: n
    }));
  },
  beforeUnmount(a) {
    Cd(a);
  }
}, Yx = "300px", $E = /* @__PURE__ */ ne({
  __name: "BTableSimple",
  props: {
    bordered: { type: Boolean, default: !1 },
    borderless: { type: Boolean, default: !1 },
    borderVariant: { default: null },
    captionTop: { type: Boolean, default: !1 },
    dark: { type: Boolean, default: !1 },
    fixed: { type: Boolean, default: !1 },
    hover: { type: Boolean, default: !1 },
    id: { default: void 0 },
    noBorderCollapse: { type: Boolean, default: !1 },
    outlined: { type: Boolean, default: !1 },
    responsive: { type: [Boolean, String], default: !1 },
    small: { type: Boolean, default: !1 },
    stacked: { type: [Boolean, String], default: !1 },
    stickyHeader: { type: [Boolean, String, Number], default: !1 },
    striped: { type: Boolean, default: !1 },
    stripedColumns: { type: Boolean, default: !1 },
    tableClass: { default: void 0 },
    variant: { default: null }
  },
  setup(a) {
    const r = a, n = I(() => [
      r.tableClass,
      "table",
      "b-table",
      {
        "table-bordered": r.bordered,
        "table-borderless": r.borderless,
        [`border-${r.borderVariant}`]: r.borderVariant !== null,
        "caption-top": r.captionTop,
        "table-dark": r.dark,
        "table-hover": r.hover,
        "b-table-stacked": r.stacked === !0,
        [`b-table-stacked-${r.stacked}`]: typeof r.stacked == "string",
        "table-striped": r.striped,
        "table-sm": r.small,
        [`table-${r.variant}`]: r.variant !== null,
        "table-striped-columns": r.stripedColumns
      }
    ]), o = I(() => ({
      id: r.id,
      class: n.value
    })), l = Z1(
      I(
        () => (r.stickyHeader === !0 ? Yx : r.stickyHeader) || NaN
      )
    ), s = I(() => r.stickyHeader !== !1), f = I(() => r.responsive !== !1 || s.value), d = I(
      () => s.value ? { maxHeight: l.value } : void 0
    ), p = I(() => ({
      "table-responsive": r.responsive === !0,
      [`table-responsive-${r.responsive}`]: typeof r.responsive == "string",
      "b-table-sticky-header": s.value
    }));
    return (g, m) => f.value ? (P(), j("div", {
      key: 0,
      class: Q(p.value),
      style: dn(d.value)
    }, [
      je("table", Wt(An(o.value)), [
        W(g.$slots, "default")
      ], 16)
    ], 6)) : (P(), j("table", Wt(xe({ key: 1 }, o.value)), [
      W(g.$slots, "default")
    ], 16));
  }
}), Nd = Qo("cols", [""], { type: [String, Number], default: null });
ne({
  name: "BRow",
  slots: Object,
  props: {
    tag: { type: String, default: "div" },
    gutterX: { type: String, default: null },
    gutterY: { type: String, default: null },
    noGutters: { type: Boolean, default: !1 },
    alignV: { type: String, default: null },
    alignH: { type: String, default: null },
    alignContent: { type: String, default: null },
    ...Nd
  },
  setup(a) {
    const r = kc(() => a.alignH), n = I(() => Qc(a, Nd, "cols", "row-cols"));
    return {
      computedClasses: I(() => [
        n.value,
        {
          [`gx-${a.gutterX}`]: a.gutterX !== null,
          [`gy-${a.gutterY}`]: a.gutterY !== null,
          "g-0": a.noGutters,
          [`align-items-${a.alignV}`]: a.alignV !== null,
          [r.value]: a.alignH !== null,
          [`align-content-${a.alignContent}`]: a.alignContent !== null
        }
      ])
    };
  }
});
const RE = /* @__PURE__ */ ne({
  __name: "BTbody",
  props: {
    variant: { default: null }
  },
  setup(a) {
    const r = a, n = I(() => ({
      [`thead-${r.variant}`]: r.variant !== null
    }));
    return (o, l) => (P(), j("tbody", {
      class: Q(n.value)
    }, [
      W(o.$slots, "default")
    ], 2));
  }
}), Zx = ["scope", "colspan", "rowspan", "data-label"], Qx = { key: 0 }, FE = /* @__PURE__ */ ne({
  __name: "BTd",
  props: {
    colspan: { default: void 0 },
    rowspan: { default: void 0 },
    stackedHeading: { default: void 0 },
    stickyColumn: { type: Boolean, default: !1 },
    variant: { default: null }
  },
  setup(a) {
    const r = a, n = I(() => ({
      [`table-${r.variant}`]: r.variant !== null,
      "b-table-sticky-column": r.stickyColumn,
      "table-b-table-default": r.stickyColumn && r.variant === null
    })), o = U(() => r.colspan ? "colspan" : r.rowspan ? "rowspan" : "col");
    return (l, s) => (P(), j("td", {
      scope: o.value,
      class: Q(n.value),
      colspan: l.colspan,
      rowspan: l.rowspan,
      "data-label": l.stackedHeading
    }, [
      l.stackedHeading ? (P(), j("div", Qx, [
        W(l.$slots, "default")
      ])) : W(l.$slots, "default", { key: 1 })
    ], 10, Zx));
  }
}), PE = /* @__PURE__ */ ne({
  __name: "BTfoot",
  props: {
    variant: { default: null }
  },
  setup(a) {
    const r = a, n = I(() => ({
      [`table-${r.variant}`]: r.variant !== null
    }));
    return (o, l) => (P(), j("tfoot", {
      class: Q(n.value)
    }, [
      W(o.$slots, "default")
    ], 2));
  }
}), eS = ["scope", "colspan", "rowspan", "data-label"], tS = { key: 0 }, LE = /* @__PURE__ */ ne({
  __name: "BTh",
  props: {
    colspan: { default: void 0 },
    rowspan: { default: void 0 },
    stackedHeading: { default: void 0 },
    stickyColumn: { type: Boolean, default: !1 },
    variant: { default: null }
  },
  setup(a) {
    const r = a, n = I(() => ({
      [`table-${r.variant}`]: r.variant !== null,
      "b-table-sticky-column": r.stickyColumn,
      "table-b-table-default": r.stickyColumn && r.variant === null
    })), o = U(() => r.colspan ? "colspan" : r.rowspan ? "rowspan" : "col");
    return (l, s) => (P(), j("th", {
      scope: o.value,
      class: Q(n.value),
      colspan: l.colspan,
      rowspan: l.rowspan,
      "data-label": l.stackedHeading
    }, [
      l.stackedHeading !== void 0 ? (P(), j("div", tS, [
        W(l.$slots, "default")
      ])) : W(l.$slots, "default", { key: 1 })
    ], 10, eS));
  }
}), IE = /* @__PURE__ */ ne({
  __name: "BThead",
  props: {
    variant: { default: null }
  },
  setup(a) {
    const r = a, n = I(() => ({
      [`table-${r.variant}`]: r.variant !== null
    }));
    return (o, l) => (P(), j("thead", {
      class: Q(n.value)
    }, [
      W(o.$slots, "default")
    ], 2));
  }
}), NE = /* @__PURE__ */ ne({
  __name: "BTr",
  props: {
    variant: { default: null }
  },
  setup(a) {
    const r = a, n = I(() => ({
      [`table-${r.variant}`]: r.variant !== null
    }));
    return (o, l) => (P(), j("tr", {
      class: Q(n.value)
    }, [
      W(o.$slots, "default")
    ], 2));
  }
}), VE = /* @__PURE__ */ ne({
  inheritAttrs: !1,
  __name: "BTab",
  props: /* @__PURE__ */ ct({
    active: { type: Boolean },
    buttonId: { default: void 0 },
    disabled: { type: Boolean, default: !1 },
    id: { default: void 0 },
    lazy: { type: Boolean, default: void 0 },
    lazyOnce: { type: Boolean, default: void 0 },
    noBody: { type: Boolean, default: !1 },
    tag: { default: "div" },
    title: { default: void 0 },
    titleItemClass: { default: void 0 },
    titleLinkAttrs: { default: void 0 },
    titleLinkClass: { default: void 0 }
  }, {
    active: { type: Boolean, default: !1 },
    activeModifiers: {}
  }),
  emits: ["update:active"],
  setup(a) {
    const r = a, n = fr(), o = Lt(a, "active"), l = Gt(ep, null), s = et(() => r.id, "tabpane"), f = et(() => r.buttonId, "tab"), d = te(!1), p = te(null), { onClick: g, ...m } = fs(), w = I(
      () => ({
        id: s.value,
        buttonId: f.value,
        disabled: r.disabled,
        title: r.title,
        titleComponent: n.title,
        titleItemClass: r.titleItemClass,
        titleLinkAttrs: r.titleLinkAttrs,
        titleLinkClass: r.titleLinkClass,
        onClick: g,
        el: p.value
      })
    );
    xa(() => {
      l && (l.registerTab(w), r.active && l.activateTab(s.value));
    }), iw(() => {
      l && l.unregisterTab(s.value);
    });
    const S = U(() => (l == null ? void 0 : l.activeId.value) === s.value), b = te(S.value), y = U(() => !!(l != null && l.lazy.value || (r.lazyOnce ?? r.lazy))), x = U(() => r.lazyOnce !== void 0), B = U(() => S.value && !r.disabled), E = U(
      () => B.value || !y.value || y.value && x.value && d.value
    );
    Ue(S, (L) => {
      if (L) {
        o.value = !0, setTimeout(() => {
          b.value = !0;
        }, 0);
        return;
      }
      b.value = !1, o.value = !1;
    }), Ue(
      () => r.active,
      (L) => {
        if (l) {
          if (!L) {
            S.value && l.activateTab(void 0);
            return;
          }
          l.activateTab(s.value);
        }
      }
    );
    const F = I(() => [
      {
        active: S.value,
        show: b.value,
        "card-body": (l == null ? void 0 : l.card.value) && r.noBody === !1,
        fade: !(l != null && l.noFade.value)
      },
      b.value ? l == null ? void 0 : l.activeTabClass : l == null ? void 0 : l.inactiveTabClass,
      l == null ? void 0 : l.tabClass
    ]);
    return Ue(E, (L) => {
      L && !d.value && (d.value = !0);
    }), (L, $) => (P(), X(Ve(L.tag), xe({
      id: K(s),
      ref_key: "el",
      ref: p,
      class: ["tab-pane", F.value],
      role: "tabpanel",
      "aria-labelledby": K(f)
    }, m), {
      default: ee(() => [
        E.value ? W(L.$slots, "default", { key: 0 }) : $e("", !0)
      ]),
      _: 3
    }, 16, ["id", "class", "aria-labelledby"]));
  }
}), nS = ["aria-orientation"], rS = ["id", "aria-controls", "aria-selected", "onClick"], DE = /* @__PURE__ */ ne({
  __name: "BTabs",
  props: /* @__PURE__ */ ct({
    activeId: {},
    activeNavItemClass: { default: void 0 },
    activeTabClass: { default: void 0 },
    align: { default: void 0 },
    card: { type: Boolean, default: !1 },
    contentClass: { default: void 0 },
    end: { type: Boolean, default: !1 },
    fill: { type: Boolean, default: !1 },
    id: { default: void 0 },
    inactiveNavItemClass: { default: void 0 },
    inactiveTabClass: { default: void 0 },
    justified: { type: Boolean, default: !1 },
    lazy: { type: Boolean, default: !1 },
    modelValue: {},
    navClass: { default: void 0 },
    navItemClass: { default: void 0 },
    navWrapperClass: { default: void 0 },
    noFade: { type: Boolean, default: !1 },
    noNavStyle: { type: Boolean, default: !1 },
    pills: { type: Boolean, default: !1 },
    small: { type: Boolean, default: !1 },
    tag: { default: "div" },
    tabClass: { default: void 0 },
    vertical: { type: Boolean, default: !1 }
  }, {
    modelValue: {
      default: -1
    },
    modelModifiers: {},
    activeId: {
      default: void 0
    },
    activeIdModifiers: {}
  }),
  emits: /* @__PURE__ */ ct(["activate-tab", "click"], ["update:modelValue", "update:activeId"]),
  setup(a, { emit: r }) {
    const n = a, o = r, l = Lt(a, "modelValue"), s = Lt(a, "activeId"), f = Xc(), d = te([]), p = I(
      () => d.value.map(($) => {
        const k = K($), z = k.id === s.value;
        return {
          ...k,
          active: z,
          navItemClasses: [
            {
              active: z,
              disabled: k.disabled
            },
            z ? n.activeNavItemClass : n.inactiveNavItemClass,
            n.navItemClass,
            k.titleLinkClass
          ]
        };
      })
    ), g = U(() => !(p != null && p.value && p.value.length > 0)), m = I(() => ({
      "d-flex": n.vertical,
      "align-items-start": n.vertical
    })), w = kc(() => n.align), S = I(() => ({
      "nav-pills": n.pills,
      "flex-column me-3": n.vertical,
      [w.value]: n.align !== void 0,
      "nav-fill": n.fill,
      "card-header-tabs": n.card,
      "nav-justified": n.justified,
      "nav-tabs": !n.noNavStyle && !n.pills,
      small: n.small
    })), b = ($) => {
      var k;
      if ($ !== void 0) {
        const z = (k = p.value[$]) == null ? void 0 : k.id;
        if ($ > -1 && $ < p.value.length && !p.value[$].disabled && (l.value < 0 || s.value !== z || l.value !== $)) {
          const H = new ba("activate-tab", { cancelable: !0 });
          o("activate-tab", $, l.value, H), H.defaultPrevented || (s.value !== z && (s.value = z), l.value !== $ && (l.value = $));
        }
      }
    }, y = ($, k) => {
      var z, H, ie;
      b(k), k >= 0 && !p.value[k].disabled && (z = p.value[k]) != null && z.onClick && typeof p.value[k].onClick == "function" && ((ie = (H = p.value[k]).onClick) == null || ie.call(H, $));
    }, x = ($) => {
      var k, z;
      p.value.length <= 0 || (l.value = B(l.value + $, $), (z = document.getElementById((k = p.value[l.value]) == null ? void 0 : k.buttonId)) == null || z.focus());
    }, B = ($, k) => {
      let z = $, H = -1, ie = -1;
      for (let se = 0; se < p.value.length; se++)
        p.value[se].disabled || (H === -1 && (H = se), ie = se);
      for (; z >= H && z <= ie && p.value[z].disabled; )
        z += k;
      return z < H && (z = H), z > ie && (z = ie), z;
    };
    Ue(l, ($, k) => {
      if ($ === k || p.value.length <= 0)
        return;
      const z = B($, $ > k ? 1 : -1);
      Mn(() => {
        b(z);
      });
    }), Ue(s, ($, k) => {
      const z = p.value.findIndex((H) => H.id === $);
      if ($ !== k && !(p.value.length <= 0)) {
        if (z === -1) {
          b(B(0, 1));
          return;
        }
        b(z);
      }
    });
    const E = ($) => {
      d.value.find((k) => k.value.id === $.value.id) ? d.value[d.value.findIndex((k) => k.value.id === $.value.id)] = $ : d.value.push($), d.value.sort((k, z) => {
        if (!Node || !k.value.el || !z.value.el)
          return 0;
        const H = k.value.el.compareDocumentPosition(z.value.el);
        return H & Node.DOCUMENT_POSITION_FOLLOWING ? -1 : H & Node.DOCUMENT_POSITION_PRECEDING ? 1 : 0;
      });
    }, F = ($) => {
      d.value = d.value.filter((k) => k.value.id !== $);
    };
    Ue(
      d,
      () => {
        L();
      },
      { deep: !0 }
    );
    const L = () => {
      var $;
      if (p.value.length === 0) {
        l.value = -1, s.value = void 0;
        return;
      }
      if (l.value >= 0 && !s.value && (s.value = ($ = p.value[l.value]) == null ? void 0 : $.id), p.value.find((k) => k.id === s.value)) {
        b(p.value.findIndex((k) => k.id === s.value));
        return;
      }
      b(p.value.map((k) => !k.disabled).indexOf(!0));
    };
    return Sa(ep, {
      lazy: U(() => n.lazy),
      card: U(() => n.card),
      noFade: U(() => n.noFade),
      activeTabClass: U(() => n.activeTabClass),
      inactiveTabClass: U(() => n.inactiveTabClass),
      tabClass: U(() => n.tabClass),
      registerTab: E,
      unregisterTab: F,
      activeId: s,
      activateTab: ($) => {
        const k = p.value.findIndex((z) => z.id === $);
        if ($ === void 0 || k === -1) {
          b(B(0, 1));
          return;
        }
        b(k);
      }
    }), ($, k) => (P(), X(Ve($.tag), {
      id: $.id,
      class: Q(["tabs", m.value])
    }, {
      default: ee(() => [
        Rt(K(f).define, null, {
          default: ee(() => [
            je("div", {
              class: Q(["tab-content", $.contentClass])
            }, [
              W($.$slots, "default"),
              g.value ? (P(), j("div", {
                key: "bv-empty-tab",
                class: Q(["tab-pane active", { "card-body": n.card }])
              }, [
                W($.$slots, "empty")
              ], 2)) : $e("", !0)
            ], 2)
          ]),
          _: 3
        }),
        n.end ? (P(), X(K(f).reuse, { key: 0 })) : $e("", !0),
        je("div", {
          class: Q([$.navWrapperClass, { "card-header": n.card, "ms-auto": $.vertical && n.end }])
        }, [
          je("ul", {
            class: Q(["nav", [S.value, $.navClass]]),
            role: "tablist",
            "aria-orientation": $.vertical ? "vertical" : "horizontal"
          }, [
            W($.$slots, "tabs-start"),
            (P(!0), j(rt, null, ur(p.value, (z, H) => (P(), j("li", {
              key: z.id,
              class: Q(["nav-item", z.titleItemClass]),
              role: "presentation"
            }, [
              je("button", xe({
                id: z.buttonId,
                class: ["nav-link", z.navItemClasses],
                role: "tab",
                "aria-controls": z.id,
                "aria-selected": z.active,
                ref_for: !0
              }, z.titleLinkAttrs, {
                onKeydown: [
                  k[0] || (k[0] = Rr(ga((ie) => x(-1), ["stop", "prevent"]), ["left"])),
                  k[1] || (k[1] = Rr(ga((ie) => x(1), ["stop", "prevent"]), ["right"])),
                  k[2] || (k[2] = Rr(ga((ie) => x(-999), ["stop", "prevent"]), ["page-up"])),
                  k[3] || (k[3] = Rr(ga((ie) => x(999), ["stop", "prevent"]), ["page-down"]))
                ],
                onClick: ga((ie) => y(ie, H), ["stop", "prevent"])
              }), [
                z.titleComponent ? (P(), X(Ve(z.titleComponent), { key: 0 })) : (P(), j(rt, { key: 1 }, [
                  Te(Ee(z.title), 1)
                ], 64))
              ], 16, rS)
            ], 2))), 128)),
            W($.$slots, "tabs-end")
          ], 10, nS)
        ], 2),
        n.end ? $e("", !0) : (P(), X(K(f).reuse, { key: 1 }))
      ]),
      _: 3
    }, 8, ["id", "class"]));
  }
}), aS = "1200px", oS = "1116px", iS = "768px", lS = "1140px", sS = "696px", uS = "576px", fS = "992px", dS = "0", cS = "720px", pS = "516px", vS = "936px", gS = "540px", hS = "960px", mS = "1340px", yS = "1316px", bS = "1400px", _S = "12", wS = "24px", xS = "12px", SS = "16px", BS = "20px", AS = "24px", CS = "30px", TS = "40px", ES = "1", kS = "0.5", OS = "0", $S = ".08s", RS = ".2s", FS = ".5s", PS = "no-js", LS = "rgb(42, 40, 41)", IS = "rgb(239, 239, 239)", NS = {
  "vs-color-background-primary": "rgb(255, 255, 255)",
  "vs-color-background-secondary": "rgb(242, 242, 248)",
  "vs-color-background-bold": "rgb(32, 15, 46)",
  "vs-color-background-brand": "rgb(91, 36, 135)",
  "vs-color-background-inverse": "rgb(255, 255, 255)",
  "vs-color-background-highlight": "rgb(168, 48, 140)",
  "vs-color-background-information": "rgb(229, 229, 240)",
  "vs-color-background-success": "rgb(3, 170, 70)",
  "vs-color-background-warning": "rgb(253, 236, 83)",
  "vs-color-background-error": "rgb(148, 15, 44)",
  "vs-color-background-accent-heather-80": "rgb(51, 34, 93)",
  "vs-color-background-accent-heather-30": "rgb(163, 163, 204)",
  "vs-color-background-accent-tolsta-40": "rgb(20, 161, 158)",
  "vs-color-background-accent-gorse-05": "rgb(255, 250, 153)",
  "vs-color-border-primary": "rgb(233, 233, 233)",
  "vs-color-border-secondary": "rgb(163, 163, 204)",
  "vs-color-border-inverse": "rgb(255, 255, 255)",
  "vs-color-border-input": "rgb(83, 83, 150)",
  "vs-color-border-highlight": "rgb(168, 48, 140)",
  "vs-color-border-bold": "rgb(32, 15, 46)",
  "vs-color-border-success": "rgb(3, 170, 70)",
  "vs-color-border-warning": "rgb(253, 236, 83)",
  "vs-color-border-error": "rgb(148, 15, 44)",
  "vs-color-border-disabled": "rgb(144, 144, 144)",
  "vs-color-icon-primary": "rgb(32, 15, 46)",
  "vs-color-icon-secondary": "rgb(96, 96, 96)",
  "vs-color-icon-tertiary": "rgb(83, 83, 150)",
  "vs-color-icon-inverse": "rgb(255, 255, 255)",
  "vs-color-icon-cta-on-light": "rgb(31, 73, 214)",
  "vs-color-icon-highlight": "rgb(168, 48, 140)",
  "vs-color-icon-success": "rgb(3, 170, 70)",
  "vs-color-icon-warning": "rgb(253, 236, 83)",
  "vs-color-icon-error": "rgb(148, 15, 44)",
  "vs-color-icon-disabled": "rgb(144, 144, 144)",
  "vs-color-icon-accent-saltire-30": "rgb(133, 173, 255)",
  "vs-color-icon-ski-easy": "rgb(3, 135, 55)",
  "vs-color-icon-ski-intermediate": "rgb(23, 53, 156)",
  "vs-color-icon-ski-difficult": "rgb(194, 20, 58)",
  "vs-color-icon-ski-very-difficult": "rgb(51, 51, 51)",
  "vs-color-icon-ski-itineraries": "rgb(255, 173, 102)",
  "vs-color-icon-ski-other": "rgb(163, 163, 204)",
  "vs-color-icon-accent-loch-ness": "rgb(18, 43, 128)",
  "vs-color-icon-accent-grey-02": "rgb(117, 109, 148)",
  "vs-color-icon-accent-saltire": "rgb(31, 73, 214)",
  "vs-color-icon-accent-bramble": "rgb(168, 48, 140)",
  "vs-color-icon-accent-heather": "rgb(35, 1, 74)",
  "vs-color-icon-accent-whisky": "rgb(241, 135, 1)",
  "vs-color-icon-accent-cranachan": "rgb(255, 71, 111)",
  "vs-color-icon-accent-buachaille": "rgb(255, 212, 0)",
  "vs-color-interaction-focus": "rgb(51, 105, 255)",
  "vs-color-interaction-focus-on-bold": "rgb(255, 250, 153)",
  "vs-color-interaction-cta-primary": "rgb(31, 73, 214)",
  "vs-color-interaction-cta-secondary": "rgb(255, 255, 255)",
  "vs-color-interaction-cta-hover": "rgb(23, 53, 156)",
  "vs-color-interaction-cta-pressed": "rgb(17, 40, 118)",
  "vs-color-interaction-cta-disabled": "rgb(233, 233, 233)",
  "vs-color-interaction-cta-subtle-hover": "rgb(214, 229, 255)",
  "vs-color-interaction-cta-subtle-pressed": "rgb(51, 105, 255)",
  "vs-color-interaction-cta-subtle": "rgba(0, 0, 0, 0)",
  "vs-color-interaction-link-primary": "rgb(31, 73, 214)",
  "vs-color-interaction-link-secondary": "rgb(32, 15, 46)",
  "vs-color-interaction-link-active": "rgb(17, 40, 118)",
  "vs-color-interaction-link-visited": "rgb(168, 48, 140)",
  "vs-color-interaction-link-disabled": "rgb(144, 144, 144)",
  "vs-color-interaction-link-visited-on-bold": "rgb(214, 229, 255)",
  "vs-color-text-primary": "rgb(32, 15, 46)",
  "vs-color-text-secondary": "rgb(96, 96, 96)",
  "vs-color-text-tertiary": "rgb(83, 83, 150)",
  "vs-color-text-inverse": "rgb(255, 255, 255)",
  "vs-color-text-cta-on-light": "rgb(31, 73, 214)",
  "vs-color-text-highlight": "rgb(168, 48, 140)",
  "vs-color-text-brand": "rgb(91, 36, 135)",
  "vs-color-text-error": "rgb(148, 15, 44)",
  "vs-color-text-success": "rgb(3, 170, 70)",
  "vs-color-text-disabled": "rgb(144, 144, 144)",
  breakpoint_xl: aS,
  grid_container_width_xl: oS,
  breakpoint_md: iS,
  max_container_width_xl: lS,
  grid_container_width_md: sS,
  breakpoint_sm: uS,
  breakpoint_lg: fS,
  breakpoint_xs: dS,
  max_container_width_md: cS,
  grid_container_width_sm: pS,
  grid_container_width_lg: vS,
  max_container_width_sm: gS,
  max_container_width_lg: hS,
  max_container_width_xxl: mS,
  grid_container_width_xxl: yS,
  breakpoint_xxl: bS,
  grid_columns: _S,
  grid_gutter_width: wS,
  "vs-spacer-0": "0rem",
  "vs-spacer-0125": "0.125rem",
  "vs-spacer-025": "0.25rem",
  "vs-spacer-050": "0.5rem",
  "vs-spacer-075": "0.75rem",
  "vs-spacer-100": "1rem",
  "vs-spacer-125": "1.25rem",
  "vs-spacer-150": "1.5rem",
  "vs-spacer-175": "1.75rem",
  "vs-spacer-200": "2rem",
  "vs-spacer-250": "2.5rem",
  "vs-spacer-300": "3rem",
  "vs-spacer-400": "4rem",
  "vs-spacer-500": "5rem",
  "vs-spacer-600": "6rem",
  "vs-spacer-700": "7rem",
  "vs-radius-medium": "0.75rem",
  "vs-radius-none": "0rem",
  "vs-radius-large-increased": "1.25rem",
  "vs-radius-extra-large-increased": "2rem",
  "vs-radius-tiny": "0.25rem",
  "vs-radius-large": "1rem",
  "vs-radius-extra-large": "1.75rem",
  "vs-radius-extra-extra-large": "3rem",
  "vs-radius-huge": "4rem",
  "vs-radius-full": "1000rem",
  "vs-radius-small": "0.5rem",
  "vs-elevation-shadow-raised": "0px 1px 2px 0px rgba(0,0,0,0.30), 0px 1px 3px 1px rgba(0,0,0,0.15)",
  "vs-elevation-shadow-overlay": "0px 1px 2px 0px rgba(0,0,0,0.30), 0px 2px 6px 2px rgba(0,0,0,0.15)",
  "vs-elevation-surface": "#ffffff",
  "vs-elevation-surface-section": "#F2F2F8",
  "vs-elevation-surface-raised": "#ffffff",
  "vs-elevation-surface-overlay": "#ffffff",
  "vs-focus-shadow": "0 0 0 2px $vs-color-interaction-focus",
  "vs-focus-shadow-on-dark": "0 0 0 2px $vs-color-interaction-focus-on-bold",
  "vs-border-width-sm": "1px",
  "vs-border-width-md": "2px",
  "vs-font-size-display-m": "2.5625rem",
  "vs-font-size-display-s": "1.8125rem",
  "vs-font-size-heading-xl": "2.25rem",
  "vs-font-size-heading-l": "2rem",
  "vs-font-size-heading-m": "1.8125rem",
  "vs-font-size-heading-s": "1.625rem",
  "vs-font-size-heading-xs": "1.4375rem",
  "vs-font-size-heading-xxs": "1.25rem",
  "vs-font-size-heading-xxxs": "1rem",
  "vs-font-size-detail-l": "1.125rem",
  "vs-font-size-detail-m": "1rem",
  "vs-font-size-detail-s": "0.875rem",
  "vs-font-size-body-l": "1.25rem",
  "vs-font-size-body-m": "1.125rem",
  "vs-font-size-body-s": "1rem",
  "vs-font-weight-strong": "700",
  "vs-font-weight-medium": "600",
  "vs-font-weight-regular": "400",
  "vs-font-weight-subtle": "300",
  "vs-font-weight-heading": "600",
  "vs-font-weight-body": "400",
  "vs-font-weight-detail": "400",
  "vs-font-family-sans-serif": "'Source Sans Pro', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol'",
  "vs-font-family-display": "'evelethclean-regular', sans-serif, $vs-font-family-sans-serif",
  "vs-line-height-heading": 1.2,
  "vs-line-height-detail": 1.3,
  "vs-line-height-body": 1.5,
  "vs-letter-spacing-display": "0.02em",
  "vs-letter-spacing-heading": "-0.01em",
  "vs-letter-spacing-detail": "0",
  "vs-letter-spacing-body": "0.01em",
  icon_size_xxs: xS,
  icon_size_xs: SS,
  icon_size_sm: BS,
  icon_size_md: AS,
  icon_size_lg: CS,
  icon_size_xl: TS,
  "vs-icon-facility-audio-loop": "fa-regular fa-ear-listen",
  "vs-icon-control-collapse": "fa-regular fa-chevron-up",
  "vs-icon-facility-wheelchair-access": "fa-regular fa-wheelchair",
  "vs-icon-control-pause": "fa-regular fa-pause",
  "vs-icon-facility-cafe": "fa-regular fa-mug-saucer",
  "vs-icon-feedback-error": "fa-regular fa-circle-xmark",
  "vs-icon-facility-accessible-parking": "fa-kit fa-vs-accessible-parking",
  "vs-icon-control-filters": "fa-regular fa-sliders",
  "vs-icon-feedback-information": "fa-regular fa-circle-info",
  "vs-icon-control-external-link": "fa-regular fa-square-arrow-up-right",
  "vs-icon-control-play": "fa-regular fa-play",
  "vs-icon-facility-accessible-dining": "fa-kit fa-vs-accessible-dining",
  "vs-icon-facility-public-transport": "fa-kit fa-vs-public-transport",
  "vs-icon-season-winter": "fa-regular fa-snowman",
  "vs-icon-control-expand": "fa-regular fa-chevron-down",
  "vs-icon-facility-pets-welcome": "fa-regular fa-dog-leashed",
  "vs-icon-facility-parking": "fa-regular fa-square-parking",
  "vs-icon-control-menu": "fa-regular fa-bars",
  "vs-icon-season-spring": "fa-regular fa-flower-tulip",
  "vs-icon-season-summer": "fa-regular fa-sun",
  "vs-icon-facility-breakfast": "fa-regular fa-egg-fried",
  "vs-icon-control-back-to-top": "fa-regular fa-arrow-up",
  "vs-icon-control-dismiss": "fa-regular fa-xmark",
  "vs-icon-feedback-warning": "fa-regular fa-triangle-exclamation",
  "vs-icon-facility-accessible-shower": "fa-kit fa-vs-accessible-shower",
  "vs-icon-facility-wifi": "fa-regular fa-wifi",
  "vs-icon-control-search": "fa-regular fa-magnifying-glass",
  "vs-icon-control-download": "fa-regular fa-arrow-down-to-line",
  "vs-icon-season-autumn": "fa-regular fa-leaf-oak",
  "vs-icon-facility-accessible-toilet": "fa-kit fa-vs-accessible-toilet",
  "vs-icon-feedback-success": "fa-regular fa-circle-check",
  "vs-icon-facility-vegan": "fa-kit fa-vs-vegan",
  "font-size-1": "0.625rem",
  "font-size-2": "0.75rem",
  "font-size-3": "0.875rem",
  "font-size-4": "1rem",
  "font-size-5": "1.125rem",
  "font-size-6": "1.25rem",
  "font-size-7": "1.375rem",
  "font-size-8": "1.5rem",
  "font-size-9": "1.875rem",
  "font-size-10": "2.5rem",
  "font-size-body": "1rem",
  "font-size-body-md": "1.125rem",
  "font-size-lead": "1.125rem",
  "font-size-lead-md": "1.25rem",
  "font-size-teaser": "1rem",
  opacity_100: ES,
  opacity_50: kS,
  opacity_0: OS,
  duration_quickly: $S,
  duration_base: RS,
  duration_slowly: FS,
  no_javascript: PS,
  theme_dark: LS,
  theme_grey: IS
}, VS = {
  name: "VsIcon",
  status: "prototype",
  release: "0.1.0",
  props: {
    /**
     * A string that specifies the Font Awesome icon to render,
     * either as a semantic design token or as a set
     * of `fa-` classes.
     */
    icon: {
      type: String,
      required: !0
    },
    /**
     * The color of the icon.
     * `primary|secondary|tertiary|cta|inverse|disabled|highlight|error|warning|success`
     */
    variant: {
      type: String,
      default: "primary",
      validator: (a) => a.match(
        /(primary|secondary|tertiary|cta|inverse|disabled|highlight|error|warning|success)/
      )
    },
    /**
     * Accepts a colour (hex code or colour name) for the icon, if this is
     * set it will override the given variant. This should be used for individual
     * exceptions but if one is being used regularly it should likely be a variant
     * instead.
     */
    customColour: {
      type: String,
      default: null
    },
    /**
    * Size of icon
    * `xxs|xs|sm|md|lg|xl`
    */
    size: {
      type: String,
      default: "md",
      validator: (a) => a.match(/(xxs|xs|sm|md|lg|xl)/)
    },
    /**
    * Changes the size of the icon at sm and xs viewports
    * `xxs|xs|sm|md|lg|xl`
    */
    smallSize: {
      type: String,
      default: null,
      validator: (a) => a.match(/(xxs|xs|sm|md|lg|xl)/)
    }
  },
  data() {
    return {
      tokens: NS
    };
  },
  computed: {
    iconClasses() {
      return [
        this.fontAwesomeClasses,
        "vs-icon",
        `vs-icon--size-${this.size}`,
        this.smallSize && `vs-icon--sm-size-${this.smallSize}`,
        this.variant && `vs-icon--variant-${this.variant}`
      ];
    },
    fontAwesomeClasses() {
      return this.tokens[this.icon] || this.icon;
    },
    iconStyles() {
      return this.customColour ? {
        color: this.customColour
      } : null;
    }
  }
};
function DS(a, r, n, o, l, s) {
  return P(), j("i", xe({
    class: s.iconClasses,
    style: s.iconStyles
  }, a.$attrs, { "data-test": "vs-icon" }), null, 16);
}
const xs = /* @__PURE__ */ rn(VS, [["render", DS]]), Vd = fw("datalayer", () => {
  const a = te(""), r = te(!1), n = te({});
  function o(d) {
    r.value = d;
  }
  function l(d) {
    a.value = d;
  }
  function s(d) {
    n.value[d.key] = d.value;
  }
  function f(d) {
    return n.value[d];
  }
  return {
    pageUrl: a,
    tagsTestRun: r,
    GTMData: n,
    getValueFromKey: f,
    setTestRun: o,
    setPageUrl: l,
    processPayload: s
  };
}), Ap = (a, r, n = 0) => {
  window.setTimeout(() => {
    window[a] ? r(window[a]) : Ap(a, r, 500);
  }, n);
}, zE = [
  {
    key: "accommodation",
    text: "Accommodation",
    value: "acco"
  },
  {
    key: "eventsAndFestivals",
    text: "Events & Festivals",
    value: "even"
  },
  {
    key: "foodAndDrink",
    text: "Food & Drink",
    value: "cate"
  },
  {
    key: "thingsToDo",
    text: "Things to do",
    value: "acti,attr,reta"
  },
  {
    key: "tours",
    text: "Tours",
    value: "tour"
  }
], ME = {
  acco: "accommodation",
  "acti,attr,reta": "see-do",
  cate: "food-drink",
  even: "events",
  tour: "tours"
}, HE = [
  {
    label: "January",
    value: "january"
  },
  {
    label: "February",
    value: "february"
  },
  {
    label: "March",
    value: "march"
  },
  {
    label: "April",
    value: "april"
  },
  {
    label: "May",
    value: "may"
  },
  {
    label: "June",
    value: "june"
  },
  {
    label: "July",
    value: "july"
  },
  {
    label: "August",
    value: "august"
  },
  {
    label: "September",
    value: "september"
  },
  {
    label: "October",
    value: "october"
  },
  {
    label: "November",
    value: "november"
  },
  {
    label: "December",
    value: "december"
  }
], UE = [
  "visitabdn.com",
  "wildaboutargyll.co.uk",
  "ayrshireandarran.com",
  "visitarran.com",
  "dundee.com/visit",
  "visitangus.com",
  "edinburgh.org",
  "visiteastlothian.org",
  "visitwestlothian.co.uk",
  "visitmidlothian.co.uk",
  "welcometofife.com",
  "visitglasgow.com",
  "visitlanarkshire.com",
  "discoverinverclyde.com",
  "paisley.is",
  "west-dunbarton.gov.uk/leisure-parks-events/tourism-and-visitor-attractions",
  "eastdunbarton.gov.uk/residents/tourism-visited-exploreed/places-visit-east-dunbartonshire",
  "visitinvernesslochness.com",
  "nairnscotland.co.uk",
  "outdoorcapital.co.uk",
  "westhighlandpeninsulas.com/uk",
  "roadtotheisles.com",
  "discoverglencoe.scot",
  "visitcairngorms.com",
  "badenochstorylands.com",
  "grantownonline.com",
  "northcoast500.com",
  "venture-north.co.uk",
  "discoverassynt.co.uk",
  "nwhgeopark.com",
  "visitwester-ross.com",
  "wrb.scot",
  "visitdornoch.com",
  "easterrosspeninsula.com",
  "black-isle.info",
  "myskyetime.com",
  "morayspeyside.com",
  "yourstirling.com",
  "visitfalkirk.com",
  "discoverclackmannanshire.com",
  "lochlomond-trossachs.org",
  "accessforthvalley.com",
  "orkney.com",
  "visitouterhebrides.co.uk",
  "highlandperthshire.org",
  "perthcityandtowns.co.uk",
  "scotlandstartshere.com",
  "shetland.org/visit",
  "peoplemakeglasgow.com"
], Dd = [
  "findbusinesssupport.gov.scot",
  "www.bgateway.com",
  "www.creativescotland.com",
  "www.hie.co.uk",
  "www.scottish-enterprise.com",
  "www.southofscotlandenterprise.com",
  "www.skillsdevelopmentscotland.co.uk",
  "www.slaed.org.uk"
], zS = [
  "event",
  "site_language",
  "content_language",
  "content_category",
  "content_page_type",
  "page_category_1",
  "page_category_2",
  "page_category_3",
  "page_category_4",
  "page_category_5",
  "page_category_6",
  "content_region",
  "content_city",
  "provider_search_region",
  "provider_search_category",
  "provider_search_facilities",
  "provider_search_grading",
  "provider_search_awards",
  "provider_search_rooms",
  "provider_search_min_price",
  "provider_search_max_price",
  "provider_category",
  "provider_category_level_1",
  "provider_category_level_2",
  "provider_name",
  "provider_ID",
  "star_grading",
  "provider_region",
  "provider_city",
  "provider_has_website",
  "page_with_booking_link",
  "provider_has_availability",
  "availability_search_start_date",
  "availability_search_end_date",
  "availability_search_no_of_nights",
  "availability_search_no_of_adults",
  "availability_search_no_of_children",
  "availability_search_no_of_rooms",
  "availability_search_price_range",
  "hit_timestamp",
  "tag_name",
  "meta_data"
], MS = [
  "event",
  "site_language",
  "page_category_1",
  "page_category_2",
  "page_category_3",
  "page_category_4",
  "user_country_setting",
  "hit_timestamp",
  "tag_name",
  "meta_data",
  "psr_search_type",
  "psr_search_location",
  "page_path"
], HS = [
  "event",
  "site_language",
  "user_country_setting",
  "hit_timestamp",
  "tag_name",
  "meta_data",
  "click_text",
  "click_URL"
], US = [
  "event",
  "site_language",
  "page_category_1",
  "page_category_2",
  "page_category_3",
  "page_category_4",
  "page_category_5",
  "page_category_6",
  "user_country_setting",
  "hit_timestamp",
  "tag_name",
  "meta_data",
  "click_text",
  "click_URL"
], WS = [
  "event",
  "site_language",
  "page_category_1",
  "page_category_2",
  "page_category_3",
  "page_category_4",
  "page_category_5",
  "page_category_6",
  "user_country_setting",
  "hit_timestamp",
  "tag_name",
  "meta_data",
  "click_URL",
  "location_on_page"
], qS = [
  "event",
  "site_language",
  "page_category_1",
  "page_category_2",
  "page_category_3",
  "page_category_4",
  "page_category_5",
  "page_category_6",
  "user_country_setting",
  "hit_timestamp",
  "tag_name",
  "meta_data",
  "click_URL"
], GS = [
  "event",
  "language",
  "page_category_1",
  "page_category_2",
  "page_category_3",
  "page_category_4",
  "page_category_5",
  "page_category_6",
  "user_country_setting",
  "hit_timestamp",
  "tag_name",
  "meta_data",
  "video_status",
  "video_title",
  "video_percent"
], jS = [
  "event",
  "site_language",
  "page_category_1",
  "page_category_2",
  "page_category_3",
  "page_category_4",
  "page_category_5",
  "page_category_6",
  "user_country_setting",
  "hit_timestamp",
  "tag_name",
  "meta_data",
  "click_text",
  "click_URL",
  "dmo_referral",
  "partner_referral",
  "event_listing"
], KS = [
  "event",
  "site_language",
  "page_category_1",
  "page_category_2",
  "page_category_3",
  "page_category_4",
  "page_category_5",
  "page_category_6",
  "user_country_setting",
  "hit_timestamp",
  "tag_name",
  "meta_data",
  "click_text",
  "click_URL"
], JS = [
  "event",
  "site_language",
  "page_category_1",
  "page_category_2",
  "page_category_3",
  "page_category_4",
  "page_category_5",
  "page_category_6",
  "user_country_setting",
  "hit_timestamp",
  "tag_name",
  "meta_data",
  "error_type",
  "error_details",
  "page_path"
], XS = [
  "event",
  "site_language",
  "page_category_1",
  "page_category_2",
  "page_category_3",
  "page_category_4",
  "page_category_5",
  "page_category_6",
  "user_country_setting",
  "hit_timestamp",
  "tag_name",
  "meta_data",
  "page_path",
  "form_status"
], YS = [
  "event",
  "site_language",
  "page_category_1",
  "page_category_2",
  "page_category_3",
  "page_category_4",
  "page_category_5",
  "page_category_6",
  "user_country_setting",
  "hit_timestamp",
  "tag_name",
  "meta_data",
  "question_number",
  "answer"
], ZS = [
  "event",
  "site_language",
  "page_category_1",
  "page_category_2",
  "page_category_3",
  "page_category_4",
  "page_category_5",
  "page_category_6",
  "user_country_setting",
  "hit_timestamp",
  "tag_name",
  "meta_data",
  "total_emissions",
  "total_per_day",
  "travel_percent",
  "accommodation_percent",
  "food_percent"
], QS = [
  "event",
  "site_language",
  "page_category_1",
  "page_category_2",
  "page_category_3",
  "page_category_4",
  "page_category_5",
  "page_category_6",
  "user_country_setting",
  "hit_timestamp",
  "tag_name",
  "tag_name",
  "meta_data",
  "referral_location",
  "attraction_name",
  "dmo_referral"
], eB = [
  "event",
  "site_language",
  "page_category_1",
  "page_category_2",
  "page_category_3",
  "page_category_4",
  "page_category_5",
  "page_category_6",
  "user_country_setting",
  "hit_timestamp",
  "tag_name",
  "meta_data",
  "accordion_text"
], tB = [
  "event",
  "event_tab",
  "site_language",
  "user_country_setting",
  "hit_timestamp",
  "tag_name",
  "meta_data",
  "event_listing"
], nB = {
  computed: {
    // Retrieving page url from the page store created by:
    // dataLayer.store.js (Central Store)
    // TagManagerWrapper.vue (Global component that reads and updates the store)
    ...dw(Vd, {
      pageUrl(a) {
        return a.pageUrl;
      }
    })
  },
  data() {
    return {
      dataLayerLoadConfirmed: !1,
      dataLayerStore: null
    };
  },
  mounted() {
    this.dataLayerStore = Vd();
  },
  methods: {
    // This function matches values passed as an object
    // To the template listed on data-layer-template.js
    // And return any value not matched as undefined
    templateFiller(a, r) {
      const n = {};
      return a.forEach((o) => {
        n[o] = "", n[o] = r[o] || void 0, o === "language" && n[o] === void 0 && (n[o] = r.site_language || void 0);
      }), n;
    },
    createDataLayerObject(a, r, n) {
      let o, l, s, f, d, p, g = "", m = "False";
      switch (r && r.target && (r.target.text ? p = r.target.text.trim() : p = r.target.innerText, r.target.closest('[data-event-listing="True"]') && (m = "True")), a) {
        case "productSearchDataEvent":
          o = "psr_search", l = "VS - GA4 - Event - PSR Search", s = {
            event: o,
            psr_search_type: r.searchType,
            psr_search_location: r.searchLocation,
            page_path: n,
            tag_name: l
          }, f = this.compileFullTemplate(s), d = this.templateFiller(MS, f);
          break;
        case "pageViewTemplateDataEvent":
          o = "page_view", s = {
            event: o
          }, f = this.compileFullTemplate(s), d = this.templateFiller(zS, f);
          break;
        case "menuNavigationDataEvent":
          o = "menu_navigation", s = {
            event: o,
            click_text: r.target.text.trim(),
            click_URL: n
          }, f = this.compileFullTemplate(s), d = this.templateFiller(HS, f);
          break;
        case "socialMediaExternalLinkDataEvent":
          o = "social_media_footer", l = "VS - GA - Social Media External Link", s = {
            event: o,
            tag_name: l,
            click_URL: n
          }, f = this.compileFullTemplate(s), d = this.templateFiller(WS, f);
          break;
        case "homePageLogoClickDataEvent":
          o = "homepage_logo_click", l = "VS - GA - Homepage Logo Click", s = {
            event: o,
            tag_name: l
          }, f = this.compileFullTemplate(s), d = this.templateFiller(qS, f);
          break;
        case "externalLinkDataEvent":
          o = "external_link", l = "VS - GA - External Link", s = {
            event: o,
            tag_name: l,
            click_text: p,
            click_URL: n,
            partner_referral: "False",
            event_listing: m
          };
          for (let w = 0; w < Dd.length; w++)
            n.includes(Dd[w]) && (s.partner_referral = "True");
          f = this.compileFullTemplate(s), d = this.templateFiller(jS, f);
          break;
        case "internalLinkDataEvent":
          o = "internal_link", l = "VS - GA - Internal Link", s = {
            event: o,
            tag_name: l,
            click_text: p,
            click_URL: n
          }, f = this.compileFullTemplate(s), d = this.templateFiller(KS, f);
          break;
        case "formsDataEvent":
          o = "forms", l = "VS - GA - Forms", s = {
            event: o,
            tag_name: l,
            form_status: "form_submitted"
          }, f = this.compileFullTemplate(s), d = this.templateFiller(XS, f);
          break;
        case "errorDataEvent":
          o = "errors", l = "VS - GA - Errors", s = {
            event: o,
            tag_name: l,
            error_type: r.error_type,
            error_details: r.error_details
          }, f = this.compileFullTemplate(s), d = this.templateFiller(JS, f);
          break;
        case "videoTrackingDataEvent":
          o = "video_tracking", l = "VS - GA - Video Tracking", s = {
            event: o,
            tag_name: l,
            video_status: r.status,
            video_title: r.title || "",
            video_percent: r.percent || 0
          }, f = this.compileFullTemplate(s), d = this.templateFiller(GS, f);
          break;
        case "socialShareDataEvent":
          o = "share", l = "VS - GA - Share", typeof r.target.text < "u" && (g = r.target.text.trim()), s = {
            event: o,
            tag_name: l,
            click_text: g,
            click_URL: n
          }, f = this.compileFullTemplate(s), d = this.templateFiller(US, f);
          break;
        case "carbonQuestionEvent":
          o = "carbonCalculatorQuestion", l = "VS - GA - Carbon Calculator Question Answered", s = {
            event: o,
            tag_name: l,
            question_number: r.questionNumber,
            answer: r.answer
          }, f = this.compileFullTemplate(s), d = this.templateFiller(YS, f);
          break;
        case "carbonCompleteEvent":
          o = "carbonCalculatorComplete", l = "VS - GA - Carbon Calculator Complete", s = {
            event: o,
            tag_name: l,
            total_emissions: r.totalEmissions,
            total_per_day: r.totalPerDay,
            travel_percent: r.travelPercent,
            accommodation_percent: r.accommodationPercent,
            food_percent: r.foodPercent
          }, f = this.compileFullTemplate(s), d = this.templateFiller(ZS, f);
          break;
        case "cmsReferral":
          o = "cms_referral", l = "VS - GA4 - Event - CMS Link Click Referral", s = {
            event: o,
            tag_name: l,
            referral_location: r.referral_location,
            dmo_referral: r.dmo_referral
          }, f = this.compileFullTemplate(s), d = this.templateFiller(QS, f);
          break;
        case "accordionOpenEvent":
          o = "accordion_opened", l = "GA4 - Event - Accordion Opened", s = {
            event: o,
            tag_name: l,
            accordion_text: r.accordion_text
          }, f = this.compileFullTemplate(s), d = this.templateFiller(eB, f);
          break;
        case "tabClickEvent":
          o = "tab_click", l = "GA4 - Event - Tab Click", s = {
            event: o,
            tag_name: l,
            event_tab: p,
            event_listing: m
          }, f = this.compileFullTemplate(s), d = this.templateFiller(tB, f);
          break;
      }
      this.pushToDataLayer(d);
    },
    returnIsoDate() {
      return new Date(Date.now()).toISOString();
    },
    pushToDataLayer(a) {
      this.dataLayerLoadConfirmed ? window.dataLayer.push(a) : Ap("dataLayer", () => {
        this.dataLayerLoadConfirmed = !0, window.dataLayer.push(a);
      });
    },
    compileFullTemplate(a) {
      const n = {
        ...this.dataLayerStore.GTMData,
        ...a
      };
      return n.hit_timestamp = this.returnIsoDate(), n;
    },
    targetText(a) {
      let r;
      return a.target.text ? r = a.target.text.trim() : r = "", r;
    }
  }
}, rB = {
  name: "VsButton",
  status: "prototype",
  release: "0.0.1",
  components: {
    BButton: Nr,
    VsIcon: xs
  },
  mixins: [
    nB
  ],
  props: {
    /**
     * Use this option to render the button as an anchor element with the given href.
     */
    href: {
      type: String,
      default: null
    },
    /**
     * Tab index value - this is needed as tabindex attribute is sometimes stripped
     * from the button on first update with nested components.
     */
    tabindex: {
      type: String,
      default: null
    },
    /**
     * Style variation to give additional meaning
     * `primary|secondary|subtle`.
     */
    variant: {
      type: String,
      default: "primary",
      validator: (a) => a.match(
        /(primary|secondary|subtle)/
      )
    },
    /**
     * Size of the button
     * `sm|md|lg`.
     */
    size: {
      type: String,
      default: "md",
      validator: (a) => a.match(/(sm|md|lg)/)
    },
    /**
     * By default, buttons have an animation behaviour on click.
     * Pass `false` to disable.
     */
    animate: {
      type: Boolean,
      default: !0
    },
    /**
     * Pass the name of the icon to add it to the button.
     */
    rounded: {
      type: Boolean,
      default: !0
    },
    /**
     * Pass the name of the icon to add it to the button.
     */
    icon: {
      type: String,
      default: ""
    },
    /**
     * If the button contains an icon only with no text.
     */
    iconOnly: {
      type: Boolean,
      default: !1
    },
    /**
     * The position of the icon
     * `left|right`
     */
    iconPosition: {
      type: String,
      default: "left",
      validator: (a) => a.match(/(left|right)/)
    }
  },
  emits: ["btnFocus"],
  data() {
    return {
      isAnimating: !1
    };
  },
  computed: {
    buttonClasses() {
      return [
        {
          "vs-button--animated": this.animate && !this.iconOnly,
          "vs-button--is-animating": this.isAnimating,
          "vs-button--rounded": this.rounded,
          "vs-button--icon-only": this.iconOnly,
          "vs-button--flex-reverse": this.iconPosition === "right"
        }
      ];
    },
    iconClasses() {
      return [
        {
          "vs-icon--right": this.iconPosition === "right",
          "vs-icon--left": this.iconPosition === "left"
        }
      ];
    }
  },
  methods: {
    animateHandler(a) {
      this.isAnimating = !0, setTimeout(() => {
        this.isAnimating = !1;
      }, 1e3), this.href !== null && (a.preventDefault(), this.trackLink(a));
    },
    tabbedIn(a) {
      this.$emit("btnFocus", a);
    },
    trackLink(a) {
      let r;
      this.href.includes("http") ? r = "externalLinkDataEvent" : r = "internalLinkDataEvent", this.createDataLayerObject(r, a, this.href), this.href !== "#" && this.href !== null && (window.location.href = this.href);
    }
  }
};
function aB(a, r, n, o, l, s) {
  const f = qt("VsIcon"), d = qt("BButton");
  return P(), X(d, xe({
    variant: n.variant,
    href: n.href || void 0,
    tabindex: n.tabindex || void 0,
    class: ["vs-button justify-content-center", s.buttonClasses],
    size: n.size
  }, a.$attrs, {
    "aria-disabled": !!a.$attrs.disabled,
    onClick: r[0] || (r[0] = (p) => s.animateHandler(p)),
    onKeyup: Rr(s.tabbedIn, ["tab"])
  }), {
    default: ee(() => [
      n.icon ? (P(), X(f, {
        key: 0,
        class: Q(["align-self-center", s.iconClasses]),
        icon: n.icon,
        size: "xs",
        padding: 0
      }, null, 8, ["icon", "class"])) : $e("", !0),
      je("span", {
        class: Q(["vs-button__text", { "visually-hidden": n.iconOnly }])
      }, [
        W(a.$slots, "default")
      ], 2)
    ]),
    _: 3
  }, 16, ["variant", "href", "tabindex", "class", "size", "aria-disabled", "onKeyup"]);
}
const oB = /* @__PURE__ */ rn(rB, [["render", aB]]);
var nr = typeof globalThis < "u" ? globalThis : typeof window < "u" ? window : typeof global < "u" ? global : typeof self < "u" ? self : {};
function WE(a) {
  return a && a.__esModule && Object.prototype.hasOwnProperty.call(a, "default") ? a.default : a;
}
function iB(a) {
  if (a.__esModule) return a;
  var r = a.default;
  if (typeof r == "function") {
    var n = function o() {
      return this instanceof o ? Reflect.construct(r, arguments, this.constructor) : r.apply(this, arguments);
    };
    n.prototype = r.prototype;
  } else n = {};
  return Object.defineProperty(n, "__esModule", { value: !0 }), Object.keys(a).forEach(function(o) {
    var l = Object.getOwnPropertyDescriptor(a, o);
    Object.defineProperty(n, o, l.get ? l : {
      enumerable: !0,
      get: function() {
        return a[o];
      }
    });
  }), n;
}
var Ho = { exports: {} };
/**
 * @license
 * Lodash <https://lodash.com/>
 * Copyright OpenJS Foundation and other contributors <https://openjsf.org/>
 * Released under MIT license <https://lodash.com/license>
 * Based on Underscore.js 1.8.3 <http://underscorejs.org/LICENSE>
 * Copyright Jeremy Ashkenas, DocumentCloud and Investigative Reporters & Editors
 */
Ho.exports;
(function(a, r) {
  (function() {
    var n, o = "4.17.21", l = 200, s = "Unsupported core-js use. Try https://npms.io/search?q=ponyfill.", f = "Expected a function", d = "Invalid `variable` option passed into `_.template`", p = "__lodash_hash_undefined__", g = 500, m = "__lodash_placeholder__", w = 1, S = 2, b = 4, y = 1, x = 2, B = 1, E = 2, F = 4, L = 8, $ = 16, k = 32, z = 64, H = 128, ie = 256, se = 512, Ae = 30, Ie = "...", Ce = 800, Re = 16, q = 1, ge = 2, de = 3, Se = 1 / 0, pe = 9007199254740991, Ke = 17976931348623157e292, Me = NaN, Pe = 4294967295, it = Pe - 1, Y = Pe >>> 1, me = [
      ["ary", H],
      ["bind", B],
      ["bindKey", E],
      ["curry", L],
      ["curryRight", $],
      ["flip", se],
      ["partial", k],
      ["partialRight", z],
      ["rearg", ie]
    ], De = "[object Arguments]", mt = "[object Array]", dr = "[object AsyncFunction]", pt = "[object Boolean]", le = "[object Date]", qe = "[object DOMException]", qn = "[object Error]", pn = "[object Function]", Ps = "[object GeneratorFunction]", Jt = "[object Map]", Jr = "[object Number]", mv = "[object Null]", vn = "[object Object]", Ls = "[object Promise]", yv = "[object Proxy]", Xr = "[object RegExp]", Xt = "[object Set]", Yr = "[object String]", La = "[object Symbol]", bv = "[object Undefined]", Zr = "[object WeakMap]", _v = "[object WeakSet]", Qr = "[object ArrayBuffer]", cr = "[object DataView]", si = "[object Float32Array]", ui = "[object Float64Array]", fi = "[object Int8Array]", di = "[object Int16Array]", ci = "[object Int32Array]", pi = "[object Uint8Array]", vi = "[object Uint8ClampedArray]", gi = "[object Uint16Array]", hi = "[object Uint32Array]", wv = /\b__p \+= '';/g, xv = /\b(__p \+=) '' \+/g, Sv = /(__e\(.*?\)|\b__t\)) \+\n'';/g, Is = /&(?:amp|lt|gt|quot|#39);/g, Ns = /[&<>"']/g, Bv = RegExp(Is.source), Av = RegExp(Ns.source), Cv = /<%-([\s\S]+?)%>/g, Tv = /<%([\s\S]+?)%>/g, Vs = /<%=([\s\S]+?)%>/g, Ev = /\.|\[(?:[^[\]]*|(["'])(?:(?!\1)[^\\]|\\.)*?\1)\]/, kv = /^\w*$/, Ov = /[^.[\]]+|\[(?:(-?\d+(?:\.\d+)?)|(["'])((?:(?!\2)[^\\]|\\.)*?)\2)\]|(?=(?:\.|\[\])(?:\.|\[\]|$))/g, mi = /[\\^$.*+?()[\]{}|]/g, $v = RegExp(mi.source), yi = /^\s+/, Rv = /\s/, Fv = /\{(?:\n\/\* \[wrapped with .+\] \*\/)?\n?/, Pv = /\{\n\/\* \[wrapped with (.+)\] \*/, Lv = /,? & /, Iv = /[^\x00-\x2f\x3a-\x40\x5b-\x60\x7b-\x7f]+/g, Nv = /[()=,{}\[\]\/\s]/, Vv = /\\(\\)?/g, Dv = /\$\{([^\\}]*(?:\\.[^\\}]*)*)\}/g, Ds = /\w*$/, zv = /^[-+]0x[0-9a-f]+$/i, Mv = /^0b[01]+$/i, Hv = /^\[object .+?Constructor\]$/, Uv = /^0o[0-7]+$/i, Wv = /^(?:0|[1-9]\d*)$/, qv = /[\xc0-\xd6\xd8-\xf6\xf8-\xff\u0100-\u017f]/g, Ia = /($^)/, Gv = /['\n\r\u2028\u2029\\]/g, Na = "\\ud800-\\udfff", jv = "\\u0300-\\u036f", Kv = "\\ufe20-\\ufe2f", Jv = "\\u20d0-\\u20ff", zs = jv + Kv + Jv, Ms = "\\u2700-\\u27bf", Hs = "a-z\\xdf-\\xf6\\xf8-\\xff", Xv = "\\xac\\xb1\\xd7\\xf7", Yv = "\\x00-\\x2f\\x3a-\\x40\\x5b-\\x60\\x7b-\\xbf", Zv = "\\u2000-\\u206f", Qv = " \\t\\x0b\\f\\xa0\\ufeff\\n\\r\\u2028\\u2029\\u1680\\u180e\\u2000\\u2001\\u2002\\u2003\\u2004\\u2005\\u2006\\u2007\\u2008\\u2009\\u200a\\u202f\\u205f\\u3000", Us = "A-Z\\xc0-\\xd6\\xd8-\\xde", Ws = "\\ufe0e\\ufe0f", qs = Xv + Yv + Zv + Qv, bi = "['’]", eg = "[" + Na + "]", Gs = "[" + qs + "]", Va = "[" + zs + "]", js = "\\d+", tg = "[" + Ms + "]", Ks = "[" + Hs + "]", Js = "[^" + Na + qs + js + Ms + Hs + Us + "]", _i = "\\ud83c[\\udffb-\\udfff]", ng = "(?:" + Va + "|" + _i + ")", Xs = "[^" + Na + "]", wi = "(?:\\ud83c[\\udde6-\\uddff]){2}", xi = "[\\ud800-\\udbff][\\udc00-\\udfff]", pr = "[" + Us + "]", Ys = "\\u200d", Zs = "(?:" + Ks + "|" + Js + ")", rg = "(?:" + pr + "|" + Js + ")", Qs = "(?:" + bi + "(?:d|ll|m|re|s|t|ve))?", eu = "(?:" + bi + "(?:D|LL|M|RE|S|T|VE))?", tu = ng + "?", nu = "[" + Ws + "]?", ag = "(?:" + Ys + "(?:" + [Xs, wi, xi].join("|") + ")" + nu + tu + ")*", og = "\\d*(?:1st|2nd|3rd|(?![123])\\dth)(?=\\b|[A-Z_])", ig = "\\d*(?:1ST|2ND|3RD|(?![123])\\dTH)(?=\\b|[a-z_])", ru = nu + tu + ag, lg = "(?:" + [tg, wi, xi].join("|") + ")" + ru, sg = "(?:" + [Xs + Va + "?", Va, wi, xi, eg].join("|") + ")", ug = RegExp(bi, "g"), fg = RegExp(Va, "g"), Si = RegExp(_i + "(?=" + _i + ")|" + sg + ru, "g"), dg = RegExp([
      pr + "?" + Ks + "+" + Qs + "(?=" + [Gs, pr, "$"].join("|") + ")",
      rg + "+" + eu + "(?=" + [Gs, pr + Zs, "$"].join("|") + ")",
      pr + "?" + Zs + "+" + Qs,
      pr + "+" + eu,
      ig,
      og,
      js,
      lg
    ].join("|"), "g"), cg = RegExp("[" + Ys + Na + zs + Ws + "]"), pg = /[a-z][A-Z]|[A-Z]{2}[a-z]|[0-9][a-zA-Z]|[a-zA-Z][0-9]|[^a-zA-Z0-9 ]/, vg = [
      "Array",
      "Buffer",
      "DataView",
      "Date",
      "Error",
      "Float32Array",
      "Float64Array",
      "Function",
      "Int8Array",
      "Int16Array",
      "Int32Array",
      "Map",
      "Math",
      "Object",
      "Promise",
      "RegExp",
      "Set",
      "String",
      "Symbol",
      "TypeError",
      "Uint8Array",
      "Uint8ClampedArray",
      "Uint16Array",
      "Uint32Array",
      "WeakMap",
      "_",
      "clearTimeout",
      "isFinite",
      "parseInt",
      "setTimeout"
    ], gg = -1, ze = {};
    ze[si] = ze[ui] = ze[fi] = ze[di] = ze[ci] = ze[pi] = ze[vi] = ze[gi] = ze[hi] = !0, ze[De] = ze[mt] = ze[Qr] = ze[pt] = ze[cr] = ze[le] = ze[qn] = ze[pn] = ze[Jt] = ze[Jr] = ze[vn] = ze[Xr] = ze[Xt] = ze[Yr] = ze[Zr] = !1;
    var Ne = {};
    Ne[De] = Ne[mt] = Ne[Qr] = Ne[cr] = Ne[pt] = Ne[le] = Ne[si] = Ne[ui] = Ne[fi] = Ne[di] = Ne[ci] = Ne[Jt] = Ne[Jr] = Ne[vn] = Ne[Xr] = Ne[Xt] = Ne[Yr] = Ne[La] = Ne[pi] = Ne[vi] = Ne[gi] = Ne[hi] = !0, Ne[qn] = Ne[pn] = Ne[Zr] = !1;
    var hg = {
      // Latin-1 Supplement block.
      À: "A",
      Á: "A",
      Â: "A",
      Ã: "A",
      Ä: "A",
      Å: "A",
      à: "a",
      á: "a",
      â: "a",
      ã: "a",
      ä: "a",
      å: "a",
      Ç: "C",
      ç: "c",
      Ð: "D",
      ð: "d",
      È: "E",
      É: "E",
      Ê: "E",
      Ë: "E",
      è: "e",
      é: "e",
      ê: "e",
      ë: "e",
      Ì: "I",
      Í: "I",
      Î: "I",
      Ï: "I",
      ì: "i",
      í: "i",
      î: "i",
      ï: "i",
      Ñ: "N",
      ñ: "n",
      Ò: "O",
      Ó: "O",
      Ô: "O",
      Õ: "O",
      Ö: "O",
      Ø: "O",
      ò: "o",
      ó: "o",
      ô: "o",
      õ: "o",
      ö: "o",
      ø: "o",
      Ù: "U",
      Ú: "U",
      Û: "U",
      Ü: "U",
      ù: "u",
      ú: "u",
      û: "u",
      ü: "u",
      Ý: "Y",
      ý: "y",
      ÿ: "y",
      Æ: "Ae",
      æ: "ae",
      Þ: "Th",
      þ: "th",
      ß: "ss",
      // Latin Extended-A block.
      Ā: "A",
      Ă: "A",
      Ą: "A",
      ā: "a",
      ă: "a",
      ą: "a",
      Ć: "C",
      Ĉ: "C",
      Ċ: "C",
      Č: "C",
      ć: "c",
      ĉ: "c",
      ċ: "c",
      č: "c",
      Ď: "D",
      Đ: "D",
      ď: "d",
      đ: "d",
      Ē: "E",
      Ĕ: "E",
      Ė: "E",
      Ę: "E",
      Ě: "E",
      ē: "e",
      ĕ: "e",
      ė: "e",
      ę: "e",
      ě: "e",
      Ĝ: "G",
      Ğ: "G",
      Ġ: "G",
      Ģ: "G",
      ĝ: "g",
      ğ: "g",
      ġ: "g",
      ģ: "g",
      Ĥ: "H",
      Ħ: "H",
      ĥ: "h",
      ħ: "h",
      Ĩ: "I",
      Ī: "I",
      Ĭ: "I",
      Į: "I",
      İ: "I",
      ĩ: "i",
      ī: "i",
      ĭ: "i",
      į: "i",
      ı: "i",
      Ĵ: "J",
      ĵ: "j",
      Ķ: "K",
      ķ: "k",
      ĸ: "k",
      Ĺ: "L",
      Ļ: "L",
      Ľ: "L",
      Ŀ: "L",
      Ł: "L",
      ĺ: "l",
      ļ: "l",
      ľ: "l",
      ŀ: "l",
      ł: "l",
      Ń: "N",
      Ņ: "N",
      Ň: "N",
      Ŋ: "N",
      ń: "n",
      ņ: "n",
      ň: "n",
      ŋ: "n",
      Ō: "O",
      Ŏ: "O",
      Ő: "O",
      ō: "o",
      ŏ: "o",
      ő: "o",
      Ŕ: "R",
      Ŗ: "R",
      Ř: "R",
      ŕ: "r",
      ŗ: "r",
      ř: "r",
      Ś: "S",
      Ŝ: "S",
      Ş: "S",
      Š: "S",
      ś: "s",
      ŝ: "s",
      ş: "s",
      š: "s",
      Ţ: "T",
      Ť: "T",
      Ŧ: "T",
      ţ: "t",
      ť: "t",
      ŧ: "t",
      Ũ: "U",
      Ū: "U",
      Ŭ: "U",
      Ů: "U",
      Ű: "U",
      Ų: "U",
      ũ: "u",
      ū: "u",
      ŭ: "u",
      ů: "u",
      ű: "u",
      ų: "u",
      Ŵ: "W",
      ŵ: "w",
      Ŷ: "Y",
      ŷ: "y",
      Ÿ: "Y",
      Ź: "Z",
      Ż: "Z",
      Ž: "Z",
      ź: "z",
      ż: "z",
      ž: "z",
      Ĳ: "IJ",
      ĳ: "ij",
      Œ: "Oe",
      œ: "oe",
      ŉ: "'n",
      ſ: "s"
    }, mg = {
      "&": "&amp;",
      "<": "&lt;",
      ">": "&gt;",
      '"': "&quot;",
      "'": "&#39;"
    }, yg = {
      "&amp;": "&",
      "&lt;": "<",
      "&gt;": ">",
      "&quot;": '"',
      "&#39;": "'"
    }, bg = {
      "\\": "\\",
      "'": "'",
      "\n": "n",
      "\r": "r",
      "\u2028": "u2028",
      "\u2029": "u2029"
    }, _g = parseFloat, wg = parseInt, au = typeof nr == "object" && nr && nr.Object === Object && nr, xg = typeof self == "object" && self && self.Object === Object && self, at = au || xg || Function("return this")(), Bi = r && !r.nodeType && r, Gn = Bi && !0 && a && !a.nodeType && a, ou = Gn && Gn.exports === Bi, Ai = ou && au.process, It = function() {
      try {
        var C = Gn && Gn.require && Gn.require("util").types;
        return C || Ai && Ai.binding && Ai.binding("util");
      } catch {
      }
    }(), iu = It && It.isArrayBuffer, lu = It && It.isDate, su = It && It.isMap, uu = It && It.isRegExp, fu = It && It.isSet, du = It && It.isTypedArray;
    function Bt(C, N, R) {
      switch (R.length) {
        case 0:
          return C.call(N);
        case 1:
          return C.call(N, R[0]);
        case 2:
          return C.call(N, R[0], R[1]);
        case 3:
          return C.call(N, R[0], R[1], R[2]);
      }
      return C.apply(N, R);
    }
    function Sg(C, N, R, J) {
      for (var ue = -1, ke = C == null ? 0 : C.length; ++ue < ke; ) {
        var Ze = C[ue];
        N(J, Ze, R(Ze), C);
      }
      return J;
    }
    function Nt(C, N) {
      for (var R = -1, J = C == null ? 0 : C.length; ++R < J && N(C[R], R, C) !== !1; )
        ;
      return C;
    }
    function Bg(C, N) {
      for (var R = C == null ? 0 : C.length; R-- && N(C[R], R, C) !== !1; )
        ;
      return C;
    }
    function cu(C, N) {
      for (var R = -1, J = C == null ? 0 : C.length; ++R < J; )
        if (!N(C[R], R, C))
          return !1;
      return !0;
    }
    function $n(C, N) {
      for (var R = -1, J = C == null ? 0 : C.length, ue = 0, ke = []; ++R < J; ) {
        var Ze = C[R];
        N(Ze, R, C) && (ke[ue++] = Ze);
      }
      return ke;
    }
    function Da(C, N) {
      var R = C == null ? 0 : C.length;
      return !!R && vr(C, N, 0) > -1;
    }
    function Ci(C, N, R) {
      for (var J = -1, ue = C == null ? 0 : C.length; ++J < ue; )
        if (R(N, C[J]))
          return !0;
      return !1;
    }
    function He(C, N) {
      for (var R = -1, J = C == null ? 0 : C.length, ue = Array(J); ++R < J; )
        ue[R] = N(C[R], R, C);
      return ue;
    }
    function Rn(C, N) {
      for (var R = -1, J = N.length, ue = C.length; ++R < J; )
        C[ue + R] = N[R];
      return C;
    }
    function Ti(C, N, R, J) {
      var ue = -1, ke = C == null ? 0 : C.length;
      for (J && ke && (R = C[++ue]); ++ue < ke; )
        R = N(R, C[ue], ue, C);
      return R;
    }
    function Ag(C, N, R, J) {
      var ue = C == null ? 0 : C.length;
      for (J && ue && (R = C[--ue]); ue--; )
        R = N(R, C[ue], ue, C);
      return R;
    }
    function Ei(C, N) {
      for (var R = -1, J = C == null ? 0 : C.length; ++R < J; )
        if (N(C[R], R, C))
          return !0;
      return !1;
    }
    var Cg = ki("length");
    function Tg(C) {
      return C.split("");
    }
    function Eg(C) {
      return C.match(Iv) || [];
    }
    function pu(C, N, R) {
      var J;
      return R(C, function(ue, ke, Ze) {
        if (N(ue, ke, Ze))
          return J = ke, !1;
      }), J;
    }
    function za(C, N, R, J) {
      for (var ue = C.length, ke = R + (J ? 1 : -1); J ? ke-- : ++ke < ue; )
        if (N(C[ke], ke, C))
          return ke;
      return -1;
    }
    function vr(C, N, R) {
      return N === N ? zg(C, N, R) : za(C, vu, R);
    }
    function kg(C, N, R, J) {
      for (var ue = R - 1, ke = C.length; ++ue < ke; )
        if (J(C[ue], N))
          return ue;
      return -1;
    }
    function vu(C) {
      return C !== C;
    }
    function gu(C, N) {
      var R = C == null ? 0 : C.length;
      return R ? $i(C, N) / R : Me;
    }
    function ki(C) {
      return function(N) {
        return N == null ? n : N[C];
      };
    }
    function Oi(C) {
      return function(N) {
        return C == null ? n : C[N];
      };
    }
    function hu(C, N, R, J, ue) {
      return ue(C, function(ke, Ze, Le) {
        R = J ? (J = !1, ke) : N(R, ke, Ze, Le);
      }), R;
    }
    function Og(C, N) {
      var R = C.length;
      for (C.sort(N); R--; )
        C[R] = C[R].value;
      return C;
    }
    function $i(C, N) {
      for (var R, J = -1, ue = C.length; ++J < ue; ) {
        var ke = N(C[J]);
        ke !== n && (R = R === n ? ke : R + ke);
      }
      return R;
    }
    function Ri(C, N) {
      for (var R = -1, J = Array(C); ++R < C; )
        J[R] = N(R);
      return J;
    }
    function $g(C, N) {
      return He(N, function(R) {
        return [R, C[R]];
      });
    }
    function mu(C) {
      return C && C.slice(0, wu(C) + 1).replace(yi, "");
    }
    function At(C) {
      return function(N) {
        return C(N);
      };
    }
    function Fi(C, N) {
      return He(N, function(R) {
        return C[R];
      });
    }
    function ea(C, N) {
      return C.has(N);
    }
    function yu(C, N) {
      for (var R = -1, J = C.length; ++R < J && vr(N, C[R], 0) > -1; )
        ;
      return R;
    }
    function bu(C, N) {
      for (var R = C.length; R-- && vr(N, C[R], 0) > -1; )
        ;
      return R;
    }
    function Rg(C, N) {
      for (var R = C.length, J = 0; R--; )
        C[R] === N && ++J;
      return J;
    }
    var Fg = Oi(hg), Pg = Oi(mg);
    function Lg(C) {
      return "\\" + bg[C];
    }
    function Ig(C, N) {
      return C == null ? n : C[N];
    }
    function gr(C) {
      return cg.test(C);
    }
    function Ng(C) {
      return pg.test(C);
    }
    function Vg(C) {
      for (var N, R = []; !(N = C.next()).done; )
        R.push(N.value);
      return R;
    }
    function Pi(C) {
      var N = -1, R = Array(C.size);
      return C.forEach(function(J, ue) {
        R[++N] = [ue, J];
      }), R;
    }
    function _u(C, N) {
      return function(R) {
        return C(N(R));
      };
    }
    function Fn(C, N) {
      for (var R = -1, J = C.length, ue = 0, ke = []; ++R < J; ) {
        var Ze = C[R];
        (Ze === N || Ze === m) && (C[R] = m, ke[ue++] = R);
      }
      return ke;
    }
    function Ma(C) {
      var N = -1, R = Array(C.size);
      return C.forEach(function(J) {
        R[++N] = J;
      }), R;
    }
    function Dg(C) {
      var N = -1, R = Array(C.size);
      return C.forEach(function(J) {
        R[++N] = [J, J];
      }), R;
    }
    function zg(C, N, R) {
      for (var J = R - 1, ue = C.length; ++J < ue; )
        if (C[J] === N)
          return J;
      return -1;
    }
    function Mg(C, N, R) {
      for (var J = R + 1; J--; )
        if (C[J] === N)
          return J;
      return J;
    }
    function hr(C) {
      return gr(C) ? Ug(C) : Cg(C);
    }
    function Yt(C) {
      return gr(C) ? Wg(C) : Tg(C);
    }
    function wu(C) {
      for (var N = C.length; N-- && Rv.test(C.charAt(N)); )
        ;
      return N;
    }
    var Hg = Oi(yg);
    function Ug(C) {
      for (var N = Si.lastIndex = 0; Si.test(C); )
        ++N;
      return N;
    }
    function Wg(C) {
      return C.match(Si) || [];
    }
    function qg(C) {
      return C.match(dg) || [];
    }
    var Gg = function C(N) {
      N = N == null ? at : mr.defaults(at.Object(), N, mr.pick(at, vg));
      var R = N.Array, J = N.Date, ue = N.Error, ke = N.Function, Ze = N.Math, Le = N.Object, Li = N.RegExp, jg = N.String, Vt = N.TypeError, Ha = R.prototype, Kg = ke.prototype, yr = Le.prototype, Ua = N["__core-js_shared__"], Wa = Kg.toString, Fe = yr.hasOwnProperty, Jg = 0, xu = function() {
        var e = /[^.]+$/.exec(Ua && Ua.keys && Ua.keys.IE_PROTO || "");
        return e ? "Symbol(src)_1." + e : "";
      }(), qa = yr.toString, Xg = Wa.call(Le), Yg = at._, Zg = Li(
        "^" + Wa.call(Fe).replace(mi, "\\$&").replace(/hasOwnProperty|(function).*?(?=\\\()| for .+?(?=\\\])/g, "$1.*?") + "$"
      ), Ga = ou ? N.Buffer : n, Pn = N.Symbol, ja = N.Uint8Array, Su = Ga ? Ga.allocUnsafe : n, Ka = _u(Le.getPrototypeOf, Le), Bu = Le.create, Au = yr.propertyIsEnumerable, Ja = Ha.splice, Cu = Pn ? Pn.isConcatSpreadable : n, ta = Pn ? Pn.iterator : n, jn = Pn ? Pn.toStringTag : n, Xa = function() {
        try {
          var e = Zn(Le, "defineProperty");
          return e({}, "", {}), e;
        } catch {
        }
      }(), Qg = N.clearTimeout !== at.clearTimeout && N.clearTimeout, eh = J && J.now !== at.Date.now && J.now, th = N.setTimeout !== at.setTimeout && N.setTimeout, Ya = Ze.ceil, Za = Ze.floor, Ii = Le.getOwnPropertySymbols, nh = Ga ? Ga.isBuffer : n, Tu = N.isFinite, rh = Ha.join, ah = _u(Le.keys, Le), Qe = Ze.max, lt = Ze.min, oh = J.now, ih = N.parseInt, Eu = Ze.random, lh = Ha.reverse, Ni = Zn(N, "DataView"), na = Zn(N, "Map"), Vi = Zn(N, "Promise"), br = Zn(N, "Set"), ra = Zn(N, "WeakMap"), aa = Zn(Le, "create"), Qa = ra && new ra(), _r = {}, sh = Qn(Ni), uh = Qn(na), fh = Qn(Vi), dh = Qn(br), ch = Qn(ra), eo = Pn ? Pn.prototype : n, oa = eo ? eo.valueOf : n, ku = eo ? eo.toString : n;
      function v(e) {
        if (Ge(e) && !fe(e) && !(e instanceof _e)) {
          if (e instanceof Dt)
            return e;
          if (Fe.call(e, "__wrapped__"))
            return $f(e);
        }
        return new Dt(e);
      }
      var wr = /* @__PURE__ */ function() {
        function e() {
        }
        return function(t) {
          if (!We(t))
            return {};
          if (Bu)
            return Bu(t);
          e.prototype = t;
          var i = new e();
          return e.prototype = n, i;
        };
      }();
      function to() {
      }
      function Dt(e, t) {
        this.__wrapped__ = e, this.__actions__ = [], this.__chain__ = !!t, this.__index__ = 0, this.__values__ = n;
      }
      v.templateSettings = {
        /**
         * Used to detect `data` property values to be HTML-escaped.
         *
         * @memberOf _.templateSettings
         * @type {RegExp}
         */
        escape: Cv,
        /**
         * Used to detect code to be evaluated.
         *
         * @memberOf _.templateSettings
         * @type {RegExp}
         */
        evaluate: Tv,
        /**
         * Used to detect `data` property values to inject.
         *
         * @memberOf _.templateSettings
         * @type {RegExp}
         */
        interpolate: Vs,
        /**
         * Used to reference the data object in the template text.
         *
         * @memberOf _.templateSettings
         * @type {string}
         */
        variable: "",
        /**
         * Used to import variables into the compiled template.
         *
         * @memberOf _.templateSettings
         * @type {Object}
         */
        imports: {
          /**
           * A reference to the `lodash` function.
           *
           * @memberOf _.templateSettings.imports
           * @type {Function}
           */
          _: v
        }
      }, v.prototype = to.prototype, v.prototype.constructor = v, Dt.prototype = wr(to.prototype), Dt.prototype.constructor = Dt;
      function _e(e) {
        this.__wrapped__ = e, this.__actions__ = [], this.__dir__ = 1, this.__filtered__ = !1, this.__iteratees__ = [], this.__takeCount__ = Pe, this.__views__ = [];
      }
      function ph() {
        var e = new _e(this.__wrapped__);
        return e.__actions__ = yt(this.__actions__), e.__dir__ = this.__dir__, e.__filtered__ = this.__filtered__, e.__iteratees__ = yt(this.__iteratees__), e.__takeCount__ = this.__takeCount__, e.__views__ = yt(this.__views__), e;
      }
      function vh() {
        if (this.__filtered__) {
          var e = new _e(this);
          e.__dir__ = -1, e.__filtered__ = !0;
        } else
          e = this.clone(), e.__dir__ *= -1;
        return e;
      }
      function gh() {
        var e = this.__wrapped__.value(), t = this.__dir__, i = fe(e), u = t < 0, c = i ? e.length : 0, h = Tm(0, c, this.__views__), _ = h.start, A = h.end, T = A - _, V = u ? A : _ - 1, D = this.__iteratees__, M = D.length, G = 0, Z = lt(T, this.__takeCount__);
        if (!i || !u && c == T && Z == T)
          return Qu(e, this.__actions__);
        var ae = [];
        e:
          for (; T-- && G < Z; ) {
            V += t;
            for (var he = -1, oe = e[V]; ++he < M; ) {
              var be = D[he], Be = be.iteratee, Et = be.type, ht = Be(oe);
              if (Et == ge)
                oe = ht;
              else if (!ht) {
                if (Et == q)
                  continue e;
                break e;
              }
            }
            ae[G++] = oe;
          }
        return ae;
      }
      _e.prototype = wr(to.prototype), _e.prototype.constructor = _e;
      function Kn(e) {
        var t = -1, i = e == null ? 0 : e.length;
        for (this.clear(); ++t < i; ) {
          var u = e[t];
          this.set(u[0], u[1]);
        }
      }
      function hh() {
        this.__data__ = aa ? aa(null) : {}, this.size = 0;
      }
      function mh(e) {
        var t = this.has(e) && delete this.__data__[e];
        return this.size -= t ? 1 : 0, t;
      }
      function yh(e) {
        var t = this.__data__;
        if (aa) {
          var i = t[e];
          return i === p ? n : i;
        }
        return Fe.call(t, e) ? t[e] : n;
      }
      function bh(e) {
        var t = this.__data__;
        return aa ? t[e] !== n : Fe.call(t, e);
      }
      function _h(e, t) {
        var i = this.__data__;
        return this.size += this.has(e) ? 0 : 1, i[e] = aa && t === n ? p : t, this;
      }
      Kn.prototype.clear = hh, Kn.prototype.delete = mh, Kn.prototype.get = yh, Kn.prototype.has = bh, Kn.prototype.set = _h;
      function gn(e) {
        var t = -1, i = e == null ? 0 : e.length;
        for (this.clear(); ++t < i; ) {
          var u = e[t];
          this.set(u[0], u[1]);
        }
      }
      function wh() {
        this.__data__ = [], this.size = 0;
      }
      function xh(e) {
        var t = this.__data__, i = no(t, e);
        if (i < 0)
          return !1;
        var u = t.length - 1;
        return i == u ? t.pop() : Ja.call(t, i, 1), --this.size, !0;
      }
      function Sh(e) {
        var t = this.__data__, i = no(t, e);
        return i < 0 ? n : t[i][1];
      }
      function Bh(e) {
        return no(this.__data__, e) > -1;
      }
      function Ah(e, t) {
        var i = this.__data__, u = no(i, e);
        return u < 0 ? (++this.size, i.push([e, t])) : i[u][1] = t, this;
      }
      gn.prototype.clear = wh, gn.prototype.delete = xh, gn.prototype.get = Sh, gn.prototype.has = Bh, gn.prototype.set = Ah;
      function hn(e) {
        var t = -1, i = e == null ? 0 : e.length;
        for (this.clear(); ++t < i; ) {
          var u = e[t];
          this.set(u[0], u[1]);
        }
      }
      function Ch() {
        this.size = 0, this.__data__ = {
          hash: new Kn(),
          map: new (na || gn)(),
          string: new Kn()
        };
      }
      function Th(e) {
        var t = go(this, e).delete(e);
        return this.size -= t ? 1 : 0, t;
      }
      function Eh(e) {
        return go(this, e).get(e);
      }
      function kh(e) {
        return go(this, e).has(e);
      }
      function Oh(e, t) {
        var i = go(this, e), u = i.size;
        return i.set(e, t), this.size += i.size == u ? 0 : 1, this;
      }
      hn.prototype.clear = Ch, hn.prototype.delete = Th, hn.prototype.get = Eh, hn.prototype.has = kh, hn.prototype.set = Oh;
      function Jn(e) {
        var t = -1, i = e == null ? 0 : e.length;
        for (this.__data__ = new hn(); ++t < i; )
          this.add(e[t]);
      }
      function $h(e) {
        return this.__data__.set(e, p), this;
      }
      function Rh(e) {
        return this.__data__.has(e);
      }
      Jn.prototype.add = Jn.prototype.push = $h, Jn.prototype.has = Rh;
      function Zt(e) {
        var t = this.__data__ = new gn(e);
        this.size = t.size;
      }
      function Fh() {
        this.__data__ = new gn(), this.size = 0;
      }
      function Ph(e) {
        var t = this.__data__, i = t.delete(e);
        return this.size = t.size, i;
      }
      function Lh(e) {
        return this.__data__.get(e);
      }
      function Ih(e) {
        return this.__data__.has(e);
      }
      function Nh(e, t) {
        var i = this.__data__;
        if (i instanceof gn) {
          var u = i.__data__;
          if (!na || u.length < l - 1)
            return u.push([e, t]), this.size = ++i.size, this;
          i = this.__data__ = new hn(u);
        }
        return i.set(e, t), this.size = i.size, this;
      }
      Zt.prototype.clear = Fh, Zt.prototype.delete = Ph, Zt.prototype.get = Lh, Zt.prototype.has = Ih, Zt.prototype.set = Nh;
      function Ou(e, t) {
        var i = fe(e), u = !i && er(e), c = !i && !u && Dn(e), h = !i && !u && !c && Ar(e), _ = i || u || c || h, A = _ ? Ri(e.length, jg) : [], T = A.length;
        for (var V in e)
          (t || Fe.call(e, V)) && !(_ && // Safari 9 has enumerable `arguments.length` in strict mode.
          (V == "length" || // Node.js 0.10 has enumerable non-index properties on buffers.
          c && (V == "offset" || V == "parent") || // PhantomJS 2 has enumerable non-index properties on typed arrays.
          h && (V == "buffer" || V == "byteLength" || V == "byteOffset") || // Skip index properties.
          _n(V, T))) && A.push(V);
        return A;
      }
      function $u(e) {
        var t = e.length;
        return t ? e[Ji(0, t - 1)] : n;
      }
      function Vh(e, t) {
        return ho(yt(e), Xn(t, 0, e.length));
      }
      function Dh(e) {
        return ho(yt(e));
      }
      function Di(e, t, i) {
        (i !== n && !Qt(e[t], i) || i === n && !(t in e)) && mn(e, t, i);
      }
      function ia(e, t, i) {
        var u = e[t];
        (!(Fe.call(e, t) && Qt(u, i)) || i === n && !(t in e)) && mn(e, t, i);
      }
      function no(e, t) {
        for (var i = e.length; i--; )
          if (Qt(e[i][0], t))
            return i;
        return -1;
      }
      function zh(e, t, i, u) {
        return Ln(e, function(c, h, _) {
          t(u, c, i(c), _);
        }), u;
      }
      function Ru(e, t) {
        return e && ln(t, tt(t), e);
      }
      function Mh(e, t) {
        return e && ln(t, _t(t), e);
      }
      function mn(e, t, i) {
        t == "__proto__" && Xa ? Xa(e, t, {
          configurable: !0,
          enumerable: !0,
          value: i,
          writable: !0
        }) : e[t] = i;
      }
      function zi(e, t) {
        for (var i = -1, u = t.length, c = R(u), h = e == null; ++i < u; )
          c[i] = h ? n : _l(e, t[i]);
        return c;
      }
      function Xn(e, t, i) {
        return e === e && (i !== n && (e = e <= i ? e : i), t !== n && (e = e >= t ? e : t)), e;
      }
      function zt(e, t, i, u, c, h) {
        var _, A = t & w, T = t & S, V = t & b;
        if (i && (_ = c ? i(e, u, c, h) : i(e)), _ !== n)
          return _;
        if (!We(e))
          return e;
        var D = fe(e);
        if (D) {
          if (_ = km(e), !A)
            return yt(e, _);
        } else {
          var M = st(e), G = M == pn || M == Ps;
          if (Dn(e))
            return nf(e, A);
          if (M == vn || M == De || G && !c) {
            if (_ = T || G ? {} : xf(e), !A)
              return T ? ym(e, Mh(_, e)) : mm(e, Ru(_, e));
          } else {
            if (!Ne[M])
              return c ? e : {};
            _ = Om(e, M, A);
          }
        }
        h || (h = new Zt());
        var Z = h.get(e);
        if (Z)
          return Z;
        h.set(e, _), Yf(e) ? e.forEach(function(oe) {
          _.add(zt(oe, t, i, oe, e, h));
        }) : Jf(e) && e.forEach(function(oe, be) {
          _.set(be, zt(oe, t, i, be, e, h));
        });
        var ae = V ? T ? il : ol : T ? _t : tt, he = D ? n : ae(e);
        return Nt(he || e, function(oe, be) {
          he && (be = oe, oe = e[be]), ia(_, be, zt(oe, t, i, be, e, h));
        }), _;
      }
      function Hh(e) {
        var t = tt(e);
        return function(i) {
          return Fu(i, e, t);
        };
      }
      function Fu(e, t, i) {
        var u = i.length;
        if (e == null)
          return !u;
        for (e = Le(e); u--; ) {
          var c = i[u], h = t[c], _ = e[c];
          if (_ === n && !(c in e) || !h(_))
            return !1;
        }
        return !0;
      }
      function Pu(e, t, i) {
        if (typeof e != "function")
          throw new Vt(f);
        return pa(function() {
          e.apply(n, i);
        }, t);
      }
      function la(e, t, i, u) {
        var c = -1, h = Da, _ = !0, A = e.length, T = [], V = t.length;
        if (!A)
          return T;
        i && (t = He(t, At(i))), u ? (h = Ci, _ = !1) : t.length >= l && (h = ea, _ = !1, t = new Jn(t));
        e:
          for (; ++c < A; ) {
            var D = e[c], M = i == null ? D : i(D);
            if (D = u || D !== 0 ? D : 0, _ && M === M) {
              for (var G = V; G--; )
                if (t[G] === M)
                  continue e;
              T.push(D);
            } else h(t, M, u) || T.push(D);
          }
        return T;
      }
      var Ln = sf(on), Lu = sf(Hi, !0);
      function Uh(e, t) {
        var i = !0;
        return Ln(e, function(u, c, h) {
          return i = !!t(u, c, h), i;
        }), i;
      }
      function ro(e, t, i) {
        for (var u = -1, c = e.length; ++u < c; ) {
          var h = e[u], _ = t(h);
          if (_ != null && (A === n ? _ === _ && !Tt(_) : i(_, A)))
            var A = _, T = h;
        }
        return T;
      }
      function Wh(e, t, i, u) {
        var c = e.length;
        for (i = ve(i), i < 0 && (i = -i > c ? 0 : c + i), u = u === n || u > c ? c : ve(u), u < 0 && (u += c), u = i > u ? 0 : Qf(u); i < u; )
          e[i++] = t;
        return e;
      }
      function Iu(e, t) {
        var i = [];
        return Ln(e, function(u, c, h) {
          t(u, c, h) && i.push(u);
        }), i;
      }
      function ot(e, t, i, u, c) {
        var h = -1, _ = e.length;
        for (i || (i = Rm), c || (c = []); ++h < _; ) {
          var A = e[h];
          t > 0 && i(A) ? t > 1 ? ot(A, t - 1, i, u, c) : Rn(c, A) : u || (c[c.length] = A);
        }
        return c;
      }
      var Mi = uf(), Nu = uf(!0);
      function on(e, t) {
        return e && Mi(e, t, tt);
      }
      function Hi(e, t) {
        return e && Nu(e, t, tt);
      }
      function ao(e, t) {
        return $n(t, function(i) {
          return wn(e[i]);
        });
      }
      function Yn(e, t) {
        t = Nn(t, e);
        for (var i = 0, u = t.length; e != null && i < u; )
          e = e[sn(t[i++])];
        return i && i == u ? e : n;
      }
      function Vu(e, t, i) {
        var u = t(e);
        return fe(e) ? u : Rn(u, i(e));
      }
      function vt(e) {
        return e == null ? e === n ? bv : mv : jn && jn in Le(e) ? Cm(e) : Dm(e);
      }
      function Ui(e, t) {
        return e > t;
      }
      function qh(e, t) {
        return e != null && Fe.call(e, t);
      }
      function Gh(e, t) {
        return e != null && t in Le(e);
      }
      function jh(e, t, i) {
        return e >= lt(t, i) && e < Qe(t, i);
      }
      function Wi(e, t, i) {
        for (var u = i ? Ci : Da, c = e[0].length, h = e.length, _ = h, A = R(h), T = 1 / 0, V = []; _--; ) {
          var D = e[_];
          _ && t && (D = He(D, At(t))), T = lt(D.length, T), A[_] = !i && (t || c >= 120 && D.length >= 120) ? new Jn(_ && D) : n;
        }
        D = e[0];
        var M = -1, G = A[0];
        e:
          for (; ++M < c && V.length < T; ) {
            var Z = D[M], ae = t ? t(Z) : Z;
            if (Z = i || Z !== 0 ? Z : 0, !(G ? ea(G, ae) : u(V, ae, i))) {
              for (_ = h; --_; ) {
                var he = A[_];
                if (!(he ? ea(he, ae) : u(e[_], ae, i)))
                  continue e;
              }
              G && G.push(ae), V.push(Z);
            }
          }
        return V;
      }
      function Kh(e, t, i, u) {
        return on(e, function(c, h, _) {
          t(u, i(c), h, _);
        }), u;
      }
      function sa(e, t, i) {
        t = Nn(t, e), e = Cf(e, t);
        var u = e == null ? e : e[sn(Ht(t))];
        return u == null ? n : Bt(u, e, i);
      }
      function Du(e) {
        return Ge(e) && vt(e) == De;
      }
      function Jh(e) {
        return Ge(e) && vt(e) == Qr;
      }
      function Xh(e) {
        return Ge(e) && vt(e) == le;
      }
      function ua(e, t, i, u, c) {
        return e === t ? !0 : e == null || t == null || !Ge(e) && !Ge(t) ? e !== e && t !== t : Yh(e, t, i, u, ua, c);
      }
      function Yh(e, t, i, u, c, h) {
        var _ = fe(e), A = fe(t), T = _ ? mt : st(e), V = A ? mt : st(t);
        T = T == De ? vn : T, V = V == De ? vn : V;
        var D = T == vn, M = V == vn, G = T == V;
        if (G && Dn(e)) {
          if (!Dn(t))
            return !1;
          _ = !0, D = !1;
        }
        if (G && !D)
          return h || (h = new Zt()), _ || Ar(e) ? bf(e, t, i, u, c, h) : Bm(e, t, T, i, u, c, h);
        if (!(i & y)) {
          var Z = D && Fe.call(e, "__wrapped__"), ae = M && Fe.call(t, "__wrapped__");
          if (Z || ae) {
            var he = Z ? e.value() : e, oe = ae ? t.value() : t;
            return h || (h = new Zt()), c(he, oe, i, u, h);
          }
        }
        return G ? (h || (h = new Zt()), Am(e, t, i, u, c, h)) : !1;
      }
      function Zh(e) {
        return Ge(e) && st(e) == Jt;
      }
      function qi(e, t, i, u) {
        var c = i.length, h = c, _ = !u;
        if (e == null)
          return !h;
        for (e = Le(e); c--; ) {
          var A = i[c];
          if (_ && A[2] ? A[1] !== e[A[0]] : !(A[0] in e))
            return !1;
        }
        for (; ++c < h; ) {
          A = i[c];
          var T = A[0], V = e[T], D = A[1];
          if (_ && A[2]) {
            if (V === n && !(T in e))
              return !1;
          } else {
            var M = new Zt();
            if (u)
              var G = u(V, D, T, e, t, M);
            if (!(G === n ? ua(D, V, y | x, u, M) : G))
              return !1;
          }
        }
        return !0;
      }
      function zu(e) {
        if (!We(e) || Pm(e))
          return !1;
        var t = wn(e) ? Zg : Hv;
        return t.test(Qn(e));
      }
      function Qh(e) {
        return Ge(e) && vt(e) == Xr;
      }
      function em(e) {
        return Ge(e) && st(e) == Xt;
      }
      function tm(e) {
        return Ge(e) && xo(e.length) && !!ze[vt(e)];
      }
      function Mu(e) {
        return typeof e == "function" ? e : e == null ? wt : typeof e == "object" ? fe(e) ? Wu(e[0], e[1]) : Uu(e) : fd(e);
      }
      function Gi(e) {
        if (!ca(e))
          return ah(e);
        var t = [];
        for (var i in Le(e))
          Fe.call(e, i) && i != "constructor" && t.push(i);
        return t;
      }
      function nm(e) {
        if (!We(e))
          return Vm(e);
        var t = ca(e), i = [];
        for (var u in e)
          u == "constructor" && (t || !Fe.call(e, u)) || i.push(u);
        return i;
      }
      function ji(e, t) {
        return e < t;
      }
      function Hu(e, t) {
        var i = -1, u = bt(e) ? R(e.length) : [];
        return Ln(e, function(c, h, _) {
          u[++i] = t(c, h, _);
        }), u;
      }
      function Uu(e) {
        var t = sl(e);
        return t.length == 1 && t[0][2] ? Bf(t[0][0], t[0][1]) : function(i) {
          return i === e || qi(i, e, t);
        };
      }
      function Wu(e, t) {
        return fl(e) && Sf(t) ? Bf(sn(e), t) : function(i) {
          var u = _l(i, e);
          return u === n && u === t ? wl(i, e) : ua(t, u, y | x);
        };
      }
      function oo(e, t, i, u, c) {
        e !== t && Mi(t, function(h, _) {
          if (c || (c = new Zt()), We(h))
            rm(e, t, _, i, oo, u, c);
          else {
            var A = u ? u(cl(e, _), h, _ + "", e, t, c) : n;
            A === n && (A = h), Di(e, _, A);
          }
        }, _t);
      }
      function rm(e, t, i, u, c, h, _) {
        var A = cl(e, i), T = cl(t, i), V = _.get(T);
        if (V) {
          Di(e, i, V);
          return;
        }
        var D = h ? h(A, T, i + "", e, t, _) : n, M = D === n;
        if (M) {
          var G = fe(T), Z = !G && Dn(T), ae = !G && !Z && Ar(T);
          D = T, G || Z || ae ? fe(A) ? D = A : Je(A) ? D = yt(A) : Z ? (M = !1, D = nf(T, !0)) : ae ? (M = !1, D = rf(T, !0)) : D = [] : va(T) || er(T) ? (D = A, er(A) ? D = ed(A) : (!We(A) || wn(A)) && (D = xf(T))) : M = !1;
        }
        M && (_.set(T, D), c(D, T, u, h, _), _.delete(T)), Di(e, i, D);
      }
      function qu(e, t) {
        var i = e.length;
        if (i)
          return t += t < 0 ? i : 0, _n(t, i) ? e[t] : n;
      }
      function Gu(e, t, i) {
        t.length ? t = He(t, function(h) {
          return fe(h) ? function(_) {
            return Yn(_, h.length === 1 ? h[0] : h);
          } : h;
        }) : t = [wt];
        var u = -1;
        t = He(t, At(re()));
        var c = Hu(e, function(h, _, A) {
          var T = He(t, function(V) {
            return V(h);
          });
          return { criteria: T, index: ++u, value: h };
        });
        return Og(c, function(h, _) {
          return hm(h, _, i);
        });
      }
      function am(e, t) {
        return ju(e, t, function(i, u) {
          return wl(e, u);
        });
      }
      function ju(e, t, i) {
        for (var u = -1, c = t.length, h = {}; ++u < c; ) {
          var _ = t[u], A = Yn(e, _);
          i(A, _) && fa(h, Nn(_, e), A);
        }
        return h;
      }
      function om(e) {
        return function(t) {
          return Yn(t, e);
        };
      }
      function Ki(e, t, i, u) {
        var c = u ? kg : vr, h = -1, _ = t.length, A = e;
        for (e === t && (t = yt(t)), i && (A = He(e, At(i))); ++h < _; )
          for (var T = 0, V = t[h], D = i ? i(V) : V; (T = c(A, D, T, u)) > -1; )
            A !== e && Ja.call(A, T, 1), Ja.call(e, T, 1);
        return e;
      }
      function Ku(e, t) {
        for (var i = e ? t.length : 0, u = i - 1; i--; ) {
          var c = t[i];
          if (i == u || c !== h) {
            var h = c;
            _n(c) ? Ja.call(e, c, 1) : Zi(e, c);
          }
        }
        return e;
      }
      function Ji(e, t) {
        return e + Za(Eu() * (t - e + 1));
      }
      function im(e, t, i, u) {
        for (var c = -1, h = Qe(Ya((t - e) / (i || 1)), 0), _ = R(h); h--; )
          _[u ? h : ++c] = e, e += i;
        return _;
      }
      function Xi(e, t) {
        var i = "";
        if (!e || t < 1 || t > pe)
          return i;
        do
          t % 2 && (i += e), t = Za(t / 2), t && (e += e);
        while (t);
        return i;
      }
      function ye(e, t) {
        return pl(Af(e, t, wt), e + "");
      }
      function lm(e) {
        return $u(Cr(e));
      }
      function sm(e, t) {
        var i = Cr(e);
        return ho(i, Xn(t, 0, i.length));
      }
      function fa(e, t, i, u) {
        if (!We(e))
          return e;
        t = Nn(t, e);
        for (var c = -1, h = t.length, _ = h - 1, A = e; A != null && ++c < h; ) {
          var T = sn(t[c]), V = i;
          if (T === "__proto__" || T === "constructor" || T === "prototype")
            return e;
          if (c != _) {
            var D = A[T];
            V = u ? u(D, T, A) : n, V === n && (V = We(D) ? D : _n(t[c + 1]) ? [] : {});
          }
          ia(A, T, V), A = A[T];
        }
        return e;
      }
      var Ju = Qa ? function(e, t) {
        return Qa.set(e, t), e;
      } : wt, um = Xa ? function(e, t) {
        return Xa(e, "toString", {
          configurable: !0,
          enumerable: !1,
          value: Sl(t),
          writable: !0
        });
      } : wt;
      function fm(e) {
        return ho(Cr(e));
      }
      function Mt(e, t, i) {
        var u = -1, c = e.length;
        t < 0 && (t = -t > c ? 0 : c + t), i = i > c ? c : i, i < 0 && (i += c), c = t > i ? 0 : i - t >>> 0, t >>>= 0;
        for (var h = R(c); ++u < c; )
          h[u] = e[u + t];
        return h;
      }
      function dm(e, t) {
        var i;
        return Ln(e, function(u, c, h) {
          return i = t(u, c, h), !i;
        }), !!i;
      }
      function io(e, t, i) {
        var u = 0, c = e == null ? u : e.length;
        if (typeof t == "number" && t === t && c <= Y) {
          for (; u < c; ) {
            var h = u + c >>> 1, _ = e[h];
            _ !== null && !Tt(_) && (i ? _ <= t : _ < t) ? u = h + 1 : c = h;
          }
          return c;
        }
        return Yi(e, t, wt, i);
      }
      function Yi(e, t, i, u) {
        var c = 0, h = e == null ? 0 : e.length;
        if (h === 0)
          return 0;
        t = i(t);
        for (var _ = t !== t, A = t === null, T = Tt(t), V = t === n; c < h; ) {
          var D = Za((c + h) / 2), M = i(e[D]), G = M !== n, Z = M === null, ae = M === M, he = Tt(M);
          if (_)
            var oe = u || ae;
          else V ? oe = ae && (u || G) : A ? oe = ae && G && (u || !Z) : T ? oe = ae && G && !Z && (u || !he) : Z || he ? oe = !1 : oe = u ? M <= t : M < t;
          oe ? c = D + 1 : h = D;
        }
        return lt(h, it);
      }
      function Xu(e, t) {
        for (var i = -1, u = e.length, c = 0, h = []; ++i < u; ) {
          var _ = e[i], A = t ? t(_) : _;
          if (!i || !Qt(A, T)) {
            var T = A;
            h[c++] = _ === 0 ? 0 : _;
          }
        }
        return h;
      }
      function Yu(e) {
        return typeof e == "number" ? e : Tt(e) ? Me : +e;
      }
      function Ct(e) {
        if (typeof e == "string")
          return e;
        if (fe(e))
          return He(e, Ct) + "";
        if (Tt(e))
          return ku ? ku.call(e) : "";
        var t = e + "";
        return t == "0" && 1 / e == -1 / 0 ? "-0" : t;
      }
      function In(e, t, i) {
        var u = -1, c = Da, h = e.length, _ = !0, A = [], T = A;
        if (i)
          _ = !1, c = Ci;
        else if (h >= l) {
          var V = t ? null : xm(e);
          if (V)
            return Ma(V);
          _ = !1, c = ea, T = new Jn();
        } else
          T = t ? [] : A;
        e:
          for (; ++u < h; ) {
            var D = e[u], M = t ? t(D) : D;
            if (D = i || D !== 0 ? D : 0, _ && M === M) {
              for (var G = T.length; G--; )
                if (T[G] === M)
                  continue e;
              t && T.push(M), A.push(D);
            } else c(T, M, i) || (T !== A && T.push(M), A.push(D));
          }
        return A;
      }
      function Zi(e, t) {
        return t = Nn(t, e), e = Cf(e, t), e == null || delete e[sn(Ht(t))];
      }
      function Zu(e, t, i, u) {
        return fa(e, t, i(Yn(e, t)), u);
      }
      function lo(e, t, i, u) {
        for (var c = e.length, h = u ? c : -1; (u ? h-- : ++h < c) && t(e[h], h, e); )
          ;
        return i ? Mt(e, u ? 0 : h, u ? h + 1 : c) : Mt(e, u ? h + 1 : 0, u ? c : h);
      }
      function Qu(e, t) {
        var i = e;
        return i instanceof _e && (i = i.value()), Ti(t, function(u, c) {
          return c.func.apply(c.thisArg, Rn([u], c.args));
        }, i);
      }
      function Qi(e, t, i) {
        var u = e.length;
        if (u < 2)
          return u ? In(e[0]) : [];
        for (var c = -1, h = R(u); ++c < u; )
          for (var _ = e[c], A = -1; ++A < u; )
            A != c && (h[c] = la(h[c] || _, e[A], t, i));
        return In(ot(h, 1), t, i);
      }
      function ef(e, t, i) {
        for (var u = -1, c = e.length, h = t.length, _ = {}; ++u < c; ) {
          var A = u < h ? t[u] : n;
          i(_, e[u], A);
        }
        return _;
      }
      function el(e) {
        return Je(e) ? e : [];
      }
      function tl(e) {
        return typeof e == "function" ? e : wt;
      }
      function Nn(e, t) {
        return fe(e) ? e : fl(e, t) ? [e] : Of(Oe(e));
      }
      var cm = ye;
      function Vn(e, t, i) {
        var u = e.length;
        return i = i === n ? u : i, !t && i >= u ? e : Mt(e, t, i);
      }
      var tf = Qg || function(e) {
        return at.clearTimeout(e);
      };
      function nf(e, t) {
        if (t)
          return e.slice();
        var i = e.length, u = Su ? Su(i) : new e.constructor(i);
        return e.copy(u), u;
      }
      function nl(e) {
        var t = new e.constructor(e.byteLength);
        return new ja(t).set(new ja(e)), t;
      }
      function pm(e, t) {
        var i = t ? nl(e.buffer) : e.buffer;
        return new e.constructor(i, e.byteOffset, e.byteLength);
      }
      function vm(e) {
        var t = new e.constructor(e.source, Ds.exec(e));
        return t.lastIndex = e.lastIndex, t;
      }
      function gm(e) {
        return oa ? Le(oa.call(e)) : {};
      }
      function rf(e, t) {
        var i = t ? nl(e.buffer) : e.buffer;
        return new e.constructor(i, e.byteOffset, e.length);
      }
      function af(e, t) {
        if (e !== t) {
          var i = e !== n, u = e === null, c = e === e, h = Tt(e), _ = t !== n, A = t === null, T = t === t, V = Tt(t);
          if (!A && !V && !h && e > t || h && _ && T && !A && !V || u && _ && T || !i && T || !c)
            return 1;
          if (!u && !h && !V && e < t || V && i && c && !u && !h || A && i && c || !_ && c || !T)
            return -1;
        }
        return 0;
      }
      function hm(e, t, i) {
        for (var u = -1, c = e.criteria, h = t.criteria, _ = c.length, A = i.length; ++u < _; ) {
          var T = af(c[u], h[u]);
          if (T) {
            if (u >= A)
              return T;
            var V = i[u];
            return T * (V == "desc" ? -1 : 1);
          }
        }
        return e.index - t.index;
      }
      function of(e, t, i, u) {
        for (var c = -1, h = e.length, _ = i.length, A = -1, T = t.length, V = Qe(h - _, 0), D = R(T + V), M = !u; ++A < T; )
          D[A] = t[A];
        for (; ++c < _; )
          (M || c < h) && (D[i[c]] = e[c]);
        for (; V--; )
          D[A++] = e[c++];
        return D;
      }
      function lf(e, t, i, u) {
        for (var c = -1, h = e.length, _ = -1, A = i.length, T = -1, V = t.length, D = Qe(h - A, 0), M = R(D + V), G = !u; ++c < D; )
          M[c] = e[c];
        for (var Z = c; ++T < V; )
          M[Z + T] = t[T];
        for (; ++_ < A; )
          (G || c < h) && (M[Z + i[_]] = e[c++]);
        return M;
      }
      function yt(e, t) {
        var i = -1, u = e.length;
        for (t || (t = R(u)); ++i < u; )
          t[i] = e[i];
        return t;
      }
      function ln(e, t, i, u) {
        var c = !i;
        i || (i = {});
        for (var h = -1, _ = t.length; ++h < _; ) {
          var A = t[h], T = u ? u(i[A], e[A], A, i, e) : n;
          T === n && (T = e[A]), c ? mn(i, A, T) : ia(i, A, T);
        }
        return i;
      }
      function mm(e, t) {
        return ln(e, ul(e), t);
      }
      function ym(e, t) {
        return ln(e, _f(e), t);
      }
      function so(e, t) {
        return function(i, u) {
          var c = fe(i) ? Sg : zh, h = t ? t() : {};
          return c(i, e, re(u, 2), h);
        };
      }
      function xr(e) {
        return ye(function(t, i) {
          var u = -1, c = i.length, h = c > 1 ? i[c - 1] : n, _ = c > 2 ? i[2] : n;
          for (h = e.length > 3 && typeof h == "function" ? (c--, h) : n, _ && gt(i[0], i[1], _) && (h = c < 3 ? n : h, c = 1), t = Le(t); ++u < c; ) {
            var A = i[u];
            A && e(t, A, u, h);
          }
          return t;
        });
      }
      function sf(e, t) {
        return function(i, u) {
          if (i == null)
            return i;
          if (!bt(i))
            return e(i, u);
          for (var c = i.length, h = t ? c : -1, _ = Le(i); (t ? h-- : ++h < c) && u(_[h], h, _) !== !1; )
            ;
          return i;
        };
      }
      function uf(e) {
        return function(t, i, u) {
          for (var c = -1, h = Le(t), _ = u(t), A = _.length; A--; ) {
            var T = _[e ? A : ++c];
            if (i(h[T], T, h) === !1)
              break;
          }
          return t;
        };
      }
      function bm(e, t, i) {
        var u = t & B, c = da(e);
        function h() {
          var _ = this && this !== at && this instanceof h ? c : e;
          return _.apply(u ? i : this, arguments);
        }
        return h;
      }
      function ff(e) {
        return function(t) {
          t = Oe(t);
          var i = gr(t) ? Yt(t) : n, u = i ? i[0] : t.charAt(0), c = i ? Vn(i, 1).join("") : t.slice(1);
          return u[e]() + c;
        };
      }
      function Sr(e) {
        return function(t) {
          return Ti(sd(ld(t).replace(ug, "")), e, "");
        };
      }
      function da(e) {
        return function() {
          var t = arguments;
          switch (t.length) {
            case 0:
              return new e();
            case 1:
              return new e(t[0]);
            case 2:
              return new e(t[0], t[1]);
            case 3:
              return new e(t[0], t[1], t[2]);
            case 4:
              return new e(t[0], t[1], t[2], t[3]);
            case 5:
              return new e(t[0], t[1], t[2], t[3], t[4]);
            case 6:
              return new e(t[0], t[1], t[2], t[3], t[4], t[5]);
            case 7:
              return new e(t[0], t[1], t[2], t[3], t[4], t[5], t[6]);
          }
          var i = wr(e.prototype), u = e.apply(i, t);
          return We(u) ? u : i;
        };
      }
      function _m(e, t, i) {
        var u = da(e);
        function c() {
          for (var h = arguments.length, _ = R(h), A = h, T = Br(c); A--; )
            _[A] = arguments[A];
          var V = h < 3 && _[0] !== T && _[h - 1] !== T ? [] : Fn(_, T);
          if (h -= V.length, h < i)
            return gf(
              e,
              t,
              uo,
              c.placeholder,
              n,
              _,
              V,
              n,
              n,
              i - h
            );
          var D = this && this !== at && this instanceof c ? u : e;
          return Bt(D, this, _);
        }
        return c;
      }
      function df(e) {
        return function(t, i, u) {
          var c = Le(t);
          if (!bt(t)) {
            var h = re(i, 3);
            t = tt(t), i = function(A) {
              return h(c[A], A, c);
            };
          }
          var _ = e(t, i, u);
          return _ > -1 ? c[h ? t[_] : _] : n;
        };
      }
      function cf(e) {
        return bn(function(t) {
          var i = t.length, u = i, c = Dt.prototype.thru;
          for (e && t.reverse(); u--; ) {
            var h = t[u];
            if (typeof h != "function")
              throw new Vt(f);
            if (c && !_ && vo(h) == "wrapper")
              var _ = new Dt([], !0);
          }
          for (u = _ ? u : i; ++u < i; ) {
            h = t[u];
            var A = vo(h), T = A == "wrapper" ? ll(h) : n;
            T && dl(T[0]) && T[1] == (H | L | k | ie) && !T[4].length && T[9] == 1 ? _ = _[vo(T[0])].apply(_, T[3]) : _ = h.length == 1 && dl(h) ? _[A]() : _.thru(h);
          }
          return function() {
            var V = arguments, D = V[0];
            if (_ && V.length == 1 && fe(D))
              return _.plant(D).value();
            for (var M = 0, G = i ? t[M].apply(this, V) : D; ++M < i; )
              G = t[M].call(this, G);
            return G;
          };
        });
      }
      function uo(e, t, i, u, c, h, _, A, T, V) {
        var D = t & H, M = t & B, G = t & E, Z = t & (L | $), ae = t & se, he = G ? n : da(e);
        function oe() {
          for (var be = arguments.length, Be = R(be), Et = be; Et--; )
            Be[Et] = arguments[Et];
          if (Z)
            var ht = Br(oe), kt = Rg(Be, ht);
          if (u && (Be = of(Be, u, c, Z)), h && (Be = lf(Be, h, _, Z)), be -= kt, Z && be < V) {
            var Xe = Fn(Be, ht);
            return gf(
              e,
              t,
              uo,
              oe.placeholder,
              i,
              Be,
              Xe,
              A,
              T,
              V - be
            );
          }
          var en = M ? i : this, Sn = G ? en[e] : e;
          return be = Be.length, A ? Be = zm(Be, A) : ae && be > 1 && Be.reverse(), D && T < be && (Be.length = T), this && this !== at && this instanceof oe && (Sn = he || da(Sn)), Sn.apply(en, Be);
        }
        return oe;
      }
      function pf(e, t) {
        return function(i, u) {
          return Kh(i, e, t(u), {});
        };
      }
      function fo(e, t) {
        return function(i, u) {
          var c;
          if (i === n && u === n)
            return t;
          if (i !== n && (c = i), u !== n) {
            if (c === n)
              return u;
            typeof i == "string" || typeof u == "string" ? (i = Ct(i), u = Ct(u)) : (i = Yu(i), u = Yu(u)), c = e(i, u);
          }
          return c;
        };
      }
      function rl(e) {
        return bn(function(t) {
          return t = He(t, At(re())), ye(function(i) {
            var u = this;
            return e(t, function(c) {
              return Bt(c, u, i);
            });
          });
        });
      }
      function co(e, t) {
        t = t === n ? " " : Ct(t);
        var i = t.length;
        if (i < 2)
          return i ? Xi(t, e) : t;
        var u = Xi(t, Ya(e / hr(t)));
        return gr(t) ? Vn(Yt(u), 0, e).join("") : u.slice(0, e);
      }
      function wm(e, t, i, u) {
        var c = t & B, h = da(e);
        function _() {
          for (var A = -1, T = arguments.length, V = -1, D = u.length, M = R(D + T), G = this && this !== at && this instanceof _ ? h : e; ++V < D; )
            M[V] = u[V];
          for (; T--; )
            M[V++] = arguments[++A];
          return Bt(G, c ? i : this, M);
        }
        return _;
      }
      function vf(e) {
        return function(t, i, u) {
          return u && typeof u != "number" && gt(t, i, u) && (i = u = n), t = xn(t), i === n ? (i = t, t = 0) : i = xn(i), u = u === n ? t < i ? 1 : -1 : xn(u), im(t, i, u, e);
        };
      }
      function po(e) {
        return function(t, i) {
          return typeof t == "string" && typeof i == "string" || (t = Ut(t), i = Ut(i)), e(t, i);
        };
      }
      function gf(e, t, i, u, c, h, _, A, T, V) {
        var D = t & L, M = D ? _ : n, G = D ? n : _, Z = D ? h : n, ae = D ? n : h;
        t |= D ? k : z, t &= ~(D ? z : k), t & F || (t &= -4);
        var he = [
          e,
          t,
          c,
          Z,
          M,
          ae,
          G,
          A,
          T,
          V
        ], oe = i.apply(n, he);
        return dl(e) && Tf(oe, he), oe.placeholder = u, Ef(oe, e, t);
      }
      function al(e) {
        var t = Ze[e];
        return function(i, u) {
          if (i = Ut(i), u = u == null ? 0 : lt(ve(u), 292), u && Tu(i)) {
            var c = (Oe(i) + "e").split("e"), h = t(c[0] + "e" + (+c[1] + u));
            return c = (Oe(h) + "e").split("e"), +(c[0] + "e" + (+c[1] - u));
          }
          return t(i);
        };
      }
      var xm = br && 1 / Ma(new br([, -0]))[1] == Se ? function(e) {
        return new br(e);
      } : Cl;
      function hf(e) {
        return function(t) {
          var i = st(t);
          return i == Jt ? Pi(t) : i == Xt ? Dg(t) : $g(t, e(t));
        };
      }
      function yn(e, t, i, u, c, h, _, A) {
        var T = t & E;
        if (!T && typeof e != "function")
          throw new Vt(f);
        var V = u ? u.length : 0;
        if (V || (t &= -97, u = c = n), _ = _ === n ? _ : Qe(ve(_), 0), A = A === n ? A : ve(A), V -= c ? c.length : 0, t & z) {
          var D = u, M = c;
          u = c = n;
        }
        var G = T ? n : ll(e), Z = [
          e,
          t,
          i,
          u,
          c,
          D,
          M,
          h,
          _,
          A
        ];
        if (G && Nm(Z, G), e = Z[0], t = Z[1], i = Z[2], u = Z[3], c = Z[4], A = Z[9] = Z[9] === n ? T ? 0 : e.length : Qe(Z[9] - V, 0), !A && t & (L | $) && (t &= -25), !t || t == B)
          var ae = bm(e, t, i);
        else t == L || t == $ ? ae = _m(e, t, A) : (t == k || t == (B | k)) && !c.length ? ae = wm(e, t, i, u) : ae = uo.apply(n, Z);
        var he = G ? Ju : Tf;
        return Ef(he(ae, Z), e, t);
      }
      function mf(e, t, i, u) {
        return e === n || Qt(e, yr[i]) && !Fe.call(u, i) ? t : e;
      }
      function yf(e, t, i, u, c, h) {
        return We(e) && We(t) && (h.set(t, e), oo(e, t, n, yf, h), h.delete(t)), e;
      }
      function Sm(e) {
        return va(e) ? n : e;
      }
      function bf(e, t, i, u, c, h) {
        var _ = i & y, A = e.length, T = t.length;
        if (A != T && !(_ && T > A))
          return !1;
        var V = h.get(e), D = h.get(t);
        if (V && D)
          return V == t && D == e;
        var M = -1, G = !0, Z = i & x ? new Jn() : n;
        for (h.set(e, t), h.set(t, e); ++M < A; ) {
          var ae = e[M], he = t[M];
          if (u)
            var oe = _ ? u(he, ae, M, t, e, h) : u(ae, he, M, e, t, h);
          if (oe !== n) {
            if (oe)
              continue;
            G = !1;
            break;
          }
          if (Z) {
            if (!Ei(t, function(be, Be) {
              if (!ea(Z, Be) && (ae === be || c(ae, be, i, u, h)))
                return Z.push(Be);
            })) {
              G = !1;
              break;
            }
          } else if (!(ae === he || c(ae, he, i, u, h))) {
            G = !1;
            break;
          }
        }
        return h.delete(e), h.delete(t), G;
      }
      function Bm(e, t, i, u, c, h, _) {
        switch (i) {
          case cr:
            if (e.byteLength != t.byteLength || e.byteOffset != t.byteOffset)
              return !1;
            e = e.buffer, t = t.buffer;
          case Qr:
            return !(e.byteLength != t.byteLength || !h(new ja(e), new ja(t)));
          case pt:
          case le:
          case Jr:
            return Qt(+e, +t);
          case qn:
            return e.name == t.name && e.message == t.message;
          case Xr:
          case Yr:
            return e == t + "";
          case Jt:
            var A = Pi;
          case Xt:
            var T = u & y;
            if (A || (A = Ma), e.size != t.size && !T)
              return !1;
            var V = _.get(e);
            if (V)
              return V == t;
            u |= x, _.set(e, t);
            var D = bf(A(e), A(t), u, c, h, _);
            return _.delete(e), D;
          case La:
            if (oa)
              return oa.call(e) == oa.call(t);
        }
        return !1;
      }
      function Am(e, t, i, u, c, h) {
        var _ = i & y, A = ol(e), T = A.length, V = ol(t), D = V.length;
        if (T != D && !_)
          return !1;
        for (var M = T; M--; ) {
          var G = A[M];
          if (!(_ ? G in t : Fe.call(t, G)))
            return !1;
        }
        var Z = h.get(e), ae = h.get(t);
        if (Z && ae)
          return Z == t && ae == e;
        var he = !0;
        h.set(e, t), h.set(t, e);
        for (var oe = _; ++M < T; ) {
          G = A[M];
          var be = e[G], Be = t[G];
          if (u)
            var Et = _ ? u(Be, be, G, t, e, h) : u(be, Be, G, e, t, h);
          if (!(Et === n ? be === Be || c(be, Be, i, u, h) : Et)) {
            he = !1;
            break;
          }
          oe || (oe = G == "constructor");
        }
        if (he && !oe) {
          var ht = e.constructor, kt = t.constructor;
          ht != kt && "constructor" in e && "constructor" in t && !(typeof ht == "function" && ht instanceof ht && typeof kt == "function" && kt instanceof kt) && (he = !1);
        }
        return h.delete(e), h.delete(t), he;
      }
      function bn(e) {
        return pl(Af(e, n, Pf), e + "");
      }
      function ol(e) {
        return Vu(e, tt, ul);
      }
      function il(e) {
        return Vu(e, _t, _f);
      }
      var ll = Qa ? function(e) {
        return Qa.get(e);
      } : Cl;
      function vo(e) {
        for (var t = e.name + "", i = _r[t], u = Fe.call(_r, t) ? i.length : 0; u--; ) {
          var c = i[u], h = c.func;
          if (h == null || h == e)
            return c.name;
        }
        return t;
      }
      function Br(e) {
        var t = Fe.call(v, "placeholder") ? v : e;
        return t.placeholder;
      }
      function re() {
        var e = v.iteratee || Bl;
        return e = e === Bl ? Mu : e, arguments.length ? e(arguments[0], arguments[1]) : e;
      }
      function go(e, t) {
        var i = e.__data__;
        return Fm(t) ? i[typeof t == "string" ? "string" : "hash"] : i.map;
      }
      function sl(e) {
        for (var t = tt(e), i = t.length; i--; ) {
          var u = t[i], c = e[u];
          t[i] = [u, c, Sf(c)];
        }
        return t;
      }
      function Zn(e, t) {
        var i = Ig(e, t);
        return zu(i) ? i : n;
      }
      function Cm(e) {
        var t = Fe.call(e, jn), i = e[jn];
        try {
          e[jn] = n;
          var u = !0;
        } catch {
        }
        var c = qa.call(e);
        return u && (t ? e[jn] = i : delete e[jn]), c;
      }
      var ul = Ii ? function(e) {
        return e == null ? [] : (e = Le(e), $n(Ii(e), function(t) {
          return Au.call(e, t);
        }));
      } : Tl, _f = Ii ? function(e) {
        for (var t = []; e; )
          Rn(t, ul(e)), e = Ka(e);
        return t;
      } : Tl, st = vt;
      (Ni && st(new Ni(new ArrayBuffer(1))) != cr || na && st(new na()) != Jt || Vi && st(Vi.resolve()) != Ls || br && st(new br()) != Xt || ra && st(new ra()) != Zr) && (st = function(e) {
        var t = vt(e), i = t == vn ? e.constructor : n, u = i ? Qn(i) : "";
        if (u)
          switch (u) {
            case sh:
              return cr;
            case uh:
              return Jt;
            case fh:
              return Ls;
            case dh:
              return Xt;
            case ch:
              return Zr;
          }
        return t;
      });
      function Tm(e, t, i) {
        for (var u = -1, c = i.length; ++u < c; ) {
          var h = i[u], _ = h.size;
          switch (h.type) {
            case "drop":
              e += _;
              break;
            case "dropRight":
              t -= _;
              break;
            case "take":
              t = lt(t, e + _);
              break;
            case "takeRight":
              e = Qe(e, t - _);
              break;
          }
        }
        return { start: e, end: t };
      }
      function Em(e) {
        var t = e.match(Pv);
        return t ? t[1].split(Lv) : [];
      }
      function wf(e, t, i) {
        t = Nn(t, e);
        for (var u = -1, c = t.length, h = !1; ++u < c; ) {
          var _ = sn(t[u]);
          if (!(h = e != null && i(e, _)))
            break;
          e = e[_];
        }
        return h || ++u != c ? h : (c = e == null ? 0 : e.length, !!c && xo(c) && _n(_, c) && (fe(e) || er(e)));
      }
      function km(e) {
        var t = e.length, i = new e.constructor(t);
        return t && typeof e[0] == "string" && Fe.call(e, "index") && (i.index = e.index, i.input = e.input), i;
      }
      function xf(e) {
        return typeof e.constructor == "function" && !ca(e) ? wr(Ka(e)) : {};
      }
      function Om(e, t, i) {
        var u = e.constructor;
        switch (t) {
          case Qr:
            return nl(e);
          case pt:
          case le:
            return new u(+e);
          case cr:
            return pm(e, i);
          case si:
          case ui:
          case fi:
          case di:
          case ci:
          case pi:
          case vi:
          case gi:
          case hi:
            return rf(e, i);
          case Jt:
            return new u();
          case Jr:
          case Yr:
            return new u(e);
          case Xr:
            return vm(e);
          case Xt:
            return new u();
          case La:
            return gm(e);
        }
      }
      function $m(e, t) {
        var i = t.length;
        if (!i)
          return e;
        var u = i - 1;
        return t[u] = (i > 1 ? "& " : "") + t[u], t = t.join(i > 2 ? ", " : " "), e.replace(Fv, `{
/* [wrapped with ` + t + `] */
`);
      }
      function Rm(e) {
        return fe(e) || er(e) || !!(Cu && e && e[Cu]);
      }
      function _n(e, t) {
        var i = typeof e;
        return t = t ?? pe, !!t && (i == "number" || i != "symbol" && Wv.test(e)) && e > -1 && e % 1 == 0 && e < t;
      }
      function gt(e, t, i) {
        if (!We(i))
          return !1;
        var u = typeof t;
        return (u == "number" ? bt(i) && _n(t, i.length) : u == "string" && t in i) ? Qt(i[t], e) : !1;
      }
      function fl(e, t) {
        if (fe(e))
          return !1;
        var i = typeof e;
        return i == "number" || i == "symbol" || i == "boolean" || e == null || Tt(e) ? !0 : kv.test(e) || !Ev.test(e) || t != null && e in Le(t);
      }
      function Fm(e) {
        var t = typeof e;
        return t == "string" || t == "number" || t == "symbol" || t == "boolean" ? e !== "__proto__" : e === null;
      }
      function dl(e) {
        var t = vo(e), i = v[t];
        if (typeof i != "function" || !(t in _e.prototype))
          return !1;
        if (e === i)
          return !0;
        var u = ll(i);
        return !!u && e === u[0];
      }
      function Pm(e) {
        return !!xu && xu in e;
      }
      var Lm = Ua ? wn : El;
      function ca(e) {
        var t = e && e.constructor, i = typeof t == "function" && t.prototype || yr;
        return e === i;
      }
      function Sf(e) {
        return e === e && !We(e);
      }
      function Bf(e, t) {
        return function(i) {
          return i == null ? !1 : i[e] === t && (t !== n || e in Le(i));
        };
      }
      function Im(e) {
        var t = _o(e, function(u) {
          return i.size === g && i.clear(), u;
        }), i = t.cache;
        return t;
      }
      function Nm(e, t) {
        var i = e[1], u = t[1], c = i | u, h = c < (B | E | H), _ = u == H && i == L || u == H && i == ie && e[7].length <= t[8] || u == (H | ie) && t[7].length <= t[8] && i == L;
        if (!(h || _))
          return e;
        u & B && (e[2] = t[2], c |= i & B ? 0 : F);
        var A = t[3];
        if (A) {
          var T = e[3];
          e[3] = T ? of(T, A, t[4]) : A, e[4] = T ? Fn(e[3], m) : t[4];
        }
        return A = t[5], A && (T = e[5], e[5] = T ? lf(T, A, t[6]) : A, e[6] = T ? Fn(e[5], m) : t[6]), A = t[7], A && (e[7] = A), u & H && (e[8] = e[8] == null ? t[8] : lt(e[8], t[8])), e[9] == null && (e[9] = t[9]), e[0] = t[0], e[1] = c, e;
      }
      function Vm(e) {
        var t = [];
        if (e != null)
          for (var i in Le(e))
            t.push(i);
        return t;
      }
      function Dm(e) {
        return qa.call(e);
      }
      function Af(e, t, i) {
        return t = Qe(t === n ? e.length - 1 : t, 0), function() {
          for (var u = arguments, c = -1, h = Qe(u.length - t, 0), _ = R(h); ++c < h; )
            _[c] = u[t + c];
          c = -1;
          for (var A = R(t + 1); ++c < t; )
            A[c] = u[c];
          return A[t] = i(_), Bt(e, this, A);
        };
      }
      function Cf(e, t) {
        return t.length < 2 ? e : Yn(e, Mt(t, 0, -1));
      }
      function zm(e, t) {
        for (var i = e.length, u = lt(t.length, i), c = yt(e); u--; ) {
          var h = t[u];
          e[u] = _n(h, i) ? c[h] : n;
        }
        return e;
      }
      function cl(e, t) {
        if (!(t === "constructor" && typeof e[t] == "function") && t != "__proto__")
          return e[t];
      }
      var Tf = kf(Ju), pa = th || function(e, t) {
        return at.setTimeout(e, t);
      }, pl = kf(um);
      function Ef(e, t, i) {
        var u = t + "";
        return pl(e, $m(u, Mm(Em(u), i)));
      }
      function kf(e) {
        var t = 0, i = 0;
        return function() {
          var u = oh(), c = Re - (u - i);
          if (i = u, c > 0) {
            if (++t >= Ce)
              return arguments[0];
          } else
            t = 0;
          return e.apply(n, arguments);
        };
      }
      function ho(e, t) {
        var i = -1, u = e.length, c = u - 1;
        for (t = t === n ? u : t; ++i < t; ) {
          var h = Ji(i, c), _ = e[h];
          e[h] = e[i], e[i] = _;
        }
        return e.length = t, e;
      }
      var Of = Im(function(e) {
        var t = [];
        return e.charCodeAt(0) === 46 && t.push(""), e.replace(Ov, function(i, u, c, h) {
          t.push(c ? h.replace(Vv, "$1") : u || i);
        }), t;
      });
      function sn(e) {
        if (typeof e == "string" || Tt(e))
          return e;
        var t = e + "";
        return t == "0" && 1 / e == -1 / 0 ? "-0" : t;
      }
      function Qn(e) {
        if (e != null) {
          try {
            return Wa.call(e);
          } catch {
          }
          try {
            return e + "";
          } catch {
          }
        }
        return "";
      }
      function Mm(e, t) {
        return Nt(me, function(i) {
          var u = "_." + i[0];
          t & i[1] && !Da(e, u) && e.push(u);
        }), e.sort();
      }
      function $f(e) {
        if (e instanceof _e)
          return e.clone();
        var t = new Dt(e.__wrapped__, e.__chain__);
        return t.__actions__ = yt(e.__actions__), t.__index__ = e.__index__, t.__values__ = e.__values__, t;
      }
      function Hm(e, t, i) {
        (i ? gt(e, t, i) : t === n) ? t = 1 : t = Qe(ve(t), 0);
        var u = e == null ? 0 : e.length;
        if (!u || t < 1)
          return [];
        for (var c = 0, h = 0, _ = R(Ya(u / t)); c < u; )
          _[h++] = Mt(e, c, c += t);
        return _;
      }
      function Um(e) {
        for (var t = -1, i = e == null ? 0 : e.length, u = 0, c = []; ++t < i; ) {
          var h = e[t];
          h && (c[u++] = h);
        }
        return c;
      }
      function Wm() {
        var e = arguments.length;
        if (!e)
          return [];
        for (var t = R(e - 1), i = arguments[0], u = e; u--; )
          t[u - 1] = arguments[u];
        return Rn(fe(i) ? yt(i) : [i], ot(t, 1));
      }
      var qm = ye(function(e, t) {
        return Je(e) ? la(e, ot(t, 1, Je, !0)) : [];
      }), Gm = ye(function(e, t) {
        var i = Ht(t);
        return Je(i) && (i = n), Je(e) ? la(e, ot(t, 1, Je, !0), re(i, 2)) : [];
      }), jm = ye(function(e, t) {
        var i = Ht(t);
        return Je(i) && (i = n), Je(e) ? la(e, ot(t, 1, Je, !0), n, i) : [];
      });
      function Km(e, t, i) {
        var u = e == null ? 0 : e.length;
        return u ? (t = i || t === n ? 1 : ve(t), Mt(e, t < 0 ? 0 : t, u)) : [];
      }
      function Jm(e, t, i) {
        var u = e == null ? 0 : e.length;
        return u ? (t = i || t === n ? 1 : ve(t), t = u - t, Mt(e, 0, t < 0 ? 0 : t)) : [];
      }
      function Xm(e, t) {
        return e && e.length ? lo(e, re(t, 3), !0, !0) : [];
      }
      function Ym(e, t) {
        return e && e.length ? lo(e, re(t, 3), !0) : [];
      }
      function Zm(e, t, i, u) {
        var c = e == null ? 0 : e.length;
        return c ? (i && typeof i != "number" && gt(e, t, i) && (i = 0, u = c), Wh(e, t, i, u)) : [];
      }
      function Rf(e, t, i) {
        var u = e == null ? 0 : e.length;
        if (!u)
          return -1;
        var c = i == null ? 0 : ve(i);
        return c < 0 && (c = Qe(u + c, 0)), za(e, re(t, 3), c);
      }
      function Ff(e, t, i) {
        var u = e == null ? 0 : e.length;
        if (!u)
          return -1;
        var c = u - 1;
        return i !== n && (c = ve(i), c = i < 0 ? Qe(u + c, 0) : lt(c, u - 1)), za(e, re(t, 3), c, !0);
      }
      function Pf(e) {
        var t = e == null ? 0 : e.length;
        return t ? ot(e, 1) : [];
      }
      function Qm(e) {
        var t = e == null ? 0 : e.length;
        return t ? ot(e, Se) : [];
      }
      function ey(e, t) {
        var i = e == null ? 0 : e.length;
        return i ? (t = t === n ? 1 : ve(t), ot(e, t)) : [];
      }
      function ty(e) {
        for (var t = -1, i = e == null ? 0 : e.length, u = {}; ++t < i; ) {
          var c = e[t];
          u[c[0]] = c[1];
        }
        return u;
      }
      function Lf(e) {
        return e && e.length ? e[0] : n;
      }
      function ny(e, t, i) {
        var u = e == null ? 0 : e.length;
        if (!u)
          return -1;
        var c = i == null ? 0 : ve(i);
        return c < 0 && (c = Qe(u + c, 0)), vr(e, t, c);
      }
      function ry(e) {
        var t = e == null ? 0 : e.length;
        return t ? Mt(e, 0, -1) : [];
      }
      var ay = ye(function(e) {
        var t = He(e, el);
        return t.length && t[0] === e[0] ? Wi(t) : [];
      }), oy = ye(function(e) {
        var t = Ht(e), i = He(e, el);
        return t === Ht(i) ? t = n : i.pop(), i.length && i[0] === e[0] ? Wi(i, re(t, 2)) : [];
      }), iy = ye(function(e) {
        var t = Ht(e), i = He(e, el);
        return t = typeof t == "function" ? t : n, t && i.pop(), i.length && i[0] === e[0] ? Wi(i, n, t) : [];
      });
      function ly(e, t) {
        return e == null ? "" : rh.call(e, t);
      }
      function Ht(e) {
        var t = e == null ? 0 : e.length;
        return t ? e[t - 1] : n;
      }
      function sy(e, t, i) {
        var u = e == null ? 0 : e.length;
        if (!u)
          return -1;
        var c = u;
        return i !== n && (c = ve(i), c = c < 0 ? Qe(u + c, 0) : lt(c, u - 1)), t === t ? Mg(e, t, c) : za(e, vu, c, !0);
      }
      function uy(e, t) {
        return e && e.length ? qu(e, ve(t)) : n;
      }
      var fy = ye(If);
      function If(e, t) {
        return e && e.length && t && t.length ? Ki(e, t) : e;
      }
      function dy(e, t, i) {
        return e && e.length && t && t.length ? Ki(e, t, re(i, 2)) : e;
      }
      function cy(e, t, i) {
        return e && e.length && t && t.length ? Ki(e, t, n, i) : e;
      }
      var py = bn(function(e, t) {
        var i = e == null ? 0 : e.length, u = zi(e, t);
        return Ku(e, He(t, function(c) {
          return _n(c, i) ? +c : c;
        }).sort(af)), u;
      });
      function vy(e, t) {
        var i = [];
        if (!(e && e.length))
          return i;
        var u = -1, c = [], h = e.length;
        for (t = re(t, 3); ++u < h; ) {
          var _ = e[u];
          t(_, u, e) && (i.push(_), c.push(u));
        }
        return Ku(e, c), i;
      }
      function vl(e) {
        return e == null ? e : lh.call(e);
      }
      function gy(e, t, i) {
        var u = e == null ? 0 : e.length;
        return u ? (i && typeof i != "number" && gt(e, t, i) ? (t = 0, i = u) : (t = t == null ? 0 : ve(t), i = i === n ? u : ve(i)), Mt(e, t, i)) : [];
      }
      function hy(e, t) {
        return io(e, t);
      }
      function my(e, t, i) {
        return Yi(e, t, re(i, 2));
      }
      function yy(e, t) {
        var i = e == null ? 0 : e.length;
        if (i) {
          var u = io(e, t);
          if (u < i && Qt(e[u], t))
            return u;
        }
        return -1;
      }
      function by(e, t) {
        return io(e, t, !0);
      }
      function _y(e, t, i) {
        return Yi(e, t, re(i, 2), !0);
      }
      function wy(e, t) {
        var i = e == null ? 0 : e.length;
        if (i) {
          var u = io(e, t, !0) - 1;
          if (Qt(e[u], t))
            return u;
        }
        return -1;
      }
      function xy(e) {
        return e && e.length ? Xu(e) : [];
      }
      function Sy(e, t) {
        return e && e.length ? Xu(e, re(t, 2)) : [];
      }
      function By(e) {
        var t = e == null ? 0 : e.length;
        return t ? Mt(e, 1, t) : [];
      }
      function Ay(e, t, i) {
        return e && e.length ? (t = i || t === n ? 1 : ve(t), Mt(e, 0, t < 0 ? 0 : t)) : [];
      }
      function Cy(e, t, i) {
        var u = e == null ? 0 : e.length;
        return u ? (t = i || t === n ? 1 : ve(t), t = u - t, Mt(e, t < 0 ? 0 : t, u)) : [];
      }
      function Ty(e, t) {
        return e && e.length ? lo(e, re(t, 3), !1, !0) : [];
      }
      function Ey(e, t) {
        return e && e.length ? lo(e, re(t, 3)) : [];
      }
      var ky = ye(function(e) {
        return In(ot(e, 1, Je, !0));
      }), Oy = ye(function(e) {
        var t = Ht(e);
        return Je(t) && (t = n), In(ot(e, 1, Je, !0), re(t, 2));
      }), $y = ye(function(e) {
        var t = Ht(e);
        return t = typeof t == "function" ? t : n, In(ot(e, 1, Je, !0), n, t);
      });
      function Ry(e) {
        return e && e.length ? In(e) : [];
      }
      function Fy(e, t) {
        return e && e.length ? In(e, re(t, 2)) : [];
      }
      function Py(e, t) {
        return t = typeof t == "function" ? t : n, e && e.length ? In(e, n, t) : [];
      }
      function gl(e) {
        if (!(e && e.length))
          return [];
        var t = 0;
        return e = $n(e, function(i) {
          if (Je(i))
            return t = Qe(i.length, t), !0;
        }), Ri(t, function(i) {
          return He(e, ki(i));
        });
      }
      function Nf(e, t) {
        if (!(e && e.length))
          return [];
        var i = gl(e);
        return t == null ? i : He(i, function(u) {
          return Bt(t, n, u);
        });
      }
      var Ly = ye(function(e, t) {
        return Je(e) ? la(e, t) : [];
      }), Iy = ye(function(e) {
        return Qi($n(e, Je));
      }), Ny = ye(function(e) {
        var t = Ht(e);
        return Je(t) && (t = n), Qi($n(e, Je), re(t, 2));
      }), Vy = ye(function(e) {
        var t = Ht(e);
        return t = typeof t == "function" ? t : n, Qi($n(e, Je), n, t);
      }), Dy = ye(gl);
      function zy(e, t) {
        return ef(e || [], t || [], ia);
      }
      function My(e, t) {
        return ef(e || [], t || [], fa);
      }
      var Hy = ye(function(e) {
        var t = e.length, i = t > 1 ? e[t - 1] : n;
        return i = typeof i == "function" ? (e.pop(), i) : n, Nf(e, i);
      });
      function Vf(e) {
        var t = v(e);
        return t.__chain__ = !0, t;
      }
      function Uy(e, t) {
        return t(e), e;
      }
      function mo(e, t) {
        return t(e);
      }
      var Wy = bn(function(e) {
        var t = e.length, i = t ? e[0] : 0, u = this.__wrapped__, c = function(h) {
          return zi(h, e);
        };
        return t > 1 || this.__actions__.length || !(u instanceof _e) || !_n(i) ? this.thru(c) : (u = u.slice(i, +i + (t ? 1 : 0)), u.__actions__.push({
          func: mo,
          args: [c],
          thisArg: n
        }), new Dt(u, this.__chain__).thru(function(h) {
          return t && !h.length && h.push(n), h;
        }));
      });
      function qy() {
        return Vf(this);
      }
      function Gy() {
        return new Dt(this.value(), this.__chain__);
      }
      function jy() {
        this.__values__ === n && (this.__values__ = Zf(this.value()));
        var e = this.__index__ >= this.__values__.length, t = e ? n : this.__values__[this.__index__++];
        return { done: e, value: t };
      }
      function Ky() {
        return this;
      }
      function Jy(e) {
        for (var t, i = this; i instanceof to; ) {
          var u = $f(i);
          u.__index__ = 0, u.__values__ = n, t ? c.__wrapped__ = u : t = u;
          var c = u;
          i = i.__wrapped__;
        }
        return c.__wrapped__ = e, t;
      }
      function Xy() {
        var e = this.__wrapped__;
        if (e instanceof _e) {
          var t = e;
          return this.__actions__.length && (t = new _e(this)), t = t.reverse(), t.__actions__.push({
            func: mo,
            args: [vl],
            thisArg: n
          }), new Dt(t, this.__chain__);
        }
        return this.thru(vl);
      }
      function Yy() {
        return Qu(this.__wrapped__, this.__actions__);
      }
      var Zy = so(function(e, t, i) {
        Fe.call(e, i) ? ++e[i] : mn(e, i, 1);
      });
      function Qy(e, t, i) {
        var u = fe(e) ? cu : Uh;
        return i && gt(e, t, i) && (t = n), u(e, re(t, 3));
      }
      function eb(e, t) {
        var i = fe(e) ? $n : Iu;
        return i(e, re(t, 3));
      }
      var tb = df(Rf), nb = df(Ff);
      function rb(e, t) {
        return ot(yo(e, t), 1);
      }
      function ab(e, t) {
        return ot(yo(e, t), Se);
      }
      function ob(e, t, i) {
        return i = i === n ? 1 : ve(i), ot(yo(e, t), i);
      }
      function Df(e, t) {
        var i = fe(e) ? Nt : Ln;
        return i(e, re(t, 3));
      }
      function zf(e, t) {
        var i = fe(e) ? Bg : Lu;
        return i(e, re(t, 3));
      }
      var ib = so(function(e, t, i) {
        Fe.call(e, i) ? e[i].push(t) : mn(e, i, [t]);
      });
      function lb(e, t, i, u) {
        e = bt(e) ? e : Cr(e), i = i && !u ? ve(i) : 0;
        var c = e.length;
        return i < 0 && (i = Qe(c + i, 0)), So(e) ? i <= c && e.indexOf(t, i) > -1 : !!c && vr(e, t, i) > -1;
      }
      var sb = ye(function(e, t, i) {
        var u = -1, c = typeof t == "function", h = bt(e) ? R(e.length) : [];
        return Ln(e, function(_) {
          h[++u] = c ? Bt(t, _, i) : sa(_, t, i);
        }), h;
      }), ub = so(function(e, t, i) {
        mn(e, i, t);
      });
      function yo(e, t) {
        var i = fe(e) ? He : Hu;
        return i(e, re(t, 3));
      }
      function fb(e, t, i, u) {
        return e == null ? [] : (fe(t) || (t = t == null ? [] : [t]), i = u ? n : i, fe(i) || (i = i == null ? [] : [i]), Gu(e, t, i));
      }
      var db = so(function(e, t, i) {
        e[i ? 0 : 1].push(t);
      }, function() {
        return [[], []];
      });
      function cb(e, t, i) {
        var u = fe(e) ? Ti : hu, c = arguments.length < 3;
        return u(e, re(t, 4), i, c, Ln);
      }
      function pb(e, t, i) {
        var u = fe(e) ? Ag : hu, c = arguments.length < 3;
        return u(e, re(t, 4), i, c, Lu);
      }
      function vb(e, t) {
        var i = fe(e) ? $n : Iu;
        return i(e, wo(re(t, 3)));
      }
      function gb(e) {
        var t = fe(e) ? $u : lm;
        return t(e);
      }
      function hb(e, t, i) {
        (i ? gt(e, t, i) : t === n) ? t = 1 : t = ve(t);
        var u = fe(e) ? Vh : sm;
        return u(e, t);
      }
      function mb(e) {
        var t = fe(e) ? Dh : fm;
        return t(e);
      }
      function yb(e) {
        if (e == null)
          return 0;
        if (bt(e))
          return So(e) ? hr(e) : e.length;
        var t = st(e);
        return t == Jt || t == Xt ? e.size : Gi(e).length;
      }
      function bb(e, t, i) {
        var u = fe(e) ? Ei : dm;
        return i && gt(e, t, i) && (t = n), u(e, re(t, 3));
      }
      var _b = ye(function(e, t) {
        if (e == null)
          return [];
        var i = t.length;
        return i > 1 && gt(e, t[0], t[1]) ? t = [] : i > 2 && gt(t[0], t[1], t[2]) && (t = [t[0]]), Gu(e, ot(t, 1), []);
      }), bo = eh || function() {
        return at.Date.now();
      };
      function wb(e, t) {
        if (typeof t != "function")
          throw new Vt(f);
        return e = ve(e), function() {
          if (--e < 1)
            return t.apply(this, arguments);
        };
      }
      function Mf(e, t, i) {
        return t = i ? n : t, t = e && t == null ? e.length : t, yn(e, H, n, n, n, n, t);
      }
      function Hf(e, t) {
        var i;
        if (typeof t != "function")
          throw new Vt(f);
        return e = ve(e), function() {
          return --e > 0 && (i = t.apply(this, arguments)), e <= 1 && (t = n), i;
        };
      }
      var hl = ye(function(e, t, i) {
        var u = B;
        if (i.length) {
          var c = Fn(i, Br(hl));
          u |= k;
        }
        return yn(e, u, t, i, c);
      }), Uf = ye(function(e, t, i) {
        var u = B | E;
        if (i.length) {
          var c = Fn(i, Br(Uf));
          u |= k;
        }
        return yn(t, u, e, i, c);
      });
      function Wf(e, t, i) {
        t = i ? n : t;
        var u = yn(e, L, n, n, n, n, n, t);
        return u.placeholder = Wf.placeholder, u;
      }
      function qf(e, t, i) {
        t = i ? n : t;
        var u = yn(e, $, n, n, n, n, n, t);
        return u.placeholder = qf.placeholder, u;
      }
      function Gf(e, t, i) {
        var u, c, h, _, A, T, V = 0, D = !1, M = !1, G = !0;
        if (typeof e != "function")
          throw new Vt(f);
        t = Ut(t) || 0, We(i) && (D = !!i.leading, M = "maxWait" in i, h = M ? Qe(Ut(i.maxWait) || 0, t) : h, G = "trailing" in i ? !!i.trailing : G);
        function Z(Xe) {
          var en = u, Sn = c;
          return u = c = n, V = Xe, _ = e.apply(Sn, en), _;
        }
        function ae(Xe) {
          return V = Xe, A = pa(be, t), D ? Z(Xe) : _;
        }
        function he(Xe) {
          var en = Xe - T, Sn = Xe - V, dd = t - en;
          return M ? lt(dd, h - Sn) : dd;
        }
        function oe(Xe) {
          var en = Xe - T, Sn = Xe - V;
          return T === n || en >= t || en < 0 || M && Sn >= h;
        }
        function be() {
          var Xe = bo();
          if (oe(Xe))
            return Be(Xe);
          A = pa(be, he(Xe));
        }
        function Be(Xe) {
          return A = n, G && u ? Z(Xe) : (u = c = n, _);
        }
        function Et() {
          A !== n && tf(A), V = 0, u = T = c = A = n;
        }
        function ht() {
          return A === n ? _ : Be(bo());
        }
        function kt() {
          var Xe = bo(), en = oe(Xe);
          if (u = arguments, c = this, T = Xe, en) {
            if (A === n)
              return ae(T);
            if (M)
              return tf(A), A = pa(be, t), Z(T);
          }
          return A === n && (A = pa(be, t)), _;
        }
        return kt.cancel = Et, kt.flush = ht, kt;
      }
      var xb = ye(function(e, t) {
        return Pu(e, 1, t);
      }), Sb = ye(function(e, t, i) {
        return Pu(e, Ut(t) || 0, i);
      });
      function Bb(e) {
        return yn(e, se);
      }
      function _o(e, t) {
        if (typeof e != "function" || t != null && typeof t != "function")
          throw new Vt(f);
        var i = function() {
          var u = arguments, c = t ? t.apply(this, u) : u[0], h = i.cache;
          if (h.has(c))
            return h.get(c);
          var _ = e.apply(this, u);
          return i.cache = h.set(c, _) || h, _;
        };
        return i.cache = new (_o.Cache || hn)(), i;
      }
      _o.Cache = hn;
      function wo(e) {
        if (typeof e != "function")
          throw new Vt(f);
        return function() {
          var t = arguments;
          switch (t.length) {
            case 0:
              return !e.call(this);
            case 1:
              return !e.call(this, t[0]);
            case 2:
              return !e.call(this, t[0], t[1]);
            case 3:
              return !e.call(this, t[0], t[1], t[2]);
          }
          return !e.apply(this, t);
        };
      }
      function Ab(e) {
        return Hf(2, e);
      }
      var Cb = cm(function(e, t) {
        t = t.length == 1 && fe(t[0]) ? He(t[0], At(re())) : He(ot(t, 1), At(re()));
        var i = t.length;
        return ye(function(u) {
          for (var c = -1, h = lt(u.length, i); ++c < h; )
            u[c] = t[c].call(this, u[c]);
          return Bt(e, this, u);
        });
      }), ml = ye(function(e, t) {
        var i = Fn(t, Br(ml));
        return yn(e, k, n, t, i);
      }), jf = ye(function(e, t) {
        var i = Fn(t, Br(jf));
        return yn(e, z, n, t, i);
      }), Tb = bn(function(e, t) {
        return yn(e, ie, n, n, n, t);
      });
      function Eb(e, t) {
        if (typeof e != "function")
          throw new Vt(f);
        return t = t === n ? t : ve(t), ye(e, t);
      }
      function kb(e, t) {
        if (typeof e != "function")
          throw new Vt(f);
        return t = t == null ? 0 : Qe(ve(t), 0), ye(function(i) {
          var u = i[t], c = Vn(i, 0, t);
          return u && Rn(c, u), Bt(e, this, c);
        });
      }
      function Ob(e, t, i) {
        var u = !0, c = !0;
        if (typeof e != "function")
          throw new Vt(f);
        return We(i) && (u = "leading" in i ? !!i.leading : u, c = "trailing" in i ? !!i.trailing : c), Gf(e, t, {
          leading: u,
          maxWait: t,
          trailing: c
        });
      }
      function $b(e) {
        return Mf(e, 1);
      }
      function Rb(e, t) {
        return ml(tl(t), e);
      }
      function Fb() {
        if (!arguments.length)
          return [];
        var e = arguments[0];
        return fe(e) ? e : [e];
      }
      function Pb(e) {
        return zt(e, b);
      }
      function Lb(e, t) {
        return t = typeof t == "function" ? t : n, zt(e, b, t);
      }
      function Ib(e) {
        return zt(e, w | b);
      }
      function Nb(e, t) {
        return t = typeof t == "function" ? t : n, zt(e, w | b, t);
      }
      function Vb(e, t) {
        return t == null || Fu(e, t, tt(t));
      }
      function Qt(e, t) {
        return e === t || e !== e && t !== t;
      }
      var Db = po(Ui), zb = po(function(e, t) {
        return e >= t;
      }), er = Du(/* @__PURE__ */ function() {
        return arguments;
      }()) ? Du : function(e) {
        return Ge(e) && Fe.call(e, "callee") && !Au.call(e, "callee");
      }, fe = R.isArray, Mb = iu ? At(iu) : Jh;
      function bt(e) {
        return e != null && xo(e.length) && !wn(e);
      }
      function Je(e) {
        return Ge(e) && bt(e);
      }
      function Hb(e) {
        return e === !0 || e === !1 || Ge(e) && vt(e) == pt;
      }
      var Dn = nh || El, Ub = lu ? At(lu) : Xh;
      function Wb(e) {
        return Ge(e) && e.nodeType === 1 && !va(e);
      }
      function qb(e) {
        if (e == null)
          return !0;
        if (bt(e) && (fe(e) || typeof e == "string" || typeof e.splice == "function" || Dn(e) || Ar(e) || er(e)))
          return !e.length;
        var t = st(e);
        if (t == Jt || t == Xt)
          return !e.size;
        if (ca(e))
          return !Gi(e).length;
        for (var i in e)
          if (Fe.call(e, i))
            return !1;
        return !0;
      }
      function Gb(e, t) {
        return ua(e, t);
      }
      function jb(e, t, i) {
        i = typeof i == "function" ? i : n;
        var u = i ? i(e, t) : n;
        return u === n ? ua(e, t, n, i) : !!u;
      }
      function yl(e) {
        if (!Ge(e))
          return !1;
        var t = vt(e);
        return t == qn || t == qe || typeof e.message == "string" && typeof e.name == "string" && !va(e);
      }
      function Kb(e) {
        return typeof e == "number" && Tu(e);
      }
      function wn(e) {
        if (!We(e))
          return !1;
        var t = vt(e);
        return t == pn || t == Ps || t == dr || t == yv;
      }
      function Kf(e) {
        return typeof e == "number" && e == ve(e);
      }
      function xo(e) {
        return typeof e == "number" && e > -1 && e % 1 == 0 && e <= pe;
      }
      function We(e) {
        var t = typeof e;
        return e != null && (t == "object" || t == "function");
      }
      function Ge(e) {
        return e != null && typeof e == "object";
      }
      var Jf = su ? At(su) : Zh;
      function Jb(e, t) {
        return e === t || qi(e, t, sl(t));
      }
      function Xb(e, t, i) {
        return i = typeof i == "function" ? i : n, qi(e, t, sl(t), i);
      }
      function Yb(e) {
        return Xf(e) && e != +e;
      }
      function Zb(e) {
        if (Lm(e))
          throw new ue(s);
        return zu(e);
      }
      function Qb(e) {
        return e === null;
      }
      function e0(e) {
        return e == null;
      }
      function Xf(e) {
        return typeof e == "number" || Ge(e) && vt(e) == Jr;
      }
      function va(e) {
        if (!Ge(e) || vt(e) != vn)
          return !1;
        var t = Ka(e);
        if (t === null)
          return !0;
        var i = Fe.call(t, "constructor") && t.constructor;
        return typeof i == "function" && i instanceof i && Wa.call(i) == Xg;
      }
      var bl = uu ? At(uu) : Qh;
      function t0(e) {
        return Kf(e) && e >= -9007199254740991 && e <= pe;
      }
      var Yf = fu ? At(fu) : em;
      function So(e) {
        return typeof e == "string" || !fe(e) && Ge(e) && vt(e) == Yr;
      }
      function Tt(e) {
        return typeof e == "symbol" || Ge(e) && vt(e) == La;
      }
      var Ar = du ? At(du) : tm;
      function n0(e) {
        return e === n;
      }
      function r0(e) {
        return Ge(e) && st(e) == Zr;
      }
      function a0(e) {
        return Ge(e) && vt(e) == _v;
      }
      var o0 = po(ji), i0 = po(function(e, t) {
        return e <= t;
      });
      function Zf(e) {
        if (!e)
          return [];
        if (bt(e))
          return So(e) ? Yt(e) : yt(e);
        if (ta && e[ta])
          return Vg(e[ta]());
        var t = st(e), i = t == Jt ? Pi : t == Xt ? Ma : Cr;
        return i(e);
      }
      function xn(e) {
        if (!e)
          return e === 0 ? e : 0;
        if (e = Ut(e), e === Se || e === -1 / 0) {
          var t = e < 0 ? -1 : 1;
          return t * Ke;
        }
        return e === e ? e : 0;
      }
      function ve(e) {
        var t = xn(e), i = t % 1;
        return t === t ? i ? t - i : t : 0;
      }
      function Qf(e) {
        return e ? Xn(ve(e), 0, Pe) : 0;
      }
      function Ut(e) {
        if (typeof e == "number")
          return e;
        if (Tt(e))
          return Me;
        if (We(e)) {
          var t = typeof e.valueOf == "function" ? e.valueOf() : e;
          e = We(t) ? t + "" : t;
        }
        if (typeof e != "string")
          return e === 0 ? e : +e;
        e = mu(e);
        var i = Mv.test(e);
        return i || Uv.test(e) ? wg(e.slice(2), i ? 2 : 8) : zv.test(e) ? Me : +e;
      }
      function ed(e) {
        return ln(e, _t(e));
      }
      function l0(e) {
        return e ? Xn(ve(e), -9007199254740991, pe) : e === 0 ? e : 0;
      }
      function Oe(e) {
        return e == null ? "" : Ct(e);
      }
      var s0 = xr(function(e, t) {
        if (ca(t) || bt(t)) {
          ln(t, tt(t), e);
          return;
        }
        for (var i in t)
          Fe.call(t, i) && ia(e, i, t[i]);
      }), td = xr(function(e, t) {
        ln(t, _t(t), e);
      }), Bo = xr(function(e, t, i, u) {
        ln(t, _t(t), e, u);
      }), u0 = xr(function(e, t, i, u) {
        ln(t, tt(t), e, u);
      }), f0 = bn(zi);
      function d0(e, t) {
        var i = wr(e);
        return t == null ? i : Ru(i, t);
      }
      var c0 = ye(function(e, t) {
        e = Le(e);
        var i = -1, u = t.length, c = u > 2 ? t[2] : n;
        for (c && gt(t[0], t[1], c) && (u = 1); ++i < u; )
          for (var h = t[i], _ = _t(h), A = -1, T = _.length; ++A < T; ) {
            var V = _[A], D = e[V];
            (D === n || Qt(D, yr[V]) && !Fe.call(e, V)) && (e[V] = h[V]);
          }
        return e;
      }), p0 = ye(function(e) {
        return e.push(n, yf), Bt(nd, n, e);
      });
      function v0(e, t) {
        return pu(e, re(t, 3), on);
      }
      function g0(e, t) {
        return pu(e, re(t, 3), Hi);
      }
      function h0(e, t) {
        return e == null ? e : Mi(e, re(t, 3), _t);
      }
      function m0(e, t) {
        return e == null ? e : Nu(e, re(t, 3), _t);
      }
      function y0(e, t) {
        return e && on(e, re(t, 3));
      }
      function b0(e, t) {
        return e && Hi(e, re(t, 3));
      }
      function _0(e) {
        return e == null ? [] : ao(e, tt(e));
      }
      function w0(e) {
        return e == null ? [] : ao(e, _t(e));
      }
      function _l(e, t, i) {
        var u = e == null ? n : Yn(e, t);
        return u === n ? i : u;
      }
      function x0(e, t) {
        return e != null && wf(e, t, qh);
      }
      function wl(e, t) {
        return e != null && wf(e, t, Gh);
      }
      var S0 = pf(function(e, t, i) {
        t != null && typeof t.toString != "function" && (t = qa.call(t)), e[t] = i;
      }, Sl(wt)), B0 = pf(function(e, t, i) {
        t != null && typeof t.toString != "function" && (t = qa.call(t)), Fe.call(e, t) ? e[t].push(i) : e[t] = [i];
      }, re), A0 = ye(sa);
      function tt(e) {
        return bt(e) ? Ou(e) : Gi(e);
      }
      function _t(e) {
        return bt(e) ? Ou(e, !0) : nm(e);
      }
      function C0(e, t) {
        var i = {};
        return t = re(t, 3), on(e, function(u, c, h) {
          mn(i, t(u, c, h), u);
        }), i;
      }
      function T0(e, t) {
        var i = {};
        return t = re(t, 3), on(e, function(u, c, h) {
          mn(i, c, t(u, c, h));
        }), i;
      }
      var E0 = xr(function(e, t, i) {
        oo(e, t, i);
      }), nd = xr(function(e, t, i, u) {
        oo(e, t, i, u);
      }), k0 = bn(function(e, t) {
        var i = {};
        if (e == null)
          return i;
        var u = !1;
        t = He(t, function(h) {
          return h = Nn(h, e), u || (u = h.length > 1), h;
        }), ln(e, il(e), i), u && (i = zt(i, w | S | b, Sm));
        for (var c = t.length; c--; )
          Zi(i, t[c]);
        return i;
      });
      function O0(e, t) {
        return rd(e, wo(re(t)));
      }
      var $0 = bn(function(e, t) {
        return e == null ? {} : am(e, t);
      });
      function rd(e, t) {
        if (e == null)
          return {};
        var i = He(il(e), function(u) {
          return [u];
        });
        return t = re(t), ju(e, i, function(u, c) {
          return t(u, c[0]);
        });
      }
      function R0(e, t, i) {
        t = Nn(t, e);
        var u = -1, c = t.length;
        for (c || (c = 1, e = n); ++u < c; ) {
          var h = e == null ? n : e[sn(t[u])];
          h === n && (u = c, h = i), e = wn(h) ? h.call(e) : h;
        }
        return e;
      }
      function F0(e, t, i) {
        return e == null ? e : fa(e, t, i);
      }
      function P0(e, t, i, u) {
        return u = typeof u == "function" ? u : n, e == null ? e : fa(e, t, i, u);
      }
      var ad = hf(tt), od = hf(_t);
      function L0(e, t, i) {
        var u = fe(e), c = u || Dn(e) || Ar(e);
        if (t = re(t, 4), i == null) {
          var h = e && e.constructor;
          c ? i = u ? new h() : [] : We(e) ? i = wn(h) ? wr(Ka(e)) : {} : i = {};
        }
        return (c ? Nt : on)(e, function(_, A, T) {
          return t(i, _, A, T);
        }), i;
      }
      function I0(e, t) {
        return e == null ? !0 : Zi(e, t);
      }
      function N0(e, t, i) {
        return e == null ? e : Zu(e, t, tl(i));
      }
      function V0(e, t, i, u) {
        return u = typeof u == "function" ? u : n, e == null ? e : Zu(e, t, tl(i), u);
      }
      function Cr(e) {
        return e == null ? [] : Fi(e, tt(e));
      }
      function D0(e) {
        return e == null ? [] : Fi(e, _t(e));
      }
      function z0(e, t, i) {
        return i === n && (i = t, t = n), i !== n && (i = Ut(i), i = i === i ? i : 0), t !== n && (t = Ut(t), t = t === t ? t : 0), Xn(Ut(e), t, i);
      }
      function M0(e, t, i) {
        return t = xn(t), i === n ? (i = t, t = 0) : i = xn(i), e = Ut(e), jh(e, t, i);
      }
      function H0(e, t, i) {
        if (i && typeof i != "boolean" && gt(e, t, i) && (t = i = n), i === n && (typeof t == "boolean" ? (i = t, t = n) : typeof e == "boolean" && (i = e, e = n)), e === n && t === n ? (e = 0, t = 1) : (e = xn(e), t === n ? (t = e, e = 0) : t = xn(t)), e > t) {
          var u = e;
          e = t, t = u;
        }
        if (i || e % 1 || t % 1) {
          var c = Eu();
          return lt(e + c * (t - e + _g("1e-" + ((c + "").length - 1))), t);
        }
        return Ji(e, t);
      }
      var U0 = Sr(function(e, t, i) {
        return t = t.toLowerCase(), e + (i ? id(t) : t);
      });
      function id(e) {
        return xl(Oe(e).toLowerCase());
      }
      function ld(e) {
        return e = Oe(e), e && e.replace(qv, Fg).replace(fg, "");
      }
      function W0(e, t, i) {
        e = Oe(e), t = Ct(t);
        var u = e.length;
        i = i === n ? u : Xn(ve(i), 0, u);
        var c = i;
        return i -= t.length, i >= 0 && e.slice(i, c) == t;
      }
      function q0(e) {
        return e = Oe(e), e && Av.test(e) ? e.replace(Ns, Pg) : e;
      }
      function G0(e) {
        return e = Oe(e), e && $v.test(e) ? e.replace(mi, "\\$&") : e;
      }
      var j0 = Sr(function(e, t, i) {
        return e + (i ? "-" : "") + t.toLowerCase();
      }), K0 = Sr(function(e, t, i) {
        return e + (i ? " " : "") + t.toLowerCase();
      }), J0 = ff("toLowerCase");
      function X0(e, t, i) {
        e = Oe(e), t = ve(t);
        var u = t ? hr(e) : 0;
        if (!t || u >= t)
          return e;
        var c = (t - u) / 2;
        return co(Za(c), i) + e + co(Ya(c), i);
      }
      function Y0(e, t, i) {
        e = Oe(e), t = ve(t);
        var u = t ? hr(e) : 0;
        return t && u < t ? e + co(t - u, i) : e;
      }
      function Z0(e, t, i) {
        e = Oe(e), t = ve(t);
        var u = t ? hr(e) : 0;
        return t && u < t ? co(t - u, i) + e : e;
      }
      function Q0(e, t, i) {
        return i || t == null ? t = 0 : t && (t = +t), ih(Oe(e).replace(yi, ""), t || 0);
      }
      function e_(e, t, i) {
        return (i ? gt(e, t, i) : t === n) ? t = 1 : t = ve(t), Xi(Oe(e), t);
      }
      function t_() {
        var e = arguments, t = Oe(e[0]);
        return e.length < 3 ? t : t.replace(e[1], e[2]);
      }
      var n_ = Sr(function(e, t, i) {
        return e + (i ? "_" : "") + t.toLowerCase();
      });
      function r_(e, t, i) {
        return i && typeof i != "number" && gt(e, t, i) && (t = i = n), i = i === n ? Pe : i >>> 0, i ? (e = Oe(e), e && (typeof t == "string" || t != null && !bl(t)) && (t = Ct(t), !t && gr(e)) ? Vn(Yt(e), 0, i) : e.split(t, i)) : [];
      }
      var a_ = Sr(function(e, t, i) {
        return e + (i ? " " : "") + xl(t);
      });
      function o_(e, t, i) {
        return e = Oe(e), i = i == null ? 0 : Xn(ve(i), 0, e.length), t = Ct(t), e.slice(i, i + t.length) == t;
      }
      function i_(e, t, i) {
        var u = v.templateSettings;
        i && gt(e, t, i) && (t = n), e = Oe(e), t = Bo({}, t, u, mf);
        var c = Bo({}, t.imports, u.imports, mf), h = tt(c), _ = Fi(c, h), A, T, V = 0, D = t.interpolate || Ia, M = "__p += '", G = Li(
          (t.escape || Ia).source + "|" + D.source + "|" + (D === Vs ? Dv : Ia).source + "|" + (t.evaluate || Ia).source + "|$",
          "g"
        ), Z = "//# sourceURL=" + (Fe.call(t, "sourceURL") ? (t.sourceURL + "").replace(/\s/g, " ") : "lodash.templateSources[" + ++gg + "]") + `
`;
        e.replace(G, function(oe, be, Be, Et, ht, kt) {
          return Be || (Be = Et), M += e.slice(V, kt).replace(Gv, Lg), be && (A = !0, M += `' +
__e(` + be + `) +
'`), ht && (T = !0, M += `';
` + ht + `;
__p += '`), Be && (M += `' +
((__t = (` + Be + `)) == null ? '' : __t) +
'`), V = kt + oe.length, oe;
        }), M += `';
`;
        var ae = Fe.call(t, "variable") && t.variable;
        if (!ae)
          M = `with (obj) {
` + M + `
}
`;
        else if (Nv.test(ae))
          throw new ue(d);
        M = (T ? M.replace(wv, "") : M).replace(xv, "$1").replace(Sv, "$1;"), M = "function(" + (ae || "obj") + `) {
` + (ae ? "" : `obj || (obj = {});
`) + "var __t, __p = ''" + (A ? ", __e = _.escape" : "") + (T ? `, __j = Array.prototype.join;
function print() { __p += __j.call(arguments, '') }
` : `;
`) + M + `return __p
}`;
        var he = ud(function() {
          return ke(h, Z + "return " + M).apply(n, _);
        });
        if (he.source = M, yl(he))
          throw he;
        return he;
      }
      function l_(e) {
        return Oe(e).toLowerCase();
      }
      function s_(e) {
        return Oe(e).toUpperCase();
      }
      function u_(e, t, i) {
        if (e = Oe(e), e && (i || t === n))
          return mu(e);
        if (!e || !(t = Ct(t)))
          return e;
        var u = Yt(e), c = Yt(t), h = yu(u, c), _ = bu(u, c) + 1;
        return Vn(u, h, _).join("");
      }
      function f_(e, t, i) {
        if (e = Oe(e), e && (i || t === n))
          return e.slice(0, wu(e) + 1);
        if (!e || !(t = Ct(t)))
          return e;
        var u = Yt(e), c = bu(u, Yt(t)) + 1;
        return Vn(u, 0, c).join("");
      }
      function d_(e, t, i) {
        if (e = Oe(e), e && (i || t === n))
          return e.replace(yi, "");
        if (!e || !(t = Ct(t)))
          return e;
        var u = Yt(e), c = yu(u, Yt(t));
        return Vn(u, c).join("");
      }
      function c_(e, t) {
        var i = Ae, u = Ie;
        if (We(t)) {
          var c = "separator" in t ? t.separator : c;
          i = "length" in t ? ve(t.length) : i, u = "omission" in t ? Ct(t.omission) : u;
        }
        e = Oe(e);
        var h = e.length;
        if (gr(e)) {
          var _ = Yt(e);
          h = _.length;
        }
        if (i >= h)
          return e;
        var A = i - hr(u);
        if (A < 1)
          return u;
        var T = _ ? Vn(_, 0, A).join("") : e.slice(0, A);
        if (c === n)
          return T + u;
        if (_ && (A += T.length - A), bl(c)) {
          if (e.slice(A).search(c)) {
            var V, D = T;
            for (c.global || (c = Li(c.source, Oe(Ds.exec(c)) + "g")), c.lastIndex = 0; V = c.exec(D); )
              var M = V.index;
            T = T.slice(0, M === n ? A : M);
          }
        } else if (e.indexOf(Ct(c), A) != A) {
          var G = T.lastIndexOf(c);
          G > -1 && (T = T.slice(0, G));
        }
        return T + u;
      }
      function p_(e) {
        return e = Oe(e), e && Bv.test(e) ? e.replace(Is, Hg) : e;
      }
      var v_ = Sr(function(e, t, i) {
        return e + (i ? " " : "") + t.toUpperCase();
      }), xl = ff("toUpperCase");
      function sd(e, t, i) {
        return e = Oe(e), t = i ? n : t, t === n ? Ng(e) ? qg(e) : Eg(e) : e.match(t) || [];
      }
      var ud = ye(function(e, t) {
        try {
          return Bt(e, n, t);
        } catch (i) {
          return yl(i) ? i : new ue(i);
        }
      }), g_ = bn(function(e, t) {
        return Nt(t, function(i) {
          i = sn(i), mn(e, i, hl(e[i], e));
        }), e;
      });
      function h_(e) {
        var t = e == null ? 0 : e.length, i = re();
        return e = t ? He(e, function(u) {
          if (typeof u[1] != "function")
            throw new Vt(f);
          return [i(u[0]), u[1]];
        }) : [], ye(function(u) {
          for (var c = -1; ++c < t; ) {
            var h = e[c];
            if (Bt(h[0], this, u))
              return Bt(h[1], this, u);
          }
        });
      }
      function m_(e) {
        return Hh(zt(e, w));
      }
      function Sl(e) {
        return function() {
          return e;
        };
      }
      function y_(e, t) {
        return e == null || e !== e ? t : e;
      }
      var b_ = cf(), __ = cf(!0);
      function wt(e) {
        return e;
      }
      function Bl(e) {
        return Mu(typeof e == "function" ? e : zt(e, w));
      }
      function w_(e) {
        return Uu(zt(e, w));
      }
      function x_(e, t) {
        return Wu(e, zt(t, w));
      }
      var S_ = ye(function(e, t) {
        return function(i) {
          return sa(i, e, t);
        };
      }), B_ = ye(function(e, t) {
        return function(i) {
          return sa(e, i, t);
        };
      });
      function Al(e, t, i) {
        var u = tt(t), c = ao(t, u);
        i == null && !(We(t) && (c.length || !u.length)) && (i = t, t = e, e = this, c = ao(t, tt(t)));
        var h = !(We(i) && "chain" in i) || !!i.chain, _ = wn(e);
        return Nt(c, function(A) {
          var T = t[A];
          e[A] = T, _ && (e.prototype[A] = function() {
            var V = this.__chain__;
            if (h || V) {
              var D = e(this.__wrapped__), M = D.__actions__ = yt(this.__actions__);
              return M.push({ func: T, args: arguments, thisArg: e }), D.__chain__ = V, D;
            }
            return T.apply(e, Rn([this.value()], arguments));
          });
        }), e;
      }
      function A_() {
        return at._ === this && (at._ = Yg), this;
      }
      function Cl() {
      }
      function C_(e) {
        return e = ve(e), ye(function(t) {
          return qu(t, e);
        });
      }
      var T_ = rl(He), E_ = rl(cu), k_ = rl(Ei);
      function fd(e) {
        return fl(e) ? ki(sn(e)) : om(e);
      }
      function O_(e) {
        return function(t) {
          return e == null ? n : Yn(e, t);
        };
      }
      var $_ = vf(), R_ = vf(!0);
      function Tl() {
        return [];
      }
      function El() {
        return !1;
      }
      function F_() {
        return {};
      }
      function P_() {
        return "";
      }
      function L_() {
        return !0;
      }
      function I_(e, t) {
        if (e = ve(e), e < 1 || e > pe)
          return [];
        var i = Pe, u = lt(e, Pe);
        t = re(t), e -= Pe;
        for (var c = Ri(u, t); ++i < e; )
          t(i);
        return c;
      }
      function N_(e) {
        return fe(e) ? He(e, sn) : Tt(e) ? [e] : yt(Of(Oe(e)));
      }
      function V_(e) {
        var t = ++Jg;
        return Oe(e) + t;
      }
      var D_ = fo(function(e, t) {
        return e + t;
      }, 0), z_ = al("ceil"), M_ = fo(function(e, t) {
        return e / t;
      }, 1), H_ = al("floor");
      function U_(e) {
        return e && e.length ? ro(e, wt, Ui) : n;
      }
      function W_(e, t) {
        return e && e.length ? ro(e, re(t, 2), Ui) : n;
      }
      function q_(e) {
        return gu(e, wt);
      }
      function G_(e, t) {
        return gu(e, re(t, 2));
      }
      function j_(e) {
        return e && e.length ? ro(e, wt, ji) : n;
      }
      function K_(e, t) {
        return e && e.length ? ro(e, re(t, 2), ji) : n;
      }
      var J_ = fo(function(e, t) {
        return e * t;
      }, 1), X_ = al("round"), Y_ = fo(function(e, t) {
        return e - t;
      }, 0);
      function Z_(e) {
        return e && e.length ? $i(e, wt) : 0;
      }
      function Q_(e, t) {
        return e && e.length ? $i(e, re(t, 2)) : 0;
      }
      return v.after = wb, v.ary = Mf, v.assign = s0, v.assignIn = td, v.assignInWith = Bo, v.assignWith = u0, v.at = f0, v.before = Hf, v.bind = hl, v.bindAll = g_, v.bindKey = Uf, v.castArray = Fb, v.chain = Vf, v.chunk = Hm, v.compact = Um, v.concat = Wm, v.cond = h_, v.conforms = m_, v.constant = Sl, v.countBy = Zy, v.create = d0, v.curry = Wf, v.curryRight = qf, v.debounce = Gf, v.defaults = c0, v.defaultsDeep = p0, v.defer = xb, v.delay = Sb, v.difference = qm, v.differenceBy = Gm, v.differenceWith = jm, v.drop = Km, v.dropRight = Jm, v.dropRightWhile = Xm, v.dropWhile = Ym, v.fill = Zm, v.filter = eb, v.flatMap = rb, v.flatMapDeep = ab, v.flatMapDepth = ob, v.flatten = Pf, v.flattenDeep = Qm, v.flattenDepth = ey, v.flip = Bb, v.flow = b_, v.flowRight = __, v.fromPairs = ty, v.functions = _0, v.functionsIn = w0, v.groupBy = ib, v.initial = ry, v.intersection = ay, v.intersectionBy = oy, v.intersectionWith = iy, v.invert = S0, v.invertBy = B0, v.invokeMap = sb, v.iteratee = Bl, v.keyBy = ub, v.keys = tt, v.keysIn = _t, v.map = yo, v.mapKeys = C0, v.mapValues = T0, v.matches = w_, v.matchesProperty = x_, v.memoize = _o, v.merge = E0, v.mergeWith = nd, v.method = S_, v.methodOf = B_, v.mixin = Al, v.negate = wo, v.nthArg = C_, v.omit = k0, v.omitBy = O0, v.once = Ab, v.orderBy = fb, v.over = T_, v.overArgs = Cb, v.overEvery = E_, v.overSome = k_, v.partial = ml, v.partialRight = jf, v.partition = db, v.pick = $0, v.pickBy = rd, v.property = fd, v.propertyOf = O_, v.pull = fy, v.pullAll = If, v.pullAllBy = dy, v.pullAllWith = cy, v.pullAt = py, v.range = $_, v.rangeRight = R_, v.rearg = Tb, v.reject = vb, v.remove = vy, v.rest = Eb, v.reverse = vl, v.sampleSize = hb, v.set = F0, v.setWith = P0, v.shuffle = mb, v.slice = gy, v.sortBy = _b, v.sortedUniq = xy, v.sortedUniqBy = Sy, v.split = r_, v.spread = kb, v.tail = By, v.take = Ay, v.takeRight = Cy, v.takeRightWhile = Ty, v.takeWhile = Ey, v.tap = Uy, v.throttle = Ob, v.thru = mo, v.toArray = Zf, v.toPairs = ad, v.toPairsIn = od, v.toPath = N_, v.toPlainObject = ed, v.transform = L0, v.unary = $b, v.union = ky, v.unionBy = Oy, v.unionWith = $y, v.uniq = Ry, v.uniqBy = Fy, v.uniqWith = Py, v.unset = I0, v.unzip = gl, v.unzipWith = Nf, v.update = N0, v.updateWith = V0, v.values = Cr, v.valuesIn = D0, v.without = Ly, v.words = sd, v.wrap = Rb, v.xor = Iy, v.xorBy = Ny, v.xorWith = Vy, v.zip = Dy, v.zipObject = zy, v.zipObjectDeep = My, v.zipWith = Hy, v.entries = ad, v.entriesIn = od, v.extend = td, v.extendWith = Bo, Al(v, v), v.add = D_, v.attempt = ud, v.camelCase = U0, v.capitalize = id, v.ceil = z_, v.clamp = z0, v.clone = Pb, v.cloneDeep = Ib, v.cloneDeepWith = Nb, v.cloneWith = Lb, v.conformsTo = Vb, v.deburr = ld, v.defaultTo = y_, v.divide = M_, v.endsWith = W0, v.eq = Qt, v.escape = q0, v.escapeRegExp = G0, v.every = Qy, v.find = tb, v.findIndex = Rf, v.findKey = v0, v.findLast = nb, v.findLastIndex = Ff, v.findLastKey = g0, v.floor = H_, v.forEach = Df, v.forEachRight = zf, v.forIn = h0, v.forInRight = m0, v.forOwn = y0, v.forOwnRight = b0, v.get = _l, v.gt = Db, v.gte = zb, v.has = x0, v.hasIn = wl, v.head = Lf, v.identity = wt, v.includes = lb, v.indexOf = ny, v.inRange = M0, v.invoke = A0, v.isArguments = er, v.isArray = fe, v.isArrayBuffer = Mb, v.isArrayLike = bt, v.isArrayLikeObject = Je, v.isBoolean = Hb, v.isBuffer = Dn, v.isDate = Ub, v.isElement = Wb, v.isEmpty = qb, v.isEqual = Gb, v.isEqualWith = jb, v.isError = yl, v.isFinite = Kb, v.isFunction = wn, v.isInteger = Kf, v.isLength = xo, v.isMap = Jf, v.isMatch = Jb, v.isMatchWith = Xb, v.isNaN = Yb, v.isNative = Zb, v.isNil = e0, v.isNull = Qb, v.isNumber = Xf, v.isObject = We, v.isObjectLike = Ge, v.isPlainObject = va, v.isRegExp = bl, v.isSafeInteger = t0, v.isSet = Yf, v.isString = So, v.isSymbol = Tt, v.isTypedArray = Ar, v.isUndefined = n0, v.isWeakMap = r0, v.isWeakSet = a0, v.join = ly, v.kebabCase = j0, v.last = Ht, v.lastIndexOf = sy, v.lowerCase = K0, v.lowerFirst = J0, v.lt = o0, v.lte = i0, v.max = U_, v.maxBy = W_, v.mean = q_, v.meanBy = G_, v.min = j_, v.minBy = K_, v.stubArray = Tl, v.stubFalse = El, v.stubObject = F_, v.stubString = P_, v.stubTrue = L_, v.multiply = J_, v.nth = uy, v.noConflict = A_, v.noop = Cl, v.now = bo, v.pad = X0, v.padEnd = Y0, v.padStart = Z0, v.parseInt = Q0, v.random = H0, v.reduce = cb, v.reduceRight = pb, v.repeat = e_, v.replace = t_, v.result = R0, v.round = X_, v.runInContext = C, v.sample = gb, v.size = yb, v.snakeCase = n_, v.some = bb, v.sortedIndex = hy, v.sortedIndexBy = my, v.sortedIndexOf = yy, v.sortedLastIndex = by, v.sortedLastIndexBy = _y, v.sortedLastIndexOf = wy, v.startCase = a_, v.startsWith = o_, v.subtract = Y_, v.sum = Z_, v.sumBy = Q_, v.template = i_, v.times = I_, v.toFinite = xn, v.toInteger = ve, v.toLength = Qf, v.toLower = l_, v.toNumber = Ut, v.toSafeInteger = l0, v.toString = Oe, v.toUpper = s_, v.trim = u_, v.trimEnd = f_, v.trimStart = d_, v.truncate = c_, v.unescape = p_, v.uniqueId = V_, v.upperCase = v_, v.upperFirst = xl, v.each = Df, v.eachRight = zf, v.first = Lf, Al(v, function() {
        var e = {};
        return on(v, function(t, i) {
          Fe.call(v.prototype, i) || (e[i] = t);
        }), e;
      }(), { chain: !1 }), v.VERSION = o, Nt(["bind", "bindKey", "curry", "curryRight", "partial", "partialRight"], function(e) {
        v[e].placeholder = v;
      }), Nt(["drop", "take"], function(e, t) {
        _e.prototype[e] = function(i) {
          i = i === n ? 1 : Qe(ve(i), 0);
          var u = this.__filtered__ && !t ? new _e(this) : this.clone();
          return u.__filtered__ ? u.__takeCount__ = lt(i, u.__takeCount__) : u.__views__.push({
            size: lt(i, Pe),
            type: e + (u.__dir__ < 0 ? "Right" : "")
          }), u;
        }, _e.prototype[e + "Right"] = function(i) {
          return this.reverse()[e](i).reverse();
        };
      }), Nt(["filter", "map", "takeWhile"], function(e, t) {
        var i = t + 1, u = i == q || i == de;
        _e.prototype[e] = function(c) {
          var h = this.clone();
          return h.__iteratees__.push({
            iteratee: re(c, 3),
            type: i
          }), h.__filtered__ = h.__filtered__ || u, h;
        };
      }), Nt(["head", "last"], function(e, t) {
        var i = "take" + (t ? "Right" : "");
        _e.prototype[e] = function() {
          return this[i](1).value()[0];
        };
      }), Nt(["initial", "tail"], function(e, t) {
        var i = "drop" + (t ? "" : "Right");
        _e.prototype[e] = function() {
          return this.__filtered__ ? new _e(this) : this[i](1);
        };
      }), _e.prototype.compact = function() {
        return this.filter(wt);
      }, _e.prototype.find = function(e) {
        return this.filter(e).head();
      }, _e.prototype.findLast = function(e) {
        return this.reverse().find(e);
      }, _e.prototype.invokeMap = ye(function(e, t) {
        return typeof e == "function" ? new _e(this) : this.map(function(i) {
          return sa(i, e, t);
        });
      }), _e.prototype.reject = function(e) {
        return this.filter(wo(re(e)));
      }, _e.prototype.slice = function(e, t) {
        e = ve(e);
        var i = this;
        return i.__filtered__ && (e > 0 || t < 0) ? new _e(i) : (e < 0 ? i = i.takeRight(-e) : e && (i = i.drop(e)), t !== n && (t = ve(t), i = t < 0 ? i.dropRight(-t) : i.take(t - e)), i);
      }, _e.prototype.takeRightWhile = function(e) {
        return this.reverse().takeWhile(e).reverse();
      }, _e.prototype.toArray = function() {
        return this.take(Pe);
      }, on(_e.prototype, function(e, t) {
        var i = /^(?:filter|find|map|reject)|While$/.test(t), u = /^(?:head|last)$/.test(t), c = v[u ? "take" + (t == "last" ? "Right" : "") : t], h = u || /^find/.test(t);
        c && (v.prototype[t] = function() {
          var _ = this.__wrapped__, A = u ? [1] : arguments, T = _ instanceof _e, V = A[0], D = T || fe(_), M = function(be) {
            var Be = c.apply(v, Rn([be], A));
            return u && G ? Be[0] : Be;
          };
          D && i && typeof V == "function" && V.length != 1 && (T = D = !1);
          var G = this.__chain__, Z = !!this.__actions__.length, ae = h && !G, he = T && !Z;
          if (!h && D) {
            _ = he ? _ : new _e(this);
            var oe = e.apply(_, A);
            return oe.__actions__.push({ func: mo, args: [M], thisArg: n }), new Dt(oe, G);
          }
          return ae && he ? e.apply(this, A) : (oe = this.thru(M), ae ? u ? oe.value()[0] : oe.value() : oe);
        });
      }), Nt(["pop", "push", "shift", "sort", "splice", "unshift"], function(e) {
        var t = Ha[e], i = /^(?:push|sort|unshift)$/.test(e) ? "tap" : "thru", u = /^(?:pop|shift)$/.test(e);
        v.prototype[e] = function() {
          var c = arguments;
          if (u && !this.__chain__) {
            var h = this.value();
            return t.apply(fe(h) ? h : [], c);
          }
          return this[i](function(_) {
            return t.apply(fe(_) ? _ : [], c);
          });
        };
      }), on(_e.prototype, function(e, t) {
        var i = v[t];
        if (i) {
          var u = i.name + "";
          Fe.call(_r, u) || (_r[u] = []), _r[u].push({ name: t, func: i });
        }
      }), _r[uo(n, E).name] = [{
        name: "wrapper",
        func: n
      }], _e.prototype.clone = ph, _e.prototype.reverse = vh, _e.prototype.value = gh, v.prototype.at = Wy, v.prototype.chain = qy, v.prototype.commit = Gy, v.prototype.next = jy, v.prototype.plant = Jy, v.prototype.reverse = Xy, v.prototype.toJSON = v.prototype.valueOf = v.prototype.value = Yy, v.prototype.first = v.prototype.head, ta && (v.prototype[ta] = Ky), v;
    }, mr = Gg();
    Gn ? ((Gn.exports = mr)._ = mr, Bi._ = mr) : at._ = mr;
  }).call(nr);
})(Ho, Ho.exports);
var lB = Ho.exports;
const sB = {
  name: "VsHeading",
  status: "prototype",
  release: "0.1.0",
  props: {
    /**
     * The heading level used for the heading.
     * `1|2|3|4|5|6`
     */
    level: {
      type: [String, Number],
      default: "1",
      validator: (a) => lB.isNumber(a) ? a > 0 && a < 7 : a.match(/(1|2|3|4|5|6)/)
    },
    /**
     * The heading style used for the heading.
     * `display-m|display-s|heading-xl|
     * heading-l|heading-m|heading-s|heading-xs|heading-xxs`
     */
    headingStyle: {
      type: String,
      required: !0,
      validator: (a) => a.match(
        /(display-m|display-s|heading-xl|heading-l|heading-m|heading-s|heading-xs|heading-xxs|heading-xxxs)/
      )
    },
    /**
     * id string for h tag
     * typically — though not necessarily — used as page anchor
     */
    id: {
      type: String,
      default: null
    },
    /**
     * Option to remove margins from headings.
     * Useful when heading is used inside a component
     */
    noMargins: {
      type: Boolean,
      default: !1
    }
  },
  computed: {
    headingClasses() {
      return [
        this.headingStyle ? `vs-heading--${this.headingStyle}` : "",
        ...this.noMargins ? ["vs-heading--no-margins"] : []
      ];
    },
    type() {
      return `h${this.level}`;
    }
  }
};
function uB(a, r, n, o, l, s) {
  return P(), X(Ve(s.type), {
    class: Q(["vs-heading", s.headingClasses]),
    id: n.id
  }, {
    default: ee(() => [
      W(a.$slots, "default")
    ]),
    _: 3
  }, 8, ["class", "id"]);
}
const qE = /* @__PURE__ */ rn(sB, [["render", uB]]), fB = {
  name: "VsCol",
  status: "prototype",
  release: "0.1.0",
  components: {
    BCol: ya
  },
  /**
   * To reduce page weight we want to load images at an appropriate size based on their
   * appearance on the screen, as well as the size of that screen. By providing this
   * information about the grid layout of the page, any VsImg component within this Col in the
   * page can estimate its maximum size. See the `imgSizesMixin` for more details.
   */
  provide() {
    return {
      cols: this.$attrs.cols,
      sm: this.$attrs.sm,
      md: this.$attrs.md,
      lg: this.$attrs.lg,
      xl: this.$attrs.xl
    };
  }
};
function dB(a, r, n, o, l, s) {
  const f = qt("BCol");
  return P(), X(f, Wt(An(a.$attrs)), {
    default: ee(() => [
      W(a.$slots, "default")
    ]),
    _: 3
  }, 16);
}
const GE = /* @__PURE__ */ rn(fB, [["render", dB]]), cB = {
  name: "VsRow",
  status: "prototype",
  release: "0.0.1"
};
function pB(a, r, n, o, l, s) {
  return P(), j("div", xe({ class: "row" }, a.$attrs), [
    W(a.$slots, "default")
  ], 16);
}
const jE = /* @__PURE__ */ rn(cB, [["render", pB]]), vB = {
  name: "VsContainer",
  status: "prototype",
  release: "0.1.0",
  components: {
    BContainer: xx
  }
};
function gB(a, r, n, o, l, s) {
  const f = qt("BContainer");
  return P(), X(f, xe({ tag: "div" }, a.$attrs), {
    default: ee(() => [
      W(a.$slots, "default")
    ]),
    _: 3
  }, 16);
}
const KE = /* @__PURE__ */ rn(vB, [["render", gB]]), hB = {
  name: "VsBody",
  status: "prototype",
  release: "0.0.1",
  props: {
    /**
     * The font size used by the wrapper
     * `normal|lead`
     */
    variant: {
      type: String,
      default: "normal",
      validator: (a) => a.match(/(normal|lead)/)
    },
    /**
     * Option to remove margins from body text.
     * Useful when body is used inside a component
     */
    noMargins: {
      type: Boolean,
      default: !1
    }
  },
  computed: {
    bodyClasses() {
      return [
        "vs-body",
        `vs-body--${this.variant}`,
        ...this.noMargins ? [] : ["vs-body--with-margins"]
      ];
    }
  }
};
function mB(a, r, n, o, l, s) {
  return P(), j("div", {
    class: Q(s.bodyClasses)
  }, [
    W(a.$slots, "default")
  ], 2);
}
const yB = /* @__PURE__ */ rn(hB, [["render", mB]]), bB = {
  name: "VsButtonToggleGroup",
  status: "prototype",
  release: "0.0.1",
  components: {
    BFormGroup: Nx,
    BFormRadioGroup: Ux,
    BFormRadio: Sp,
    VsIcon: xs
  },
  props: {
    /**
     * An object of options for the
     * toggle buttons
     */
    options: {
      type: Array,
      required: !0
    },
    /**
     * Initially selected options
     */
    initialSelected: {
      type: String,
      default: ""
    },
    /**
     * Initially selected options
     */
    buttonsLabel: {
      type: String,
      required: !0
    }
  },
  emits: ["toggleChanged"],
  data() {
    return {
      selected: this.initialSelected,
      groupTabbedInto: !1
    };
  },
  watch: {
    initialSelected(a) {
      this.selected = a;
    }
  },
  mounted() {
    this.initialSelected === "" && (this.selected = this.options[0].value);
  },
  methods: {
    /**
     * Emit checked value when the selected
     * item changes
     */
    toggleChange(a) {
      const r = a.target.value;
      this.$emit("toggleChanged", r);
    },
    /**
     * Updates data value to signify that the element
     * has been tabbed into
     */
    addTabClass() {
      this.groupTabbedInto = !0;
    },
    /**
     * Removes the 'tabbed into' class
     */
    removeTabClass(a) {
      a.target.tagName !== "INPUT" && (this.groupTabbedInto = !1);
    }
  }
};
function _B(a, r, n, o, l, s) {
  const f = qt("VsIcon"), d = qt("BFormRadio"), p = qt("BFormRadioGroup"), g = qt("BFormGroup");
  return P(), X(g, {
    label: n.buttonsLabel,
    class: "vs-button-toggle-group",
    "data-test": "vs-button-toggle-group"
  }, {
    default: ee(({ ariaDescribedby: m }) => [
      Rt(p, {
        class: Q(["vs-button-toggle-group--radios", l.groupTabbedInto ? "vs-button-toggle-group--tabbed-focus" : ""]),
        id: "btn-radios-1",
        modelValue: l.selected,
        "onUpdate:modelValue": r[2] || (r[2] = (w) => l.selected = w),
        "aria-describedby": m,
        name: "radios-btn-default",
        buttons: "",
        onChange: s.toggleChange,
        onFocusout: s.removeTabClass
      }, {
        default: ee(() => [
          (P(!0), j(rt, null, ur(n.options, (w) => (P(), j("div", {
            key: w.text,
            class: Q(["vs-button-toggle-group--button", l.selected === w.value ? "active" : ""]),
            onKeyup: r[0] || (r[0] = Rr((...S) => s.addTabClass && s.addTabClass(...S), ["tab"])),
            "onFocus`out": r[1] || (r[1] = (...S) => s.removeTabClass && s.removeTabClass(...S))
          }, [
            (P(), X(d, {
              value: w.value,
              key: w.text
            }, {
              default: ee(() => [
                je("span", null, [
                  w.icon ? (P(), X(f, {
                    key: 0,
                    icon: w.icon,
                    class: "me-025"
                  }, null, 8, ["icon"])) : $e("", !0),
                  Te(" " + Ee(w.text), 1)
                ])
              ]),
              _: 2
            }, 1032, ["value"]))
          ], 34))), 128))
        ]),
        _: 2
      }, 1032, ["modelValue", "aria-describedby", "onChange", "class", "onFocusout"])
    ]),
    _: 1
  }, 8, ["label"]);
}
const JE = /* @__PURE__ */ rn(bB, [["render", _B]]), wB = {
  computed: {
    fullSrcSet() {
      return `
                ${this.specificImgSize("xs")} 300w,
                ${this.specificImgSize("sm")} 600w,
                ${this.specificImgSize("md")} 1200w,
                ${this.specificImgSize("lg")} 2048w`;
    }
  },
  methods: {
    specificImgSize(a) {
      if (!this.src)
        return "";
      let r = "?";
      return this.src.includes("?") && (r = "&"), `${this.src}${r}size=${a}`;
    }
  }
}, xB = {
  /**
   * Inject the grid information from the closest ancestor VsCol component. If multiple nested
   * VsCols are present this will load from the closest one. With multiple nested VsCols an
   * image may end up being loaded larger than it needs to be, as this system naively assumes
   * that something 6 cols wide on a 12 column grid will always be 50% of the viewport width,
   * but it will never be smaller than it needs to be.
   *
   * In cases where images are sized without the use of VsCol they will continue to default to
   * assuming the image might need to be 100% of the viewport width.
   */
  inject: {
    cols: {
      default: 0
    },
    sm: {
      default: 0
    },
    md: {
      default: 0
    },
    lg: {
      default: 0
    },
    xl: {
      default: 0
    }
  },
  computed: {
    /**
     * Generates a string of media queries for the image, which can be used in the `sizes`
     * attribute. This string is parsed left to right searching for the first match, so we
     * start with the largest viewport size and work down to the smallest, requesting an
     * image at an appropriate percent of the viewport width.
     */
    computedSizes() {
      const r = [
        {
          viewportSize: "xl",
          minWidth: 1200
        },
        {
          viewportSize: "lg",
          minWidth: 992
        },
        {
          viewportSize: "md",
          minWidth: 769
        },
        {
          viewportSize: "sm",
          minWidth: 576
        }
      ].filter(({ viewportSize: n }) => this[n]).map(({ viewportSize: n, minWidth: o }) => {
        const l = this[n] / 12 * 100;
        return `(min-width: ${o}px) ${l}vw`;
      });
      return this.cols ? r.push(`${this.cols / 12 * 100}vw`) : r.push("100vw"), r.join(", ");
    }
  }
}, SB = {
  name: "VsImg",
  status: "prototype",
  release: "0.0.1",
  components: {
    BImg: bp
  },
  mixins: [
    wB,
    xB
  ],
  props: {
    /**
     * The source URL
     */
    src: {
      type: String,
      required: !0
    },
    /**
     * Provide low res image to be initially loaded
     */
    lowResImage: {
      type: String,
      default: null
    },
    /**
     * The alt text for accessibility
     */
    alt: {
      type: String,
      default: ""
    },
    /**
     * If true makes the image responsive, scaling with the parent
     * up to a max of the native width of the image itself
     */
    fluid: {
      type: Boolean,
      default: !1
    },
    /**
     * If true makes the image responsive, scaling with the parent
     * beyond the native image width if necessary
     */
    fluidGrow: {
      type: Boolean,
      default: !1
    },
    /**
     * If true a generic LQIP is used for lazyloading, rather than the
     * provided xxs path to the image scaler. Used when the image scaler
     * is not functioning as desired for certain images.
     */
    useGenericLqip: {
      type: Boolean,
      default: !1
    },
    /**
     * If true switches on lazy loading for the image
    */
    useLazyLoading: {
      type: Boolean,
      default: !0
    }
  },
  computed: {
    imgStyle() {
      return !this.useGenericLqip && !this.src.includes(".svg") ? {
        backgroundImage: `url(${this.specificImgSize("xxs")})`
      } : null;
    },
    isSvg() {
      return this.src.includes(".svg");
    },
    computedSrcSet() {
      return this.isSvg ? null : this.$attrs.srcset ? this.$attrs.srcset : this.fullSrcSet;
    }
  }
};
function BB(a, r, n, o, l, s) {
  const f = qt("BImg");
  return P(), X(f, xe(a.$attrs, {
    src: n.src,
    alt: n.alt,
    fluid: n.fluid,
    "fluid-grow": n.fluidGrow,
    loading: n.useLazyLoading ? "lazy" : "eager",
    style: s.imgStyle,
    class: ["vs-img low-res-img", n.useGenericLqip ? "generic-lqip" : ""],
    srcset: s.computedSrcSet,
    "low-res-image": s.isSvg ? "" : a.specificImgSize("xxs"),
    sizes: a.computedSizes
  }), {
    default: ee(() => [
      W(a.$slots, "default")
    ]),
    _: 3
  }, 16, ["src", "alt", "fluid", "fluid-grow", "loading", "style", "class", "srcset", "low-res-image", "sizes"]);
}
const XE = /* @__PURE__ */ rn(SB, [["render", BB]]);
function Cp(a, r) {
  return function() {
    return a.apply(r, arguments);
  };
}
const { toString: AB } = Object.prototype, { getPrototypeOf: Ss } = Object, { iterator: ti, toStringTag: Tp } = Symbol, ni = /* @__PURE__ */ ((a) => (r) => {
  const n = AB.call(r);
  return a[n] || (a[n] = n.slice(8, -1).toLowerCase());
})(/* @__PURE__ */ Object.create(null)), an = (a) => (a = a.toLowerCase(), (r) => ni(r) === a), ri = (a) => (r) => typeof r === a, { isArray: Gr } = Array, Hr = ri("undefined");
function Ta(a) {
  return a !== null && !Hr(a) && a.constructor !== null && !Hr(a.constructor) && xt(a.constructor.isBuffer) && a.constructor.isBuffer(a);
}
const Ep = an("ArrayBuffer");
function CB(a) {
  let r;
  return typeof ArrayBuffer < "u" && ArrayBuffer.isView ? r = ArrayBuffer.isView(a) : r = a && a.buffer && Ep(a.buffer), r;
}
const TB = ri("string"), xt = ri("function"), kp = ri("number"), Ea = (a) => a !== null && typeof a == "object", EB = (a) => a === !0 || a === !1, $o = (a) => {
  if (ni(a) !== "object")
    return !1;
  const r = Ss(a);
  return (r === null || r === Object.prototype || Object.getPrototypeOf(r) === null) && !(Tp in a) && !(ti in a);
}, kB = (a) => {
  if (!Ea(a) || Ta(a))
    return !1;
  try {
    return Object.keys(a).length === 0 && Object.getPrototypeOf(a) === Object.prototype;
  } catch {
    return !1;
  }
}, OB = an("Date"), $B = an("File"), RB = an("Blob"), FB = an("FileList"), PB = (a) => Ea(a) && xt(a.pipe), LB = (a) => {
  let r;
  return a && (typeof FormData == "function" && a instanceof FormData || xt(a.append) && ((r = ni(a)) === "formdata" || // detect form-data instance
  r === "object" && xt(a.toString) && a.toString() === "[object FormData]"));
}, IB = an("URLSearchParams"), [NB, VB, DB, zB] = ["ReadableStream", "Request", "Response", "Headers"].map(an), MB = (a) => a.trim ? a.trim() : a.replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, "");
function ka(a, r, { allOwnKeys: n = !1 } = {}) {
  if (a === null || typeof a > "u")
    return;
  let o, l;
  if (typeof a != "object" && (a = [a]), Gr(a))
    for (o = 0, l = a.length; o < l; o++)
      r.call(null, a[o], o, a);
  else {
    if (Ta(a))
      return;
    const s = n ? Object.getOwnPropertyNames(a) : Object.keys(a), f = s.length;
    let d;
    for (o = 0; o < f; o++)
      d = s[o], r.call(null, a[d], d, a);
  }
}
function Op(a, r) {
  if (Ta(a))
    return null;
  r = r.toLowerCase();
  const n = Object.keys(a);
  let o = n.length, l;
  for (; o-- > 0; )
    if (l = n[o], r === l.toLowerCase())
      return l;
  return null;
}
const rr = typeof globalThis < "u" ? globalThis : typeof self < "u" ? self : typeof window < "u" ? window : global, $p = (a) => !Hr(a) && a !== rr;
function es() {
  const { caseless: a, skipUndefined: r } = $p(this) && this || {}, n = {}, o = (l, s) => {
    const f = a && Op(n, s) || s;
    $o(n[f]) && $o(l) ? n[f] = es(n[f], l) : $o(l) ? n[f] = es({}, l) : Gr(l) ? n[f] = l.slice() : (!r || !Hr(l)) && (n[f] = l);
  };
  for (let l = 0, s = arguments.length; l < s; l++)
    arguments[l] && ka(arguments[l], o);
  return n;
}
const HB = (a, r, n, { allOwnKeys: o } = {}) => (ka(r, (l, s) => {
  n && xt(l) ? a[s] = Cp(l, n) : a[s] = l;
}, { allOwnKeys: o }), a), UB = (a) => (a.charCodeAt(0) === 65279 && (a = a.slice(1)), a), WB = (a, r, n, o) => {
  a.prototype = Object.create(r.prototype, o), a.prototype.constructor = a, Object.defineProperty(a, "super", {
    value: r.prototype
  }), n && Object.assign(a.prototype, n);
}, qB = (a, r, n, o) => {
  let l, s, f;
  const d = {};
  if (r = r || {}, a == null) return r;
  do {
    for (l = Object.getOwnPropertyNames(a), s = l.length; s-- > 0; )
      f = l[s], (!o || o(f, a, r)) && !d[f] && (r[f] = a[f], d[f] = !0);
    a = n !== !1 && Ss(a);
  } while (a && (!n || n(a, r)) && a !== Object.prototype);
  return r;
}, GB = (a, r, n) => {
  a = String(a), (n === void 0 || n > a.length) && (n = a.length), n -= r.length;
  const o = a.indexOf(r, n);
  return o !== -1 && o === n;
}, jB = (a) => {
  if (!a) return null;
  if (Gr(a)) return a;
  let r = a.length;
  if (!kp(r)) return null;
  const n = new Array(r);
  for (; r-- > 0; )
    n[r] = a[r];
  return n;
}, KB = /* @__PURE__ */ ((a) => (r) => a && r instanceof a)(typeof Uint8Array < "u" && Ss(Uint8Array)), JB = (a, r) => {
  const o = (a && a[ti]).call(a);
  let l;
  for (; (l = o.next()) && !l.done; ) {
    const s = l.value;
    r.call(a, s[0], s[1]);
  }
}, XB = (a, r) => {
  let n;
  const o = [];
  for (; (n = a.exec(r)) !== null; )
    o.push(n);
  return o;
}, YB = an("HTMLFormElement"), ZB = (a) => a.toLowerCase().replace(
  /[-_\s]([a-z\d])(\w*)/g,
  function(n, o, l) {
    return o.toUpperCase() + l;
  }
), zd = (({ hasOwnProperty: a }) => (r, n) => a.call(r, n))(Object.prototype), QB = an("RegExp"), Rp = (a, r) => {
  const n = Object.getOwnPropertyDescriptors(a), o = {};
  ka(n, (l, s) => {
    let f;
    (f = r(l, s, a)) !== !1 && (o[s] = f || l);
  }), Object.defineProperties(a, o);
}, eA = (a) => {
  Rp(a, (r, n) => {
    if (xt(a) && ["arguments", "caller", "callee"].indexOf(n) !== -1)
      return !1;
    const o = a[n];
    if (xt(o)) {
      if (r.enumerable = !1, "writable" in r) {
        r.writable = !1;
        return;
      }
      r.set || (r.set = () => {
        throw Error("Can not rewrite read-only method '" + n + "'");
      });
    }
  });
}, tA = (a, r) => {
  const n = {}, o = (l) => {
    l.forEach((s) => {
      n[s] = !0;
    });
  };
  return Gr(a) ? o(a) : o(String(a).split(r)), n;
}, nA = () => {
}, rA = (a, r) => a != null && Number.isFinite(a = +a) ? a : r;
function aA(a) {
  return !!(a && xt(a.append) && a[Tp] === "FormData" && a[ti]);
}
const oA = (a) => {
  const r = new Array(10), n = (o, l) => {
    if (Ea(o)) {
      if (r.indexOf(o) >= 0)
        return;
      if (Ta(o))
        return o;
      if (!("toJSON" in o)) {
        r[l] = o;
        const s = Gr(o) ? [] : {};
        return ka(o, (f, d) => {
          const p = n(f, l + 1);
          !Hr(p) && (s[d] = p);
        }), r[l] = void 0, s;
      }
    }
    return o;
  };
  return n(a, 0);
}, iA = an("AsyncFunction"), lA = (a) => a && (Ea(a) || xt(a)) && xt(a.then) && xt(a.catch), Fp = ((a, r) => a ? setImmediate : r ? ((n, o) => (rr.addEventListener("message", ({ source: l, data: s }) => {
  l === rr && s === n && o.length && o.shift()();
}, !1), (l) => {
  o.push(l), rr.postMessage(n, "*");
}))(`axios@${Math.random()}`, []) : (n) => setTimeout(n))(
  typeof setImmediate == "function",
  xt(rr.postMessage)
), sA = typeof queueMicrotask < "u" ? queueMicrotask.bind(rr) : typeof process < "u" && process.nextTick || Fp, uA = (a) => a != null && xt(a[ti]), O = {
  isArray: Gr,
  isArrayBuffer: Ep,
  isBuffer: Ta,
  isFormData: LB,
  isArrayBufferView: CB,
  isString: TB,
  isNumber: kp,
  isBoolean: EB,
  isObject: Ea,
  isPlainObject: $o,
  isEmptyObject: kB,
  isReadableStream: NB,
  isRequest: VB,
  isResponse: DB,
  isHeaders: zB,
  isUndefined: Hr,
  isDate: OB,
  isFile: $B,
  isBlob: RB,
  isRegExp: QB,
  isFunction: xt,
  isStream: PB,
  isURLSearchParams: IB,
  isTypedArray: KB,
  isFileList: FB,
  forEach: ka,
  merge: es,
  extend: HB,
  trim: MB,
  stripBOM: UB,
  inherits: WB,
  toFlatObject: qB,
  kindOf: ni,
  kindOfTest: an,
  endsWith: GB,
  toArray: jB,
  forEachEntry: JB,
  matchAll: XB,
  isHTMLForm: YB,
  hasOwnProperty: zd,
  hasOwnProp: zd,
  // an alias to avoid ESLint no-prototype-builtins detection
  reduceDescriptors: Rp,
  freezeMethods: eA,
  toObjectSet: tA,
  toCamelCase: ZB,
  noop: nA,
  toFiniteNumber: rA,
  findKey: Op,
  global: rr,
  isContextDefined: $p,
  isSpecCompliantForm: aA,
  toJSONObject: oA,
  isAsyncFn: iA,
  isThenable: lA,
  setImmediate: Fp,
  asap: sA,
  isIterable: uA
};
function ce(a, r, n, o, l) {
  Error.call(this), Error.captureStackTrace ? Error.captureStackTrace(this, this.constructor) : this.stack = new Error().stack, this.message = a, this.name = "AxiosError", r && (this.code = r), n && (this.config = n), o && (this.request = o), l && (this.response = l, this.status = l.status ? l.status : null);
}
O.inherits(ce, Error, {
  toJSON: function() {
    return {
      // Standard
      message: this.message,
      name: this.name,
      // Microsoft
      description: this.description,
      number: this.number,
      // Mozilla
      fileName: this.fileName,
      lineNumber: this.lineNumber,
      columnNumber: this.columnNumber,
      stack: this.stack,
      // Axios
      config: O.toJSONObject(this.config),
      code: this.code,
      status: this.status
    };
  }
});
const Pp = ce.prototype, Lp = {};
[
  "ERR_BAD_OPTION_VALUE",
  "ERR_BAD_OPTION",
  "ECONNABORTED",
  "ETIMEDOUT",
  "ERR_NETWORK",
  "ERR_FR_TOO_MANY_REDIRECTS",
  "ERR_DEPRECATED",
  "ERR_BAD_RESPONSE",
  "ERR_BAD_REQUEST",
  "ERR_CANCELED",
  "ERR_NOT_SUPPORT",
  "ERR_INVALID_URL"
  // eslint-disable-next-line func-names
].forEach((a) => {
  Lp[a] = { value: a };
});
Object.defineProperties(ce, Lp);
Object.defineProperty(Pp, "isAxiosError", { value: !0 });
ce.from = (a, r, n, o, l, s) => {
  const f = Object.create(Pp);
  O.toFlatObject(a, f, function(m) {
    return m !== Error.prototype;
  }, (g) => g !== "isAxiosError");
  const d = a && a.message ? a.message : "Error", p = r == null && a ? a.code : r;
  return ce.call(f, d, p, n, o, l), a && f.cause == null && Object.defineProperty(f, "cause", { value: a, configurable: !0 }), f.name = a && a.name || "Error", s && Object.assign(f, s), f;
};
const fA = null;
function ts(a) {
  return O.isPlainObject(a) || O.isArray(a);
}
function Ip(a) {
  return O.endsWith(a, "[]") ? a.slice(0, -2) : a;
}
function Md(a, r, n) {
  return a ? a.concat(r).map(function(l, s) {
    return l = Ip(l), !n && s ? "[" + l + "]" : l;
  }).join(n ? "." : "") : r;
}
function dA(a) {
  return O.isArray(a) && !a.some(ts);
}
const cA = O.toFlatObject(O, {}, null, function(r) {
  return /^is[A-Z]/.test(r);
});
function ai(a, r, n) {
  if (!O.isObject(a))
    throw new TypeError("target must be an object");
  r = r || new FormData(), n = O.toFlatObject(n, {
    metaTokens: !0,
    dots: !1,
    indexes: !1
  }, !1, function(x, B) {
    return !O.isUndefined(B[x]);
  });
  const o = n.metaTokens, l = n.visitor || m, s = n.dots, f = n.indexes, p = (n.Blob || typeof Blob < "u" && Blob) && O.isSpecCompliantForm(r);
  if (!O.isFunction(l))
    throw new TypeError("visitor must be a function");
  function g(y) {
    if (y === null) return "";
    if (O.isDate(y))
      return y.toISOString();
    if (O.isBoolean(y))
      return y.toString();
    if (!p && O.isBlob(y))
      throw new ce("Blob is not supported. Use a Buffer instead.");
    return O.isArrayBuffer(y) || O.isTypedArray(y) ? p && typeof Blob == "function" ? new Blob([y]) : Buffer.from(y) : y;
  }
  function m(y, x, B) {
    let E = y;
    if (y && !B && typeof y == "object") {
      if (O.endsWith(x, "{}"))
        x = o ? x : x.slice(0, -2), y = JSON.stringify(y);
      else if (O.isArray(y) && dA(y) || (O.isFileList(y) || O.endsWith(x, "[]")) && (E = O.toArray(y)))
        return x = Ip(x), E.forEach(function(L, $) {
          !(O.isUndefined(L) || L === null) && r.append(
            // eslint-disable-next-line no-nested-ternary
            f === !0 ? Md([x], $, s) : f === null ? x : x + "[]",
            g(L)
          );
        }), !1;
    }
    return ts(y) ? !0 : (r.append(Md(B, x, s), g(y)), !1);
  }
  const w = [], S = Object.assign(cA, {
    defaultVisitor: m,
    convertValue: g,
    isVisitable: ts
  });
  function b(y, x) {
    if (!O.isUndefined(y)) {
      if (w.indexOf(y) !== -1)
        throw Error("Circular reference detected in " + x.join("."));
      w.push(y), O.forEach(y, function(E, F) {
        (!(O.isUndefined(E) || E === null) && l.call(
          r,
          E,
          O.isString(F) ? F.trim() : F,
          x,
          S
        )) === !0 && b(E, x ? x.concat(F) : [F]);
      }), w.pop();
    }
  }
  if (!O.isObject(a))
    throw new TypeError("data must be an object");
  return b(a), r;
}
function Hd(a) {
  const r = {
    "!": "%21",
    "'": "%27",
    "(": "%28",
    ")": "%29",
    "~": "%7E",
    "%20": "+",
    "%00": "\0"
  };
  return encodeURIComponent(a).replace(/[!'()~]|%20|%00/g, function(o) {
    return r[o];
  });
}
function Bs(a, r) {
  this._pairs = [], a && ai(a, this, r);
}
const Np = Bs.prototype;
Np.append = function(r, n) {
  this._pairs.push([r, n]);
};
Np.toString = function(r) {
  const n = r ? function(o) {
    return r.call(this, o, Hd);
  } : Hd;
  return this._pairs.map(function(l) {
    return n(l[0]) + "=" + n(l[1]);
  }, "").join("&");
};
function pA(a) {
  return encodeURIComponent(a).replace(/%3A/gi, ":").replace(/%24/g, "$").replace(/%2C/gi, ",").replace(/%20/g, "+");
}
function Vp(a, r, n) {
  if (!r)
    return a;
  const o = n && n.encode || pA;
  O.isFunction(n) && (n = {
    serialize: n
  });
  const l = n && n.serialize;
  let s;
  if (l ? s = l(r, n) : s = O.isURLSearchParams(r) ? r.toString() : new Bs(r, n).toString(o), s) {
    const f = a.indexOf("#");
    f !== -1 && (a = a.slice(0, f)), a += (a.indexOf("?") === -1 ? "?" : "&") + s;
  }
  return a;
}
class Ud {
  constructor() {
    this.handlers = [];
  }
  /**
   * Add a new interceptor to the stack
   *
   * @param {Function} fulfilled The function to handle `then` for a `Promise`
   * @param {Function} rejected The function to handle `reject` for a `Promise`
   *
   * @return {Number} An ID used to remove interceptor later
   */
  use(r, n, o) {
    return this.handlers.push({
      fulfilled: r,
      rejected: n,
      synchronous: o ? o.synchronous : !1,
      runWhen: o ? o.runWhen : null
    }), this.handlers.length - 1;
  }
  /**
   * Remove an interceptor from the stack
   *
   * @param {Number} id The ID that was returned by `use`
   *
   * @returns {void}
   */
  eject(r) {
    this.handlers[r] && (this.handlers[r] = null);
  }
  /**
   * Clear all interceptors from the stack
   *
   * @returns {void}
   */
  clear() {
    this.handlers && (this.handlers = []);
  }
  /**
   * Iterate over all the registered interceptors
   *
   * This method is particularly useful for skipping over any
   * interceptors that may have become `null` calling `eject`.
   *
   * @param {Function} fn The function to call for each interceptor
   *
   * @returns {void}
   */
  forEach(r) {
    O.forEach(this.handlers, function(o) {
      o !== null && r(o);
    });
  }
}
const Dp = {
  silentJSONParsing: !0,
  forcedJSONParsing: !0,
  clarifyTimeoutError: !1
}, vA = typeof URLSearchParams < "u" ? URLSearchParams : Bs, gA = typeof FormData < "u" ? FormData : null, hA = typeof Blob < "u" ? Blob : null, mA = {
  isBrowser: !0,
  classes: {
    URLSearchParams: vA,
    FormData: gA,
    Blob: hA
  },
  protocols: ["http", "https", "file", "blob", "url", "data"]
}, As = typeof window < "u" && typeof document < "u", ns = typeof navigator == "object" && navigator || void 0, yA = As && (!ns || ["ReactNative", "NativeScript", "NS"].indexOf(ns.product) < 0), bA = typeof WorkerGlobalScope < "u" && // eslint-disable-next-line no-undef
self instanceof WorkerGlobalScope && typeof self.importScripts == "function", _A = As && window.location.href || "http://localhost", wA = /* @__PURE__ */ Object.freeze(/* @__PURE__ */ Object.defineProperty({
  __proto__: null,
  hasBrowserEnv: As,
  hasStandardBrowserEnv: yA,
  hasStandardBrowserWebWorkerEnv: bA,
  navigator: ns,
  origin: _A
}, Symbol.toStringTag, { value: "Module" })), dt = {
  ...wA,
  ...mA
};
function xA(a, r) {
  return ai(a, new dt.classes.URLSearchParams(), {
    visitor: function(n, o, l, s) {
      return dt.isNode && O.isBuffer(n) ? (this.append(o, n.toString("base64")), !1) : s.defaultVisitor.apply(this, arguments);
    },
    ...r
  });
}
function SA(a) {
  return O.matchAll(/\w+|\[(\w*)]/g, a).map((r) => r[0] === "[]" ? "" : r[1] || r[0]);
}
function BA(a) {
  const r = {}, n = Object.keys(a);
  let o;
  const l = n.length;
  let s;
  for (o = 0; o < l; o++)
    s = n[o], r[s] = a[s];
  return r;
}
function zp(a) {
  function r(n, o, l, s) {
    let f = n[s++];
    if (f === "__proto__") return !0;
    const d = Number.isFinite(+f), p = s >= n.length;
    return f = !f && O.isArray(l) ? l.length : f, p ? (O.hasOwnProp(l, f) ? l[f] = [l[f], o] : l[f] = o, !d) : ((!l[f] || !O.isObject(l[f])) && (l[f] = []), r(n, o, l[f], s) && O.isArray(l[f]) && (l[f] = BA(l[f])), !d);
  }
  if (O.isFormData(a) && O.isFunction(a.entries)) {
    const n = {};
    return O.forEachEntry(a, (o, l) => {
      r(SA(o), l, n, 0);
    }), n;
  }
  return null;
}
function AA(a, r, n) {
  if (O.isString(a))
    try {
      return (r || JSON.parse)(a), O.trim(a);
    } catch (o) {
      if (o.name !== "SyntaxError")
        throw o;
    }
  return (n || JSON.stringify)(a);
}
const Oa = {
  transitional: Dp,
  adapter: ["xhr", "http", "fetch"],
  transformRequest: [function(r, n) {
    const o = n.getContentType() || "", l = o.indexOf("application/json") > -1, s = O.isObject(r);
    if (s && O.isHTMLForm(r) && (r = new FormData(r)), O.isFormData(r))
      return l ? JSON.stringify(zp(r)) : r;
    if (O.isArrayBuffer(r) || O.isBuffer(r) || O.isStream(r) || O.isFile(r) || O.isBlob(r) || O.isReadableStream(r))
      return r;
    if (O.isArrayBufferView(r))
      return r.buffer;
    if (O.isURLSearchParams(r))
      return n.setContentType("application/x-www-form-urlencoded;charset=utf-8", !1), r.toString();
    let d;
    if (s) {
      if (o.indexOf("application/x-www-form-urlencoded") > -1)
        return xA(r, this.formSerializer).toString();
      if ((d = O.isFileList(r)) || o.indexOf("multipart/form-data") > -1) {
        const p = this.env && this.env.FormData;
        return ai(
          d ? { "files[]": r } : r,
          p && new p(),
          this.formSerializer
        );
      }
    }
    return s || l ? (n.setContentType("application/json", !1), AA(r)) : r;
  }],
  transformResponse: [function(r) {
    const n = this.transitional || Oa.transitional, o = n && n.forcedJSONParsing, l = this.responseType === "json";
    if (O.isResponse(r) || O.isReadableStream(r))
      return r;
    if (r && O.isString(r) && (o && !this.responseType || l)) {
      const f = !(n && n.silentJSONParsing) && l;
      try {
        return JSON.parse(r, this.parseReviver);
      } catch (d) {
        if (f)
          throw d.name === "SyntaxError" ? ce.from(d, ce.ERR_BAD_RESPONSE, this, null, this.response) : d;
      }
    }
    return r;
  }],
  /**
   * A timeout in milliseconds to abort a request. If set to 0 (default) a
   * timeout is not created.
   */
  timeout: 0,
  xsrfCookieName: "XSRF-TOKEN",
  xsrfHeaderName: "X-XSRF-TOKEN",
  maxContentLength: -1,
  maxBodyLength: -1,
  env: {
    FormData: dt.classes.FormData,
    Blob: dt.classes.Blob
  },
  validateStatus: function(r) {
    return r >= 200 && r < 300;
  },
  headers: {
    common: {
      Accept: "application/json, text/plain, */*",
      "Content-Type": void 0
    }
  }
};
O.forEach(["delete", "get", "head", "post", "put", "patch"], (a) => {
  Oa.headers[a] = {};
});
const CA = O.toObjectSet([
  "age",
  "authorization",
  "content-length",
  "content-type",
  "etag",
  "expires",
  "from",
  "host",
  "if-modified-since",
  "if-unmodified-since",
  "last-modified",
  "location",
  "max-forwards",
  "proxy-authorization",
  "referer",
  "retry-after",
  "user-agent"
]), TA = (a) => {
  const r = {};
  let n, o, l;
  return a && a.split(`
`).forEach(function(f) {
    l = f.indexOf(":"), n = f.substring(0, l).trim().toLowerCase(), o = f.substring(l + 1).trim(), !(!n || r[n] && CA[n]) && (n === "set-cookie" ? r[n] ? r[n].push(o) : r[n] = [o] : r[n] = r[n] ? r[n] + ", " + o : o);
  }), r;
}, Wd = Symbol("internals");
function ma(a) {
  return a && String(a).trim().toLowerCase();
}
function Ro(a) {
  return a === !1 || a == null ? a : O.isArray(a) ? a.map(Ro) : String(a);
}
function EA(a) {
  const r = /* @__PURE__ */ Object.create(null), n = /([^\s,;=]+)\s*(?:=\s*([^,;]+))?/g;
  let o;
  for (; o = n.exec(a); )
    r[o[1]] = o[2];
  return r;
}
const kA = (a) => /^[-_a-zA-Z0-9^`|~,!#$%&'*+.]+$/.test(a.trim());
function Fl(a, r, n, o, l) {
  if (O.isFunction(o))
    return o.call(this, r, n);
  if (l && (r = n), !!O.isString(r)) {
    if (O.isString(o))
      return r.indexOf(o) !== -1;
    if (O.isRegExp(o))
      return o.test(r);
  }
}
function OA(a) {
  return a.trim().toLowerCase().replace(/([a-z\d])(\w*)/g, (r, n, o) => n.toUpperCase() + o);
}
function $A(a, r) {
  const n = O.toCamelCase(" " + r);
  ["get", "set", "has"].forEach((o) => {
    Object.defineProperty(a, o + n, {
      value: function(l, s, f) {
        return this[o].call(this, r, l, s, f);
      },
      configurable: !0
    });
  });
}
let St = class {
  constructor(r) {
    r && this.set(r);
  }
  set(r, n, o) {
    const l = this;
    function s(d, p, g) {
      const m = ma(p);
      if (!m)
        throw new Error("header name must be a non-empty string");
      const w = O.findKey(l, m);
      (!w || l[w] === void 0 || g === !0 || g === void 0 && l[w] !== !1) && (l[w || p] = Ro(d));
    }
    const f = (d, p) => O.forEach(d, (g, m) => s(g, m, p));
    if (O.isPlainObject(r) || r instanceof this.constructor)
      f(r, n);
    else if (O.isString(r) && (r = r.trim()) && !kA(r))
      f(TA(r), n);
    else if (O.isObject(r) && O.isIterable(r)) {
      let d = {}, p, g;
      for (const m of r) {
        if (!O.isArray(m))
          throw TypeError("Object iterator must return a key-value pair");
        d[g = m[0]] = (p = d[g]) ? O.isArray(p) ? [...p, m[1]] : [p, m[1]] : m[1];
      }
      f(d, n);
    } else
      r != null && s(n, r, o);
    return this;
  }
  get(r, n) {
    if (r = ma(r), r) {
      const o = O.findKey(this, r);
      if (o) {
        const l = this[o];
        if (!n)
          return l;
        if (n === !0)
          return EA(l);
        if (O.isFunction(n))
          return n.call(this, l, o);
        if (O.isRegExp(n))
          return n.exec(l);
        throw new TypeError("parser must be boolean|regexp|function");
      }
    }
  }
  has(r, n) {
    if (r = ma(r), r) {
      const o = O.findKey(this, r);
      return !!(o && this[o] !== void 0 && (!n || Fl(this, this[o], o, n)));
    }
    return !1;
  }
  delete(r, n) {
    const o = this;
    let l = !1;
    function s(f) {
      if (f = ma(f), f) {
        const d = O.findKey(o, f);
        d && (!n || Fl(o, o[d], d, n)) && (delete o[d], l = !0);
      }
    }
    return O.isArray(r) ? r.forEach(s) : s(r), l;
  }
  clear(r) {
    const n = Object.keys(this);
    let o = n.length, l = !1;
    for (; o--; ) {
      const s = n[o];
      (!r || Fl(this, this[s], s, r, !0)) && (delete this[s], l = !0);
    }
    return l;
  }
  normalize(r) {
    const n = this, o = {};
    return O.forEach(this, (l, s) => {
      const f = O.findKey(o, s);
      if (f) {
        n[f] = Ro(l), delete n[s];
        return;
      }
      const d = r ? OA(s) : String(s).trim();
      d !== s && delete n[s], n[d] = Ro(l), o[d] = !0;
    }), this;
  }
  concat(...r) {
    return this.constructor.concat(this, ...r);
  }
  toJSON(r) {
    const n = /* @__PURE__ */ Object.create(null);
    return O.forEach(this, (o, l) => {
      o != null && o !== !1 && (n[l] = r && O.isArray(o) ? o.join(", ") : o);
    }), n;
  }
  [Symbol.iterator]() {
    return Object.entries(this.toJSON())[Symbol.iterator]();
  }
  toString() {
    return Object.entries(this.toJSON()).map(([r, n]) => r + ": " + n).join(`
`);
  }
  getSetCookie() {
    return this.get("set-cookie") || [];
  }
  get [Symbol.toStringTag]() {
    return "AxiosHeaders";
  }
  static from(r) {
    return r instanceof this ? r : new this(r);
  }
  static concat(r, ...n) {
    const o = new this(r);
    return n.forEach((l) => o.set(l)), o;
  }
  static accessor(r) {
    const o = (this[Wd] = this[Wd] = {
      accessors: {}
    }).accessors, l = this.prototype;
    function s(f) {
      const d = ma(f);
      o[d] || ($A(l, f), o[d] = !0);
    }
    return O.isArray(r) ? r.forEach(s) : s(r), this;
  }
};
St.accessor(["Content-Type", "Content-Length", "Accept", "Accept-Encoding", "User-Agent", "Authorization"]);
O.reduceDescriptors(St.prototype, ({ value: a }, r) => {
  let n = r[0].toUpperCase() + r.slice(1);
  return {
    get: () => a,
    set(o) {
      this[n] = o;
    }
  };
});
O.freezeMethods(St);
function Pl(a, r) {
  const n = this || Oa, o = r || n, l = St.from(o.headers);
  let s = o.data;
  return O.forEach(a, function(d) {
    s = d.call(n, s, l.normalize(), r ? r.status : void 0);
  }), l.normalize(), s;
}
function Mp(a) {
  return !!(a && a.__CANCEL__);
}
function jr(a, r, n) {
  ce.call(this, a ?? "canceled", ce.ERR_CANCELED, r, n), this.name = "CanceledError";
}
O.inherits(jr, ce, {
  __CANCEL__: !0
});
function Hp(a, r, n) {
  const o = n.config.validateStatus;
  !n.status || !o || o(n.status) ? a(n) : r(new ce(
    "Request failed with status code " + n.status,
    [ce.ERR_BAD_REQUEST, ce.ERR_BAD_RESPONSE][Math.floor(n.status / 100) - 4],
    n.config,
    n.request,
    n
  ));
}
function RA(a) {
  const r = /^([-+\w]{1,25})(:?\/\/|:)/.exec(a);
  return r && r[1] || "";
}
function FA(a, r) {
  a = a || 10;
  const n = new Array(a), o = new Array(a);
  let l = 0, s = 0, f;
  return r = r !== void 0 ? r : 1e3, function(p) {
    const g = Date.now(), m = o[s];
    f || (f = g), n[l] = p, o[l] = g;
    let w = s, S = 0;
    for (; w !== l; )
      S += n[w++], w = w % a;
    if (l = (l + 1) % a, l === s && (s = (s + 1) % a), g - f < r)
      return;
    const b = m && g - m;
    return b ? Math.round(S * 1e3 / b) : void 0;
  };
}
function PA(a, r) {
  let n = 0, o = 1e3 / r, l, s;
  const f = (g, m = Date.now()) => {
    n = m, l = null, s && (clearTimeout(s), s = null), a(...g);
  };
  return [(...g) => {
    const m = Date.now(), w = m - n;
    w >= o ? f(g, m) : (l = g, s || (s = setTimeout(() => {
      s = null, f(l);
    }, o - w)));
  }, () => l && f(l)];
}
const Uo = (a, r, n = 3) => {
  let o = 0;
  const l = FA(50, 250);
  return PA((s) => {
    const f = s.loaded, d = s.lengthComputable ? s.total : void 0, p = f - o, g = l(p), m = f <= d;
    o = f;
    const w = {
      loaded: f,
      total: d,
      progress: d ? f / d : void 0,
      bytes: p,
      rate: g || void 0,
      estimated: g && d && m ? (d - f) / g : void 0,
      event: s,
      lengthComputable: d != null,
      [r ? "download" : "upload"]: !0
    };
    a(w);
  }, n);
}, qd = (a, r) => {
  const n = a != null;
  return [(o) => r[0]({
    lengthComputable: n,
    total: a,
    loaded: o
  }), r[1]];
}, Gd = (a) => (...r) => O.asap(() => a(...r)), LA = dt.hasStandardBrowserEnv ? /* @__PURE__ */ ((a, r) => (n) => (n = new URL(n, dt.origin), a.protocol === n.protocol && a.host === n.host && (r || a.port === n.port)))(
  new URL(dt.origin),
  dt.navigator && /(msie|trident)/i.test(dt.navigator.userAgent)
) : () => !0, IA = dt.hasStandardBrowserEnv ? (
  // Standard browser envs support document.cookie
  {
    write(a, r, n, o, l, s, f) {
      if (typeof document > "u") return;
      const d = [`${a}=${encodeURIComponent(r)}`];
      O.isNumber(n) && d.push(`expires=${new Date(n).toUTCString()}`), O.isString(o) && d.push(`path=${o}`), O.isString(l) && d.push(`domain=${l}`), s === !0 && d.push("secure"), O.isString(f) && d.push(`SameSite=${f}`), document.cookie = d.join("; ");
    },
    read(a) {
      if (typeof document > "u") return null;
      const r = document.cookie.match(new RegExp("(?:^|; )" + a + "=([^;]*)"));
      return r ? decodeURIComponent(r[1]) : null;
    },
    remove(a) {
      this.write(a, "", Date.now() - 864e5, "/");
    }
  }
) : (
  // Non-standard browser env (web workers, react-native) lack needed support.
  {
    write() {
    },
    read() {
      return null;
    },
    remove() {
    }
  }
);
function NA(a) {
  return /^([a-z][a-z\d+\-.]*:)?\/\//i.test(a);
}
function VA(a, r) {
  return r ? a.replace(/\/?\/$/, "") + "/" + r.replace(/^\/+/, "") : a;
}
function Up(a, r, n) {
  let o = !NA(r);
  return a && (o || n == !1) ? VA(a, r) : r;
}
const jd = (a) => a instanceof St ? { ...a } : a;
function sr(a, r) {
  r = r || {};
  const n = {};
  function o(g, m, w, S) {
    return O.isPlainObject(g) && O.isPlainObject(m) ? O.merge.call({ caseless: S }, g, m) : O.isPlainObject(m) ? O.merge({}, m) : O.isArray(m) ? m.slice() : m;
  }
  function l(g, m, w, S) {
    if (O.isUndefined(m)) {
      if (!O.isUndefined(g))
        return o(void 0, g, w, S);
    } else return o(g, m, w, S);
  }
  function s(g, m) {
    if (!O.isUndefined(m))
      return o(void 0, m);
  }
  function f(g, m) {
    if (O.isUndefined(m)) {
      if (!O.isUndefined(g))
        return o(void 0, g);
    } else return o(void 0, m);
  }
  function d(g, m, w) {
    if (w in r)
      return o(g, m);
    if (w in a)
      return o(void 0, g);
  }
  const p = {
    url: s,
    method: s,
    data: s,
    baseURL: f,
    transformRequest: f,
    transformResponse: f,
    paramsSerializer: f,
    timeout: f,
    timeoutMessage: f,
    withCredentials: f,
    withXSRFToken: f,
    adapter: f,
    responseType: f,
    xsrfCookieName: f,
    xsrfHeaderName: f,
    onUploadProgress: f,
    onDownloadProgress: f,
    decompress: f,
    maxContentLength: f,
    maxBodyLength: f,
    beforeRedirect: f,
    transport: f,
    httpAgent: f,
    httpsAgent: f,
    cancelToken: f,
    socketPath: f,
    responseEncoding: f,
    validateStatus: d,
    headers: (g, m, w) => l(jd(g), jd(m), w, !0)
  };
  return O.forEach(Object.keys({ ...a, ...r }), function(m) {
    const w = p[m] || l, S = w(a[m], r[m], m);
    O.isUndefined(S) && w !== d || (n[m] = S);
  }), n;
}
const Wp = (a) => {
  const r = sr({}, a);
  let { data: n, withXSRFToken: o, xsrfHeaderName: l, xsrfCookieName: s, headers: f, auth: d } = r;
  if (r.headers = f = St.from(f), r.url = Vp(Up(r.baseURL, r.url, r.allowAbsoluteUrls), a.params, a.paramsSerializer), d && f.set(
    "Authorization",
    "Basic " + btoa((d.username || "") + ":" + (d.password ? unescape(encodeURIComponent(d.password)) : ""))
  ), O.isFormData(n)) {
    if (dt.hasStandardBrowserEnv || dt.hasStandardBrowserWebWorkerEnv)
      f.setContentType(void 0);
    else if (O.isFunction(n.getHeaders)) {
      const p = n.getHeaders(), g = ["content-type", "content-length"];
      Object.entries(p).forEach(([m, w]) => {
        g.includes(m.toLowerCase()) && f.set(m, w);
      });
    }
  }
  if (dt.hasStandardBrowserEnv && (o && O.isFunction(o) && (o = o(r)), o || o !== !1 && LA(r.url))) {
    const p = l && s && IA.read(s);
    p && f.set(l, p);
  }
  return r;
}, DA = typeof XMLHttpRequest < "u", zA = DA && function(a) {
  return new Promise(function(n, o) {
    const l = Wp(a);
    let s = l.data;
    const f = St.from(l.headers).normalize();
    let { responseType: d, onUploadProgress: p, onDownloadProgress: g } = l, m, w, S, b, y;
    function x() {
      b && b(), y && y(), l.cancelToken && l.cancelToken.unsubscribe(m), l.signal && l.signal.removeEventListener("abort", m);
    }
    let B = new XMLHttpRequest();
    B.open(l.method.toUpperCase(), l.url, !0), B.timeout = l.timeout;
    function E() {
      if (!B)
        return;
      const L = St.from(
        "getAllResponseHeaders" in B && B.getAllResponseHeaders()
      ), k = {
        data: !d || d === "text" || d === "json" ? B.responseText : B.response,
        status: B.status,
        statusText: B.statusText,
        headers: L,
        config: a,
        request: B
      };
      Hp(function(H) {
        n(H), x();
      }, function(H) {
        o(H), x();
      }, k), B = null;
    }
    "onloadend" in B ? B.onloadend = E : B.onreadystatechange = function() {
      !B || B.readyState !== 4 || B.status === 0 && !(B.responseURL && B.responseURL.indexOf("file:") === 0) || setTimeout(E);
    }, B.onabort = function() {
      B && (o(new ce("Request aborted", ce.ECONNABORTED, a, B)), B = null);
    }, B.onerror = function($) {
      const k = $ && $.message ? $.message : "Network Error", z = new ce(k, ce.ERR_NETWORK, a, B);
      z.event = $ || null, o(z), B = null;
    }, B.ontimeout = function() {
      let $ = l.timeout ? "timeout of " + l.timeout + "ms exceeded" : "timeout exceeded";
      const k = l.transitional || Dp;
      l.timeoutErrorMessage && ($ = l.timeoutErrorMessage), o(new ce(
        $,
        k.clarifyTimeoutError ? ce.ETIMEDOUT : ce.ECONNABORTED,
        a,
        B
      )), B = null;
    }, s === void 0 && f.setContentType(null), "setRequestHeader" in B && O.forEach(f.toJSON(), function($, k) {
      B.setRequestHeader(k, $);
    }), O.isUndefined(l.withCredentials) || (B.withCredentials = !!l.withCredentials), d && d !== "json" && (B.responseType = l.responseType), g && ([S, y] = Uo(g, !0), B.addEventListener("progress", S)), p && B.upload && ([w, b] = Uo(p), B.upload.addEventListener("progress", w), B.upload.addEventListener("loadend", b)), (l.cancelToken || l.signal) && (m = (L) => {
      B && (o(!L || L.type ? new jr(null, a, B) : L), B.abort(), B = null);
    }, l.cancelToken && l.cancelToken.subscribe(m), l.signal && (l.signal.aborted ? m() : l.signal.addEventListener("abort", m)));
    const F = RA(l.url);
    if (F && dt.protocols.indexOf(F) === -1) {
      o(new ce("Unsupported protocol " + F + ":", ce.ERR_BAD_REQUEST, a));
      return;
    }
    B.send(s || null);
  });
}, MA = (a, r) => {
  const { length: n } = a = a ? a.filter(Boolean) : [];
  if (r || n) {
    let o = new AbortController(), l;
    const s = function(g) {
      if (!l) {
        l = !0, d();
        const m = g instanceof Error ? g : this.reason;
        o.abort(m instanceof ce ? m : new jr(m instanceof Error ? m.message : m));
      }
    };
    let f = r && setTimeout(() => {
      f = null, s(new ce(`timeout ${r} of ms exceeded`, ce.ETIMEDOUT));
    }, r);
    const d = () => {
      a && (f && clearTimeout(f), f = null, a.forEach((g) => {
        g.unsubscribe ? g.unsubscribe(s) : g.removeEventListener("abort", s);
      }), a = null);
    };
    a.forEach((g) => g.addEventListener("abort", s));
    const { signal: p } = o;
    return p.unsubscribe = () => O.asap(d), p;
  }
}, HA = function* (a, r) {
  let n = a.byteLength;
  if (n < r) {
    yield a;
    return;
  }
  let o = 0, l;
  for (; o < n; )
    l = o + r, yield a.slice(o, l), o = l;
}, UA = async function* (a, r) {
  for await (const n of WA(a))
    yield* HA(n, r);
}, WA = async function* (a) {
  if (a[Symbol.asyncIterator]) {
    yield* a;
    return;
  }
  const r = a.getReader();
  try {
    for (; ; ) {
      const { done: n, value: o } = await r.read();
      if (n)
        break;
      yield o;
    }
  } finally {
    await r.cancel();
  }
}, Kd = (a, r, n, o) => {
  const l = UA(a, r);
  let s = 0, f, d = (p) => {
    f || (f = !0, o && o(p));
  };
  return new ReadableStream({
    async pull(p) {
      try {
        const { done: g, value: m } = await l.next();
        if (g) {
          d(), p.close();
          return;
        }
        let w = m.byteLength;
        if (n) {
          let S = s += w;
          n(S);
        }
        p.enqueue(new Uint8Array(m));
      } catch (g) {
        throw d(g), g;
      }
    },
    cancel(p) {
      return d(p), l.return();
    }
  }, {
    highWaterMark: 2
  });
}, Jd = 64 * 1024, { isFunction: ko } = O, qA = (({ Request: a, Response: r }) => ({
  Request: a,
  Response: r
}))(O.global), {
  ReadableStream: Xd,
  TextEncoder: Yd
} = O.global, Zd = (a, ...r) => {
  try {
    return !!a(...r);
  } catch {
    return !1;
  }
}, GA = (a) => {
  a = O.merge.call({
    skipUndefined: !0
  }, qA, a);
  const { fetch: r, Request: n, Response: o } = a, l = r ? ko(r) : typeof fetch == "function", s = ko(n), f = ko(o);
  if (!l)
    return !1;
  const d = l && ko(Xd), p = l && (typeof Yd == "function" ? /* @__PURE__ */ ((y) => (x) => y.encode(x))(new Yd()) : async (y) => new Uint8Array(await new n(y).arrayBuffer())), g = s && d && Zd(() => {
    let y = !1;
    const x = new n(dt.origin, {
      body: new Xd(),
      method: "POST",
      get duplex() {
        return y = !0, "half";
      }
    }).headers.has("Content-Type");
    return y && !x;
  }), m = f && d && Zd(() => O.isReadableStream(new o("").body)), w = {
    stream: m && ((y) => y.body)
  };
  l && ["text", "arrayBuffer", "blob", "formData", "stream"].forEach((y) => {
    !w[y] && (w[y] = (x, B) => {
      let E = x && x[y];
      if (E)
        return E.call(x);
      throw new ce(`Response type '${y}' is not supported`, ce.ERR_NOT_SUPPORT, B);
    });
  });
  const S = async (y) => {
    if (y == null)
      return 0;
    if (O.isBlob(y))
      return y.size;
    if (O.isSpecCompliantForm(y))
      return (await new n(dt.origin, {
        method: "POST",
        body: y
      }).arrayBuffer()).byteLength;
    if (O.isArrayBufferView(y) || O.isArrayBuffer(y))
      return y.byteLength;
    if (O.isURLSearchParams(y) && (y = y + ""), O.isString(y))
      return (await p(y)).byteLength;
  }, b = async (y, x) => {
    const B = O.toFiniteNumber(y.getContentLength());
    return B ?? S(x);
  };
  return async (y) => {
    let {
      url: x,
      method: B,
      data: E,
      signal: F,
      cancelToken: L,
      timeout: $,
      onDownloadProgress: k,
      onUploadProgress: z,
      responseType: H,
      headers: ie,
      withCredentials: se = "same-origin",
      fetchOptions: Ae
    } = Wp(y), Ie = r || fetch;
    H = H ? (H + "").toLowerCase() : "text";
    let Ce = MA([F, L && L.toAbortSignal()], $), Re = null;
    const q = Ce && Ce.unsubscribe && (() => {
      Ce.unsubscribe();
    });
    let ge;
    try {
      if (z && g && B !== "get" && B !== "head" && (ge = await b(ie, E)) !== 0) {
        let Pe = new n(x, {
          method: "POST",
          body: E,
          duplex: "half"
        }), it;
        if (O.isFormData(E) && (it = Pe.headers.get("content-type")) && ie.setContentType(it), Pe.body) {
          const [Y, me] = qd(
            ge,
            Uo(Gd(z))
          );
          E = Kd(Pe.body, Jd, Y, me);
        }
      }
      O.isString(se) || (se = se ? "include" : "omit");
      const de = s && "credentials" in n.prototype, Se = {
        ...Ae,
        signal: Ce,
        method: B.toUpperCase(),
        headers: ie.normalize().toJSON(),
        body: E,
        duplex: "half",
        credentials: de ? se : void 0
      };
      Re = s && new n(x, Se);
      let pe = await (s ? Ie(Re, Ae) : Ie(x, Se));
      const Ke = m && (H === "stream" || H === "response");
      if (m && (k || Ke && q)) {
        const Pe = {};
        ["status", "statusText", "headers"].forEach((De) => {
          Pe[De] = pe[De];
        });
        const it = O.toFiniteNumber(pe.headers.get("content-length")), [Y, me] = k && qd(
          it,
          Uo(Gd(k), !0)
        ) || [];
        pe = new o(
          Kd(pe.body, Jd, Y, () => {
            me && me(), q && q();
          }),
          Pe
        );
      }
      H = H || "text";
      let Me = await w[O.findKey(w, H) || "text"](pe, y);
      return !Ke && q && q(), await new Promise((Pe, it) => {
        Hp(Pe, it, {
          data: Me,
          headers: St.from(pe.headers),
          status: pe.status,
          statusText: pe.statusText,
          config: y,
          request: Re
        });
      });
    } catch (de) {
      throw q && q(), de && de.name === "TypeError" && /Load failed|fetch/i.test(de.message) ? Object.assign(
        new ce("Network Error", ce.ERR_NETWORK, y, Re),
        {
          cause: de.cause || de
        }
      ) : ce.from(de, de && de.code, y, Re);
    }
  };
}, jA = /* @__PURE__ */ new Map(), qp = (a) => {
  let r = a && a.env || {};
  const { fetch: n, Request: o, Response: l } = r, s = [
    o,
    l,
    n
  ];
  let f = s.length, d = f, p, g, m = jA;
  for (; d--; )
    p = s[d], g = m.get(p), g === void 0 && m.set(p, g = d ? /* @__PURE__ */ new Map() : GA(r)), m = g;
  return g;
};
qp();
const Cs = {
  http: fA,
  xhr: zA,
  fetch: {
    get: qp
  }
};
O.forEach(Cs, (a, r) => {
  if (a) {
    try {
      Object.defineProperty(a, "name", { value: r });
    } catch {
    }
    Object.defineProperty(a, "adapterName", { value: r });
  }
});
const Qd = (a) => `- ${a}`, KA = (a) => O.isFunction(a) || a === null || a === !1;
function JA(a, r) {
  a = O.isArray(a) ? a : [a];
  const { length: n } = a;
  let o, l;
  const s = {};
  for (let f = 0; f < n; f++) {
    o = a[f];
    let d;
    if (l = o, !KA(o) && (l = Cs[(d = String(o)).toLowerCase()], l === void 0))
      throw new ce(`Unknown adapter '${d}'`);
    if (l && (O.isFunction(l) || (l = l.get(r))))
      break;
    s[d || "#" + f] = l;
  }
  if (!l) {
    const f = Object.entries(s).map(
      ([p, g]) => `adapter ${p} ` + (g === !1 ? "is not supported by the environment" : "is not available in the build")
    );
    let d = n ? f.length > 1 ? `since :
` + f.map(Qd).join(`
`) : " " + Qd(f[0]) : "as no adapter specified";
    throw new ce(
      "There is no suitable adapter to dispatch the request " + d,
      "ERR_NOT_SUPPORT"
    );
  }
  return l;
}
const Gp = {
  /**
   * Resolve an adapter from a list of adapter names or functions.
   * @type {Function}
   */
  getAdapter: JA,
  /**
   * Exposes all known adapters
   * @type {Object<string, Function|Object>}
   */
  adapters: Cs
};
function Ll(a) {
  if (a.cancelToken && a.cancelToken.throwIfRequested(), a.signal && a.signal.aborted)
    throw new jr(null, a);
}
function ec(a) {
  return Ll(a), a.headers = St.from(a.headers), a.data = Pl.call(
    a,
    a.transformRequest
  ), ["post", "put", "patch"].indexOf(a.method) !== -1 && a.headers.setContentType("application/x-www-form-urlencoded", !1), Gp.getAdapter(a.adapter || Oa.adapter, a)(a).then(function(o) {
    return Ll(a), o.data = Pl.call(
      a,
      a.transformResponse,
      o
    ), o.headers = St.from(o.headers), o;
  }, function(o) {
    return Mp(o) || (Ll(a), o && o.response && (o.response.data = Pl.call(
      a,
      a.transformResponse,
      o.response
    ), o.response.headers = St.from(o.response.headers))), Promise.reject(o);
  });
}
const jp = "1.13.2", oi = {};
["object", "boolean", "number", "function", "string", "symbol"].forEach((a, r) => {
  oi[a] = function(o) {
    return typeof o === a || "a" + (r < 1 ? "n " : " ") + a;
  };
});
const tc = {};
oi.transitional = function(r, n, o) {
  function l(s, f) {
    return "[Axios v" + jp + "] Transitional option '" + s + "'" + f + (o ? ". " + o : "");
  }
  return (s, f, d) => {
    if (r === !1)
      throw new ce(
        l(f, " has been removed" + (n ? " in " + n : "")),
        ce.ERR_DEPRECATED
      );
    return n && !tc[f] && (tc[f] = !0, console.warn(
      l(
        f,
        " has been deprecated since v" + n + " and will be removed in the near future"
      )
    )), r ? r(s, f, d) : !0;
  };
};
oi.spelling = function(r) {
  return (n, o) => (console.warn(`${o} is likely a misspelling of ${r}`), !0);
};
function XA(a, r, n) {
  if (typeof a != "object")
    throw new ce("options must be an object", ce.ERR_BAD_OPTION_VALUE);
  const o = Object.keys(a);
  let l = o.length;
  for (; l-- > 0; ) {
    const s = o[l], f = r[s];
    if (f) {
      const d = a[s], p = d === void 0 || f(d, s, a);
      if (p !== !0)
        throw new ce("option " + s + " must be " + p, ce.ERR_BAD_OPTION_VALUE);
      continue;
    }
    if (n !== !0)
      throw new ce("Unknown option " + s, ce.ERR_BAD_OPTION);
  }
}
const Fo = {
  assertOptions: XA,
  validators: oi
}, un = Fo.validators;
let or = class {
  constructor(r) {
    this.defaults = r || {}, this.interceptors = {
      request: new Ud(),
      response: new Ud()
    };
  }
  /**
   * Dispatch a request
   *
   * @param {String|Object} configOrUrl The config specific for this request (merged with this.defaults)
   * @param {?Object} config
   *
   * @returns {Promise} The Promise to be fulfilled
   */
  async request(r, n) {
    try {
      return await this._request(r, n);
    } catch (o) {
      if (o instanceof Error) {
        let l = {};
        Error.captureStackTrace ? Error.captureStackTrace(l) : l = new Error();
        const s = l.stack ? l.stack.replace(/^.+\n/, "") : "";
        try {
          o.stack ? s && !String(o.stack).endsWith(s.replace(/^.+\n.+\n/, "")) && (o.stack += `
` + s) : o.stack = s;
        } catch {
        }
      }
      throw o;
    }
  }
  _request(r, n) {
    typeof r == "string" ? (n = n || {}, n.url = r) : n = r || {}, n = sr(this.defaults, n);
    const { transitional: o, paramsSerializer: l, headers: s } = n;
    o !== void 0 && Fo.assertOptions(o, {
      silentJSONParsing: un.transitional(un.boolean),
      forcedJSONParsing: un.transitional(un.boolean),
      clarifyTimeoutError: un.transitional(un.boolean)
    }, !1), l != null && (O.isFunction(l) ? n.paramsSerializer = {
      serialize: l
    } : Fo.assertOptions(l, {
      encode: un.function,
      serialize: un.function
    }, !0)), n.allowAbsoluteUrls !== void 0 || (this.defaults.allowAbsoluteUrls !== void 0 ? n.allowAbsoluteUrls = this.defaults.allowAbsoluteUrls : n.allowAbsoluteUrls = !0), Fo.assertOptions(n, {
      baseUrl: un.spelling("baseURL"),
      withXsrfToken: un.spelling("withXSRFToken")
    }, !0), n.method = (n.method || this.defaults.method || "get").toLowerCase();
    let f = s && O.merge(
      s.common,
      s[n.method]
    );
    s && O.forEach(
      ["delete", "get", "head", "post", "put", "patch", "common"],
      (y) => {
        delete s[y];
      }
    ), n.headers = St.concat(f, s);
    const d = [];
    let p = !0;
    this.interceptors.request.forEach(function(x) {
      typeof x.runWhen == "function" && x.runWhen(n) === !1 || (p = p && x.synchronous, d.unshift(x.fulfilled, x.rejected));
    });
    const g = [];
    this.interceptors.response.forEach(function(x) {
      g.push(x.fulfilled, x.rejected);
    });
    let m, w = 0, S;
    if (!p) {
      const y = [ec.bind(this), void 0];
      for (y.unshift(...d), y.push(...g), S = y.length, m = Promise.resolve(n); w < S; )
        m = m.then(y[w++], y[w++]);
      return m;
    }
    S = d.length;
    let b = n;
    for (; w < S; ) {
      const y = d[w++], x = d[w++];
      try {
        b = y(b);
      } catch (B) {
        x.call(this, B);
        break;
      }
    }
    try {
      m = ec.call(this, b);
    } catch (y) {
      return Promise.reject(y);
    }
    for (w = 0, S = g.length; w < S; )
      m = m.then(g[w++], g[w++]);
    return m;
  }
  getUri(r) {
    r = sr(this.defaults, r);
    const n = Up(r.baseURL, r.url, r.allowAbsoluteUrls);
    return Vp(n, r.params, r.paramsSerializer);
  }
};
O.forEach(["delete", "get", "head", "options"], function(r) {
  or.prototype[r] = function(n, o) {
    return this.request(sr(o || {}, {
      method: r,
      url: n,
      data: (o || {}).data
    }));
  };
});
O.forEach(["post", "put", "patch"], function(r) {
  function n(o) {
    return function(s, f, d) {
      return this.request(sr(d || {}, {
        method: r,
        headers: o ? {
          "Content-Type": "multipart/form-data"
        } : {},
        url: s,
        data: f
      }));
    };
  }
  or.prototype[r] = n(), or.prototype[r + "Form"] = n(!0);
});
let YA = class Kp {
  constructor(r) {
    if (typeof r != "function")
      throw new TypeError("executor must be a function.");
    let n;
    this.promise = new Promise(function(s) {
      n = s;
    });
    const o = this;
    this.promise.then((l) => {
      if (!o._listeners) return;
      let s = o._listeners.length;
      for (; s-- > 0; )
        o._listeners[s](l);
      o._listeners = null;
    }), this.promise.then = (l) => {
      let s;
      const f = new Promise((d) => {
        o.subscribe(d), s = d;
      }).then(l);
      return f.cancel = function() {
        o.unsubscribe(s);
      }, f;
    }, r(function(s, f, d) {
      o.reason || (o.reason = new jr(s, f, d), n(o.reason));
    });
  }
  /**
   * Throws a `CanceledError` if cancellation has been requested.
   */
  throwIfRequested() {
    if (this.reason)
      throw this.reason;
  }
  /**
   * Subscribe to the cancel signal
   */
  subscribe(r) {
    if (this.reason) {
      r(this.reason);
      return;
    }
    this._listeners ? this._listeners.push(r) : this._listeners = [r];
  }
  /**
   * Unsubscribe from the cancel signal
   */
  unsubscribe(r) {
    if (!this._listeners)
      return;
    const n = this._listeners.indexOf(r);
    n !== -1 && this._listeners.splice(n, 1);
  }
  toAbortSignal() {
    const r = new AbortController(), n = (o) => {
      r.abort(o);
    };
    return this.subscribe(n), r.signal.unsubscribe = () => this.unsubscribe(n), r.signal;
  }
  /**
   * Returns an object that contains a new `CancelToken` and a function that, when called,
   * cancels the `CancelToken`.
   */
  static source() {
    let r;
    return {
      token: new Kp(function(l) {
        r = l;
      }),
      cancel: r
    };
  }
};
function ZA(a) {
  return function(n) {
    return a.apply(null, n);
  };
}
function QA(a) {
  return O.isObject(a) && a.isAxiosError === !0;
}
const rs = {
  Continue: 100,
  SwitchingProtocols: 101,
  Processing: 102,
  EarlyHints: 103,
  Ok: 200,
  Created: 201,
  Accepted: 202,
  NonAuthoritativeInformation: 203,
  NoContent: 204,
  ResetContent: 205,
  PartialContent: 206,
  MultiStatus: 207,
  AlreadyReported: 208,
  ImUsed: 226,
  MultipleChoices: 300,
  MovedPermanently: 301,
  Found: 302,
  SeeOther: 303,
  NotModified: 304,
  UseProxy: 305,
  Unused: 306,
  TemporaryRedirect: 307,
  PermanentRedirect: 308,
  BadRequest: 400,
  Unauthorized: 401,
  PaymentRequired: 402,
  Forbidden: 403,
  NotFound: 404,
  MethodNotAllowed: 405,
  NotAcceptable: 406,
  ProxyAuthenticationRequired: 407,
  RequestTimeout: 408,
  Conflict: 409,
  Gone: 410,
  LengthRequired: 411,
  PreconditionFailed: 412,
  PayloadTooLarge: 413,
  UriTooLong: 414,
  UnsupportedMediaType: 415,
  RangeNotSatisfiable: 416,
  ExpectationFailed: 417,
  ImATeapot: 418,
  MisdirectedRequest: 421,
  UnprocessableEntity: 422,
  Locked: 423,
  FailedDependency: 424,
  TooEarly: 425,
  UpgradeRequired: 426,
  PreconditionRequired: 428,
  TooManyRequests: 429,
  RequestHeaderFieldsTooLarge: 431,
  UnavailableForLegalReasons: 451,
  InternalServerError: 500,
  NotImplemented: 501,
  BadGateway: 502,
  ServiceUnavailable: 503,
  GatewayTimeout: 504,
  HttpVersionNotSupported: 505,
  VariantAlsoNegotiates: 506,
  InsufficientStorage: 507,
  LoopDetected: 508,
  NotExtended: 510,
  NetworkAuthenticationRequired: 511,
  WebServerIsDown: 521,
  ConnectionTimedOut: 522,
  OriginIsUnreachable: 523,
  TimeoutOccurred: 524,
  SslHandshakeFailed: 525,
  InvalidSslCertificate: 526
};
Object.entries(rs).forEach(([a, r]) => {
  rs[r] = a;
});
function Jp(a) {
  const r = new or(a), n = Cp(or.prototype.request, r);
  return O.extend(n, or.prototype, r, { allOwnKeys: !0 }), O.extend(n, r, null, { allOwnKeys: !0 }), n.create = function(l) {
    return Jp(sr(a, l));
  }, n;
}
const Ye = Jp(Oa);
Ye.Axios = or;
Ye.CanceledError = jr;
Ye.CancelToken = YA;
Ye.isCancel = Mp;
Ye.VERSION = jp;
Ye.toFormData = ai;
Ye.AxiosError = ce;
Ye.Cancel = Ye.CanceledError;
Ye.all = function(r) {
  return Promise.all(r);
};
Ye.spread = ZA;
Ye.isAxiosError = QA;
Ye.mergeConfig = sr;
Ye.AxiosHeaders = St;
Ye.formToJSON = (a) => zp(O.isHTMLForm(a) ? new FormData(a) : a);
Ye.getAdapter = Gp.getAdapter;
Ye.HttpStatusCode = rs;
Ye.default = Ye;
const {
  Axios: QE,
  AxiosError: ek,
  CanceledError: tk,
  isCancel: nk,
  CancelToken: rk,
  VERSION: ak,
  all: ok,
  Cancel: ik,
  isAxiosError: lk,
  spread: sk,
  toFormData: uk,
  AxiosHeaders: fk,
  HttpStatusCode: dk,
  formToJSON: ck,
  getAdapter: pk,
  mergeConfig: vk
} = Ye, eC = {
  name: "VsWarning",
  status: "prototype",
  release: "0.1.0",
  components: {
    VsIcon: xs,
    VsButton: oB,
    VsBody: yB
  },
  props: {
    /**
    * The warning message to display to the user
    */
    icon: {
      type: String,
      default: "vs-icon-feedback-warning"
    },
    /**
    * Type of warning
    */
    type: {
      type: String,
      default: "normal",
      validator: (a) => a.match(
        /(normal|cookie)/
      )
    },
    /**
    * Message size - can be `small` or `normal`
    */
    size: {
      type: String,
      default: "normal",
      validator: (a) => a.match(
        /(small|normal)/
      )
    }
  },
  computed: {
    warningClasses() {
      return [
        `vs-warning--${this.size}`
      ];
    },
    btnAttrs() {
      const a = {};
      return this.type === "cookie" && (a.class = "vs-warning__cookie-trigger"), this.size === "small" && (a.size = "sm"), a;
    }
  },
  methods: {
    manageCookies() {
      typeof CookieControl < "u" && CookieControl.open();
    }
  }
}, tC = { class: "vs-warning__content" }, nC = { key: 0 };
function rC(a, r, n, o, l, s) {
  const f = qt("VsIcon"), d = qt("VsBody"), p = qt("VsButton");
  return P(), j("div", {
    class: Q(["vs-warning", s.warningClasses]),
    "data-test": "vs-warning"
  }, [
    je("div", tC, [
      Rt(f, {
        class: "vs-warning__icon",
        icon: n.icon,
        variant: "highlight"
      }, null, 8, ["icon"]),
      je("div", null, [
        Rt(d, null, {
          default: ee(() => [
            je("p", null, [
              W(a.$slots, "default")
            ]),
            a.$slots["extra-content"] && a.$slots["extra-content"]() ? (P(), j("p", nC, [
              W(a.$slots, "extra-content")
            ])) : $e("", !0)
          ]),
          _: 3
        })
      ])
    ]),
    Wr(Rt(p, xe(s.btnAttrs, {
      variant: "primary",
      class: "vs-warning__button",
      onClick: s.manageCookies
    }), {
      default: ee(() => [
        W(a.$slots, "button-text")
      ]),
      _: 3
    }, 16, ["onClick"]), [
      [cs, a.$slots["button-text"] && a.$slots["button-text"]()]
    ])
  ], 2);
}
const gk = /* @__PURE__ */ rn(eC, [["render", rC]]), aC = {}, oC = /* @__PURE__ */ Object.freeze(/* @__PURE__ */ Object.defineProperty({
  __proto__: null,
  default: aC
}, Symbol.toStringTag, { value: "Module" })), hk = /* @__PURE__ */ iB(oC);
var iC = "Function.prototype.bind called on incompatible ", lC = Object.prototype.toString, sC = Math.max, uC = "[object Function]", nc = function(r, n) {
  for (var o = [], l = 0; l < r.length; l += 1)
    o[l] = r[l];
  for (var s = 0; s < n.length; s += 1)
    o[s + r.length] = n[s];
  return o;
}, fC = function(r, n) {
  for (var o = [], l = n, s = 0; l < r.length; l += 1, s += 1)
    o[s] = r[l];
  return o;
}, dC = function(a, r) {
  for (var n = "", o = 0; o < a.length; o += 1)
    n += a[o], o + 1 < a.length && (n += r);
  return n;
}, cC = function(r) {
  var n = this;
  if (typeof n != "function" || lC.apply(n) !== uC)
    throw new TypeError(iC + n);
  for (var o = fC(arguments, 1), l, s = function() {
    if (this instanceof l) {
      var m = n.apply(
        this,
        nc(o, arguments)
      );
      return Object(m) === m ? m : this;
    }
    return n.apply(
      r,
      nc(o, arguments)
    );
  }, f = sC(0, n.length - o.length), d = [], p = 0; p < f; p++)
    d[p] = "$" + p;
  if (l = Function("binder", "return function (" + dC(d, ",") + "){ return binder.apply(this,arguments); }")(s), n.prototype) {
    var g = function() {
    };
    g.prototype = n.prototype, l.prototype = new g(), g.prototype = null;
  }
  return l;
}, pC = cC, $a = Function.prototype.bind || pC, Il, rc;
function Xp() {
  if (rc) return Il;
  rc = 1;
  var a = Function.prototype.call, r = Object.prototype.hasOwnProperty, n = $a;
  return Il = n.call(a, r), Il;
}
var Nl, ac;
function Yp() {
  return ac || (ac = 1, Nl = function() {
    if (typeof Symbol != "function" || typeof Object.getOwnPropertySymbols != "function")
      return !1;
    if (typeof Symbol.iterator == "symbol")
      return !0;
    var r = {}, n = Symbol("test"), o = Object(n);
    if (typeof n == "string" || Object.prototype.toString.call(n) !== "[object Symbol]" || Object.prototype.toString.call(o) !== "[object Symbol]")
      return !1;
    var l = 42;
    r[n] = l;
    for (var s in r)
      return !1;
    if (typeof Object.keys == "function" && Object.keys(r).length !== 0 || typeof Object.getOwnPropertyNames == "function" && Object.getOwnPropertyNames(r).length !== 0)
      return !1;
    var f = Object.getOwnPropertySymbols(r);
    if (f.length !== 1 || f[0] !== n || !Object.prototype.propertyIsEnumerable.call(r, n))
      return !1;
    if (typeof Object.getOwnPropertyDescriptor == "function") {
      var d = (
        /** @type {PropertyDescriptor} */
        Object.getOwnPropertyDescriptor(r, n)
      );
      if (d.value !== l || d.enumerable !== !0)
        return !1;
    }
    return !0;
  }), Nl;
}
var Vl, oc;
function Ts() {
  if (oc) return Vl;
  oc = 1;
  var a = Yp();
  return Vl = function() {
    return a() && !!Symbol.toStringTag;
  }, Vl;
}
var Zp = Object, vC = Error, gC = EvalError, hC = RangeError, mC = ReferenceError, Qp = SyntaxError, Ra = TypeError, yC = URIError, bC = Math.abs, _C = Math.floor, wC = Math.max, xC = Math.min, SC = Math.pow, BC = Math.round, AC = Number.isNaN || function(r) {
  return r !== r;
}, CC = AC, TC = function(r) {
  return CC(r) || r === 0 ? r : r < 0 ? -1 : 1;
}, EC = Object.getOwnPropertyDescriptor, Po = EC;
if (Po)
  try {
    Po([], "length");
  } catch {
    Po = null;
  }
var Kr = Po, Lo = Object.defineProperty || !1;
if (Lo)
  try {
    Lo({}, "a", { value: 1 });
  } catch {
    Lo = !1;
  }
var ii = Lo, Dl, ic;
function kC() {
  if (ic) return Dl;
  ic = 1;
  var a = typeof Symbol < "u" && Symbol, r = Yp();
  return Dl = function() {
    return typeof a != "function" || typeof Symbol != "function" || typeof a("foo") != "symbol" || typeof Symbol("bar") != "symbol" ? !1 : r();
  }, Dl;
}
var ev = typeof Reflect < "u" && Reflect.getPrototypeOf || null, OC = Zp, tv = OC.getPrototypeOf || null, Es = Function.prototype.call, ks = Function.prototype.apply, $C = typeof Reflect < "u" && Reflect && Reflect.apply, RC = $a, FC = ks, PC = Es, LC = $C, nv = LC || RC.call(PC, FC), IC = $a, NC = Ra, VC = Es, DC = nv, Os = function(r) {
  if (r.length < 1 || typeof r[0] != "function")
    throw new NC("a function is required");
  return DC(IC, VC, r);
}, zC = Os, lc = Kr, rv;
try {
  rv = /** @type {{ __proto__?: typeof Array.prototype }} */
  [].__proto__ === Array.prototype;
} catch (a) {
  if (!a || typeof a != "object" || !("code" in a) || a.code !== "ERR_PROTO_ACCESS")
    throw a;
}
var zl = !!rv && lc && lc(
  Object.prototype,
  /** @type {keyof typeof Object.prototype} */
  "__proto__"
), av = Object, sc = av.getPrototypeOf, MC = zl && typeof zl.get == "function" ? zC([zl.get]) : typeof sc == "function" ? (
  /** @type {import('./get')} */
  function(r) {
    return sc(r == null ? r : av(r));
  }
) : !1, uc = ev, fc = tv, dc = MC, $s = uc ? function(r) {
  return uc(r);
} : fc ? function(r) {
  if (!r || typeof r != "object" && typeof r != "function")
    throw new TypeError("getProto: not an object");
  return fc(r);
} : dc ? function(r) {
  return dc(r);
} : null, we, HC = Zp, UC = vC, WC = gC, qC = hC, GC = mC, Ur = Qp, Vr = Ra, jC = yC, KC = bC, JC = _C, XC = wC, YC = xC, ZC = SC, QC = BC, eT = TC, ov = Function, Ml = function(a) {
  try {
    return ov('"use strict"; return (' + a + ").constructor;")();
  } catch {
  }
}, wa = Kr, tT = ii, Hl = function() {
  throw new Vr();
}, nT = wa ? function() {
  try {
    return arguments.callee, Hl;
  } catch {
    try {
      return wa(arguments, "callee").get;
    } catch {
      return Hl;
    }
  }
}() : Hl, kr = kC()(), nt = $s, rT = tv, aT = ev, iv = ks, Fa = Es, $r = {}, oT = typeof Uint8Array > "u" || !nt ? we : nt(Uint8Array), ir = {
  __proto__: null,
  "%AggregateError%": typeof AggregateError > "u" ? we : AggregateError,
  "%Array%": Array,
  "%ArrayBuffer%": typeof ArrayBuffer > "u" ? we : ArrayBuffer,
  "%ArrayIteratorPrototype%": kr && nt ? nt([][Symbol.iterator]()) : we,
  "%AsyncFromSyncIteratorPrototype%": we,
  "%AsyncFunction%": $r,
  "%AsyncGenerator%": $r,
  "%AsyncGeneratorFunction%": $r,
  "%AsyncIteratorPrototype%": $r,
  "%Atomics%": typeof Atomics > "u" ? we : Atomics,
  "%BigInt%": typeof BigInt > "u" ? we : BigInt,
  "%BigInt64Array%": typeof BigInt64Array > "u" ? we : BigInt64Array,
  "%BigUint64Array%": typeof BigUint64Array > "u" ? we : BigUint64Array,
  "%Boolean%": Boolean,
  "%DataView%": typeof DataView > "u" ? we : DataView,
  "%Date%": Date,
  "%decodeURI%": decodeURI,
  "%decodeURIComponent%": decodeURIComponent,
  "%encodeURI%": encodeURI,
  "%encodeURIComponent%": encodeURIComponent,
  "%Error%": UC,
  "%eval%": eval,
  // eslint-disable-line no-eval
  "%EvalError%": WC,
  "%Float16Array%": typeof Float16Array > "u" ? we : Float16Array,
  "%Float32Array%": typeof Float32Array > "u" ? we : Float32Array,
  "%Float64Array%": typeof Float64Array > "u" ? we : Float64Array,
  "%FinalizationRegistry%": typeof FinalizationRegistry > "u" ? we : FinalizationRegistry,
  "%Function%": ov,
  "%GeneratorFunction%": $r,
  "%Int8Array%": typeof Int8Array > "u" ? we : Int8Array,
  "%Int16Array%": typeof Int16Array > "u" ? we : Int16Array,
  "%Int32Array%": typeof Int32Array > "u" ? we : Int32Array,
  "%isFinite%": isFinite,
  "%isNaN%": isNaN,
  "%IteratorPrototype%": kr && nt ? nt(nt([][Symbol.iterator]())) : we,
  "%JSON%": typeof JSON == "object" ? JSON : we,
  "%Map%": typeof Map > "u" ? we : Map,
  "%MapIteratorPrototype%": typeof Map > "u" || !kr || !nt ? we : nt((/* @__PURE__ */ new Map())[Symbol.iterator]()),
  "%Math%": Math,
  "%Number%": Number,
  "%Object%": HC,
  "%Object.getOwnPropertyDescriptor%": wa,
  "%parseFloat%": parseFloat,
  "%parseInt%": parseInt,
  "%Promise%": typeof Promise > "u" ? we : Promise,
  "%Proxy%": typeof Proxy > "u" ? we : Proxy,
  "%RangeError%": qC,
  "%ReferenceError%": GC,
  "%Reflect%": typeof Reflect > "u" ? we : Reflect,
  "%RegExp%": RegExp,
  "%Set%": typeof Set > "u" ? we : Set,
  "%SetIteratorPrototype%": typeof Set > "u" || !kr || !nt ? we : nt((/* @__PURE__ */ new Set())[Symbol.iterator]()),
  "%SharedArrayBuffer%": typeof SharedArrayBuffer > "u" ? we : SharedArrayBuffer,
  "%String%": String,
  "%StringIteratorPrototype%": kr && nt ? nt(""[Symbol.iterator]()) : we,
  "%Symbol%": kr ? Symbol : we,
  "%SyntaxError%": Ur,
  "%ThrowTypeError%": nT,
  "%TypedArray%": oT,
  "%TypeError%": Vr,
  "%Uint8Array%": typeof Uint8Array > "u" ? we : Uint8Array,
  "%Uint8ClampedArray%": typeof Uint8ClampedArray > "u" ? we : Uint8ClampedArray,
  "%Uint16Array%": typeof Uint16Array > "u" ? we : Uint16Array,
  "%Uint32Array%": typeof Uint32Array > "u" ? we : Uint32Array,
  "%URIError%": jC,
  "%WeakMap%": typeof WeakMap > "u" ? we : WeakMap,
  "%WeakRef%": typeof WeakRef > "u" ? we : WeakRef,
  "%WeakSet%": typeof WeakSet > "u" ? we : WeakSet,
  "%Function.prototype.call%": Fa,
  "%Function.prototype.apply%": iv,
  "%Object.defineProperty%": tT,
  "%Object.getPrototypeOf%": rT,
  "%Math.abs%": KC,
  "%Math.floor%": JC,
  "%Math.max%": XC,
  "%Math.min%": YC,
  "%Math.pow%": ZC,
  "%Math.round%": QC,
  "%Math.sign%": eT,
  "%Reflect.getPrototypeOf%": aT
};
if (nt)
  try {
    null.error;
  } catch (a) {
    var iT = nt(nt(a));
    ir["%Error.prototype%"] = iT;
  }
var lT = function a(r) {
  var n;
  if (r === "%AsyncFunction%")
    n = Ml("async function () {}");
  else if (r === "%GeneratorFunction%")
    n = Ml("function* () {}");
  else if (r === "%AsyncGeneratorFunction%")
    n = Ml("async function* () {}");
  else if (r === "%AsyncGenerator%") {
    var o = a("%AsyncGeneratorFunction%");
    o && (n = o.prototype);
  } else if (r === "%AsyncIteratorPrototype%") {
    var l = a("%AsyncGenerator%");
    l && nt && (n = nt(l.prototype));
  }
  return ir[r] = n, n;
}, cc = {
  __proto__: null,
  "%ArrayBufferPrototype%": ["ArrayBuffer", "prototype"],
  "%ArrayPrototype%": ["Array", "prototype"],
  "%ArrayProto_entries%": ["Array", "prototype", "entries"],
  "%ArrayProto_forEach%": ["Array", "prototype", "forEach"],
  "%ArrayProto_keys%": ["Array", "prototype", "keys"],
  "%ArrayProto_values%": ["Array", "prototype", "values"],
  "%AsyncFunctionPrototype%": ["AsyncFunction", "prototype"],
  "%AsyncGenerator%": ["AsyncGeneratorFunction", "prototype"],
  "%AsyncGeneratorPrototype%": ["AsyncGeneratorFunction", "prototype", "prototype"],
  "%BooleanPrototype%": ["Boolean", "prototype"],
  "%DataViewPrototype%": ["DataView", "prototype"],
  "%DatePrototype%": ["Date", "prototype"],
  "%ErrorPrototype%": ["Error", "prototype"],
  "%EvalErrorPrototype%": ["EvalError", "prototype"],
  "%Float32ArrayPrototype%": ["Float32Array", "prototype"],
  "%Float64ArrayPrototype%": ["Float64Array", "prototype"],
  "%FunctionPrototype%": ["Function", "prototype"],
  "%Generator%": ["GeneratorFunction", "prototype"],
  "%GeneratorPrototype%": ["GeneratorFunction", "prototype", "prototype"],
  "%Int8ArrayPrototype%": ["Int8Array", "prototype"],
  "%Int16ArrayPrototype%": ["Int16Array", "prototype"],
  "%Int32ArrayPrototype%": ["Int32Array", "prototype"],
  "%JSONParse%": ["JSON", "parse"],
  "%JSONStringify%": ["JSON", "stringify"],
  "%MapPrototype%": ["Map", "prototype"],
  "%NumberPrototype%": ["Number", "prototype"],
  "%ObjectPrototype%": ["Object", "prototype"],
  "%ObjProto_toString%": ["Object", "prototype", "toString"],
  "%ObjProto_valueOf%": ["Object", "prototype", "valueOf"],
  "%PromisePrototype%": ["Promise", "prototype"],
  "%PromiseProto_then%": ["Promise", "prototype", "then"],
  "%Promise_all%": ["Promise", "all"],
  "%Promise_reject%": ["Promise", "reject"],
  "%Promise_resolve%": ["Promise", "resolve"],
  "%RangeErrorPrototype%": ["RangeError", "prototype"],
  "%ReferenceErrorPrototype%": ["ReferenceError", "prototype"],
  "%RegExpPrototype%": ["RegExp", "prototype"],
  "%SetPrototype%": ["Set", "prototype"],
  "%SharedArrayBufferPrototype%": ["SharedArrayBuffer", "prototype"],
  "%StringPrototype%": ["String", "prototype"],
  "%SymbolPrototype%": ["Symbol", "prototype"],
  "%SyntaxErrorPrototype%": ["SyntaxError", "prototype"],
  "%TypedArrayPrototype%": ["TypedArray", "prototype"],
  "%TypeErrorPrototype%": ["TypeError", "prototype"],
  "%Uint8ArrayPrototype%": ["Uint8Array", "prototype"],
  "%Uint8ClampedArrayPrototype%": ["Uint8ClampedArray", "prototype"],
  "%Uint16ArrayPrototype%": ["Uint16Array", "prototype"],
  "%Uint32ArrayPrototype%": ["Uint32Array", "prototype"],
  "%URIErrorPrototype%": ["URIError", "prototype"],
  "%WeakMapPrototype%": ["WeakMap", "prototype"],
  "%WeakSetPrototype%": ["WeakSet", "prototype"]
}, Pa = $a, Wo = Xp(), sT = Pa.call(Fa, Array.prototype.concat), uT = Pa.call(iv, Array.prototype.splice), pc = Pa.call(Fa, String.prototype.replace), qo = Pa.call(Fa, String.prototype.slice), fT = Pa.call(Fa, RegExp.prototype.exec), dT = /[^%.[\]]+|\[(?:(-?\d+(?:\.\d+)?)|(["'])((?:(?!\2)[^\\]|\\.)*?)\2)\]|(?=(?:\.|\[\])(?:\.|\[\]|%$))/g, cT = /\\(\\)?/g, pT = function(r) {
  var n = qo(r, 0, 1), o = qo(r, -1);
  if (n === "%" && o !== "%")
    throw new Ur("invalid intrinsic syntax, expected closing `%`");
  if (o === "%" && n !== "%")
    throw new Ur("invalid intrinsic syntax, expected opening `%`");
  var l = [];
  return pc(r, dT, function(s, f, d, p) {
    l[l.length] = d ? pc(p, cT, "$1") : f || s;
  }), l;
}, vT = function(r, n) {
  var o = r, l;
  if (Wo(cc, o) && (l = cc[o], o = "%" + l[0] + "%"), Wo(ir, o)) {
    var s = ir[o];
    if (s === $r && (s = lT(o)), typeof s > "u" && !n)
      throw new Vr("intrinsic " + r + " exists, but is not available. Please file an issue!");
    return {
      alias: l,
      name: o,
      value: s
    };
  }
  throw new Ur("intrinsic " + r + " does not exist!");
}, lv = function(r, n) {
  if (typeof r != "string" || r.length === 0)
    throw new Vr("intrinsic name must be a non-empty string");
  if (arguments.length > 1 && typeof n != "boolean")
    throw new Vr('"allowMissing" argument must be a boolean');
  if (fT(/^%?[^%]*%?$/, r) === null)
    throw new Ur("`%` may not be present anywhere but at the beginning and end of the intrinsic name");
  var o = pT(r), l = o.length > 0 ? o[0] : "", s = vT("%" + l + "%", n), f = s.name, d = s.value, p = !1, g = s.alias;
  g && (l = g[0], uT(o, sT([0, 1], g)));
  for (var m = 1, w = !0; m < o.length; m += 1) {
    var S = o[m], b = qo(S, 0, 1), y = qo(S, -1);
    if ((b === '"' || b === "'" || b === "`" || y === '"' || y === "'" || y === "`") && b !== y)
      throw new Ur("property names with quotes must have matching quotes");
    if ((S === "constructor" || !w) && (p = !0), l += "." + S, f = "%" + l + "%", Wo(ir, f))
      d = ir[f];
    else if (d != null) {
      if (!(S in d)) {
        if (!n)
          throw new Vr("base intrinsic for " + r + " exists, but the property is not available.");
        return;
      }
      if (wa && m + 1 >= o.length) {
        var x = wa(d, S);
        w = !!x, w && "get" in x && !("originalValue" in x.get) ? d = x.get : d = d[S];
      } else
        w = Wo(d, S), d = d[S];
      w && !p && (ir[f] = d);
    }
  }
  return d;
}, sv = lv, uv = Os, gT = uv([sv("%String.prototype.indexOf%")]), li = function(r, n) {
  var o = (
    /** @type {(this: unknown, ...args: unknown[]) => unknown} */
    sv(r, !!n)
  );
  return typeof o == "function" && gT(r, ".prototype.") > -1 ? uv(
    /** @type {const} */
    [o]
  ) : o;
}, vc = li, hT = Ts()(), mT = Xp(), yT = Kr, as;
if (hT) {
  var bT = vc("RegExp.prototype.exec"), gc = {}, Ul = function() {
    throw gc;
  }, hc = {
    toString: Ul,
    valueOf: Ul
  };
  typeof Symbol.toPrimitive == "symbol" && (hc[Symbol.toPrimitive] = Ul), as = function(r) {
    if (!r || typeof r != "object")
      return !1;
    var n = (
      /** @type {NonNullable<typeof gOPD>} */
      yT(
        /** @type {{ lastIndex?: unknown }} */
        r,
        "lastIndex"
      )
    ), o = n && mT(n, "value");
    if (!o)
      return !1;
    try {
      bT(
        r,
        /** @type {string} */
        /** @type {unknown} */
        hc
      );
    } catch (l) {
      return l === gc;
    }
  };
} else {
  var _T = vc("Object.prototype.toString"), wT = "[object RegExp]";
  as = function(r) {
    return !r || typeof r != "object" && typeof r != "function" ? !1 : _T(r) === wT;
  };
}
var xT = as, Wl, mc;
function ST() {
  if (mc) return Wl;
  mc = 1;
  var a = li, r = xT, n = a("RegExp.prototype.exec"), o = Ra;
  return Wl = function(s) {
    if (!r(s))
      throw new o("`regex` must be a RegExp");
    return function(d) {
      return n(s, d) !== null;
    };
  }, Wl;
}
var fv = li, BT = ST(), AT = BT(/^\s*(?:function)?\*/), dv = Ts()(), ql = $s, CT = fv("Object.prototype.toString"), TT = fv("Function.prototype.toString"), ET = function() {
  if (!dv)
    return !1;
  try {
    return Function("return function*() {}")();
  } catch {
  }
}, Gl, mk = function(r) {
  if (typeof r != "function")
    return !1;
  if (AT(TT(r)))
    return !0;
  if (!dv) {
    var n = CT(r);
    return n === "[object GeneratorFunction]";
  }
  if (!ql)
    return !1;
  if (typeof Gl > "u") {
    var o = ET();
    Gl = o ? (
      /** @type {GeneratorFunctionConstructor} */
      ql(o)
    ) : !1;
  }
  return ql(r) === Gl;
}, cv = Function.prototype.toString, Lr = typeof Reflect == "object" && Reflect !== null && Reflect.apply, os, Io;
if (typeof Lr == "function" && typeof Object.defineProperty == "function")
  try {
    os = Object.defineProperty({}, "length", {
      get: function() {
        throw Io;
      }
    }), Io = {}, Lr(function() {
      throw 42;
    }, null, os);
  } catch (a) {
    a !== Io && (Lr = null);
  }
else
  Lr = null;
var kT = /^\s*class\b/, is = function(r) {
  try {
    var n = cv.call(r);
    return kT.test(n);
  } catch {
    return !1;
  }
}, jl = function(r) {
  try {
    return is(r) ? !1 : (cv.call(r), !0);
  } catch {
    return !1;
  }
}, No = Object.prototype.toString, OT = "[object Object]", $T = "[object Function]", RT = "[object GeneratorFunction]", FT = "[object HTMLAllCollection]", PT = "[object HTML document.all class]", LT = "[object HTMLCollection]", IT = typeof Symbol == "function" && !!Symbol.toStringTag, NT = !(0 in [,]), ls = function() {
  return !1;
};
if (typeof document == "object") {
  var VT = document.all;
  No.call(VT) === No.call(document.all) && (ls = function(r) {
    if ((NT || !r) && (typeof r > "u" || typeof r == "object"))
      try {
        var n = No.call(r);
        return (n === FT || n === PT || n === LT || n === OT) && r("") == null;
      } catch {
      }
    return !1;
  });
}
var DT = Lr ? function(r) {
  if (ls(r))
    return !0;
  if (!r || typeof r != "function" && typeof r != "object")
    return !1;
  try {
    Lr(r, null, os);
  } catch (n) {
    if (n !== Io)
      return !1;
  }
  return !is(r) && jl(r);
} : function(r) {
  if (ls(r))
    return !0;
  if (!r || typeof r != "function" && typeof r != "object")
    return !1;
  if (IT)
    return jl(r);
  if (is(r))
    return !1;
  var n = No.call(r);
  return n !== $T && n !== RT && !/^\[object HTML/.test(n) ? !1 : jl(r);
}, zT = DT, MT = Object.prototype.toString, pv = Object.prototype.hasOwnProperty, HT = function(r, n, o) {
  for (var l = 0, s = r.length; l < s; l++)
    pv.call(r, l) && (o == null ? n(r[l], l, r) : n.call(o, r[l], l, r));
}, UT = function(r, n, o) {
  for (var l = 0, s = r.length; l < s; l++)
    o == null ? n(r.charAt(l), l, r) : n.call(o, r.charAt(l), l, r);
}, WT = function(r, n, o) {
  for (var l in r)
    pv.call(r, l) && (o == null ? n(r[l], l, r) : n.call(o, r[l], l, r));
};
function qT(a) {
  return MT.call(a) === "[object Array]";
}
var GT = function(r, n, o) {
  if (!zT(n))
    throw new TypeError("iterator must be a function");
  var l;
  arguments.length >= 3 && (l = o), qT(r) ? HT(r, n, l) : typeof r == "string" ? UT(r, n, l) : WT(r, n, l);
}, jT = [
  "Float16Array",
  "Float32Array",
  "Float64Array",
  "Int8Array",
  "Int16Array",
  "Int32Array",
  "Uint8Array",
  "Uint8ClampedArray",
  "Uint16Array",
  "Uint32Array",
  "BigInt64Array",
  "BigUint64Array"
], Kl = jT, KT = typeof globalThis > "u" ? nr : globalThis, JT = function() {
  for (var r = [], n = 0; n < Kl.length; n++)
    typeof KT[Kl[n]] == "function" && (r[r.length] = Kl[n]);
  return r;
}, vv = { exports: {} }, yc = ii, XT = Qp, Or = Ra, bc = Kr, YT = function(r, n, o) {
  if (!r || typeof r != "object" && typeof r != "function")
    throw new Or("`obj` must be an object or a function`");
  if (typeof n != "string" && typeof n != "symbol")
    throw new Or("`property` must be a string or a symbol`");
  if (arguments.length > 3 && typeof arguments[3] != "boolean" && arguments[3] !== null)
    throw new Or("`nonEnumerable`, if provided, must be a boolean or null");
  if (arguments.length > 4 && typeof arguments[4] != "boolean" && arguments[4] !== null)
    throw new Or("`nonWritable`, if provided, must be a boolean or null");
  if (arguments.length > 5 && typeof arguments[5] != "boolean" && arguments[5] !== null)
    throw new Or("`nonConfigurable`, if provided, must be a boolean or null");
  if (arguments.length > 6 && typeof arguments[6] != "boolean")
    throw new Or("`loose`, if provided, must be a boolean");
  var l = arguments.length > 3 ? arguments[3] : null, s = arguments.length > 4 ? arguments[4] : null, f = arguments.length > 5 ? arguments[5] : null, d = arguments.length > 6 ? arguments[6] : !1, p = !!bc && bc(r, n);
  if (yc)
    yc(r, n, {
      configurable: f === null && p ? p.configurable : !f,
      enumerable: l === null && p ? p.enumerable : !l,
      value: o,
      writable: s === null && p ? p.writable : !s
    });
  else if (d || !l && !s && !f)
    r[n] = o;
  else
    throw new XT("This environment does not support defining a property as non-configurable, non-writable, or non-enumerable.");
}, ss = ii, gv = function() {
  return !!ss;
};
gv.hasArrayLengthDefineBug = function() {
  if (!ss)
    return null;
  try {
    return ss([], "length", { value: 1 }).length !== 1;
  } catch {
    return !0;
  }
};
var ZT = gv, QT = lv, _c = YT, eE = ZT(), wc = Kr, xc = Ra, tE = QT("%Math.floor%"), nE = function(r, n) {
  if (typeof r != "function")
    throw new xc("`fn` is not a function");
  if (typeof n != "number" || n < 0 || n > 4294967295 || tE(n) !== n)
    throw new xc("`length` must be a positive 32-bit integer");
  var o = arguments.length > 2 && !!arguments[2], l = !0, s = !0;
  if ("length" in r && wc) {
    var f = wc(r, "length");
    f && !f.configurable && (l = !1), f && !f.writable && (s = !1);
  }
  return (l || s || !o) && (eE ? _c(
    /** @type {Parameters<define>[0]} */
    r,
    "length",
    n,
    !0,
    !0
  ) : _c(
    /** @type {Parameters<define>[0]} */
    r,
    "length",
    n
  )), r;
}, rE = $a, aE = ks, oE = nv, iE = function() {
  return oE(rE, aE, arguments);
};
(function(a) {
  var r = nE, n = ii, o = Os, l = iE;
  a.exports = function(f) {
    var d = o(arguments), p = f.length - (arguments.length - 1);
    return r(
      d,
      1 + (p > 0 ? p : 0),
      !0
    );
  }, n ? n(a.exports, "apply", { value: l }) : a.exports.apply = l;
})(vv);
var lE = vv.exports, Go = GT, sE = JT, Sc = lE, Rs = li, Vo = Kr, Oo = $s, uE = Rs("Object.prototype.toString"), hv = Ts()(), Bc = typeof globalThis > "u" ? nr : globalThis, us = sE(), Fs = Rs("String.prototype.slice"), fE = Rs("Array.prototype.indexOf", !0) || function(r, n) {
  for (var o = 0; o < r.length; o += 1)
    if (r[o] === n)
      return o;
  return -1;
}, jo = { __proto__: null };
hv && Vo && Oo ? Go(us, function(a) {
  var r = new Bc[a]();
  if (Symbol.toStringTag in r && Oo) {
    var n = Oo(r), o = Vo(n, Symbol.toStringTag);
    if (!o && n) {
      var l = Oo(n);
      o = Vo(l, Symbol.toStringTag);
    }
    jo["$" + a] = Sc(o.get);
  }
}) : Go(us, function(a) {
  var r = new Bc[a](), n = r.slice || r.set;
  n && (jo[
    /** @type {`$${import('.').TypedArrayName}`} */
    "$" + a
  ] = /** @type {import('./types').BoundSlice | import('./types').BoundSet} */
  // @ts-expect-error TODO FIXME
  Sc(n));
});
var dE = function(r) {
  var n = !1;
  return Go(
    /** @type {Record<`\$${import('.').TypedArrayName}`, Getter>} */
    jo,
    /** @type {(getter: Getter, name: `\$${import('.').TypedArrayName}`) => void} */
    function(o, l) {
      if (!n)
        try {
          "$" + o(r) === l && (n = /** @type {import('.').TypedArrayName} */
          Fs(l, 1));
        } catch {
        }
    }
  ), n;
}, cE = function(r) {
  var n = !1;
  return Go(
    /** @type {Record<`\$${import('.').TypedArrayName}`, Getter>} */
    jo,
    /** @type {(getter: Getter, name: `\$${import('.').TypedArrayName}`) => void} */
    function(o, l) {
      if (!n)
        try {
          o(r), n = /** @type {import('.').TypedArrayName} */
          Fs(l, 1);
        } catch {
        }
    }
  ), n;
}, pE = function(r) {
  if (!r || typeof r != "object")
    return !1;
  if (!hv) {
    var n = Fs(uE(r), 8, -1);
    return fE(us, n) > -1 ? n : n !== "Object" ? !1 : cE(r);
  }
  return Vo ? dE(r) : null;
}, vE = pE, yk = function(r) {
  return !!vE(r);
};
const gE = {
  name: "VsLoadingSpinner",
  status: "prototype",
  release: "0.1.0",
  props: {
    /*
     * Variant of loading spinner theme
     * `dark|light`
     */
    variant: {
      type: String,
      default: "light",
      validator: (a) => a.match(
        /(dark|light)/
      )
    }
  },
  computed: {
    spinnerClasses() {
      return this.variant ? `vs-loading-spinner--${this.variant}` : "";
    }
  }
};
function hE(a, r, n, o, l, s) {
  return P(), j("div", {
    class: Q(["vs-loading-spinner", s.spinnerClasses]),
    "data-test": "vs-loading-spinner"
  }, r[0] || (r[0] = [
    uw('<div class="vs-loading-spinner__dot"></div><div class="vs-loading-spinner__dot"></div><div class="vs-loading-spinner__dot"></div><div class="vs-loading-spinner__dot"></div><div class="vs-loading-spinner__dot"></div><div class="vs-loading-spinner__dot"></div><div class="vs-loading-spinner__dot"></div>', 7)
  ]), 2);
}
const bk = /* @__PURE__ */ rn(gE, [["render", hE]]);
export {
  FE as $,
  Ts as A,
  li as B,
  mk as C,
  yk as D,
  OE as E,
  pE as F,
  iB as G,
  WE as H,
  kE as I,
  _E as J,
  zE as K,
  EE as L,
  ME as M,
  HE as N,
  Ux as O,
  bk as P,
  $E as Q,
  IE as R,
  LE as S,
  TE as T,
  RE as U,
  oB as V,
  NE as W,
  AE as X,
  rx as Y,
  bE as Z,
  rn as _,
  qE as a,
  PE as a0,
  V1 as a1,
  DE as a2,
  VE as a3,
  JE as a4,
  YT as a5,
  ZT as a6,
  Ra as a7,
  AC as a8,
  _C as a9,
  bC as aa,
  lE as ab,
  lv as ac,
  JT as ad,
  SC as ae,
  hC as af,
  Qp as ag,
  Yp as ah,
  kC as ai,
  ST as aj,
  DT as ak,
  xC as al,
  wC as am,
  ii as an,
  $s as ao,
  xT as ap,
  Zp as aq,
  GT as ar,
  Kr as as,
  jT as at,
  CE as au,
  gx as b,
  xs as c,
  nB as d,
  yB as e,
  KE as f,
  jE as g,
  GE as h,
  wE as i,
  ux as j,
  UE as k,
  lB as l,
  XE as m,
  yx as n,
  Ye as o,
  xE as p,
  NS as q,
  SE as r,
  Sp as s,
  Nx as t,
  gk as u,
  Tx as v,
  BE as w,
  nr as x,
  hk as y,
  Xp as z
};
