package com.muhammedturgut.kenniyiyakalakotlin

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.muhammedturgut.kenniyiyakalakotlin.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var scoor = 0
    var time=15
    var arrayImages = Array(3) { Array<ImageView?>(3) { null } }
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // ImageView'ları dizimize atıyoruz
        arrayImages[0][0] = binding.thor1
        arrayImages[0][1] = binding.thor2
        arrayImages[0][2] = binding.thor3
        arrayImages[1][0] = binding.thor4
        arrayImages[1][1] = binding.thor5
        arrayImages[1][2] = binding.thor6
        arrayImages[2][0] = binding.thor7
        arrayImages[2][1] = binding.thor8
        arrayImages[2][2] = binding.thor9

        // Başlangıçta tüm görselleri gizle
        hideAllImages()

        // Her saniyede bir rastgele görsel göster
        startRandomImageCycle()
        onTime()
    }

    // Tüm görselleri gizleme fonksiyonu
    private fun hideAllImages() {
        for (i in 0..2) {
            for (j in 0..2) {
                arrayImages[i][j]?.visibility = View.INVISIBLE
            }
        }
    }

    // Rastgele bir görseli göstermek için fonksiyon
    private fun showRandomImage() {
        // Tüm görselleri gizle
        hideAllImages()

        // 1 ile 9 arasında rastgele bir sayı üret
        val randomNumber = Random.nextInt(1, 10)  // 1 dahil, 10 hariç

        // Rastgele sayıdaki görseli göster
        val row = (randomNumber - 1) / 3  // Satır
        val col = (randomNumber - 1) % 3  // Sütun
        arrayImages[row][col]?.visibility = View.VISIBLE  // Görseli görünür yap
    }

    // Her saniyede bir rastgele görsel göstermek için bir Handler kullanıyoruz
    private fun startRandomImageCycle() {
        val runnable = object : Runnable {
            override fun run() {
                showRandomImage()  // Rastgele bir görseli göster
                handler.postDelayed(this, 500)  // 1 saniye sonra tekrar çalıştır
            }
        }
        handler.post(runnable)  // İlk çalıştırmayı başlatıyoruz
    }

    // Butonlara tıklama olayları ekliyoruz
    private fun onThorClick(i: Int, j: Int) {
        scoor++
        binding.Scoore.text = "Score: $scoor"
    }
    private fun onTime(){
        val runnable = object : Runnable {
            override fun run() {
                handler.postDelayed(this, 1000)  // 1 saniye sonra tekrar çalıştır

                if(time==0){
                    time=14
                    val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                    builder
                        .setMessage("UYARI !")
                        .setTitle("Yeniden Başlamak İstermisiniz?"+"Scoore: "+scoor)
                        .setPositiveButton("Yeniden Başla") { dialog, which ->
                            scoor=0
                            time=15
                        }

                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
                time--
                binding.time.setText("Time: "+time)
            }
        }
        handler.post(runnable)
    }

    // Butonlara tıklama fonksiyonları
    fun thor1Button(view: View) {
        onThorClick(0, 0)
    }

    fun thor2Button(view: View) {
        onThorClick(0, 1)
    }

    fun thor3Button(view: View) {
        onThorClick(0, 2)
    }

    fun thor4Button(view: View) {
        onThorClick(1, 0)
    }

    fun thor5Button(view: View) {
        onThorClick(1, 1)
    }

    fun thor6Button(view: View) {
        onThorClick(1, 2)
    }

    fun thor7Button(view: View) {
        onThorClick(2, 0)
    }

    fun thor8Button(view: View) {
        onThorClick(2, 1)
    }

    fun thor9Button(view: View) {
        onThorClick(2, 2)
    }
}
