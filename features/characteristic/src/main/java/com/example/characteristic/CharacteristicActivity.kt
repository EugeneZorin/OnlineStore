package com.example.characteristic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.characteristic.databinding.CharacteristicActivityBinding

class CharacteristicActivity : AppCompatActivity() {

    private lateinit var binding: CharacteristicActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CharacteristicActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}