import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BaseApiResponseModel, url } from './index';
import { Observable } from 'rxjs/index';


@Injectable()
export class HttpService {
  constructor(private http: HttpClient) { }
/*
  delPfaccountInfoByPfacid(pfacid): Observable<BaseApiResponseModel> {
    return this.http.get<BaseApiResponseModel>(url.delbypfacid,{
      params:{
        pfacid
      }
    });
  }

  upDatePlatInfo(platInfo:PlatInfo): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.updatebypfid,platInfo);
  }
  */
  findbydate(date:{date:string}): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.findbydate,date);
  }

  addProject(form:FormData): Observable<BaseApiResponseModel> {
    return this.http.post<BaseApiResponseModel>(url.addProject,form);
  }
}
