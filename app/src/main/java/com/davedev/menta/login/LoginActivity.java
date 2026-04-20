package com.davedev.menta.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.davedev.menta.R;
import com.davedev.menta.onboarding.IntroActivity;
import com.google.android.material.divider.MaterialDivider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextView descTextView, titleTextView, suppTextView, newAccTextView, registerTextView, forgotPassTextView, errorFieldTextView;
    ImageView logoImageView, bgRightImageView, bgLeftImageView;
    TextInputLayout userTextField, passwordTextField;
    TextInputEditText userTextInputEditText, passwordTextInputEditText;
    Button loginButton;
    FloatingActionButton facebookFab, googleFab, appleFab;
    Animation downMove, upMove, leftMove, rightMove, fadeIn;
    MaterialDivider leftDivider, rightDivider;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        //TextView
        errorFieldTextView = findViewById(R.id.errorFieldTextView);
        descTextView = findViewById(R.id.descTextView);
        titleTextView = findViewById(R.id.titleTextView);
        suppTextView = findViewById(R.id.suppTextView);
        newAccTextView = findViewById(R.id.newAccTextView);
        forgotPassTextView = findViewById(R.id.forgotPassTextView);
        registerTextView = findViewById(R.id.registerTextView);

        //ImageView
        logoImageView = findViewById(R.id.logoImageView);
        bgRightImageView = findViewById(R.id.bgRightImageView);
        bgLeftImageView = findViewById(R.id.bgLeftImageView);

        //TextInput
        userTextField = findViewById(R.id.userTextField);
        passwordTextField = findViewById(R.id.passwordTextField);

        //EditText
        userTextInputEditText = findViewById(R.id.userTextInputEditText);
        passwordTextInputEditText = findViewById(R.id.passwordTextInputEditText);

        //Button
        loginButton = findViewById(R.id.loginButton);

        //Dividers
        leftDivider = findViewById(R.id.leftMaterialDivider);
        rightDivider = findViewById(R.id.rightMaterialDivider2);

        //FABs
        facebookFab = findViewById(R.id.facebookFab);
        googleFab = findViewById(R.id.googleFab);
        appleFab = findViewById(R.id.appleFab);

        //Animations
        downMove = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.down_move);
        upMove = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.up_move);
        leftMove = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.left_move);
        rightMove = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.right_move);
        fadeIn = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.fade_in);

        //Set Animations
        bgLeftImageView.setAnimation(fadeIn);
        bgRightImageView.setAnimation(fadeIn);
        logoImageView.setAnimation(downMove);
        descTextView.setAnimation(downMove);
        titleTextView.setAnimation(downMove);
        userTextField.setAnimation(leftMove);
        passwordTextField.setAnimation(rightMove);
        forgotPassTextView.setAnimation(rightMove);
        loginButton.setAnimation(upMove);
        suppTextView.setAnimation(upMove);
        facebookFab.setAnimation(leftMove);
        googleFab.setAnimation(upMove);
        appleFab.setAnimation(rightMove);
        newAccTextView.setAnimation(upMove);
        registerTextView.setAnimation(upMove);
        leftDivider.setAnimation(leftMove);
        rightDivider.setAnimation(rightMove);

        forgotPassTextView.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
            finish();
        });

        registerTextView.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });

        loginButton.setOnClickListener(view -> validateField());
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void validateField(){

        String email = Objects.requireNonNull(userTextInputEditText.getText()).toString().trim();
        String password = Objects.requireNonNull(passwordTextInputEditText.getText()).toString().trim();

        boolean valid = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userTextInputEditText.setError(getString(R.string.errorMail));
            valid = false;
        } else {
            userTextInputEditText.setError(null);
        }

        if (password.isEmpty()) {
            passwordTextInputEditText.setError(getString(R.string.errorPass));
            valid = false;
        } else {
            passwordTextInputEditText.setError(null);
        }

        if(valid){
            startSession(email, password);
        }
    }

    public void startSession(String email, String password){

        loginButton.setEnabled(false);

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {

                    loginButton.setEnabled(true);

                    if(task.isSuccessful()){
                        errorFieldTextView.setVisibility(View.GONE);

                        Intent intent = new Intent(LoginActivity.this, IntroActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        errorFieldTextView.setVisibility(View.VISIBLE);
                        errorFieldTextView.setText("Correo o contraseña incorrectos");
                    }
                });
    }
}