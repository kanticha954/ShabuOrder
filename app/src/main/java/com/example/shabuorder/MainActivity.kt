package com.example.shabuorder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {
//    var url = "https://api.airtable.com/v0/appa52TPaCAoceIzK/order_has_product"

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_main)
//        Log.d("Get", "Order has product" +)
        super.onCreate(savedInstanceState)
        run("https://api.airtable.com/v0/appa52TPaCAoceIzK")

    }

    //
    private fun run(url: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .header(
                "Authorization",
                "Bearer pat4gRWb9zHFmF6Rb.3d7f9b6dd4b62dae15ce0c21dbdd589b6add5c85409f69896137baa5357813ad"
            ).url("$url/order_has_product")
            .build()


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) =
                println(response.body?.string())
        })
    }

}
