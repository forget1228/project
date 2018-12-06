
$(document).ready(function() {
    _findDate();
    $(".thumbnail").hover(function () {
        $(this).children("div").css("display","block");
    }, function () {
        $(this).children("div").css("display","none");
    });
});


function _findDate(){
    $.ajax({
        url: url.findDate ,
        type: 'post',
        data: null,
        cache: false,
        ifModified:true,
        processData: false,
        contentType: false,
        async: false
    }).done(function(result) {
        console.info(result.data)
        if(result.data.length > 0) {
            var divStart = "<div class=\"row\">";
            var divEnd = "</div>";
            var indexF = 0;
            var indexS = 0;
            var divF = "";
            var divS = "";
            var groupF = "";
            var groupS = "";
            for (var i = 0; i < result.data.length; i++) {
                var ls = result.data[i];
                if(ls.group_name == "www"){
                    divF +=
                        "   <div class=\"col-md-4\">" +
                        "       <div class=\"thumbnail\">" +
                        "           <img alt=" + ls.document_type + " src=\"static/image/" + ls.document_type + ".png\" height=\"60\" width=\"60\"/>" +
                        "           <p>" + ls.template_name + "</p>" +
                        "           <div class=\"caption\" style=\"display: none;\">" +
                        "               <p></p>" +
                        "               <p>" +
                        "                   <a class=\"btn btn-primary\" href=\"javascript:void(0);\" onclick=\"_template(" + ls.abk_id + ");\">下载</a>" +
                        "                   <a data-toggle=\"modal\" href=\"#uploadModal\" class=\" btn btn-primary\"  >更新</a>" +
                        "               </p>" +
                        "           </div>" +
                        "       </div>" +
                        "   </div>";
                    indexF ++;
                    if (indexF%3==0) {
                        groupF +=divStart+divF+divEnd;
                        divF = "";
                    }
                }else {
                    divS +=
                        "   <div class=\"col-md-4\">" +
                        "       <div class=\"thumbnail\">" +
                        "           <img alt=" + ls.document_type + " src=\"static/image/" + ls.document_type + ".png\" height=\"60\" width=\"60\"/>" +
                        "           <p>" + ls.template_name + "</p>" +
                        "           <div class=\"caption\" style=\"display: none;\">" +
                        "               <p></p>" +
                        "               <p>" +
                        "                   <a class=\"btn btn-primary\" href=\"javascript:void(0);\" onclick=\"_template(" + ls.abk_id + ");\">下载</a>" +
                        "                   <a data-toggle=\"modal\" href=\"#uploadModal\" class=\" btn btn-primary\"  >更新</a>" +
                        "               </p>" +
                        "           </div>" +
                        "       </div>" +
                        "   </div>";
                    indexS ++;
                    if (indexS%3==0) {
                        groupS +=divStart+divS+divEnd;
                        divS = "";
                    }
                }
                if(i == result.data.length-1){
                    groupF = "<p><em>公共模板</em></em></p>" + groupF+divStart+divF+divEnd;
                    groupS = "<p><em>"+ls.group_name+" 模板</em></em></p>" + groupS+divStart+divS+divEnd;
                }
            }
            $("#template").html("");
            $("#template").append(groupF + groupS);
            $(".thumbnail").hover(function () {
                $(this).children("div").css("display", "block");
            }, function () {
                $(this).children("div").css("display", "none");
            });
        }
    }).fail(function(result) {
        console.info("加载数据失败");
    });
}

function _upload(){
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
        console.info("文件更新失败");
        $('#waring').append("<h5  style='color: red'>"+result.msg+"</h5>");
    });
    $('#uploadModal').modal('hide');
    findDate();
}

function _init(){
    $.ajax({
        url: url.init ,
        type: 'post',
        data: new FormData($("#initForm")[0]),
        cache: false,
        ifModified:true,
        processData: false,
        contentType: false,
        async: false
    }).done(function(result) {
        alert(result.msg)
    }).fail(function(result) {
        console.info("文件初始化失败");
        $('#waring').append("<h5  style='color: red'>"+result.msg+"</h5>");
    });
    $('#initModal').modal('hide');
    _findDate();
}

function _template(body){
    window.location.href = url.template + "?abkId="+ body;
}