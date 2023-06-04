package com.example.bn

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bn.api.SessionManager
import kotlinx.coroutines.launch

class SignUpTabFragment : Fragment() {
    private lateinit var master: Button
    private lateinit var client: Button
    private val sessionManager = SessionManager.getInstance()

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

        val registrationRequest = RegistrationRequestDto()
          registrationRequest.name =sessionManager.getUser().name
          registrationRequest.surname = sessionManager.getUser().surname
          registrationRequest.phoneNumber = "123456789"
          registrationRequest.country = sessionManager.getUser().country
          registrationRequest.region =sessionManager.getUser().region
          registrationRequest.city = sessionManager.getUser().city
          registrationRequest.role ="MASTER"


        master.setOnClickListener {
            registrationRequest.role ="MASTER"
            Toast.makeText(requireContext(), "MASTER role granted", Toast.LENGTH_SHORT).show()

            sessionManager.getCoroutineScope().launch {
                performSetMe(sessionManager.getToken(),registrationRequest)
            }
        }
        client.setOnClickListener {
            registrationRequest.role ="CLIENT"
            Toast.makeText(requireContext(), "CLIENT role granted", Toast.LENGTH_SHORT).show()

            sessionManager.getCoroutineScope().launch {
                performSetMe(sessionManager.getToken(),registrationRequest)
            }
        }
        return root
    }
    private suspend fun performSetMe(token: String, user:RegistrationRequestDto) {
        try {
            val response = sessionManager.getUserApi().setRole(user,token)
            if (response.isSuccessful) {
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(requireContext(), "Grant", Toast.LENGTH_SHORT).show()
            } else {
                val errorBody = response.errorBody()?.string()
                Toast.makeText(requireContext(), "Error: $errorBody", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }}
}