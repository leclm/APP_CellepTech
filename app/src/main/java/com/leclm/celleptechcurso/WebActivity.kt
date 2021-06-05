package com.leclm.celleptechcurso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        // Habilitando a leitura e execução de códigos JS
        wbvWeb.settings.javaScriptEnabled = true

        // Carregando um enredeço web para a WebView
        wbvWeb.loadUrl("http://br.cellep.com/estacaohack/")

        // Definindo o webview como o cliente padrao
        wbvWeb.webViewClient = WebViewClient()
    }
}