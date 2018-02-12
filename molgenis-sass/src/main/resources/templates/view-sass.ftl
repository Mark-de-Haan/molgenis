<#include "resource-macros.ftl">
<#include "molgenis-header.ftl">
<#include "molgenis-footer.ftl">

<#assign js = []>
<#assign css = []>

<#assign version = 2>
<@header css js version/>

<div class="row">
    <div class="col-md-6">
        <textarea id="sass-input" rows="5" cols="20"></textarea>
    </div>
    <div class="col-md-6">
        <textarea id="css-output" rows="5" cols="20"></textarea>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <button type="button" id="convert" class="btn btn-primary">Compile</button>
    </div>
</div>

<script type=text/javascript src="<@resource_href "/js/sass.js"/>"></script>
<script type=text/javascript src="<@resource_href "/js/sass-compiler.js"/>"></script>

<@footer version/>