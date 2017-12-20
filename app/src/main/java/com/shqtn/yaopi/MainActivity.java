package com.shqtn.yaopi;

import com.shqtn.yaopi.ui.LoginActivity;

public class MainActivity extends BaseActivity {


    @Override
    public void createView() {
        startActivity(LoginActivity.class);
        finish();
    }
}
