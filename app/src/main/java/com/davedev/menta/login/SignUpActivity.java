package com.davedev.menta.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.davedev.menta.R;
import com.davedev.menta.onboarding.IntroActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    TextView descTextView, titleTextView, newAccTextView, registerTextView;
    ImageView logoImageView, bgRightImageView, bgLeftImageView;

    TextInputLayout userTextField, passwordTextField, emailTextField, repeatPasswordTextField;

    TextInputEditText nameTextInputEditText, passTextInputEditText,
            confirmPassTextInputEditText, emailTextInputEditText;

    Button loginButton;

    Animation downMove, upMove, leftMove, rightMove, fadeIn;

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        //TextView
        descTextView = findViewById(R.id.descTextView);
        titleTextView = findViewById(R.id.titleTextView);
        newAccTextView = findViewById(R.id.newAccTextView);
        registerTextView = findViewById(R.id.registerTextView);

        //ImageView
        logoImageView = findViewById(R.id.logoImageView);
        bgRightImageView = findViewById(R.id.bgRightImageView);
        bgLeftImageView = findViewById(R.id.bgLeftImageView);

        //EditText
        nameTextInputEditText = findViewById(R.id.nameTextInputEditText);
        emailTextInputEditText = findViewById(R.id.emailTextInputEditText);
        passTextInputEditText = findViewById(R.id.passTextInputEditText);
        confirmPassTextInputEditText = findViewById(R.id.confirmPassTextInputEditText);

        //TextInput
        userTextField = findViewById(R.id.userTextField);
        passwordTextField = findViewById(R.id.passwordTextField);
        emailTextField = findViewById(R.id.emailTextField);
        repeatPasswordTextField = findViewById(R.id.repeatPasswordTextField);

        //Button
        loginButton = findViewById(R.id.loginButton);

        //Animations
        downMove = AnimationUtils.loadAnimation(this, R.anim.down_move);
        upMove = AnimationUtils.loadAnimation(this, R.anim.up_move);
        leftMove = AnimationUtils.loadAnimation(this, R.anim.left_move);
        rightMove = AnimationUtils.loadAnimation(this, R.anim.right_move);
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        //Set Animations
        bgLeftImageView.setAnimation(fadeIn);
        bgRightImageView.setAnimation(fadeIn);
        logoImageView.setAnimation(downMove);
        descTextView.setAnimation(downMove);
        titleTextView.setAnimation(downMove);
        userTextField.setAnimation(leftMove);
        passwordTextField.setAnimation(rightMove);
        loginButton.setAnimation(upMove);
        newAccTextView.setAnimation(upMove);
        registerTextView.setAnimation(upMove);
        emailTextField.setAnimation(leftMove);
        repeatPasswordTextField.setAnimation(rightMove);

        loginButton.setOnClickListener(view -> validateField());

        registerTextView.setOnClickListener(view -> {
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
        finish();
    }

    public void validateField(){

        String name = Objects.requireNonNull(nameTextInputEditText.getText()).toString().trim();
        String email = Objects.requireNonNull(emailTextInputEditText.getText()).toString().trim();
        String password = Objects.requireNonNull(passTextInputEditText.getText()).toString().trim();
        String confirmPassword = Objects.requireNonNull(confirmPassTextInputEditText.getText()).toString().trim();

        boolean valid = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTextInputEditText.setError(getString(R.string.errorMail));
            valid = false;
        } else emailTextInputEditText.setError(null);

        if (name.length() < 3) {
            nameTextInputEditText.setError(getString(R.string.errorName));
            valid = false;
        } else nameTextInputEditText.setError(null);

        if (password.length() < 8) {
            passTextInputEditText.setError(getString(R.string.errorCaracter));
            valid = false;
        } else if (!password.matches(".*\\d.*")) {
            passTextInputEditText.setError(getString(R.string.errorNum));
            valid = false;
        } else passTextInputEditText.setError(null);

        if (!confirmPassword.equals(password)) {
            confirmPassTextInputEditText.setError(getString(R.string.errorPassDiff));
            valid = false;
        } else confirmPassTextInputEditText.setError(null);

        if(valid){
            registerUser(name,email,password);
        }
    }

    private void registerUser(String name,String email,String password){

        loginButton.setEnabled(false);

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(task -> {

                    loginButton.setEnabled(true);

                    if(task.isSuccessful()){

                        String uid = mAuth.getCurrentUser().getUid();

                        Map<String,Object> userMap = new HashMap<>();
                        userMap.put("nombre",name);
                        userMap.put("email",email);

                        db.collection("usuarios")
                                .document(uid)
                                .set(userMap)
                                .addOnSuccessListener(unused -> {

                                    Toast.makeText(this,"Registro exitoso",Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(SignUpActivity.this, IntroActivity.class));
                                    finish();
                                })
                                .addOnFailureListener(e ->
                                        Toast.makeText(this,"Error guardando datos",Toast.LENGTH_LONG).show()
                                );

                    } else {
                        Toast.makeText(this,"Error: "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
    }
}