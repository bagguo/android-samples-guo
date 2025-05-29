package com.example.android_lesson.wallet;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android_lesson.R;
import com.example.android_lesson.base.activity.BaseActivity;
import com.example.android_lesson.util.StringUtil;

import org.web3j.crypto.Bip39Wallet;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.crypto.WalletUtils;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.IOException;

public class ImportWalletActivity extends BaseActivity {

    private static final String TAG = ImportWalletActivity.class.getSimpleName();

    EditText editText;
    TextView addressTv;
    TextView privateKeyAddressTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_import_wallet;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        editText = findViewById(R.id.et);
        addressTv = findViewById(R.id.tv_address);

        Button mnemonicImportBtn = findViewById(R.id.btn_import_mnemonic);
        mnemonicImportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mnemonic = editText.getText().toString();
                importWalletByMnemonic(mnemonic);
            }
        });

        Button privateKeyImportBtn = findViewById(R.id.btn_import_private_key);
        privateKeyImportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String privateKey = editText.getText().toString();
                importWalletByPrivateKey(privateKey);
            }
        });

        Button keystoreImportBtn = findViewById(R.id.btn_import_keystore);
        keystoreImportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filePath = ETHWalletHelper.PATH + File.separator + "UTC--2023-01-10T03-09-42.325000000Z--1256b39f11591c3469a41df722c52c6f5c970622.json";
                String address = ETHWalletHelper.loadJsonCredentials(filePath);
                addressTv.setText(address);
                Toast.makeText(ImportWalletActivity.this, "导入成功", Toast.LENGTH_LONG).show();
            }
        });
    }

    //助记词导入钱包
    private void importWalletByMnemonic(String mnemonic) {
        Bip39Wallet wallet;
        try {
            wallet = WalletUtils.generateBip39WalletFromMnemonic(ETHWalletHelper.PASSWORD, mnemonic, ETHWalletHelper.PATH);

            String address = StringUtil.getAddressFromWalletJsonFileName(wallet.getFilename());
            Toast.makeText(ImportWalletActivity.this, "导入成功", Toast.LENGTH_LONG).show();
            addressTv.setText(address);
        } catch (CipherException | IOException e) {
            e.printStackTrace();
        }
    }

    //私钥导入钱包
    private void importWalletByPrivateKey(String privateKey) {
        String hexPrivateKey = StringUtil.bigInteger2Hex(privateKey);
        ECKeyPair ecKeyPair = ECKeyPair.create(Numeric.toBigInt(hexPrivateKey));

        WalletFile wallet;
        try {
            wallet = Wallet.createStandard(ETHWalletHelper.PASSWORD, ecKeyPair);

            Toast.makeText(ImportWalletActivity.this, "导入成功", Toast.LENGTH_LONG).show();
            privateKeyAddressTv.setText(wallet.getAddress());
        } catch (CipherException e) {
            e.printStackTrace();
        }
    }

}