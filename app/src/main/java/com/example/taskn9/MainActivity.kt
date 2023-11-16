package com.example.taskn9

import CenterItemDecoration
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskn9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ClothesRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private val listOfClothes = mutableListOf<Clothes>()
    private lateinit var selectedButton: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        recyclerView = binding.clothesRecycler
        setContentView(binding.root)
        setUp()
        listeners()
    }

    private fun setUp(){
        giveData()
        setUpClothesRecycler()
        selectedButton = binding.fillterAllBtn
        selectedButton.setBackgroundResource(R.drawable.selected_category_background)
        selectedButton.setTextColor(resources.getColor(R.color.white, theme))
    }

    private fun listeners(){
        filterOnAll()
        filterOnCategory(binding.filterPartyBtn, CategoryType.PARTY)
        filterOnCategory(binding.filterCampingBtn, CategoryType.CAMPING)
        filterOnCategory(binding.filterClassicBtn, CategoryType.CLASSIC)
        filterOnCategory(binding.filterHoodiesBtn, CategoryType.HOODIES)
        filterOnCategory(binding.filterCasualBtn, CategoryType.CASUAL)
    }

    private fun filterOnAll(){
        binding.fillterAllBtn.setOnClickListener {
            adapter.setData(listOfClothes)
            changeSelected(binding.fillterAllBtn)
        }
    }

    private fun filterOnCategory(categoryButton: AppCompatButton, categoryType: CategoryType){
        categoryButton.setOnClickListener {
            val categoryList = listOfClothes.filter { it.categoryType == categoryType }.toMutableList()
            adapter.setData(categoryList)
            changeSelected(categoryButton)
        }
    }

    private fun setUpClothesRecycler(){
        adapter = ClothesRecyclerViewAdapter()
        binding.clothesRecycler.layoutManager = GridLayoutManager(this, 2)
        binding.clothesRecycler.addItemDecoration(CenterItemDecoration(2, 20))
        binding.clothesRecycler.adapter = adapter
        adapter.setData(listOfClothes)
    }

    private fun giveData(){
        listOfClothes.add(Clothes(R.drawable.yellow_image, "Belt suit blazer", "$100", CategoryType.PARTY ))
        listOfClothes.add(Clothes(R.drawable.black_image, "Belt suit blazer", "$125", CategoryType.HOODIES ))
        listOfClothes.add(Clothes(R.drawable.blue_image, "Belt suit blazer", "$130", CategoryType.CAMPING ))
        listOfClothes.add(Clothes(R.drawable.red_image, "Belt suit blazer", "$145", CategoryType.CLASSIC ))
        listOfClothes.add(Clothes(R.drawable.yellow_image, "Belt suit blazer", "$120", CategoryType.HOODIES ))
        listOfClothes.add(Clothes(R.drawable.black_image, "Belt suit blazer", "$125", CategoryType.CAMPING ))
        listOfClothes.add(Clothes(R.drawable.blue_image, "Belt suit blazer", "$180", CategoryType.HOODIES ))
        listOfClothes.add(Clothes(R.drawable.red_image, "Belt suit blazer", "$150", CategoryType.CASUAL ))
        listOfClothes.add(Clothes(R.drawable.yellow_image, "Belt suit blazer", "$135", CategoryType.CASUAL ))
        listOfClothes.add(Clothes(R.drawable.black_image, "Belt suit blazer", "$125", CategoryType.CASUAL ))
        listOfClothes.add(Clothes(R.drawable.blue_image, "Belt suit blazer", "$120", CategoryType.PARTY ))
        listOfClothes.add(Clothes(R.drawable.red_image, "Belt suit blazer", "$110", CategoryType.CAMPING ))
    }

    @SuppressLint("ResourceAsColor")
    private fun changeSelected(buttonToChange: AppCompatButton){
        selectedButton.setBackgroundResource(R.drawable.unselected_category_background)
        selectedButton.setTextColor(resources.getColor(R.color.light_grey, theme))
        selectedButton = buttonToChange
        selectedButton.setBackgroundResource(R.drawable.selected_category_background)
        selectedButton.setTextColor(resources.getColor(R.color.white, theme))
    }

}