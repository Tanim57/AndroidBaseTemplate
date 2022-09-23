package com.tanim.androidbasetemplate.utils

import com.tanim.androidbasetemplate.utils.DateFormationUtils
import kotlin.Throws
import com.tanim.androidbasetemplate.utils.ConverterUtils
import android.text.TextUtils
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Bitmap.CompressFormat
import android.util.Base64
import com.google.android.gms.common.util.Base64Utils
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.lang.StringBuilder
import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.*

//import org.opencv.android.Utils;
//import org.opencv.core.Mat;
object ConverterUtils {
    fun getLocalTime(time: Long): LocalTime {
        return Date(time).toInstant().atZone(ZoneId.systemDefault()).toLocalTime()
    }

    fun getLocalDateTime(time: Long): LocalDateTime {
        return Date(time).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
    }

    fun getLocalDtae(time: Long): LocalDate {
        return Date(time).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    }

    fun getTimeStampFromDateString(dateString: String?, format: String?): Long {
        val date = DateFormationUtils.getDateFromString(dateString, format) ?: return -1
        return date.time
    }

    fun isExpired(currentTime: Date?, dateString: String?, format: String?): Boolean {
        val date = DateFormationUtils.getDateFromString(dateString, format)
        return if (date == null || currentTime == null) false else currentTime.time > date.time
    }

    fun getTitleWithPlus(str: String?): String? {
        if (str == null) {
            return null
        }
        var space = true
        val builder = StringBuilder(str)
        val len = builder.length
        for (i in 0 until len) {
            val c = builder[i]
            if (space) {
                if (!Character.isWhitespace(c)) {
                    // Convert to title case and switch out of whitespace mode.
                    builder.setCharAt(i, Character.toTitleCase(c))
                    space = false
                }
            } else if (Character.isWhitespace(c)) {
                space = true
                builder.setCharAt(i, '+')
            } else {
                builder.setCharAt(i, Character.toLowerCase(c))
            }
        }
        return builder.toString()
    }

    @Throws(IOException::class)
    fun getBytes(inputStream: InputStream): ByteArray {
        val byteBuffer = ByteArrayOutputStream()
        val bufferSize = 1024
        val buffer = ByteArray(bufferSize)
        var len = 0
        while (inputStream.read(buffer).also { len = it } != -1) {
            byteBuffer.write(buffer, 0, len)
        }
        return byteBuffer.toByteArray()
    }

    @JvmStatic
    fun toTitleCase(str: String?): String? {
        if (str == null) {
            return null
        }
        var space = true
        val builder = StringBuilder(str)
        val len = builder.length
        for (i in 0 until len) {
            val c = builder[i]
            if (space) {
                if ( /*!Character.isWhitespace(c) */Character.isLetter(c)) {
                    // Convert to title case and switch out of whitespace mode.
                    builder.setCharAt(i, Character.toTitleCase(c))
                    space = false
                }
            } else if (Character.isWhitespace(c) or (c == '(') or (c == ')') or (c == '\\')) {
                space = true
            } else {
                builder.setCharAt(i, Character.toLowerCase(c))
            }
        }
        return builder.toString()
    }

    fun toCamelCase(s: String?): String? {
        if (s == null) return s
        val parts = s.split(" ").toTypedArray()
        val camelCaseString = StringBuilder()
        for (part in parts) {
            camelCaseString.append(toProperCase(part))
        }
        return camelCaseString.toString()
    }

    fun toProperCase(s: String): String {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase()
    }

    fun convertPassMd5(pass: String): String? {
        return hashString(
            pass,
            "MD5"
        )
    }

    fun convertPassSHA256(pass: String): String? {
        return hashString(
            pass,
            "SHA-256"
        )
    }

    fun hashString(pass: String, type: String?): String? {
        var pass = pass
        var password: String? = null
        val mdEnc: MessageDigest
        try {
            mdEnc = MessageDigest.getInstance(type)
            mdEnc.update(pass.toByteArray(), 0, pass.length)
            pass = BigInteger(1, mdEnc.digest()).toString(16)
            while (pass.length < 32) {
                pass = "0$pass"
            }
            password = pass
        } catch (e1: NoSuchAlgorithmException) {
            e1.printStackTrace()
        }
        return password
    }

