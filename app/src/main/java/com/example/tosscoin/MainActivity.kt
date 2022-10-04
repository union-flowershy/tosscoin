package com.example.tosscoin

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    
    var state : Int = 1 // 동전의 앞면, 뒷면
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tossButton = findViewById<Button>(R.id.toss_button)
        val coinImage = findViewById<ImageView>(R.id.coin_image)

        tossButton.setOnClickListener {

            val animation : Animation = AnimationUtils.loadAnimation(this, R.anim.coin_effect)
            coinImage.startAnimation(animation)

            // 동전 사운드
            val mediaPlayer : MediaPlayer? = MediaPlayer.create(this, R.raw.coin_effect)
            mediaPlayer?.start()

            // 동전을 던지고 난 후의 결과 값
            // 동전이 내려오고 난 후에 결과값을 보여주기 위해 2.2초로 설정
            Timer().schedule(500) {
                state = (0..1).random()

                when(state) {
                    0 -> coinImage.setImageResource(R.drawable.coin_back)
                    1 -> coinImage.setImageResource(R.drawable.coin_front)
                }

            }
        }

    }
}