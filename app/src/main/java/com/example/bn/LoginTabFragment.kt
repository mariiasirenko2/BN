package com.example.bn

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bn.R

class LoginTabFragment : Fragment() {
    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var forgetPassword: TextView
    private lateinit var loginButton: Button
    private var message = "BeautyNote App 1.0 support login only via Facebook"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val root = inflater.inflate(R.layout.login_tab_fragment, container, false) as ViewGroup
        email = root.findViewById(R.id.email)
        pass = root.findViewById(R.id.password)
        forgetPassword = root.findViewById(R.id.forget_password)
        loginButton = root.findViewById(R.id.button)

        email.translationX = 800f
        pass.translationX = 800f
        forgetPassword.translationX = 800f
        loginButton.translationX = 800f

        email.alpha = 0f
        pass.alpha = 0f
        forgetPassword.alpha = 0f
        loginButton.alpha = 0f

        email.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(300).start()
        pass.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(500).start()
        forgetPassword.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(500)
            .start()
        loginButton.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(700).start()

        loginButton.setOnClickListener {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }

        return root
    }
}