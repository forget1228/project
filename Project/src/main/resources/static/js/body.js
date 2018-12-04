
$(document).ready(function() {
    findDate();
    $(".thumbnail").hover(function () {
        $(this).children("div").css("display","block");
    }, function () {
        $(this).children("div").css("display","none");
    });
});


function findDate(){
    $.post(url.findDate,null,function (result) {
        if(result.code==0){
            console.info(result.data)
            var html = "";
            for(var i=0;i< result.data.length;i++) {
                var ls = result.data[i];
                if(ls.group_name == "www") {
                    $("#template").append("<div class=\"col-md-4\">" +
                        "   <div class=\"row\">" +
                        "       <div class=\"thumbnail\">" +
                        "           <img alt=" + ls.document_type + " src=\"static/image/" + ls.document_type + ".png\" height=\"60\" width=\"60\"/>" +
                        "           <p>" + ls.template_name + "</p>" +
                        "           <div class=\"caption\" style=\"display: none;\">" +
                        "               <p></p>" +
                        "               <p>" +
                        "                   <a class=\"btn btn-primary\" href=\"javascript:void(0);\" onclick=\"template("+ls.abk_id+");\">下载</a>" +
                        "                   <a data-toggle=\"modal\" href=\"#uploadModal\" class=\" btn btn-primary\"  >更新</a>" +
                        "               </p>" +
                        "           </div>" +
                        "       </div>" +
                        "   </div>" +
                        "</div>");
                    $(".thumbnail").hover(function () {
                        $(this).children("div").css("display","block");
                    }, function () {
                        $(this).children("div").css("display","none");
                    });
                }
            }

        }else{
            $('#waring').append("<h5  style='color: red'>"+result.msg+"</h5>");
        }
    });
}

function upload(){
    //var form = new FormData($("#uploadForm")[0]);
    //form.set("time",time);
    $.ajax({
        url: url.upload ,
        type: 'post',
        data: new FormData($("#uploadForm")[0]),
        cache: false,
        processData: false,
        contentType: false,
        async: false
    }).done(function(result) {
        alert(result.msg)
    }).fail(function(result) {
        alert("文件上传失败");
    });
    $('#uploadModal').modal('hide');
}

function init(){
    $.ajax({
        url: url.init ,
        type: 'post',
        data: new FormData($("#initForm")[0]),
        cache: false,
        processData: false,
        contentType: false,
        async: false
    }).done(function(result) {
        alert(result.msg)
    }).fail(function(result) {
        alert("文件上传失败");
    });
    $('#initModal').modal('hide');
}

function template(body){
    window.location.href = url.template + "?abkId="+ body;
}