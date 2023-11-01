package com.xmolnia.vicQuiz.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.xmolnia.vicQuiz.cleanarch.RecyclerViewAdapter
import com.xmolnia.vicQuiz.databinding.TestsBinding
import com.xmolnia.vicQuiz.models.Recyclers


class Tests : Fragment() {

    var imagesInternet = arrayListOf<String>()
    var titlesInternet = arrayListOf<String>()
    var detailsInternet = arrayListOf<String>()

    private lateinit var binding: TestsBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter =
            RecyclerViewAdapter(requireContext(), titlesInternet, detailsInternet, imagesInternet)
        binding.recyclerView.adapter = adapter


        val myRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Recycler")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                titlesInternet.clear()
                detailsInternet.clear()
                imagesInternet.clear()
                for (d in dataSnapshot.children.flatMap { it.children }) {
                    d.getValue(Recyclers::class.java)?.let {
                        if (it.title != "") {
                            titlesInternet.add(it.title)
                            detailsInternet.add(it.detail)
                            imagesInternet.add(it.imageUri)
                        }

                    }

                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })


    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding= TestsBinding.inflate(inflater,container,false)
       return binding.root
    }
    init {
        Log.d("CREATED", "Tests : $this")

    }

}


