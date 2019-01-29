import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { NzMessageService, UploadFile } from 'ng-zorro-antd';
import { PublicFun } from '../public/public.fun';
import { HttpService } from '../public/http.service';
import {ParamsService} from "../public/params.service";


@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  selectTime = null;
  triggerTemplate = null;
  @ViewChild('trigger') customTrigger: TemplateRef<void>;

  /** custom trigger can be TemplateRef **/
  changeTrigger(): void {
    this.triggerTemplate = this.customTrigger;
  }
  ngStyle = {
    'margin-top':'64px',
    // 'min-height':document.documentElement.clientHeight - 134+'px'
    'min-height':document.documentElement.clientHeight - 134 +69 +'px', //无底部固定块
    'minHeight':document.documentElement.clientHeight - 134 +69 //无底部固定块
  };

  ngOnInit(): void {
    this.paramsService.getParamsSubject().subscribe(next=>{
      this.clickTime(next);
    });
  }
  selectedYear = PublicFun.Today.getFullYear();

  todayYear = PublicFun.Today.getFullYear();

  selectedIndex = PublicFun.Today.getMonth();
  todayMonth = PublicFun.Today.getMonth();
  // popTitle = '';

  getPopTitle(){
    let m = this.todayMonth ==0?11:this.todayMonth;
    let y = this.todayMonth ==0?this.todayYear -1:this.todayYear;
    let lastTime = y + '-'+(m<10?'0'+m:m);
    let selectm = ((this.selectedIndex+1)<10?'0'+(this.selectedIndex+1):(this.selectedIndex+1));
    let selectTime= this.selectedYear+'-'+selectm;
    return `确认:上传报表时间${ this.selectedYear }取消:上传报表时间${ y }`
  }

  private clickTime(next){
    if(next.type == 0){
      this.selectedYear = PublicFun.Today.getFullYear();
      this.selectedIndex = PublicFun.Today.getMonth();
    }else if(next.type == -1){
      this.selectedIndex = next.month
    }else if(next.type == 1 || next.type == 2){
      this.selectedIndex = next.month.key;
      this.selectedYear = next.year;
    }
  }

  onResize(ev){
    // document.documentElement.clientWidth;  // 可见区域宽度
    // document.documentElement.clientHeight - 134;  // 可见区域高度
    this.ngStyle['min-height'] = document.documentElement.clientHeight - 134 +69 + 'px'; //无底部固定块
  }

  uploading = false;
  fileList1= [];
  fileList2= [];

  constructor(private http: HttpService, private msg: NzMessageService,private paramsService:ParamsService) {}

  beforeUpload1 = (file: UploadFile): boolean => {
    file.url = PublicFun.IP_AND_PORT+'static/image/excel.png';
    this.fileList1.push(file);
    return false;
  };
  beforeUpload2 = (file: UploadFile): boolean => {
    file.url = PublicFun.IP_AND_PORT+'static/image/excel.png';
    this.fileList2.push(file);
    return false;
  };

  handleUpload(type): void {

    let m = this.todayMonth ==0?11:this.todayMonth;
    let y = this.todayMonth ==0?this.todayYear -1:this.todayYear;
    let lastTime = y + '-'+(m<10?'0'+m:m);
    let selectm = ((this.selectedIndex+1)<10?'0'+(this.selectedIndex+1):(this.selectedIndex+1));
    let selectTime= this.selectedYear+'-'+selectm;
    let time = '';
    if(type == 2){
      time = lastTime
    }else {
      time = selectTime
    }
    const formData = new FormData();
    this.fileList1.forEach((file: any) => {
      formData.append('fileName', file);
    });
    this.fileList2.forEach((file: any) => {
      formData.append('fileName', file);
    });
    formData.append('time', time);
    formData.append('switch',this.switchValue+''); //'true' || 'false'
    this.http
      .addProject(formData)
      .subscribe(
        (event) => {
          console.log(event);
          this.uploading = false;

          if(event.code == 0){
            this.msg.success(event.msg);
            let next = {
              type:-2,
              year:time.substring(0,4),
              month:time.substring(5,7)
            };
            this.paramsService.sendParamsSubject(next)
          }else{
            this.msg.error(event.msg);
          }
        },
        err => {
          this.uploading = false;
          this.msg.error('上传失败.');
        }
      );
  }

  switchValue = true;

}
