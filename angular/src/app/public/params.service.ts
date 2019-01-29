import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs/index';



@Injectable()
export class ParamsService {
  private paramsSubject = new Subject<string>();


  constructor() { }

  sendParamsSubject(value:any) {
    this.paramsSubject.next(value);
  }

  getParamsSubject(): Observable<any> {
    return this.paramsSubject.asObservable();
  }
}
