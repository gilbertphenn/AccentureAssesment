package gilbert.assessment.gilbertaccentureassessment.ui.fragment.userList.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import gilbert.assessment.gilbertaccentureassessment.R
import gilbert.assessment.gilbertaccentureassessment.model.UserData

class UserAdapter(var activity: Activity, private var data: MutableList<UserData>, var listener: AdapterItemOnClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface AdapterItemOnClick {
        fun onItemClick(name: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = Item(LayoutInflater.from(activity).inflate(R.layout.adapter_user, parent, false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Item) {
            holder.apply {
                val data = data[position]
                tvUser.text = "@${data.login}"
                Glide.with(activity)
                    .load(data.avatar_url)
                    .into(civProfile)

            }
        }
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filteredList: MutableList<UserData>) {
        data = filteredList
        notifyDataSetChanged()
    }

    private inner class Item(view: View) : RecyclerView.ViewHolder(view) {
        val civProfile: CircleImageView = view.findViewById(R.id.civProfile)
        val tvUser: TextView = view.findViewById(R.id.tvUser)
        val tvLink: TextView = view.findViewById(R.id.tvLink)

        init {
            view.setOnClickListener {
                listener.onItemClick(data[layoutPosition].login)
            }
        }
    }
}