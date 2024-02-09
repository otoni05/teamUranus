package com.example.demo.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.demo.model.UserForm;

/**
 * ユーザーサービスの実装クラス
 */
@Service
public class UserService {

	@Autowired
	private ValidationChacker validationChecker;



    /**
     * ユーザーフォームのバリデーションを実行し、ユーザーを保存
     *
     * @param userForm ユーザーフォーム
     * @param result   バリデーション結果
     */
    public void validateAndSaveUser(UserForm userForm, BindingResult result) {
        // 名前のバリデーションを実行
        validateName(userForm.getName(), result);

        // フリガナのバリデーションを実行
        validateKana(userForm.getKana(), result);

        // 趣味のバリデーションを実行
        validateHobby(userForm.getHobby(), result);

        // 他の項目に対するバリデーションを必要に応じて追加

        // バリデーション結果にエラーがない場合に処理を実行
        if (!result.hasErrors()) {
            // 現在のUTCの瞬間を取得
            Instant currentUtcInstant = Instant.now();

            // 瞬間を秒までの精度に切り捨て
            Instant truncatedInstant = currentUtcInstant.truncatedTo(ChronoUnit.SECONDS);

            // InstantからTimestampへ変換
            Timestamp currentUtcTimestamp = Timestamp.from(truncatedInstant);

            // 新しいユーザーの作成日時を設定
            userForm.setCreatedAt(currentUtcTimestamp);

            // 新規ユーザーをデータベースに保存
            // (実際の保存処理はRepositoryに依存します)
            // userRepository.save(userForm);
        }
    }

    /**
     * 名前のバリデーションを実行します。
     *
     * @param name   名前
     * @param result バリデーション結果
     */
    public void validateName(String name, BindingResult result) {
        if (name == null || name.trim().isEmpty()) {
            result.rejectValue("name", "error.user", "名前は必須項目です。");
        } else if (name.length() > 10) {
            result.rejectValue("name", "error.user", "名前は10文字以内で入力してください。");
        }
    }

    /**
     * フリガナのバリデーションを実行します。
     *
     * @param kana   フリガナ
     * @param result バリデーション結果
     */
    public void validateKana(String kana, BindingResult result) {
        if (kana == null || kana.trim().isEmpty()) {
            result.rejectValue("kana", "error.user", "フリガナは必須項目です。");
        } else if (kana.length() > 20) {
            result.rejectValue("kana", "error.user", "フリガナは20文字以内で入力してください。");
        }
    }
    /**
     * 趣味のバリデーションを実行します。
     *
     * @param hobby 趣味
     * @param result バリデーション結果
     */
    public void validateHobby(String hobby, BindingResult result) {
        if (hobby == null || hobby.trim().isEmpty()) {
            result.rejectValue("hobby", "error.user", "趣味は必須項目です。1つ以上選んでください。");
        }
        // 他の趣味のバリデーションルールを必要に応じて追加
    }
}
