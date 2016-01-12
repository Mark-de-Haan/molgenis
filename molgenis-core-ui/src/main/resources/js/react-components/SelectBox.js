define(function(require, exports, module) {
	/**
	 * @module SelectBox
	 */
	"use strict";

	var React = require('react');
	var _ = require('underscore');

	var DeepPureRenderMixin = require('./mixin/DeepPureRenderMixin');

	var div = React.DOM.div;
	var select = React.DOM.select;
	var option = React.DOM.option;

	/**
	 * @memberOf SelectBox
	 */
	var SelectBox = React.createClass({
		mixins : [ DeepPureRenderMixin ],
		displayName : 'SelectBox',
		propTypes : {
			options : React.PropTypes.arrayOf(React.PropTypes.shape({
				value : React.PropTypes.oneOfType([ React.PropTypes.string, React.PropTypes.number ]),
				text : React.PropTypes.oneOfType([ React.PropTypes.string, React.PropTypes.number ])
			})),
			value : React.PropTypes.oneOfType([ React.PropTypes.string, React.PropTypes.number ]),
			onChange : React.PropTypes.func.isRequired
		},
		getDefaultProps : function() {
			return {
				options : []
			}
		},
		getInitialState : function() {
			return {
				value : this.props.value
			};
		},
		render : function() {
			return select({
				className : 'form-control',
				value : this.state.value,
				onChange : this._handleChange
			}, this.props.options.map(function(item, i) {
				return (option({
					value : item.value,
					key : item.value
				}, item.text));
			}));
		},
		_handleChange : function(e) {
			this.setState({
				value : e.target.value
			});
			this.props.onChange(e);
		}
	});

	module.exports = React.createFactory(SelectBox)
});
