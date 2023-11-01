package com.xmolnia.vicQuiz.activities


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.xmolnia.vicQuiz.Base
import com.xmolnia.vicQuiz.DEFAULT
import com.xmolnia.vicQuiz.DISNEYLAND2_CORRECT_ANSWERS
import com.xmolnia.vicQuiz.DISNEYLAND_CORRECT_ANSWERS
import com.xmolnia.vicQuiz.FREE_FIRE_CORRECT_ANSWERS
import com.xmolnia.vicQuiz.GAMER_NAME
import com.xmolnia.vicQuiz.IMAGE_PICK_CODE
import com.xmolnia.vicQuiz.MARVEL_MOVIE_CORRECT_ANSWERS
import com.xmolnia.vicQuiz.MARVEL_PIXEL_CORRECT_ANSWERS
import com.xmolnia.vicQuiz.MARVEL_TEST_CORRECT_ANSWERS
import com.xmolnia.vicQuiz.PART1_CORRECT_ANSWERS
import com.xmolnia.vicQuiz.PART2_CORRECT_ANSWERS
import com.xmolnia.vicQuiz.PERMISSION_CODE
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.ZOMBIE_TEST_CORRECT_ANSWERS
import com.xmolnia.vicQuiz.adapters.CustomListViewAdapter
import com.xmolnia.vicQuiz.databinding.ProfileBinding


class Profile : Fragment(R.layout.profile) {

    private var handler = Handler()
    lateinit var scaleUp: Animation
    lateinit var scaleDown: Animation
    private lateinit var profile: ProfileBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profile = ProfileBinding.bind(view)
        Base.initial(requireContext())


        if (Base.getProfilePhoto() != DEFAULT) {
            profile.userImage.setImageURI(Base.getProfilePhoto().toUri())
        } else {
            profile.userImage.setImageResource(R.drawable.avatar)
        }



        scaleUp = AnimationUtils.loadAnimation(requireContext(), R.anim.button_scale_up)
        scaleDown = AnimationUtils.loadAnimation(requireContext(), R.anim.button_scale_up)

        profile.btnRating.setOnClickListener {
            val i = Intent(requireContext(), Rating::class.java)
            startActivity(i)
        }

        runnable.run()


//        activity?.runOnUiThread {
//            Blurry.with(context).radius(100).sampling(30).color(R.color.main_color).async()
//                .animate(500).onto(profile.layout2)
//
//        }
//
//        Blurry.with(context).radius(10)
//            .sampling(8)
//            .color(Color.argb(66, 255, 255, 0))
//            .async().onto(profile.layout2)

        //
        //



//        val blurredBitmap: Bitmap= GaussianBlur.with(requireContext()).render(R.drawable.alok)
//
//        profile.profileImage.setImageBitmap(blurredBitmap)
//        profile.layout2.background.
//        GaussianBlur.with(requireContext()).

//        Blurry.with(context).capture(View).into(profile.profileImage)

        //getGamerName in shared


        profile.userName.text = Base.getValues(GAMER_NAME)
        val marvelMovie = Base.getAnswer(MARVEL_MOVIE_CORRECT_ANSWERS)
        val marvelTest = Base.getAnswer(MARVEL_TEST_CORRECT_ANSWERS)
        val marvelPixel = Base.getAnswer(MARVEL_PIXEL_CORRECT_ANSWERS)
        val freeFire = Base.getAnswer(FREE_FIRE_CORRECT_ANSWERS)
        val zombieMovie = Base.getAnswer(ZOMBIE_TEST_CORRECT_ANSWERS)
        val disneyLand = Base.getAnswer(DISNEYLAND_CORRECT_ANSWERS)
        val disneyLand2 = Base.getAnswer(DISNEYLAND2_CORRECT_ANSWERS)
        val partOne = Base.getAnswer(PART1_CORRECT_ANSWERS)
        val partTwo = Base.getAnswer(PART2_CORRECT_ANSWERS)
        val title = ArrayList<String>()
        val description = ArrayList<String>()

        if (marvelTest != 0) {
            title.add(getString(R.string.marvel_movie_question))
            description.add("$marvelTest/20")
        }

        if (marvelMovie != 0) {
            title.add(getString(R.string.test_marvel_movie))
            description.add("$marvelMovie/20")
        }
        if (marvelPixel != 0) {
            title.add(getString(R.string.test_marvel_pixel))
            description.add("$marvelPixel/20")
        }


        if (freeFire != 0) {
            title.add(getString(R.string.test_free_fire))
            description.add("$freeFire/20")
        }


        if (zombieMovie != 0) {
            title.add(getString(R.string.test_zombies_test))
            description.add("$zombieMovie/10")
        }

        if (disneyLand != 0) {
            title.add(getString(R.string.disney_test))
            description.add("$disneyLand/20")
        }

        if (disneyLand2 != 0) {
            title.add(getString(R.string.disney_test))
            description.add("$disneyLand2/20")
        }

        if (partOne != 0) {
            title.add(getString(R.string.trend1_test))
            description.add("$partOne/20")
        }

        if (partTwo != 0) {
            title.add(getString(R.string.trend2_test))
            description.add("$partTwo/20")
        }


        val customAdapter = CustomListViewAdapter(requireContext(), title, description)
        profile.resultList.adapter = customAdapter








        profile.uploadUserImage.setOnClickListener {

            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) ==
                PackageManager.PERMISSION_DENIED
            ) {
                //show popup to request runtime permission
                requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    PERMISSION_CODE
                )
            } else {
                //permission already granted
                pickImageFromGallery()
            }

        }

    }

    private fun pickImageFromGallery() {

        //Intent to pick image
        val intent = Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {

                    //permission from popup granted
                    pickImageFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(activity, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {

            Base.putProfilePhoto(data?.data.toString())

            profile.userImage.setImageURI(data?.data)

        }
    }

    private var runnable = object : Runnable {
        override fun run() {


            profile.btnRating.startAnimation(scaleUp)

            profile.btnRating.startAnimation(scaleDown)

            handler.postDelayed(this, 700)
        }
    }

    init {
        Log.d("CREATED", "Profile : $this")

    }
}