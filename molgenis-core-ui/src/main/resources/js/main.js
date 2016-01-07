'use strict';

console.log('loading requirejs configuration...');

require.config({
	// Set a baseUrl to the js/ folder, and let RequireJS know where our
	// libraries are with the paths option (compulsory when using jQuery as it
	// is a named module)
	baseUrl : '/js/',
	paths : {
		jquery : 'components/jquery/jquery-2.1.1.min',
		domready : 'components/requirejs/requirejs-domready',
		handlebars : 'components/handlebars/handlebars-v4.0.5',
		bootstrap : 'components/bootstrap/bootstrap.min',
		react : 'components/react/react-with-addons',
		underscore : 'components/underscore/underscore-min',
		molgenis : 'modules/molgenis'
	},
	// Shim any scripts that do not have AMD support
	// This is common with most jQuery plugins
	shim : {
		underscore : {
			exports : '_'
		},
		bootstrap : [ 'jquery' ],
		domready : [ 'jquery' ],
		'plugins/jQEditRangeSlider-min.js' : [ 'jquery' ],
		'plugins/jquery-sortable-min.js' : [ 'jquery' ],
		'plugins/jquery.bootstrap.pager.js' : [ 'jquery' ],
		'plugins/jquery.bootstrap.wizard.min.js' : [ 'jquery' ],
		'plugins/jquery.cookie-1.4.1.min.js' : [ 'jquery' ],
		'plugins/jquery.fancytree.min.js' : [ 'jquery' ],
		'plugins/jquery.form.min.js' : [ 'jquery' ],
		'plugins/jquery.molgenis.attributemetadata.table.js' : [ 'jquery' ],
		'plugins/jquery.molgenis.table.js' : [ 'jquery' ],
		'plugins/jquery.molgenis.tree.js' : [ 'jquery' ],
		'plugins/jquery.molgenis.xrefmrefsearch.js' : [ 'jquery' ],
		'plugins/jquery.validate.min.js' : [ 'jquery' ],
		'plugins/select2-patched.js' : [ 'jquery' ],
		'plugins/bootstrap.file-input.js' : [ 'jquery', 'bootstrap' ],
		'plugins/bootstrap-datetimepicker.min.js' : [ 'jquery', 'bootstrap' ],
		'plugins/bootstrap-fileupload.min.js' : [ 'jquery', 'bootstrap' ]
	}
});

// Specify the Core libraries which are required, and also any modules that you
// might want to load and initialise on every page
define(function(require) {
	console.log('loading main dependencies...')

	var $ = require('jquery');
	var domReady = require('domready');
	var Handlebars = require('handlebars');
	var Bootstrap = require('bootstrap');
	var React = require('react');
	var molgenis = require('molgenis');
	var _ = require('underscore');

	domReady(function() {
		// Init common module code here
		// TODO common modules, like React components????
		console.log('All dependencies loaded and DOM ready!');
	});
});
