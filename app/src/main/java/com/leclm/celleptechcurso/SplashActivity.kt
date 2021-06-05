package com.leclm.celleptechcurso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // tempo de espera antes de abrir tela de login
        Handler(Looper.getMainLooper()).postDelayed({
            // criando a intent de abrir uma nova tela
            val myIntent = Intent(this, LoginActivity::class.java) // 2 args, a tela que eu to e a tela que eu vou

            //Iniciar a LoginActivity através da Intent
            startActivity(myIntent)

            // Encerrando a tela
            finish()
        }, 2500)
    }
}