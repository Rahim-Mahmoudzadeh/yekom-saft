package ir.rahimmahmuodzadeh.yekomsaft.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.rahimmahmuodzadeh.yekomsaft.data.model.Contact
import ir.rahimmahmuodzadeh.yekomsaft.databinding.ContactRvBinding

class AdapterRvHome : RecyclerView.Adapter<AdapterRvHome.ViewHolder>() {

    var contact = ArrayList<Contact>()
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
       fun bind(contact: Contact)
       {

       }
    }
}