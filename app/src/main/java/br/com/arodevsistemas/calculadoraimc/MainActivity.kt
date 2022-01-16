package br.com.arodevsistemas.calculadoraimc

import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.arodevsistemas.calculadoraimc.databinding.ActivityMainBinding
import br.com.arodevsistemas.calculadoraimc.util.text

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var fgSexo : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mainIvBoy.setOnClickListener {
            it.setBackgroundColor(Color.LTGRAY)
            binding.mainIvGirl.setBackgroundColor(Color.WHITE)
            fgSexo = "M"
        }

        binding.mainIvGirl.setOnClickListener {
            it.setBackgroundColor(Color.LTGRAY)
            binding.mainIvBoy.setBackgroundColor(Color.WHITE)
            fgSexo = "F"
        }

        binding.mainBtnCalcular.setOnClickListener {
            listener()
        }

        binding.mainBtnLimpar.setOnClickListener {
            limpar()
        }

    }

    @Override
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    private fun limpar() {
        binding.mainRespTxt.text = ""
        binding.mainRespIv.visibility = View.GONE
        binding.mainTilPeso.text = ""
        binding.mainTilAltura.text = ""
        binding.mainIvBoy.setBackgroundColor(Color.WHITE)
        binding.mainIvGirl.setBackgroundColor(Color.WHITE)
    }

    fun listener() {

        var peso = binding.mainTilPeso.editText?.text.toString().replace(",",".")
        var altura = binding.mainTilAltura.editText?.text.toString().replace(",",".")

        if(fgSexo.isNullOrEmpty()) {
            Toast.makeText(this, "É necessário selecionar o sexo!", Toast.LENGTH_SHORT).show()
        } else if (peso.isNullOrEmpty()){
            Toast.makeText(this, "É necessário informar o peso!", Toast.LENGTH_SHORT).show()
        } else if (altura.isNullOrEmpty()){
            Toast.makeText(this, "É necessário informar a altura!", Toast.LENGTH_SHORT).show()
        }else{
            calculoIMC(peso?.toFloat(), altura?.toFloat())
        }
    }

    fun calculoIMC(peso: Float, altura: Float){
        var total = (peso / (altura * altura))

        when {
            total < 18.5f -> {
                binding.mainRespTxt.text = "Seu IMC é ${"%.2f".format(total)} e sua classifcação é muito magro!"

                binding.mainRespIv.setImageResource(if (fgSexo=="M"){
                    R.drawable.icone_homem_muito_magro
                }else{
                    R.drawable.icone_mulher_muito_magra
                })
            }
            total >= 18.5f && total <= 24.9f -> {
                binding.mainRespTxt.text = "Seu IMC é ${"%.2f".format(total)} e sua classifcação é nomral!"
                binding.mainRespIv.setImageResource(if (fgSexo=="M"){
                    R.drawable.icone_homem
                }else{
                    R.drawable.icone_mulher
                })

            }
            total >= 25.0f && total <= 24.99f -> {
                binding.mainRespTxt.text = "Seu IMC é ${"%.2f".format(total)} e sua classifcação é sobrepeso!"
                binding.mainRespIv.setImageResource(if (fgSexo=="M"){
                    R.drawable.icone_homem_acima_peso
                }else{
                    R.drawable.icone_mulher_acima_peso
                })

            }
            total >= 30.0f && total <= 39.99f -> {
                binding.mainRespTxt.text = "Seu IMC é ${"%.2f".format(total)} e sua classifcação é obesidade grau 1!"
                binding.mainRespIv.setImageResource(if (fgSexo=="M"){
                    R.drawable.icone_homem_acima_peso
                }else{
                    R.drawable.icone_mulher_acima_peso
                })

            }
            total >= 40f -> {
                binding.mainRespTxt.text = "Seu IMC é ${"%.2f".format(total)} e sua classifcação é obesidade grau 2!"
                binding.mainRespIv.setImageResource(if (fgSexo=="M"){
                    R.drawable.icone_homem_acima_peso
                }else{
                    R.drawable.icone_mulher_acima_peso
                })

            }
        }

    }
}