function validateForm() {
    // 全てのエラーメッセージをリセット
    resetErrorMessages();

    // バリデーションを行う
    let valid = true;

    // 名前のバリデーション
    const nameInput = document.getElementById('inputname');
    const nameError = document.getElementById('name-error');
    if (nameInput.value === '') {
        nameError.innerText = '名前は必須項目です。';
        valid = false;
    } else if (nameInput.value.length > 10) {
        nameError.innerText = '名前は10文字以内で入力してください。';
        valid = false;
    }

    // フリガナのバリデーション
    const phoneticInput = document.getElementById('inputphonetic');
    const phoneticError = document.getElementById('phonetic-error');
    if (phoneticInput.value === '') {
        phoneticError.innerText = 'フリガナは必須項目です。';
        valid = false;
    } else if (phoneticInput.value.length > 20) {
        phoneticError.innerText = 'フリガナは20文字以内で入力してください。';
        valid = false;
    }


    // 趣味のバリデーション（一つ以上選択が必要）
    const hobbyCheckboxes = document.querySelectorAll('input[name="hobby"]:checked');
    const hobbyError = document.getElementById('hobby-error');
    if (hobbyCheckboxes.length === 0) {
        hobbyError.innerText = '趣味は必須項目です。1つ以上選んでください。';
        valid = false;
    }

    // 一言のバリデーション
    const hitokotoTextarea = document.querySelector('textarea[name="hitokoto"]');
    const hitokotoError = document.getElementById('hitokoto-error');
    if (hitokotoTextarea.value === '') {
        hitokotoError.innerText = '一言は必須項目です。';
        valid = false;
    }

    return valid;
}

// 全てのエラーメッセージをリセットする関数
function resetErrorMessages() {
    const errorMessages = document.querySelectorAll('.error-message');
    errorMessages.forEach(function (errorMessage) {
        errorMessage.innerText = '';
    });
}
