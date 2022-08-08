package com.ivanmanzaba.men_dinmico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    lateinit var credenciales: JSONArray
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.credenciales = JSONArray(
            """[{"usuario":"cliente1", "clave":"12345", "rol":"cliente", "items": ["item 1", "item 2", "item 3", "item 4", "item 5" , "item 6", "item 7", "item 8", "item 9"] , "avatar":"https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/styles/480/public/media/image/2022/03/avatar-facebook-2632445.jpg?itok=lsD0KFxA"},
            |{"usuario":"admin1", "clave":"12345", "rol":"admin","items": ["Usuarios", "Productos", "Proveedores"], "avatar":"https://www.ekosnegocios.com/image/posts/February2022/hzbjmecHf7TgBQ3EyajA.jpg" },
            | {"usuario":"cliente2", "clave":"54321", "rol":"cliente", "items": ["item - cliente 100", "item - cliente 200", "item - cliente 300"], "avatar":"https://www.informador.mx/__export/1591209620028/sites/elinformador/img/2020/06/03/whatsapp_image_2020-06-03_at_1_22_36_pm_x1x_crop1591209586178.jpg_788543494.jpg"}]""".trimMargin()
        )
    }

    public fun loguear(view: View) {
        val user = findViewById<TextView>(R.id.txtUser)
        val password = findViewById<TextView>(R.id.txtPassword)
        var indice = -1
        println(user.text)
        println(password.text)
        for (i in 0 until this.credenciales.length()) {

            if (this.credenciales.getJSONObject(i).getString("usuario").toString() == user.text.toString()) {
                if (this.credenciales.getJSONObject(i).getString("clave").toString() ==password.text.toString())
                    indice = i
                else
                    break
                break
            }
        }
        if (indice > -1) {
            val intent = Intent(this, MainActivity2::class.java)
            val bundle = Bundle()
            var items: ArrayList<String>? = ArrayList()
            for (i in 0 until this.credenciales.getJSONObject(indice).getJSONArray("items").length()) {
                items?.add(this.credenciales.getJSONObject(indice).getJSONArray("items")[i].toString());

            }
            println(items)
            bundle.putString("user",user.text.toString())
            bundle.putString("avatar", this.credenciales.getJSONObject(indice).getString("avatar"))
            bundle.putStringArrayList("items", items)
            bundle.putString("rol", this.credenciales.getJSONObject(indice).getString("rol"))
            intent.putExtras(bundle)
            startActivity(intent)
        }
        else
            print("Credenciales incorrectas")
    }
}