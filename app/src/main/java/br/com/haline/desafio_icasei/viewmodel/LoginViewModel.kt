package br.com.haline.desafio_icasei.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginViewModel: ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    fun signInWithGoogle(token: String, onResult: (Boolean) -> Unit) {
        val firebaseCredencial = GoogleAuthProvider.getCredential(token, null)
        auth.signInWithCredential(firebaseCredencial)
            .addOnCompleteListener { task ->
                onResult(task.isSuccessful)
            }
    }
}