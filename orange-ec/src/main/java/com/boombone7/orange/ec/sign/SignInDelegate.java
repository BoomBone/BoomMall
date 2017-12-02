package com.boombone7.orange.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.boombone7.core.delegates.OrangeDelegate;
import com.boombone7.orange.ec.R;
import com.boombone7.orange.ec.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Ting
 * @date 2017/12/2.
 */

public class SignInDelegate extends OrangeDelegate {
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private String email;
    private String password;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    @OnClick(R2.id.btn_sign_in)
    public void onMBtnSignInClicked() {
        if (checkForm()){
            //TODO 登录入口默认账户admin@qq.com 密码123456
            if (email.equals("admin@qq.com")||password.equals("123456")){
                Toast.makeText(getContext(), "登陆成功", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), "登陆失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R2.id.tv_link_sign_up)
    public void onMTvLinkSignUpClicked() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    public void onMIconSignInWechatClicked() {
        if (checkForm()){
            //TODO 微信登录
            Toast.makeText(getContext(), "微信登录，不好使，别点了", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkForm() {
        email = mEmail.getText().toString();
        password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }
}
