package com.example.shabuorderv1

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.loader.content.AsyncTaskLoader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonFileString = getJsonDataFromAsset(applicationContext, "pdDataset.json")
        if (jsonFileString != null) {
            Log.i("data", jsonFileString)
        }

        val gson = Gson()
        val listProductData = object : TypeToken<List<ProductData>>() {}.type

        val products: List<ProductData> = gson.fromJson(jsonFileString, listProductData)
        products.forEachIndexed{ index: Int, _: ProductData
            ->  Log.i("data","> Product $index:\n$products")}

        print(products[0])
        Log.i("data", "Product[0] : " + products[0].product_name)

        title = "KotlinApp"
        DownloadImageFromInternet(findViewById(R.id.imageView)).execute(products[0].img) //เลือก id รูป กับ id pd
        DownloadImageFromInternet(findViewById(R.id.imageView2)).execute(products[2].img)


        val product1 = ProductData(5, "หมูสามชั้น", "https://www.hatyaifocus.com/ckeditor/upload/forums/5/Na/%E0%B8%A3%E0%B9%89%E0%B8%B2%E0%B8%99%E0%B8%82%E0%B8%B2%E0%B8%A2%E0%B8%AB%E0%B8%A1%E0%B8%B9/W%20Oleng-21.jpg",1)
        insertDatainJson(product1)

        Log.i("dataNew","> Product $products")

    }
    @SuppressLint("StaticFieldLeak")
    @Suppress("DEPRECATION")
    private  inner class DownloadImageFromInternet(var imageView: ImageView) : AsyncTask<String, Void, Bitmap?>(){
        init {
            Toast.makeText(applicationContext, "Please Wait",Toast.LENGTH_SHORT).show()
        }
        override fun doInBackground(vararg urls: String): Bitmap?{
            val imageURL = urls[0]
            var image: Bitmap? = null
            try {
                val  `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream( `in`)
            } catch (e: Exception) {
                Log.e("Error Message", e.message.toString())
                e.printStackTrace()
            }
            return image
        }
        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
        }
    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String?{
        val jsonString: String

        try{
            jsonString = context.assets.open(fileName).bufferedReader().use{
                it.readText()
            }
        } catch (ioException : IOException){
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun insertDatainJson(addData : ProductData){
//        val product1 = ProductData(5, "หมูสามชั้น", "https://www.hatyaifocus.com/ckeditor/upload/forums/5/Na/%E0%B8%A3%E0%B9%89%E0%B8%B2%E0%B8%99%E0%B8%82%E0%B8%B2%E0%B8%A2%E0%B8%AB%E0%B8%A1%E0%B8%B9/W%20Oleng-21.jpg",1)
        val path = "C:/Users/USER/AndroidAppProj/app/src/main/assets/pdDataset.json"
        try {
            PrintWriter(FileWriter(path)).use {
                val gson = Gson()
                val jsonString = gson.toJson(addData)
                it.write(jsonString)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    }

