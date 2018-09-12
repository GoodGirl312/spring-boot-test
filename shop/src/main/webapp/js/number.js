function num(flag){
    var value=$("#count").val();

    if(flag=='+'){
        $("#count").val(parseInt(value)+1);
    }else if(flag=='-'){
        if(value>1){
            $("#count").val(parseInt(value)-1);
        }

    }
}