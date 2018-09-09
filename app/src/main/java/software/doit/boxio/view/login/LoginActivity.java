package software.doit.boxio.view.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import software.doit.boxio.R;
import software.doit.boxio.databinding.ActivityLoginBinding;
import software.doit.boxio.view.main.MainActivity;
import timber.log.Timber;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends DaggerAppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */

    // UI references.
    //ActivityLoginBinding activityLoginBinding;

    @Inject
    LoginViewModel loginViewModel;

    ActivityLoginBinding binding;

    private AutoCompleteTextView mEmailView;
    private EditText mNameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button mEmailSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        // Set up the login form.
        binding.setViewModel(loginViewModel);
        mEmailView = findViewById(R.id.email);
        mNameView = findViewById(R.id.name);
        mPasswordView = findViewById(R.id.password);
        mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        observeViewModel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgress(false);
    }

    private void observeViewModel() {
        binding.getViewModel().toastMessage.observe(this, o -> {
            Timber.d("toastMessage changed ");
            Toast.makeText(LoginActivity.this, o, Toast.LENGTH_SHORT).show();
        });
        binding.getViewModel().goToMainActivity.observe(this, o -> {
            Timber.d("goToMainActivity");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        binding.getViewModel().enableRegisterMode.observe(this, isRegisterModeEnable -> {
            if (isRegisterModeEnable != null && isRegisterModeEnable) {
                mNameView.setVisibility(View.VISIBLE);
                mEmailSignInButton.setText(getString(R.string.reg_new_usr));
            }
        });
        binding.getViewModel().showProgress.observe(this, showProgress -> {
            if (showProgress != null) {
                showProgress(showProgress);
            }
        });
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}

