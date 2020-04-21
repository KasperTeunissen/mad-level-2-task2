package com.example.level2_task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.item_question.view.*

class QuestionAdapter(private val questions: List<Question>) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(question: Question) {
            itemView.tvQuestion.text = question.questionText
            itemView.setOnClickListener { itemClicked(question) }

        }

        private fun itemClicked(question: Question) {
            Toast.makeText(itemView.context, "The answer is: " + question.answer.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * returns a ViewHolder inflating the item_question layout
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    /**
     * used by RecyclerView to bind data to specific indices
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

}