    fun getNullFromEmpty(value: String): String? {
        return if (value.isEmpty()) null else value
    }

    fun mergeName(firstName: String, lastName: String): String? {
        if (TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName)) {
            return null
        }
        if (TextUtils.isEmpty(firstName)) {
            return lastName
        }
        return if (TextUtils.isEmpty(lastName)) {
            firstName
        } else "$firstName $lastName".trim { it <= ' ' }
    }

    fun bitmapToByteArray(bitmap: Bitmap, factor: Int): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(CompressFormat.PNG, factor, stream)
        return stream.toByteArray()
    }

    fun byteArrayToBitmap(photoData: ByteArray?): Bitmap? {
        return if (photoData == null) null else BitmapFactory.decodeByteArray(
            photoData,
            0,
            photoData.size
        )
    }

    fun bitmapToByteArray(bitmap: Bitmap, factor: Int, format: CompressFormat?): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(format, factor, stream)
        return stream.toByteArray()
    }

    fun getBitmapFromString(base64String: String?): Bitmap? {
        if (base64String == null) return null
        val decodedString = Base64.decode(base64String, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    fun getEncodedStringFromBitmap(bitmap: Bitmap, format: CompressFormat?): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(format, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return getEncodedStringFromByteArray(byteArray)
    }

    //    public static String getEncodedStringFromByteArray(byte[] data){
    //
    //        if (data != null && data.length > 0){
    //            return Base64.encodeToString(data, Base64.NO_PADDING);
    //        }
    //        return null;
    //    }
    fun getBase64Image(imageBytes: ByteArray?): String {
        return if (imageBytes == null) "" else try {
            //            base64 = "data:image/jpeg;base64," + base64;
            Base64Utils.encode(imageBytes)
        } catch (ex: Exception) {
            ""
        }
    }

    @JvmStatic
    fun getEncodedStringFromByteArray(byteArray: ByteArray?): String {
        val encoded = Base64.encodeToString(byteArray, Base64.NO_WRAP)
        val ptext = encoded.toByteArray()
        return String(ptext, StandardCharsets.UTF_8)
        //return encoded;
    }

    fun isValidData(field: String?): Boolean {
        if (field == null) {
            return false
        }
        return if (field.isEmpty()) {
            false
        } else true
    }

    @JvmStatic
    fun convertEngToBnNumber(numberString: String): String {
        return numberString
            .replace("0".toRegex(), "০")
            .replace("1".toRegex(), "১")
            .replace("2".toRegex(), "২")
            .replace("3".toRegex(), "৩")
            .replace("4".toRegex(), "৪")
            .replace("5".toRegex(), "৫")
            .replace("6".toRegex(), "৬")
            .replace("7".toRegex(), "৭")
            .replace("8".toRegex(), "৮")
            .replace("9".toRegex(), "৯")
    }

    fun convertBnToEnNumber(numberString: String?): String? {
        return numberString?.replace("০".toRegex(), "0")?.replace("১".toRegex(), "1")
            ?.replace("২".toRegex(), "2")?.replace("৩".toRegex(), "3")?.replace("৪".toRegex(), "4")
            ?.replace("৫".toRegex(), "5")?.replace("৬".toRegex(), "6")?.replace("৭".toRegex(), "7")
            ?.replace("৮".toRegex(), "8")?.replace("৯".toRegex(), "9")
    } //    public static Bitmap convertMat2Bitmap(Mat mat){
    //        Bitmap bmp = Bitmap.createBitmap(mat.width(),mat.height(),Bitmap.Config.ARGB_8888);
    //        Utils.matToBitmap(mat,bmp);
    //        return bmp;
    //    }
    //    public static Mat convertBitmap2Mat(Bitmap bmp){
    //        Mat mat = new Mat();
    //        Utils.bitmapToMat(bmp, mat);
    //        return mat;
    //    }
}