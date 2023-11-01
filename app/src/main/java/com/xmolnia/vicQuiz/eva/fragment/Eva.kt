package com.xmolnia.vicQuiz.eva.fragment

import androidx.appcompat.app.AppCompatActivity


class Eva :AppCompatActivity() {

//    companion object{
//        const val REQUEST_RECORD_AUDIO_PERMISSION = 200
//        private val permission = arrayOf( android.Manifest.permission.RECORD_AUDIO)
//    }
//
//    private lateinit var messageModalArrayList: ArrayList<MessageModal>
//    private lateinit var messageRVAdapter: MessageAdapter
//    private lateinit var binding: FragmentEvaBinding
//    private lateinit var bottomBar: AnimatedBottomBar
//    private lateinit var bottomLayout: LinearLayout
//    private lateinit var container: FrameLayout
//    private lateinit var speechRecognizer: SpeechRecognizer
//    private lateinit var speechRecognizerModel: SpeechRecognizerViewModel
//    private var count = 0
//
////    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
////        // Inflate the layout for this fragment
////        return inflater.inflate(R.layout.fragment_eva, container, false)
////    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = FragmentEvaBinding.inflate(layoutInflater)
//        setContentView(binding.root)
////        bottomBar = findViewById(R.id.bottom_bar)
////        bottomLayout = findViewById(R.id.bottomBarLayout)
////        container = findViewById(R.id.fragment_container)
//
////        container.layoutParams?.height = FrameLayout.LayoutParams.MATCH_PARENT
////
////        bottomLayout.visibility = View.GONE
//
//        messageModalArrayList = ArrayList()
//
//
//        binding.layoutSend.setOnClickListener(View.OnClickListener { // checking if the message entered
//
//            if (binding.inputMessage.text.toString().isEmpty()) {
//                // if the edit text is empty display a toast message.
//                Toast.makeText(this, "Please enter your message..", Toast.LENGTH_SHORT).show()
//                return@OnClickListener
//            }
//
//            // calling a method to send message
//            // to our bot to get response.
//            sendMessage(binding.inputMessage.text.toString())
//
//            // below line we are setting text in our edit text as empty
//            binding.inputMessage.setText("")
//        })
//
//        // on below line we are initialing our adapter class and passing our array lit to it.
//        messageRVAdapter = MessageAdapter(this, messageModalArrayList)
//
//
//
//        binding.RVChats.adapter = messageRVAdapter
//
//        binding.back.setOnClickListener {
//            backClick()
//        }
//
//
//        setupSpeechViewModel()
//
//
//
//        binding.micro.setOnClickListener {
//
//            if(!speechRecognizerModel.permissionToRecordAudio){
//                ActivityCompat.requestPermissions(this, permission, REQUEST_RECORD_AUDIO_PERMISSION)
//
//            }
////
//            if (speechRecognizerModel.isListening) {
//                speechRecognizerModel.stopListening()
//            }
//            else {
//                speechRecognizerModel.startListening()
//            }
//
//
//        }
//
//
//
//
//    }
//
//    private fun setupSpeechViewModel() {
//
//        speechRecognizerModel= ViewModelProviders.of(this).get(SpeechRecognizerViewModel::class.java)
//        speechRecognizerModel.getViewState().observe(this, androidx.lifecycle.Observer<SpeechRecognizerViewModel.ViewState> { viewState->
//            render(viewState)
//        })
//    }
//
//    private fun render(uiOutput: SpeechRecognizerViewModel.ViewState?){
//        if(uiOutput == null) return
//
//        else{
//            binding.inputMessage.setText(uiOutput.spokenText)
//            binding.inputMessage.setText("uiOutput.spokenText")
//
//            binding.micro.setImageResource(if(uiOutput.isListening) R.drawable.ic_micro else R.drawable.ic_mic_mute)
//        }
//
//
////        if(uiOutput.rmsDbChanged){
////
////        }
//
//
//
//    }
//
//
//
//
//
//
//    private fun sendMessage(userMsg: String) {
//        // below line is to pass message to our
//        // array list which is entered by the user.
//        messageModalArrayList.add(MessageModal(userMsg, USER_KEY))
//        messageRVAdapter.notifyDataSetChanged()
//        binding.RVChats.smoothScrollToPosition(messageRVAdapter.itemCount)
//
//
//
//
//        Handler(Looper.getMainLooper()).postDelayed({
//
//            val data = MovieBase.getInstance().getDataRandom()
//
//            if (idType(userMsg) == 1) {
//                messageModalArrayList.add(MessageModal(AIDIALOG.messageGenerator(userMsg), BOT_KEY, idType(userMsg), data))
//
//            }
//            else {
//                messageModalArrayList.add(MessageModal(AIDIALOG.messageGenerator(userMsg), BOT_KEY, idType(userMsg)))
//            }
//            // notifying our adapter as data changed.
//            messageRVAdapter.notifyDataSetChanged()
//            binding.RVChats.smoothScrollToPosition(messageRVAdapter.itemCount)
//        }, 500)
//    }
//
//
//    fun backClick() {
//
//        val fragmentManager = this.supportFragmentManager.beginTransaction()
//        fragmentManager.replace(R.id.fragment_container, Movie())
//        fragmentManager.commit()
//        bottomBar.selectTabById(R.id.movie)
//        container.layoutParams.height = 0
//        bottomLayout.visibility = View.VISIBLE
//
//
//    }
//
//
//    fun idType(userMsg: String): Int {
//        return when (userMsg.lowercase(Locale.getDefault())) {
//            "1" -> {
//                1
//            }
//
//            "посоветуй фильм" -> {
//                1
//            }
//
//            "пасоветуй фильм" -> {
//                1
//            }
//
//            "какой фильм  смотреть" -> {
//                1
//            }
//
//            "какой филм  смотрет" -> {
//                1
//            }
//
//            "какой фильм лучше" -> {
//                1
//            }
//
//            else -> {
//                0
//            }
//        }
//
//
//    }
//
////    fun playRecord(view: View) = Unit
//
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
//            speechRecognizerModel.permissionToRecordAudio = grantResults[0] == PackageManager.PERMISSION_GRANTED
//        }
//        if(speechRecognizerModel.permissionToRecordAudio){
//            binding.micro.performClick()
//
//
//
//
//        }
//    }


}