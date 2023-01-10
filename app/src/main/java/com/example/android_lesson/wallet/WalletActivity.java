package com.example.android_lesson.wallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android_lesson.MainActivity;
import com.example.android_lesson.MyApplication;
import com.example.android_lesson.R;

public class WalletActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    private String mAddress;
    private TextView mCallResultTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        MyApplication.setupBouncyCastle();

        findViewById(R.id.btn_create_bip39_wallet).setOnClickListener(view ->
                ETHWalletHelper.getInstance().generateBip39Wallets()
        );

        TextView mnemonicTv = findViewById(R.id.tv_show_mnemonic);
        TextView privateKeyTv = findViewById(R.id.tv_show_private_key);

        findViewById(R.id.btn_get_mnemonic).setOnClickListener(view ->
                mnemonicTv.setText(ETHWalletHelper.getInstance().getMnemonic())
        );

        findViewById(R.id.btn_get_private_key).setOnClickListener(view ->
                privateKeyTv.setText(ETHWalletHelper.getInstance().getPrivateKey())
        );

        findViewById(R.id.btn_connect_ganache).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddress = ETHWalletHelper.getInstance().connectWallet(ETHWalletHelper.GANACHE_SERVER_URL,
                        ETHWalletHelper.PASSWORD,
                        ETHWalletHelper.MNEMONIC);
            }
        });

        findViewById(R.id.btn_store).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String value = String.valueOf(Math.random());
                            ETHWalletHelper.getInstance().setValue(mAddress, "store", value);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });

        TextView balanceTv = findViewById(R.id.tv_balance);
        findViewById(R.id.btn_get_balance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String balance = ETHWalletHelper.getInstance().getAccountBalance(ETHWalletHelper.ACCOUNT1);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                balanceTv.setText(balance);
                            }
                        });
                    }
                }).start();
            }
        });

        findViewById(R.id.btn_import_wallet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WalletActivity.this, ImportWalletActivity.class);
                startActivity(intent);
            }
        });

        mCallResultTv = findViewById(R.id.tv_call_result);
        findViewById(R.id.btn_retrieve).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String retrieve = ETHWalletHelper.getInstance().getValue("retrieve");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mCallResultTv.setText(retrieve);
                            }
                        });
                    }
                }).start();
            }
        });
    }
}