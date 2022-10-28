package com.berkedursunoglu.kapimda.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.HashMap


class FirebaseModule {

    val firebase :FirebaseAuth = FirebaseAuth.getInstance()
    val fireStore : FirebaseFirestore = FirebaseFirestore.getInstance()

    fun register(email:String,password:String): Task<AuthResult> {
        return firebase.createUserWithEmailAndPassword(email,password)
    }

    fun registerWithUsername(userName: String,uid: String,email: String,password: String): Task<Void> {
        val user: MutableMap<String, Any> = HashMap()
        user["userName"] = userName
        user["uid"] = uid
        user["e-mail"] = email
        user["password"] = password
        return fireStore.collection("users").document(uid).set(user)
    }

    fun login(email:String,password:String): Task<AuthResult> {
       return firebase.signInWithEmailAndPassword(email,password)
    }


}