package kr.co.prnd.sample

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import com.gun0912.tedonactivityresult.coroutine.TedOnActivityResult

class FileChooser(
    private val context: Context,
) {
    suspend fun show(
        filePathCallback: ValueCallback<Array<Uri>>,
    ) {
        val chooserIntent = createChooserIntent()
        val activityResult = TedOnActivityResult.with(context)
            .startActivityForResult(chooserIntent)
        val fileChooserResult = FileChooserResult.parse(
            resultCode = activityResult.resultCode,
            data = activityResult.data,
        )
        handleFileChooserResult(
            fileChooserResult = fileChooserResult,
            filePathCallback = filePathCallback,
        )
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

    private fun handleFileChooserResult(
        fileChooserResult: FileChooserResult,
        filePathCallback: ValueCallback<Array<Uri>>,
    ) {
        when (fileChooserResult) {
            FileChooserResult.Empty -> filePathCallback.onReceiveValue(null)
            is FileChooserResult.File -> {
                takePersistableUriPermission(fileChooserResult.uri)
                val result = WebChromeClient.FileChooserParams.parseResult(
                    fileChooserResult.resultCode,
                    fileChooserResult.data,
                )
                filePathCallback.onReceiveValue(result)
            }
        }
    }

    private fun takePersistableUriPermission(uri: Uri) {
        val takeFlags =
            Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        context.contentResolver.takePersistableUriPermission(uri, takeFlags)
    }
}
