import { Component, Input, OnInit } from '@angular/core';
import { monthList } from '../../public/index';
import { ParamsService } from '../../public/params.service';
import { HttpService } from '../../public/http.service';
import { PublicFun } from '../../public/public.fun';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
  tableLoading = false;
  @Input() maxHeight;
  selectedIndex = PublicFun.Today.getMonth();
  selectedYear = PublicFun.Today.getFullYear();
  monthList = monthList.monthchinaese;
  dataSet = [];
  total = null;

  constructor(private paramsService:ParamsService,private http:HttpService) { }

  ngOnInit() {
    this.paramsService.getParamsSubject().subscribe(next=>{
        this.clickHeader(next);
    });
    let body = {
      date:PublicFun.dateFormat(PublicFun.Today,'yyyy-MM')
    };
    this.findbydate(body);
  }

  private findbydate(body){
    this.tableLoading = true;
    this.http.findbydate(body).subscribe(result=>{
      this.tableLoading = false;
      console.log(result);
      if (result.code != 0) {
        return;
      }
      this.dataSet = result.data.list == null ? [] : result.data.list;
      //this.total = result.data.sum == null ? null:result.data.sum;
      for (let t of this.dataSet) {
        let salary = t.wage_base_total;
        let cost = t.company_total;
        if (salary == null) {
          salary = 0.0;
        }
        if (cost == '' || cost == null) {
          cost = 0.0;
        }
        t.count = (parseFloat(salary) + parseFloat(cost)).toFixed(2)
        t.count = t.count * 100/100;
      }
    })
  }

  private clickHeader(next){
    if(next.type == 0){
      this.selectedYear = PublicFun.Today.getFullYear();
      this.selectedIndex = PublicFun.Today.getMonth();
    }else if(next.type == -1){}
    else if(next.type == 1 || next.type == 2){
      this.selectedIndex = next.month.key;
      this.selectedYear = next.year;
    }else if(next.type == -2){
      this.selectedIndex = next.month-1;
    }

    if(next.type != -1){
      let y = this.selectedYear;
      let m:any ;
      m = this.selectedIndex + 1
      m = m < 10?0+''+m:m;

      let body = {
        date:y +'-'+m
      };
      this.findbydate(body);
    }
  }

  tabClick(ev){
    let y = this.selectedYear;
    let m:any = ev+1;
    m = m < 10?0+''+m:m;
    let body = {
      date:y +'-'+m
    };
    this.findbydate(body);

    let params = {
      type:-1,
      month:ev
    };
    this.paramsService.sendParamsSubject(params);
  }
}
