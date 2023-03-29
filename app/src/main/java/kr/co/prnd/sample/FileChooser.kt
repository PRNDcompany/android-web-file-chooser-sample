package kr.co.prnd.sample

import android.content.Context
import android.content.Intent
import android.util.Log
import com.gun0912.tedonactivityresult.coroutine.TedOnActivityResult

class FileChooser(
    private val context: Context,
) {
    suspend fun show() {
        val chooserIntent = createChooserIntent()

        val intent: Intent? = TedOnActivityResult.with(context)
            .startActivityForResult(chooserIntent)
            .data

        // 선택 X: null
        // 선택 O: Intent { dat=content://com.android.providers.media.documents/... flg=0x43 }
        Log.i("TEST", "$intent")
    }

    private fun createChooserIntent(): Intent {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
            val mimeTypes = arrayOf("image/*", "application/pdf")
            putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        }
        return Intent.createChooser(intent, "첨부파일 선택")
    }
}
