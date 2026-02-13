package fhnw.emoba.modules.module09.uguu.viewmodel

import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.ComponentActivity
import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fhnw.emoba.R
import fhnw.emoba.modules.module09.uguu.data.downloadBitmapFromUguu
import fhnw.emoba.modules.module09.uguu.data.uploadBitmapToUguu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UguuModel : ViewModel() {
    val title = "uguu.se App"
    lateinit var originalImg: Bitmap

    var currentTab by mutableStateOf(HomeScreenTabId.UPLOAD)

    var uguuURL          by mutableStateOf<String?>(null)
    var uploadInProgress   by mutableStateOf(false)

    var downloadedImg     by mutableStateOf<Bitmap?>(null)
    var downloadInProgress by mutableStateOf(false)
    var downloadMessage    by mutableStateOf("")

    lateinit var clipboardManager : ClipboardManager

    fun initializeWithNewActivity(activity: ComponentActivity){
        clipboardManager = getClipBoardManager(activity)
        originalImg = loadImage(activity, R.drawable.starwars)
    }

    private fun getClipBoardManager(activity: ComponentActivity)  =
        activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    fun uploadToUguu() {
        uploadInProgress = true
        uguuURL = null
        viewModelScope.launch(Dispatchers.IO) {
            uploadBitmapToUguu(bitmap    = originalImg,
                                 onSuccess = { uguuURL = it},
                                 onError   = {_, _ -> })  //Todo: was machen wir denn nun?
            uploadInProgress = false
        }
    }

    fun downloadFromUguu(){
        if(uguuURL != null){
            downloadedImg = null
            downloadInProgress = true
            viewModelScope.launch(Dispatchers.IO)  {
                downloadBitmapFromUguu(url       = uguuURL!!,
                                         onSuccess = { downloadedImg = it},
                                         onDeleted = { downloadMessage = "File is deleted"},
                                         onError   = { downloadMessage = "Connection failed"})
                downloadInProgress = false
            }
        }
    }

    private fun loadImage(activity: ComponentActivity, @DrawableRes id: Int) =
        BitmapFactory.decodeResource(activity.resources, id)

}


