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
                        Log.d("emailsend", response.message().toString())
                        when (response!!.code()) {
                            200 -> {
                                Toast.makeText(this@JoinActivity, "200", Toast.LENGTH_LONG).show()
                            }
                            400 -> {
                                Toast.makeText(this@JoinActivity, "400", Toast.LENGTH_LONG).show()
                            }
                            500 -> {
                                Toast.makeText(this@JoinActivity, "500", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                    }
                }
            )


            /*
            Client.retrofitService.getTags()?.enqueue(object : Callback<TokenDto>{
                override fun onResponse(call: Call<TokenDto>, response: Response<TokenDto>) {
                    if (response.isSuccessful){
                        Log.d("tlqkf3", response.body()?.result?.get(0)?.children.toString())
                    }else{

                    }
                }

                override fun onFailure(call: Call<TokenDto>, t: Throwable) {
                }
            })
             */
        }

        val emailCodeCheckBtn = findViewById<Button>(R.id.EmailCodeCheckBtn)
        emailCodeCheckBtn.setOnClickListener {
            val emailCode = findViewById<EditText>(R.id.EmailCodeCheck).text.toString()
            val email = findViewById<EditText>(R.id.EmailAddress).text.toString()
            Client.retrofitService.codeCheck(EmailCheckDto(email, emailCode)).enqueue(
                object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Log.d("emailcodecheck", response.message().toString())
                        when (response!!.code()) {
                            200 -> {
                                Toast.makeText(this@JoinActivity, "200", Toast.LENGTH_LONG).show()
                                emailCheck = true
                            }
                            400 -> {
                                Toast.makeText(this@JoinActivity, "400", Toast.LENGTH_LONG).show()
                            }
                            500 -> {
                                Toast.makeText(this@JoinActivity, "500", Toast.LENGTH_LONG).show()
                            }
                        }
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
                        Log.d("nicknamecheck", response.toString())
                        when (response!!.code()) {
                            200 -> {
                                Toast.makeText(this@JoinActivity, "200", Toast.LENGTH_LONG).show()
                                nicknameCheck = true
                            }
                            400 -> {
                                Toast.makeText(this@JoinActivity, "400", Toast.LENGTH_LONG).show()
                            }
                            500 -> {
                                Toast.makeText(this@JoinActivity, "500", Toast.LENGTH_LONG).show()
                            }
                        }
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
                    object : Callback<LoginDto> {
                        override fun onResponse(call: Call<LoginDto>, response: Response<LoginDto>) {
                            Client.value = response.body()?.result?.accessToken.toString()
                            when (response!!.code()) {
                                200 -> {
                                    Toast.makeText(this@JoinActivity, "200", Toast.LENGTH_LONG).show()
                                }
                                400 -> {
                                    Toast.makeText(this@JoinActivity, "400", Toast.LENGTH_LONG).show()
                                }
                                500 -> {
                                    Toast.makeText(this@JoinActivity, "500", Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        override fun onFailure(call: Call<LoginDto>, t: Throwable) {
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
