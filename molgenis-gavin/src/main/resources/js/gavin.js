$(function () {
    var form = $('#gavin-form');

    if (form.length) {
        React.render(molgenis.ui.UploadContainer({
            'id' : 'gavin-fileupload',
            'url' : '/plugin/gavin-app/annotate-file',
            'type' : 'file',
            'name' : 'gavin-uploader',
            'width' : '12',
            onSubmit : function (jobExecutionID) {
                location = "gavin-app/job/" + jobExecutionID;
            },
            validExtensions : ['.vcf', '.vcf.gz', '.tsv', '.tsv.gz']
        }), form[0]);
    } else {
        $('#gavin-view').on('click', '.glyphicon-cog', function (e) {
            var formNode = $('#form')[0];
            var annotator = $(e.target).data('name');
            React.unmountComponentAtNode(formNode);
            React.render(molgenis.ui.Form({
                entity : 'sys_set_' + annotator,
                entityInstance : annotator,
                mode : 'edit',
                modal : true,
                enableOptionalFilter : false,
                enableFormIndex : false,
                onSubmitSuccess : function () {
                    location.reload();
                }
            }), formNode);
        });
    }
});