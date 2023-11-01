package com.xmolnia.vicQuiz.activities


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.xmolnia.vicQuiz.*
import com.xmolnia.vicQuiz.cleanarch.MainActivity


import com.xmolnia.vicQuiz.databinding.ActivitySignBinding
import com.xmolnia.vicQuiz.models.User


class Sign : BaseActivity() {
    private lateinit var sign: ActivitySignBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    private companion object {
        const val RC_SIGN_IN = 100
        private const val TAG = "GOOGLE_SIGN_IN_TAG"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sign = ActivitySignBinding.inflate(layoutInflater)
        setContentView(sign.root)

        Base.initial(this)


//         CONFIGURE GOOGLE SIGN IN
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = Firebase.auth
        sign.btnGoogle.setOnClickListener {

                    sign.signRulesTitle.text = getString(R.string.sign_in_title)
                    sign.loading.visibility = View.VISIBLE
                    Log.d(TAG, "onCreate: begin Google Sign in")
                    val signInIntent: Intent = googleSignInClient.signInIntent
                    startActivityForResult(signInIntent, RC_SIGN_IN)

        }


        sign.linkSignUp.setOnClickListener {
            sign.signRules.text = getString(R.string.sign_up_rules)
            sign.signRulesTitle.text = getString(R.string.sign_up_title)
            sign.btnSignUp.visibility = View.VISIBLE
            sign.usernameBox.visibility = View.GONE
            sign.btnSignIn.visibility = View.GONE
            sign.usernameBox.visibility= View.VISIBLE
            sign.btnGoogle.visibility=View.GONE
            sign.tvForget.visibility = View.VISIBLE
            sign.tvSignHaveQue.visibility = View.GONE

        }


        sign.tvForget.setOnClickListener {
            sign.signRulesTitle.text = getString(R.string.sign_forget_title)
            sign.signRules.text = getString(R.string.sign_forget_rules)
            sign.tvForget.visibility = View.GONE
            sign.passwordBox.visibility = View.GONE
            sign.linkSignUp.visibility= View.GONE
            sign.btnGoogle.visibility= View.GONE
            sign.usernameBox.visibility = View.GONE
            sign.btnSignUp.visibility= View.GONE
            sign.btnForgetPassword.visibility = View.VISIBLE
            sign.btnSignIn.visibility = View.GONE

        }


    }


    fun signUp(view: View) {

        sign.loading.visibility = View.VISIBLE

        when {

            TextUtils.isEmpty(sign.email.text.toString().trim { it <= ' ' }) -> {
                sign.loading.visibility = View.GONE
                Toast.makeText(
                    applicationContext,
                    getString(R.string.email_error),
                    Toast.LENGTH_SHORT
                ).show()
                sign.loading.visibility = View.GONE

            }
            TextUtils.isEmpty(sign.password.text.toString().trim { it <= ' ' }) -> {
                sign.loading.visibility = View.GONE
                Toast.makeText(
                    applicationContext,
                    getString(R.string.password_error),
                    Toast.LENGTH_SHORT
                ).show()
                sign.loading.visibility = View.GONE

            }

            sign.password.length() < 6 -> {
                sign.password.error = getString(R.string.password_error)
                sign.loading.visibility = View.GONE


            }

            TextUtils.isEmpty(sign.username.text.toString().trim { it <= ' ' }) -> {
                sign.loading.visibility = View.GONE
                Toast.makeText(
                    applicationContext,
                    getString(R.string.please_enter_your_name),
                    Toast.LENGTH_SHORT
                ).show()
                sign.loading.visibility = View.GONE

            }

            else -> {
                sign.loading.visibility = View.VISIBLE
                val email: String = sign.email.text.toString().trim { it <= ' ' }
                val password: String = sign.password.text.toString().trim { it <= ' ' }


                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->

                        //усли ренистрация прошла успешна
                        if (task.isSuccessful) {


                            /*Тут мы сначала сохрогяем имя человекав в базе(класса), затем сохроняем то что мы в аккаунте отныне,
                            после делам контейнер с id пользователья  и отправляемяс в новый активити*/
                            val userId = auth.currentUser?.uid.toString()
                            Base.putValues(USER_ID, userId)

                            Base.putValues(GAMER_NAME, sign.username.text.toString())
                            Base.putValues("log", "log")
                            Base.mDataBase.child(userId)
                                .setValue(User(sign.username.text.toString()))
                            Toast.makeText(
                                applicationContext,
                                getString(R.string.succesful_sign_up),
                                Toast.LENGTH_LONG
                            ).show()


                            startActivity(Intent(this, MainActivity::class.java))
                            finish()

                        } else {
                            Toast.makeText(
                                applicationContext,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                            sign.loading.visibility = View.GONE
                        }
                    }
            }
        }
    }


