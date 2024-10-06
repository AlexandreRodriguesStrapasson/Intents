package com.example.intents

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intents.MainActivity.Constantes.parametro_exta
import com.example.intents.databinding.ActivityParentBinding

class ParentActivity : AppCompatActivity() {
    private val apb: ActivityParentBinding by lazy {
        ActivityParentBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apb.root)

        setSupportActionBar(apb.toolbarTb)
        supportActionBar?.title = "${getString(R.string.app_name)} - ${this::class.java.simpleName}"

        intent.getStringExtra(parametro_exta)?.also { parametro ->    //Colcoado parametro para contextualizar, se não posso deixar "it"
            apb.parametroEt.setText(parametro)
        }

        apb.enviarParametroBt.setOnClickListener{
            Intent().apply {
                apb.parametroEt.text.toString().let{
                    putExtra(parametro_exta, it)
                }
            }
            setResult()
        }
    }
}