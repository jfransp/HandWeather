package com.example.components.printablecomposable

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.FileProvider
import androidx.core.graphics.applyCanvas
import java.io.File
import java.io.FileOutputStream

//Was going to use this for a share function, but didn't have time to do it
/*
class ComposePrintable(
    private val context: Context,
    var bitmap: Bitmap? = null
) {

    companion object {
        const val SCREENSHOT_FILE_NAME = "printablecomposable_temp_image"
        const val CACHE_DIRECTORY = "Pictures/"
    }

    @Composable
    fun SetContent(
        content: @Composable () -> Unit,
    ) {

        AndroidView(
            factory = { ComposeView(context = this.context) },
            update = { composeView ->
                composeView.setContent {
                    content()
                    this.captureBitmap(LocalView.current)
                }
            }
        )

    }

    private fun captureBitmap(view: View) {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable {
            bitmap = Bitmap.createBitmap(
                view.width, view.height,
                Bitmap.Config.ARGB_8888
            ).applyCanvas {
                view.draw(this)
            }
        }, 1000)
    }

    //Creates a temporary file on the device and return its URI address
    fun makeImage(): Uri {
        val cachePath = File(context.externalCacheDir, CACHE_DIRECTORY)
        cachePath.mkdirs()

        val screenshotFile = File(cachePath, SCREENSHOT_FILE_NAME).also { file ->
            FileOutputStream(file).use { fileOutputStream ->
                bitmap?.compress(
                    Bitmap.CompressFormat.PNG,
                    100,
                    fileOutputStream
                )
            }
        }.apply {
            deleteOnExit()
        }

        return FileProvider.getUriForFile(
            context,
            context.applicationContext.packageName + ".provider",
            screenshotFile
        )
    }
}
*/
