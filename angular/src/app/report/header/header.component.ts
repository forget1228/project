import { Component, OnInit} from '@angular/core';
import { monthList } from '../../public/index';
import { ParamsService } from '../../public/params.service';
import { PublicFun } from '../../public/public.fun';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  hostPort = PublicFun.IP_AND_PORT;
  // monthList = montheng;

  monthObj = {
    src :monthList.montheng[PublicFun.Today.getMonth()],
    key:PublicFun.Today.getMonth()
  };
  yearString = PublicFun.Today.getFullYear();

  today = PublicFun.Today;

  toDay(){
    this.monthObj.key = this.today.getMonth();
    this.monthObj.src = monthList.montheng[this.today.getMonth()];
    this.yearString = this.today.getFullYear();
    let params = {
      type:0,
      value:this.yearString
    };
    this.paramsService.sendParamsSubject(params);
  }

  constructor(private paramsService:ParamsService) { }

  ngOnInit() {
    this.paramsService.getParamsSubject().subscribe(next=>{
      this.clickContent(next);
    });
  }
  clickContent(next){
    if(next.type == -1){
      this.monthObj.key = next.month;
      this.monthObj.src = monthList.montheng[this.monthObj.key];
    }else if(next.type == -2){
      this.yearString = next.year;
      this.monthObj.key =(next.month)<10?(next.month-1):(next.month-1);
      this.monthObj.src = monthList.montheng[monthList.months.indexOf(next.month)];
      //console.info(this.monthObj.key);
    }
  }

  clickLeftFun(){
    // this.yearString = this.monthObj.key == 0?this.yearString-1:this.yearString;
    // this.monthObj.key = this.monthObj.key == 0?11:this.monthObj.key-1;
    // this.monthObj.src = monthList.montheng[this.monthObj.key];
    this.yearString = this.yearString-1;
    let params = {
      type:1,
      year:this.yearString,
      month:this.monthObj
    };
    this.paramsService.sendParamsSubject(params);
  }

  clickRightFun(){
   /* this.yearString = this.monthObj.key == 11?this.yearString+1:this.yearString;
    this.monthObj.key = this.monthObj.key == 11?0:this.monthObj.key+1;
    this.monthObj.src = monthList.montheng[this.monthObj.key];*/
    this.yearString = parseInt(this.yearString+"")+1;
    let params = {
      type:2,
      year:this.yearString,
      month:this.monthObj
    };
    this.paramsService.sendParamsSubject(params);
  }
}
