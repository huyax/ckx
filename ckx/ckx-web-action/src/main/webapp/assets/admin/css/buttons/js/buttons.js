(function (e, c, a, h) {
    var d = "menuButton";
    var g = ".button-dropdown";
    var f = {propertyName: "value"};

    function b(j, i) {
        this.options = e.extend({}, f, i);
        this._defaults = f;
        this._name = d;
        this.$element = e(j);
        this.init()
    }

    b.prototype = {
        constructor: b, init: function () {
            this.toggle()
        }, toggle: function (j, i) {
            if (this.$element.data("dropdown") === "show") {
                this.hideMenu()
            } else {
                this.showMenu()
            }
        }, showMenu: function () {
            this.$element.data("dropdown", "show");
            this.$element.find("ul").show();
            if (this.$overlay) {
                this.$overlay.show()
            } else {
                this.$overlay = e('<div class="button-overlay"></div>');
                this.$element.append(this.$overlay)
            }
        }, hideMenu: function () {
            this.$element.data("dropdown", "hide");
            this.$element.find("ul").hide();
            this.$overlay.hide()
        }
    };
    e.fn[d] = function (i) {
        return this.each(function () {
            if (e.data(this, "plugin_" + d)) {
                e.data(this, "plugin_" + d).toggle()
            } else {
                e.data(this, "plugin_" + d, new b(this, i))
            }
        })
    };
    e(a).on("click", "[data-buttons=dropdown]", function (j) {
        var i = e(j.currentTarget);
        i.menuButton()
    });
    e(a).on("click", "[data-buttons=dropdown] > a", function (i) {
        i.preventDefault()
    })
})(jQuery, window, document);