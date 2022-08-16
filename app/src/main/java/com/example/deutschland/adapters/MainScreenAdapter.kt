package com.example.deutschland.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deutschland.R
import com.example.deutschland.databinding.CourseRvItemBinding
import com.example.deutschland.model.Result

interface MainOnClickListener {
    fun onClickOnItem(result: Result)
}

class UsersDiffCallback(private val oldList: List<Result>, private  val newList: List<Result>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser.id == newUser.id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser == newUser
    }

}

class MainScreenAdapter(private val listener: MainOnClickListener) : RecyclerView.Adapter<MainScreenAdapter.CourseRVViewHolder>() {
    private var listOfCourse = listOf<Result>()

    class CourseRVViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding by lazy { CourseRvItemBinding.bind(item) }

        fun bind(result: Result, listener: MainOnClickListener) {
            if (result.id == 15 || result.id == 2) {
                Log.d("MyLog", "LOG:${result.id} $result")
            }
            binding.nameebs.text = result.name
            binding.details.text = result.details
            binding.color.text = result.colour
            binding.size.text = result.size
            binding.price.text = result.price.toString()
            Glide.with(binding.root).load(result.main_image).into(binding.idIVCourse)
            binding.root.setOnClickListener {
                listener.onClickOnItem(result)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseRVViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.course_rv_item, parent, false)
        return CourseRVViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseRVViewHolder, position: Int) {
        holder.bind(listOfCourse[position], listener)
    }

    override fun getItemCount() = listOfCourse.size

    fun setNewList(newListOfItems: List<Result>) {
        val diffCallback = UsersDiffCallback(oldList = listOfCourse, newList = newListOfItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listOfCourse = newListOfItems
        diffResult.dispatchUpdatesTo(this)
    }
}