(function ($, rf) {

    rf.ui = rf.ui || {};

    rf.ui.CollapsibleSubTable = function (id, f, options) {
        this.id = id;
        this.options = $.extend(this.options, options || {});
        this.stateInput = options.stateInput;
        this.optionsInput = options.optionsInput;
        this.expandMode = options.expandMode || rf.ui.CollapsibleSubTable.MODE_CLNT;
        this.eventOptions = options.eventOptions;
        this.formId = f;
        this.isNested = options.isNested;

        if (!this.isNested) {
            var self = this;
            var tbody = $(document.getElementById(this.id)).parent();
            tbody.find(".rf-dt-c-srt").each(function () {
                $(this).bind("click", {sortHandle: this}, $.proxy(self.sortHandler, self));
            });
            tbody.find(".rf-dt-flt-i").each(function () {
                $(this).bind("blur", {filterHandle: this}, $.proxy(self.filterHandler, self));
            });

        }

        this.attachToDom();
    };

    $.extend(rf.ui.CollapsibleSubTable, {
        MODE_AJAX: "ajax",
        MODE_SRV: "server",
        MODE_CLNT: "client",
        collapse: 0,
        expand: 1,
        SORTING: "rich:sorting",
        FILTERING: "rich:filtering"
    });

    rf.BaseComponent.extend(rf.ui.CollapsibleSubTable);
    var $super = rf.ui.CollapsibleSubTable.$super;

    $.extend(rf.ui.CollapsibleSubTable.prototype, (function () {

        var invoke = function (event, attributes) {
            rf.ajax(this.id, event, {"parameters": attributes});
        };

        var createParameters = function (type, id, arg1, arg2) {
            var parameters = {};
            var key = this.id + type;
            parameters[key] = (id + ":" + (arg1 || "") + ":" + arg2);

            var eventOptions = this.options.ajaxEventOption;
            for (key in eventOptions) {
                if (!parameters[key]) {
                    parameters[key] = eventOptions[key];
                }
            }
            return parameters;
        };

        var element = function () {
            if (!this.isNested) {
                //use parent tbody as parent dom elem
                return $(document.getElementById(this.id)).parent();
            } else {
                var regex = new RegExp("^" + this.id + "\\:\\d+\\:b$");
                return $(document.getElementById(this.id)).parent().find("tr").filter(function () {
                    return this.id.match(regex);
                });
            }
        };

        var stateInputElem = function () {
            return $(document.getElementById(this.stateInput));
        };

        var optionsInputElem = function () {
            return $(document.getElementById(this.optionsInput));
        };

        var ajax = function (e, options) {
            this.__switchState();
            rf.ajax(this.id, e, options);
        };

        var server = function (options) {
            this.__switchState();
            $(document.getElementById(this.formId)).submit();
        };

        var client = function (options) {
            if (this.isExpanded()) {
                this.collapse(options);
            } else {
                this.expand(options);
            }
        };


        return {

            name: "CollapsibleSubTable",

            sort: function (columnId, direction, isClear) {
                invoke.call(this, null, createParameters.call(this, rf.ui.CollapsibleSubTable.SORTING, columnId, direction, isClear));
            },

            clearSorting: function () {
                this.sort("", "", true);
            },

            sortHandler: function (event) {
                var sortHandle = $(event.data.sortHandle);
                var button = sortHandle.find('.rf-dt-srt-btn');
                var columnId = button.data('columnid');
                var sortOrder = button.hasClass('rf-dt-srt-asc') ? 'descending' : 'ascending';
                this.sort(columnId, sortOrder, false);
            },

            filter: function (columnId, filterValue, isClear) {
                invoke.call(this, null, createParameters.call(this, rf.ui.CollapsibleSubTable.FILTERING, columnId, filterValue, isClear));
            },

            clearFiltering: function () {
                this.filter("", "", true);
            },

            filterHandler: function (event) {
                var filterHandle = $(event.data.filterHandle);
                var columnId = filterHandle.data('columnid');
                var filterValue = filterHandle.val();
                this.filter(columnId, filterValue, false);
            },

            switchState: function (e, options) {
                if (this.expandMode == rf.ui.CollapsibleSubTable.MODE_AJAX) {
                    ajax.call(this, e, this.eventOptions, options);
                } else if (this.expandMode == rf.ui.CollapsibleSubTable.MODE_SRV) {
                    server.call(this, options);
                } else if (this.expandMode == rf.ui.CollapsibleSubTable.MODE_CLNT) {
                    client.call(this, options);
                }
            },

            collapse: function (options) {


                if (this.isNested) {
                    var expandedTogglerRegex = new RegExp("^" + this.id + "\\:\\d+\\:\\w+\\:expanded$");
                    var collapseTogglerRegex = new RegExp("^" + this.id + "\\:\\d+\\:\\w+\\:collapsed$");
                    var subtableRegex = new RegExp("^" + this.id + "\\:\\d+\\:\\w+$");
                    $(document.getElementById(this.id)).parent().find("tr[style='display: none;']").filter(function () {
                        return this.id.match(subtableRegex);
                    }).each(function () {
                        if (this.rf) {
                            if (this.rf.component.isExpanded) {
                                $(document.getElementById(this.id)).parent().find(".rf-csttg-exp").filter(function () {
                                    return this.id.match(expandedTogglerRegex);
                                }).each(function () {
                                    $(this).hide();
                                });
                                $(document.getElementById(this.id)).parent().find(".rf-csttg-colps").filter(function () {
                                    return this.id.match(collapseTogglerRegex);
                                }).each(function () {
                                    $(this).show();
                                });
                                this.rf.component.collapse();
                            }
                        }
                    });
                }


                this.setState(rf.ui.CollapsibleSubTable.collapse);
                element.call(this).hide();
            },

            expand: function (options) {
                this.setState(rf.ui.CollapsibleSubTable.expand);
                element.call(this).show();
            },

            isExpanded: function () {
                return (parseInt(this.getState()) == rf.ui.CollapsibleSubTable.expand);
            },

            __switchState: function (options) {
                var state = this.isExpanded() ? rf.ui.CollapsibleSubTable.collapse : rf.ui.CollapsibleSubTable.expand;
                this.setState(state);
            },

            getState: function () {
                return stateInputElem.call(this).val();
            },

            setState: function (state) {
                stateInputElem.call(this).val(state)
            },

            setOption: function (option) {
                optionsInputElem.call(this).val(option);
            },

            getMode: function () {
                return this.expandMode;
            },
            destroy: function () {
                $super.destroy.call(this);
            }
        };

    })());

})(RichFaces.jQuery, window.RichFaces);