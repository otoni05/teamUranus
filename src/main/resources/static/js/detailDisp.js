window.onload = function(){
// 削除ボタン
const modelOpenBtn = document.getElementById('modelOpenBtn');
// 削除ボタン押下時に全画面表示されるウインドウ
const deleteModal = document.getElementById('deleteModal');
// メッセージと確認ボタンが表示されるウインドウ
const miniWindow = document.getElementById('miniWindow');
// キャンセルボタン
const cancel = document.getElementById('cancel');

// 削除ボタンが押されたら
modelOpenBtn.addEventListener('click', function(){
	// 隠れていた二つの画面を有効にする
	deleteModal.style.display = 'block';
	miniWindow.style.display = 'block';
});
// キャンセルボタンが押されたら
cancel.addEventListener('click',function(){
	// 二つの画面を無効化する
	deleteModal.style.display = 'none';
	miniWindow.style.display = 'none';
})

}