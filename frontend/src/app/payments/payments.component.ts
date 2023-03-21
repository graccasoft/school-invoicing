import { formatCurrency, formatDate } from '@angular/common';
import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { Payment } from '../model/payment';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent {

  payments!: Payment[]
  
  constructor(private apiService: ApiService){}

  ngOnInit(){
    this.fetchPayments()
  }

  fetchPayments(){
    this.apiService.fetchAllPayments().subscribe(payments=>{
      payments.map((payment, index)=>{
        payments[index].formattedAmount = formatCurrency(payment.amount,"en_ZW","$")
        payments[index].createdAt = formatDate(payment.createdAt,"dd MMM yyyy","en_ZW")
      })
      this.payments = payments
    })
  }
}
