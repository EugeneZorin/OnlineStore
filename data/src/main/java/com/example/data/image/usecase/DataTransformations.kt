package com.example.data.image.usecase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.widget.ImageView
import coil.load
import com.example.data.image.contract.RequestContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DataTransformations(
    private val requestContract: RequestContract
) {


    suspend fun downloadImage(urlString: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            var bitmap: Bitmap? = null
            var inputStream: InputStream? = null
            var connection: HttpURLConnection? = null

            for (url in requestContract.requestDatabase()){
                try {
                    val urlT = URL(urlString)
                    connection = urlT.openConnection() as HttpURLConnection
                    connection.doInput = true
                    connection.connect()
                    inputStream = connection.inputStream
                    bitmap = BitmapFactory.decodeStream(inputStream)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    connection?.disconnect()
                    inputStream?.close()
                }

            }
            bitmap
        }
    }
}