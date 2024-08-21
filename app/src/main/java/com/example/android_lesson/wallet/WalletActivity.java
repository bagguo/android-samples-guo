package com.example.android_lesson.wallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android_lesson.R;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Provider;
import java.security.Security;
import java.util.Objects;

public class WalletActivity extends AppCompatActivity {

    private static final String TAG = WalletActivity.class.getSimpleName();

    public static void start(Context context) {
        Intent intent = new Intent(context, WalletActivity.class);
        context.startActivity(intent);
    }

    private String mAddress;
    private TextView mCallResultTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        setupBouncyCastle();

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

        findViewById(R.id.btn_connect_rpc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddress = ETHWalletHelper.getInstance().connectWallet(
                        ETHWalletHelper.RPC_SEPOLIA,
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

        findViewById(R.id.btnGetTxStatus).setOnClickListener(view -> new Thread(new Runnable() {
            @Override
            public void run() {
                String hash = "0x9a7968a806025d32c9126c7cb652513346dd041289e6b3ed4104115c422b6e1c";

                int balance = Objects.requireNonNull(ETHWalletHelper.getInstance()).getTxStatus(hash);
            }
        }).start());

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

    public static void setupBouncyCastle() {
        final Provider provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
        if (provider == null) {
            // Web3j will set up the provider lazily when it's first used.
            return;
        }
        if (provider.getClass().equals(BouncyCastleProvider.class)) {
            // BC with same package name, shouldn't happen in real life.
            return;
        }
        // Android registers its own BC provider. As it might be outdated and might not include
        // all needed ciphers, we substitute it with a known BC bundled in the app.
        // Android's BC has its package rewritten to "com.android.org.bouncycastle" and because
        // of that it's possible to have another BC implementation loaded in VM.
        Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
    }
}