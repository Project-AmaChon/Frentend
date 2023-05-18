package com.example.amachon_demo3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.amachon_demo3.data.ChangeRegionTagDto
import com.example.amachon_demo3.data.ChangeTechTagDto
import com.example.amachon_demo3.data.changeDto
import com.example.amachon_demo3.databinding.ActivityJoinTagSetBinding
import com.example.amachon_demo3.network.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinTagSetActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_tag_set)

        val regionBtn = findViewById<ImageView>(R.id.regionBtn)
        regionBtn.setOnClickListener {
            var List : MutableList<String> = mutableListOf("A", "B")

            val CGDto = ChangeRegionTagDto("LA")
            val CTDto = ChangeTechTagDto(List)

            val changeDto = changeDto(CTDto, CGDto)

            Client.retrofitService.change(changeDto).enqueue(
                object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        when (response!!.code()) {
                            200 -> {
                                Toast.makeText(this@JoinTagSetActivity, "200", Toast.LENGTH_LONG).show()
                            }
                            400 -> {
                                Toast.makeText(this@JoinTagSetActivity, "400", Toast.LENGTH_LONG).show()
                            }
                            500 -> {
                                Toast.makeText(this@JoinTagSetActivity, "500", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                    }

                }
            )
        }

        val nextBtn = findViewById<Button>(R.id.nextMainBtn)
        nextBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}