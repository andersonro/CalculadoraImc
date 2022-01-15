package br.com.arodevsistemas.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.arodevsistemas.calculadoraimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.txtMain.text = "Teste"
    }
}