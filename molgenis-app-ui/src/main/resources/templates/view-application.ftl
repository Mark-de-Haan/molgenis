<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Application title -->
        <title>${application_title}</title>

        <!-- Application metadata -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap + Font Awesome stylesheets -->
        <link rel="stylesheet" href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="/css/font-awesome.min.css" type="text/css">

        <!-- JQuery -->
        <script src="/js/jquery-3.3.1.min.js" type="text/javascript"></script>

        <!-- Bootstrap bundle which includes popper.js -->
        <script src="/js/bootstrap.bundle.min.js" type="text/javascript"></script>

        <!-- VueJS for including Dynamic plugins -->
        <script src="/js/vue.min.js" type="text/javascript"></script>

        <!-- Browser tab icon -->
        <link rel="shortcut icon" href="https://avatars1.githubusercontent.com/u/1688158?v=4&s=280"/>

        <!-- Load application CSS -->
        <link rel="stylesheet" href="/css/application/molgenis-application.css" type="text/css">
    </head>

    <!-- Page content -->
    <body>

        <!-- Application container -->
        <div id="application-container"></div>

        <!-- Load initial state for Vue application -->
        <script>
            window.MOLGENIS_PLUGIN_ROUTES = [
                {
                    id: 'hello-world',
                    name: 'Hello World',
                    props: {
                        props: {
                            component: require('/plugins/Test.vue'),
                            name: 'Hello World'
                        }
                    }
                },
                {
                    id: 'bye-world',
                    name: 'Bye World',
                    props: {
                        props: {
                            url: '/Users/mdehaan/git/vue-playground/molgenis-ui-bootstrap/src/components/biobank-explorer/index.html',
                            name: 'Bye World'
                        }
                    }
                }
            ]
        </script>

        <!-- Load Application javascript after initial page rendering -->
       <script src="/js/application/molgenis-application.js" type="text/javascript"></script>
    </body>

</html>