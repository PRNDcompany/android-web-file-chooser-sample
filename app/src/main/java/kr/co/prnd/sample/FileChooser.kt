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

        val activityResult = TedOnActivityResult.with(context)
            .startActivityForResult(chooserIntent)
        val fileChooserResult = FileChooserResult.parse(
            resultCode = activityResult.resultCode,
            data = activityResult.data,
        )

        // 선택 X: kr.co.prnd.sample.FileChooserResult$Empty@cca43f2
        // 선택 O: File(resultCode=-1, data=Intent { dat=content://com.android.providers.media.documents/... flg=0x43 }, uri=content://com.android.providers.media.documents/document/image%3A1000004727)
        Log.i("TEST", "$fileChooserResult")
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
