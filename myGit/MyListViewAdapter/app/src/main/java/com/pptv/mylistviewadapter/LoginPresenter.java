package com.pptv.mylistviewadapter;

import com.pptv.mylistviewadapter.biz.CallBack;
import com.pptv.mylistviewadapter.biz.loginSource;
import com.pptv.mylistviewadapter.view.UiView;

/**
 * @author LeiKang
 * @time 2017/2/28
 */
public class LoginPresenter
{
    private UiView uiView;

    private loginSource loginSource;

    public LoginPresenter(UiView uiView)
    {
        this.uiView = uiView;
    }

    public void login()
    {
        loginSource.login(uiView.getName(), uiView.getPas(), new CallBack()
        {
            @Override
            public void loginSuccess()
            {

            }

            @Override
            public void loginFail()
            {

            }
        });

    }

}
