package ir.rahimmahmuodzadeh.yekomsaft.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.rahimmahmuodzadeh.yekomsaft.R
import ir.rahimmahmuodzadeh.yekomsaft.data.model.Contact
import ir.rahimmahmuodzadeh.yekomsaft.databinding.ContactRvBinding

class AdapterHome(val detail: Detail): RecyclerView.Adapter<AdapterHome.ViewHolder>() {

    var contact = ArrayList<Contact>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ContactRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contact[position])
    }

    override fun getItemCount(): Int = contact.size
    inner class ViewHolder(itemView: ContactRvBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val item = itemView
        fun bind(contact: Contact) {
            item.tvName.text =
                itemView.context.resources.getString(R.string.firstName) + " " + contact.firstName + " " + contact.lastName
            item.tvNumber.text =
                itemView.context.resources.getString(R.string.number) + " " + contact.number
            itemView.setOnClickListener {
                  detail.show(contact)
            }
            itemView.setOnLongClickListener {
                detail.update(contact)
                false
            }

        }
    }
    interface Detail{
        fun show(contact: Contact)
        fun update(contact: Contact)
    }
}