define(function(require, exports, module) {
	/**
	 * @module AttributeLoaderMixin
	 */
	"use strict";

	var api = require('../../RestClientV1');
	var _ = require('underscore');

	/**
	 * @memberOf AttributeLoaderMixin
	 */
	var AttributeLoaderMixin = {
		componentDidMount : function() {
			this._initAttr(this.props.attr);
		},
		componentWillReceiveProps : function(nextProps) {
			if (!_.isEqual(this.props.attr, nextProps.attr)) {
				this._initAttr(nextProps.attr);
			}
		},
		_isLoaded : function(attr) {
			if (attr.name !== undefined && (attr.refEntity === undefined || attr.refEntity.name !== undefined)) {
				if (attr.attributes.length > 0) {
					for (var i = 0; i < attr.attributes.length; i++) {
						if (attr.attributes[i].name == undefined) {
							return false;
						}
					}
				}
				return true;
			}
		},
		_initAttr : function(attr) {
			// fetch attribute if not exists
			if (typeof attr === 'object') {
				if (!this._isLoaded(attr)) {
					this._loadAttr(attr.href);
				} else {
					this._setAttr(attr);
				}
			} else if (typeof attr === 'string') {
				this._loadAttr(attr);
			}
		},
		_loadAttr : function(href) {
			api.getAsync(href, {
				expand : [ 'refEntity', 'attributes' ]
			}).done(function(attr) {
				if (this.isMounted()) {
					this._setAttr(attr);
				}
			}.bind(this));
		},
		_setAttr : function(attr) {
			if (this._onAttrInit) {
				this.setState({
					attr : attr
				}, this._onAttrInit);
			} else {
				this.setState({
					attr : attr
				});
			}
		}
	};

	module.exports = AttributeLoaderMixin;
});