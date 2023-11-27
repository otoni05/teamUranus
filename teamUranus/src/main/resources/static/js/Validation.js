

window.onload = function(){
    /*各画面オブジェクト*/
    const btnSubmit = document.getElementById('inputname');
    const inputName = document.getElementById('inputphonetic');
    const inputAge = document.getElementsByClassName('checkradio');
    const reg = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}.[A-Za-z0-9]{1,}$/;
    
    
    btnSubmit.addEventListener('click', function(event) {
        let message = [];
        /*入力値チェック*/
        if(inputname.value ==""){
            message.push("名前は必須項目です");
        }
        if (inputname.length > 0 && inputname.length <= 10) {
            message.push("名前は10文字以内で入力してください");
        }
        if(inputphonetic.value ==""){
            message.push("名前は必須項目です");
        }
        if (inputphonetic.length > 0 && inputphonetic.length <= 20) {
            message.push("名前は20文字以内で入力してください");
        }
        alert('入力チェックOK');
    });
};