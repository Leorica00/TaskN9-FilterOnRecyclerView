package com.example.taskn9

import CenterItemDecoration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskn9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var clothesAdapter: ClothesRecyclerViewAdapter
    private lateinit var categoryAdapter: CategoryRecyclerViewAdapter
    private lateinit var clothesRecyclerView: RecyclerView
    private lateinit var categoryRecyclerView: RecyclerView
    private val listOfClothes = mutableListOf<Clothes>()
    private val categories = listOf(CategoryType.All, CategoryType.PARTY, CategoryType.CAMPING, CategoryType.CLASSIC, CategoryType.HOODIES, CategoryType.CASUAL)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        clothesRecyclerView = binding.clothesRecycler
        categoryRecyclerView = binding.categoryRecycler
        setContentView(binding.root)
        setUp()
    }

    private fun setUp() {
        giveData()
        setUpClothesRecycler()
        setUpCategoryRecycler()
    }

    private fun filterOnCategory(categoryButton: AppCompatButton, categoryType: CategoryType) {
        if (categoryType == CategoryType.All) {
            clothesAdapter.setData(listOfClothes)
            changeSelected(categoryButton)
        } else {
            val categoryList =
                listOfClothes.filter { it.categoryType == categoryType }.toMutableList()
            clothesAdapter.setData(categoryList)
            changeSelected(categoryButton)
        }
    }

    private fun setUpClothesRecycler() {
        clothesAdapter = ClothesRecyclerViewAdapter()
        clothesRecyclerView.layoutManager = GridLayoutManager(this, 2)
        clothesRecyclerView.addItemDecoration(CenterItemDecoration(2, 20))
        clothesRecyclerView.adapter = clothesAdapter
        clothesAdapter.setData(listOfClothes)
    }

    private fun setUpCategoryRecycler() {
        categoryAdapter = CategoryRecyclerViewAdapter()
        categoryRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        categoryRecyclerView.adapter = categoryAdapter
        categoryAdapter.setData(categories)
        categoryAdapter.onItemClick = { button: AppCompatButton, category: CategoryType ->
            filterOnCategory(button, category)
        }
    }

    private fun giveData() {
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

    private fun changeSelected(buttonToChange: AppCompatButton) {
        buttonToChange.setBackgroundResource(R.drawable.selected_category_background)
        buttonToChange.setTextColor(resources.getColor(R.color.white, theme))
    }

}