    fun signIn(view: View) {
        sign.loading.visibility = View.VISIBLE

        when {

            TextUtils.isEmpty(sign.email.text.toString().trim { it <= ' ' }) -> {
                sign.loading.visibility = View.GONE
                Toast.makeText(
                    applicationContext,
                    getString(R.string.email_error),
                    Toast.LENGTH_SHORT
                ).show()
                sign.loading.visibility = View.GONE

            }
            TextUtils.isEmpty(sign.password.text.toString().trim { it <= ' ' }) -> {
                sign.loading.visibility = View.GONE
                Toast.makeText(
                    applicationContext,
                    getString(R.string.password_error),
                    Toast.LENGTH_SHORT
                ).show()
                sign.loading.visibility = View.GONE

            }

            sign.password.length() < 6 -> {
                sign.password.error = getString(R.string.password_error)
                sign.loading.visibility = View.GONE


            }


            else -> {
                sign.loading.visibility = View.VISIBLE
                val email: String = sign.email.text.toString().trim { it <= ' ' }
                val password: String = sign.password.text.toString().trim { it <= ' ' }

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->

                        if (task.isSuccessful) {

                            val userId = auth.currentUser?.uid.toString()
                            Base.putValues(USER_ID, userId)
                            Base.putValues("log", "log")


                            Base.mDataBase.child(userId).get().addOnSuccessListener {


                                if (it.value != null) {
                                    val user = it.getValue(User::class.java)
                                    user?.let {
                                        Base.putValues(GAMER_NAME, user.name)
                                        Base.putAnswer(MARVEL_TEST_CORRECT_ANSWERS, user.marvelTest)
                                        Base.putAnswer(
                                            MARVEL_MOVIE_CORRECT_ANSWERS,
                                            user.marvelMovie
                                        )
                                        Base.putAnswer(
                                            MARVEL_PIXEL_CORRECT_ANSWERS,
                                            user.marvelPixel
                                        )
                                        Base.putAnswer(FREE_FIRE_CORRECT_ANSWERS, user.freeFire)
                                        Base.putAnswer(
                                            ZOMBIE_TEST_CORRECT_ANSWERS,
                                            user.zombieMovie
                                        )
                                        Base.putAnswer(DISNEYLAND_CORRECT_ANSWERS, user.disneyLand)
                                    }

                                }
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()

                            }


                        } else {
                            sign.loading.visibility = View.GONE
                            Toast.makeText(
                                applicationContext,
                                getString(R.string.failded_sign_in),
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
            }
        }
    }


    fun forgetPassword(view: View) {
        val email = sign.email.text.toString().trim { it <= ' ' }

        if (email.isEmpty()) {
            Toast.makeText(this, getString(R.string.please_enter_your_name), Toast.LENGTH_SHORT)
                .show()
        } else {
            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->


                if (task.isSuccessful) {
                    sign.passwordBox.visibility = View.VISIBLE
                    sign.btnForgetPassword.visibility = View.GONE
                    sign.btnSignIn.visibility = View.VISIBLE
                    Toast.makeText(this, "email sent successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onStart() {
        super.onStart()
        val cUser: FirebaseUser? = auth.currentUser

        if (cUser != null) {
            Toast.makeText(this, "User not null", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.user_is_null), Toast.LENGTH_SHORT).show()
        }
    }


/*=======================GOOGLE===========================*/


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception

            if (task.isSuccessful) {
                Log.d(TAG, "onActivityResult: Google Sign in intent result")

                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
//                    Log.d("Google", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    sign.loading.visibility = View.GONE
                    // Google Sign In failed, update UI appropriately
                    Log.d(TAG, "OnActivityResult: ${e.message}+catch")
                }
            } else {
                sign.loading.visibility = View.GONE
                Log.d(TAG, "OnActivityResult: $exception else")
            }
        }
    }


    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                Log.d(TAG, "firebaseAuthWithGoogle: begin FireBase auth with google account")

                if (task.isSuccessful) {

                    // Sign in success, update UI with the signed-in user's information
                    val userId = auth.currentUser?.uid.toString()
                    Base.putValues(USER_ID, userId)
                    Log.d(TAG, "firebaseAuthWithGoogle: uid:$userId")


                    Base.mDataBase.child(userId).get().addOnSuccessListener {
                        if (it.value != null) {
                            Base.putValues("log", "log")
                            val user = it.getValue(User::class.java)
                            user?.let {
                                Base.putValues(GAMER_NAME, user.name)
                                Base.putAnswer(MARVEL_TEST_CORRECT_ANSWERS, user.marvelTest)
                                Base.putAnswer(MARVEL_MOVIE_CORRECT_ANSWERS, user.marvelMovie)
                                Base.putAnswer(MARVEL_PIXEL_CORRECT_ANSWERS, user.marvelPixel)
                                Base.putAnswer(FREE_FIRE_CORRECT_ANSWERS, user.freeFire)
                                Base.putAnswer(ZOMBIE_TEST_CORRECT_ANSWERS, user.zombieMovie)
                                Base.putAnswer(DISNEYLAND_CORRECT_ANSWERS, user.disneyLand)
                            }
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            sign.loading.visibility = View.GONE
                            sign.passwordBox.visibility = View.GONE
                            sign.emailBox.visibility = View.GONE
                            sign.tvSignHaveQue.visibility = View.GONE
                            sign.btnGoogle.visibility = View.GONE
                            sign.btnSignIn.visibility = View.GONE
                            sign.btnSignUp.visibility = View.GONE
                            sign.btnContinue.visibility = View.VISIBLE
                            sign.signRules.text = getString(R.string.please_enter_your_name)



                            sign.btnContinue.setOnClickListener {

                                if (TextUtils.isEmpty(
                                        sign.username.text.toString().trim { uN -> uN <= ' ' })
                                ) {
                                    sign.loading.visibility = View.GONE
                                    Toast.makeText(
                                        applicationContext,
                                        getString(R.string.please_enter_your_name),
                                        Toast.LENGTH_SHORT
                                    ).show()

                                } else {

                                    sign.loading.visibility = View.VISIBLE
                                    val username: String =
                                        sign.username.text.toString().trim { uN -> uN <= ' ' }
                                    Base.putValues("log", "log")
                                    Base.putValues(GAMER_NAME, username)

                                    Base.mDataBase.child(userId).setValue(User(username))
                                    Toast.makeText(
                                        applicationContext,
                                        getString(R.string.succesful_sign_up),
                                        Toast.LENGTH_LONG
                                    ).show()
                                    startActivity(Intent(this, MainActivity::class.java))
                                    finish()
                                }

                            }
                        }
                    }
                } else {
                    sign.loading.visibility = View.GONE
                }

            }
    }



}
