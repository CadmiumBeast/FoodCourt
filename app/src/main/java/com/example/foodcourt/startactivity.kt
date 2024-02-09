package com.example.foodcourt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodcourt.databinding.ActivityStartactivityBinding

class startactivity : AppCompatActivity() {
private val binding: ActivityStartactivityBinding by lazy{
    ActivityStartactivityBinding.inflate(layoutInflater)
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.nextButton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent);
        }
    }
}