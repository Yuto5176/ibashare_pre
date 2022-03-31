package com.swift2021.ibashareandroid.view

import android.content.ContentValues.TAG
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.constraintlayout.utils.widget.ImageFilterButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.swift2021.ibashareandroid.PlaceData
import com.swift2021.ibashareandroid.R
import com.swift2021.ibashareandroid.UserData
import com.swift2021.ibashareandroid.databinding.FragmentTopPageBinding
import com.swift2021.ibashareandroid.viewmodel.TopPageViewModel
import kotlinx.android.synthetic.main.fragment_top_page.*
import kotlinx.android.synthetic.main.random_image_view.view.*
import kotlinx.android.synthetic.main.toppage_genre_layout.view.*


class TopPageFragment : Fragment() {
    private lateinit var binding: FragmentTopPageBinding
    private val viewModel: TopPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopPageBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    val bundle = Bundle()

    private var locationIndex: Int = 1
    private val db = Firebase.firestore
    private var isTimeZero = true
    private val fujimon = UserData(
        "hU5yQWM3JN2eG4rdeWfO",
        "藤門千明",
        "将棋・囲碁",
        "編み物",
        "料理"
    )

    private lateinit var handler: Handler
    private lateinit var rnb: Runnable

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(rnb)
        locationIndex = 1
    }


    // ActivityでのOnCreateの役割
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        pointColorSet()
        timeEvent()

        setTextView()
        setImageView()

        setButtonEvent()

        val dataAmimono: SharedPreferences =
            requireActivity().getSharedPreferences("DataAmimono", MODE_PRIVATE)
        val dataShogi: SharedPreferences =
            requireActivity().getSharedPreferences("DataShogi", MODE_PRIVATE)
        val dataIgo: SharedPreferences =
            requireActivity().getSharedPreferences("DataIgo", MODE_PRIVATE)
        val dataCook: SharedPreferences =
            requireActivity().getSharedPreferences("DataCook", MODE_PRIVATE)

        var amimonoTap = dataAmimono.getInt("amimono", 0)
        var shogiTap = dataShogi.getInt("shogi", 0)
        var igoTap = dataIgo.getInt("igo", 0)
        var cookTap = dataCook.getInt("cook", 0)

        fujimon.amimono = amimonoTap
        fujimon.shogi = shogiTap
        fujimon.igo = igoTap
        fujimon.cook = cookTap

        db.collection("users").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document?.toObjects(PlaceData::class.java) != null) {
                    val genreList = document.toObjects(UserData::class.java)

                }
            } else {
                Log.d(TAG, "No such document")
            }
        }
        //         コンフリクトで消えた箇所↓
//         image1.setOnClickListener {
//             val intent = Intent(this, MainActivity2::class.java)
//             fujimon.tapAmimono()

//             intent.putExtra("PlaceName", placeName1.text.toString())
//             intent.putExtra("PlaceImage", 1)
//             var editorAmimono = dataAmimono.edit()
//             editorAmimono.putInt("amimono", fujimon.amimono)
//             editorAmimono.apply()
//             startActivity(intent)

