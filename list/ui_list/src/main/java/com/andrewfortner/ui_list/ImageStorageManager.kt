package com.andrewfortner.ui_list

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.graphics.scale
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.util.*

object ImageStorageManager {
    fun saveToInternalStorage(context: Context, bitmapImage: Bitmap, imageFileName: String): String {
        context.openFileOutput(imageFileName, Context.MODE_PRIVATE).use { fos ->
            bitmapImage
                .scale(width = 512, height = 512, filter = false)
                .compress(Bitmap.CompressFormat.PNG, 25, fos)
        }
        return context.filesDir.absolutePath
    }

    fun getImageFromInternalStorage(context: Context, imageFileName: String): Bitmap? {
        if(imageFileName.isBlank())
            return null
        val directory = context.filesDir
        val file = File(directory, imageFileName)
        return BitmapFactory.decodeStream(FileInputStream(file))
    }

    fun deleteImageFromInternalStorage(context: Context, imageFileName: String): Boolean {
        val dir = context.filesDir
        val file = File(dir, imageFileName)
        return file.delete()
    }

        fun stringToBitMap(encodedString: String?): Bitmap? {
        return try {
            val encodeByte: ByteArray = Base64.getDecoder().decode(encodedString)
            BitmapFactory.decodeByteArray(
                encodeByte, 0,
                encodeByte.size
            )
        } catch (e: Exception) {
            e.message
            null
        }
    }

        fun bitMapToString(bitmap: Bitmap?): String? {
        if(bitmap == null)
            return null

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b: ByteArray = baos.toByteArray()
        return Base64.getEncoder().encodeToString(b)
    }
}