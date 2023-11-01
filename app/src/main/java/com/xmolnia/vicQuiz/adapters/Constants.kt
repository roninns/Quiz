package com.xmolnia.vicQuiz.adapters

import com.xmolnia.vicQuiz.BaseActivity
import com.xmolnia.vicQuiz.R
import com.xmolnia.vicQuiz.cleanarch.TestPreviewModel
import com.xmolnia.vicQuiz.models.ImageU
import com.xmolnia.vicQuiz.models.Question


/**
 * Created by Osman Boy on 11.03.2021.
 **/

object Constants : BaseActivity() {
    const val girlGif =
        "https://firebasestorage.googleapis.com/v0/b/vicquiz.appspot.com/o/gif%2FgirlGif.gif?alt=media&token=c0cf5df5-366d-4e49-b377-f789ae96da73"

    const val manGif =
        "https://firebasestorage.googleapis.com/v0/b/vicquiz.appspot.com/o/gif%2FmanGif.gif?alt=media&token=3132218d-b875-44dc-992e-350811c7a513"

    const val part1 = "https://firebasestorage.googleapis.com/v0/b/vicquiz.appspot.com/o/part1%2F"

    const val part2 = "https://firebasestorage.googleapis.com/v0/b/vicquiz.appspot.com/o/part2%2F"

    const val disney1 =
        "https://firebasestorage.googleapis.com/v0/b/vicquiz.appspot.com/o/disney2%2F"

    const val imageU =
        "https://firebasestorage.googleapis.com/v0/b/vicquiz.appspot.com/o/imageQuiz%2F"

    fun getMarvelQuestion(): ArrayList<Question> {
        val marvelTestQuestionsList = ArrayList<Question>()
        val que1 = Question(
            1,
            R.string.question1,
            R.drawable.marvel_test_question_1,
            R.string.spider_man,
            R.string.hulk,
            R.string.wolverine,
            R.string.dead_pool,
            3
        )
        marvelTestQuestionsList.add(que1)


        val que2 = Question(
            1,
            R.string.question2, R.drawable.marvel_test_question_2,
            R.string.adamantium,
            R.string.vibranium,
            R.string.titan,
            R.string.kriptonit,
            2
        )
        marvelTestQuestionsList.add(que2)


        val que3 = Question(
            1,
            R.string.marvel_test_question_3, R.drawable.marvel_test_question_3,
            R.string.vibranium,
            R.string.titan,
            R.string.kriptonit,
            R.string.adamantium,
            4
        )
        marvelTestQuestionsList.add(que3)


        val que4 = Question(
            1,
            R.string.marvel_test_question_4, R.drawable.marvel_test_question_4,
            R.string.tchala,
            R.string.primat,
            R.string.tchaka,
            R.string.njobu,
            1
        )
        marvelTestQuestionsList.add(que4)


        val que5 = Question(
            1,
            R.string.marvel_test_question_5, R.drawable.marvel_test_question_5,
            R.string.bog_lzhi,
            R.string.bog_obmana,
            R.string.black_prince,
            R.string.lord_elphie,
            2
        )
        marvelTestQuestionsList.add(que5)


        val que6 = Question(
            1,
            R.string.marvel_test_question_6, R.drawable.marvel_test_question_6,
            R.string.deadpool,
            R.string.dead_pool,
            R.string.mister_pool,
            R.string.spider_man, 1
        )
        marvelTestQuestionsList.add(que6)


        val que7 = Question(
            1,
            R.string.marvel_test_question_7, R.drawable.marvel_test_question_7,
            R.string.octopusDoctor,
            R.string.scrool,
            R.string.gidra,
            R.string.tanos,
            4
        )
        marvelTestQuestionsList.add(que7)


        val que8 = Question(
            1,
            R.string.marvel_test_question_8, R.drawable.marvel_test_question_8,
            R.string.escalibur,
            R.string.god_grom,
            R.string.miolner,
            R.string.asgard,
            3
        )
        marvelTestQuestionsList.add(que8)


        val que9 = Question(
            1,
            R.string.marvel_test_question_9, R.drawable.marvel_test_question_9,
            R.string.kamen_power,
            R.string.kamen_razuma,
            R.string.kamen_dushi,
            R.string.kamen_time,
            4
        )
        marvelTestQuestionsList.add(que9)


        val que10 = Question(
            1,
            R.string.marvel_test_question_10, R.drawable.marvel_test_question_10,
            R.string.experiment,
            R.string.gamma,
            R.string.mutagen,
            R.string.X,
            2
        )
        marvelTestQuestionsList.add(que10)

        val que11 = Question(
            1,
            R.string.marvel_test_question_11, R.drawable.marvel_test_question_11,
            R.string.nic_fiury,
            R.string.bruce_banner,
            R.string.clinton_barton,
            R.string.agent_kolson,
            3
        )
        marvelTestQuestionsList.add(que11)


        val que12 = Question(
            1,
            R.string.marvel_test_question_12, R.drawable.marvel_test_question_12,
            R.string.red_shot,
            R.string.zemo,
            R.string.gidra,
            R.string.baron,
            1
        )
        marvelTestQuestionsList.add(que12)

        val que13 = Question(
            1,
            R.string.marvel_test_question_13, R.drawable.marvel_test_question_13,
            R.string.midgard,
            R.string.cripton,
            R.string.cybertron,
            R.string.titan,
            4
        )
        marvelTestQuestionsList.add(que13)


        val que14 = Question(
            1,
            R.string.marvel_test_question_14, R.drawable.marvel_test_question_14,
            R.string.luchnik,
            R.string.ronin,
            R.string.arrow,
            R.string.samurai,
            2
        )
        marvelTestQuestionsList.add(que14)

        val que15 = Question(
            1,
            R.string.marvel_test_question_15, R.drawable.marvel_test_question_15,
            R.string.magice,
            R.string.mutation,
            R.string.tehnology,
            R.string.nauka,
            2
        )
        marvelTestQuestionsList.add(que15)

        val que16 = Question(
            1,
            R.string.marvel_test_question_16, R.drawable.marvel_test_question_16,
            R.string.kamen_power,
            R.string.kamen_dushi,
            R.string.kamen_time,
            R.string.kamen_razuma,
            4
        )
        marvelTestQuestionsList.add(que16)

        val que17 = Question(
            1,
            R.string.marvel_test_question_17, R.drawable.marvel_test_question_17,
            R.string.angel,
            R.string.demon,
            R.string.hranitel,
            R.string.mutant,
            1
        )
        marvelTestQuestionsList.add(que17)

        val que18 = Question(
            1,
            R.string.marvel_test_question_18, R.drawable.marvel_test_question_18,
            R.string.muravev,
            R.string.vakums,
            R.string.malekuls,
            R.string.atom,
            4
        )
        marvelTestQuestionsList.add(que18)

        val que19 = Question(
            1,
            R.string.marvel_test_question_19, R.drawable.marvel_test_question_19,
            R.string.tohni_stark,
            R.string.bruce_banner,
            R.string.muravei,
            R.string.vizhn,
            3
        )
        marvelTestQuestionsList.add(que19)


        val que20 = Question(
            1,
            R.string.marvel_test_question_20, R.drawable.marvel_test_question_20,
            R.string.day_vampire,
            R.string.vampire,
            R.string.polukrovka_vampire,
            R.string.mutant_vampire,
            1
        )
        marvelTestQuestionsList.add(que20)



        return marvelTestQuestionsList
    }