//         }


    }

    private fun timeEvent() {
        handler = Handler((Looper.getMainLooper()))
        rnb = object : Runnable {
            override fun run() {
                handler.postDelayed(this, 10000)
                if (!isTimeZero) {
                    changeCard(flipper)
                }
                isTimeZero = false
            }
        }
        handler.post(rnb)
    }

    private fun changeCard(viewFlipper: ViewFlipper) {
        locationIndex = (locationIndex % 5) + 1
        when (locationIndex) {
            0 -> {
                point1.setImageResource(R.drawable.circle_random_image)
                point2.setImageResource(R.drawable.circle_random_image)
                point3.setImageResource(R.drawable.circle_random_image)
                point4.setImageResource(R.drawable.circle_random_image)
                point5.setImageResource(R.drawable.circle_random_image)
            }
            1 -> {
                point1.setImageResource(R.drawable.circle_random_image_on)
                point2.setImageResource(R.drawable.circle_random_image)
                point3.setImageResource(R.drawable.circle_random_image)
                point4.setImageResource(R.drawable.circle_random_image)
                point5.setImageResource(R.drawable.circle_random_image)
                viewFlipper.showNext()
            }
            2 -> {
                point2.setImageResource(R.drawable.circle_random_image_on)
                point1.setImageResource(R.drawable.circle_random_image)
                point3.setImageResource(R.drawable.circle_random_image)
                point4.setImageResource(R.drawable.circle_random_image)
                point5.setImageResource(R.drawable.circle_random_image)
                viewFlipper.showNext()
            }
            3 -> {
                point3.setImageResource(R.drawable.circle_random_image_on)
                point1.setImageResource(R.drawable.circle_random_image)
                point2.setImageResource(R.drawable.circle_random_image)
                point4.setImageResource(R.drawable.circle_random_image)
                point5.setImageResource(R.drawable.circle_random_image)
                viewFlipper.showNext()
            }
            4 -> {
                point4.setImageResource(R.drawable.circle_random_image_on)
                point1.setImageResource(R.drawable.circle_random_image)
                point2.setImageResource(R.drawable.circle_random_image)
                point3.setImageResource(R.drawable.circle_random_image)
                point5.setImageResource(R.drawable.circle_random_image)
                viewFlipper.showNext()
            }
            5 -> {
                point5.setImageResource(R.drawable.circle_random_image_on)
                point1.setImageResource(R.drawable.circle_random_image)
                point2.setImageResource(R.drawable.circle_random_image)
                point3.setImageResource(R.drawable.circle_random_image)
                point4.setImageResource(R.drawable.circle_random_image)
                viewFlipper.showNext()
            }
        }
    }

    private fun pointColorSet() {
        point1.setImageResource(R.drawable.circle_random_image_on)
        point2.setImageResource(R.drawable.circle_random_image)
        point3.setImageResource(R.drawable.circle_random_image)
        point4.setImageResource(R.drawable.circle_random_image)
        point5.setImageResource(R.drawable.circle_random_image)
    }

    private fun setTextView() {

        // 各ジャンルTitleのTextView
        val genre1TitleTextView = genre1_layout.GenreTitleName
        val genre2TitleTextView = genre2_layout.GenreTitleName
        val genre3TitleTextView = genre3_layout.GenreTitleName
        val genreTitleList: List<TextView> =
            listOf(genre1TitleTextView, genre2TitleTextView, genre3TitleTextView)

        // 各ジャンルのTextView
        val randomTextViewList =
            listOf<TextView>(
                firstView.randomTextView,
                secondView.randomTextView,
                thirdView.randomTextView,
                fourthView.randomTextView,
                fifthView.randomTextView
            )

        val recommendTextViewList =
            listOf<TextView>(
                recommend_layout.genre_place1,
                recommend_layout.genre_place2,
                recommend_layout.genre_place3,
                recommend_layout.genre_place4
            )

        val genre1TextViewList = listOf<TextView>(
            genre1_layout.genre_place1,
            genre1_layout.genre_place2,
            genre1_layout.genre_place3,
            genre1_layout.genre_place4
        )
        val genre2TextViewList = listOf<TextView>(
            genre2_layout.genre_place1,
            genre2_layout.genre_place2,
            genre2_layout.genre_place3,
            genre2_layout.genre_place4
        )
        val genre3TextViewList = listOf<TextView>(
            genre3_layout.genre_place1,
            genre3_layout.genre_place2,
            genre3_layout.genre_place3,
            genre3_layout.genre_place4
        )

        val selectedGenreName =
            listOf<String>("囲碁", "編み物", "将棋")

        // Firebaseから居場所名をセット
        //setPlaceName(randomTextViewList, "編み物")
        setPlaceName(recommendTextViewList, "将棋")

        // ジャンル名をFirestoreから
        setGenreName(genreTitleList, selectedGenreName)

        // 居場所名をFirestoreから
        setPlaceName(genre1TextViewList, selectedGenreName[0])
        setPlaceName(genre2TextViewList, selectedGenreName[1])
        setPlaceName(genre3TextViewList, selectedGenreName[2])
    }

    private fun setImageView() {
        // 画像のパス
        val randomImagePath =
            listOf(
                R.mipmap.amimono01,
                R.mipmap.amimono02,
                R.mipmap.amimono03,
                R.mipmap.amimono04,
                R.mipmap.amimono03
            )
        val recommendImagePath =
            listOf(R.mipmap.shogi01, R.mipmap.shogi02, R.mipmap.shogi03, R.mipmap.shogi04)
        val genre1ImagePath =
            listOf(R.mipmap.igo01, R.mipmap.igo02, R.mipmap.igo03, R.mipmap.igo04)
        val genre2ImagePath =
            listOf(R.mipmap.amimono01, R.mipmap.amimono02, R.mipmap.amimono03, R.mipmap.amimono04)
        val genre3ImagePath =
            listOf(R.mipmap.shogi01, R.mipmap.shogi02, R.mipmap.shogi03, R.mipmap.shogi04)

        // 各ジャンルのImageView
        val randomImageViewList = listOf<ImageFilterButton>(
            firstView.randomImageButton,
            secondView.randomImageButton,
            thirdView.randomImageButton,
            fourthView.randomImageButton,
            fifthView.randomImageButton
        )

        val recommendImageViewList = listOf<ImageView>(
            recommend_layout.genre_image1,
            recommend_layout.genre_image2,
            recommend_layout.genre_image3,
            recommend_layout.genre_image4
        )

        val genre1ImageViewList =
            listOf<ImageView>(
                genre1_layout.genre_image1,
                genre1_layout.genre_image2,
                genre1_layout.genre_image3,
                genre1_layout.genre_image4
            )
        val genre2ImageViewList =
            listOf<ImageView>(
                genre2_layout.genre_image1,
                genre2_layout.genre_image2,
                genre2_layout.genre_image3,
                genre2_layout.genre_image4
            )
        val genre3ImageViewList =
            listOf<ImageView>(
                genre3_layout.genre_image1,
                genre3_layout.genre_image2,
                genre3_layout.genre_image3,
                genre3_layout.genre_image4
            )

        // 画像をセット
        setImage(randomImageViewList, randomImagePath)
        setImage(recommendImageViewList, recommendImagePath)
        setImage(genre1ImageViewList, genre1ImagePath)
        setImage(genre2ImageViewList, genre2ImagePath)
        setImage(genre3ImageViewList, genre3ImagePath)
    }

    private fun setPlaceName(placeViewList: List<TextView>, genreName: String) {
        db.collection("place").whereEqualTo("genre", genreName).limit(4)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document?.toObjects(PlaceData::class.java) != null) {
                        val placeList = document.toObjects(PlaceData::class.java)
                        Log.d(TAG, "getDataAll")
                        Log.d(TAG, "placeList.size " + placeList.size)

                        for (i in 0 until placeList.size) {
                            placeViewList[i].text = placeList[i].title
                        }
                    }
                } else {
                    Log.d(TAG, "No such document")
                }
            }
    }

    private fun setGenreName_firebase(genreTitleTextViewList: List<TextView>) {

        db.collection("users").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document?.toObjects(PlaceData::class.java) != null) {
                    val userList = document.toObjects(UserData::class.java)
                    genreTitleTextViewList[0].text = userList[0].genre1
                    genreTitleTextViewList[1].text = userList[0].genre2
                    genreTitleTextViewList[2].text = userList[0].genre3
                }
            } else {
                Log.d(TAG, "No such document")
            }
        }
    }

    private fun setGenreName(
        genreTitleTextViewList: List<TextView>,
        selectedGenreName: List<String>
    ) {
        genreTitleTextViewList[0].text = selectedGenreName[0]
        genreTitleTextViewList[1].text = selectedGenreName[1]
        genreTitleTextViewList[2].text = selectedGenreName[2]
    }

    private fun setImage(ImageViewList: List<ImageView>, ImagePath: List<Int>) {
        for (i in ImageViewList.indices) {
            ImageViewList[i].setImageResource(ImagePath[i])
        }
    }

    private fun setButtonEvent() {
        val recommendCardList = listOf<MaterialCardView>(
            recommend_layout.genre_place1_card,
            recommend_layout.genre_place2_card,
            recommend_layout.genre_place3_card,
            recommend_layout.genre_place4_card
        )
        val randomCardList = listOf<ImageFilterButton>(
            random_image_include1.randomImageCard.randomImageButton,
            random_image_include2.randomImageCard.randomImageButton,
            random_image_include3.randomImageCard.randomImageButton,
            random_image_include4.randomImageCard.randomImageButton
//            random_image_include5.randomImageCard.randomImageButton
        )
        val genre1CardList = listOf<MaterialCardView>(
            genre1_layout.genre_place1_card,
            genre1_layout.genre_place2_card,
            genre1_layout.genre_place3_card,
            genre1_layout.genre_place4_card
        )
        val genre2CardList = listOf<MaterialCardView>(
            genre2_layout.genre_place1_card,
            genre2_layout.genre_place2_card,
            genre2_layout.genre_place3_card,
            genre2_layout.genre_place4_card
        )
        val genre3CardList = listOf<MaterialCardView>(
            genre3_layout.genre_place1_card,
            genre3_layout.genre_place2_card,
            genre3_layout.genre_place3_card,
            genre3_layout.genre_place4_card
        )
        setClickEventRandom(randomCardList, "編み物")
        setClickEvent(recommendCardList, "将棋")
        setClickEvent(genre1CardList, "囲碁")
        setClickEvent(genre2CardList, "編み物")
        setClickEvent(genre3CardList, "将棋")


        setClickEventSeeMore()
        setClickEventSeeTown()
    }

    // ジャンル内の4つの居場所idを取る
    private fun getPlaceId(
        genreName: String,
        listener: (list_for_listener: MutableList<String>) -> Unit
    ) {
        var placeIdList: MutableList<String> = mutableListOf()
        db.collection("place").whereEqualTo("genre", genreName).limit(4)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document?.toObjects(PlaceData::class.java) != null) {
                        val placeList = document.toObjects(PlaceData::class.java)
                        for (i in 0 until placeList.size) {
                            placeIdList.add(placeList[i].id)
                        }
                    }
                } else {
                    Log.d(TAG, "No such document")
                }
            }.addOnSuccessListener {
                listener(placeIdList)
            }
    }

    // randomImage以外
    private fun setClickEvent(cardList: List<MaterialCardView>, genreTitle: String) {
        // placeIdListに値が入るまで処理が止まるはず
        getPlaceId(genreTitle) { placeIdList ->
            // Log.d(TAG, "placeIdList:$placeIdList")
            for (i in cardList.indices) {
                cardList[i].setOnClickListener {
                    when (i) {
                        0 -> {
                            fujimon.tapAmimono()
                        }
                        1 -> {
                            fujimon.tapIgo()
                        }
                        2 -> {
                            fujimon.tapShogi()
                        }
                        3 -> {
                            fujimon.tapIgo()
                        }
                    }
                    Log.d(TAG, "toSecondButton pressed!")
                    val action = TopPageFragmentDirections.actionTopToPlaceDetail(placeIdList[i])
                    findNavController().navigate(action)
                }
            }
        }
    }

    // randomImageの場合、上の関数でクリックできなかった為
    private fun setClickEventRandom(cardList: List<ImageFilterButton>, genreTitle: String) {
        // placeIdListに値が入るまで処理が止まるはず
        getPlaceId(genreTitle) { placeIdList ->
            // Log.d(TAG, "placeIdList:$placeIdList")
            for (i in cardList.indices) {
                cardList[i].setOnClickListener {
                    when (i) {
                        0 -> {
                            fujimon.tapAmimono()
                        }
                        1 -> {
                            fujimon.tapIgo()
                        }
                        2 -> {
                            fujimon.tapShogi()
                        }
                        3 -> {
                            fujimon.tapIgo()
                        }
                    }
                    Log.d(TAG, "toSecondButton pressed!")
                    val action = TopPageFragmentDirections.actionTopToPlaceDetail(placeIdList[i])
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun setClickEventSeeMore() {
        val seeMoreList = listOf<Button>(
            recommend_layout.seeMoreGenreButton,
            genre1_layout.seeMoreGenreButton,
            genre2_layout.seeMoreGenreButton,
            genre3_layout.seeMoreGenreButton
        )
        for (i in seeMoreList.indices) {
            seeMoreList[i].setOnClickListener {
                findNavController().navigate(R.id.action_top_to_see_more)
            }
        }
    }

    private fun setClickEventSeeTown() {
        seeTownButton.setOnClickListener {
            findNavController().navigate(R.id.action_top_to_select)
        }
    }

}
