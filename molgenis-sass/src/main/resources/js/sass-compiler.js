var sass = window.sass = new Sass();

(function ($, molgenis) {
  "use strict";

  var convert = $('#convert');
  var path = "custom.scss"

  convert.on('click', function () {
    // var code = document.getElementById("sass-input").value
    //
    // console.log(code)

    console.log('click and compile')

    sass.compileFile(path, function callback(result) {
      // (string) content is the file's content,
      //   `undefined` when the read failed
      console.log(result)
    });

    // sass.compile(code, function (result) {
    //   if (result.status) {
    //     var formatted = result.formatted;
    //     console.log(formatted)
    //   } else {
    //     console.log(result.text)
    //   }
    // })
  })
})($, window.top.molgenis = window.top.molgenis || {});