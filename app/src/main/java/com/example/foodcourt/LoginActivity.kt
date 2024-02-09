package com.example.foodcourt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.foodcourt.api.AuthService
import com.example.foodcourt.api.TokenResponse
import com.example.foodcourt.api.UserLoginData
import com.example.foodcourt.databinding.ActivityLoginBinding
import com.example.foodcourt.databinding.ActivitySignoutBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var authService: AuthService

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.donthave.setOnClickListener{
            val intent = Intent(this, SignoutActivity::class.java)
            startActivity(intent)
        }
        val retrofit = Retrofit.Builder()
            .baseUrl("http://127.0.0.1:5000//")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        authService = retrofit.create(AuthService::class.java)

        binding.loginButton.setOnClickListener{
            val username = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()

            login("username", "password")

            val intent = Intent(this, ChooseLocationActivity::class.java)
            startActivity(intent)
        }




        
        

    }

    private fun login(username: String, password: String) {
        val userData = UserLoginData(username, password)
        authService.login(userData).enqueue(object : Callback<TokenResponse> {
            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                if (response.isSuccessful) {
                    val token = response.body()?.token
                    Toast.makeText(this@LoginActivity, "Login successful", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    // Login failed
                    Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                // Login request failed
                Toast.makeText(this@LoginActivity, "Login request failed", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

}