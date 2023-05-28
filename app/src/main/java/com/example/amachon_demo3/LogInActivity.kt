package com.example.amachon_demo3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.amachon_demo3.data.LoginDto
import com.example.amachon_demo3.data.LoginResponseDto
import com.example.amachon_demo3.network.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val joinBtn = findViewById<TextView>(R.id.joinBtn)
        joinBtn.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener {
            val email = findViewById<EditText>(R.id.email).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()
            var isSuccess = false
            var token = ""
            var memberId = 0
            Client.retrofitService.login(LoginDto(email, password)).enqueue(object : Callback<LoginResponseDto> {
                override fun onResponse(
                    call: Call<LoginResponseDto>,
                    response: Response<LoginResponseDto>
                ) {
                    isSuccess = response.body()?.isSuccess!!
                    if (isSuccess) {
                        Client.memberId = response.body()?.result?.memberId!!
                        token = response.body()?.result?.accessToken!!
                        memberId = response.body()?.result?.memberId!!
                    }
                }

                override fun onFailure(call: Call<LoginResponseDto>, t: Throwable) {
                }

            })



            Handler().postDelayed({
                if (isSuccess) {
                    Client.value = token
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
                }
            }, 1000)
        }
    }
}