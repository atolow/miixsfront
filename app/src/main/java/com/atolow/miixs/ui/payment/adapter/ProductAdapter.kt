package com.atolow.miixs.ui.payment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.atolow.miixs.data.model.payment.ProductResponseDto
import com.atolow.miixs.databinding.ItemProductBinding
import java.text.NumberFormat
import java.util.Locale

class ProductAdapter(
    private val onProductClick: (ProductResponseDto) -> Unit
) : ListAdapter<ProductResponseDto, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding, onProductClick)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductViewHolder(
        private val binding: ItemProductBinding,
        private val onProductClick: (ProductResponseDto) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductResponseDto) {
            binding.tvProductName.text = product.name
            binding.tvCashAmount.text = "${formatNumber(product.cashAmount)} 믹시즈"
            binding.tvAmount.text = "${formatNumber(product.amount)}원"

            // 클릭 리스너를 별도 클래스로 분리 (ProGuard 대응)
            val clickListener = ProductClickListener(binding.root.context, product, onProductClick)
            binding.root.setOnClickListener(clickListener)
            
            // 클릭 가능하도록 설정
            binding.root.isClickable = true
            binding.root.isFocusable = true
        }

        private fun formatNumber(number: Long): String {
            return NumberFormat.getNumberInstance(Locale.KOREA).format(number)
        }
    }
    
    // 클릭 리스너를 별도 클래스로 분리 (ProGuard 대응)
    class ProductClickListener(
        private val context: android.content.Context,
        private val product: ProductResponseDto,
        private val onProductClick: (ProductResponseDto) -> Unit
    ) : android.view.View.OnClickListener {
        override fun onClick(v: android.view.View?) {
            try {
                onProductClick(product)
            } catch (e: Exception) {
                android.util.Log.e("ProductAdapter", "onProductClick 호출 중 오류 발생", e)
            }
        }
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<ProductResponseDto>() {
        override fun areItemsTheSame(oldItem: ProductResponseDto, newItem: ProductResponseDto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductResponseDto, newItem: ProductResponseDto): Boolean {
            return oldItem == newItem
        }
    }
}

