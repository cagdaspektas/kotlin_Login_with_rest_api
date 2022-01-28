package com.example.kotlin_login_with_api

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_login_with_api.databinding.ActivityLayoutBinding



import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivityLayoutBinding

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        binding = ActivityLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
      binding.  buttonLogin.setOnClickListener {

            val email =binding. editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            if(email.isEmpty()){
                binding.  editTextEmail.error = "Email required"
                binding.  editTextEmail.requestFocus()
                return@setOnClickListener
            }


            if(password.isEmpty()){
                binding.  editTextPassword.error = "Password required"
                binding.  editTextPassword.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.login(email, password)
                .enqueue(object: Callback<LoginResponseModel>{
                    override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<LoginResponseModel>, response: Response<LoginResponseModel>) {
                    /*    if(!response.body()?.error!!){

                            SharedPrefManager.getInstance(applicationContext).saveUser(response.body()?.user!!)

                            val intent = Intent(applicationContext, ProfileActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                            startActivity(intent)


                        }else{*/
                            Toast.makeText(applicationContext, response.body()?.token, Toast.LENGTH_LONG).show()
                        //}

                    }
                })

        }
    }

   /* override fun onStart() {
        super.onStart()

        if(SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, ProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }*/
}