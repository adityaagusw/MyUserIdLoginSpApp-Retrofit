package com.example.crudlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crudlogin.BackEnd.ApiClient;
import com.example.crudlogin.BackEnd.ApiInterface;
import com.example.crudlogin.Model.PesanModel;
import com.example.crudlogin.Storage.SessionManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView txtId, txtNama, txtJK;
    private EditText edtPesan;
    private Button btnLogout, btnKirimPesan;

    //session
    private SessionManager sm;

    //id
    String getId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        sm.checkLogin();

        HashMap<String, String> map = sm.getDetailLogin();
//        txtId.setText(map.get(sm.KEY_ID));
        txtNama.setText(map.get(sm.KEY_NAMA));
        txtJK.setText(map.get(sm.KEY_JK));

        getId = map.get(sm.KEY_ID);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sm.logout();
                sm.checkLogin();
                finish();
            }
        });

        btnKirimPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pesan = edtPesan.getText().toString().trim();

                if (pesan.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Jangan di kosongkan", Toast.LENGTH_SHORT).show();
                } else {
                    kirimPesanUser(getId, pesan);
                }

            }
        });

    }

    private void kirimPesanUser (String user_id, String pesan) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Loading ...");
        pd.setMessage("Sedang Mengirim Pesan");
        pd.setCancelable(false);
        pd.show();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<PesanModel> call = apiInterface.kirimPesan(user_id,pesan);

        call.enqueue(new Callback<PesanModel>() {
            @Override
            public void onResponse(Call<PesanModel> call, Response<PesanModel> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                pd.dismiss();

                if (value.equals("1")){
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PesanModel> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(MainActivity.this, "ERROR : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void init() {
        txtId = findViewById(R.id.txtId);
        txtNama = findViewById(R.id.txtNama);
        txtJK = findViewById(R.id.txtJK);

        edtPesan = findViewById(R.id.edtPesan);

        btnLogout = findViewById(R.id.btnLogout);
        btnKirimPesan = findViewById(R.id.btnKirimPesan);

        sm = new SessionManager(MainActivity.this);
    }
}
