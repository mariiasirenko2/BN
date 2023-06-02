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
    private lateinit var master: Button
    private lateinit var client: Button
    private var message = "BeautyNote App 1.0 support login only via Facebook"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val root = inflater.inflate(R.layout.sign_up_tab_fragment, container, false) as ViewGroup
        master = root.findViewById(R.id.master)
        client = root.findViewById(R.id.client)

        master.translationX = 800f
        client.translationX = 800f

        master.alpha = 0f
        client.alpha = 0f

        master.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(300).start()
        client.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(700)
            .start()

        master.setOnClickListener {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }
        client.setOnClickListener {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)

        }
        return root
    }
}