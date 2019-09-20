package com.example.crudlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudlogin.BackEnd.ApiClient;
import com.example.crudlogin.BackEnd.ApiInterface;
import com.example.crudlogin.Model.ResponseApiModel;
import com.example.crudlogin.Model.UserModel;
import com.example.crudlogin.Storage.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnLogin;

    //session
    private SessionManager sm;

    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Field is empty", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser();
                }

            }
        });

    }

    private void loginUser() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Loading ...");
        pd.setMessage("User Sedang Login");
        pd.setCancelable(false);
        pd.show();

        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseApiModel> call = apiInterface.loginUser(email, password);

        call.enqueue(new Callback<ResponseApiModel>() {
            @Override
            public void onResponse(Call<ResponseApiModel> call, Response<ResponseApiModel> response) {
                pd.dismiss();
                Log.d(TAG, "Response : " + response.toString());
                ResponseApiModel res = response.body();
                List<UserModel> user = res.getResult();
                if (res.getKode().equals("1")) {

                    sm.storeLogin(
                            user.get(0).getId(),
                            user.get(0).getNamauser(),
                            user.get(0).getJk());

                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("id", user.get(0).getId());
                    intent.putExtra("nama", user.get(0).getNamauser());
                    intent.putExtra("jk", user.get(0).getJk());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseApiModel> call, Throwable t) {
                pd.dismiss();
                Log.e(TAG, "ERROR : " + t.getMessage());
            }
        });

    }

    private void init() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        sm = new SessionManager(LoginActivity.this);
    }
}