    fun getMarvelMovieQuestion(): ArrayList<Question> {
        val marvelMovieQuestionsList = ArrayList<Question>()

        val que1 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_1,
            R.drawable.marvel_movie_show_1,
            R.string.muravei,
            R.string.tor_rognarek,
            R.string.morbius,
            R.string.perviy_mstitel,
            4
        )
        marvelMovieQuestionsList.add(que1)


        val que2 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_2,
            R.drawable.marvel_movie_show_2,
            R.string.muravei,
            R.string.avengers,
            R.string.iron_man,
            R.string.hulk_neveroyatniy,
            4
        )
        marvelMovieQuestionsList.add(que2)


        val que3 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_3,
            R.drawable.marvel_movie_show_3,
            R.string.era_altron,
            R.string.perviy_mstitel_protivostoyanie,
            R.string.perviy_mstitel_drugaya_voina,
            R.string.avengers,
            3
        )
        marvelMovieQuestionsList.add(que3)


        val que4 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_4,
            R.drawable.marvel_movie_show_4,
            R.string.tor_2,
            R.string.morbius,
            R.string.tor,
            R.string.avengers,
            3
        )
        marvelMovieQuestionsList.add(que4)


        val que5 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_5,
            R.drawable.marvel_movie_show_5,
            R.string.era_altron,
            R.string.tor_rognarek,
            R.string.avengers,
            R.string.perviy_mstitel_protivostoyanie,
            1
        )
        marvelMovieQuestionsList.add(que5)


        val que6 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_6,
            R.drawable.marvel_movie_show_6,
            R.string.hulk_neveroyatniy,
            R.string.tor_2,
            R.string.doctor_strange,
            R.string.perviy_mstitel,
            2
        )
        marvelMovieQuestionsList.add(que6)


        val que7 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_7,
            R.drawable.marvel_movie_show_7,
            R.string.iron_man_2,
            R.string.perviy_mstitel_protivostoyanie,
            R.string.capitan_marvel,
            R.string.spider_man_back_home,
            2
        )
        marvelMovieQuestionsList.add(que7)


        val que8 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_8,
            R.drawable.marvel_movie_show_8,
            R.string.spider_man_back_home,
            R.string.iron_man_3,
            R.string.avengers,
            R.string.perviy_mstitel_protivostoyanie,
            1
        )
        marvelMovieQuestionsList.add(que8)


        val que9 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_9,
            R.drawable.marvel_movie_show_9,
            R.string.galaxy_strage,
            R.string.muravei,
            R.string.hulk_neveroyatniy,
            R.string.doctor_strange,
            2
        )
        marvelMovieQuestionsList.add(que9)


        val que10 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_10,
            R.drawable.marvel_movie_show_10,
            R.string.iron_man,
            R.string.iron_man_3,
            R.string.avengers,
            R.string.iron_man_2,
            1
        )
        marvelMovieQuestionsList.add(que10)


        val que11 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_11,
            R.drawable.marvel_movie_show_11,
            R.string.doctor_strange,
            R.string.perviy_mstitel_protivostoyanie,
            R.string.black_panter,
            R.string.tor_2,
            3
        )
        marvelMovieQuestionsList.add(que11)


        val que12 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_12,
            R.drawable.marvel_movie_show_12,
            R.string.avengers_voina_beskonnechnosti,
            R.string.galaxy_strage,
            R.string.morbius,
            R.string.galaxy_strage_2,
            2
        )
        marvelMovieQuestionsList.add(que12)


        val que13 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_13,
            R.drawable.marvel_movie_show_13,
            R.string.capitan_marvel,
            R.string.era_altron,
            R.string.avengers,
            R.string.perviy_mstitel_protivostoyanie,
            3
        )
        marvelMovieQuestionsList.add(que13)


        val que14 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_14,
            R.drawable.marvel_movie_show_14,
            R.string.era_altron,
            R.string.tor_rognarek,
            R.string.galaxy_strage_2,
            R.string.doctor_strange,
            4
        )
        marvelMovieQuestionsList.add(que14)


        val que15 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_15,
            R.drawable.marvel_movie_show_15,
            R.string.galaxy_strage_2,
            R.string.muravei,
            R.string.tor_rognarek,
            R.string.morbius,
            3
        )
        marvelMovieQuestionsList.add(que15)


        val que16 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_16,
            R.drawable.marvel_movie_show_16,
            R.string.iron_man_2,
            R.string.iron_man,
            R.string.iron_man_3,
            R.string.capitan_marvel,
            1
        )
        marvelMovieQuestionsList.add(que16)


        val que17 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_17,
            R.drawable.marvel_movie_show_17,
            R.string.tor,
            R.string.tor_rognarek,
            R.string.black_panter,
            R.string.iron_man_3,
            4
        )
        marvelMovieQuestionsList.add(que17)


        val que18 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_18,
            R.drawable.marvel_movie_show_18,
            R.string.galaxy_strage,
            R.string.galaxy_strage_2,
            R.string.doctor_strange,
            R.string.tor_rognarek,
            2
        )
        marvelMovieQuestionsList.add(que18)


        val que19 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_19,
            R.drawable.marvel_movie_show_19,
            R.string.perviy_mstitel,
            R.string.black_panter,
            R.string.capitan_marvel,
            R.string.tor,
            3
        )
        marvelMovieQuestionsList.add(que19)


        val que20 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_movie_question_20,
            R.drawable.marvel_movie_show_20,
            R.string.morbius,
            R.string.muravei_and_osa,
            R.string.capitan_marvel,
            R.string.muravei,
            1
        )
        marvelMovieQuestionsList.add(que20)

        return marvelMovieQuestionsList
    }

    fun getMarvelPixelQuestion(): ArrayList<Question> {
        val marvelPixelQuestionsList = ArrayList<Question>()

        val que1 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_1,
            R.drawable.marvel_pixel_show_1,
            R.string.spider_man_back_home,
            R.string.iron_man,
            R.string.morbius,
            R.string.perviy_mstitel,
            4
        )
        marvelPixelQuestionsList.add(que1)


        val que2 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_2,
            R.drawable.marvel_pixel_show_2,
            R.string.muravei,
            R.string.capitan_marvel,
            R.string.black_panter,
            R.string.hulk_neveroyatniy,
            3
        )
        marvelPixelQuestionsList.add(que2)


        val que3 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_3,
            R.drawable.marvel_pixel_show_3,
            R.string.avengers,
            R.string.perviy_mstitel_protivostoyanie,
            R.string.logan,
            R.string.iron_man,
            4
        )
        marvelPixelQuestionsList.add(que3)


        val que4 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_4,
            R.drawable.marvel_pixel_show_4,
            R.string.tor_2,
            R.string.tor_rognarek,
            R.string.x_men_apokalypse,
            R.string.capitan_marvel,
            2
        )
        marvelPixelQuestionsList.add(que4)


        val que5 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_5,
            R.drawable.marvel_pixel_show_5,
            R.string.tor,
            R.string.tor_rognarek,
            R.string.avengers,
            R.string.x_men_new_mutant,
            1
        )
        marvelPixelQuestionsList.add(que5)


        val que6 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_6,
            R.drawable.marvel_pixel_show_6,
            R.string.hulk_neveroyatniy,
            R.string.dedpul,
            R.string.iron_man_2,
            R.string.perviy_mstitel,
            3
        )
        marvelPixelQuestionsList.add(que6)


        val que7 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_7,
            R.drawable.marvel_pixel_show_7,
            R.string.avengers,
            R.string.perviy_mstitel_protivostoyanie,
            R.string.perviy_mstitel,
            R.string.dedpul,
            2
        )
        marvelPixelQuestionsList.add(que7)


        val que8 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_8,
            R.drawable.marvel_pixel_show_8,
            R.string.doctor_strange,
            R.string.iron_man_3,
            R.string.avengers,
            R.string.logan,
            1
        )
        marvelPixelQuestionsList.add(que8)


        val que9 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_9,
            R.drawable.marvel_pixel_show_9,
            R.string.galaxy_strage,
            R.string.dedpul,
            R.string.hulk_neveroyatniy,
            R.string.spider_man_back_home,
            4
        )
        marvelPixelQuestionsList.add(que9)


        val que10 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_10,
            R.drawable.marvel_pixel_show_10,
            R.string.iron_man,
            R.string.iron_man_3,
            R.string.avengers,
            R.string.iron_man_2,
            3
        )
        marvelPixelQuestionsList.add(que10)


        val que11 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_11,
            R.drawable.marvel_pixel_show_11,
            R.string.muravei,
            R.string.muravei_and_osa,
            R.string.black_panter,
            R.string.tor_2,
            1
        )
        marvelPixelQuestionsList.add(que11)


        val que12 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_12,
            R.drawable.marvel_pixel_show_12,
            R.string.logan,
            R.string.morbius,
            R.string.muravei,
            R.string.muravei_and_osa,
            4
        )
        marvelPixelQuestionsList.add(que12)


        val que13 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_13,
            R.drawable.marvel_pixel_show_13,
            R.string.perviy_mstitel_drugaya_voina,
            R.string.era_altron,
            R.string.galaxy_strage,
            R.string.perviy_mstitel_protivostoyanie,
            3
        )
        marvelPixelQuestionsList.add(que13)


        val que14 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_14,
            R.drawable.marvel_pixel_show_14,
            R.string.era_altron,
            R.string.hulk_neveroyatniy,
            R.string.galaxy_strage_2,
            R.string.doctor_strange,
            2
        )
        marvelPixelQuestionsList.add(que14)


        val que15 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_15,
            R.drawable.marvel_pixel_show_15,
            R.string.capitan_marvel,
            R.string.logan,
            R.string.x_men_apokalypse,
            R.string.morbius,
            1
        )
        marvelPixelQuestionsList.add(que15)


        val que16 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_16,
            R.drawable.marvel_pixel_show_16,
            R.string.black_panter,
            R.string.spider_man,
            R.string.spider_man_back_home,
            R.string.spider_into_spider_verse,
            4
        )
        marvelPixelQuestionsList.add(que16)


        val que17 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_17,
            R.drawable.marvel_pixel_show_17,
            R.string.morbius,
            R.string.muravei,
            R.string.logan,
            R.string.iron_man_2,
            3
        )
        marvelPixelQuestionsList.add(que17)


        val que18 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_18,
            R.drawable.marvel_pixel_show_18,
            R.string.perviy_mstitel_drugaya_voina,
            R.string.dedpul,
            R.string.galaxy_strage,
            R.string.perviy_mstitel_protivostoyanie,
            2
        )
        marvelPixelQuestionsList.add(que18)


        val que19 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_19,
            R.drawable.marvel_pixel_show_19,
            R.string.x_men_new_mutant,
            R.string.x_men_apokalypse,
            R.string.avengers,
            R.string.morbius,
            1
        )
        marvelPixelQuestionsList.add(que19)


        val que20 = Question(
            2,
            R.string.marvel_movie_question,
            R.drawable.marvel_pixel_20,
            R.drawable.marvel_pixel_show_20,
            R.string.galaxy_strage,
            R.string.muravei,
            R.string.x_men_apokalypse,
            R.string.spider_man_back_home,
            3
        )
        marvelPixelQuestionsList.add(que20)



        return marvelPixelQuestionsList


    }

    fun getFreeFireQuestion(): ArrayList<Question> {
        val freeFireQuestionsList = ArrayList<Question>()

        val que1 = Question(
            2,
            R.drawable.shiro,
            R.string.miguel,
            R.string.misha,
            R.string.shiro,
            R.string.moco,
            3
        )
        freeFireQuestionsList.add(que1)


        val que2 = Question(
            2,
            R.drawable.chronos,
            R.string.jai,
            R.string.shiro,
            R.string.alok,
            R.string.chronos,
            4
        )
        freeFireQuestionsList.add(que2)

        val que3 = Question(
            2,
            R.drawable.dasha,
            R.string.laura,
            R.string.dasha,
            R.string.steffie,
            R.string.kelly,
            2
        )

        freeFireQuestionsList.add(que3)

        val que4 = Question(
            2,
            R.drawable.k,
            R.string.luqueta,
            R.string.jai,
            R.string.k,
            R.string.monkey_king,
            3
        )

        freeFireQuestionsList.add(que4)

        val que5 = Question(
            2,
            R.drawable.jai,
            R.string.jai,
            R.string.luqueta,
            R.string.maxim,
            R.string.alok,
            1
        )

        freeFireQuestionsList.add(que5)

        val que6 = Question(
            2,
            R.drawable.maxim,
            R.string.andrew,
            R.string.maxim,
            R.string.raphael,
            R.string.wolfrahh,
            2
        )

        freeFireQuestionsList.add(que6)

        val que7 = Question(
            2,
            R.drawable.paloma,
            R.string.kelly,
            R.string.moco,
            R.string.laura,
            R.string.paloma,
            4
        )

        freeFireQuestionsList.add(que7)

        val que8 = Question(
            2,
            R.drawable.luqueta,
            R.string.miguel,
            R.string.jai,
            R.string.luqueta,
            R.string.k,
            3
        )

        freeFireQuestionsList.add(que8)

        val que9 = Question(
            2,
            R.drawable.kelly,
            R.string.moco,
            R.string.laura,
            R.string.kelly,
            R.string.steffie,
            3
        )

        freeFireQuestionsList.add(que9)

        val que10 = Question(
            2,
            R.drawable.hayato,
            R.string.hayato,
            R.string.alok,
            R.string.luqueta,
            R.string.andrew,
            1
        )

        freeFireQuestionsList.add(que10)

        val que11 = Question(
            2,
            R.drawable.moco,
            R.string.laura,
            R.string.steffie,
            R.string.moco,
            R.string.kelly,
            3
        )

        freeFireQuestionsList.add(que11)

        val que12 = Question(
            2,
            R.drawable.steffie,
            R.string.misha,
            R.string.steffie,
            R.string.paloma,
            R.string.kelly,
            2
        )

        freeFireQuestionsList.add(que12)

        val que13 = Question(
            2,
            R.drawable.misha,
            R.string.wolfrahh,
            R.string.laura,
            R.string.kelly,
            R.string.misha,
            4
        )

        freeFireQuestionsList.add(que13)

        val que14 = Question(
            2,
            R.drawable.andrew,
            R.string.miguel,
            R.string.andrew,
            R.string.maxim,
            R.string.misha,
            2
        )

        freeFireQuestionsList.add(que14)

        val que15 = Question(
            2,
            R.drawable.laura,
            R.string.laura,
            R.string.moco,
            R.string.steffie,
            R.string.paloma,
            1
        )

        freeFireQuestionsList.add(que15)

        val que16 = Question(
            2,
            R.drawable.rafael,
            R.string.andrew,
            R.string.monkey_king,
            R.string.raphael,
            R.string.hayato,
            3
        )

        freeFireQuestionsList.add(que16)


        val que17 = Question(
            2,
            R.drawable.alok,
            R.string.wolfrahh,
            R.string.chronos,
            R.string.shiro,
            R.string.alok,
            4
        )

        freeFireQuestionsList.add(que17)


        val que18 = Question(
            2,
            R.drawable.wolfrahh,
            R.string.andrew,
            R.string.wolfrahh,
            R.string.luqueta,
            R.string.monkey_king,
            2
        )

        freeFireQuestionsList.add(que18)


        val que19 = Question(
            2,
            R.drawable.monkey_king,
            R.string.monkey_king,
            R.string.moco,
            R.string.misha,
            R.string.miguel,
            1
        )

        freeFireQuestionsList.add(que19)


        val que20 = Question(
            2,
            R.drawable.miguel,
            R.string.maxim,
            R.string.misha,
            R.string.miguel,
            R.string.moco,
            3
        )

        freeFireQuestionsList.add(que20)

        return freeFireQuestionsList

    }

    fun getZombieQuestion(): ArrayList<Question> {
        val zombiesQuestionsList = ArrayList<Question>()

        val que1 = Question(
            2,
            R.drawable.zombie_1,
            R.string.train_to_pusan,
            R.string.santa_clarita_diet,
            R.string.new_era_Z,
            R.string.machete_kills,
            3
        )
        zombiesQuestionsList.add(que1)


        val que2 = Question(
            2,
            R.drawable.zombie_2,
            R.string.pride_prejudice_zombies,
            R.string.machete_kills,
            R.string.from_dusk_to_dawn,
            R.string.dawn_of_the_dead,
            4
        )
        zombiesQuestionsList.add(que2)

        val que3 = Question(
            2,
            R.drawable.zombie_3,
            R.string.game_of_thrones,
            R.string.pet_cemetery,
            R.string.pride_prejudice_zombies,
            R.string.from_dusk_to_dawn,
            1
        )

        zombiesQuestionsList.add(que3)

        val que4 = Question(
            2,
            R.drawable.zombie_4,
            R.string.pet_cemetery,
            R.string.grind_house,
            R.string.para_norman,
            R.string.resident_evil,
            4
        )

        zombiesQuestionsList.add(que4)

        val que5 = Question(
            2,
            R.drawable.zombie_5,
            R.string.corpse_bride,
            R.string.scouts_guide_to_the_zombie,
            R.string.welcome_to_Zombie_land,
            R.string._28_days_later,
            3
        )

        zombiesQuestionsList.add(que5)

        val que6 = Question(
            2,
            R.drawable.zombie_6,
            R.string._28_days_later,
            R.string.shaun_of_the_dead,
            R.string.really_History,
            R.string.night_of_the_living_dead,
            2
        )

        zombiesQuestionsList.add(que6)

        val que7 = Question(
            2,
            R.drawable.zombie_7,
            R.string.the_walking_dead,
            R.string.nosferatu,
            R.string.caroline_in_the_country_nightmares,
            R.string.night_of_the_living_dead,
            4
        )

        zombiesQuestionsList.add(que7)

        val que8 = Question(
            2,
            R.drawable.zombie_8,
            R.string.the_walking_dead,
            R.string.im_legend,
            R.string.monster_on_vacation,
            R.string.corpse_bride,
            1
        )

        zombiesQuestionsList.add(que8)

        val que9 = Question(
            2,
            R.drawable.zombie_9,
            R.string.the_walking_dead,
            R.string.night_of_the_living_dead,
            R.string.warm_bodies,
            R.string.twilight,
            3
        )

        zombiesQuestionsList.add(que9)

        val que10 = Question(
            2,
            R.drawable.zombie_10,
            R.string.ash_vs_evil_dead,
            R.string.pet_cemetery,
            R.string.death_becomes_her,
            R.string.im_legend,
            1
        )

        zombiesQuestionsList.add(que10)

        return zombiesQuestionsList
    }

    fun getDisneyQuestion(): ArrayList<Question> {
        val disneyQuestionsList = ArrayList<Question>()

        val que1 = Question(
            2,
            R.drawable.dusha,
            R.string.up0,
            R.string.inside_ut,
            R.string.dusha,
            R.string.zolushka,
            3
        )
        disneyQuestionsList.add(que1)


        val que2 = Question(
            2,
            R.drawable.zootopia,
            R.string.snejnie_psi,
            R.string.monster_corparation,
            R.string.monster_on_vacation,
            R.string.zoo_topia,
            4
        )
        disneyQuestionsList.add(que2)

        val que3 = Question(
            2,
            R.drawable.cars_2,
            R.string.cars_2,
            R.string.cars,
            R.string.cars_3,
            R.string.heroes_city,
            1
        )

        disneyQuestionsList.add(que3)

        val que4 = Question(
            2,
            R.drawable.super_familly,
            R.string.aivan,
            R.string.aremis_faul,
            R.string.toy_stories,
            R.string.super_family,
            4
        )

        disneyQuestionsList.add(que4)

        val que5 = Question(
            2,
            R.drawable.holodnoe_serdce_2,
            R.string.holodnoe_serdce,
            R.string.dnevniki_princessi,
            R.string.holodnoe_serdce_2,
            R.string.hrabroe_serdce,
            3
        )

        disneyQuestionsList.add(que5)

        val que6 = Question(
            2,
            R.drawable.vpered,
            R.string.inside_ut,
            R.string.vpered,
            R.string.heroes_city,
            R.string.up0,
            2
        )

        disneyQuestionsList.add(que6)

        val que7 = Question(
            2,
            R.drawable.raya_and_last_dragon,
            R.string.rapunzel,
            R.string.planeta_spkrovish,
            R.string.mulan,
            R.string.rai_and_last_dragon,
            4
        )

        disneyQuestionsList.add(que7)


        val que8 = Question(
            2,
            R.drawable.heroes_city,
            R.string.heroes_city,
            R.string.vpered,
            R.string.cars_3,
            R.string.ralf,
            1
        )

        disneyQuestionsList.add(que8)

        val que9 = Question(
            2,
            R.drawable.toy_stories_1,
            R.string.taina_koko,
            R.string.zoo_topia,
            R.string.toy_stories,
            R.string.alita_battle_angel,
            3
        )

        disneyQuestionsList.add(que9)

        val que10 = Question(
            2,
            R.drawable.ralf,
            R.string.ralf,
            R.string.up0,
            R.string.ralph_vs_internet,
            R.string.idel_toy,
            1
        )

        disneyQuestionsList.add(que10)

        val que11 = Question(
            2,
            R.drawable.rapuncel,
            R.string.rapunzel,
            R.string.dusha,
            R.string.moana,
            R.string.hrabroe_serdce,
            1
        )

        disneyQuestionsList.add(que11)

        val que12 = Question(
            2,
            R.string.walle,

            R.string.ralph_vs_internet,
            R.string.toy_stories,
            R.string.ralf,
            2
        )

        disneyQuestionsList.add(que12)

        val que13 = Question(
            2,
            R.drawable.moana,
            R.string.ratatouille,
            R.string.rapunzel,
            R.string.moana,
            R.string.astra_boy,
            3
        )
        disneyQuestionsList.add(que13)


        val que14 = Question(
            2,
            R.drawable.alica_zazerkale,
            R.string.zolushka,
            R.string.inside_ut,
            R.string.monster_corparation,
            R.string.alica_zazerkale,
            4
        )

        disneyQuestionsList.add(que14)


        val que15 = Question(
            2,
            R.drawable.valli_i,
            R.string.nasledniki,
            R.string.dusha,
            R.string.walle,
            R.string.shelkunchik_i_4_korolevstv,
            3
        )
        disneyQuestionsList.add(que15)

        val que16 = Question(
            2,
            R.drawable.golovolomka1,
            R.string.hrabroe_serdce,
            R.string.inside_ut,
            R.string.ratatouille,
            R.string.monster_on_vacation,
            2
        )

        disneyQuestionsList.add(que16)


        val que17 = Question(
            2,
            R.drawable.top,
            R.string.up0,
            R.string.dusha,
            R.string.monster_on_vacation,
            R.string.ratatouille,
            1
        )

        disneyQuestionsList.add(que17)


        val que18 = Question(
            2,
            R.drawable.monster_coporation,
            R.string.nasledniki,
            R.string.monster_corparation,
            R.string.monster_on_vacation,
            R.string.hotel_transelvania,
            2
        )

        disneyQuestionsList.add(que18)

        val que19 = Question(
            2,
            R.drawable.ratatui,
            R.string.rapunzel,
            R.string.ralf,
            R.string.ratatouille,
            R.string.hrabroe_serdce,
            3
        )

        disneyQuestionsList.add(que19)

        val que20 = Question(
            2,
            R.drawable.hrabroe_serdcem,
            R.string.hranitel,
            R.string.holodnoe_serdce_2,
            R.string.holodnoe_serdce,
            R.string.hrabroe_serdce,
            4
        )

        disneyQuestionsList.add(que20)


        return disneyQuestionsList
    }

    fun getTrend1Question(): ArrayList<Question> {
        val tempList = ArrayList<Question>()
        val que1 = Question(
            6,
            "21_bridges.jpeg?alt=media&token=da9779b9-264e-48f0-87b7-570d8b07db21",
            R.string.black_panter,
            R.string.twenty_one_bridges,
            R.string.project_power,
            R.string.avengers_infinity_War,
            2
        )
        tempList.add(que1)


        val que2 = Question(
            6,
            "6_undergroud.jpeg?alt=media&token=932c5e8d-0772-43a1-a9e2-cbb86b737aef",
            R.string.hitmans_bodyguard,
            R.string.hitman_wife_bodyguard,
            R.string.dedpul,
            R.string.six6_undergroud,
            4
        )
        tempList.add(que2)


        val que3 = Question(
            6,
            "ALITA_BATTLE_ANGEL.jpeg?alt=media&token=0e7ba685-9d5c-4654-a6af-36b33b7ca683",
            R.string.mortal_engines,
            R.string.hunter_monster,
            R.string.alita_battle_angel,
            R.string.project_power,
            3
        )
        tempList.add(que3)


        val que4 = Question(
            6,
            "aquaman.jpeg?alt=media&token=6635d1ec-58f8-4958-9556-61a0f03457fd",
            R.string.aquaman,
            R.string.zack_snider_justice_league,
            R.string.gentelmens,
            R.string.avengers_infinity_War,
            1
        )
        tempList.add(que4)


        val que5 = Question(
            6,
            "avengers_infinity_war.webp?alt=media&token=94ae7f97-4f15-4e08-9218-69f9ab524091",
            R.string.avengers_final,
            R.string.avengers_infinity_War,
            R.string.avengers,
            R.string.perviy_mstitel_drugaya_voina,
            2
        )
        tempList.add(que5)


        val que6 = Question(
            6,
            "bad_boys_for_life.jpg?alt=media&token=23a3f47d-f310-424a-a72d-e850ac27e766",
            R.string.im_legend,
            R.string.gemini,
            R.string.bad_boys_for_life,
            R.string.project_power,
            3
        )
        tempList.add(que6)


        val que7 = Question(
            6,
            "call_of_the_wild.jpg?alt=media&token=3938102a-26b1-41f1-80d7-646d98b83526",
            R.string.bad_boys_for_life,
            R.string.mortal_engines,
            R.string.alita_battle_angel,
            R.string.the_call_of_the_wild,
            4
        )
        tempList.add(que7)


        val que8 = Question(
            6,
            "fast_furious-presents_hobbs_shaw.jpeg?alt=media&token=c2f3d0bc-84de-4f2c-baea-f0086241fa52",
            R.string.transporter,
            R.string.mechanic,
            R.string.fast_hobbs_shaw,
            R.string.morbius,
            3
        )
        tempList.add(que8)


        val que9 = Question(
            6,
            "gemini.jpg?alt=media&token=981f17e7-4534-4895-b59c-82e5961ff97e",
            R.string.im_legend,
            R.string.gemini,
            R.string.bad_boys_for_life,
            R.string.twenty_one_bridges,
            2
        )
        tempList.add(que9)


        val que10 = Question(
            6,
            "2Fgodzilla_vs_kong.jpeg?alt=media&token=91227c3a-b0b1-4969-9993-75a5fabbf481",
            R.string.godzilla_vs_kong,
            R.string.kong_skull_island,
            R.string.godzilla,
            R.string.godzilla_king_monstres,
            1
        )
        tempList.add(que10)


        val que11 = Question(
            6,
            "justice_league_zack_snyder.jpg?alt=media&token=14f05fa7-6f36-4498-b5d0-4c022f0939c0",
            R.string.black_panter,
            R.string.zack_snider_justice_league,
            R.string.aquaman,
            R.string.suicide_squad,
            2
        )
        tempList.add(que11)


        val que12 = Question(
            6,
            "mortaal_engines.jpg?alt=media&token=806ad2e4-cdcc-4c14-b803-d1612500e3eb",
            R.string.project_power,
            R.string.morbius,
            R.string.mortal_engines,
            R.string.bloodshot,
            3
        )
        tempList.add(que12)


        val que13 = Question(
            6,
            "old_guard.jpeg?alt=media&token=ff3f0c47-d6b8-4f71-bed1-5931b5534a79",
            R.string.x_men_new_mutant,
            R.string.night_of_the_living_dead,
            R.string.morbius,
            R.string.the_old_guard,
            4
        )
        tempList.add(que13)


        val que14 = Question(
            6,
            "project_power.jpeg?alt=media&token=dff2b5e5-3401-4733-b009-85e554473641",
            R.string.bad_boys_for_life,
            R.string.twenty_one_bridges,
            R.string.project_power,
            R.string.terminator_dark_fate,
            3
        )
        tempList.add(que14)


        val que15 = Question(
            6,
            "sonnic_in_movie.jpg?alt=media&token=936787ae-c0e4-43d1-9692-c993f44e11c8",
            R.string.yes_man,
            R.string.sonic_in_movie,
            R.string.bruce_almighty,
            R.string.avengers_infinity_War,
            2
        )
        tempList.add(que15)


        val que16 = Question(
            6,
            "2Fterminator_dark_fate.jpg?alt=media&token=c05a19d7-7c3d-48de-80e6-aa898cbe1a03",
            R.string.terminator_dark_fate,
            R.string.twenty_one_bridges,
            R.string.project_power,
            R.string.six6_undergroud,
            1
        )
        tempList.add(que16)


        val que17 = Question(
            6,
            "gentelmens.jpeg?alt=media&token=f1175fee-3da7-48d1-812a-3e0211e9016f",
            R.string.morbius,
            R.string.gentelmens,
            R.string.deadpool,
            R.string.venom,
            2
        )
        tempList.add(que17)


        val que18 = Question(
            6,
            "tyler_extraction.jpeg?alt=media&token=7b96adc8-6832-4ae2-bcb4-f25e0d09a2ad",
            R.string.x_men_new_mutant,
            R.string.logan,
            R.string.extraction,
            R.string.x_men_apokalypse,
            3
        )
        tempList.add(que18)


        val que19 = Question(
            6,
            "venom.jpg?alt=media&token=e0b5a469-1a79-4b0f-ae7b-eb1fd994049b",
            R.string.welcome_to_Zombie_land,
            R.string.pride_prejudice_zombies,
            R.string.scouts_guide_to_the_zombie,
            R.string.venom,
            4
        )
        tempList.add(que19)


        val que20 = Question(
            6,
            "wonder_woman_1984.jpg?alt=media&token=2ccb13ad-5eec-4dfa-a82b-7b882f7ff89c",
            R.string.zack_snider_justice_league,
            R.string.suicide_squad,
            R.string.wonder_woman_1984,
            R.string.aquaman,
            3
        )
        tempList.add(que20)



        return tempList

    }

    fun getTrend2Question(): ArrayList<Question> {

        val trend2QuestionsList = ArrayList<Question>()

        val que1 = Question(
            6,
            "aladdin.jpeg?alt=media&token=9cbf63ce-a840-4a16-a03c-db076660444f",
            R.string.zoo_topia,
            R.string.aladin,
            R.string.pet_cemetery,
            R.string.hunter_monster,
            2
        )
        trend2QuestionsList.add(que1)


        val que2 = Question(
            6,
            "avengers_final.webp?alt=media&token=3b62b7e2-5704-4900-9b77-f4cbec2bc022",
            R.string.iron_man,
            R.string.avengers_infinity_War,
            R.string.avengers,
            R.string.avengers_final,
            4
        )
        trend2QuestionsList.add(que2)


        val que3 = Question(
            6,
            "blodshoot.jpg?alt=media&token=87e65bbc-0c2f-47e8-9024-eecb049a2eb6",
            R.string.furious_7,
            R.string.riddick,
            R.string.bloodshot,
            R.string.project_power,
            3
        )
        trend2QuestionsList.add(que3)


        val que4 = Question(
            6,
            "boss_level.jpg?alt=media&token=82328202-eb05-4858-8ff4-d0c22dccf3cc",
            R.string.boss_level,
            R.string.countdown,
            R.string.gentelmens,
            R.string.outside_the_ware,
            1
        )
        trend2QuestionsList.add(que4)


        val que5 = Question(
            6,
            "bumblebee.jpg?alt=media&token=4d9fd19e-d1e7-4cfb-99f2-64d751c9d2dc",
            R.string.transformers,
            R.string.bumblebee,
            R.string.transformers_2,
            R.string.perviy_mstitel_drugaya_voina,
            2
        )
        trend2QuestionsList.add(que5)


        val que6 = Question(
            6,
            "Countdown%20-%2001.jpg?alt=media&token=cae0beeb-8b7e-4052-994d-78e5d713b1ef",
            R.string.tenet,
            R.string.love_and_monstries,
            R.string.countdown,
            R.string.transformers,
            3
        )
        trend2QuestionsList.add(que6)


        val que7 = Question(
            6,
            "dragon_3.jpeg?alt=media&token=e889f90c-4cbf-4921-9ed8-7adedc0bfa68",
            R.string.zoo_topia,
            R.string.jumanji,
            R.string.rai_and_last_dragon,
            R.string.how_to_train_your_dragon_3,
            4
        )
        trend2QuestionsList.add(que7)


        val que8 = Question(
            6,
            "ford_vs_ferrari.jpg?alt=media&token=050a4607-3162-4591-a03b-ba86be34d11a",
            R.string.transporter,
            R.string.mechanic,
            R.string.ford_vs_ferrari,
            R.string.fast_hobbs_shaw,
            3
        )
        trend2QuestionsList.add(que8)


        val que9 = Question(
            6,
            "greenland.jpeg?alt=media&token=ed13ea58-bfe1-4e1b-a165-966cc541d646",
            R.string.im_legend,
            R.string.greenland,
            R.string.bad_boys_for_life,
            R.string.boss_level,
            2
        )
        trend2QuestionsList.add(que9)


        val que10 = Question(
            6,
            "john_wick_3.jpeg?alt=media&token=964a6c6d-cca4-49fd-90a2-f3d4f701f483",
            R.string.john_wick,
            R.string.platform,
            R.string.godzilla,
            R.string.the_matrix,
            1
        )
        trend2QuestionsList.add(que10)


        val que11 = Question(
            6,
            "joker.jpeg?alt=media&token=be07c23b-4e5a-45cd-9da1-b7bc6f31cf7b",
            R.string.zack_snider_justice_league,
            R.string.joker,
            R.string.aquaman,
            R.string.suicide_squad,
            2
        )
        trend2QuestionsList.add(que11)


        val que12 = Question(
            6,
            "jumanji_the_next_level.jpg?alt=media&token=501cb580-c462-4ee7-976d-890fa5580b85",
            R.string.project_power,
            R.string.tenet,
            R.string.jumanji,
            R.string.outside_the_ware,
            3
        )
        trend2QuestionsList.add(que12)


        val que13 = Question(
            6,
            "love_and_monsters.jpeg?alt=media&token=36397650-4e8a-466f-9e83-d92500a21dde",
            R.string.x_men_new_mutant,
            R.string.x_men_apokalypse,
            R.string.the_old_guard,
            R.string.love_and_monstries,
            4
        )
        trend2QuestionsList.add(que13)


        val que14 = Question(
            6,
            "maleficent_mistress_of_evil.jpeg?alt=media&token=b42882f4-de34-49b2-8eb7-d9db0c051580",
            R.string.love_and_monstries,
            R.string.night_of_the_living_dead,
            R.string.maleficenta,
            R.string.hunter_monster,
            3
        )
        trend2QuestionsList.add(que14)


        val que15 = Question(
            6,
            "monster_hunter.jpg?alt=media&token=033118e3-ee7d-4b93-a650-0e5c7eb6eb11",
            R.string.x_men_apokalypse,
            R.string.hunter_monster,
            R.string.the_old_guard,
            R.string.platform,
            2
        )
        trend2QuestionsList.add(que15)


        val que16 = Question(
            6,
            "mulan.jpeg?alt=media&token=37457d3f-2740-4abe-9f88-820ec77444f8",
            R.string.mulan,
            R.string.resident_evil,
            R.string.mortal_engines,
            R.string.outside_the_ware,
            1
        )
        trend2QuestionsList.add(que16)


        val que17 = Question(
            6,
            "outside_the_wire.jpeg?alt=media&token=504c36a6-8a3c-45a3-a9d6-26fe4c59c61e",
            R.string.gemini,
            R.string.outside_the_ware,
            R.string.im_legend,
            R.string.bad_boys_for_life,
            2
        )
        trend2QuestionsList.add(que17)


        val que18 = Question(
            6,
            "platform.jpeg?alt=media&token=8e9ad932-d95f-4ecf-94cb-fb4c6416abff",
            R.string.scouts_guide_to_the_zombie,
            R.string.extraction,
            R.string.platform,
            R.string.pride_prejudice_zombies,
            3
        )
        trend2QuestionsList.add(que18)


        val que19 = Question(
            6,
            "shazam.jpg?alt=media&token=4a6fd43d-a365-4795-8022-a0317dc92377",
            R.string.welcome_to_Zombie_land,
            R.string.tenet,
            R.string.the_old_guard,
            R.string.shazam,
            4
        )
        trend2QuestionsList.add(que19)


        val que20 = Question(
            6,
            "tenet.jpg?alt=media&token=f5704bbd-f6d8-4b33-91fd-9db19c663e96",
            R.string.zack_snider_justice_league,
            R.string.bad_boys_for_life,
            R.string.tenet,
            R.string.hunter_monster,
            3
        )
        trend2QuestionsList.add(que20)



        return trend2QuestionsList


    }


    fun getDisneyLand2Question(): ArrayList<Question> {

        val disney2QuestionsList = ArrayList<Question>()

        val que1 = Question(
            6,
            "aivan.jpg?alt=media&token=23be0f63-67b2-40fc-a56e-54f7e1cdb5ce",
            R.string.monster_on_vacation,
            R.string.aivan,
            R.string.snejnie_psi,
            R.string.zoo_topia,
            2
        )
        disney2QuestionsList.add(que1)


        val que2 = Question(
            6,
            "artemis_faul.jpg?alt=media&token=19824462-9229-4953-9c32-90ca9de7a8df",
            R.string.planeta_spkrovish,
            R.string.alica_zazerkale,
            R.string.hunter_monster,
            R.string.aremis_faul,
            4
        )
        disney2QuestionsList.add(que2)


        val que3 = Question(
            6,
            "book_lungli.jpg?alt=media&token=a6ca7307-705e-444b-a5c1-fe393f35dbae",
            R.string.ledi_brodiaga,
            R.string.snejnie_psi,
            R.string.book_jungli,
            R.string.taina_koko,
            3
        )
        disney2QuestionsList.add(que3)


        val que4 = Question(
            6,
            "chumovaya_piatnica.jpg?alt=media&token=bc41d8a3-46fc-4165-afd8-035f26e55296",
            R.string.chumovaya_piatnica,
            R.string.shaste_eto,
            R.string.ledi_brodiaga,
            R.string.nasledniki,
            1
        )
        disney2QuestionsList.add(que4)


        val que5 = Question(
            6,
            "ideal_toy.jpg?alt=media&token=efacbdcb-c6d6-41e9-8907-8ab2ce2f3343",
            R.string.toy_stories,
            R.string.idel_toy,
            R.string.nasledniki,
            R.string.zolushka,
            2
        )
        disney2QuestionsList.add(que5)


        val que6 = Question(
            6,
            "krasavica_and_chudovishe.webp?alt=media&token=03d0b3fc-627f-4bf2-ac97-d3c3947fb25b",
            R.string.hrabroe_serdce,
            R.string.zolushka,
            R.string.krasavics_and_chudo,
            R.string.ledi_brodiaga,
            3
        )
        disney2QuestionsList.add(que6)


        val que7 = Question(
            6,
            "ledi_i_brodyaga.jpeg?alt=media&token=edbcb336-c975-4284-bd77-64adc1bfe142",
            R.string.zoo_topia,
            R.string.jumanji,
            R.string.shaste_eto,
            R.string.ledi_brodiaga,
            4
        )
        disney2QuestionsList.add(que7)


        val que8 = Question(
            6,
            "monstr_on_vacation.jpg?alt=media&token=77fce178-1799-4105-bf2d-c2a06cdf3d9d",
            R.string.hunter_monster,
            R.string.monster_corparation,
            R.string.monster_on_vacation,
            R.string.taina_koko,
            3
        )
        disney2QuestionsList.add(que8)


        val que9 = Question(
            6,
            "nasleniki.jpg?alt=media&token=32e28ff1-4bf3-408d-a9ab-94141e4033e4",
            R.string.maleficenta,
            R.string.nasledniki,
            R.string.pit_and_dragon,
            R.string.shelkunchik_i_4_korolevstv,
            2
        )
        disney2QuestionsList.add(que9)


        val que10 = Question(
            6,
            "pit_and_drakon.jpg?alt=media&token=64f3cf9c-e2c9-41c3-80b6-fd61752c1d8f",
            R.string.pit_and_dragon,
            R.string.book_jungli,
            R.string.piterpen,
            R.string.aremis_faul,
            1
        )
        disney2QuestionsList.add(que10)


        val que11 = Question(
            6,
            "piter_pen.jpg?alt=media&token=3160beff-c11b-45d9-92d6-20ad4a626d40",
            R.string.shelkunchik_i_4_korolevstv,
            R.string.planeta_spkrovish,
            R.string.piterpen,
            R.string.shaste_eto,
            3
        )
        disney2QuestionsList.add(que11)


        val que12 = Question(
            6,
            "planeta_sokrofish.jpg?alt=media&token=e16985cc-9f2e-4fb9-ad08-3e5a990c7044",
            R.string.poiskah_nemu,
            R.string.poiskah_dorri,
            R.string.planeta_spkrovish,
            R.string.taina_koko,
            3
        )
        disney2QuestionsList.add(que12)


        val que13 = Question(
            6,
            "poiskah_dori.jpg?alt=media&token=56e4da67-2432-4630-b152-1c1769513116",
            R.string.shelkunchik_i_4_korolevstv,
            R.string.shaste_eto,
            R.string.poiskah_nemu,
            R.string.poiskah_dorri,
            4
        )
        disney2QuestionsList.add(que13)


        val que14 = Question(
            6,
            "shelkunchik_and_4_korolevstv.jpg?alt=media&token=15df467f-a1c5-4880-bda5-eaee1d619ea4",
            R.string.holodnoe_serdce_2,
            R.string.krasavics_and_chudo,
            R.string.shelkunchik_i_4_korolevstv,
            R.string.zolushka,
            3
        )
        disney2QuestionsList.add(que14)


        val que15 = Question(
            6,
            "shaste_etp.jpeg?alt=media&token=a2069e76-a2e6-4e6b-8bbc-0a11cbab53c2",
            R.string.dusha,
            R.string.shaste_eto,
            R.string.chumovaya_piatnica,
            R.string.zolushka,
            2
        )
        disney2QuestionsList.add(que15)


        val que16 = Question(
            6,
            "snejnie_psi.jpg?alt=media&token=01a17584-64e4-444d-9de0-0dfc34c30eba",
            R.string.snejnie_psi,
            R.string.taina_koko,
            R.string.holodnoe_serdce,
            R.string.the_call_of_the_wild,
            1
        )
        disney2QuestionsList.add(que16)


        val que17 = Question(
            6,
            "taina_kok.jpg?alt=media&token=8e09d807-5ceb-43a5-b025-e54badc6a187",
            R.string.night_of_the_living_dead,
            R.string.alica_zazerkale,
            R.string.caroline_in_the_country_nightmares,
            R.string.taina_koko,
            4
        )
        disney2QuestionsList.add(que17)


        val que18 = Question(
            6,
            "zolushka.jpg?alt=media&token=c8721b11-7e3c-49f6-b660-6ec1a20e3eca",
            R.string.rapunzel,
            R.string.holodnoe_serdce,
            R.string.zolushka,
            R.string.dnevniki_princessi,
            3
        )
        disney2QuestionsList.add(que18)


        val que19 = Question(
            6,
            "дневник%20принцессы.jpg?alt=media&token=6b3c2346-8124-497e-b419-32cab3746efe",
            R.string.dnevniki_princessi,
            R.string.the_old_guard,
            R.string.idel_toy,
            R.string.up0,
            1
        )
        disney2QuestionsList.add(que19)


        val que20 = Question(
            6,
            "poiskah_nemu.webp?alt=media&token=dadd3093-68e9-4007-9b37-4b703f18726b",
            R.string.mortal_engines,
            R.string.poiskah_nemu,
            R.string.tenet,
            R.string.hunter_monster,
            2
        )
        disney2QuestionsList.add(que20)



        return disney2QuestionsList


    }

    fun imageQuestion(): ArrayList<ImageU> {

        val imagequiz = ArrayList<ImageU>()

        val que1 = ImageU(
            "titanic.jpg?alt=media&token=61833387-1436-4676-8224-bc112f2a6cd4",
            "john_wick.jpg?alt=media&token=82c26b9c-1991-499c-ab80-e73b6fcfcea1",
            "after.jpg?alt=media&token=2dbd48d4-bab7-49c7-ad2e-7baa8dd86e9e",
            "tom_and_jerry.jpg?alt=media&token=c0fa4a46-c6a4-466a-9cd6-b4480a818f5f",
        )
        imagequiz.add(que1)


        val que2 = ImageU(
            "legen_poluk.jpg?alt=media&token=a6959465-dc7a-4523-bf6a-011de82967e8",
            "misiia_nev.jpg?alt=media&token=3e587462-182f-4dc3-8eb6-053130363a61",
            "Золушка%20.jpeg?alt=media&token=81a3bcb4-b23e-4dcf-a049-e0c14dacf006",
            "warm-bodies.jpg?alt=media&token=255cb06d-2987-4f79-a3bb-ded2367200d4",
        )
        imagequiz.add(que2)


        val que3 = ImageU(
            "harry_potter.jpg?alt=media&token=f7618fd7-213a-4975-b3d4-01f7d11f0525",
            "forsage.jpg?alt=media&token=4d81ae97-2490-4525-bcf2-4ecb92ec10a6",
            "hatiko.jpeg?alt=media&token=d499264c-488c-4bd6-b7ae-8311c47c0937",
            "Suicide_Squad_compress62.jpg?alt=media&token=088eecf3-c124-4e4a-88aa-68948f028bcc"
        )
        imagequiz.add(que3)


        val que4 = ImageU(
            "sumerki.jpg?alt=media&token=f6990579-14cd-48a8-b8ac-4956119bceb4",
            "pirats.jpg?alt=media&token=49bcfc82-dbad-4342-8bd0-6ca37e456f21",
            "maleficenta.jpg?alt=media&token=b4f3ce6c-2128-45eb-b0b0-6002227037ff",
            "hobbit.jpg?alt=media&token=0c3812dd-46c1-4c20-bb1b-3de2a4b8817c"
        )
        imagequiz.add(que4)

        val que5 = ImageU(
            "pit_and_his_dragon.jpg?alt=media&token=88443b0f-458c-4781-ac11-62c529fad77c",
            "MortalKombaatposter.jpg?alt=media&token=609c5ccd-0fcd-497f-8f76-a704d5322705",
            "star.jpg?alt=media&token=a0ba1f1a-8dda-44cc-9ddd-81e8efb33b12",
            "soul.jpg?alt=media&token=b293ee5c-8762-4bb3-861f-2eeef339223c"
        )
        imagequiz.add(que5)


        val que6 = ImageU(
            "3_metra.jpg?alt=media&token=e74c8307-bf60-4296-89b0-9ebc1276bcba",
            "13rayon.jpg?alt=media&token=5a91234b-da41-47d6-a7d8-d0b6f954fd87",
            "alisa.jpg?alt=media&token=2807578b-1c01-4fd3-bdac-f692b7555875",
            "pila.jpg?alt=media&token=0c93cf52-3574-4c16-8d04-1f8900814f52"
        )
        imagequiz.add(que6)


        val que7 = ImageU(
            "raia_and_last.jpg?alt=media&token=bc58539e-e04b-4be3-a989-f05557865af8",
            "troya.jpg?alt=media&token=330e5450-d84f-4a73-a979-85e5c3c0c6cd",
            "belosnejka.jpg?alt=media&token=6d37bebe-faef-4694-b3df-f4a0e164f369",
            "hroniki.jpg?alt=media&token=f7991bce-00d6-4ecd-abe9-9157948d3e88"
        )
        imagequiz.add(que7)


        val que8 = ImageU(
            "poezd_v_pusan.jpg?alt=media&token=2a704ea5-8aa1-44d4-a0f4-e762a76ac6d3",
            "recetir.jpg?alt=media&token=ac8a630b-0b4f-463e-a1d8-788699e91c15",
            "sen_cal.jpg?alt=media&token=0e4795d6-e779-41c9-8b08-e3dc28717f0e",
            "v_metre_ot.jpg?alt=media&token=a57631cd-05fd-4711-8f99-98d9eda0f6ed"
        )
        imagequiz.add(que8)






        return imagequiz

    }

    var title = arrayListOf(
        R.string.test_zombies_test,
        R.string.disney_test,
        R.string.trend1_test,
        R.string.trend2_test,
        R.string.disney_test2,
        R.string.imagequiz_test
    )
    private var details = arrayListOf(
        R.string.detail5,
        R.string.detail6,
        R.string.detail7,
        R.string.detail7,
        R.string.detail6,
        R.string.detail8
    )
    private var images = intArrayOf(
        R.drawable.zombie_test,
        R.drawable.disney,
        R.drawable.trendone,
        R.drawable.trendtwo,
        R.drawable.disney2,
        R.drawable.imageguiz_test
    )
    val testStorage = listOf(
        TestPreviewModel(
            R.string.test_marvel_neon,
            R.string.detail1,
            R.drawable.marvel_test_neon,
            getMarvelQuestion()
        ),
        TestPreviewModel(
            R.string.test_marvel_movie,
            R.string.detail2,
            R.drawable.marvel_movie,
            getMarvelMovieQuestion()
        ),
        TestPreviewModel(
            R.string.test_marvel_pixel, R.string.detail3, R.drawable.marvel_pixel,
            getMarvelPixelQuestion()

        ),
        TestPreviewModel(
            R.string.test_free_fire, R.string.detail4, R.drawable.free_fire_test,
            getFreeFireQuestion()
        )
    )

}