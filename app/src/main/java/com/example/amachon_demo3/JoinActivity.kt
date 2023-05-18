package com.example.amachon_demo3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.amachon_demo3.data.*
import com.example.amachon_demo3.network.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        var emailCheck = false
        var nicknameCheck = false

        val emailCheckBtn = findViewById<Button>(R.id.EmailAddressCertification)
        emailCheckBtn.setOnClickListener {
            val password = findViewById<EditText>(R.id.passWord).text.toString()
            val nickName = findViewById<EditText>(R.id.Nickname).text.toString()
            val email = findViewById<EditText>(R.id.EmailAddress).text.toString()

            Client.retrofitService.logUp(EmailDto(email)).enqueue(
                object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                    }
                }
            )
        }

        val emailCodeCheckBtn = findViewById<Button>(R.id.EmailCodeCheckBtn)
        emailCodeCheckBtn.setOnClickListener {
            val emailCode = findViewById<EditText>(R.id.EmailCodeCheck).text.toString()
            val email = findViewById<EditText>(R.id.EmailAddress).text.toString()
            Client.retrofitService.codeCheck(EmailCheckDto(email, emailCode)).enqueue(
                object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                    }

                }
            )
        }

        val nickNameCheckBtn = findViewById<Button>(R.id.nickNameCheckBtn)
        nickNameCheckBtn.setOnClickListener {
            val nickname = findViewById<EditText>(R.id.Nickname).text.toString()
            Client.retrofitService.nickNameCheck(NicknameDto(nickname)).enqueue(
                object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                    }

                }
            )
        }

        val nextBtn = findViewById<Button>(R.id.nextBtn)

        nextBtn.setOnClickListener {
            if (emailCheck && nicknameCheck) {
                val password = findViewById<EditText>(R.id.passWord).text.toString()
                val nickName = findViewById<EditText>(R.id.Nickname).text.toString()
                val email = findViewById<EditText>(R.id.EmailAddress).text.toString()

                Client.retrofitService.join(JoinData(email,password,nickName)).enqueue(
                    object : Callback<JoinResponseDto> {
                        override fun onResponse(call: Call<JoinResponseDto>, response: Response<JoinResponseDto>) {
                            Client.value = response.body()?.result?.accessToken.toString()
                        }

                        override fun onFailure(call: Call<JoinResponseDto>, t: Throwable) {
                        }

                    }
                )
                startActivity(Intent(this, JoinTagSetActivity::class.java))
            }
            else {
                Toast.makeText(this, "인증을 해주세요", Toast.LENGTH_LONG).show()
            }
        }
    }
}
