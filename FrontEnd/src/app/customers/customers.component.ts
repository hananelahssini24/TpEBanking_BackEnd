import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { Customer } from '../model/customer.model';
import { CustomerService } from '../services/customer.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent implements OnInit {
  customers! : Observable<Array<Customer>>;
  errorMessage!: string;
  searchFormGroup!:FormGroup;
  constructor(public customerService:CustomerService,private fb : FormBuilder, private router : Router,public authService: AuthService){

  }
  ngOnInit(): void {
    this.searchFormGroup=this.fb.group({
      keyword : this.fb.control("")
    });
    this.handleSearchCustomers();
    // this.customers=this.customerService.getCustomers().pipe(
    //   catchError(err => {
    //     this.errorMessage=err.message;
    //     return throwError(err);
    //   })
    // );
    
  }
  handleSearchCustomers() {
    let kw=this.searchFormGroup?.value.keyword;
    this.customers=this.customerService.searchCustomers(kw).pipe(
      catchError(err => {
        this.errorMessage=err.message;
        return throwError(err);
      })
    );
  }

  handleDeleteCustomer(c: Customer) {
    let conf = confirm("Are you sure?");
    if(!conf) return;
    this.customerService.deleteCustomer(c.id).subscribe({
      next : (resp) => {
        this.customers=this.customers.pipe(
          map(data=>{
            let index=data.indexOf(c);
            data.slice(index,1)
            return data;
          })
        );
      },
      error : err => {
        console.log(err);
      }
    })
  }
  handleCustomerAccounts(customer: Customer) {
    this.router.navigateByUrl("/admin/customer-accounts/"+customer.id,{state :customer});
  }
}
