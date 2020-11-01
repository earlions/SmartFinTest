package com.example.smartfintest
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.smartfintest.fragment.CheckFragment
import com.example.smartfintest.fragment.RangeFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), RangeFragment.OnFragmentInteractionListener,
    NavigationView.OnNavigationItemSelectedListener {
    val size: Int = 4
    var sum :String = ""
    var finalKol: Double = 0.00
    private var tv: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nav_view: NavigationView = findViewById(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener(this)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)!!.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
        Objects.requireNonNull(supportActionBar)!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        viewpager.adapter = object: FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {

                return size
            }

            override fun getItem(position: Int): Fragment {
               return RangeFragment().newInstance(position)
            }

            override fun getPageTitle(position: Int): CharSequence? {
                when(position){
                    0-> return "Все"
                    1-> return "Сочи"
                    2-> return "Россия"
                    3-> return "Булорусия"
                }
                return "Name $position"

            }
        }





    }
    fun onClick(view: View) {
        when(view.id){
            R.id.click0 -> sum += "0"
            R.id.click1 -> sum += "1"
            R.id.click2 -> sum += "2"
            R.id.click3 -> sum += "3"
            R.id.click4 -> sum += "4"
            R.id.click5 -> sum += "5"
            R.id.click6 -> sum += "6"
            R.id.click7 -> sum += "7"
            R.id.click8 -> sum += "8"
            R.id.click9 -> sum += "9"
            R.id.clickZ -> sum += "."
            R.id.clickX -> sum = ""
        }
        var kol:Int=0
        if (sum!="")
            kol = (sum.toDouble()*100).toInt()
        finalKol=kol.toDouble()/100
        if (tv==null)
           return
        tv!!.setText(sum)

    }

    override fun onFragmentInteraction(link: String, price:String) {
        val li = LayoutInflater.from(this)
        val promptsView: View = li.inflate(R.layout.dialog_input, null)
        val mDialogBuilder = AlertDialog.Builder(this)
        mDialogBuilder.setView(promptsView)
        tv =promptsView.findViewById(R.id.tv) as EditText
        mDialogBuilder
            .setCancelable(false)
            .setPositiveButton("Добавить",
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        sum=""
                        val fragment = supportFragmentManager
                            .findFragmentById(R.id.fragmentCheck) as CheckFragment
                        if (fragment.isInLayout) {
                            fragment.setText(link,price.toDouble(),finalKol)
                        }
                    }
                })
            .setNegativeButton("Отмена",
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        sum=""
                    }
                })
        val alertDialog = mDialogBuilder.create()
        alertDialog.show()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer_layout.closeDrawers()
        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        drawer_layout.openDrawer(GravityCompat.START)
        return true
    }

}


