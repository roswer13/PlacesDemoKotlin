package com.example.rjgarcia.placesdemokotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.google.android.gms.location.places.AutocompleteFilter
import com.google.android.gms.location.places.ui.PlaceAutocomplete

class MainActivity : AppCompatActivity() {

    companion object {
        const val PLACE_AUTOCOMPLETE_REQUEST_CODE = 12
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val place = PlaceAutocomplete.getPlace(this, data)
                    val textUbication = findViewById<TextView>(R.id.tv_info_place)
                    textUbication.text = place.address
                }
                PlaceAutocomplete.RESULT_ERROR -> {
                    val status = PlaceAutocomplete.getStatus(this, data)
                }
                Activity.RESULT_CANCELED -> {
                    // TODO: 07/09/2018
                }
            }
        }
    }

    fun callPlaceAutocompleteActivityIntent(view: View) {
        val typeFilter = AutocompleteFilter.Builder()
            .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
            .setCountry("ES")
            .build()

        val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
            .setFilter(typeFilter)
            .build(this)

        startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE)
    }

}
