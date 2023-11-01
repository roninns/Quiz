package com.xmolnia.vicQuiz.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.xmolnia.vicQuiz.Base
import com.xmolnia.vicQuiz.BaseActivity
import com.xmolnia.vicQuiz.DESCRIPTION
import com.xmolnia.vicQuiz.GAMER_NAME
import com.xmolnia.vicQuiz.NetWorkConnection
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.RATING
import com.xmolnia.vicQuiz.TITLE
import com.xmolnia.vicQuiz.adapters.RatingAdapter
import com.xmolnia.vicQuiz.cleanarch.MainActivity
import com.xmolnia.vicQuiz.databinding.ActivityRatingBinding
import com.xmolnia.vicQuiz.models.All
import com.xmolnia.vicQuiz.models.Pair
import com.xmolnia.vicQuiz.models.User


class Rating : BaseActivity() {

    lateinit var adapter: RatingAdapter
    lateinit var name: ArrayList<String>
    private lateinit var listAllInfo: ArrayList<User?>
    lateinit var title: ArrayList<String>
    lateinit var temp: ArrayList<Pair>
    private lateinit var temps: ArrayList<Pair>
    private lateinit var description: ArrayList<String>
    lateinit var all: ArrayList<All>
    lateinit var rating: ArrayList<String>
    private lateinit var ratingBinding: ActivityRatingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ratingBinding = ActivityRatingBinding.inflate(layoutInflater)
        setContentView(ratingBinding.root)

        title = ArrayList()
        temp = ArrayList()
        temps = ArrayList()
        description = ArrayList()
        name = ArrayList()
        all = ArrayList()
        listAllInfo = ArrayList()
        rating = ArrayList()


        adapter = RatingAdapter(this, name, rating)
        ratingBinding.listView.adapter = adapter

        val netWorkConnection = NetWorkConnection(applicationContext)
        netWorkConnection.observe(this) { isConnected ->

            if (isConnected) {
                ratingBinding.layoutDisconnected.visibility = View.GONE
                getData(this)

            } else {
                ratingBinding.listView.visibility = View.GONE
                ratingBinding.ShimmerLayout.visibility = View.GONE
                ratingBinding.layoutDisconnected.visibility = View.VISIBLE

            }
        }
        setOnClickItem(this)
    }

    private fun getData(context: Context) {

        /**Shimmer check list item with support Handler*/
        Handler(Looper.getMainLooper()).postDelayed({

            if (name.isEmpty()) {
                ratingBinding.ShimmerLayout.visibility = View.VISIBLE
                ratingBinding.ShimmerLayout.startShimmer()
            } else {
                ratingBinding.ShimmerLayout.visibility = View.GONE
            }

        }, 300)



        val vListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                name.clear()
                rating.clear()
                all.clear()



                for (d in dataSnapshot.children) {


                    d.getValue(User::class.java)?.let {
                        all.add(
                            All(
                                it.name,
                                arrayListOf(
                                    it.marvelTest,
                                    it.marvelMovie,
                                    it.marvelPixel,
                                    it.freeFire,
                                    it.zombieMovie,
                                    it.disneyLand,
                                    it.partOne,
                                    it.partTwo,
                                    it.disneyLand2
                                )
                            )
                        )
                    }
                }

                all.sortByDescending { it.rating.sum() }

                for (v in all) {
                    if (v.rating.toIntArray().sum() > 0) {
                        name.add(v.name); rating.add(v.rating.sum().toString())
                        ratingBinding.listView.visibility = View.VISIBLE
                    }
                }


                adapter.notifyDataSetChanged()


            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    context,
                    "${getString(R.string.onCanceled)} ${databaseError.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        Base.mDataBase.addValueEventListener(vListener)
        ratingBinding.ShimmerLayout.visibility = View.GONE

    }


    private fun setOnClickItem(context: Context) {


        ratingBinding.listView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->

                title.clear()
                temp.clear()
                description.clear()
                temps.clear()
                temp.add(Pair(getString(R.string.test_marvel_neon), all[position].rating[0]))
                temp.add(Pair(getString(R.string.test_marvel_movie), all[position].rating[1]))
                temp.add(Pair(getString(R.string.test_marvel_pixel), all[position].rating[2]))
                temp.add(Pair(getString(R.string.test_free_fire), all[position].rating[3]))
                temp.add(Pair(getString(R.string.test_zombies_test), all[position].rating[4]))
                temp.add(Pair(getString(R.string.disney_test), all[position].rating[5]))
                temp.add(Pair(getString(R.string.trend1_test), all[position].rating[6]))
                temp.add(Pair(getString(R.string.trend2_test), all[position].rating[7]))
                temp.add(Pair(getString(R.string.disney_test2), all[position].rating[8]))


                temp.sortByDescending { it.rating }
                for (i in temp) {
                    if (i.rating > 0) {
                        temps.add(Pair(i.name, i.rating))
                    }
                }

                for (i in temps) {
                    description.add(String.format("%d", i.rating))
                    title.add(i.name)
                }

                val intent = Intent(context, ShowActivity::class.java)
                intent.putExtra(TITLE, title)
                intent.putExtra(DESCRIPTION, description)
                intent.putExtra(GAMER_NAME, all[position].name)
                intent.putExtra(RATING, position + 1)

                startActivity(intent)
                finish()

            }
    }

    fun backClick(view: View) {
        val intents = Intent(this, MainActivity::class.java)
        startActivity(intents)
        finish()
        ratingBinding.ShimmerLayout.startShimmer()
        ratingBinding.ShimmerLayout.visibility= View.VISIBLE
    }

}