package com.example.bn

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class SignUpTabFragment : Fragment() {
    private lateinit var email: EditText
    private lateinit var singUpButton: Button
    private var message = "BeautyNote App 1.0 support login only via Facebook"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val root = inflater.inflate(R.layout.sign_up_tab_fragment, container, false) as ViewGroup
        email = root.findViewById(R.id.email)
        singUpButton = root.findViewById(R.id.button)

        email.translationX = 800f
        singUpButton.translationX = 800f

        email.alpha = 0f
        singUpButton.alpha = 0f

        email.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(300).start()
        singUpButton.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(700)
            .start()

        singUpButton.setOnClickListener {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }
        return root
    }
}