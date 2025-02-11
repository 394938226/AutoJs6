package org.autojs.autojs.ui.settings

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.autojs.autojs.app.GlobalAppContext
import org.autojs.autojs.ui.BaseActivity
import org.autojs.autojs6.R

/**
 * Created by Stardust on 2017/2/2.
 * Modified by SuperMonster003 as of Dec 1, 2021.
 */
@EActivity(R.layout.activity_settings)
open class SettingsActivity : BaseActivity() {

    @AfterViews
    fun setUpUI() {
        setUpToolbar()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_setting, SettingsFragment())
            .disallowAddToBackStack()
            .commit()
    }

    private fun setUpToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle(R.string.text_settings)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
    }

    companion object {

        @JvmStatic
        @JvmOverloads
        fun launch(context: Context? = GlobalAppContext.get()) = try {
            SettingsActivity_.intent(context)
                .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .start()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    }

}