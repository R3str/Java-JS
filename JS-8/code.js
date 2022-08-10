!function() {
    function e(t, n, o) {
        function i(s, c) {
            if (!n[s]) {
                var u = n[s] = {
                    exports: {}
                };
                t[s][0].call(u.exports, function(e) {
                    return i(t[s][1][e] || e)
                }, u, u.exports, e, t, n, o)
            }
            return n[s].exports
        }
        for (var r = "function" == typeof require && require, s = 0; s < o.length; s++)
            i(o[s]);
        return i
    }
    return e
}
()({
    1: [function(e, t, n) {
        function o(e) {
            m = e.touches ? e.touches[0].pageX : e.pageX,
            p = !0,
            c.classList.remove("animation")
        }
        function i() {
            c.classList.add("animation"),
            p = !1,
            g = h,
            h >= y / 2 ? (g = y,
            s(y)) : (g = 0,
            s(0))
        }
        function r(e) {
            p && (f = e.touches ? e.touches[0].pageX : e.pageX,
            (h = g + (f - m)) <= 300 && s(h))
        }
        function s(e) {
            e <= 0 ? (c.style.cssText = "visibility: hidden",
            a.style.cssText = "opacity: 0",
            l.style.cssText = "transform: translateX(0)") : (c.style.cssText = "visibility: visible",
            a.style.cssText = "opacity: " + 1 / y * e,
            l.style.cssText = "transform: translateX(" + e + "px)")
        }
        var c = document.querySelector(".mobile-menu")
          , a = document.querySelector(".overlay")
          , l = document.querySelector(".mobile-menu .menu")
          , u = document.querySelectorAll("[data-menu-open]")
          , d = document.querySelectorAll("[data-menu-close]")
          , m = 0
          , f = 0
          , p = !1
          , h = 0
          , g = 0
          , y = 300;
        window.addEventListener("mousedown", o),
        window.addEventListener("touchstart", o),
        window.addEventListener("mouseup", i),
        window.addEventListener("touchend", i),
        window.addEventListener("mousemove", r),
        window.addEventListener("touchmove", r),
        u.forEach(function(e) {
            e.addEventListener("click", function() {
                g = y,
                s(y)
            })
        }),
        d.forEach(function(e) {
            e.addEventListener("click", function() {
                g = 0,
                s(0)
            })
        })
    }
    , {}],
    2: [function(e, t, n) {
        "use strict";
        t.exports = {}
    }
    , {}],
    3: [function(e, t, n) {
        "use strict";
        e("./functions");
        document.addEventListener("DOMContentLoaded", function() {
            if (document.querySelectorAll("[data-popup-text]").forEach(function(e) {
                e.addEventListener("click", function() {
                    var e = this.getAttribute("data-href");
                    if (-1 == e.indexOf("http"))
                        var t = window.location.hostname + "/" + e;
                    else
                        var t = e;
                    navigator.clipboard.writeText(t).then(function() {
                        this.classList.add("active-popup"),
                        setTimeout(function() {
                            this.classList.remove("active-popup")
                        }
                        .bind(this), 1e3)
                    }
                    .bind(this))
                })
            }),
            document.querySelector(".mobile-menu") && e("./components/mobile-menu")) {
            }
        })
    }
    , {
        "./components/mobile-menu": 1,
        "./functions": 2
    }]
}, {}, [3]);