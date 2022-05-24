package com.example.kmarket.view.adapter
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kmarket.R
import com.example.kmarket.databinding.ListElementBinding
import com.example.kmarket.model.Products


class Adaptador(context: Context, products: List<Products>, listener: OnItemListener): RecyclerView.Adapter<Adaptador.ViewHolder>() {

    private val mProducts = products
    private val mListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adaptador.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = ListElementBinding.inflate(layoutInflater)

        return ViewHolder(binding, mListener)
    }


    override fun onBindViewHolder(holder: Adaptador.ViewHolder, position: Int) {
        holder.bindData(mProducts[position])
    }

    override fun getItemCount(): Int {
        return mProducts.size
    }

    interface OnItemListener{
        fun onItemClick(product: Products)
    }

    class ViewHolder(binding: ListElementBinding, listener: OnItemListener): RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        private val ivProduct = binding.ivProduct
        private val tvTitle = binding.tvTitle
        private val tvProvider = binding.tvProvider
        private val tvPrice = binding.tvPrice
        private val tvDelivery = binding.tvDelivery

        private val context = binding.root.context
        private val listener = listener
        private lateinit var product: Products
        //Constructor que se ejecuta cada vez que se instancia
        init {
            binding.root.setOnClickListener(this)
        }

        fun bindData(item: Products){
            tvTitle.text = item.name
            tvProvider.text = item.prov
            tvPrice.text = context.getString(R.string.signo, item.price)


            if (item.cdelivery == "0.00") tvDelivery.text = context.getString(R.string.gratis)
            else{
                tvDelivery.text = context.getString(R.string.signo, item.cdelivery)
            }

            //glide para image view
            Glide.with(context)
                .load(item.img)
                .into(ivProduct)
            product = item
        }

        override fun onClick(p0: View?) {
            listener.onItemClick(product)
        }




    }

}