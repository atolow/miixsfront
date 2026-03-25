package com.atolow.miixs.ui.payment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.atolow.miixs.R
import com.atolow.miixs.data.model.payment.PaymentResponseDto
import com.atolow.miixs.databinding.ItemPaymentHistoryBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class PaymentHistoryAdapter : ListAdapter<PaymentResponseDto, PaymentHistoryAdapter.PaymentHistoryViewHolder>(
    PaymentHistoryDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentHistoryViewHolder {
        val binding = ItemPaymentHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PaymentHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentHistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PaymentHistoryViewHolder(
        private val binding: ItemPaymentHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(payment: PaymentResponseDto) {
            // 결제 수단 표시
            binding.tvPaymentMethod.text = when (payment.paymentMethod) {
                "GOOGLE_PLAY" -> "구글 플레이"
                else -> payment.paymentMethod
            }

            // 상태 표시
            val statusText = when (payment.status) {
                "COMPLETED" -> "완료"
                "PENDING" -> "대기"
                "FAILED" -> "실패"
                "CANCELLED" -> "취소"
                else -> payment.status
            }

            binding.tvStatus.text = statusText

            // 상태에 따른 텍스트 색상 변경
            val statusColor = when (payment.status) {
                "COMPLETED" -> ContextCompat.getColor(binding.root.context, com.atolow.miixs.R.color.status_complete)
                "PENDING" -> ContextCompat.getColor(binding.root.context, com.atolow.miixs.R.color.status_pending)
                "FAILED", "CANCELLED" -> ContextCompat.getColor(binding.root.context, com.atolow.miixs.R.color.status_failed)
                else -> ContextCompat.getColor(binding.root.context, android.R.color.darker_gray)
            }

            val statusBg = when (payment.status) {
                "COMPLETED" -> com.atolow.miixs.R.drawable.bg_status_complete
                "PENDING" -> com.atolow.miixs.R.drawable.bg_status_pending
                "FAILED", "CANCELLED" -> com.atolow.miixs.R.drawable.bg_status_failed
                else -> com.atolow.miixs.R.drawable.bg_status_complete
            }

            binding.tvStatus.setTextColor(statusColor)
            binding.tvStatus.setBackgroundResource(statusBg)

            // 금액 표시
            binding.tvAmount.text = "${formatNumber(payment.amount)}원"

            // 충전된 캐시 표시
            binding.tvCashAmount.text = "${formatNumber(payment.cashAmount)} 믹시즈 충전"

            // 날짜 표시
            payment.completedAt?.let {
                binding.tvDate.text = formatDate(it)
            } ?: run {
                payment.createdAt?.let {
                    binding.tvDate.text = formatDate(it)
                } ?: run {
                    binding.tvDate.text = ""
                }
            }
        }

        private fun formatNumber(number: Long): String {
            return NumberFormat.getNumberInstance(Locale.KOREA).format(number)
        }

        private fun formatDate(dateString: String): String {
            return try {
                // 서버에서 UTC 시간으로 보내므로 UTC로 파싱
                // 다양한 날짜 형식 지원 (yyyy-MM-dd'T'HH:mm:ss 또는 yyyy-MM-dd'T'HH:mm:ss.SSS)
                val cleanDateString = dateString.substringBefore(".") // 밀리초 제거
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                inputFormat.timeZone = TimeZone.getTimeZone("UTC")
                
                // 한국 시간(KST)으로 포맷팅
                val outputFormat = SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm", Locale.getDefault())
                outputFormat.timeZone = TimeZone.getTimeZone("Asia/Seoul")
                
                val date = inputFormat.parse(cleanDateString)
                date?.let { outputFormat.format(it) } ?: dateString
            } catch (e: Exception) {
                // 파싱 실패 시 원본 문자열 반환
                dateString
            }
        }
    }

    class PaymentHistoryDiffCallback : DiffUtil.ItemCallback<PaymentResponseDto>() {
        override fun areItemsTheSame(oldItem: PaymentResponseDto, newItem: PaymentResponseDto): Boolean {
            return oldItem.paymentId == newItem.paymentId
        }

        override fun areContentsTheSame(oldItem: PaymentResponseDto, newItem: PaymentResponseDto): Boolean {
            return oldItem == newItem
        }
    }
}

