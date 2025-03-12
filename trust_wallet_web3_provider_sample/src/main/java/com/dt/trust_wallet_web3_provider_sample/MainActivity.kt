package com.dt.trust_wallet_web3_provider_sample

import android.app.Activity
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : Activity() {
    companion object {
        private const val DAPP_URL = "https://app.uniswap.org/"
        private const val CHAIN_ID = 56
        private const val RPC_URL = "https://bsc-dataseed2.binance.org"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val provderJs = loadProviderJs()
        val initJs = loadInitJs(
            CHAIN_ID,
            RPC_URL
        )
        WebView.setWebContentsDebuggingEnabled(true)
        val webview: WebView = findViewById(R.id.webview)
        webview.settings.run {
            javaScriptEnabled = true
            domStorageEnabled = true
        }
        WebAppInterface(this, webview, DAPP_URL).run {
            webview.addJavascriptInterface(this, "_tw_")

            val webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    view?.evaluateJavascript(provderJs, null)
                    view?.evaluateJavascript(initJs, null)
                }

                override fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
                ) {
                    // TODO: verify
                    // Ignore SSL certificate errors
                    handler?.proceed()
                    println(error.toString())
                }
            }
            webview.webViewClient = webViewClient
            webview.loadUrl(DAPP_URL)
        }
    }

    private fun loadProviderJs(): String {
        return resources.openRawResource(R.raw.trust_min).bufferedReader().use { it.readText() }
    }

    private fun loadInitJs(chainId: Int, rpcUrl: String): String {
        val source = """
        (function() {
            var config = {                
                ethereum: {
                    address: "0xF42b63F38D019014ECCa3AB07CeDAB68e76a3516",
                    chainId: 8922,
                    rpcUrl: "https://rpc.alg2-test.algen.network/",
                    networkVersion: 1,
                    isMimo: true,
                    isZapry: true,
                    isDebug: true,
                    isMetaMask: true,
                },
                solana: {
                    cluster: "mainnet-beta",
                    rpcUrl: "https://fabled-cosmopolitan-snow.solana-mainnet.quiknode.pro/42611fd7321eb23f2af790453f543e65fc99e2ef",
                    isMimo: true,
                    isZapry: true,
                    isDebug: true,
                    isPhantom: true,
                },
                tronLink: {
                   rpcUrl: "https://nile.trongrid.io",
                   address: "TJgvecXA5R4QPSQB8QqiJvNzXHgiTkJYjN",
                   isMimo: true,
                   isZapry: true,
                   isDebug: true,
                }
            };
            
            trustwallet.ethereum = new trustwallet.Provider(config);
            trustwallet.solana = new trustwallet.SolanaProvider(config);
            trustwallet.tronLink = new trustwallet.TronProvider(config.tronLink);
            
            trustwallet.postMessage = (jsonString) => {
                window._tw_.postMessage(JSON.stringify(jsonString));
            }
                        
            window.ethereum = trustwallet.ethereum;
            window.tronLink = trustwallet.tronLink
            window.tron = trustwallet.tronLink
            window.solana = trustwallet.solana
        })();
        """
        return  source
    }
}