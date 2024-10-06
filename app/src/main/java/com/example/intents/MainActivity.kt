package com.example.intents

import android.content.Intent
import android.content.Intent.ACTION_CALL
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.intents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    companion object Constantes{
        const val parametro_exta = "PARAMETRO_EXTRA"
        const val PARAMETRO_REQUEST_CODE = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(amb.toolbarTb)
        supportActionBar?.title = "${getString(R.string.app_name)} - ${this::class.java.simpleName}"



        amb.entrarParametroBt.setOnClickListener{
            Intent(this, ParentActivity::class.java).apply {
                amb.parametroTv.text.toString().let {
                    putExtra(parametro_exta, it)
                }
                startActivityForResult(this, PARAMETRO_REQUEST_CODE)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.manu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.ViewMi -> {
                val url : Uri = Uri.parse(amb.parametroTv.text.toString())
                //val navegadorIntent: Intent(ACTION_VIEW , url)
                //startActivity(navegadorIntent)
                true
            }
            R.id.DiaMi -> {
                val telUri = Uri.parse("tel: ${amb.parametroTv.text}")
                val discarIntent = Intent(ACTIVITY_SERVICE)
                discarIntent.setData(telUri)
                startActivity(discarIntent)

                /*Uri.parse("tel: ${amb.parametroTv.text}").let {
                    Intent(ACTIVITY_SERVICE).apply {
                        this.setData(it)
                        startActivity(this)
                    }
                }*/
                true
            }
            R.id.callMi -> {
                Uri.parse("tel: ${amb.parametroTv.text}").let {
                    Intent(ACTION_CALL).apply {
                        data = it
                        startActivity(this)
                    }
                }
                true
            }
            R.id.pickMi -> {true}
            R.id.chooserMi -> {true}
            else -> {false}
        }
    }
}