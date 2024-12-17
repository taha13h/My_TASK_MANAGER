package com.example.my_task_manager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.PopupMenu

class TaskAdapter(
    private val taskList: MutableList<Task>,
    private val onTaskModified: (Task) -> Unit,
    private val onTaskDeleted: (Task) -> Unit

) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskCheckBox: CheckBox = itemView.findViewById(R.id.task_checkbox)
        val taskTitle: TextView = itemView.findViewById(R.id.task_title)
        val taskDescription: TextView = itemView.findViewById(R.id.task_description)
        val taskDate: TextView = itemView.findViewById(R.id.task_date)
        val taskOptions: ImageView = itemView.findViewById(R.id.task_options)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]

        holder.taskTitle.text = task.title
        holder.taskDescription.text = task.description
        holder.taskDate.text = task.date
        holder.taskCheckBox.isChecked = task.isCompleted

        // Marquer une tâche comme terminée/non terminée
        holder.taskCheckBox.setOnCheckedChangeListener { _, isChecked ->
            // Créer une copie de la tâche et mettre à jour l'état
            val modifiedTask = task.copy(isCompleted = isChecked)
            onTaskModified(modifiedTask) // Passer la tâche modifiée
            taskList[position] = modifiedTask // Mettre à jour la liste
            notifyItemChanged(position) // Notifier l'adaptateur du changement
        }



        // Afficher le menu des options (modifier ou supprimer une tâche)
        holder.taskOptions.setOnClickListener { view ->
            val popupMenu = PopupMenu(view.context, view)
            popupMenu.inflate(R.menu.task_menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_edit -> {
                        onTaskModified(task)
                        true
                    }
                    R.id.action_delete -> {
                        taskList.removeAt(position)
                        notifyItemRemoved(position) // Notifier l'adaptateur de la suppression
                        onTaskDeleted(task)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }



    override fun getItemCount(): Int = taskList.size
}
