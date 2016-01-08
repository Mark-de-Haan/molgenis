define(function(require, exports, module) {
	/**
	 * @module LanguageSelectBox
	 */
	"use strict";

	var React = require('react');
	var _ = require('underscore');

	var Button = require('component/Button');
	var Modal = require('component/Modal');
	var Spinner = require('component/Spinner');

	var DeepPureRenderMixin = require('component/mixin/DeepPureRenderMixin');

	var Select2 = require('component/wrapper/Select2');

	var api = require('modules/RestClientV1');

	/**
	 * Shows a Select2 box for switching the user language
	 * 
	 * @memberOf LanguageSelectBox
	 */
	var LanguageSelectBox = React.createClass({
		mixins : [ DeepPureRenderMixin ],
		displayName : 'LanguageSelectBox',
		propTypes : {
			onValueChange : React.PropTypes.func
		},
		getInitialState : function() {
			return {
				select2Data : null,
				selectedLanguage : null
			};
		},
		componentDidMount : function() {
			this._loadLanguages();
		},
		render : function() {
			if (this.state.select2Data === null) {
				return Spinner();
			}

			if (this.state.select2Data.length > 1) {
				return Select2({
					options : {
						data : this.state.select2Data,
					},
					value : this.state.selectedLanguage,
					name : 'languages',
					onChange : this._handleChange
				});
			}

			return React.DOM.div();
		},
		_loadLanguages : function() {
			var self = this;
			api.getAsync('/api/v2/languages').done(function(languages) {
				var selectedLanguage = null;
				var select2Data = languages.items.map(function(item) {
					if (item.code === languages.meta.languageCode) {
						selectedLanguage = {
							id : item.code,
							text : item.name
						};
					}
					return {
						id : item.code,
						text : item.name
					}
				});

				self.setState({
					select2Data : select2Data,
					selectedLanguage : selectedLanguage
				});
			});
		},
		_handleChange : function(language) {
			$.ajax({
				type : 'POST',
				url : '/plugin/useraccount/language/update',
				data : 'languageCode=' + language.id,
				success : function() {
					location.reload(true);
				}
			});
		}
	});

	module.exports = React.createFactory(LanguageSelectBox)
});