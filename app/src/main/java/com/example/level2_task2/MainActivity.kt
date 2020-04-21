package com.example.level2_task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.level2_task2.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        recyclerView = findViewById(R.id.rvQuestions)
        createItemTouchHelper().attachToRecyclerView(recyclerView)

    }

    private fun initViews() {
        binding.rvQuestions.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvQuestions.adapter = questionAdapter

        for (i in Question.QUESTIONS.indices) {
            questions.add(Question(Question.QUESTIONS[i], Question.ANSWERS[i]))
        }
        questionAdapter.notifyDataSetChanged()
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                //left == false
                if(direction == ItemTouchHelper.LEFT ){
                    if(!questions[position].answer){
                        onCorrect(position)
                    } else {
                       onIncorrect()
                    }
                    //right = true
                } else {
                    if(questions[position].answer){
                        onCorrect(position)
                    } else {
                        onIncorrect()
                    }
                }
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun onIncorrect() {
        Snackbar.make(binding.clLayoutWrapper, "incorrect answer, question is not removed from the list", Snackbar.LENGTH_SHORT).show()
        questionAdapter.notifyDataSetChanged()

    }

    private fun onCorrect(position: Int) {
       questions.remove(questions[position])
        questionAdapter.notifyDataSetChanged()
    }


}
