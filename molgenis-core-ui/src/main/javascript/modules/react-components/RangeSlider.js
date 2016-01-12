define(function(require, exports, module) {
	/**
	 * @module RangeSlider
	 */
	"use strict";

	var React = require('react');
	var _ = require('underscore');

	var DeepPureRenderMixin = require('./mixin/DeepPureRenderMixin');

	var JQRangeSlider = require('./wrapper/JQRangeSlider');

	/**
	 * Range slider control for number types
	 * 
	 * @memberOf RangeSlider
	 */
	var RangeSlider = React.createClass({ // FIXME support readOnly
		mixins : [ DeepPureRenderMixin ],
		displayName : 'RangeSlider',
		propTypes : {
			id : React.PropTypes.string,
			range : React.PropTypes.shape({
				min : React.PropTypes.number.isRequired,
				max : React.PropTypes.number.isRequired
			}).isRequired,
			value : React.PropTypes.arrayOf(React.PropTypes.number),
			step : React.PropTypes.string,
			disabled : React.PropTypes.bool,
			onValueChange : React.PropTypes.func.isRequired
		},
		render : function() {
			var range = this.props.range;
			var value = this.props.value;

			var fromValue = value && value[0] ? value[0] : range.min;
			var toValue = value && value[1] ? value[1] : range.max;
			var options = {
				symmetricPositionning : true,
				bounds : {
					min : range.min,
					max : range.max
				},
				defaultValues : {
					min : fromValue,
					max : toValue
				},
				step : this.props.step,
				type : 'number'
			};

			return JQRangeSlider({
				id : this.props.id,
				options : options,
				disabled : this.props.disabled,
				value : [ fromValue, toValue ],
				onChange : this._handleChange
			});
		},
		_handleChange : function(value) {
			this.props.onValueChange({
				value : value
			});
		}
	});

	module.exports = React.createFactory(RangeSlider)
});